package com.veritasfinance;

import java.sql.Connection;
import com.veritasfinance.util.DBConnection;

public class TestConnection {

    public static void main(String[] args) {

        try {

            Connection con = DBConnection.getConnection();

            if (con != null) {
                System.out.println("==================================");
                System.out.println(" DATABASE CONNECTED SUCCESSFULLY ");
                System.out.println("==================================");
                con.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}