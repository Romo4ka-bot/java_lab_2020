package ru.itis.springbootdemo.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.itis.springbootdemo.models.User;

import java.util.List;

/**
 * @author Roman Leontev
 * 17:55 21.02.2021
 * group 11-905
 */

@Repository
public class UsersRepositoryImpl implements UsersRepository {

    private static final String SQL_SELECT = "select * from account";
    private static final String SQL_INSERT = "insert into account(email, password, confirmCode, state) values (?, ?, ?, ?)";
    private final JdbcTemplate jdbcTemplate;
    private RowMapper<User> userRowMapper = (row, i) -> User.builder()
            .id(row.getLong("id"))
            .email(row.getString("email"))
            .password(row.getString("password"))
            .confirmCode(row.getString("confirmCode"))
            .state(Enum.valueOf(User.State.class, row.getString("state")))
            .build();

    public UsersRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_SELECT, userRowMapper);
    }

    @Override
    public void save(User user) {
        jdbcTemplate.update(SQL_INSERT, user.getEmail(), user.getPassword(), user.getConfirmCode(), user.getState().toString());
    }
}
