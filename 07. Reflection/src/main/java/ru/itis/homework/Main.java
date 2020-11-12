package ru.itis.homework;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class Main {
    public static void main(String[] args) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/weblab");
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        hikariConfig.setUsername("postgres");
        hikariConfig.setPassword("111");
        hikariConfig.setMaximumPoolSize(Integer.parseInt("10"));
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        EntityManager entityManager = new EntityManager(dataSource);
        User user = new User(1L, "Roman", "Leontev", true);
        entityManager.createTable("user", user.getClass());
        entityManager.save("user", user);
        User user1 = entityManager.findById("user", user.getClass(), Long.class, 1L);
        System.out.println(user1);
    }
}
