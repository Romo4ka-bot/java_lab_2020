package ru.itis.javalab.repositories;

import ru.itis.javalab.models.News;

public interface NewsRepository extends CrudRepository<News> {

    News findById(Long id);

    @Override
    default void update(News entity) {

    }
}
