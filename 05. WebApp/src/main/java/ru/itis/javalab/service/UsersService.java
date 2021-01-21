package ru.itis.javalab.service;

import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    List<User> getAllUsers();
    List<UserDto> getAllUser(int page, int size);
    Long addUser(UserDto userDto);

    Optional<User> getByUuid(String value);

    User getByUsername(String username);

    void updateUser(User user);
}
