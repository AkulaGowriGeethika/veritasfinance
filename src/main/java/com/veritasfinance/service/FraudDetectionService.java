package com.veritasfinance.service;

public class FraudDetectionService {

    public int calculateRisk(double actualIncome,
                             double claimedIncome,
                             String aadhaar,
                             String pan) {

        int risk = 0;

        // Income mismatch
        if (claimedIncome > actualIncome * 2) {
            risk += 50;
        } else if (claimedIncome > actualIncome * 1.5) {
            risk += 30;
        } else if (claimedIncome > actualIncome) {
            risk += 15;
        }

        // Aadhaar verification
        if (aadhaar.equalsIgnoreCase("No")) {
            risk += 25;
        }

        // PAN verification
        if (pan.equalsIgnoreCase("No")) {
            risk += 25;
        }

        if (risk > 100) {
            risk = 100;
        }

        return risk;
    }

    public String getRiskLevel(int risk) {

        if (risk >= 70) {
            return "HIGH";
        }
        else if (risk >= 40) {
            return "MEDIUM";
        }
        else {
            return "LOW";
        }
    }

    public String getAdvice(int risk) {

        if (risk >= 70) {
            return "High fraud risk. Manual verification is recommended before approving any financial service.";
        }
        else if (risk >= 40) {
            return "Moderate risk. Verify documents carefully before processing.";
        }
        else {
            return "Low fraud risk. Information appears consistent.";
        }
    }
}