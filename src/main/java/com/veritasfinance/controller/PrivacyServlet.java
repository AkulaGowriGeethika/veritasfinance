package com.veritasfinance.controller;

import java.io.IOException;

import com.veritasfinance.privacy.PrivacyService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/privacy")
public class PrivacyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        PrivacyService service = new PrivacyService();

        response.setContentType("text/html");

        response.getWriter().println("<html><head><title>Privacy</title></head><body>");

        response.getWriter().println("<h2>Privacy Center</h2>");

        response.getWriter().println("<p>");
        response.getWriter().println(service.getPrivacyMessage());
        response.getWriter().println("</p>");

        response.getWriter().println("<br>");

        response.getWriter().println("<a href='dashboard.html'>Back to Dashboard</a>");

        response.getWriter().println("</body></html>");

    }

}