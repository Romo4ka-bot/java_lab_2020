package ru.itis.javalab.services;

import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.dto.UserForm;
import ru.itis.javalab.models.User;

public interface UsersService {

    void signUp(UserForm form);

    User authUser(String login);

    User userIsExist(String login);

    User getUserById(Long user_id);

    void updateUser(User user);

    UserDto getUserByLogin(String login);

    UserDto userConfirmCodeIsExist(String confirmCode);
}
