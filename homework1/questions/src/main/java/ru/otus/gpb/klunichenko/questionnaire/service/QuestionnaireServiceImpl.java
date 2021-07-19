package ru.otus.gpb.klunichenko.questionnaire.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import ru.otus.gpb.klunichenko.questionnaire.dao.Parser;
import ru.otus.gpb.klunichenko.questionnaire.domain.Question;
import ru.otus.gpb.klunichenko.questionnaire.tools.ConsoleQuest;
import ru.otus.gpb.klunichenko.questionnaire.tools.ConsoleQuestImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Configuration
@Service
public class QuestionnaireServiceImpl implements  QuestionnaireService{
    private  final Parser parser;
    private ConsoleQuest console = new ConsoleQuestImpl();

    private List<Question> questions;


    public QuestionnaireServiceImpl(@Value("${questions.filequestions}") String fileInit, Parser parser) {
        this.parser     =   parser;
        this.questions  =   new ArrayList<Question>();

        parser.setFileName(fileInit);
    }

    /*****************************************
     *заполнить массив с вопросами
     */
    void loadQuestions(){
        for(String[] fields:parser.getRows()){
            questions.add(new Question(fields));
        }

    }

    /**********************************************
     * Задать вопросы
     * @throws IOException
     */
    void askQuetions(){
        for(Question question:questions){
            question.askToQuestion();
        }

    }

    /***********************************************
     * Напечатать резултаты опроса
     * @throws IOException
     */
    void printResult(){
        for(Question question:questions){
            question.printResult();
        }

    }


    @Override
    public void execute() throws IOException {
        parser.parse();

        //загрузить вопросы из файла
        loadQuestions();

        //задать вопросы
        askQuetions();

        //напечатать  результаты
        printResult();

        //System.out.println("Thank you for the answers");
        console.println("Thank you for the answers");
    }
}
