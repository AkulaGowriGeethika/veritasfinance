package com.veritasfinance.service;

import com.veritasfinance.model.Document;

public class AnalyticsService {

    public String getFinancialStatus(Document document) {

        double income = document.getMonthlyIncome();
        double savings = document.getMonthlySavings();

        if (income <= 0) {
            return "Invalid Income";
        }

        double rate = (savings / income) * 100;

        if (rate >= 50) {
            return "Excellent Financial Health";
        }
        else if (rate >= 30) {
            return "Good Financial Health";
        }
        else if (rate >= 20) {
            return "Average Financial Health";
        }
        else {
            return "Needs Financial Improvement";
        }
    }

    public String getAIAdvice(Document document) {

        double income = document.getMonthlyIncome();
        double savings = document.getMonthlySavings();

        if (income <= 0) {
            return "Enter valid income.";
        }

        double rate = (savings / income) * 100;

        if (rate >= 50) {
            return "Excellent! Keep investing regularly.";
        }
        else if (rate >= 30) {
            return "Good job! Try increasing your investments gradually.";
        }
        else if (rate >= 20) {
            return "Reduce unnecessary expenses and increase savings.";
        }
        else {
            return "Your savings are low. Create a monthly budget and save at least 20% of your income.";
        }
    }

    public double getSavingsRate(Document document) {

        if (document.getMonthlyIncome() == 0) {
            return 0;
        }

        return (document.getMonthlySavings() /
                document.getMonthlyIncome()) * 100;
    }
}