package ru.itis.javalab.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.javalab.repositories.UsersRepository;
import ru.itis.javalab.repositories.UsersRepositoryJdbcTemplateImpl;
import ru.itis.javalab.service.UsersService;
import ru.itis.javalab.service.UsersServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.util.Properties;

@WebListener
public class AppConfigServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();

        Properties properties = new Properties();
        try {
            properties.load(servletContext.getResourceAsStream("/WEB-INF/properties/db.properties"));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(properties.getProperty("db.url"));
        hikariConfig.setDriverClassName(properties.getProperty("db.driver.classname"));
        hikariConfig.setUsername(properties.getProperty("db.username"));
        hikariConfig.setPassword(properties.getProperty("db.password"));
        hikariConfig.setMaximumPoolSize(Integer.parseInt(properties.getProperty("db.hikari.max-pool-size")));
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        servletContext.setAttribute("dataSource", dataSource);

        UsersRepository usersRepository = new UsersRepositoryJdbcTemplateImpl(dataSource);
        UsersService usersService = new UsersServiceImpl(usersRepository);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        ObjectMapper objectMapper = new ObjectMapper();

        servletContext.setAttribute("usersService", usersService);
        servletContext.setAttribute("objectMapper", objectMapper);
        servletContext.setAttribute("passwordEncoder", passwordEncoder);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
