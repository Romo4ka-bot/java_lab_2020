package ru.itis.javalab.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Roman Leontev
 * 23:22 18.01.2021
 * group 11-905
 */

@Controller
public class LoginController {

    @GetMapping("login")
    public String getLoginPage() {
        return "Login";
    }

    @PostMapping("login")
    public String redirectToHomePage() {
        return "redirect:";
    }
}
