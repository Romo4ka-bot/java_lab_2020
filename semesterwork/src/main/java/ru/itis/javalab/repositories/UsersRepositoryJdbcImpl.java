package ru.itis.javalab.repositories;

import ru.itis.javalab.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    private static final String SQL_SELECT_BY_CONFIRM_CODE = "select * from users where confirm_code = ?";
    private DataSource dataSource;
    private JdbcTemplate template;

    //language=SQL
    private static final String SQL_SELECT_USER_LOG = "select * from users where login = ?";

    //language=SQL
    private static final String SQL_INSERT_USER = "insert into users(name, surname, login, password, gender, date_registration, confirm_code, state) " +
            "values(?, ?, ?, ?, ?, ?, ?, ?);";

    //language=SQL
    private static final String SQL_SELECT_BY_ID = "select * from users where id = ?";

    //language=SQL
    private static final String SQL_UPDATE = "update users set name = ?, surname = ?, photo = ?, date_birthday = ?, gender = ?, info = ?, confirm_code = ?, state = ? where id = ?";

    //language=SQL
    private static final String SQL_SELECT_ALL_WITH_PAGES = "select * from users order by id limit ? offset ?;";

    private RowMapper<User> userRowMapper = (row, i) -> User.builder()
            .id(row.getLong("id"))
            .name(row.getString("name"))
            .surname(row.getString("surname"))
            .login(row.getString("login"))
            .password(row.getString("password"))
            .photo(row.getString("photo"))
            .gender(row.getString("gender"))
            .dateBirthday(row.getString("date_birthday"))
            .dateRegistration(row.getString("date_registration"))
            .info(row.getString("info"))
            .confirmCode(row.getString("confirm_code"))
            .state(Enum.valueOf(User.State.class, row.getString("state")))
            .build();


    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        template = new JdbcTemplate(dataSource);
    }

    @Override
    public User findByLogin(String login) {
        List<User> list = template.query(SQL_SELECT_USER_LOG, userRowMapper, login);
        return !list.isEmpty() ? list.get(0) : null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void save(User entity) {

        String name = entity.getName();
        String surname = entity.getSurname();
        String login = entity.getLogin();
        String password = entity.getPassword();
        String gender = entity.getGender();
        String dateRegistration = entity.getDateRegistration();
        String confirmCode = entity.getConfirmCode();
        String state = entity.getState().toString();

        template.update(SQL_INSERT_USER, name, surname, login, password, gender, dateRegistration, confirmCode, state);
    }

    @Override
    public void update(User entity) {

        Long id = entity.getId();
        String name = entity.getName();
        String surname = entity.getSurname();
        String photo = entity.getPhoto();
        String dateBirthday = entity.getDateBirthday();
        String gender = entity.getGender();
        String info = entity.getInfo();
        String confirmCode = entity.getConfirmCode();
        String state = entity.getState().toString();

        template.update(SQL_UPDATE, name, surname, photo, dateBirthday, gender, info, confirmCode, state, id);
    }

    @Override
    public void delete(User entity) {}

    @Override
    public User findById(Long id) {
        return template.query(SQL_SELECT_BY_ID, userRowMapper, id).get(0);
    }

    @Override
    public List<User> findAll(int page, int size) {
        return template.query(SQL_SELECT_ALL_WITH_PAGES, userRowMapper, size, page * size);
    }

    @Override
    public User findByConfirmCode(String confirmCode) {
        return template.query(SQL_SELECT_BY_CONFIRM_CODE, userRowMapper, confirmCode).get(0);
    }
}
