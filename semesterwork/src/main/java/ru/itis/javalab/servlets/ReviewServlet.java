package ru.itis.javalab.servlets;

import ru.itis.javalab.models.Feed;
import ru.itis.javalab.models.Review;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.*;
import ru.itis.javalab.services.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReviewServlet extends HttpServlet {

    private ReviewService reviewService;
    private FeedService feedService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        DataSource dataSource = (DataSource) config.getServletContext().getAttribute("dataSource");

        FeedRepository feedRepository = new FeedRepositoryJdbcImpl(dataSource);
        feedService = new FeedServiceImpl(feedRepository);

        UsersRepository usersRepository = new UsersRepositoryJdbcImpl(dataSource);
        UsersService usersService = new UsersServiceImpl(usersRepository);

        ReviewRepository reviewRepository = new ReviewRepositoryJdbcImpl(dataSource, usersService, feedService);
        reviewService = new ReviewServiceImpl(reviewRepository);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        String content = req.getParameter("comment");
        Long feed_id = Long.parseLong(req.getParameter("feed_id"));
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
        resp.sendRedirect("/ticket?id=" + feed_id);
    }
}
