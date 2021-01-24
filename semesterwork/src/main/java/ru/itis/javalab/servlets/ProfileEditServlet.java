package ru.itis.javalab.servlets;

import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UsersRepository;
import ru.itis.javalab.repositories.UsersRepositoryJdbcImpl;
import ru.itis.javalab.services.UsersService;
import ru.itis.javalab.services.UsersServiceImpl;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import javax.sql.DataSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@MultipartConfig
public class ProfileEditServlet extends HttpServlet {

    private UsersService usersService;


    @Override
    public void init(ServletConfig config) throws ServletException {
        DataSource dataSource = (DataSource) config.getServletContext().getAttribute("dataSource");
        UsersRepository usersRepositoryJdbcImpl = new UsersRepositoryJdbcImpl(dataSource);
        usersService = new UsersServiceImpl(usersRepositoryJdbcImpl);
        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String date = req.getParameter("date");
        String gender = req.getParameter("gender");
        String info = req.getParameter("info");
        String uploadDir = getServletConfig().getInitParameter("uploadDir");
        Part file = req.getPart("filename");

        String imgName = UUID.randomUUID().toString() +
                "-" +
                file.getSubmittedFileName();

        IOUtils.copyLarge(
                file.getInputStream(),
                new FileOutputStream(uploadDir +
                        File.separator + imgName

                )
        );
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        String nameCheck = name.replace(" ", "");
        String surnameCheck = name.replace(" ", "");

        if (!name.equals("") && !nameCheck.equals(""))
            user.setName(name);
        if (!surname.equals("") && !surnameCheck.equals(""))
            user.setSurname(surname);
        if (!date.equals(""))
            user.setDateBirthday(date);
        if (!file.getSubmittedFileName().equals(""))
            user.setPhoto(imgName);

        user.setGender(gender);
        user.setInfo(info);

        req.setAttribute("user", user);


        usersService.updateUser(user);
        session.setAttribute("user", user);
        req.getRequestDispatcher("/Profile.ftl").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        req.setAttribute("user", user);
        req.getRequestDispatcher("/Profile_edit.ftl").forward(req, resp);
    }
}
