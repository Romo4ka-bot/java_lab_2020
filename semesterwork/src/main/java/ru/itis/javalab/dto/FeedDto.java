package ru.itis.javalab.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.models.BookedFeed;
import ru.itis.javalab.models.Feed;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Roman Leontev
 * 23:18 24.01.2021
 * group 11-905
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeedDto {
    private Long feed_id;
    private Long user_id;
    private String title;
    private String photo;
    private String content;
    private String dateFrom;
    private String dateTo;
    private String description;
    private Integer price;
    private Integer stars;
    private String date;

    public static FeedDto fromFeed(Feed feed) {
        return FeedDto.builder()
        .feed_id(feed.getId())
        .title(feed.getTitle())
        .photo(feed.getPhoto())
        .content(feed.getContent())
        .dateFrom(feed.getDateFrom())
        .dateTo(feed.getDateTo())
        .description(feed.getDescription())
        .price(feed.getPrice())
        .stars(feed.getStars())
        .build();
    }

    public static List<FeedDto> fromFeeds(List<Feed> feeds) {
        return feeds.stream()
                .map(FeedDto::fromFeed)
                .collect(Collectors.toList());
    }

    public static FeedDto fromBookedFeed(BookedFeed bookedFeed) {
        return FeedDto.builder()
                .user_id(bookedFeed.getUser().getId())
                .date(bookedFeed.getDate())
                .build();
    }

    public static List<FeedDto> fromBookedFeeds(List<BookedFeed> feeds) {
        return feeds.stream()
                .map(FeedDto::fromBookedFeed)
                .collect(Collectors.toList());
    }
}
