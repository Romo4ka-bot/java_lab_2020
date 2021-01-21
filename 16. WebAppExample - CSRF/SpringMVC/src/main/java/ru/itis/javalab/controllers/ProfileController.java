package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.javalab.models.User;
import ru.itis.javalab.services.UsersService;

import javax.servlet.http.HttpSession;

/**
 * @author Roman Leontev
 * 17:30 17.01.2021
 * group 11-905
 */

@Controller
public class ProfileController {

    @Autowired
    UsersService usersService;
    @Autowired
    HttpSession session;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView getProfilePage(Model model) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user_id", user.getId());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Profile");
        return modelAndView;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public ModelAndView deleteUser() {
        User user = (User)session.getAttribute("user");
        usersService.deleteUserById(user.getId());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Sign_in");
        return modelAndView;

    }
}