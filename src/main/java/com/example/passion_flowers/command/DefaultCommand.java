package com.example.passion_flowers.command;

import jakarta.servlet.http.HttpServletRequest;

public class DefaultCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        return "";
    }
}