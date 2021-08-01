package ru.otus.gpb.klunichenko.questionnaire.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.otus.gpb.klunichenko.questionnaire.config.GlobalProperties;
import ru.otus.gpb.klunichenko.questionnaire.config.QuestionsProperties;
import ru.otus.gpb.klunichenko.questionnaire.config.model.QuestionProperty;
import ru.otus.gpb.klunichenko.questionnaire.domain.Question;
import ru.otus.gpb.klunichenko.questionnaire.exceptions.QuestionaireException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionsDaoImpl implements QuestionsDao {

    private final QuestionsProperties questionsProperties;

    public QuestionsDaoImpl(QuestionsProperties questionsProperties) {
        this.questionsProperties = questionsProperties;

    }

    public List<Question> getAll()  {
        List<Question> rows = new ArrayList<>();

        for(QuestionProperty property:questionsProperties.getQuestions()){
            rows.add(new Question(property.getQuestion(), property.getAnswer(), property.getAnswers()));
        }

        return rows;
    }


}
