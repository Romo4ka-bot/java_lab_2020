package ru.itis.javalab.servlets;

import ru.itis.javalab.models.Feed;
import ru.itis.javalab.repositories.FeedRepositoryJdbcImpl;
import ru.itis.javalab.repositories.ReviewRepositoryJdbcImpl;
import ru.itis.javalab.repositories.UsersRepositoryJdbcImpl;
import com.google.gson.Gson;
import ru.itis.javalab.services.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FeedServlet extends HttpServlet {

    private FeedService feedService;
    private ReviewService reviewService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        DataSource dataSource = (DataSource) config.getServletContext().getAttribute("dataSource");
        FeedRepositoryJdbcImpl feedRepositoryJdbcImpl = new FeedRepositoryJdbcImpl(dataSource);
        ReviewRepositoryJdbcImpl reviewRepositoryJdbcImpl = new ReviewRepositoryJdbcImpl(dataSource, new UsersServiceImpl(new UsersRepositoryJdbcImpl(dataSource)), feedService);
        feedService = new FeedServiceImpl(feedRepositoryJdbcImpl);
        reviewService = new ReviewServiceImpl(reviewRepositoryJdbcImpl);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String search = req.getParameter("search");
        String sorting = req.getParameter("value");
        String priceFrom = req.getParameter("priceFrom");
        String priceTo = req.getParameter("priceTo");
        String s = req.getParameter("tags");
        Integer page =Integer.parseInt(req.getParameter("page"));

        String[] tags = null;
        if (!s.equals("[]")) {
            tags = s.split(",");
            for (int i = 0; i < tags.length; i++) {
                tags[i] = tags[i].replace("[", "");
                tags[i] = tags[i].replace("\"", "");
                tags[i] = tags[i].replace("]", "");
            }
        }
        List<Integer> listStars = new ArrayList<>();

        if (tags != null)
            for (String tag : tags) listStars.add(Integer.parseInt(tag));

        List<Feed> feeds = new ArrayList<>();
        if (priceFrom.equals("") && priceTo.equals("")) {
            switch (sorting) {
                case "normal":
                    feeds = feedService.getAllWithSearch(search, listStars, page);
                    break;
                case "priceIncrease":
                    feeds = feedService.getAllByIncreasePrice(search, listStars, page);
                    break;
                case "priceDecrease":
                    feeds = feedService.getAllByDecreasePrice(search, listStars, page);
                    break;
            }
        } else if (priceFrom.equals("")) {
            switch (sorting) {
                case "normal":
                    feeds = feedService.getAllByLeftLimitPrice(Long.parseLong(priceTo), search, listStars, page);
                    break;
                case "priceIncrease":
                    feeds = feedService.getAllByIncreaseAndLeftLimitPrice(Long.parseLong(priceTo), search, listStars, page);
                    break;
                case "priceDecrease":
                    feeds = feedService.getAllByDecreaseAndLeftLimitPrice(Long.parseLong(priceTo), search, listStars, page);
                    break;
            }
        } else if (priceTo.equals("")) {
            switch (sorting) {
                case "normal":
                    feeds = feedService.getAllByRightLimitPrice(Long.parseLong(priceFrom), search, listStars, page);
                    break;
                case "priceIncrease":
                    feeds = feedService.getAllByIncreaseAndRightLimitPrice(Long.parseLong(priceFrom), search, listStars, page);
                    break;
                case "priceDecrease":
                    feeds = feedService.getAllByDecreaseAndRightLimitPrice(Long.parseLong(priceFrom), search, listStars, page);
                    break;
            }
        } else {
            switch (sorting) {
                case "normal":
                    feeds = feedService.getAllByLimitPrice(Long.parseLong(priceFrom), Long.parseLong(priceTo), search, listStars, page);
                    break;
                case "priceIncrease":
                    feeds = feedService.getAllByIncreaseAndLimitPrice(Long.parseLong(priceFrom), Long.parseLong(priceTo), search, listStars, page);
                    break;
                case "priceDecrease":
                    feeds = feedService.getAllByDecreaseAndLimitPrice(Long.parseLong(priceFrom), Long.parseLong(priceTo), search, listStars, page);
                    break;
            }
        }

        resp.setContentType("application/json");
        String json = new Gson().toJson(feeds);
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        List<Feed> list = feedService.getAllByPage();
//        req.setAttribute("list", list);

        req.getRequestDispatcher("/Feed.ftl").forward(req, resp);
    }
}
