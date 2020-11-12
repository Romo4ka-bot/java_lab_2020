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
import java.util.Optional;

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

//        Cookie[] cookies = request.getCookies();
        boolean flag = false;
//        if (cookies != null)
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("Auth")) {
//                    Optional<User> user = usersService.getByUuid(cookie.getValue());
//                    if (user.isPresent()) {
//                        flag = true;
//                    }
//                }
//            }

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            flag = true;
        }

        if (!flag && !request.getRequestURI().equals("/login")) {
            response.sendRedirect("/login");
            return;
        }



        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
