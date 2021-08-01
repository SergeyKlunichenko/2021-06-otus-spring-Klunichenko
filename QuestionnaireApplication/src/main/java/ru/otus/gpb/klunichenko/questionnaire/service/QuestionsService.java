package ru.otus.gpb.klunichenko.questionnaire.service;

import ru.otus.gpb.klunichenko.questionnaire.domain.AnswerToQuestion;

import java.io.IOException;
import java.util.List;

public interface QuestionsService {

    List<AnswerToQuestion> askToQuestions() throws IOException;
    void printResult(List<AnswerToQuestion> answers);

}
