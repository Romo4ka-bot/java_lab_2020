package ru.itis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.itis.repositories.UsersRepository;
import ru.itis.repositories.UsersRepositoryJdbcTemplateImpl;
import ru.itis.services.UsersService;
import ru.itis.services.UsersServiceImpl;
import ru.itis.utils.PasswordCheckerUtil;
import ru.itis.utils.PasswordCheckerUtilImpl;

@Configuration
@ComponentScan("ru.itis")
public class AppConfiguration {

//    @Bean
//    public UsersService usersService() {
//        return new UsersServiceImpl(usersRepository(), passwordChecker());
//    }

    @Bean
    public PasswordCheckerUtil passwordChecker() {
        return new PasswordCheckerUtilImpl();
    }

//    @Bean
//    public UsersRepository usersRepository() {
//        return new UsersRepositoryJdbcTemplateImpl();
//    }
}
