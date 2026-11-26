package com.veritasfinance.ai;

public class GeminiService {

    public String getAdvice(int trustScore) {

        if (trustScore >= 90) {
            return "Excellent financial profile. You are eligible for premium loans and financial products.";
        }

        if (trustScore >= 75) {
            return "Good financial profile. Continue maintaining savings and timely bill payments.";
        }

        if (trustScore >= 60) {
            return "Average financial profile. Improve savings habits and reduce debt.";
        }

        return "Low financial profile. Focus on paying bills on time and increasing monthly savings.";
    }
}