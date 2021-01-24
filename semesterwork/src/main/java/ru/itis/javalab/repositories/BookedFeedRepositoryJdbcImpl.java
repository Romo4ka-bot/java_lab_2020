package ru.itis.javalab.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
}
