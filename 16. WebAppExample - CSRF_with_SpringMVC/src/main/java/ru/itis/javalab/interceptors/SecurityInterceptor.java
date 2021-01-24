package ru.itis.javalab.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Roman Leontev
 * 17:35 17.01.2021
 * group 11-905
 */

class SecurityInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (isProtected(request.getRequestURI())) {
            return true;
        } else {
            HttpSession session = request.getSession(false);
            if (session != null) {
                Boolean authenticated = (Boolean) session.getAttribute("authenticated");
                if (authenticated != null && authenticated) {
                    return true;
                }
            }
            ResponseUtil.sendForbidden(request, response);
        }
        return false;
    }

    private boolean isProtected(String path) {
        return !path.startsWith("/sign_in") && !path.equals("/favicon.ico");
    }
}