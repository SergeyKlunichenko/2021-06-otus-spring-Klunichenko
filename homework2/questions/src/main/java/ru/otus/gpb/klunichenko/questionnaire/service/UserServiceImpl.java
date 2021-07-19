package ru.otus.gpb.klunichenko.questionnaire.service;

import org.springframework.stereotype.Component;
import ru.otus.gpb.klunichenko.questionnaire.domain.User;
import ru.otus.gpb.klunichenko.questionnaire.tools.IOService;

import java.io.IOException;

@Component
public class UserServiceImpl implements UserService{
    final  private  IOService ioService;
    private User        user;
    public UserServiceImpl(IOService ioService){
        this.ioService  = ioService;
        this.user       =   new User();
    }

    public void login() {
        ioService.println("Please enter your surname and name");

        ioService.printf("Surname:");
        user.setSurname(ioService.readLine());


        ioService.printf("Name:");
        user.setName(ioService.readLine());
    }

    @Override
    public User getUser() {
        return user;
    }
}
