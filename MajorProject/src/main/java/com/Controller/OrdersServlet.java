package com.Controller;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import com.DAOModel.User;
import com.DAOModel.OrderHistory;
import com.DAOModel.OrderSummary;
import com.DAOImpl.OrderHistoryDaoImpl;

@WebServlet("/OrdersServlet")
public class OrdersServlet extends HttpServlet {
    private OrderHistoryDaoImpl orderHistoryDao;
    
    @Override
    public void init() throws ServletException {
        orderHistoryDao = new OrderHistoryDaoImpl(); // ✅ Initialize DAO
    }


	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");
        int userId = user.getUserID();

        OrderHistoryDaoImpl dao = new OrderHistoryDaoImpl();
        List<OrderHistory> orderList = dao.fetchOrderHistoryByUserId(userId);
        

        request.setAttribute("orders", orderList);
        
        List<OrderSummary> orders = orderHistoryDao.fetchOrderSummariesByUserId(user.getUserID());
        request.setAttribute("orders", orders);

        
        
        
        request.getRequestDispatcher("Orders.jsp").forward(request, response);
    }
}
