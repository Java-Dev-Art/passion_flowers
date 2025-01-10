package com.example.passion_flowers.controller.telegrambot;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
@WebServlet(name = "telegabot", value = "/telegabot")
public class TelegramBotServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(TelegramBotServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            TelegramBotLauncher.startBot();
            logger.info("Telegram bot started successfully.");
            req.getRequestDispatcher("/jsp/telegastart.jsp").forward(req, resp);
        } catch (RuntimeException e) {
            logger.warn("Failed to start Telegram bot: " + e.getMessage());
        }
    }
}
