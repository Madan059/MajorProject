package com.Controller;

import java.io.IOException;
import java.util.List;

import com.DAOImpl.MenuDaoImpl;
import com.DAOModel.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        // 🔹 Step 1: Get restaurantId from parameter (clicked restaurant or "Add More Items" button)
        String restIdParam = request.getParameter("RestorantId");

        int restorantId = 0;

        if (restIdParam != null && !restIdParam.trim().isEmpty()) {
            try {
                restorantId = Integer.parseInt(restIdParam);
                // ✅ Update session with this restaurantId
                session.setAttribute("RestorantId", restorantId);
            } catch (NumberFormatException e) {
                System.out.println("Invalid RestorantId format. Redirecting...");
                response.sendRedirect("Welcome.jsp");
                return;
            }
        } 
        else {
            // 🔹 If parameter is missing, use the one stored in session (from Cart → Add More Items)
            Object storedId = session.getAttribute("RestorantId");
            if (storedId != null) {
                restorantId = (int) storedId;
            } else {
                System.out.println("No restaurant ID found in session or request. Redirecting...");
                response.sendRedirect("Welcome.jsp");
                return;
            }
        }

        // 🔹 Step 2: Fetch menu items for that restaurant
        MenuDaoImpl menuDao = new MenuDaoImpl();
        List<Menu> menuList = menuDao.fetchMenuByRestorantId(restorantId);

        // 🔹 Step 3: Handle menu not found case
        if (menuList == null || menuList.isEmpty()) {
            request.setAttribute("message", "No menu items found for this restaurant.");
        } else {
            request.setAttribute("menuList", menuList);
        }

        // 🔹 Step 4: Forward to JSP
        request.getRequestDispatcher("Menu.jsp").forward(request, response);
    }
}
