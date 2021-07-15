package ru.otus.gpb.klunichenko.questionnaire.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.otus.gpb.klunichenko.questionnaire.dao.Parser;
import ru.otus.gpb.klunichenko.questionnaire.domain.Question;
import ru.otus.gpb.klunichenko.questionnaire.tools.ConsoleQuest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionsCsvImpl implements Questions {
    private List<Question> questions = new ArrayList<Question>();
    private ConsoleQuest console;
    public QuestionsCsvImpl(Parser parser, ConsoleQuest console) throws IOException {
        this.console  = console;
        parser.parse();
        loadQuestions(parser.getRows());

    }

    /************************************
     * Загрузить вопросы
     * @param rows
     */
    public void loadQuestions(List<String[]> rows){
        for(String[] fields:rows){
            questions.add(new Question(fields, console));
        }

    }

    /**************************************
     *
     */
    public void askToQuestions(){
        for(Question question:questions){
            question.askToQuestion();
        }

    }

    public void printResult(){
        for(Question question:questions){
            question.printResult();
        }
    }




}
