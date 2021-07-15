package ru.otus.gpb.klunichenko.questionnaire.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import ru.otus.gpb.klunichenko.questionnaire.dao.Parser;
import ru.otus.gpb.klunichenko.questionnaire.domain.Question;
import ru.otus.gpb.klunichenko.questionnaire.tools.ConsoleQuest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class QuestionnaireServiceImpl implements  QuestionnaireService{
    private Questions questions;
    private ConsoleQuest console;

    /*****************************************
     *
     * @param questions
     * @param console
     */
    public QuestionnaireServiceImpl( Questions questions, ConsoleQuest console) {

        this.console    =   console;
        this.questions  =   questions;
    }

    @Override
    /*************************************
     *
     */
    public void execute()  throws IOException {
        console.println("Please answer a few questions");

        questions.askToQuestions();
        questions.printResult();

        console.println("Thank you for the answers");

    }
}
