package ru.itis.javalab.models;


import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
public class Feed {
    private Long id;
    private String title;
    private String photo;
    private String content;
    private String dateFrom;
    private String dateTo;
    private String description;
    private Integer price;
    private Integer stars;
}
