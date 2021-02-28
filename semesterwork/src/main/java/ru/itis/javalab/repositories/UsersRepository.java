package ru.itis.javalab.repositories;

import ru.itis.javalab.models.User;

import java.util.List;

public interface UsersRepository extends CrudRepository<User> {
    User findByLogin(String login);
    User findById(Long id);
    List<User> findAll(int page, int size);

    User findByConfirmCode(String confirmCode);
}
