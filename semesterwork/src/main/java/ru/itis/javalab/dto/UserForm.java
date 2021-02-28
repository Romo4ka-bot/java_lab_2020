package ru.itis.javalab.dto;

import lombok.Data;
import ru.itis.javalab.validation.ValidPassword;
import ru.itis.javalab.validation.ValidPasswords;
import ru.itis.javalab.validation.ValidUniqEmail;

import javax.validation.constraints.Email;

/**
 * @author Roman Leontev
 * 20:28 23.02.2021
 * group 11-905
 */

@Data
@ValidPasswords(
        password1 = "password1",
        password2 = "password2"
)
public class UserForm {
    private String firstName;
    private String secondName;

    @Email(message = "{errors.incorrect.email}")
//    @ValidUniqEmail
    private String login;

    private String gender;

    @ValidPassword(message = "{errors.invalid.password}")
    private String password1;

    private String password2;
}
