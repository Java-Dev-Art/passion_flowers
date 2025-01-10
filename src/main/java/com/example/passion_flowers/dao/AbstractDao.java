package com.example.passion_flowers.dao;

import com.example.passion_flowers.entity.Entity;
import com.example.passion_flowers.model.User;
import com.example.passion_flowers.service.ServiceException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class AbstractDao <T extends Entity> {
    protected Connection connection;
    public abstract List<T> findAll() throws DaoException;
    public abstract T findByEntityById(long id) throws DaoException;
    public abstract boolean delete(T t) throws DaoException;
    public abstract boolean delete(long id) throws DaoException;
    public abstract boolean create(T t) throws DaoException;
    public abstract boolean update(T t) throws DaoException;
    public abstract User findUserByUsername(String username) throws DaoException;
    public abstract boolean createUser(User user) throws ServiceException;
    public abstract boolean authentication(String login, String password) throws ServiceException;
    public void close(Statement statement) throws DaoException {
        try {
            if (statement != null){
                statement.close();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void setConnection(Connection connection){
        this.connection = connection;
    }
}
