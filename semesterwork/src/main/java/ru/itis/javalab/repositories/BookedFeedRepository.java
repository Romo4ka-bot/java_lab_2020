package ru.itis.javalab.repositories;

import ru.itis.javalab.models.BookedFeed;

public interface BookedFeedRepository extends CrudRepository<BookedFeed> {
    BookedFeed find(BookedFeed bookedFeed);
}
