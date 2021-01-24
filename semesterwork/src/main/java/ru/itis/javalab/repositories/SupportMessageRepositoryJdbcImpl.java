package ru.itis.javalab.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.javalab.models.SupportMessage;
import ru.itis.javalab.services.UsersService;

import javax.sql.DataSource;
import java.util.List;

public class SupportMessageRepositoryJdbcImpl implements SupportMessageRepository {

    private final DataSource dataSource;
    private JdbcTemplate template;
    private UsersService usersService;

    //language=SQL
    private static final String SQL_INSERT_AUTHORIZED = "insert into support_message(users_id, name, surname, email, date, content)" +
            " values (?, ?, ?, ?, ?, ?)";

    //language=SQL
    private static final String SQL_INSERT_NOT_AUTHORIZED = "insert into support_message(name, surname, email, date, content)" +
            " values (?, ?, ?, ?, ?)";

    private RowMapper<SupportMessage> supportMessageRowMapper = (row, i) -> SupportMessage.builder()
            .id(row.getLong("id"))
            .userId(row.getLong("users_id"))
            .name(row.getString("name"))
            .surname(row.getString("surname"))
            .email(row.getString("email"))
            .date(row.getString("date"))
            .content(row.getString("content"))
            .build();

    public SupportMessageRepositoryJdbcImpl(DataSource dataSource, UsersService usersService) {
        this.dataSource = dataSource;
        this.usersService = usersService;
        template = new JdbcTemplate(dataSource);
    }

    @Override
    public List<SupportMessage> findAll() {
        return null;
    }

    @Override
    public void save(SupportMessage entity) {
        Long id = entity.getUserId();
        String name = entity.getName();
        String surname = entity.getSurname();
        String email = entity.getEmail();
        String date = entity.getDate();
        String content = entity.getContent();
        if (id == null)
            template.update(SQL_INSERT_NOT_AUTHORIZED, name, surname, email, date, content);
        else
            template.update(SQL_INSERT_AUTHORIZED, id, name, surname, email, date, content);
    }

    @Override
    public void update(SupportMessage entity) {

    }

    @Override
    public void delete(SupportMessage entity) {

    }
}
