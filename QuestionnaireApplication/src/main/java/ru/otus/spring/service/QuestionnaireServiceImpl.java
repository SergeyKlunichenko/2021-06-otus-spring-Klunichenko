package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.config.MessageService;
import ru.otus.spring.domain.AnswerToQuestion;
import ru.otus.spring.domain.User;
import ru.otus.spring.tools.IOService;


import java.util.List;


@Service
public class QuestionnaireServiceImpl implements  QuestionnaireService{

    private  final IOService ioService;
    private  final UserService userService;
    private  final QuestionsService questions;
    private  final MessageService messageService;
    public QuestionnaireServiceImpl(QuestionsService questions, IOService ioService, UserService userService, MessageService messageService) {
        this.ioService = ioService ;
        this.questions  = questions;
        this.userService = userService;
        this.messageService = messageService;
    }


    @Override
    public void execute() {
        User user =         userService.login();
        try {
            ioService.println(messageService.getMessage("messages.questions.title", new String[]{user.getSurname(), user.getName()}));
            List<AnswerToQuestion> answers = questions.askToQuestions();

            ioService.println("------------------------------------------------------------------");
            ioService.println(messageService.getMessage("messages.questions.result", null));

            questions.printResult(answers);
            ioService.println(messageService.getMessage("messages.questions.thank", new String[]{user.getSurname(), user.getName()}));
        } catch (Exception e){
            ioService.println("Runtime Error:"+e.getMessage());
        }

    }
}
