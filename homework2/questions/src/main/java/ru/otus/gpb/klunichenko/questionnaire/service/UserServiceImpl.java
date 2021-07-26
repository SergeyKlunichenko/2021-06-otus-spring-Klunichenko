package ru.otus.gpb.klunichenko.questionnaire.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.otus.gpb.klunichenko.questionnaire.config.Messages;
import ru.otus.gpb.klunichenko.questionnaire.domain.User;
import ru.otus.gpb.klunichenko.questionnaire.tools.IOService;

import java.io.IOException;

@Service
public class UserServiceImpl implements UserService{
    private  IOService ioService;
    private Messages messages;
    public UserServiceImpl(IOService ioService, Messages messages){
        this.ioService  = ioService;
        this.messages   = messages;
    }

    public User login() {
        User user = new User();
        String name ;
        ioService.println("Please enter your surname and name");

        ioService.printf("Surname:");
        name = ioService.readLine();
        user.setSurname(name.equals("")? messages.getMessage("UserUnknow") : name);

        ioService.printf("Name:");
        name = ioService.readLine();
        user.setName(name.equals("")? messages.getMessage("UserNoname") : name);

        if(user.getSurname().equals(messages.getMessage("UserUnknow")) || user.getName().equals(messages.getMessage("UserNoname"))){
            return login();
        }

        return user;
    }

}
