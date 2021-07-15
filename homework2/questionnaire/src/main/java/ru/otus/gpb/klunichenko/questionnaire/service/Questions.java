package ru.otus.gpb.klunichenko.questionnaire.service;

import java.util.List;

public interface Questions {
     void loadQuestions(List<String[]> rows);
    void askToQuestions();

    void printResult();

}
