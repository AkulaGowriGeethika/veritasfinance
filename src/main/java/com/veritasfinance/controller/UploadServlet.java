package com.veritasfinance.controller;

import java.io.IOException;

import com.veritasfinance.dao.DocumentDAO;
import com.veritasfinance.model.Document;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/upload")
public class UploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        try {

            // Temporary demo user ID
            // Later this can be replaced with the logged-in user's ID
            int userId = 1;

            // Get financial details from the form
            String incomeText = request.getParameter("income");
            String savingsText = request.getParameter("savings");

            String bill = request.getParameter("bill");
            String loan = request.getParameter("loan");

            // Validate income
            if (incomeText == null || incomeText.trim().isEmpty()) {
                response.getWriter().println("Monthly income is required.");
                return;
            }

            // Validate savings
            if (savingsText == null || savingsText.trim().isEmpty()) {
                response.getWriter().println("Monthly savings is required.");
                return;
            }

            double income = Double.parseDouble(incomeText);
            double savings = Double.parseDouble(savingsText);

            // Basic validation
            if (income < 0 || savings < 0) {
                response.getWriter().println("Income and savings cannot be negative.");
                return;
            }

            if (savings > income) {
                response.getWriter().println("Savings cannot be greater than income.");
                return;
            }

            // Create Document object
            Document document = new Document();

            document.setUserId(userId);

            // No files are being uploaded in the current form
            document.setBankStatement("");
            document.setSalarySlip("");
            document.setUtilityBill("");

            // Financial information
            document.setMonthlyIncome(income);
            document.setMonthlySavings(savings);

            document.setBillPaymentStatus(bill);
            document.setLoanStatus(loan);

            // Save to database
            DocumentDAO dao = new DocumentDAO();

            boolean saved = dao.saveDocument(document);

            if (saved) {

                response.sendRedirect("trustscore.html");

            } else {

                response.setContentType("text/html");

                response.getWriter().println("""
                    <!DOCTYPE html>
                    <html>
                    <head>
                        <title>Upload Failed</title>
                        <style>
                            body {
                                font-family: Arial, sans-serif;
                                background: #eef3fb;
                                text-align: center;
                                padding-top: 100px;
                            }

                            .box {
                                width: 500px;
                                margin: auto;
                                background: white;
                                padding: 40px;
                                border-radius: 15px;
                                box-shadow: 0 5px 20px rgba(0,0,0,0.15);
                            }

                            h1 {
                                color: #dc3545;
                            }

                            a {
                                display: inline-block;
                                margin-top: 20px;
                                padding: 12px 22px;
                                background: #1d5fbf;
                                color: white;
                                text-decoration: none;
                                border-radius: 8px;
                            }
                        </style>
                    </head>

                    <body>

                        <div class="box">

                            <h1>Unable to Save Details</h1>

                            <p>
                                Your financial information could not be saved.
                            </p>

                            <a href="upload.html">
                                Try Again
                            </a>

                        </div>

                    </body>
                    </html>
                    """);
            }

        } catch (NumberFormatException e) {

            response.setContentType("text/html");

            response.getWriter().println("""
                <!DOCTYPE html>
                <html>
                <head>
                    <title>Invalid Input</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            background: #eef3fb;
                            text-align: center;
                            padding-top: 100px;
                        }

                        .box {
                            width: 500px;
                            margin: auto;
                            background: white;
                            padding: 40px;
                            border-radius: 15px;
                            box-shadow: 0 5px 20px rgba(0,0,0,0.15);
                        }

                        h1 {
                            color: #dc3545;
                        }

                        a {
                            display: inline-block;
                            margin-top: 20px;
                            padding: 12px 22px;
                            background: #1d5fbf;
                            color: white;
                            text-decoration: none;
                            border-radius: 8px;
                        }
                    </style>
                </head>

                <body>

                    <div class="box">

                        <h1>Invalid Financial Details</h1>

                        <p>
                            Please enter valid numbers for income and savings.
                        </p>

                        <a href="upload.html">
                            Go Back
                        </a>

                    </div>

                </body>
                </html>
                """);

        } catch (Exception e) {

            e.printStackTrace();

            response.setContentType("text/html");

            response.getWriter().println("""
                <!DOCTYPE html>
                <html>
                <head>
                    <title>Server Error</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            background: #eef3fb;
                            text-align: center;
                            padding-top: 100px;
                        }

                        .box {
                            width: 500px;
                            margin: auto;
                            background: white;
                            padding: 40px;
                            border-radius: 15px;
                            box-shadow: 0 5px 20px rgba(0,0,0,0.15);
                        }

                        h1 {
                            color: #dc3545;
                        }

                        a {
                            display: inline-block;
                            margin-top: 20px;
                            padding: 12px 22px;
                            background: #1d5fbf;
                            color: white;
                            text-decoration: none;
                            border-radius: 8px;
                        }
                    </style>
                </head>

                <body>

                    <div class="box">

                        <h1>Something Went Wrong</h1>

                        <p>
                            We could not process your financial details.
                        </p>

                        <a href="upload.html">
                            Try Again
                        </a>

                    </div>

                </body>
                </html>
                """);
        }
    }
}