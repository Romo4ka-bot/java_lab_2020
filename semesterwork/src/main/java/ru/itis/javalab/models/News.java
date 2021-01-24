package ru.itis.javalab.models;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
public class News {
    private Long id;
    private String title;
    private String photo;
    private String content;
    private String date;
}
