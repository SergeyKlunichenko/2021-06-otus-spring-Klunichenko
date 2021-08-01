package ru.otus.gpb.klunichenko.questionnaire.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Класс User")
class UserTest {
    @DisplayName("Конструктор User")
    @Test

    void shoulHaveCorrectConstructor(){
        User user =  new User("Иван",  "Петров");
        /*
        assertEquals("Иван", user.getName());
        assertEquals("Петров", user.getSurname());
         */

        assertAll("user",
                ()->assertEquals("Иван", user.getName()),
                ()->assertEquals("Петров", user.getSurname())
        );
    }

}