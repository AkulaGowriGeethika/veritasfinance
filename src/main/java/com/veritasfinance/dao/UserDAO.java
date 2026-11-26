package com.veritasfinance.dao;

import com.veritasfinance.model.User;
import com.veritasfinance.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.mindrot.jbcrypt.BCrypt;

public class UserDAO {

    // Register User
    public boolean registerUser(User user) {

        boolean status = false;

        String sql = "INSERT INTO users(full_name,email,phone,password,language,voice_enabled) VALUES(?,?,?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail().trim().toLowerCase());
            ps.setString(3, user.getPhone());
            ps.setString(4, hashedPassword);
            ps.setString(5, user.getLanguage());
            ps.setBoolean(6, user.isVoiceEnabled());

            status = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }


    public boolean emailExists(String email) {
        String sql = "SELECT 1 FROM users WHERE email = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email.trim().toLowerCase());
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Login User
    public User loginUser(String email, String password) {

        User user = null;

        String sql = "SELECT * FROM users WHERE email=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email.trim().toLowerCase());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                if (password != null && rs.getString("password") != null && BCrypt.checkpw(password, rs.getString("password"))) {

                    user = new User();

                    user.setUserId(rs.getInt("user_id"));
                    user.setFullName(rs.getString("full_name"));
                    user.setEmail(rs.getString("email"));
                    user.setPhone(rs.getString("phone"));
                    user.setLanguage(rs.getString("language"));
                    user.setVoiceEnabled(rs.getBoolean("voice_enabled"));

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
}