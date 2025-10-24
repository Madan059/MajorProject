package com.Controller;

import java.io.IOException;
import com.DAOModel.User;
import com.secure.MyDecrypt;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");
        String name=MyDecrypt.decrypt(user.getUsername());
        String email=MyDecrypt.decrypt(user.getEmail());
        String address=MyDecrypt.decrypt(user.getAddress());
        
        request.setAttribute("name", name);
        request.setAttribute("email", email);
        request.setAttribute("address", address);
        

        RequestDispatcher rd = request.getRequestDispatcher("Profile.jsp");
        rd.forward(request, response);
    }
}