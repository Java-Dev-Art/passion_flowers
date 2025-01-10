package com.example.passion_flowers.service.impl;

import com.example.passion_flowers.dao.DaoException;
import com.example.passion_flowers.dao.EntityTransaction;
import com.example.passion_flowers.dao.PasswordUtils;
import com.example.passion_flowers.dao.UserDao;
import com.example.passion_flowers.model.User;
import com.example.passion_flowers.service.ServiceException;
import com.example.passion_flowers.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private final UserDao userDao = new UserDao();

    @Override
    public User findUserByUsername(String username) throws ServiceException {
        EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.begin(userDao);
            return userDao.findUserByUsername(username);
        } catch (DaoException e) {
            throw new ServiceException("Error finding user by username: " + username, e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public boolean createUser(User user) throws ServiceException {
        EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.begin(userDao);
            boolean isCreated = userDao.create(user);
            transaction.commit();
            return isCreated;
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException("Error creating user", e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public boolean authentication(String login, String password) throws ServiceException {
        try {
            // Находим пользователя по логину
            User user = findUserByUsername(login);
            logger.info("Login: " + login + " / Password: " + password);

            if (user != null) {
                logger.info("User found: " + user.getUserName() + " / Stored hash: " + user.getPasswordHash());

                // Сравниваем введённый пароль (хэшированный) с хэшем из базы
                boolean isPasswordValid = PasswordUtils.verifyPassword(password, user.getPasswordHash());

                if (isPasswordValid) {
                    logger.info("Authentication successful for user: " + login);
                    return true;
                } else {
                    logger.warn("Authentication failed: incorrect password for user: " + login);
                    return false;
                }
            }

            logger.warn("Authentication failed: user not found for login: " + login);
            return false;
        } catch (ServiceException e) {
            throw new ServiceException("Error during authentication for user: " + login, e);
        }
    }
}
