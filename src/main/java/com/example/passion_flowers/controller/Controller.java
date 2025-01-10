package com.example.passion_flowers.controller;

import com.example.passion_flowers.command.Command;
import com.example.passion_flowers.command.CommandType;
import com.example.passion_flowers.service.ServiceException;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "controller", value = "/controller")
public class Controller extends HttpServlet {
    public Controller() {

    }

    @Override
    public void init(ServletConfig config) throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            processRequest(req, resp);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServiceException {
        resp.setContentType("text/html");
        System.out.println("processRequest");
        String commandStr = req.getParameter("command");

        Command command = CommandType.findCommand(commandStr);
        String page = command.execute(req);
        req.getRequestDispatcher(page).forward(req,resp);
    }

    @Override
    public void destroy() {

    }
}
