package ru.itis.javalab.filters;

import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UsersRepository;
import ru.itis.javalab.repositories.UsersRepositoryJdbcImpl;
import ru.itis.javalab.services.UsersService;
import ru.itis.javalab.services.UsersServiceImpl;
import ru.itis.javalab.util.HashPassword;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;

//@WebFilter(filterName = "loginFilter", urlPatterns = {"/LoginServlet"})
public class LoginFilter implements Filter {

    private UsersService usersService;


    @Override
    public void init(FilterConfig config) throws ServletException {
        DataSource dataSource = (DataSource) config.getServletContext().getAttribute("dataSource");
        UsersRepository usersRepositoryJdbcImpl = new UsersRepositoryJdbcImpl(dataSource);
        usersService = new UsersServiceImpl(usersRepositoryJdbcImpl);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

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
                request.getRequestDispatcher("/Home.ftl").forward(request, response);

            } else {
                request.setAttribute("errMessage", "Invalid user credentials");
                request.getRequestDispatcher("/Login.ftl").forward(request, response);
            }
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
