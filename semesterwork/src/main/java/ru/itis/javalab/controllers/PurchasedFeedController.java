package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.javalab.dto.FeedDto;
import ru.itis.javalab.models.User;
import ru.itis.javalab.services.BookedFeedService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Roman Leontev
 * 22:37 24.01.2021
 * group 11-905
 */

@Controller
public class PurchasedFeedController {

    @Autowired
    HttpSession session;

    @Autowired
    BookedFeedService bookedFeedService;

    @GetMapping("purchased_feed")
    public String getPurchasedFeed(Model model) {

        User user = (User) session.getAttribute("user");
        if (user != null) {
            List<FeedDto> feeds = bookedFeedService.getAllByUserId(user.getId());
            System.out.println(feeds);
            if (!feeds.isEmpty())
                model.addAttribute("list", feeds);
            return "Purchased_feed";
        }
        else return "redirect:feed";
    }
}
