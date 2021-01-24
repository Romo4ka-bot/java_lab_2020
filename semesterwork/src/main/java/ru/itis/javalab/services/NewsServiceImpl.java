package ru.itis.javalab.services;

import ru.itis.javalab.models.News;
import ru.itis.javalab.repositories.NewsRepository;

import java.util.List;

public class NewsServiceImpl implements NewsService {
    NewsRepository newsRepository;

    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public List<News> getAll() {
        return newsRepository.findAll();
    }

    @Override
    public void addNews(News news) {
        newsRepository.save(news);
    }
}
