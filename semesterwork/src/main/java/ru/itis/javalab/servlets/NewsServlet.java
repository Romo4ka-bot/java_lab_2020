package ru.itis.javalab.servlets;

import ru.itis.javalab.models.News;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.NewsRepository;
import ru.itis.javalab.repositories.NewsRepositoryJdbcImpl;
import ru.itis.javalab.repositories.UsersRepository;
import ru.itis.javalab.repositories.UsersRepositoryJdbcImpl;
import ru.itis.javalab.services.NewsService;
import ru.itis.javalab.services.NewsServiceImpl;
import ru.itis.javalab.services.UsersService;
import ru.itis.javalab.services.UsersServiceImpl;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import javax.sql.DataSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@MultipartConfig
public class NewsServlet extends HttpServlet {

    private NewsService newsService;
    private UsersService usersService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        DataSource dataSource = (DataSource) config.getServletContext().getAttribute("dataSource");

        UsersRepository usersRepository = new UsersRepositoryJdbcImpl(dataSource);
        UsersService usersService = new UsersServiceImpl(usersRepository);

        NewsRepository newsRepository = new NewsRepositoryJdbcImpl(dataSource, usersService);
        newsService = new NewsServiceImpl(newsRepository);
        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            String title = req.getParameter("title");
            String content = req.getParameter("content");

            String uploadDir = getServletConfig().getInitParameter("uploadDir");
            Part file = req.getPart("photo");

            String imgName = UUID.randomUUID().toString() +
                    "-" +
                    file.getSubmittedFileName();

            IOUtils.copyLarge(
                    file.getInputStream(),
                    new FileOutputStream(uploadDir +
                            File.separator + imgName

                    )
            );
            News news = News.builder().build();

            if (title.replace(" ", "").equals("") || content.replace(" ", "").equals("")) {
                req.setAttribute("errMessage", "Форма заполнена неверно");
                resp.sendRedirect("/News.ftl");
            }
            news.setTitle(title);
            if (!file.getSubmittedFileName().equals(""))
                news.setPhoto(imgName);
            news.setContent(content);

            Date dateNow = new Date();
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd");

            String date = formatForDateNow.format(dateNow);
            news.setDate(date);

            newsService.addNews(news);

            resp.sendRedirect("NewsServlet");
        }
        else req.getRequestDispatcher("LoginServlet").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<News> news = newsService.getAll();
        req.setAttribute("list", news);

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        req.setAttribute("user", user);

        req.getRequestDispatcher("/News.ftl").forward(req, resp);
    }
}
