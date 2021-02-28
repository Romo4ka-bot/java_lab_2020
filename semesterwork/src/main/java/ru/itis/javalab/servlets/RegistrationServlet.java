package ru.itis.javalab.servlets;

import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UsersRepositoryJdbcImpl;
import ru.itis.javalab.services.UsersService;
import ru.itis.javalab.services.UsersServiceImpl;
import ru.itis.javalab.util.HashPassword;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationServlet extends HttpServlet {

    private UsersService usersService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        DataSource dataSource = (DataSource) config.getServletContext().getAttribute("dataSource");
        UsersRepositoryJdbcImpl usersRepositoryJdbcImpl = new UsersRepositoryJdbcImpl(dataSource);
        usersService = new UsersServiceImpl(usersRepositoryJdbcImpl);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("first_name");
        String surname = req.getParameter("second_name");
        String login = req.getParameter("email");
        String gender = req.getParameter("gender");
        String password1 = req.getParameter("password1");
        String password2 = req.getParameter("password2");

        Pattern patternLogin = Pattern.compile("^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})$");
        Matcher matcherLogin = patternLogin.matcher(login);

        Pattern patternNames = Pattern.compile("^[А-Яа-яЁёA-Za-z]+$");
        Matcher matcherName = patternNames.matcher(name);
        Matcher matcherSurname = patternNames.matcher(surname);

        if (matcherLogin.matches() && matcherName.matches() && matcherSurname.matches() && password1.equals(password2)) {

            User user = User.builder().build();

            user.setName(name);
            user.setSurname(surname);
            user.setLogin(login);
            user.setGender(gender);
            user.setPassword(HashPassword.hashing(password1));

            Date dateNow = new Date();
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd");

            String date = formatForDateNow.format(dateNow);
            user.setDateRegistration(date);

//            boolean status = usersService.regUser(user);

//            if (status) {
//                User userDb = usersService.getUserByLogin(login);
//                HttpSession session = req.getSession();
//                session.setAttribute("user", userDb);
//                req.getRequestDispatcher("/Login.ftl").forward(req, resp);
//            } else {
//                req.setAttribute("errMessage", "this user already exists");
//                req.getRequestDispatcher("/Sign_up.ftl").forward(req, resp);
//            }
//        } else {
//            req.setAttribute("errMessage", "this user already exists");
//            req.getRequestDispatcher("/Sign_up.ftl").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/Sign_up.ftl").forward(req, resp);
    }
}
