package ru.itis.javalab.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * @author Roman Leontev
 * 13:04 07.12.2020
 * group 11-905
 */

@Controller
public class InfoController {
    @GetMapping(value = "/info")
    public String getInfoPage() {
        return "Info";
    }
}
