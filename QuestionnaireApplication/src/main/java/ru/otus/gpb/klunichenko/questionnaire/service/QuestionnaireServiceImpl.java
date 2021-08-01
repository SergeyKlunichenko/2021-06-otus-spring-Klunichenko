package ru.otus.gpb.klunichenko.questionnaire.service;

import org.springframework.stereotype.Service;
import ru.otus.gpb.klunichenko.questionnaire.domain.AnswerToQuestion;
import ru.otus.gpb.klunichenko.questionnaire.domain.User;
import ru.otus.gpb.klunichenko.questionnaire.tools.IOService;
//import ru.otus.gpb.klunichenko.questionnaire.domain.AnswerToQuestion;
//import ru.otus.gpb.klunichenko.questionnaire.domain.User;
//import ru.otus.gpb.klunichenko.questionnaire.tools.IOService;
//import ru.otus.gpb.klunichenko.questionnaire.tools.Logged;

import java.util.List;


@Service
public class QuestionnaireServiceImpl implements  QuestionnaireService{
    private  final IOService ioService;
    private  final UserService userService;
    private  final QuestionsService questions;
    /*
    public QuestionnaireServiceImpl(QuestionsService questions, IOService ioService, UserService userService) {
        this.ioService = ioService ; //new IOServiceConsole(System.in);
        this.questions  =   questions;
        this.userService = userService;
    }
    */
 //   public QuestionnaireServiceImpl(IOService ioService, UserService userService) {
    public QuestionnaireServiceImpl(QuestionsService questions, IOService ioService, UserService userService) {
        this.ioService = ioService ; //new IOServiceConsole(System.in);
        this.questions  = questions;
        this.userService = userService;
    }

    @Override
    //@Logged
    public void execute() {
        User user =         userService.login();
        try {
            ioService.printf("Hi, Mr %s %s, pleas answer a few questions\r\n", user.getSurname(), user.getName());
            List<AnswerToQuestion> answers = questions.askToQuestions();

            ioService.println("------------------------------------------------------------------");
            ioService.println("Result:");

            questions.printResult(answers);
            ioService.printf("Mr %s %s, thank you\r\n", user.getSurname(), user.getName());
        } catch (Exception e){
            ioService.println("Runtime Error:"+e.getMessage());
        }
        System.out.println("execute");

    }
}
