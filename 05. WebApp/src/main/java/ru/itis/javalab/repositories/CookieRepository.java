package ru.itis.javalab.repositories;

import ru.itis.javalab.models.Cookie;

public interface CookieRepository extends CrudRepository<Cookie> {
    Cookie findCookieByValue(String value);
}

