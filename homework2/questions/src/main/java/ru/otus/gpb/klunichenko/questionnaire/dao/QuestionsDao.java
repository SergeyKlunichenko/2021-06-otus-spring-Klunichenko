package ru.otus.gpb.klunichenko.questionnaire.dao;

import ru.otus.gpb.klunichenko.questionnaire.domain.Question;

import java.io.IOException;
import java.util.List;
public interface QuestionsDao {
    List<Question> getAll() throws IOException;
    String toString();
}
