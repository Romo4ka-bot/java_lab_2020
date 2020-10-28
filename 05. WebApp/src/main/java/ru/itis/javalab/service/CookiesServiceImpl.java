package ru.itis.javalab.service;

import ru.itis.javalab.models.Cookie;
import ru.itis.javalab.repositories.CookieRepository;

public class CookiesServiceImpl implements CookiesService {

    private CookieRepository cookieRepository;

    public CookiesServiceImpl(CookieRepository cookieRepository) {
        this.cookieRepository = cookieRepository;
    }

    @Override
    public Cookie getCookieByValue(String value) {
        return cookieRepository.findCookieByValue(value);
    }
}
