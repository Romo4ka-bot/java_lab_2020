package ru.itis.hibernate.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.itis.hibernate.models.Course;
import ru.itis.hibernate.models.Lesson;

import java.util.Scanner;

/**
 * 21.12.2020
 * 18. Hibernate
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate\\hibernate.cfg.xml");

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        Session session = sessionFactory.openSession();

        Course course = Course.builder()
                .title("Java")
                .build();

        Lesson lesson = Lesson.builder()
                .name("Урок по Hibernate")
                .course(course)
                .build();

        session.save(course);
        session.save(lesson);
        session.close();

        session = sessionFactory.openSession();
        Query<Course> courseQuery = session.createQuery("from Course course " +
                "where course.title = 'Java'", Course.class);

        System.out.println(courseQuery.getSingleResult());

        session.close();
        sessionFactory.close();

    }
}
