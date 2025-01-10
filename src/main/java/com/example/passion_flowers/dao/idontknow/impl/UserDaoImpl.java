package com.example.passion_flowers.dao.idontknow.impl;

import com.example.passion_flowers.dao.DaoException;
import com.example.passion_flowers.model.User;

import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public List<User> findUserByLastname(String patternName) throws DaoException {
        return List.of();
    }

    @Override
    public List<User> findAll() throws DaoException {
        return List.of();
    }

    @Override
    public User findByEntityById(Integer id) throws DaoException {
        return null;
    }

    @Override
    public boolean delete(User user) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        return false;
    }

    @Override
    public boolean create(User user) throws DaoException {
        return false;
    }

    @Override
    public boolean update(User user) throws DaoException {
        return false;
    }
}
