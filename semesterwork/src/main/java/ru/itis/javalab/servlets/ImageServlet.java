package ru.itis.javalab.servlets;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/img")
public class ImageServlet extends HttpServlet {
    private final String UPLOAD_DIR = "/Users/romanleontev/Documents/GitHub/space-taxi/data";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filename = req.getParameter("filename");

        resp.setContentType("image/png");
        IOUtils.copyLarge(
                new FileInputStream(UPLOAD_DIR + File.separator + filename),
                resp.getOutputStream()
        );
    }
}
