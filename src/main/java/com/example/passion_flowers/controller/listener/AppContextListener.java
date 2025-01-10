package com.example.passion_flowers.controller.listener;

import com.example.passion_flowers.dao.ConnectionPool;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebListener
public class AppContextListener  implements ServletContextListener {
    private static final Logger logger = LogManager.getLogger(AppContextListener.class);
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            // Инициализация пула соединений
            ConnectionPool.getInstance();
            logger.info("Connection pool initialized successfully.");
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize connection pool", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            // Закрытие пула соединений
            ConnectionPool.getInstance().shutdown();
            logger.info("Connection pool shut down successfully.");
        } catch (Exception e) {
            System.err.println("Error shutting down connection pool: " + e.getMessage());
        }
    }
}
