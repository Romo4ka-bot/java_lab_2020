package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.javalab.models.Feed;
import ru.itis.javalab.models.User;
import ru.itis.javalab.services.FeedService;

import javax.servlet.http.HttpSession;

/**
 * @author Roman Leontev
 * 13:11 07.12.2020
 * group 11-905
 */

@Controller
public class ConfirmationController {

    @Autowired
    HttpSession session;

    @Autowired
    FeedService feedService;

    @GetMapping("confirmation")
    public String getConfirmationPage(@RequestParam(value = "id") String id, Model model) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            Feed feed = feedService.getFeedById(Long.parseLong(id));
            model.addAttribute("feed", feed);
            return "Confirmation";
        }
        else
            return "/login";
    }
}
