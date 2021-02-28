package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UsersRepository;

import java.util.List;

import static ru.itis.javalab.dto.UserDto.from;

@Transactional
@RestController
public class SearchController {
    @Autowired
    private UsersRepository usersRepository;

    @RequestMapping(value = "/search/users/byNameLikeAndAgeAfter", method = RequestMethod.GET)
    public List<UserDto> searchByNameLikeAndAgeAfter(
            @RequestParam("name") String name,
            @RequestParam("age") Integer age) {
        return from(usersRepository.search(name, age));
    }
}
