package com.example.passion_flowers.command;

public enum CommandType {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    DEFAULT(new DefaultCommand());
    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
    public static Command findCommand(String commandStr){

        return CommandType.valueOf(commandStr.toUpperCase()).getCommand();// td
    }
}
