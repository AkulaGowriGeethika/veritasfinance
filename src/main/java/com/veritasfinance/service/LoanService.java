package com.veritasfinance.service;

public class LoanService {

    public boolean isEligible(int trustScore) {
        return trustScore >= 70;
    }

    public double getMaximumLoan(double income, int trustScore) {

        if (trustScore >= 90)
            return income * 60;

        if (trustScore >= 80)
            return income * 48;

        if (trustScore >= 70)
            return income * 36;

        if (trustScore >= 60)
            return income * 24;

        return income * 12;
    }

    public double getInterestRate(int trustScore) {

        if (trustScore >= 90)
            return 7.5;

        if (trustScore >= 80)
            return 8.0;

        if (trustScore >= 70)
            return 9.0;

        if (trustScore >= 60)
            return 11.0;

        return 14.0;
    }

    public String getRiskLevel(int trustScore) {

        if (trustScore >= 90)
            return "Very Low";

        if (trustScore >= 80)
            return "Low";

        if (trustScore >= 70)
            return "Medium";

        if (trustScore >= 60)
            return "High";

        return "Very High";
    }
}