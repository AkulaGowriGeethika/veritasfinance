package com.veritasfinance.model;

public class TrustScore {

    private int scoreId;
    private int userId;
    private int incomeScore;
    private int savingsScore;
    private int paymentScore;
    private int debtScore;
    private int totalScore;
    private String generatedAt;

    public TrustScore() {
    }

    public int getScoreId() {
        return scoreId;
    }

    public void setScoreId(int scoreId) {
        this.scoreId = scoreId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getIncomeScore() {
        return incomeScore;
    }

    public void setIncomeScore(int incomeScore) {
        this.incomeScore = incomeScore;
    }

    public int getSavingsScore() {
        return savingsScore;
    }

    public void setSavingsScore(int savingsScore) {
        this.savingsScore = savingsScore;
    }

    public int getPaymentScore() {
        return paymentScore;
    }

    public void setPaymentScore(int paymentScore) {
        this.paymentScore = paymentScore;
    }

    public int getDebtScore() {
        return debtScore;
    }

    public void setDebtScore(int debtScore) {
        this.debtScore = debtScore;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public String getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(String generatedAt) {
        this.generatedAt = generatedAt;
    }
}