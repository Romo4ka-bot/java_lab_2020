package ru.itis.javalab.servlets;

import ru.itis.javalab.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class InfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        req.setAttribute("user", user);

        req.getRequestDispatcher("/Info.ftl").forward(req, resp);
    }
}
