package com.example.passion_flowers.controller.telegrambot;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Telega extends TelegramLongPollingBot {
    private static Logger logger = LogManager.getLogger(Telega.class);
    @Override
    public String getBotUsername() {
        return "kspassion_bot";
    }
    @Override
    public String getBotToken() {
        return "7550558616:AAHJ57jJojxgZRvf9dF_NRxKrPp44oAXowo";
    }
    @Override
    public void onUpdateReceived(Update update) {
        logger.info("Update received: " + update);

        if (update.hasMessage() && update.getMessage().hasText()) {
            handleMessage(update);
        } else if (update.hasCallbackQuery()) {
            handleCallback(update);
        }
    }

    private void handleMessage(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        String receivedText = update.getMessage().getText();
        User user = update.getMessage().getFrom();

        // Логируем информацию о пользователе
        logger.info("User info: ID = {}, Name = {}, Username = {}",
                user.getId(), user.getFirstName(), user.getUserName());

        SendMessage message = new SendMessage();
        message.setChatId(chatId);

        switch (receivedText.toLowerCase()) {
            case "/start":
                message.setText("Привет, " + user.getFirstName() + "! Чем могу помочь?");
                message.setReplyMarkup(getMainMenuKeyboard());
                break;

            case "/help":
                message.setText("Вот список команд:\n/start - Начало работы\n/help - Помощь");
                break;

            default:
                message.setText("Извините, я не понял вашу команду.");
                break;
        }

        sendTelegramMessage(message);
    }

    private void handleCallback(Update update) {
        String chatId = update.getCallbackQuery().getMessage().getChatId().toString();
        String callbackData = update.getCallbackQuery().getData();

        SendMessage message = new SendMessage();
        message.setChatId(chatId);

        switch (callbackData) {
            case "start":
                message.setText("Вы выбрали начать! Чем я могу помочь?");
                break;

            case "help":
                message.setText("Вот что я могу для вас сделать: \n1. Начать\n2. Помощь");
                break;

            default:
                message.setText("Я не знаю, что вы хотите.");
                break;
        }

        sendTelegramMessage(message);
    }

    private InlineKeyboardMarkup getMainMenuKeyboard() {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();

        InlineKeyboardButton buttonStart = new InlineKeyboardButton();
        buttonStart.setText("Начать");
        buttonStart.setCallbackData("start");

        InlineKeyboardButton buttonHelp = new InlineKeyboardButton();
        buttonHelp.setText("Помощь");
        buttonHelp.setCallbackData("help");

        row.add(buttonStart);
        row.add(buttonHelp);
        rows.add(row);

        keyboardMarkup.setKeyboard(rows);
        return keyboardMarkup;
    }

    private void sendTelegramMessage(SendMessage message) {
        try {
            execute(message);
            logger.info("Message sent: " + message.getText());
        } catch (TelegramApiException e) {
            logger.error("Failed to send message", e);
        }
    }
}
