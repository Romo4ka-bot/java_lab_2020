//package ru.itis.javalab.listeners;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//import lombok.SneakyThrows;
//import ru.itis.javalab.repositories.*;
//import ru.itis.javalab.services.*;
//
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//
//@WebListener
//public class AppConfigServletContextListener implements ServletContextListener {
//    @SneakyThrows
//    @Override
//    public void contextInitialized(ServletContextEvent servletContextEvent) {
//
////        Properties properties = new Properties();
////        try {
////            properties.load(servletContextEvent.getServletContext().getResourceAsStream("/WEB-INF/properties/db.properties"));
////        } catch (IOException e) {
////            throw new IllegalStateException(e);
////        }
//
////        HikariConfig hikariConfig = new HikariConfig();
////        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/space-taxi");
////        hikariConfig.setUsername("postgres");
////        hikariConfig.setDriverClassName("org.postgresql.Driver");
////        hikariConfig.setPassword("111");
////        hikariConfig.setMaximumPoolSize(10);
////
////
////        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
////        servletContextEvent.getServletContext().setAttribute("dataSource", dataSource);
////
////
////
////        UsersRepository usersRepositoryJdbcImpl = new UsersRepositoryJdbcImpl(dataSource);
////        UsersService usersService = new UsersServiceImpl(usersRepositoryJdbcImpl);
////
////        FeedRepository feedRepository = new FeedRepositoryJdbcImpl(dataSource);
////        FeedService feedService = new FeedServiceImpl(feedRepository);
////
////        NewsRepository newsRepository = new NewsRepositoryJdbcImpl(dataSource, usersService);
////        NewsService newsService = new NewsServiceImpl(newsRepository);
////
////        ReviewRepository reviewRepository = new ReviewRepositoryJdbcImpl(dataSource, usersService, feedService);
////        ReviewService reviewService = new ReviewServiceImpl(reviewRepository);
////
////        servletContextEvent.getServletContext().setAttribute("dataSource", dataSource);
////        servletContextEvent.getServletContext().setAttribute("userService", usersService);
////        servletContextEvent.getServletContext().setAttribute("feedService", feedService);
////        servletContextEvent.getServletContext().setAttribute("newsService", newsService);
////        servletContextEvent.getServletContext().setAttribute("reviewService", reviewService);
//    }
//
//    @Override
//    public void contextDestroyed(ServletContextEvent servletContextEvent) {
//
//    }
//}
