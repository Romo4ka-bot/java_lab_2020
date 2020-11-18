package ru.itis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.itis.config.AppConfiguration;
import ru.itis.repositories.UsersRepository;
import ru.itis.services.UsersService;

public class Application {
    public static void main(String[] args) {
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);
        UsersService usersService = applicationContext.getBean(UsersService.class);
        int i = 0;
    }
}
