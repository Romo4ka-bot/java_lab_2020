package ru.itis.javalab.services;

import ru.itis.javalab.models.Feed;

import java.util.List;

public interface FeedService {
    Feed getFeedById(Long feedId);

    List<Feed> getAllByPage(Integer page);

    List<Feed> getAllWithSearch(String search, List<Integer> listStars, Integer page);
    List<Feed> getAllByIncreasePrice(String search, List<Integer> listStars, Integer page);
    List<Feed> getAllByDecreasePrice(String search, List<Integer> listStars, Integer page);

    List<Feed> getAllByLeftLimitPrice(Long priceTo, String search, List<Integer> listStars, Integer page);
    List<Feed> getAllByIncreaseAndLeftLimitPrice(Long priceTo, String search, List<Integer> listStars, Integer page);
    List<Feed> getAllByDecreaseAndLeftLimitPrice(Long priceTo, String search, List<Integer> listStars, Integer page);

    List<Feed> getAllByRightLimitPrice(Long priceFrom, String search, List<Integer> listStars, Integer page);
    List<Feed> getAllByIncreaseAndRightLimitPrice(Long priceFrom, String search, List<Integer> listStars, Integer page);
    List<Feed> getAllByDecreaseAndRightLimitPrice(Long priceFrom, String search, List<Integer> listStars, Integer page);

    List<Feed> getAllByLimitPrice(Long priceFrom, Long priceTo, String search, List<Integer> listStars, Integer page);
    List<Feed> getAllByIncreaseAndLimitPrice(Long priceFrom, Long priceTo, String search, List<Integer> listStars, Integer page);
    List<Feed> getAllByDecreaseAndLimitPrice(Long priceFrom, Long priceTo, String search, List<Integer> listStars, Integer page);

    Integer getCountAllPages();

    Integer getCountAllPagesBySearch(String search, String sorting, Long priceFrom, Long priceTo, List<Integer> listStars);
}
