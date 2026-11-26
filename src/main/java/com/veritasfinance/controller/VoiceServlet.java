package com.veritasfinance.controller;

import java.io.IOException;

import com.veritasfinance.voice.VoiceService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/voice")
public class VoiceServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String command = request.getParameter("command");

        VoiceService service = new VoiceService();

        String result = service.processCommand(command);

        response.setContentType("text/html");

        response.getWriter().println("<html><body>");

        response.getWriter().println("<h2>Voice Assistant</h2>");

        response.getWriter().println("<h3>"+result+"</h3>");

        response.getWriter().println("<br>");

        response.getWriter().println("<a href='voice.html'>Back</a>");

        response.getWriter().println("</body></html>");

    }

}