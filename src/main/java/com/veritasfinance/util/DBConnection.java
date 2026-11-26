
package com.veritasfinance.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class DBConnection {

    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;

    static {
        try {
            Properties properties = new Properties();

            try (InputStream input = DBConnection.class
                    .getClassLoader()
                    .getResourceAsStream("config.properties")) {

                if (input != null) {
                    properties.load(input);
                }
            }

            String configuredUrl = System.getenv("DB_URL");
            String configuredUser = System.getenv("DB_USERNAME");
            String configuredPassword = System.getenv("DB_PASSWORD");

            if (configuredUrl == null || configuredUrl.isBlank()) {
                configuredUrl = properties.getProperty("db.url", "");
            }

            if (configuredUser == null || configuredUser.isBlank()) {
                configuredUser = properties.getProperty("db.username", "");
            }

            if (configuredPassword == null || configuredPassword.isBlank()) {
                configuredPassword = properties.getProperty("db.password", "");
            }

            URL = configuredUrl.trim();
            USERNAME = configuredUser.trim();
            PASSWORD = configuredPassword;

            if (URL.isEmpty() || USERNAME.isEmpty() || PASSWORD.isEmpty()) {
                throw new IllegalStateException(
                    "Database configuration is missing."
                );
            }

            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private DBConnection() { }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
