package com.example.passion_flowers.command;

import com.example.passion_flowers.service.ServiceException;
import com.example.passion_flowers.service.UserService;
import com.example.passion_flowers.service.impl.UserServiceImpl;
import com.example.passion_flowers.view.ViewJSPType;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;



public class LoginCommand implements Command{
//    final static Logger logger = LogManager.getLogger(LoginCommand.class);
//    @Override
//    public String execute(HttpServletRequest request) throws ServiceException {
//        String login = request.getParameter("login");
//        String password = request.getParameter("password");
//        UserService userService = new UserServiceImpl();
//        String page;
//        List<String> mas = new ArrayList<>();
//        mas.add("A");
//        mas.add("B");
//
//        if (userService.authentication(login,password)){
//            logger.info("login saccesful");
//            request.setAttribute("mas",mas);
//            request.setAttribute("user", login);
//            page = ViewJSPType.MAIN;
//        }else {
//            request.setAttribute("errorLoginPassMessage", "Incorrect Login or Password");
//            page = ViewJSPType.LOGIN;
//        }
//        return page;
//    }
    private static final Logger logger = LogManager.getLogger(LoginCommand.class);
    private final UserService userService = new UserServiceImpl(); // Используем UserService для работы с пользователем

    @Override
    public String execute(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            // Проверяем аутентификацию
            if (userService.authentication(username, password)) {
                logger.info("User {} logged in successfully.", username);

                // Устанавливаем данные пользователя в атрибуты запроса
                request.setAttribute("user", username);
                return ViewJSPType.MAIN; // Перенаправляем на главную страницу
            } else {
                logger.warn("Login failed for user: {}", username);

                // Сообщаем об ошибке
                request.setAttribute("errorLoginMessage", "Incorrect username or password.");
                return ViewJSPType.LOGIN; // Возвращаем на страницу логина
            }
        } catch (ServiceException e) {
            logger.error("Error during login process for user: {}", username, e);

            // Сообщаем об ошибке на сервере
            request.setAttribute("errorMessage", "Internal server error. Please try again later.");
            return ViewJSPType.ERROR; // Страница ошибки
        }
    }
}