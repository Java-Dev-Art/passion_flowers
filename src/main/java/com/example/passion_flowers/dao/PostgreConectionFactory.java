package com.example.passion_flowers.dao;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class PostgreConectionFactory {
    private static DataSource dataSource;
    static {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("src/main/webapp/WEB-INF/database.properties")){
            properties.load(fileInputStream);
            PGSimpleDataSource pgSimpleDataSource = new PGSimpleDataSource();
            pgSimpleDataSource.setUrl(properties.getProperty("db.url"));
            pgSimpleDataSource.setUser(properties.getProperty("db.username"));
            pgSimpleDataSource.setPassword(properties.getProperty("db.password"));
            dataSource = pgSimpleDataSource;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load database configuration", e);
        }
    }
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}