package ru.itis.javalab.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.itis.javalab.models.User;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //language=SQL
    private static final String SQL_SELECT_BY_ID = "select * from driver where id = ?";

    //language=SQL
    private static final String SQL_SELECT_ALL_WITH_PAGES = "select * from driver order by id limit :limit offset :offset ;";

    //language=SQL
    private static final String SQL_SELECT_ALL = "select * from driver";

    //language=SQL
    private static final String SQL_SELECT_BY_AGE = "select * from driver where age = ?";

    //language=SQL
    private static final String SQL_INSERT_USER = "insert into users(firstName, lastName, age, uuid, password) " +
            "values(?, ?, ?, ?, ?);";

    //language=SQL
    private static final String SQL_UPDATE = "update users set firstName = ?, lastName = ?, age = ?, uuid = ?, password = ? where id = ?";

    private RowMapper<User> userRowMapper = (row, i) -> User.builder()
            .id(row.getLong("id"))
            .firstName(row.getString("first_name"))
            .lastName(row.getString("last_name"))
            .age(row.getInt("age"))
            .build();

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<User> findAllByAge(Integer age) {
        return jdbcTemplate.query(SQL_SELECT_BY_AGE, userRowMapper, age);
    }

    @Override
    public Optional<User> findFirstByFirstnameAndLastname(String firstName, String lastName) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, userRowMapper);
    }

    @Override
    public List<User> findAll(int page, int size) {
        Map<String, Object> params = new HashMap<>();
        params.put("limit", size);
        params.put("offset", page * size);
        return namedParameterJdbcTemplate.query(SQL_SELECT_ALL_WITH_PAGES, params, userRowMapper);
    }

    @Override
    public Optional<User> findById(Long id) {
        User user;
        try {
            user = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, userRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            user = null;
        }

        return Optional.ofNullable(user);

    }

    @Override
    public void save(User entity) {

        String firstName = entity.getFirstName();
        String lastName = entity.getLastName();
        Integer age = entity.getAge();
        String uuid = entity.getUuid();
        String password = entity.getPassword();

        jdbcTemplate.update(SQL_INSERT_USER, firstName, lastName, age, uuid, password);
    }

    @Override
    public void update(User entity) {

        Long id = entity.getId();
        String firstName = entity.getFirstName();
        String lastName = entity.getLastName();
        Integer age = entity.getAge();
        String uuid = entity.getUuid();
        String password = entity.getPassword();

        jdbcTemplate.update(SQL_UPDATE, firstName, lastName, age, uuid, password, id);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(User entity) {

    }
}
