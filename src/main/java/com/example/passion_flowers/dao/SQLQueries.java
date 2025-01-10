package com.example.passion_flowers.dao;

public class SQLQueries {
    // Выбор всех пользователей
    public static final String SQL_SELECT_ALL_USERS =
            "SELECT user_id, username, email, phone, telegram_handle, instagram_handle FROM users.users_shop";

    // Выбор пользователя по имени
    public static final String  SQL_FIND_BY_USERNAME =
            "SELECT user_id, username, password_hash FROM users WHERE username = ?";

    // Выбор пользователя по ID
    public static final String SQL_SELECT_USERS_BY_USER_ID =
            "SELECT user_id, username, email, phone, address, telegram_handle, instagram_handle FROM users.users_shop WHERE user_id = ?";

    // Удаление пользователя по ID
    public static final String SQL_DELETE_BY_USER_ID =
            "DELETE FROM users.users_shop WHERE user_id = ?";

    // Удаление пользователя с дополнительными критериями
    public static final String SQL_DELETE_BY_USER =
            "DELETE FROM users.users_shop WHERE user_id = ? AND username = ? AND password_hash = ?";

    // Добавление нового пользователя
    public static final String SQL_CREATE_NEW_OBJECT =
            "INSERT INTO users.users_shop (username, email, password_hash, phone, address, telegram_handle, instagram_handle, role) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    // Обновление данных пользователя
    public static final String SQL_UPDATE_USER =
            "UPDATE users.users_shop SET username = ?, phone = ?, address = ?, telegram_handle = ?, instagram_handle = ? WHERE user_id = ?";

}
