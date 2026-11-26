package com.veritasfinance.controller;

import com.google.gson.Gson;
import com.veritasfinance.dao.ProfileDAO;
import com.veritasfinance.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private ProfileDAO dao = new ProfileDAO();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if(session == null || session.getAttribute("userId") == null){

            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;

        }

        int userId = (Integer) session.getAttribute("userId");

        User user = dao.getProfile(userId);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().print(new Gson().toJson(user));
    }
}