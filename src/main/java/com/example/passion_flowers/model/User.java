package com.example.passion_flowers.model;

import com.example.passion_flowers.entity.Entity;

import java.io.IOException;
import java.util.Objects;

public class User extends Entity {
    private long id;
    private String userName;
    private int userPass;

    public User(long id, String userName, int userPass) {
        this.id = id;
        this.userName = userName;
        this.userPass = userPass;
    }

    public int getUserPass() {
        return userPass;
    }

    public void setUserPass(int userPass) {
        this.userPass = userPass;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return id == user.id && userPass == user.userPass && Objects.equals(userName, user.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, userPass);
    }

    @Override
    public void close() throws IOException {

    }
}
