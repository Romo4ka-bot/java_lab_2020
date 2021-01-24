package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.javalab.models.SupportMessage;
import ru.itis.javalab.models.User;
import ru.itis.javalab.services.SupportMessageService;
import ru.itis.javalab.services.UsersService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Roman Leontev
 * 23:24 18.01.2021
 * group 11-905
 */

@Controller
public class SupportMessageController {

    @Autowired
    HttpSession session;

    @Autowired
    private SupportMessageService supportMessageService;

    @PostMapping("support_message")
    public String sendSupportMessage(@RequestParam(value = "content") String content,
                                        @RequestParam(value = "name", required = false) String name,
                                        @RequestParam(value = "surname") String surname,
                                        @RequestParam(value = "email") String email
                                        ) throws ServletException, IOException {

        User user = (User) session.getAttribute("user");

        Long userId = null;

        if (user != null) {
            userId = user.getId();
            name = user.getName();
            surname = user.getSurname();
            email = user.getLogin();
        }

        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd");

        String date = formatForDateNow.format(dateNow);

        SupportMessage supportMessage = SupportMessage.builder().build();
        supportMessage.setUserId(userId);
        supportMessage.setName(name);
        supportMessage.setSurname(surname);
        supportMessage.setEmail(email);
        supportMessage.setDate(date);
        supportMessage.setContent(content);

        supportMessageService.addSupportMessage(supportMessage);

        return "redirect:info";
    }
}
