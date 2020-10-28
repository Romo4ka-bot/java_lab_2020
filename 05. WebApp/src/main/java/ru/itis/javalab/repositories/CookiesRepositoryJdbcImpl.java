package ru.itis.javalab.repositories;

import ru.itis.javalab.models.Cookie;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class CookiesRepositoryJdbcImpl implements CookieRepository {

    private SimpleJdbcTemplate template;
    private RowMapper<Cookie> cookieRowMapper =
            resultSet -> Cookie.builder()
                    .value(resultSet.getString("value"))
                    .build();
    //language=sql
    private static final String SQL_SELECT_BY_VALUE = "select * from cookie where value=?";
    public CookiesRepositoryJdbcImpl(DataSource dataSource) {
        template = new SimpleJdbcTemplate(dataSource);
    }

    @Override
    public Cookie findCookieByValue(String value) {
        return (Cookie) template.query(SQL_SELECT_BY_VALUE, cookieRowMapper, value);
    }

    @Override
    public List<Cookie> findAll() {
        return null;
    }

    @Override
    public Optional<Cookie> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean save(Cookie entity) {
        return false;
    }

    @Override
    public void update(Cookie entity) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(Cookie entity) {

    }
}
