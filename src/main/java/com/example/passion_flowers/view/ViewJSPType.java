package com.example.passion_flowers.view;

public enum ViewJSPType {
    ERROR("jsp/error.jsp"),
    MAIN("jsp/main.jsp"),
    LOGIN("jsp/login.jsp");
    private final String page;

    ViewJSPType(String page) {
        this.page = page;
    }

    public String getPage() {
        return page;
    }
}
