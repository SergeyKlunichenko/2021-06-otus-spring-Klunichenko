package ru.otus.gpb.klunichenko.questionnaire.service;

import org.springframework.stereotype.Service;
import ru.otus.gpb.klunichenko.questionnaire.config.MessageService;
import ru.otus.gpb.klunichenko.questionnaire.domain.User;
import ru.otus.gpb.klunichenko.questionnaire.tools.IOService;

@Service
public class UserServiceImpl implements UserService{
    private final IOService ioService;
    private final MessageService messageService;
    public UserServiceImpl(IOService ioService, MessageService messageService){
        this.ioService  = ioService;
        this.messageService = messageService;
    }

    public User login() {
        User user = new User();
        String name ;
        ioService.println("Please enter your surname and name");

        ioService.printf("Surname:");
        name = ioService.readLine();
        user.setSurname(name.equals("")? messageService.getMessage("UserUnknow") : name);

        ioService.printf("Name:");
        name = ioService.readLine();
        user.setName(name.equals("")? messageService.getMessage("UserNoname") : name);

        if(user.getSurname().equals(messageService.getMessage("UserUnknow")) || user.getName().equals(messageService.getMessage("UserNoname"))){
            return login();
        }

        return user;
    }

}
