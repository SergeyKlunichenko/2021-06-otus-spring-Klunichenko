package ru.otus.spring.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.spring.config.MessageService;
import ru.otus.spring.domain.User;
import ru.otus.spring.events.QuestionEvent;
import ru.otus.spring.service.QuestionnaireService;
import ru.otus.spring.service.UserService;

@ShellComponent
public class QuestionShell {
    private  final UserService userService;
    private  final QuestionnaireService questionnaireService;
    private  final MessageService messageService;
    private  User user;

    public QuestionShell(UserService userService, QuestionnaireService questionnaireService, MessageService messageService, ApplicationEventPublisher publisher) {
        this.userService = userService;
        this.questionnaireService = questionnaireService;
        this.messageService = messageService;
        this.publisher = publisher;
    }

    @ShellMethod(key={"l", "login"}, value="User authorization ")
    public String login(){
        user = userService.login();
        return messageService.getMessage("messages.login.label.Welcome", user.getSurname(), user.getName());
    }

    @ShellMethod(key= {"e", "exec"}, value="Execute questionarie")
    @ShellMethodAvailability(value = "isUserLogin")
    public String execQuestionarie(){
        questionnaireService.execute();

        return "Ok";
    }

    private final ApplicationEventPublisher publisher;
    @ShellMethod(key="event", value="Execute questionnare on event")
    @ShellMethodAvailability(value = "isUserLogin")
    public String testEvents(){
        QuestionEvent event =  new QuestionEvent(questionnaireService);
        System.out.println("testEvents");

        publisher.publishEvent(event);
        return "OK";
    }


    private Availability isUserLogin() {
        //return user == null? Availability.unavailable(messageService.getMessage("messages.login.label.loginMessage")):Availability.available();
        return user == null? Availability.unavailable(": Login please"):Availability.available();
    }

}
