package ru.otus.spring.autoreport.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.autoreport.dto.UserDto;
import ru.otus.spring.autoreport.models.User;
import ru.otus.spring.autoreport.services.UserService;

@Controller
public class UserController {
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping("/user")
    public String getUsersPage(Model model){
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @GetMapping("/user/{id}")
    public String editUserPage(@PathVariable("id") long id, Model model){
        User user = new User();
        if(id != 0){
            user = userService.findById(id);
        }
        model.addAttribute("user", user);
        return "useredit";
    }


    @PostMapping("/useredit")
    public String saveUser(User user){
        userService.save(user);

        return "redirect:/user";
    }

    @GetMapping("/password/{id}")
    public String editUserPassword(@PathVariable("id") long id, Model model){
        UserDto userDto = UserDto.toDto(userService.findById(id));
        model.addAttribute("user", userDto);
        return "passwordedit";
    }

    @PostMapping("/password")
    public String savePassword(UserDto userDto){
        User user = userService.findById(userDto.getId());
        user.setPassword( bCryptPasswordEncoder.encode(userDto.getNewPassword()));

        userService.save(user);

        return "redirect:/user";
    }

    @GetMapping("/register")
    public String getRegistersPage(){
        return "registers";
    }
}
