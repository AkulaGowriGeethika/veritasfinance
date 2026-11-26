package com.veritasfinance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.veritasfinance.model.TrustScore;
import com.veritasfinance.util.DBConnection;

public class TrustScoreDAO {

    public boolean saveScore(TrustScore score) {

        boolean status = false;

        try {

            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO trust_scores(user_id,income_score,savings_score,payment_score,debt_score,total_score) VALUES(?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, score.getUserId());
            ps.setInt(2, score.getIncomeScore());
            ps.setInt(3, score.getSavingsScore());
            ps.setInt(4, score.getPaymentScore());
            ps.setInt(5, score.getDebtScore());
            ps.setInt(6, score.getTotalScore());

            status = ps.executeUpdate() > 0;

            con.close();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return status;
    }

}