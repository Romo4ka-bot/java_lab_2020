package ru.itis.javalab.servlets;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.javalab.models.User;
import ru.itis.javalab.service.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UsersService usersService;
    private PasswordEncoder passwordEncoder;

    @Override
    public void init(ServletConfig config) throws ServletException {
        usersService = (UsersService) config.getServletContext().getAttribute("usersService");
        passwordEncoder = (PasswordEncoder) config.getServletContext().getAttribute("passwordEncoder");
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = usersService.getByUsername(username);

        System.out.println(passwordEncoder.matches(password ,user.getPassword()));

        if (user.getUsername().equals(username)
                && passwordEncoder.matches(password ,user.getPassword())) {
//            String uuid = UUID.randomUUID().toString();
//            user.setUuid(uuid);
            usersService.updateUser(user);
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
//            resp.addCookie(new Cookie("Auth", user.getUuid()));
            resp.sendRedirect("/profile");
        }
    }
}
