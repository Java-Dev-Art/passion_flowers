package com.example.passion_flowers.service.impl;

import com.example.passion_flowers.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public boolean authentication(String login, String password) {
        return login.equalsIgnoreCase(password);// todo
    }
}
