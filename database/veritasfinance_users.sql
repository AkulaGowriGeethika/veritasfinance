CREATE DATABASE IF NOT EXISTS veritasfinance
  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE veritasfinance;

CREATE TABLE IF NOT EXISTS users (
    user_id INT NOT NULL AUTO_INCREMENT,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL,
    phone VARCHAR(10) NOT NULL,
    password VARCHAR(100) NOT NULL,
    language VARCHAR(30) NOT NULL,
    voice_enabled BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id),
    UNIQUE KEY uk_users_email (email)
);
