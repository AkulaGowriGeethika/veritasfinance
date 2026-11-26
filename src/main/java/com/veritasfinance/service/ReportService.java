package com.veritasfinance.service;

import com.veritasfinance.model.Document;
import com.veritasfinance.model.TrustScore;

public class ReportService {

    public String generateReport(Document document, TrustScore score) {

        StringBuilder report = new StringBuilder();

        report.append("========== UNIVERSAL AI FINANCIAL ID ==========\n\n");

        report.append("Monthly Income : ").append(document.getMonthlyIncome()).append("\n");
        report.append("Monthly Savings : ").append(document.getMonthlySavings()).append("\n");
        report.append("Bill Status : ").append(document.getBillPaymentStatus()).append("\n");
        report.append("Loan Status : ").append(document.getLoanStatus()).append("\n\n");

        report.append("Income Score : ").append(score.getIncomeScore()).append("\n");
        report.append("Savings Score : ").append(score.getSavingsScore()).append("\n");
        report.append("Payment Score : ").append(score.getPaymentScore()).append("\n");
        report.append("Debt Score : ").append(score.getDebtScore()).append("\n\n");

        report.append("TOTAL TRUST SCORE : ").append(score.getTotalScore()).append("/100");

        return report.toString();
    }
}