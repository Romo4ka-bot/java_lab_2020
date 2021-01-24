package ru.itis.javalab.servlets;

import ru.itis.javalab.models.Feed;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.FeedRepository;
import ru.itis.javalab.repositories.FeedRepositoryJdbcImpl;
import ru.itis.javalab.services.FeedService;
import ru.itis.javalab.services.FeedServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;

public class ConfirmationServlet extends HttpServlet {

    private FeedService feedService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        DataSource dataSource = (DataSource) config.getServletContext().getAttribute("dataSource");

        FeedRepository feedRepository = new FeedRepositoryJdbcImpl(dataSource);
        feedService = new FeedServiceImpl(feedRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            String id = req.getParameter("id");
            Feed feed = feedService.getFeedById(Long.parseLong(id));
            req.setAttribute("feed", feed);
            req.getRequestDispatcher("/Confirmation.ftl").forward(req, resp);
        }
        else
            req.getRequestDispatcher("LoginServlet").forward(req, resp);
    }
}
