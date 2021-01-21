package ru.itis.javalab.repositories;

import ru.itis.javalab.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends CrudRepository<User> {
    List<User> findAllByAge(Integer age);
    Optional<User> findFirstByFirstnameAndLastname(String firstName, String lastName);

    Optional<User> findByUuid(String value);

    User findAllByUsername(String username);

    Long saveUser(User entity);
}
