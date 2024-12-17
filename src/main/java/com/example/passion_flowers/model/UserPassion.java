package com.example.passion_flowers.model;

import java.security.SecureRandom;
import java.util.Objects;

public class UserPassion {
    private long id;
    private String userName;
    private String userSurname;
    private String telegramNick;
    private String instagramNick;
    private String mail;
    private int phoneNumber;
    private long numberOfOrder;

    public UserPassion(long id,
                       String userName,
                       String userSurname,
                       String telegramNick,
                       String instagramNick,
                       String mail,
                       int phoneNumber,
                       long numberOfOrder) {
        this.id = id;
        this.userName = userName;
        this.userSurname = userSurname;
        this.telegramNick = telegramNick;
        this.instagramNick = instagramNick;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
        this.numberOfOrder = numberOfOrder;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getTelegramNick() {
        return telegramNick;
    }

    public void setTelegramNick(String telegramNick) {
        this.telegramNick = telegramNick;
    }

    public String getInstagramNick() {
        return instagramNick;
    }

    public void setInstagramNick(String instagramNick) {
        this.instagramNick = instagramNick;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public long getNumberOfOrder() {
        return numberOfOrder;
    }

    public void setNumberOfOrder(long numberOfOrder) {
        this.numberOfOrder = numberOfOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserPassion that)) return false;
        return id == that.id && phoneNumber == that.phoneNumber && numberOfOrder == that.numberOfOrder && Objects.equals(userName, that.userName) && Objects.equals(userSurname, that.userSurname) && Objects.equals(telegramNick, that.telegramNick) && Objects.equals(instagramNick, that.instagramNick) && Objects.equals(mail, that.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, userSurname, telegramNick, instagramNick, mail, phoneNumber, numberOfOrder);
    }
}
