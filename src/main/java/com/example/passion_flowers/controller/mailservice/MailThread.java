package com.example.passion_flowers.controller.mailservice;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class MailThread extends Thread{
    private MimeMessage message;
    private String sendToMail;
    private String mailSubject;
    private String mailText;
    private Properties properties;

    public MailThread(String sendToMail,
                      String mailSubject,
                      String mailText,
                      Properties properties) {
        this.sendToMail = sendToMail;
        this.mailSubject = mailSubject;
        this.mailText = mailText;
        this.properties = properties;
    }

    private void init(){
        Session mailSession = (new SessionCreator(properties)).createSession();
        mailSession.setDebug(true);
        message = new MimeMessage(mailSession);
        try {
            message.setSubject(mailSubject);
            message.setContent(mailText,"text/html");
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(sendToMail));

        } catch (MessagingException e) {
            System.err.print("Wrong building the message" + e);
        }
    }

    public void run(){
        init();
        try{
            Transport.send(message);
        } catch (MessagingException e) {
            System.err.println("Exception with sending message" + e);
        }
    }
}
