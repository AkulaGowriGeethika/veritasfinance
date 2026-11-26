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
        try (InputStream input = DBConnection.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new IllegalStateException(
                    "config.properties is missing. Copy config.properties.example and set your MySQL details.");
            }
            Properties properties = new Properties();
            properties.load(input);
            String configuredUrl = properties.getProperty("db.url", "").trim();
            USERNAME = properties.getProperty("db.username", "").trim();
            PASSWORD = properties.getProperty("db.password", "");
            if (configuredUrl.isEmpty() || USERNAME.isEmpty()) {
                throw new IllegalStateException("Database URL or username is missing in config.properties.");
            }
            URL = addProperty(configuredUrl, "allowPublicKeyRetrieval", "true");
            Class.forName(properties.getProperty("db.driver", "com.mysql.cj.jdbc.Driver").trim());
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private DBConnection() { }

    private static String addProperty(String url, String key, String value) {
        if (url.toLowerCase().contains(key.toLowerCase() + "=")) return url;
        return url + (url.contains("?") ? "&" : "?") + key + "=" + value;
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
