package com.example.passion_flowers.command;

import jakarta.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();
        System.out.println("index");
        return "index.jsp";
    }
}
