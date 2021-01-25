package ru.itis.javalab.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.javalab.dto.FeedDto;
import ru.itis.javalab.models.BookedFeed;
import ru.itis.javalab.services.FeedService;
import ru.itis.javalab.services.UsersService;

import javax.sql.DataSource;
import java.util.List;

public class BookedFeedRepositoryJdbcImpl implements BookedFeedRepository {

    private final DataSource dataSource;
    private JdbcTemplate template;
    private UsersService usersService;
    private FeedService feedService;

    //language=SQL
    private static final String SQL_INSERT = "insert into booked_feed(user_id, feed_id, date) values (?, ?, ?)";

    //language=SQL
    private static final String SQL_SELECT = "select * from booked_feed where user_id = ? and feed_id = ?";

    //language=SQL
    private static final String SQL_SELECT_ALL_BY_USER_ID = "select f.id as feed_id, b.user_id as user_id, f.title as title, f.photo as photo, f.content as content, f.date_from as date_from, f.date_to as date_to, f.description as description, f.price as price, f.stars as stars, b.date as date from booked_feed b inner join feed f on b.feed_id = f.id where user_id = ?";

    public BookedFeedRepositoryJdbcImpl(DataSource dataSource, UsersService usersService, FeedService feedService) {
        this.dataSource = dataSource;
        this.usersService = usersService;
        this.feedService = feedService;
        template = new JdbcTemplate(dataSource);
    }

    private RowMapper<BookedFeed> bookedFeedRowMapper = (row, i) -> BookedFeed.builder()
            .id(row.getLong("id"))
            .user(usersService.getUserById(row.getLong("user_id")))
            .feed(feedService.getFeedById(row.getLong("feed_id")))
            .date(row.getString("date"))
            .build();

    private RowMapper<FeedDto> feedDtoRowMapper = (row, i) -> FeedDto.builder()
            .feed_id(row.getLong("feed_id"))
            .user_id(row.getLong("user_id"))
            .title(row.getString("title"))
            .photo(row.getString("photo"))
            .content(row.getString("content"))
            .dateFrom(row.getString("date_from"))
            .dateTo(row.getString("date_to"))
            .description(row.getString("description"))
            .price(row.getInt("price"))
            .stars(row.getInt("stars"))
            .date(row.getString("date"))
            .build();

    @Override
    public List<BookedFeed> findAll() {
        return null;
    }

    @Override
    public void save(BookedFeed entity) {

        Long userId = entity.getUser().getId();
        Long feedId = entity.getFeed().getId();
        String date = entity.getDate();

        template.update(SQL_INSERT, userId, feedId, date);
    }

    @Override
    public void update(BookedFeed entity) {

    }

    @Override
    public void delete(BookedFeed entity) {

    }

    @Override
    public BookedFeed find(BookedFeed entity) {

        Long userId = entity.getUser().getId();
        Long feedId = entity.getFeed().getId();
        List<BookedFeed> list = template.query(SQL_SELECT, bookedFeedRowMapper, userId, feedId);
        return !list.isEmpty() ? list.get(0) : null;
    }

    @Override
    public List<FeedDto> findAllByUserId(Long id) {
        return template.query(SQL_SELECT_ALL_BY_USER_ID, feedDtoRowMapper, id);
    }
}
