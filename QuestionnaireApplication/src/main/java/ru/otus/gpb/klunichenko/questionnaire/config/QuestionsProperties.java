package ru.otus.gpb.klunichenko.questionnaire.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import ru.otus.gpb.klunichenko.questionnaire.config.model.QuestionProperty;

import java.util.ArrayList;
import java.util.List;

@Component
@ConfigurationProperties //(prefix = "questions")
public class QuestionsProperties {
    private List<QuestionProperty> questions = new ArrayList<>();

    public List<QuestionProperty> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionProperty> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "QuestionsProperties{" +
                "questions=" + questions +
                '}';
    }
}
