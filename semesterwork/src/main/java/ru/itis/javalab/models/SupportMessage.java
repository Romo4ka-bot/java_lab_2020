package ru.itis.javalab.models;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
public class SupportMessage {
    private Long id;
    private Long userId;
    private String name;
    private String surname;
    private String email;
    private String date;
    private String content;
}
