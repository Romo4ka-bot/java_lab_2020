package ru.itis.javalab.controllers;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Roman Leontev
 * 23:22 18.01.2021
 * group 11-905
 */

@Controller
public class ImageController {

    @Autowired
    private String UPLOAD_DIR;

    @RequestMapping(value = "/img", method = RequestMethod.GET)
    public void sendImage(@RequestParam(value = "filename") String filename, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("image/png");
        IOUtils.copyLarge(
                new FileInputStream(UPLOAD_DIR + File.separator + filename),
                resp.getOutputStream()
        );
    }
}
