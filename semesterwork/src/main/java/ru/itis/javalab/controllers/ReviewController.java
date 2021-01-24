package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.javalab.models.Feed;
import ru.itis.javalab.models.Review;
import ru.itis.javalab.models.User;
import ru.itis.javalab.services.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Roman Leontev
 * 12:55 29.12.2020
 * group 11-905
 */

@Controller
public class ReviewController {

    @Autowired
    HttpSession session;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private FeedService feedService;

    @RequestMapping(value = "/review", method = RequestMethod.POST)
    public String sendReviewOnTicket(@RequestParam(value = "content") String content,
                                           @RequestParam(value = "feed_id") Long feed_id) throws ServletException, IOException {

        User user = (User) session.getAttribute("user");
        Feed feed = feedService.getFeedById(feed_id);

        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd");
        String date = formatForDateNow.format(dateNow);

        Review review = Review.builder().build();

        review.setFeed(feed);
        review.setUser(user);
        review.setDate(date);
        review.setContent(content);

        reviewService.addReview(review);
        return "redirect:/ticket?id=" + feed_id;
    }
}
