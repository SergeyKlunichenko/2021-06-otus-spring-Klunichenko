package ru.otus.spring.autoreport.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.autoreport.repositories.UserRepository;


@RestController
public class UserRestController {
    private final UserRepository userRepository;

    public UserRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @DeleteMapping("/rest/user/{id}")
    public void deleteUser(@PathVariable long id){
        userRepository.deleteById(id);
    }
}
