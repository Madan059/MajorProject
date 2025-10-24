package com.Controller;

import java.io.IOException;

import com.DAOImpl.UserDaoImpl;
import com.DAOModel.User;
import com.secure.MyEncrypt;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html;charset=UTF-8");

        String username = req.getParameter("username").trim();
        String email = req.getParameter("email").trim();
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        String address = req.getParameter("address").trim();

        RequestDispatcher rd = req.getRequestDispatcher("register.jsp");

        // Validation
        if (username.isEmpty() || email.isEmpty() || address.isEmpty()) {
            req.setAttribute("message", "All fields are required.");
            rd.forward(req, res);
            return;
        }

        if (!password.equals(confirmPassword)) {
//            req.setAttribute("message", "Passwords do not match.");
        	res.sendRedirect("PasswordMisMatch.jsp");
//            rd.forward(req, res);
            return;
        }
        
        // Encrypt User Data
        String encryptedName = MyEncrypt.Encrypt(username);
        String encryptedEmail = MyEncrypt.Encrypt(email);
        String encryptedPassword = MyEncrypt.Encrypt(password);
        String encryptedAddress = MyEncrypt.Encrypt(address);
        
        UserDaoImpl dao = new UserDaoImpl();

        // 🔍 Check if email already exists
        User existingUser = dao.getUserByEmail(encryptedEmail);  // <-- you'll add this method
        if (existingUser != null) {
            req.setAttribute("message", "Email already registered! .");
            req.getRequestDispatcher("register.jsp");
            rd.forward(req, res);
            return;
        }


        // Create User object
        User user = new User();
        user.setUsername(encryptedName);
        user.setEmail(encryptedEmail);
        user.setPassword(encryptedPassword);
        user.setAddress(encryptedAddress);

        // Save to DB
       
        int result = dao.insert(user);  // assuming insert() returns >0 if success

        if (result > 0) {
            // Store message in request or session
            req.setAttribute("successMessage", "Registration successful! Please login.");
            RequestDispatcher rd2 = req.getRequestDispatcher("Login.jsp");
            rd2.forward(req, res);
        } else {
            req.setAttribute("message", "Registration failed. Please try again.");
            rd.forward(req, res);
        }
        
    }
}
