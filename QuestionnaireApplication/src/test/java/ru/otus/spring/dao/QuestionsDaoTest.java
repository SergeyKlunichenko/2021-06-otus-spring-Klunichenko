package ru.otus.spring.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class QuestionsDaoTest {
    private static final int COUNT_OF_QUESTIONS= 5;

    @Autowired
    QuestionsDao questionsDao;

    @Test
    void getAllTest() {
        assertEquals(questionsDao.getAll().size(), COUNT_OF_QUESTIONS);
    }
}