package com.example.passion_flowers.command;

import com.example.passion_flowers.service.UserService;
import com.example.passion_flowers.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static com.example.passion_flowers.view.ViewJSPType.LOGIN;
import static com.example.passion_flowers.view.ViewJSPType.MAIN;

public class LoginCommand implements Command{
    final static Logger logger = LogManager.getLogger(LoginCommand.class);
    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        UserService userService = new UserServiceImpl();
        String page;
        List<String> mas = new ArrayList<>();
        mas.add("A");
        mas.add("B");

        if (userService.authentication(login,password)){
            logger.info("login saccesful");
            request.setAttribute("mas",mas);
            request.setAttribute("user", login);
            page = MAIN.getPage();
        }else {
            request.setAttribute("errorLoginPassMessage", "Incorrect Login or Password");
            page = LOGIN.getPage();
        }
        return page;
    }
}