package ru.itis.javalab.filters;

import ru.itis.javalab.models.User;
import ru.itis.javalab.service.UsersService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

@WebFilter("/*")
public class AuthFilter implements Filter {

    private UsersService usersService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext servletContext = filterConfig.getServletContext();
        usersService = (UsersService) servletContext.getAttribute("usersService");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        AtomicBoolean flag = new AtomicBoolean(false);

        // 1) Homework with cookie

//        Cookie[] cookies = request.getCookies();
//
//        if (cookies != null)
//
//            Arrays.stream(cookies).filter(cookie -> cookie.getName().equals("Auth")).forEach(cookie ->
//                    usersService.getByUuid(cookie.getValue()).ifPresent(userCurr -> flag.set(true)));

        // 2) Homework with session

        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            usersService.getByUuid(user.getUuid()).ifPresent(userCurr -> flag.set(true));
        }

        if (!flag.get() && !request.getRequestURI().equals("/login")) {
            response.sendRedirect("/login");
            return;
        }



        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
