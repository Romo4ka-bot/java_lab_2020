package ru.itis.springbootdemo.repositories;

import ru.itis.springbootdemo.models.User;

import java.util.List;

public interface UsersRepository {
    List<User> findAll();

    void save(User user);
}
