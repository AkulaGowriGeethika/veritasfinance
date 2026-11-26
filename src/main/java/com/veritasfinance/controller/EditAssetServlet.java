package com.veritasfinance.controller;

import com.google.gson.Gson;
import com.veritasfinance.dao.AssetDAO;
import com.veritasfinance.model.Asset;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/editAsset")
public class EditAssetServlet extends HttpServlet {

    private AssetDAO dao = new AssetDAO();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        Asset asset = dao.getAssetById(id);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().print(new Gson().toJson(asset));
    }
}