package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.dto.UserForm;
import ru.itis.javalab.services.UsersService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;

/**
 * @author Roman Leontev
 * 23:23 18.01.2021
 * group 11-905
 */

@Controller
public class SignUpController {

    @Autowired
    HttpSession session;

    @Autowired
    private UsersService usersService;

//    @PostMapping("registration")
//    public String addNewUser(@RequestParam(value = "first_name") String name,
//                             @RequestParam(value = "second_name") String surname,
//                             @RequestParam(value = "email") String login,
//                             @RequestParam(value = "gender") String gender,
//                             @RequestParam(value = "password1") String password1,
//                             @RequestParam(value = "password2") String password2,
//                             Model model) throws ServletException, IOException {
//
//        Pattern patternLogin = Pattern.compile("^([A-Za-z0-9_\\-.])+@([A-Za-z0-9_\\-.])+\\.([A-Za-z]{2,4})$");
//        Matcher matcherLogin = patternLogin.matcher(login);
//
//        Pattern patternNames = Pattern.compile("^[А-Яа-яЁёA-Za-z]+$");
//        Matcher matcherName = patternNames.matcher(name);
//        Matcher matcherSurname = patternNames.matcher(surname);
//
//        if (matcherLogin.matches() && matcherName.matches() && matcherSurname.matches() && password1.equals(password2)) {
//
//            User user = User.builder().build();
//
//            user.setName(name);
//            user.setSurname(surname);
//            user.setLogin(login);
//            user.setGender(gender);
//            user.setPassword(HashPassword.hashing(password1));
//
//            Date dateNow = new Date();
//            SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd");
//
//            String date = formatForDateNow.format(dateNow);
//            user.setDateRegistration(date);
//
//            boolean status = usersService.regUser(user);
//
//            if (status) {
//                User userDb = usersService.getUserByLogin(login);
//                session.setAttribute("user", userDb);
//               return "redirect:/login";
//            } else {
//                model.addAttribute("errMessage", "this user already exists");
//                return "Registration";
//            }
//        } else {
//            model.addAttribute("errMessage", "this user already exists");
//           return  "Registration";
//        }
//    }

    @PostMapping("signUp")
    public String signUp(@Valid UserForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().stream().anyMatch(error -> {
                if (Objects.requireNonNull(error.getCodes())[0].equals("userForm.ValidPasswords")) {
                    model.addAttribute("namesErrorMessage", error.getDefaultMessage());
                }
                return true;
            });
            model.addAttribute("userForm", form);
            return "Sign_up";
        } else {
            UserDto userDb = usersService.getUserByLogin(form.getLogin());
            session.setAttribute("user", userDb);
            usersService.signUp(form);
            return "redirect:/login";
        }
    }

    @GetMapping("signUp")
    public String getSignUpPage(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "Sign_up";
    }
}
