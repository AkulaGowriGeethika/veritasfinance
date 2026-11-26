package com.veritasfinance.controller;

import com.veritasfinance.dao.AssetDAO;
import com.veritasfinance.model.Asset;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/updateAsset")
public class UpdateAssetServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    AssetDAO dao = new AssetDAO();

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
System.out.println("assetId = " + request.getParameter("assetId"));
System.out.println("assetName = " + request.getParameter("assetName"));
System.out.println("assetType = " + request.getParameter("assetType"));
System.out.println("estimatedValue = " + request.getParameter("estimatedValue"));
System.out.println("purchaseYear = " + request.getParameter("purchaseYear"));
System.out.println("ownershipType = " + request.getParameter("ownershipType"));
System.out.println("location = " + request.getParameter("location"));
System.out.println("description = " + request.getParameter("description"));
System.out.println("documentPath = " + request.getParameter("documentPath"));
        response.setContentType("text/plain");

        try {

            Asset asset = new Asset();

            asset.setAssetId(Integer.parseInt(request.getParameter("assetId")));
            asset.setAssetName(request.getParameter("assetName"));
            asset.setAssetType(request.getParameter("assetType"));
            asset.setEstimatedValue(Double.parseDouble(request.getParameter("estimatedValue")));
            asset.setPurchaseYear(Integer.parseInt(request.getParameter("purchaseYear")));
            asset.setOwnershipType(request.getParameter("ownershipType"));
            asset.setLocation(request.getParameter("location"));
            asset.setDescription(request.getParameter("description"));
            asset.setDocumentPath(request.getParameter("documentPath"));

            boolean status = dao.updateAsset(asset);

            if (status) {
                response.getWriter().print("success");
            } else {
                response.getWriter().print("failed");
            }

        } catch (Exception e) {

            e.printStackTrace();     // Prints the real error in Tomcat console
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().print("Error : " + e.getMessage());

        }
    }
}