package ru.itis.javalab.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Roman Leontev
 * 15:42 24.01.2021
 * group 11-905
 */

public class ResponseUtil {
    public static void sendForbidden(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuilder path = new StringBuilder();
        path.append("/sign_in");
        if (!request.getRequestURI().equals("/sign_in")) {
            path
                    .append("?redirect=")
                    .append(request.getRequestURI());
        }
        if (request.getQueryString() != null) {
            path.append("?")
                    .append(request.getQueryString());
        }
        response.setStatus(403);
        response.sendRedirect(path.toString());
    }
}