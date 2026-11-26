package com.veritasfinance.controller;

import com.veritasfinance.dao.UserDAO;
import com.veritasfinance.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.regex.Pattern;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final Pattern EMAIL = Pattern.compile("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");
    private static final Pattern PHONE = Pattern.compile("^[0-9]{10}$");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String fullName = trim(request.getParameter("fullName"));
        String email = trim(request.getParameter("email")).toLowerCase();
        String phone = trim(request.getParameter("phone"));
        String password = request.getParameter("password");
        String language = trim(request.getParameter("language"));

        if (fullName.length() < 2 || fullName.length() > 100 ||
                !EMAIL.matcher(email).matches() || email.length() > 150 ||
                !PHONE.matcher(phone).matches() || password == null ||
                password.length() < 8 || password.length() > 72 ||
                language.isBlank()) {
            response.sendRedirect("register.html?error=invalid");
            return;
        }

        UserDAO dao = new UserDAO();
        if (dao.emailExists(email)) {
            response.sendRedirect("register.html?error=exists");
            return;
        }

        User user = new User();
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(password);
        user.setLanguage(language);
        user.setVoiceEnabled("on".equals(request.getParameter("voice")));

        if (dao.registerUser(user)) {
            response.sendRedirect("login.html?registered=1");
        } else {
            response.sendRedirect("register.html?error=failed");
        }
    }

    private String trim(String value) {
        return value == null ? "" : value.trim();
    }
}
