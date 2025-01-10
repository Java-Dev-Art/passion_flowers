package com.example.passion_flowers.controller.telegrambot;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class TelegramBotLauncher {
    private static final Logger logger = LogManager.getLogger(TelegramBotLauncher.class);
    private static boolean isBotRunning = false; // Флаг, чтобы предотвратить повторный запуск

    public static void startBot() {
        if (isBotRunning) {
            System.out.println("Telegram bot is already running.");
            return;
        }

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new Telega()); // Здесь ваш Telegram-бот
            isBotRunning = true; // Устанавливаем флаг
            System.out.println("Telegram bot started successfully.");
        } catch (TelegramApiException e) {
            logger.warn(e);
            throw new RuntimeException("Failed to start Telegram bot.", e);
        }
    }
}
