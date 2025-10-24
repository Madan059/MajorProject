package com.Controller;

import java.io.IOException;

import com.DAOImpl.MenuDaoImpl;
import com.DAOModel.Cart;
import com.DAOModel.CartItem;
import com.DAOModel.Menu;
import com.DaoInterfaces.MenuDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("🔧 CartServlet: Received POST request");

        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
            System.out.println("🛒 New cart created and added to session");
        }

        String action = req.getParameter("action");
        String itemIdParam = req.getParameter("itemId");

        System.out.println("📦 Action: " + action);
        System.out.println("📦 itemIdParam: " + itemIdParam);

        if (action == null || itemIdParam == null) {
            System.out.println("⚠️ Missing action or itemId");
            resp.sendRedirect("error.jsp");
            return;
        }

        int itemId;
        try {
            itemId = Integer.parseInt(itemIdParam);
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid itemId format");
            resp.sendRedirect("error.jsp");
            return;
        }

        MenuDao menuDao = new MenuDaoImpl();

        try {
            switch (action) {
                case "add":
                    Menu menuItem = menuDao.fetchOne(itemId);
                    System.out.println("🔍 Fetched menu item: " + menuItem);

                    if (menuItem != null) {
                        CartItem cartItem = new CartItem(
                                menuItem.getMenuId(),
                                menuItem.getRestorantId(),
                                menuItem.getName(),
                                1,
                                menuItem.getPrice()
                        );
                        cart.addItem(cartItem);
                        session.setAttribute("lastRestorantId", menuItem.getRestorantId());
                        System.out.println("✅ Item added to cart: " + cartItem.getName());
                    } else {
                        System.out.println("❌ Menu item not found for ID: " + itemId);
                    }
                    break;

                case "update":
                    String quantityParam = req.getParameter("quantity");
                    System.out.println("🔄 Update quantity: " + quantityParam);

                    if (quantityParam != null) {
                        int quantity = Integer.parseInt(quantityParam);
                        if (quantity > 0) {
                            cart.updateItem(itemId, quantity);
                            System.out.println("✅ Item updated: ID " + itemId + ", Quantity " + quantity);
                        } else {
                            cart.removeItem(itemId);
                            System.out.println("🗑️ Item removed due to zero quantity: ID " + itemId);
                        }
                    }
                    break;

                case "remove":
                    cart.removeItem(itemId);
                    System.out.println("🗑️ Item removed from cart: ID " + itemId);
                    break;

                default:
                    System.out.println("⚠️ Unknown action: " + action);
                    resp.sendRedirect("error.jsp");
                    return;
            }
        } catch (Exception e) {
            System.out.println("❌ Unexpected error:");
            e.printStackTrace();
        }

        session.setAttribute("cart", cart);
        System.out.println("🛒 Cart size after operation: " + cart.getItems().size());
        resp.sendRedirect("cart.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("🔧 CartServlet: Received GET request");
        req.getRequestDispatcher("cart.jsp").forward(req, resp);
    }
}
