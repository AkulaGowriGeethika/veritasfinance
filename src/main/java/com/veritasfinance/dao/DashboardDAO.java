package com.veritasfinance.dao;

import com.veritasfinance.util.DBConnection;
import java.sql.*;

public class DashboardDAO {

    public int getTotalAssets(int userId) {

        int total = 0;

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT COUNT(*) FROM user_assets WHERE user_id=?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                total = rs.getInt(1);
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return total;
    }

    public double getTotalAssetValue(int userId) {

        double total = 0;

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT SUM(estimated_value) FROM user_assets WHERE user_id=?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                total = rs.getDouble(1);
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return total;
    }

    public int getTrustScore(int userId) {

        int score = 0;

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT total_score FROM trust_scores WHERE user_id=?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                score = rs.getInt("total_score");
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return score;
    }

}