package com.Controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Map;

import com.DAOImpl.OrderHistoryDaoImpl;
import com.DAOImpl.OrderItemsDaoImpl;
import com.DAOImpl.OrdersDaoImpl;
import com.DAOModel.Cart;
import com.DAOModel.CartItem;
import com.DAOModel.OrderHistory;
import com.DAOModel.OrderItems;
import com.DAOModel.Orders;
import com.DAOModel.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    private OrdersDaoImpl orderDao;
    private OrderItemsDaoImpl orderItemsDao;
    private OrderHistoryDaoImpl orderHistoryDao;

    @Override
    public void init() throws ServletException {
        orderDao = new OrdersDaoImpl();
        orderItemsDao = new OrderItemsDaoImpl();
        orderHistoryDao = new OrderHistoryDaoImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null || session.getAttribute("cart") == null) {
            resp.sendRedirect("Welcome.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart.getItems() == null || cart.getItems().isEmpty()) {
            resp.sendRedirect("Welcome.jsp");
            return;
        }

        String fullName = req.getParameter("fullName");
        String phoneNumber = req.getParameter("phoneNumber");
        String deliveryAddress = req.getParameter("deliveryAddress");
        String paymentMethod = req.getParameter("paymentMethod");

        Object restIdObj = session.getAttribute("RestorantId");
        if (restIdObj == null) {
            resp.sendRedirect("Menu.jsp");
            return;
        }

        int restorantId = (int) restIdObj;
        double totalAmount = 0;
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis()); // ✅ Shared timestamp

        try {
            Orders order = new Orders();
            order.setUserId(user.getUserID());
            order.setRestorantId(restorantId);
            order.setOrderDate(new Timestamp(System.currentTimeMillis()));
            order.setPaymentMode(paymentMethod);
            order.setStatus("Pending");

            for (CartItem item : cart.getItems().values()) {
                totalAmount += item.getPrice() * item.getQuantity();
            }
            order.setTotalAmount(totalAmount);

            int generatedOrderId = orderDao.addOrder(order);
            order.setOrderId(generatedOrderId);

            for (CartItem item : cart.getItems().values()) {
                OrderItems orderItem = new OrderItems();
                orderItem.setOrderId(generatedOrderId);
                orderItem.setMenuId(item.getItemId());
                orderItem.setQuantity(item.getQuantity());
                orderItem.setItemTotal(item.getPrice() * item.getQuantity());

                orderItemsDao.addOrderItem(orderItem);

                // ✅ Insert into OrderHistory per item
                OrderHistory orderHistory = new OrderHistory();
                orderHistory.setMenuId(item.getItemId());
                orderHistory.setOrderId(generatedOrderId);
                orderHistory.setUserId(user.getUserID());
                orderHistory.setTotalAmount(item.getPrice() * item.getQuantity());
                orderHistory.setStatus("Placed");
                orderHistory.setDateAndTime(currentTimestamp); // ✅ Set DateAndTime

                int rowsInserted = orderHistoryDao.insert(orderHistory);
                if (rowsInserted > 0) {
                    System.out.println("✅ OrderHistory inserted for MenuId: " + item.getItemId());
                } else {
                    System.out.println("❌ Failed to insert OrderHistory for MenuId: " + item.getItemId());
                }
            }

            session.removeAttribute("cart");
            session.setAttribute("order", order);
            session.setAttribute("fullName", fullName);
            session.setAttribute("phoneNumber", phoneNumber);
            session.setAttribute("deliveryAddress", deliveryAddress);

            resp.sendRedirect("order_confirmation.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("Error.jsp");
        }
    }
}
