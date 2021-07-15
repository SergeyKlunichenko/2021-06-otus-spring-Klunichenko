package ru.otus.gpb.klunichenko.questionnaire;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.gpb.klunichenko.questionnaire.domain.Question;
import ru.otus.gpb.klunichenko.questionnaire.tools.ConsoleQuest;
import ru.otus.gpb.klunichenko.questionnaire.tools.ConsoleQuestImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Класс Question")
@SpringBootTest
public class QuestionTest {
    @DisplayName("Тестирование конструктора")
    @Test
    void correctConstructor(){
        String[] array = {"Вопрос", "Ответ", "Варианты ответа"};


        ConsoleQuest console = new ConsoleQuestImpl();

        Question question = new Question(array, console);
        question.setAnswer("Ответ");
        assertEquals("Вопрос", question.getQuestion());
        assertEquals("Варианты ответа", question.getVariantOfAnswer());
        assertEquals("Ответ", question.getCorrectAnswerToQuestion());
        assertEquals("Ответ", question.getAnswer());
    }

}
