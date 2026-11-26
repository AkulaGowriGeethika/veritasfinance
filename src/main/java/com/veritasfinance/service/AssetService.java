package com.veritasfinance.service;

import com.veritasfinance.dao.AssetDAO;
import com.veritasfinance.model.Asset;

import java.util.List;

public class AssetService {

    private AssetDAO assetDAO = new AssetDAO();

    public List<Asset> getAllAssets() {
        return assetDAO.getAllAssets();
    }

    public List<Asset> getAssetsByUser(int userId) {
        return assetDAO.getAssetsByUser(userId);
    }

    public boolean addAsset(Asset asset) {
        return assetDAO.addAsset(asset);
    }
}