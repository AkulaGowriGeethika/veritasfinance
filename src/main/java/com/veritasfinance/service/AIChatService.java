package com.veritasfinance.service;

public class AIChatService {

    public String getResponse(String question) {

        question = question.toLowerCase();

        if (question.contains("loan")) {
            return "Based on your Trust Score, you may be eligible for a personal loan.";
        }

        if (question.contains("save")) {
            return "Try saving at least 20% of your monthly income.";
        }

        if (question.contains("trust")) {
            return "Improving your savings and uploading verified documents can increase your Trust Score.";
        }

        if (question.contains("investment")) {
            return "Diversify your investments across fixed deposits, mutual funds, and emergency savings.";
        }

        return "I'm your AI Financial Assistant. Ask me about loans, savings, trust score, or investments.";
    }
}