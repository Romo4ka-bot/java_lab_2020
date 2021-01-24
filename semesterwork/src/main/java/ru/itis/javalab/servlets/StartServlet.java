package ru.itis.javalab.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class StartServlet extends HttpServlet {
//    private UsersService usersService;
//
//    @Override
//    public void init(ServletConfig filterConfig) throws ServletException {
//        DataSource dataSource = (DataSource) filterConfig.getServletContext().getAttribute("dataSource");
//        UsersRepository usersRepositoryJdbcImpl = new UsersRepositoryJdbcImpl(dataSource);
//        usersService = new UsersServiceImpl(usersRepositoryJdbcImpl);
//    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

//        HttpSession session = req.getSession();
//        Cookie[] cookies = req.getCookies();
//        String currCookie = null;
//        User user = null;
//
//        if (cookies != null) {
//
//            for (Cookie cookie : cookies) {
//
//                if (cookie.getName().equals("userLogin")) {
//                    currCookie = cookie.getValue();
//                }
//            }
//
//            if (currCookie != null)  {
//                user = usersService.getUserByLogin(currCookie);
//            }
//
//            if (user != null) {
//                session.setAttribute("user", user);
//            }
//        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/Home.ftl");
        requestDispatcher.forward(req,resp);
    }
}
