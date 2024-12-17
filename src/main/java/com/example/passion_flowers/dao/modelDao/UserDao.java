package com.example.passion_flowers.dao.modelDao;

import com.example.passion_flowers.dao.BaseDao;
import com.example.passion_flowers.dao.DaoException;
import com.example.passion_flowers.model.User;

import java.util.List;

public interface UserDao extends BaseDao<Integer,User> {
    List<User> findStudentByLastname(String patternName) throws DaoException;
}
