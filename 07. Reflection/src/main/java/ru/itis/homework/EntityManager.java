package ru.itis.homework;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class EntityManager {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public EntityManager(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // createTable("account", User.class);
    public <T> void createTable(String tableName, Class<T> entityClass) {
        // сгенерировать CREATE TABLE на основе класса
        // create table account ( id integer, firstName varchar(255), ...))
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("create table if not exists \"").append(tableName).append("\" (\n");
        Field[] fields = entityClass.getDeclaredFields();
        for (Field field : fields) {

            stringBuilder.append(field.getName());

            Class<?> type = field.getType();
            switch (type.getSimpleName()) {
                case ("Long"):
                    stringBuilder.append(" bigint,\n");
                    break;
                case ("String"):
                    stringBuilder.append(" varchar(255),\n");
                    break;
                case ("boolean"):
                    stringBuilder.append(" boolean,\n");
                    break;
                case ("Integer"):
                    stringBuilder.append(" int,\n");
                    break;
                case ("Float"):
                case ("Double"):
                    stringBuilder.append(" decimal,\n");
                    break;
            }
        }

        final String SQL_CREATE_TABLE = stringBuilder.substring(0, stringBuilder.length() - 2) + "\n);";

        jdbcTemplate.execute(SQL_CREATE_TABLE);
    }

    public void save(String tableName, Object entity) {
        Class<?> classOfEntity = entity.getClass();
        // сканируем его поля
        // сканируем значения этих полей
        // генерируем insert into
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("insert into \"").append(tableName).append("\" (");
        Field[] fields = classOfEntity.getDeclaredFields();
        for (Field field : fields) {
            stringBuilder.append(field.getName()).append(", ");
        }

        String currReq = stringBuilder.substring(0, stringBuilder.length() - 2) + ")\n";
        stringBuilder = new StringBuilder(currReq);
        stringBuilder.append("values (");
        for (Field field : fields) {

            field.setAccessible(true);
            Object value;

            try {
                value = field.get(entity);
            } catch (IllegalAccessException e) {
                throw new IllegalStateException(e);
            }

            String type = field.getType().getSimpleName();
            if (type.equals("String")) {
                stringBuilder.append("'").append(value).append("'").append(", ");

            } else {
                stringBuilder.append(value).append(", ");
            }
        }

        final String SQL_INSERT = stringBuilder.substring(0, stringBuilder.length() - 2) + ");";

        jdbcTemplate.update(SQL_INSERT);
    }

    // User user = entityManager.findById("account", User.class, Long.class, 10L);
    public <T, ID> T findById(String tableName, Class<T> resultType, Class<ID> idType, ID idValue) {
        // сгенеририровать select

        final String SQL_SELECT = "select * from \"" + tableName + "\" where id = " + idValue + ";";

        RowMapper<T> rowMapper = (row, i) -> {
            Field[] fields = resultType.getDeclaredFields();
            try {

                Constructor<T> constructor = resultType.getConstructor();
                T t = constructor.newInstance();

                for (Field field: fields) {
                    field.setAccessible(true);
                    field.set(t, (row.getObject(field.getName())));
                }

                return t;

            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                throw new IllegalStateException(e);
            }
        };

        T t;
        try {
            t = jdbcTemplate.queryForObject(SQL_SELECT, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            t = null;
        }

        return t;
    }
}
