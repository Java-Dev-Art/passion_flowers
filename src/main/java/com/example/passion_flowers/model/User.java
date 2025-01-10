package com.example.passion_flowers.model;

import com.example.passion_flowers.entity.Entity;

import java.io.IOException;
import java.util.Objects;

public class User extends Entity {
    private long id; // Уникальный идентификатор
    private String userName; // Имя пользователя
    private String email; // Email
    private String passwordHash; // Хэш пароля
    private String phone; // Номер телефона
    private String address; // Адрес доставки
    private String telegramHandle; // Ник в Telegram
    private String instagramHandle; // Ник в Instagram
    private String role; // Роль (admin/customer)
    private String accountStatus; // Статус аккаунта (active/blocked)

    // Конструктор
    public User(long id, String userName, String email, String passwordHash, String phone, String address,
                String telegramHandle, String instagramHandle, String role, String accountStatus) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.phone = phone;
        this.address = address;
        this.telegramHandle = telegramHandle;
        this.instagramHandle = instagramHandle;
        this.role = role;
        this.accountStatus = accountStatus;
    }

    // Геттеры и сеттеры
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelegramHandle() {
        return telegramHandle;
    }

    public void setTelegramHandle(String telegramHandle) {
        this.telegramHandle = telegramHandle;
    }

    public String getInstagramHandle() {
        return instagramHandle;
    }

    public void setInstagramHandle(String instagramHandle) {
        this.instagramHandle = instagramHandle;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    // Переопределение equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return id == user.id &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(passwordHash, user.passwordHash) &&
                Objects.equals(phone, user.phone) &&
                Objects.equals(address, user.address) &&
                Objects.equals(telegramHandle, user.telegramHandle) &&
                Objects.equals(instagramHandle, user.instagramHandle) &&
                Objects.equals(role, user.role) &&
                Objects.equals(accountStatus, user.accountStatus);
    }

    // Переопределение hashCode
    @Override
    public int hashCode() {
        return Objects.hash(id, userName, email, passwordHash, phone, address, telegramHandle, instagramHandle, role, accountStatus);
    }

    // Добавлен пустой close() метод, если он нужен
    @Override
    public void close() {
        // Закрытие ресурсов, если необходимо
    }

    // Переопределение toString() для удобства вывода информации
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", telegramHandle='" + telegramHandle + '\'' +
                ", instagramHandle='" + instagramHandle + '\'' +
                ", role='" + role + '\'' +
                ", accountStatus='" + accountStatus + '\'' +
                '}';
    }
}
