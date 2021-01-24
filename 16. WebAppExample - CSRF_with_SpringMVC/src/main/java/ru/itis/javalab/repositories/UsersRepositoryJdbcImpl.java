package ru.itis.javalab.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.itis.javalab.models.User;

import javax.sql.DataSource;
import java.util.Optional;

@Component(value = "usersRepositoryJdbcImpl")
public class UsersRepositoryJdbcImpl implements UsersRepository {

    DataSource dataSource;
    JdbcTemplate jdbcTemplate;

    RowMapper<User> userRowMapper = (row, i) -> User.builder()
            .id(row.getLong("id"))
            .email(row.getString("email"))
            .password(row.getString("password"))
            .isDeleted(row.getBoolean("isDeleted"))
            .build();

    //language=sql
    private static final String SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD = "select * from account where " +
            "login = ? and password = ? and isDeleted = false";

    //language=sql
    private static final String SQL_DELETE_USER_BY_ID = "update account set isDeleted = true where id = ?";

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<User> findUserByLoginAndPass(String login, String password) {
        User user;
        try {
            user = (User) jdbcTemplate.query(SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD, userRowMapper, login, password);
        } catch (EmptyResultDataAccessException e) {
            user = null;
        }
        return Optional.ofNullable(user);
    }

    @Override
    public void save(User entity) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update(SQL_DELETE_USER_BY_ID, id);
    }
}
