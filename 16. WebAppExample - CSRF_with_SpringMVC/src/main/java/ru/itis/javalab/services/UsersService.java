package ru.itis.javalab.services;

import ru.itis.javalab.models.User;

import java.util.Optional;

public interface UsersService {

    Optional<User> getUserByLoginAndPass(String login, String password);

    void deleteUserById(long id);
}
