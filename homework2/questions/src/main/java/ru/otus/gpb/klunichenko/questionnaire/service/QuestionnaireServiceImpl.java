package ru.otus.gpb.klunichenko.questionnaire.service;

import org.springframework.stereotype.Service;
import ru.otus.gpb.klunichenko.questionnaire.domain.AnswerToQuestion;
import ru.otus.gpb.klunichenko.questionnaire.domain.User;
import ru.otus.gpb.klunichenko.questionnaire.tools.IOService;

import java.util.List;


@Service
public class QuestionnaireServiceImpl implements  QuestionnaireService{
    private QuestionsService questions;
    private IOService ioService;
    private UserService userService;

    public QuestionnaireServiceImpl(QuestionsService questions, IOService ioService, UserService userService) {
        this.ioService = ioService;
        this.questions  =   questions;
        this.userService = userService;
    }

    @Override
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
    }
}
