package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.javalab.models.User;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    HttpSession session;

    @GetMapping("/")
    public String getHomePage(Model model) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "Home";
    }
}
