package ru.itis.javalab.repositories;

import ru.itis.javalab.models.User;

import java.util.Optional;

public interface UsersRepository extends CrudRepository<User> {
    void deleteById(Long id);
    Optional<User> findUserByLoginAndPass(String login, String password);
}
