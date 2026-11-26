package com.veritasfinance.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.veritasfinance.model.Document;
import com.veritasfinance.service.AnalyticsService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/analytics")
public class AnalyticsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String incomeParam = request.getParameter("monthlyIncome");
        String savingsParam = request.getParameter("monthlySavings");

        double income = 0;
        double savings = 0;

        try {
            income = Double.parseDouble(incomeParam);
            savings = Double.parseDouble(savingsParam);
        } catch (Exception e) {
            response.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "Please enter valid income and savings values."
            );
            return;
        }

        Document document = new Document();

        document.setMonthlyIncome(income);
        document.setMonthlySavings(savings);

        AnalyticsService service = new AnalyticsService();

        String status = service.getFinancialStatus(document);
        String advice = service.getAIAdvice(document);
        double savingsRate = service.getSavingsRate(document);

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>Financial Analytics</title>");

        out.println("<style>");

        out.println("body {");
        out.println("font-family: Arial, sans-serif;");
        out.println("background: #eef3fb;");
        out.println("margin: 0;");
        out.println("padding: 40px;");
        out.println("}");

        out.println(".container {");
        out.println("max-width: 1000px;");
        out.println("margin: auto;");
        out.println("}");

        out.println("h1 {");
        out.println("text-align: center;");
        out.println("color: #1d5fbf;");
        out.println("margin-bottom: 40px;");
        out.println("}");

        out.println(".cards {");
        out.println("display: flex;");
        out.println("justify-content: center;");
        out.println("gap: 20px;");
        out.println("flex-wrap: wrap;");
        out.println("}");

        out.println(".card {");
        out.println("background: white;");
        out.println("width: 230px;");
        out.println("padding: 25px;");
        out.println("border-radius: 12px;");
        out.println("box-shadow: 0 5px 15px rgba(0,0,0,0.15);");
        out.println("text-align: center;");
        out.println("}");

        out.println(".card h2 {");
        out.println("color: #1d5fbf;");
        out.println("font-size: 18px;");
        out.println("}");

        out.println(".value {");
        out.println("font-size: 28px;");
        out.println("font-weight: bold;");
        out.println("color: #222;");
        out.println("}");

        out.println(".advice {");
        out.println("margin-top: 30px;");
        out.println("background: white;");
        out.println("padding: 25px;");
        out.println("border-radius: 12px;");
        out.println("box-shadow: 0 5px 15px rgba(0,0,0,0.15);");
        out.println("}");

        out.println(".advice h2 {");
        out.println("color: #1d5fbf;");
        out.println("}");

        out.println(".buttons {");
        out.println("text-align: center;");
        out.println("margin-top: 30px;");
        out.println("}");

        out.println(".button {");
        out.println("display: inline-block;");
        out.println("padding: 12px 20px;");
        out.println("margin: 8px;");
        out.println("background: #1d5fbf;");
        out.println("color: white;");
        out.println("text-decoration: none;");
        out.println("border-radius: 8px;");
        out.println("}");

        out.println("</style>");

        out.println("</head>");
        out.println("<body>");

        out.println("<div class='container'>");

        out.println("<h1>Financial Analytics</h1>");

        out.println("<div class='cards'>");

        out.println("<div class='card'>");
        out.println("<h2>Monthly Income</h2>");
        out.println("<p class='value'>₹ "
                + String.format("%,.0f", income)
                + "</p>");
        out.println("</div>");

        out.println("<div class='card'>");
        out.println("<h2>Monthly Savings</h2>");
        out.println("<p class='value'>₹ "
                + String.format("%,.0f", savings)
                + "</p>");
        out.println("</div>");

        out.println("<div class='card'>");
        out.println("<h2>Savings Rate</h2>");
        out.println("<p class='value'>"
                + String.format("%.1f", savingsRate)
                + "%</p>");
        out.println("</div>");

        out.println("<div class='card'>");
        out.println("<h2>Financial Health</h2>");
        out.println("<p class='value'>"
                + escapeHtml(status)
                + "</p>");
        out.println("</div>");

        out.println("</div>");

        out.println("<div class='advice'>");
        out.println("<h2>AI Financial Advice</h2>");
        out.println("<p>" + escapeHtml(advice) + "</p>");
        out.println("</div>");

        out.println("<div class='buttons'>");

        out.println("<a class='button' href='analytics.html'>Analyze Again</a>");

        out.println("<a class='button' href='dashboard.html'>Back to Dashboard</a>");

        out.println("</div>");

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