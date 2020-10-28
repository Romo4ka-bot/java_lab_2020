package ru.itis.javalab.service;

import ru.itis.javalab.models.Cookie;

public interface CookiesService {
    Cookie getCookieByValue(String value);
}
