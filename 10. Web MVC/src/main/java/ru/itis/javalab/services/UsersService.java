package ru.itis.javalab.services;

import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.models.User;

import java.util.List;

public interface UsersService {
    List<User> getAllUsers();
    List<UserDto> getAllUser(int page, int size);
    void addUser(UserDto userDto);
}
