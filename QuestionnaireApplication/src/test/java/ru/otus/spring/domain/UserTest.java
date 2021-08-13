package ru.otus.spring.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserTest {

    @Test
    void userTest() {
        User user = new User("Иван", "Петров" );
        assertEquals(user.getSurname(), "Петров");
        assertEquals(user.getName(), "Иван");
    }

}