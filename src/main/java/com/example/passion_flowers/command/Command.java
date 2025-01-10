package com.example.passion_flowers.command;

import com.example.passion_flowers.service.ServiceException;
import jakarta.servlet.http.HttpServletRequest;

public interface Command {
    String execute(HttpServletRequest request) throws ServiceException;
}
