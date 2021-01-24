package ru.itis.javalab.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Roman Leontev
 * 13:13 07.12.2020
 * group 11-905
 */

@Controller
public class ConfirmationSuccessController {
    @RequestMapping(value = "/confirmation_success", method = RequestMethod.GET)
    public ModelAndView getConfirmationSuccessPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Confirmation_success");
        return modelAndView;
    }
}
