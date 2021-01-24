package ru.itis.javalab.services;

import ru.itis.javalab.models.News;

import java.util.List;

public interface NewsService {

    List<News> getAll();

    void addNews(News news);
}
