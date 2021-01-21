package ru.itis.hibernate.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.itis.hibernate.models.Course;

/**
 * 21.12.2020
 * 18. Hibernate
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class MainForStates {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate\\hibernate.cfg.xml");

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        Session session = sessionFactory.openSession();

        Course course = Course.builder()
                .title("Java")
                .build();
        // TRANSIENT

        // PERSISTENT
        session.save(course);
        course.setTitle("Новое название курса");
//        session.save(course); // не вызывается, потому что save требует TRANSIENT-объекта
        session.close();

        // DETACHED ~ TRANSIENT
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
//        session.save(course);
        course = session.createQuery("from Course  where id = 1", Course.class).getSingleResult();
        course.setTitle("Новое название");
        session.save(course);
        transaction.commit();
        session.close();

    }
}
