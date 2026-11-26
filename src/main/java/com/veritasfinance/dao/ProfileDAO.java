package com.veritasfinance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.veritasfinance.model.User;
import com.veritasfinance.util.DBConnection;

public class ProfileDAO {

    public User getProfile(int userId) {

        User user = null;

        String sql = "SELECT * FROM users WHERE user_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                user = new User();

                user.setUserId(rs.getInt("user_id"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setLanguage(rs.getString("language"));
                user.setVoiceEnabled(rs.getBoolean("voice_enabled"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
}