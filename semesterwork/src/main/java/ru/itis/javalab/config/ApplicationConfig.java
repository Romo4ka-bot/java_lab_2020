package ru.itis.javalab.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import ru.itis.javalab.repositories.*;
import ru.itis.javalab.services.*;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@PropertySource("classpath:properties/db.properties")
@ComponentScan(basePackages = "ru.itis.javalab")
public class ApplicationConfig {


    @Autowired
    private Environment environment;

    @Bean
    public String uploadDir() {
        return "/Users/romanleontev/Documents/GitHub/space-taxi/data";
    }

    @Bean
    public UsersService usersService() {
        return new UsersServiceImpl(usersRepository());
    }

    @Bean
    public UsersRepository usersRepository() {
        return new UsersRepositoryJdbcImpl(dataSource());
    }

    @Bean
    public NewsService newsService() {
        return new NewsServiceImpl(newsRepository());
    }

    @Bean
    public NewsRepository newsRepository() {
        return new NewsRepositoryJdbcImpl(dataSource(), usersService());
    }

    @Bean
    public FeedService feedService() {
        return new FeedServiceImpl(feedRepository());
    }

    @Bean
    public FeedRepository feedRepository() {
        return new FeedRepositoryJdbcImpl(dataSource());
    }

    @Bean
    public ReviewService reviewService() {
        return new ReviewServiceImpl(reviewRepository());
    }

    @Bean
    public ReviewRepository reviewRepository() {
        return new ReviewRepositoryJdbcImpl(dataSource(), usersService(), feedService());
    }

    @Bean
    public SupportMessageService supportMessageService() {
        return new SupportMessageServiceImpl(supportMessageRepository());
    }

    @Bean
    public SupportMessageRepository supportMessageRepository() {
        return new SupportMessageRepositoryJdbcImpl(dataSource(), usersService());
    }

    @Bean
    public BookedFeedService bookedFeedService() {
        return new BookedFeedServiceImpl(bookedFeedRepository());
    }

    @Bean
    public BookedFeedRepository bookedFeedRepository() {
        return new BookedFeedRepositoryJdbcImpl(dataSource(), usersService(), feedService());
    }

    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(hikariConfig());
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public HikariConfig hikariConfig() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(environment.getProperty("db.url"));
        hikariConfig.setMaximumPoolSize(Integer.parseInt(Objects.requireNonNull(environment.getProperty("db.hikari.max-pool-size"))));
        hikariConfig.setUsername(environment.getProperty("db.username"));
        hikariConfig.setPassword(environment.getProperty("db.password"));
        hikariConfig.setDriverClassName(environment.getProperty("db.driver.classname"));
        return hikariConfig;
    }

    @Bean
    public FreeMarkerViewResolver freemarkerViewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setPrefix("");
        resolver.setSuffix(".ftl");
        resolver.setContentType("text/html;charset=UTF-8");
        return resolver;
    }

    @Bean
    public FreeMarkerConfigurer freemarkerConfig() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setTemplateLoaderPath("/WEB-INF/ftl/");
        return configurer;
    }
}
