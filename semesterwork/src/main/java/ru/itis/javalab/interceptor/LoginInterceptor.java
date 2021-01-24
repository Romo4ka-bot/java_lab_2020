package ru.itis.javalab.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import ru.itis.javalab.models.User;
import ru.itis.javalab.services.UsersService;
import ru.itis.javalab.util.HashPassword;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Roman Leontev
 * 13:14 24.01.2021
 * group 11-905
 */

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UsersService usersService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login != null && password != null) {
            User user = usersService.authUser(login);
            String check = request.getParameter("check");

            if (user != null && user.getPassword().equals(HashPassword.hashing(password))) {

                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                if (check != null) {
                    Cookie cookie = new Cookie("userLogin", login);
                    cookie.setMaxAge(60 * 60 * 24 * 365);
                    response.addCookie(cookie);
                }

            } else {
                request.setAttribute("errMessage", "Invalid user credentials");
                request.getRequestDispatcher("/login").forward(request, response);
            }
        }
        return true;
    }
}
