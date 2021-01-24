package ru.itis.javalab.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.javalab.models.Feed;
import ru.itis.javalab.services.FeedService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Roman Leontev
 * 13:08 07.12.2020
 * group 11-905
 */

@Controller
public class FeedController {

    @Autowired
    HttpSession httpSession;

    @Autowired
    private FeedService feedService;

    @Autowired
    private ObjectMapper objectMapper;


    @RequestMapping(value = "/feed", method = RequestMethod.GET)
    public ModelAndView getFeedPage(@RequestParam(value = "search", required = false) String search,
                                            @RequestParam(value = "value", required = false) String sorting,
                                            @RequestParam(value = "priceFrom", required = false) String priceFrom,
                                            @RequestParam(value = "priceTo", required = false) String priceTo,
                                            @RequestParam(value = "tags", required = false) String sameTags,
                                            @RequestParam(value = "page") Integer page,
                                            Model model) {
        List<Feed> feeds;
        Integer pages;

        if (search == null) {
            feeds = feedService.getAllByPage(page);
            pages = feedService.getCountAllPages();

        } else {
            String[] tags = sameTags.split(",");

            List<Integer> listStars = new ArrayList<>();

            if (!tags[0].equals(""))
                for (String tag : tags) listStars.add(Integer.parseInt(tag));

            feeds = getListFeeds(search, sorting, priceFrom, priceTo, listStars, page);
            pages = feedService.getCountAllPagesBySearch(search, sorting, priceFrom.equals("") ? -1 : Long.parseLong(priceFrom), priceTo.equals("") ? -1 : Long.parseLong(priceTo), listStars);
        }

        model.addAttribute("list", feeds);
        int[] pagesMass = new int[pages];
        model.addAttribute("pages", pagesMass);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Feed");
        return modelAndView;
    }

    @RequestMapping(value = "/feed", method = RequestMethod.POST)
    public void sendAjax(@RequestParam(value = "search", required = false) String search,
                            @RequestParam(value = "value", required = false) String sorting,
                            @RequestParam(value = "priceFrom", required = false) String priceFrom,
                            @RequestParam(value = "priceTo", required = false) String priceTo,
                            @RequestParam(value = "tags", required = false) String sameTags,
                            @RequestParam(value = "page") Integer page,
                            @RequestParam(value = "alterPages", required = false) Boolean alterPages,
                            HttpServletResponse response) {

        String[] tags = parseJson(sameTags);

        List<Integer> listStars = new ArrayList<>();

        if (tags != null)
            for (String tag : tags) listStars.add(Integer.parseInt(tag));

        List<Feed> feeds = getListFeeds(search, sorting, priceFrom, priceTo, listStars, page);

        Writer writer;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        String json;
        try {
            json = objectMapper.writeValueAsString(feeds);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }

        if (alterPages != null && alterPages && priceFrom != null && priceTo != null) {
            System.out.println(true);
            Integer pages = feedService.getCountAllPagesBySearch(search, sorting, priceFrom.equals("") ? -1 : Long.parseLong(priceFrom), priceTo.equals("") ? -1 : Long.parseLong(priceTo), listStars);
            String jsonPages;
            try {
                jsonPages = objectMapper.writeValueAsString(pages);
            } catch (JsonProcessingException e) {
                throw new IllegalStateException(e);
            }
            json = json.replace("]", "," + jsonPages + "]");
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

    }

    private List<Feed> getListFeeds(String search, String sorting, String priceFrom, String priceTo, List<Integer> listStars, Integer page) {

        if (priceFrom != null && priceTo != null) {
            if (priceFrom.equals("") && priceTo.equals("")) {
                switch (sorting) {
                    case "normal":
                        return feedService.getAllWithSearch(search, listStars, page - 1);
                    case "priceIncrease":
                        return feedService.getAllByIncreasePrice(search, listStars, page - 1);
                    case "priceDecrease":
                        return feedService.getAllByDecreasePrice(search, listStars, page - 1);
                }
            } else if (priceFrom.equals("")) {
                switch (sorting) {
                    case "normal":
                        return feedService.getAllByLeftLimitPrice(Long.parseLong(priceTo), search, listStars, page - 1);
                    case "priceIncrease":
                        return feedService.getAllByIncreaseAndLeftLimitPrice(Long.parseLong(priceTo), search, listStars, page - 1);
                    case "priceDecrease":
                        return feedService.getAllByDecreaseAndLeftLimitPrice(Long.parseLong(priceTo), search, listStars, page - 1);
                }
            } else if (priceTo.equals("")) {
                switch (sorting) {
                    case "normal":
                        return feedService.getAllByRightLimitPrice(Long.parseLong(priceFrom), search, listStars, page - 1);

                    case "priceIncrease":
                        return feedService.getAllByIncreaseAndRightLimitPrice(Long.parseLong(priceFrom), search, listStars, page - 1);
                    case "priceDecrease":
                        return feedService.getAllByDecreaseAndRightLimitPrice(Long.parseLong(priceFrom), search, listStars, page - 1);
                }
            } else {
                switch (sorting) {
                    case "normal":
                        return feedService.getAllByLimitPrice(Long.parseLong(priceFrom), Long.parseLong(priceTo), search, listStars, page - 1);
                    case "priceIncrease":
                        return feedService.getAllByIncreaseAndLimitPrice(Long.parseLong(priceFrom), Long.parseLong(priceTo), search, listStars, page - 1);
                    case "priceDecrease":
                        return feedService.getAllByDecreaseAndLimitPrice(Long.parseLong(priceFrom), Long.parseLong(priceTo), search, listStars, page - 1);
                }
            }
        } else {
            return feedService.getAllByPage(page - 1);
        }
        return null;
    }

    private String[] parseJson(String sameTags) {
        String[] tags = null;
        if (sameTags != null && !sameTags.equals("[]")) {
            tags = sameTags.split(",");
            for (int i = 0; i < tags.length; i++) {
                tags[i] = tags[i].replace("[", "");
                tags[i] = tags[i].replace("\"", "");
                tags[i] = tags[i].replace("]", "");
            }
        }
        return tags;
    }
}
