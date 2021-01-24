package ru.itis.javalab.models;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
public class BookedFeed {
    private Long id;
    private User user;
    private Feed feed;
    private String date;
}
