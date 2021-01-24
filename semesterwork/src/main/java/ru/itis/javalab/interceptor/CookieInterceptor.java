package ru.itis.javalab.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import ru.itis.javalab.models.User;
import ru.itis.javalab.services.UsersService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Roman Leontev
 * 13:10 24.01.2021
 * group 11-905
 */

@Component
public class CookieInterceptor implements HandlerInterceptor {

    @Autowired
    private UsersService usersService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        Cookie[] cookies = request.getCookies();
        String currCookie = null;
        User user = null;

        if (cookies != null) {

            for (Cookie cookie : cookies) {

                if (cookie.getName().equals("userLogin")) {
                    currCookie = cookie.getValue();
                }
            }

            if (currCookie != null)  {
                user = usersService.getUserByLogin(currCookie);
            }

            if (user != null) {
                session.setAttribute("user", user);
            }
        }
        return true;
    }
}
