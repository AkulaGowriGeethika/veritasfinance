package com.veritasfinance.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.veritasfinance.service.AdminService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        AdminService service = new AdminService();

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        out.println("""
<!DOCTYPE html>
<html>
<head>

<title>Admin Dashboard</title>

<style>

body{
font-family:Arial;
background:#eef3fb;
padding:40px;
margin:0;
}

h1{
text-align:center;
color:#1d5fbf;
margin-bottom:40px;
}

.cards{
display:flex;
flex-wrap:wrap;
justify-content:center;
gap:25px;
}

.card{
width:240px;
background:white;
padding:25px;
border-radius:12px;
box-shadow:0 5px 15px rgba(0,0,0,.15);
text-align:center;
}

.card h2{
color:#1d5fbf;
font-size:20px;
}

.card p{
font-size:28px;
font-weight:bold;
color:#333;
}

.btn{
display:block;
width:220px;
margin:40px auto;
padding:15px;
background:#1d5fbf;
color:white;
text-align:center;
text-decoration:none;
border-radius:8px;
font-size:18px;
}

</style>

</head>

<body>

<h1>Admin Dashboard</h1>

<div class="cards">
""");

        out.println("<div class='card'><h2>Total Users</h2><p>"
                + service.getTotalUsers()
                + "</p></div>");

        out.println("<div class='card'><h2>Total Documents</h2><p>"
                + service.getTotalDocuments()
                + "</p></div>");

        out.println("<div class='card'><h2>Total Assets</h2><p>"
                + service.getTotalAssets()
                + "</p></div>");

        out.println("<div class='card'><h2>Total Asset Value</h2><p>₹ "
                + String.format("%,.0f", service.getTotalAssetValue())
                + "</p></div>");

        out.println("<div class='card'><h2>Average Trust Score</h2><p>"
                + service.getAverageTrustScore()
                + "</p></div>");

        out.println("<div class='card'><h2>Eligible Loan Users</h2><p>"
                + service.getEligibleLoanUsers()
                + "</p></div>");

        out.println("""
</div>

<a class="btn" href="dashboard.html">
Back to Dashboard
</a>

</body>
</html>
""");
    }
}