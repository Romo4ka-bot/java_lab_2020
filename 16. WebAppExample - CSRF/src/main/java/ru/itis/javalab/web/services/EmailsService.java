package ru.itis.javalab.web.services;

public interface EmailsService {
    void sendMail(String subject, String text, String email);
}
