package com.veritasfinance.controller;

import com.google.gson.JsonObject;
import com.veritasfinance.dao.DashboardDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    DashboardDAO dao = new DashboardDAO();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        int userId = 1;

        int totalAssets = dao.getTotalAssets(userId);
        double totalValue = dao.getTotalAssetValue(userId);
        int trustScore = dao.getTrustScore(userId);

        JsonObject json = new JsonObject();

        json.addProperty("totalAssets", totalAssets);
        json.addProperty("totalAssetValue", totalValue);
        json.addProperty("trustScore", trustScore);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().print(json.toString());

    }
}