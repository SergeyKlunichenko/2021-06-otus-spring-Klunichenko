package ru.otus.gpb.klunichenko.questionnaire.domain;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Класс Question")
class QuestionTest {
    @DisplayName("корректно создаётся конструктором")
    @Test
     void shouldHaveCorrectConstructor() {
        Question actualQuestion = new Question(new String[]{"question", "rightAnswer", "listOfAnswer"});
        actualQuestion.setAnswer("Answer");

        assertEquals("Answer", actualQuestion.getAnswer());
        assertEquals("listOfAnswer", actualQuestion.getListOfAnswer());
        assertEquals("question", actualQuestion.getQuestion());
        assertEquals("rightAnswer", actualQuestion.getRightAnswer());
    }


}

