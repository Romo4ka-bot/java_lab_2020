package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.javalab.models.BookedFeed;
import ru.itis.javalab.models.Feed;
import ru.itis.javalab.models.User;
import ru.itis.javalab.services.BookedFeedService;
import ru.itis.javalab.services.FeedService;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Roman Leontev
 * 13:13 07.12.2020
 * group 11-905
 */

@Controller
public class ConfirmationSuccessController {

    @Autowired
    HttpSession session;

    @Autowired
    FeedService feedService;

    @Autowired
    BookedFeedService bookedFeedService;

    @GetMapping("confirmation_success")
    public String getConfirmationSuccessPage(@RequestParam(value = "id") String feedId, Model model) {

        User user = (User) session.getAttribute("user");

        if (user != null) {

            Feed feed = feedService.getFeedById(Long.parseLong(feedId));
            BookedFeed bookedFeed = BookedFeed.builder().build();

            Date dateNow = new Date();
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd");

            String date = formatForDateNow.format(dateNow);
            user.setDateRegistration(date);

            bookedFeed.setUser(user);
            bookedFeed.setFeed(feed);
            bookedFeed.setDate(date);
            boolean status = bookedFeedService.addBookedFeed(bookedFeed);

            if (status)
                return "Confirmation_success";
            else {
                model.addAttribute("errMessage", "You have already purchased this package");
                return "redirect:ticket";
            }
        } else return "/login";
    }
}
