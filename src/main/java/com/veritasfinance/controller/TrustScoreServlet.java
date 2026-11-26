package com.veritasfinance.controller;

import java.io.IOException;

import com.veritasfinance.dao.TrustScoreDAO;
import com.veritasfinance.model.Document;
import com.veritasfinance.model.TrustScore;
import com.veritasfinance.service.TrustScoreService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/trustscore")
public class TrustScoreServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        Document document = new Document();

        document.setUserId(Integer.parseInt(request.getParameter("userId")));
        document.setMonthlyIncome(Double.parseDouble(request.getParameter("monthlyIncome")));
        document.setMonthlySavings(Double.parseDouble(request.getParameter("monthlySavings")));
        document.setBillPaymentStatus(request.getParameter("billPaymentStatus"));
        document.setLoanStatus(request.getParameter("loanStatus"));

        TrustScoreService service = new TrustScoreService();

        TrustScore score = service.calculate(document);

        TrustScoreDAO dao = new TrustScoreDAO();

        dao.saveScore(score);

        request.setAttribute("score", score);

        request.getRequestDispatcher("dashboard.html").forward(request, response);

    }

}