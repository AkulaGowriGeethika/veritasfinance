package com.veritasfinance.controller;

import java.io.IOException;

import com.veritasfinance.ai.GeminiAPIService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/gemini")
public class GeminiAPIServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        int score = Integer.parseInt(request.getParameter("score"));

        GeminiAPIService service = new GeminiAPIService();

        String advice = service.generateAdvice(score);

        response.setContentType("text/html");

        response.getWriter().println("<html><body>");

        response.getWriter().println("<h2>Gemini AI Financial Advisor</h2>");

        response.getWriter().println("<h3>Your Trust Score : " + score + "</h3>");

        response.getWriter().println("<p>" + advice + "</p>");

        response.getWriter().println("<br>");

        response.getWriter().println("<a href='ai.html'>Back</a>");

        response.getWriter().println("</body></html>");

    }

}