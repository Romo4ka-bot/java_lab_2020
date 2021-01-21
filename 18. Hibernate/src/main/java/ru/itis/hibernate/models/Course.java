package ru.itis.hibernate.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
public class Course {
    private Long id;
    private String title;

    private List<Lesson> lessons;
}
