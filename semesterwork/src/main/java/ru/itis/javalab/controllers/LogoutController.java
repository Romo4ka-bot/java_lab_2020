package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Roman Leontev
 * 23:22 18.01.2021
 * group 11-905
 */

@Controller
public class LogoutController {

    @Autowired
    HttpSession session;

    @GetMapping("logout")
    protected String doGet(HttpServletResponse response) throws ServletException, IOException {

        Cookie cookieUser = new Cookie("userLogin", "logoutUser");
        cookieUser.setMaxAge(1);
        response.addCookie(cookieUser);

        session.removeAttribute("user");

        return "Home";
    }
}
