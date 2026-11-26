package com.veritasfinance.controller;

import com.veritasfinance.dao.UserDAO;
import com.veritasfinance.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (email == null || email.isBlank() || password == null || password.isEmpty()) {
            response.sendRedirect("login.html?error=invalid");
            return;
        }

        User user = new UserDAO().loginUser(email.trim(), password);
        if (user != null) {
            HttpSession oldSession = request.getSession(false);
            if (oldSession != null) oldSession.invalidate();
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(30 * 60);
            response.sendRedirect("dashboard.html");
        } else {
            response.sendRedirect("login.html?error=invalid");
        }
    }
}
