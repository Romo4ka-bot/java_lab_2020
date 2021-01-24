package ru.itis.javalab.repositories;

import ru.itis.javalab.models.Review;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review> {

    List<Review> findAllByFeedId(Long feed_id);
}
