package ru.itis.javalab.repositories;

import ru.itis.javalab.models.Feed;

import java.util.List;

public interface FeedRepository extends CrudRepository<Feed> {
    Feed findById(Long id);

    List<Feed> findAllByPage(Integer page);

    List<Feed> findAllWithSearch(String search, List<Integer> listStars, Integer page);
    List<Feed> findAllByIncreasePrice(String search, List<Integer> listStars, Integer page);
    List<Feed> findAllByDecreasePrice(String search, List<Integer> listStars, Integer page);

    List<Feed> findAllByLeftLimitPrice(Long priceTo, String search, List<Integer> listStars, Integer page);
    List<Feed> findAllByIncreaseAndLeftLimitPrice(Long priceTo, String search, List<Integer> listStars, Integer page);
    List<Feed> findAllByDecreaseAndLeftLimitPrice(Long priceTo, String search, List<Integer> listStars, Integer page);

    List<Feed> findAllByRightLimitPrice(Long priceFrom, String search, List<Integer> listStars, Integer page);
    List<Feed> findAllByIncreaseAndRightLimitPrice(Long priceFrom, String search, List<Integer> listStars, Integer page);
    List<Feed> findAllByDecreaseAndRightLimitPrice(Long priceFrom, String search, List<Integer> listStars, Integer page);

    List<Feed> findAllByLimitPrice(Long priceFrom, Long priceTo, String search, List<Integer> listStars, Integer page);
    List<Feed> findAllByIncreaseAndLimitPrice(Long priceFrom, Long priceTo, String search, List<Integer> listStars, Integer page);
    List<Feed> findAllByDecreaseAndLimitPrice(Long priceFrom, Long priceTo, String search, List<Integer> listStars, Integer page);

    Integer findCountAllPages();

    Integer findCountAllPagesBySearch(String search, String sorting, Long priceFrom, Long priceTo, List<Integer> listStars);
}
