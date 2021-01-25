package ru.itis.javalab.services;

import ru.itis.javalab.dto.FeedDto;
import ru.itis.javalab.models.BookedFeed;

import java.util.List;

public interface BookedFeedService {
    boolean addBookedFeed(BookedFeed bookedFeed);

    List<FeedDto> getAllByUserId(Long id);
}
