package ru.itis.javalab.web.services;

import ru.itis.javalab.web.models.User;

import java.util.Optional;

public interface UsersService {
    Optional<User> getUserById(Long id);

    void deleteUserById(long userId);
}
