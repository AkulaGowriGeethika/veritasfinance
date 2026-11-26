package com.veritasfinance.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.veritasfinance.service.AIChatService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/aichat")
public class AIChatServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String question = request.getParameter("question");

        if (question == null || question.trim().isEmpty()) {
            question = "Please enter a financial question.";
        }

        AIChatService service = new AIChatService();
        String answer = service.getResponse(question);

        if (answer == null || answer.trim().isEmpty()) {
            answer = "Sorry, I could not generate a response.";
        }

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>AI Financial Assistant</title>");

        out.println("<style>");
        out.println("body{font-family:Arial;background:#eef3fb;padding:40px;}");
        out.println(".container{max-width:700px;margin:auto;background:white;padding:30px;border-radius:12px;box-shadow:0 5px 15px rgba(0,0,0,.2);}");
        out.println("h1{text-align:center;color:#1d5fbf;}");
        out.println(".box{margin-top:25px;padding:20px;background:#f4f8ff;border-radius:10px;}");
        out.println("h3{color:#1d5fbf;}");
        out.println("a{display:inline-block;margin-top:25px;padding:12px 20px;background:#1d5fbf;color:white;text-decoration:none;border-radius:8px;}");
        out.println("</style>");

        out.println("</head>");
        out.println("<body>");

        out.println("<div class='container'>");

        out.println("<h1>AI Financial Assistant</h1>");

        out.println("<div class='box'>");
        out.println("<h3>Your Question</h3>");
        out.println("<p>" + escapeHtml(question) + "</p>");
        out.println("</div>");

        out.println("<div class='box'>");
        out.println("<h3>AI Response</h3>");
        out.println("<p>" + escapeHtml(answer) + "</p>");
        out.println("</div>");

        out.println("<a href='aichat.html'>Ask Another Question</a>");
        out.println("<br><br>");
        out.println("<a href='dashboard.html'>Back to Dashboard</a>");

        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

    private String escapeHtml(String text) {
        if (text == null) {
            return "";
        }

        return text.replace("&", "&amp;")
                   .replace("<", "&lt;")
                   .replace(">", "&gt;")
                   .replace("\"", "&quot;")
                   .replace("'", "&#39;");
    }
}