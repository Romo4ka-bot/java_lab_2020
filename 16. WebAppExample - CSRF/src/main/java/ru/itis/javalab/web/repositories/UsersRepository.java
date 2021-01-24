package ru.itis.javalab.web.repositories;

import ru.itis.javalab.web.models.User;

import java.util.Optional;

public interface UsersRepository extends CrudRepository<User> {
    Optional<User> findOneByEmail(String email);
}
