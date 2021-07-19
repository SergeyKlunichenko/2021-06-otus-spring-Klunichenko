package ru.otus.gpb.klunichenko.questionnaire.service;

import org.springframework.stereotype.Service;
import ru.otus.gpb.klunichenko.questionnaire.domain.User;
import ru.otus.gpb.klunichenko.questionnaire.tools.IOService;



@Service
public class QuestionnaireServiceImpl implements  QuestionnaireService{
    final private QuestionsService questions;
    final private IOService ioService;
    final private User user;
    /*****************************************
     *
     */
    public QuestionnaireServiceImpl(QuestionsService questions, IOService ioService, UserService userService) {
        this.ioService = ioService;
        userService.login();
        this.user       =   userService.getUser();
        this.questions  =   questions;
    }

    @Override
    /*************************************
     *
     */
    public void execute() {

        try {
            ioService.printf("Hi, Mr %s %s, pleas answer a few questions\r\n", user.getSurname(), user.getName());

            questions.askToQuestions();
            questions.printResult();

            ioService.printf("Mr %s %s, thank you\r\n", user.getSurname(), user.getName());
        } catch (Exception e){
            ioService.println("Ошибка выполнения:"+e.getMessage());
        }
    }
}
