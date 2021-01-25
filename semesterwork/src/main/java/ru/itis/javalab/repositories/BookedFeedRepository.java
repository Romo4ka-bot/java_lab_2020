package ru.itis.javalab.repositories;

import ru.itis.javalab.dto.FeedDto;
import ru.itis.javalab.models.BookedFeed;

import java.util.List;

public interface BookedFeedRepository extends CrudRepository<BookedFeed> {
    BookedFeed find(BookedFeed bookedFeed);

    List<FeedDto> findAllByUserId(Long id);
}
