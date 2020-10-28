package ru.itis.javalab.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
public class User {
    private Long id;
    private String username;
    private Integer age;
    private String uuid;
    private String password;
}

