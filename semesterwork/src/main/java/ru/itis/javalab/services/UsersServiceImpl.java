package ru.itis.javalab.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.dto.UserForm;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UsersRepository;
import ru.itis.javalab.util.EmailUtil;
import ru.itis.javalab.util.MailsGenerator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static ru.itis.javalab.dto.UserDto.from;

@Service
public class UsersServiceImpl implements UsersService {

    private UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Autowired
    private MailsGenerator mailsGenerator;

    @Autowired
    private EmailUtil emailUtil;

    @Value("${server.url}")
    private String serverUrl;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void signUp(UserForm form) {

        User newUser = User.builder()
                .name(form.getFirstName())
                .surname(form.getSecondName())
                .login(form.getLogin())
                .password(form.getPassword1())
                .gender(form.getGender())
                .password(form.getPassword1())
                .confirmCode(UUID.randomUUID().toString())
                .state(User.State.NOT_CONFIRMED)
                .build();

        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd");

        String date = formatForDateNow.format(dateNow);
        newUser.setDateRegistration(date);

        usersRepository.save(newUser);
        String confirmMail = mailsGenerator.getMailForConfirm(serverUrl, newUser.getConfirmCode());
        emailUtil.sendMail(newUser.getLogin(), "Регистрация", from, confirmMail);
    }

    @Override
    public User authUser(String login) {
        return usersRepository.findByLogin(login);
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
    public UserDto getUserByLogin(String login) {
        User user = usersRepository.findByLogin(login);
        return user == null ? null : from(user);
    }

    @Override
    public UserDto userConfirmCodeIsExist(String confirmCode) {
        User user = usersRepository.findByConfirmCode(confirmCode);
        if (user == null || user.getState().equals(User.State.CONFIRMED))
            return null;
        else {
            user.setState(User.State.CONFIRMED);
            usersRepository.update(user);
            return from(user);
        }
    }
}
