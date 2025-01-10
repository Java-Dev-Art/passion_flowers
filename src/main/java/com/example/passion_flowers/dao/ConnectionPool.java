package com.example.passion_flowers.dao;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);

    private static ConnectionPool instance; // Singleton instance
    private static BasicDataSource dataSource;

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/study_db"; // URL базы данных
    private static final String DB_USERNAME = "root"; // Логин
    private static final String DB_PASSWORD = "root_123"; // Пароль
    private static final int MAX_TOTAL_CONNECTIONS = 50; // Максимальное количество соединений
    private static final int MAX_IDLE_CONNECTIONS = 20; // Максимальное количество простаивающих соединений
    private static final int MAX_WAIT_MILLIS = 10000; // Максимальное время ожидания соединения

    /**
     * Приватный конструктор для инициализации пула соединений.
     */
    private ConnectionPool() {
        dataSource = new BasicDataSource();
        dataSource.setUrl(DB_URL);
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);
        dataSource.setDriverClassName("org.postgresql.Driver");

        // Настройки пула соединений
        dataSource.setMaxTotal(MAX_TOTAL_CONNECTIONS);
        dataSource.setMaxIdle(MAX_IDLE_CONNECTIONS);
        dataSource.setMaxWaitMillis(MAX_WAIT_MILLIS);

        logger.info("ConnectionPool initialized successfully.");
    }

    /**
     * Метод для получения единственного экземпляра `ConnectionPool`.
     *
     * @return экземпляр `ConnectionPool`.
     */
    public static ConnectionPool getInstance() {
        if (instance == null) {
            synchronized (ConnectionPool.class) { // Потокобезопасная инициализация
                if (instance == null) {
                    instance = new ConnectionPool();
                }
            }
        }
        return instance;
    }

    /**
     * Получение соединения из пула.
     *
     * @return объект Connection.
     * @throws SQLException если соединение не удалось получить.
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * Закрытие пула соединений.
     */
    public void shutdown() {
        try {
            dataSource.close();
            logger.info("ConnectionPool shut down successfully.");
        } catch (SQLException e) {
            logger.error("Error shutting down ConnectionPool", e);
        }
    }
}