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
        if (request.getMethod().equals("POST")) {
            String requestCsrf = request.getParameter("_csrf_token");
            String sessionCsrf = (String) request.getSession(false).getAttribute("_csrf_token");
            if (!sessionCsrf.equals(requestCsrf)) {
                ResponseUtil.sendForbidden(request, response);
            }
            return true;
        }
        if (request.getMethod().equals("GET")) {
            String csrf = UUID.randomUUID().toString();
            request.setAttribute("_csrf_token", csrf);
            request.getSession().setAttribute("_csrf_token", csrf);
        }
        return true;
    }
}
