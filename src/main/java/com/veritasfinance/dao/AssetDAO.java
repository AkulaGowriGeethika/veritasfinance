package com.veritasfinance.dao;

import com.veritasfinance.model.Asset;
import com.veritasfinance.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AssetDAO {

    // Add Asset
    public boolean addAsset(Asset asset) {

        String sql = "INSERT INTO user_assets (user_id, asset_name, asset_type, estimated_value, purchase_year, ownership_type, location, description, document_path) VALUES (?,?,?,?,?,?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, asset.getUserId());
            ps.setString(2, asset.getAssetName());
            ps.setString(3, asset.getAssetType());
            ps.setDouble(4, asset.getEstimatedValue());
            ps.setInt(5, asset.getPurchaseYear());
            ps.setString(6, asset.getOwnershipType());
            ps.setString(7, asset.getLocation());
            ps.setString(8, asset.getDescription());
            ps.setString(9, asset.getDocumentPath());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Get Assets By User
    public List<Asset> getAssetsByUser(int userId) {

        List<Asset> list = new ArrayList<>();

        String sql = "SELECT * FROM user_assets WHERE user_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Asset asset = new Asset();

                asset.setAssetId(rs.getInt("asset_id"));
                asset.setUserId(rs.getInt("user_id"));
                asset.setAssetName(rs.getString("asset_name"));
                asset.setAssetType(rs.getString("asset_type"));
                asset.setEstimatedValue(rs.getDouble("estimated_value"));
                asset.setPurchaseYear(rs.getInt("purchase_year"));
                asset.setOwnershipType(rs.getString("ownership_type"));
                asset.setLocation(rs.getString("location"));
                asset.setDescription(rs.getString("description"));
                asset.setDocumentPath(rs.getString("document_path"));

                list.add(asset);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Get All Assets
    public List<Asset> getAllAssets() {

        List<Asset> list = new ArrayList<>();

        String sql = "SELECT * FROM user_assets";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Asset asset = new Asset();

                asset.setAssetId(rs.getInt("asset_id"));
                asset.setUserId(rs.getInt("user_id"));
                asset.setAssetName(rs.getString("asset_name"));
                asset.setAssetType(rs.getString("asset_type"));
                asset.setEstimatedValue(rs.getDouble("estimated_value"));
                asset.setPurchaseYear(rs.getInt("purchase_year"));
                asset.setOwnershipType(rs.getString("ownership_type"));
                asset.setLocation(rs.getString("location"));
                asset.setDescription(rs.getString("description"));
                asset.setDocumentPath(rs.getString("document_path"));

                list.add(asset);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Delete Asset
    public boolean deleteAsset(int id) {

        String sql = "DELETE FROM user_assets WHERE asset_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Get Asset By ID
    public Asset getAssetById(int id) {

        System.out.println("========== getAssetById() ==========");
        System.out.println("Searching Asset ID = " + id);

        Asset asset = null;

        String sql = "SELECT * FROM user_assets WHERE asset_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            System.out.println("Database Connected Successfully");

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println("Asset Found!");

                asset = new Asset();

                asset.setAssetId(rs.getInt("asset_id"));
                asset.setUserId(rs.getInt("user_id"));
                asset.setAssetName(rs.getString("asset_name"));
                asset.setAssetType(rs.getString("asset_type"));
                asset.setEstimatedValue(rs.getDouble("estimated_value"));
                asset.setPurchaseYear(rs.getInt("purchase_year"));
                asset.setOwnershipType(rs.getString("ownership_type"));
                asset.setLocation(rs.getString("location"));
                asset.setDescription(rs.getString("description"));
                asset.setDocumentPath(rs.getString("document_path"));

                System.out.println("Asset ID      : " + asset.getAssetId());
                System.out.println("User ID       : " + asset.getUserId());
                System.out.println("Asset Name    : " + asset.getAssetName());

            } else {

                System.out.println("No Asset Found With ID = " + id);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return asset;
    }

    // Update Asset
    public boolean updateAsset(Asset asset) {

        String sql = "UPDATE user_assets SET asset_name=?, asset_type=?, estimated_value=?, purchase_year=?, ownership_type=?, location=?, description=?, document_path=? WHERE asset_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, asset.getAssetName());
            ps.setString(2, asset.getAssetType());
            ps.setDouble(3, asset.getEstimatedValue());
            ps.setInt(4, asset.getPurchaseYear());
            ps.setString(5, asset.getOwnershipType());
            ps.setString(6, asset.getLocation());
            ps.setString(7, asset.getDescription());
            ps.setString(8, asset.getDocumentPath());
            ps.setInt(9, asset.getAssetId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}