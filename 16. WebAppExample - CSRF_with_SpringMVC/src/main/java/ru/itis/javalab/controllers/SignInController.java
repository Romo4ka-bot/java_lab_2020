package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.javalab.models.User;
import ru.itis.javalab.services.UsersService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * @author Roman Leontev
 * 17:29 17.01.2021
 * group 11-905
 */

@Controller
public class SignInController {

    @Autowired
    UsersService usersService;

    @RequestMapping(value = "/sign_in", method = RequestMethod.GET)
    public ModelAndView getSignInPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Sign_in");
        return modelAndView;
    }

    @RequestMapping(value = "/sign_in", method = RequestMethod.POST)
    public ModelAndView authorizationUser(@RequestParam(value = "login") String login,
                             @RequestParam(value = "pass") String pass, HttpServletRequest request) {

        Optional<User> optionalUser = usersService.getUserByLoginAndPass(login, pass);
        ModelAndView modelAndView = new ModelAndView();

        if (optionalUser.isPresent()) {
            HttpSession session = request.getSession();
            session.setAttribute("user", optionalUser.get());
            session.setAttribute("authenticated", true);
            modelAndView.setViewName("Profile");
            return modelAndView;
        }
        modelAndView.setViewName("Sign_in");
        return modelAndView;
    }
}
