package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.javalab.models.Feed;
import ru.itis.javalab.models.Review;
import ru.itis.javalab.models.User;
import ru.itis.javalab.services.FeedService;
import ru.itis.javalab.services.ReviewService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Roman Leontev
 * 08:23 16.12.2020
 * group 11-905
 */

@Controller
public class TicketController {

    @Autowired
    HttpSession session;

    @Autowired
    private FeedService feedService;

    @Autowired
    private ReviewService reviewService;

    @RequestMapping(value = "/ticket", method = RequestMethod.GET)
    public ModelAndView getTicketPage(@RequestParam(value = "id") String id, Model model) {

        Feed feed = feedService.getFeedById(Long.parseLong(id));
        model.addAttribute("feed", feed);

        List<Review> reviews = reviewService.getAllById(Long.parseLong(id));
        model.addAttribute("list", reviews);

        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Tickets");

        return modelAndView;
    }
}
