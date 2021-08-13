package ru.otus.spring.service;


import ru.otus.spring.domain.AnswerToQuestion;

import java.io.IOException;
import java.util.List;

public interface QuestionsService {

    List<AnswerToQuestion> askToQuestions() throws IOException;
    void printResult(List<AnswerToQuestion> answers);

}
