package ru.itis.javalab.web.services;

public interface SignInService {
    boolean authenticate(String email, String password);
}
