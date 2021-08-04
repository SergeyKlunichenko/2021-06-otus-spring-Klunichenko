package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.config.MessageService;
import ru.otus.spring.domain.User;
import ru.otus.spring.tools.IOService;

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
        ioService.println(messageService.getMessage("loginMessage"));

        ioService.printf(messageService.getMessage("label.Surname")+":");
        name = ioService.readLine();
        user.setSurname(name.equals("")? messageService.getMessage("UserUnknow") : name);

        ioService.printf(messageService.getMessage("label.Name")+":");
        name = ioService.readLine();
        user.setName(name.equals("")? messageService.getMessage("UserNoname") : name);

        if(user.getSurname().equals(messageService.getMessage("UserUnknow")) || user.getName().equals(messageService.getMessage("UserNoname"))){
            return login();
        }

        return user;
    }

}
