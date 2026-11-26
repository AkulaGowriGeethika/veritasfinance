package com.veritasfinance.service;

import com.veritasfinance.model.Document;
import com.veritasfinance.model.TrustScore;

public class TrustScoreService {

    public TrustScore calculate(Document document) {

        TrustScore score = new TrustScore();

        score.setUserId(document.getUserId());

        int income = 0;
        int savings = 0;
        int payment = 0;
        int debt = 0;

        if(document.getMonthlyIncome() >= 100000)
            income = 25;
        else if(document.getMonthlyIncome() >= 50000)
            income = 20;
        else
            income = 10;

        if(document.getMonthlySavings() >= 50000)
            savings = 25;
        else if(document.getMonthlySavings() >= 20000)
            savings = 20;
        else
            savings = 10;

        if(document.getBillPaymentStatus().equalsIgnoreCase("Paid"))
            payment = 25;
        else
            payment = 10;

        if(document.getLoanStatus().equalsIgnoreCase("No Loan"))
            debt = 25;
        else
            debt = 15;

        score.setIncomeScore(income);
        score.setSavingsScore(savings);
        score.setPaymentScore(payment);
        score.setDebtScore(debt);

        score.setTotalScore(income + savings + payment + debt);

        return score;

    }

}