package ru.itis.javalab.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.javalab.services.UsersService;

/**
 * @author Roman Leontev
 * 09:39 28.02.2021
 * group 11-905
 */

@Controller
public class SignUpSuccessController {

    private final UsersService usersService;

    public SignUpSuccessController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/confirm/{confirm-code}")
    public String checkSignUpSuccess(@PathVariable("confirm-code") String confirmCode, Model model) {
        if (usersService.userConfirmCodeIsExist(confirmCode) != null) {
            model.addAttribute("status", "Регистрация прошла успешно!");
        }
        else model.addAttribute("status", "Что-то пошло не так.");
        return "Sign_up_success";
    }
}
