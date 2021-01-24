package ru.itis.javalab.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class LogoutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cookie cookieUser = new Cookie("userLogin", "logoutUser");
        cookieUser.setMaxAge(1);
        response.addCookie(cookieUser);

        HttpSession session = request.getSession();
        session.removeAttribute("user");

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Home.ftl");
        requestDispatcher.forward(request, response);
    }
}
