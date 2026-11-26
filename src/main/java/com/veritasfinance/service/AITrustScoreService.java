package com.veritasfinance.service;

import com.veritasfinance.model.Document;

public class AITrustScoreService {

    public int calculateTrustScore(Document document) {

        double income = document.getMonthlyIncome();
        double savings = document.getMonthlySavings();

        if (income <= 0) {
            return 0;
        }

        double savingsRate = (savings / income) * 100;

        int score = 40;

        // Income Score
        if (income >= 100000) {
            score += 20;
        } else if (income >= 50000) {
            score += 15;
        } else if (income >= 30000) {
            score += 10;
        } else {
            score += 5;
        }

        // Savings Score
        if (savingsRate >= 50) {
            score += 40;
        } else if (savingsRate >= 30) {
            score += 25;
        } else if (savingsRate >= 20) {
            score += 15;
        } else {
            score += 5;
        }

        if (score > 100) {
            score = 100;
        }

        return score;
    }
}