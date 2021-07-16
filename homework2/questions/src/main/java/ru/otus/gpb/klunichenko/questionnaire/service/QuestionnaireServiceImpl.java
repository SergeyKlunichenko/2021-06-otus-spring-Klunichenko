package ru.otus.gpb.klunichenko.questionnaire.service;

import org.springframework.stereotype.Service;
import ru.otus.gpb.klunichenko.questionnaire.tools.IOService;

@Service
public class QuestionnaireServiceImpl implements  QuestionnaireService{
    final private QuestionsService questions;
    final private IOService ioService;

    /*****************************************
     *
     */
    public QuestionnaireServiceImpl(QuestionsService questions, IOService ioService) {
        this.ioService = ioService;
        this.questions  =   questions;
    }

    @Override
    /*************************************
     *
     */
    public void execute() throws Exception{
        //parser.parse();
        ioService.println("Please answer a few questions");

        questions.askToQuestions();
        questions.printResult();

        ioService.println("Thank you for the answers");

    }
}
