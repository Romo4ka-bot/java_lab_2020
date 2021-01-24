package ru.itis.javalab.services;

import ru.itis.javalab.models.Feed;
import ru.itis.javalab.repositories.FeedRepository;

import java.util.List;

public class FeedServiceImpl implements FeedService {
    private FeedRepository feedRepository;

    public FeedServiceImpl(FeedRepository feedRepository) {
        this.feedRepository = feedRepository;
    }

    @Override
    public Feed getFeedById(Long feedId) {
        return feedRepository.findById(feedId);
    }

    @Override
    public List<Feed> getAllByPage(Integer page) {
        return feedRepository.findAllByPage(page);
    }

    @Override
    public List<Feed> getAllWithSearch(String search, List<Integer> listStars, Integer page) {
        return feedRepository.findAllWithSearch(search, listStars, page);
    }

    @Override
    public List<Feed> getAllByIncreasePrice(String search, List<Integer> listStars, Integer page) {
        return feedRepository.findAllByIncreasePrice(search, listStars, page);
    }

    @Override
    public List<Feed> getAllByDecreasePrice(String search, List<Integer> listStars, Integer page) {
        return feedRepository.findAllByDecreasePrice(search, listStars, page);
    }

    @Override
    public List<Feed> getAllByLeftLimitPrice(Long priceTo, String search, List<Integer> listStars, Integer page) {
        return feedRepository.findAllByLeftLimitPrice(priceTo, search, listStars, page);
    }

    @Override
    public List<Feed> getAllByIncreaseAndLeftLimitPrice(Long priceTo, String search, List<Integer> listStars, Integer page) {
        return feedRepository.findAllByIncreaseAndLeftLimitPrice(priceTo, search, listStars, page);
    }

    @Override
    public List<Feed> getAllByDecreaseAndLeftLimitPrice(Long priceTo, String search, List<Integer> listStars, Integer page) {
        return feedRepository.findAllByDecreaseAndLeftLimitPrice(priceTo, search, listStars, page);
    }

    @Override
    public List<Feed> getAllByRightLimitPrice(Long priceFrom, String search, List<Integer> listStars, Integer page) {
        return feedRepository.findAllByRightLimitPrice(priceFrom, search, listStars, page);
    }

    @Override
    public List<Feed> getAllByIncreaseAndRightLimitPrice(Long priceFrom, String search, List<Integer> listStars, Integer page) {
        return feedRepository.findAllByIncreaseAndRightLimitPrice(priceFrom, search, listStars, page);
    }

    @Override
    public List<Feed> getAllByDecreaseAndRightLimitPrice(Long priceFrom, String search, List<Integer> listStars, Integer page) {
        return feedRepository.findAllByDecreaseAndRightLimitPrice(priceFrom, search, listStars, page);
    }

    @Override
    public List<Feed> getAllByLimitPrice(Long priceFrom, Long priceTo, String search, List<Integer> listStars, Integer page) {
        return feedRepository.findAllByLimitPrice(priceFrom, priceTo, search, listStars, page);
    }

    @Override
    public List<Feed> getAllByIncreaseAndLimitPrice(Long priceFrom, Long priceTo, String search, List<Integer> listStars, Integer page) {
        return feedRepository.findAllByIncreaseAndLimitPrice(priceFrom, priceTo, search, listStars, page);
    }

    @Override
    public List<Feed> getAllByDecreaseAndLimitPrice(Long priceFrom, Long priceTo, String search, List<Integer> listStars, Integer page) {
        return feedRepository.findAllByDecreaseAndLimitPrice(priceFrom, priceTo, search, listStars, page);
    }

    @Override
    public Integer getCountAllPages() {
        return feedRepository.findCountAllPages();
    }

    @Override
    public Integer getCountAllPagesBySearch(String search, String sorting, Long priceFrom, Long priceTo, List<Integer> listStars) {
        return feedRepository.findCountAllPagesBySearch(search, sorting, priceFrom, priceTo, listStars);
    }
}
