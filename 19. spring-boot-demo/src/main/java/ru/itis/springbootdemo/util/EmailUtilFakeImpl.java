package ru.itis.springbootdemo.util;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author Roman Leontev
 * 12:20 23.02.2021
 * group 11-905
 */

@Component
@Profile("dev")
public class EmailUtilFakeImpl implements EmailUtil {
    @Override
    public void sendMail(String to, String subject, String from, String text) {
        System.out.println(text);
    }
}
