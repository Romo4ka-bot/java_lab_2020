package ru.itis.javalab.repositories;

import ru.itis.javalab.models.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    //language=SQL
    private static final String SQL_SELECT_BY_AGE = "select * from driver where id = ?";

    //language=SQL
    private static final String SQL_SELECT = "select * from users";

    //language=SQL
    private static final String SQL_INSERT_USER = "insert into users(username, age, uuid, password) " +
            "values(?, ?, ?, ?);";

    //language=SQL
    private static final String SQL_UPDATE = "update users set username = ?, age = ?, uuid = ?, password = ? where id = ?";

    private DataSource dataSource;

    private SimpleJdbcTemplate template;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public UsersRepositoryJdbcImpl(DataSource dataSource, SimpleJdbcTemplate template) {
        this.dataSource = dataSource;
        this.template = template;
    }

    private RowMapper<User> userRowMapper = row -> User.builder()
            .id(row.getLong("id"))
            .username(row.getString("username"))
            .age(row.getInt("age"))
            .uuid(row.getString("uuid"))
            .password(row.getString("password"))
            .build();

    @Override
    public List<User> findAllByAge(Integer age) {
        return template.query(SQL_SELECT_BY_AGE, userRowMapper, age);
    }

    @Override
    public Optional<User> findFirstByFirstnameAndLastname(String firstName, String lastName) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return template.query(SQL_SELECT, userRowMapper);
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean save(User entity) {

        String username = entity.getUsername();
        Integer age = entity.getAge();
        String uuid = entity.getUuid();
        String password = entity.getPassword();

        template.update(SQL_INSERT_USER, username, age, uuid, password);
    }

    @Override
    public void update(User entity) {

        Long id = entity.getId();
        String username = entity.getUsername();
        Integer age = entity.getAge();
        String uuid = entity.getUuid();
        String password = entity.getPassword();

        template.update(SQL_UPDATE, username, age, uuid, password, id);
    }

    @Override
    public void deleteById(Long id) {}

    @Override
    public void delete(User entity) {}
}
