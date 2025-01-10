package com.example.passion_flowers.dao.idontknow.impl;

import com.example.passion_flowers.dao.DaoException;
import com.example.passion_flowers.model.User;

import java.util.List;

public interface UserDao extends BaseDao<Integer,User> {
    List<User> findUserByLastname(String patternName) throws DaoException;

}
