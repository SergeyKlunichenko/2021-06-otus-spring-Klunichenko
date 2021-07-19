package ru.otus.gpb.klunichenko.questionnaire.service;

import org.springframework.stereotype.Component;
import ru.otus.gpb.klunichenko.questionnaire.config.Messages;
import ru.otus.gpb.klunichenko.questionnaire.domain.User;
import ru.otus.gpb.klunichenko.questionnaire.tools.IOService;

import java.io.IOException;

@Component
public class UserServiceImpl implements UserService{
    private  IOService ioService;
    private Messages messages;
    public UserServiceImpl(IOService ioService, Messages messages){
        this.ioService  = ioService;
        this.messages   = messages;
    }

    public User login() {
        User user = new User(messages);
        ioService.println("Please enter your surname and name");
        ioService.printf("Surname:");
        user.setSurname(ioService.readLine());
        ioService.printf("Name:");
        user.setName(ioService.readLine());

        if(user.getSurname().equals(messages.getMessage("UserUnknow")) || user.getName().equals(messages.getMessage("UserNoname"))){
            return login();
        }

        return user;
    }

}
