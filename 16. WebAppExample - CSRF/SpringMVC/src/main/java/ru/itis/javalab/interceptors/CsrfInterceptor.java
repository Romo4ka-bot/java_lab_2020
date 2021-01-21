package ru.itis.javalab.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @author Roman Leontev
 * 17:33 17.01.2021
 * group 11-905
 */

public class CsrfInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        HttpSession session = request.getSession();

        if (request.getMethod().equals("POST")) {
            String csrfToken1 = (String) session.getAttribute("_csrf_token");
            String csrfToken2 = request.getParameter("_csrf_token");
            if (!csrfToken1.equals(csrfToken2)) {
                response.sendRedirect("/sign_in");
            }
        }
        else if (request.getMethod().equals("GET")) {
            String _csrf_token = UUID.randomUUID().toString();
            session.setAttribute("_csrf_token", _csrf_token);
            request.setAttribute("_csrf_token", _csrf_token);
        }
    }
}
