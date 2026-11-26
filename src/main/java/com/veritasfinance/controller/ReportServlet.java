package com.veritasfinance.controller;

import java.io.IOException;

import com.veritasfinance.model.Document;
import com.veritasfinance.model.TrustScore;
import com.veritasfinance.service.ReportService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/report")
public class ReportServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        Document document = new Document();

        document.setMonthlyIncome(
                Double.parseDouble(request.getParameter("monthlyIncome")));

        document.setMonthlySavings(
                Double.parseDouble(request.getParameter("monthlySavings")));

        document.setBillPaymentStatus(
                request.getParameter("billPaymentStatus"));

        document.setLoanStatus(
                request.getParameter("loanStatus"));

        TrustScore score = new TrustScore();

        score.setIncomeScore(Integer.parseInt(request.getParameter("incomeScore")));
        score.setSavingsScore(Integer.parseInt(request.getParameter("savingsScore")));
        score.setPaymentScore(Integer.parseInt(request.getParameter("paymentScore")));
        score.setDebtScore(Integer.parseInt(request.getParameter("debtScore")));
        score.setTotalScore(Integer.parseInt(request.getParameter("totalScore")));

        ReportService service = new ReportService();

        String report = service.generateReport(document, score);

        response.setContentType("text/plain");
        response.getWriter().print(report);
    }
}