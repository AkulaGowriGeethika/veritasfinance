package com.veritasfinance.controller;

import com.veritasfinance.dao.AssetDAO;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/DeleteAssetServlet")
public class DeleteAssetServlet extends HttpServlet{

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException{

        int id=Integer.parseInt(request.getParameter("id"));

        AssetDAO dao=new AssetDAO();

        dao.deleteAsset(id);

        response.getWriter().print("Deleted");

    }

}