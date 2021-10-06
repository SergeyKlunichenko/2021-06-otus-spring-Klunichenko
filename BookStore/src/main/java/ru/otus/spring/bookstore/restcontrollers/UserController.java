package ru.otus.spring.bookstore.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.spring.bookstore.models.Autor;
import ru.otus.spring.bookstore.models.User;
import ru.otus.spring.bookstore.repositories.UserRepository;

import java.util.List;

@Controller
public class UserController {
    public final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public String  findAll(Model model) {

        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "users";
    }


}
