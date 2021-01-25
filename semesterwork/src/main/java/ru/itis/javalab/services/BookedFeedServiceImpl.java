package ru.itis.javalab.services;

import ru.itis.javalab.dto.FeedDto;
import ru.itis.javalab.models.BookedFeed;
import ru.itis.javalab.repositories.BookedFeedRepository;

import java.util.List;

public class BookedFeedServiceImpl implements BookedFeedService {
    BookedFeedRepository bookedFeedRepository;

    public BookedFeedServiceImpl(BookedFeedRepository bookedFeedRepository) {
        this.bookedFeedRepository = bookedFeedRepository;
    }

    @Override
    public boolean addBookedFeed(BookedFeed bookedFeed) {
        if (bookedFeedIsExist(bookedFeed) == null) {
            bookedFeedRepository.save(bookedFeed);
            return true;
        }
        return false;
    }

    private BookedFeed bookedFeedIsExist(BookedFeed bookedFeed) {
        return bookedFeedRepository.find(bookedFeed);
    }

    @Override
    public List<FeedDto> getAllByUserId(Long id) {
        return bookedFeedRepository.findAllByUserId(id);
    }
}
