package com.Controller;

import java.io.IOException;
import com.DAOImpl.UserDaoImpl;
import com.DAOModel.User;
import com.secure.MyDecrypt;
import com.secure.MyEncrypt;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            // Encrypt email before checking DB
            String encryptedEmail = MyEncrypt.Encrypt(email);
            UserDaoImpl userDao = new UserDaoImpl();
            User user = userDao.getUserByEmail(encryptedEmail);

            if (user != null) {
                // Decrypt stored password for validation
                String decryptedPass = MyDecrypt.decrypt(user.getPassword());

                if (decryptedPass.equals(password)) {
                    // Store decrypted user in session
                    HttpSession session = request.getSession(true);
                    session.setAttribute("user", user);

                    System.out.println("✅ Login success. Session ID: " + session.getId());
                    System.out.println("User ID being saved: " + user.getUserID());

                    response.sendRedirect("GetAllRestorants");
                    return;
                }
            }

            // Invalid credentials
            request.setAttribute("error", "Invalid email or password");
            request.getRequestDispatcher("Login.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Login failed due to server error");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
    }
}
