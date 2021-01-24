package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.javalab.models.News;
import ru.itis.javalab.services.NewsService;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Roman Leontev
 * 13:09 07.12.2020
 * group 11-905
 */

@MultipartConfig
@Controller
public class NewsController {

    @Autowired
    HttpSession session;

    @Autowired
    private String uploadDir;

    @Autowired
    private NewsService newsService;

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public ModelAndView getNewsPage(Model model) {
        List<News> news = newsService.getAll();
        model.addAttribute("list", news);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("News");
        return modelAndView;
    }
}
