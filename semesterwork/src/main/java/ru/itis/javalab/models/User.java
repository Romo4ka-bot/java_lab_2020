package ru.itis.javalab.models;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String photo;
    private String gender;
    private String dateBirthday;
    private String dateRegistration;
    private String info;
    private String confirmCode;
    private State state;

    public enum State {
        CONFIRMED, NOT_CONFIRMED
    }
}
