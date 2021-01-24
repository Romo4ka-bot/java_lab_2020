package ru.itis.javalab.services;

import ru.itis.javalab.models.User;

public interface UsersService {

    User authUser(String login);

    boolean regUser(User user);

    User userIsExist(String login);

    User getUserById(Long user_id);

    void updateUser(User user);

    User getUserByLogin(String login);
}
