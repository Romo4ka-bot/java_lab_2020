package ru.itis.javalab.servlets;

import ru.itis.javalab.models.BookedFeed;
import ru.itis.javalab.models.Feed;
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

public class ConfirmationSuccessServlet extends HttpServlet {

    private BookedFeedService bookedFeedService;
    private FeedService feedService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        DataSource dataSource = (DataSource) config.getServletContext().getAttribute("dataSource");

        FeedRepository feedRepository = new FeedRepositoryJdbcImpl(dataSource);
        feedService = new FeedServiceImpl(feedRepository);

        UsersRepository usersRepository = new UsersRepositoryJdbcImpl(dataSource);
        UsersService usersService = new UsersServiceImpl(usersRepository);

        BookedFeedRepository bookedFeedRepository = new BookedFeedRepositoryJdbcImpl(dataSource, usersService, feedService);
        bookedFeedService = new BookedFeedServiceImpl(bookedFeedRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {

            String feedId = req.getParameter("id");
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
                req.getRequestDispatcher("/Confirmation_success.ftl").forward(req, resp);
            else {
                req.setAttribute("errMessage", "You have already purchased this package");
                req.getRequestDispatcher("/TicketServlet").forward(req, resp);
            }
        } else req.getRequestDispatcher("LoginServlet").forward(req, resp);
    }
}
