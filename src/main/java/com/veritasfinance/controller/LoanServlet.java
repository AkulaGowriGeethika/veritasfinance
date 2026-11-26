package com.veritasfinance.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.veritasfinance.service.LoanService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/loan")
public class LoanServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        double income = Double.parseDouble(request.getParameter("income"));
        int trustScore = Integer.parseInt(request.getParameter("trustScore"));

        LoanService service = new LoanService();

        boolean eligible = service.isEligible(trustScore);
        double maxLoan = service.getMaximumLoan(income, trustScore);
        double interest = service.getInterestRate(trustScore);
        String risk = service.getRiskLevel(trustScore);

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        out.println("""
<!DOCTYPE html>
<html>
<head>
<title>Loan Eligibility Result</title>

<style>

body{
font-family:Arial;
background:#eef3fb;
padding:40px;
}

.container{
width:700px;
margin:auto;
background:white;
padding:30px;
border-radius:12px;
box-shadow:0 5px 15px rgba(0,0,0,.2);
}

h1{
text-align:center;
color:#1d5fbf;
}

.cards{
display:flex;
flex-wrap:wrap;
justify-content:center;
gap:20px;
margin-top:30px;
}

.card{
width:220px;
background:#f7f9fc;
padding:20px;
border-radius:10px;
text-align:center;
box-shadow:0 3px 10px rgba(0,0,0,.1);
}

.card h2{
color:#1d5fbf;
font-size:18px;
}

.card p{
font-size:22px;
font-weight:bold;
}

.btn{
display:inline-block;
margin-top:30px;
padding:12px 25px;
background:#1d5fbf;
color:white;
text-decoration:none;
border-radius:8px;
}

</style>

</head>

<body>

<div class='container'>

<h1>AI Loan Eligibility Report</h1>

<div class='cards'>
""");

        out.println("<div class='card'><h2>Eligibility</h2><p>"
                + (eligible ? "Eligible ✅" : "Not Eligible ❌")
                + "</p></div>");

        out.println("<div class='card'><h2>Trust Score</h2><p>"
                + trustScore
                + "/100</p></div>");

        out.println("<div class='card'><h2>Maximum Loan</h2><p>₹ "
                + String.format("%,.0f", maxLoan)
                + "</p></div>");

        out.println("<div class='card'><h2>Interest Rate</h2><p>"
                + interest
                + "%</p></div>");

        out.println("<div class='card'><h2>Risk Level</h2><p>"
                + risk
                + "</p></div>");

        out.println("""
</div>

<div style="text-align:center;">
<a class="btn" href="loan.html">Check Again</a>
</div>

</div>

</body>
</html>
""");
    }
}