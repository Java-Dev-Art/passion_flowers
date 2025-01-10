package com.example.passion_flowers.service;

import com.example.passion_flowers.model.User;

public interface UserService {
    boolean authentication(String login, String password) throws ServiceException;
    User findUserByUsername(String username) throws ServiceException; // Поиск пользователя по имени
    boolean createUser(User user) throws ServiceException; // Создание нового пользователя
}
