package com.veritasfinance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.veritasfinance.model.Document;
import com.veritasfinance.util.DBConnection;

public class DocumentDAO {

    public boolean saveDocument(Document document) {

        boolean status = false;

        try {

            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO documents(user_id,bank_statement,salary_slip,utility_bill,monthly_income,monthly_savings,bill_payment_status,loan_status) VALUES(?,?,?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, document.getUserId());
            ps.setString(2, document.getBankStatement());
            ps.setString(3, document.getSalarySlip());
            ps.setString(4, document.getUtilityBill());
            ps.setDouble(5, document.getMonthlyIncome());
            ps.setDouble(6, document.getMonthlySavings());
            ps.setString(7, document.getBillPaymentStatus());
            ps.setString(8, document.getLoanStatus());

            status = ps.executeUpdate() > 0;

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }
}