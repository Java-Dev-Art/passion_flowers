package com.example.passion_flowers.controller.mailservice;

import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;

import java.util.Properties;

public class SessionCreator {
    private String smtpHost;
    private String smtpPort;
    private String userName;
    private String userPassword;
    private Properties sessionProperties;

    public SessionCreator(Properties configProperties) {
        smtpHost = configProperties.getProperty("mail.smtp.host");
        smtpPort = configProperties.getProperty("mail.smtp.port");
        userName = configProperties.getProperty("mail.user.name");
        userPassword = configProperties.getProperty("mail.user.pass");

        sessionProperties = new Properties();
        sessionProperties.setProperty("mail.transport.protocol","smtp");
        sessionProperties.setProperty("mail.host", smtpHost);
        sessionProperties.put("mail.smtp.auth", "true");
        sessionProperties.put("mail.smtp.port",smtpPort);
        sessionProperties.put("mail.smtp.socketFactory.port",smtpPort);
        sessionProperties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        sessionProperties.put("mail.smtp.socketFactory.fallback","false");
        sessionProperties.put("mail.smtp.ssl.enable", "true"); // Для SSL
        sessionProperties.put("mail.smtp.starttls.enable", "true");
        sessionProperties.setProperty("mail.smtp.quitwait","false");
    }
    public Session createSession(){
        return Session.getDefaultInstance(sessionProperties, new jakarta.mail.Authenticator(){
                                    protected PasswordAuthentication getPasswordAuthentication(){
                                        System.out.println(userName + " " + userPassword);
                                        return new PasswordAuthentication(userName, userPassword);
                                    }
        });
    }
}
