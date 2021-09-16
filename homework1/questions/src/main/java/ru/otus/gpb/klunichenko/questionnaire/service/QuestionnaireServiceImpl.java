package ru.otus.gpb.klunichenko.questionnaire.service;

import ru.otus.gpb.klunichenko.questionnaire.dao.Parser;
import ru.otus.gpb.klunichenko.questionnaire.domain.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionnaireServiceImpl implements  QuestionnaireService{
    private  String fileInit;
    private  Parser parser;
    private List<Question> questions;

    public QuestionnaireServiceImpl(String fileInit, Parser parser) {
        this.fileInit   =   fileInit;
        this.parser     =   parser;
        this.questions  =   new ArrayList<Question>();

        parser.setFileName(fileInit);


        //System.out.printf("fileInit:%s\r\nparser:%s\r\n", this.fileInit, this.parser.toString());



    }

    @Override
    public void execute() throws Exception{
        parser.parse();
        //заполнить массив с вопросами
        for(String[] fields:parser.getRows()){
            questions.add(new Question(fields));
            //Question question = new Question(fields);
        }

        //задать вопросы
        for(Question question:questions){
            question.askToQuestion();
        }

        System.out.println("\r\nResult:");

        //напечатать  результа
        for(Question question:questions){
            question.printResult();
        }

        //Systeym.out.println("Press any key");
        /*
        try {
            System.in.read();
        } catch (Exception e){

        }

         */
    }
}
