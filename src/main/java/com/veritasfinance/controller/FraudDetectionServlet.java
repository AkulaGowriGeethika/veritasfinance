package com.veritasfinance.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.veritasfinance.service.FraudDetectionService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/fraud")
public class FraudDetectionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        double actualIncome =
                Double.parseDouble(request.getParameter("actualIncome"));

        double claimedIncome =
                Double.parseDouble(request.getParameter("claimedIncome"));

        String aadhaar = request.getParameter("aadhaar");
        String pan = request.getParameter("pan");

        FraudDetectionService service = new FraudDetectionService();

        int risk = service.calculateRisk(actualIncome,
                claimedIncome,
                aadhaar,
                pan);

        String level = service.getRiskLevel(risk);
        String advice = service.getAdvice(risk);

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Fraud Detection Report</title>");

        out.println("<style>");
        out.println("body{font-family:Arial;background:#eef3fb;padding:40px;}");
        out.println(".container{max-width:900px;margin:auto;background:white;padding:35px;border-radius:12px;box-shadow:0 5px 15px rgba(0,0,0,.2);}");
        out.println("h1{text-align:center;color:#1d5fbf;margin-bottom:35px;}");
        out.println(".cards{display:flex;justify-content:center;gap:20px;flex-wrap:wrap;}");
        out.println(".card{background:#f4f8ff;width:220px;padding:25px;border-radius:10px;box-shadow:0 3px 10px rgba(0,0,0,.15);text-align:center;}");
        out.println(".card h2{color:#1d5fbf;font-size:24px;}");
        out.println(".card p{font-size:30px;font-weight:bold;}");
        out.println(".btn{text-align:center;margin-top:30px;}");
        out.println("a{background:#1d5fbf;color:white;padding:12px 22px;text-decoration:none;border-radius:8px;margin:8px;display:inline-block;}");
        out.println("</style>");

        out.println("</head>");
        out.println("<body>");

        out.println("<div class='container'>");
        out.println("<h1>AI Fraud Detection Report</h1>");

        out.println("<div class='cards'>");

        out.println("<div class='card'>");
        out.println("<h2>Risk Score</h2>");
        out.println("<p>" + risk + "%</p>");
        out.println("</div>");

        out.println("<div class='card'>");
        out.println("<h2>Risk Level</h2>");
        out.println("<p>" + level + "</p>");
        out.println("</div>");

        out.println("<div class='card'>");
        out.println("<h2>AI Advice</h2>");
        out.println("<p style='font-size:18px'>" + advice + "</p>");
        out.println("</div>");

        out.println("</div>");

        out.println("<div class='btn'>");
        out.println("<a href='frauddetection.html'>Check Again</a>");
        out.println("<a href='dashboard.html'>Dashboard</a>");
        out.println("</div>");

        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}