package ru.itis.hibernate.models;

import lombok.*;

/**
 * 21.12.2020
 * 18. Hibernate
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString(exclude = "course")
public class Lesson {
    private Long id;
    private String name;

    private Course course;
}
