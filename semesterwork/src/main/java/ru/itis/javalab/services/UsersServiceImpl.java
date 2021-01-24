package ru.itis.javalab.services;


import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UsersRepository;

public class UsersServiceImpl implements UsersService {

    private UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public User authUser(String login) {
        return usersRepository.findByLogin(login);
    }

    @Override
    public boolean regUser(User user) {
        if (userIsExist(user.getLogin()) == null) {
            usersRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public User userIsExist(String login) {
        return usersRepository.findByLogin(login);
    }

    @Override
    public User getUserById(Long user_id) {
        return usersRepository.findById(user_id);
    }

    @Override
    public void updateUser(User user) {
        usersRepository.update(user);
    }

    @Override
    public User getUserByLogin(String login) {
        return usersRepository.findByLogin(login);
    }
}
