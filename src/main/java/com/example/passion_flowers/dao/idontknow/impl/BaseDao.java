package com.example.passion_flowers.dao.idontknow.impl;
import com.example.passion_flowers.dao.DaoException;
import com.example.passion_flowers.entity.Entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface BaseDao <K,T extends Entity> {
    List<T> findAll() throws DaoException;
    T findByEntityById(K id) throws DaoException;
    boolean delete(T t) throws DaoException;
    boolean delete(K id) throws DaoException;
    boolean create(T t) throws DaoException;
    boolean update(T t) throws DaoException;
    default void close(Statement statement) throws DaoException {
        try {
            if (statement != null){
                statement.close();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
    default void close(Connection connection) throws DaoException {
        try {
            if (connection != null){
                //return connection to pool
                connection.close();
            }
        } catch (SQLException e) {
           throw  new DaoException(e);
        }
    }
}
