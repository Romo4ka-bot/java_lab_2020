package ru.itis.javalab.filters;

import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UsersRepository;
import ru.itis.javalab.repositories.UsersRepositoryJdbcImpl;
import ru.itis.javalab.services.UsersService;
import ru.itis.javalab.services.UsersServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;

//@WebFilter(filterName = "cookieFilter", urlPatterns = {"/"})
public class CookieFilter implements Filter {

    private UsersService usersService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        DataSource dataSource = (DataSource) filterConfig.getServletContext().getAttribute("dataSource");
        UsersRepository usersRepositoryJdbcImpl = new UsersRepositoryJdbcImpl(dataSource);
        usersService = new UsersServiceImpl(usersRepositoryJdbcImpl);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
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

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
