package com.musketeers.jewelverse.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Simple database connection test that runs on application startup
 * Remove this class after verifying the database connection works
 */
@Component
public class DatabaseConnectionTest implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;

    @Override
    public void run(String... args) throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            System.out.println("✅ Database connection successful!");
            System.out.println("Database URL: " + connection.getMetaData().getURL());
            System.out.println("Database Name: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("Database Version: " + connection.getMetaData().getDatabaseProductVersion());
        } catch (Exception e) {
            System.err.println("❌ Database connection failed: " + e.getMessage());
            throw e;
        }
    }
}
