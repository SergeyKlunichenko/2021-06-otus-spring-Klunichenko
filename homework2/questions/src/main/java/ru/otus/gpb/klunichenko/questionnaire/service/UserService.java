package ru.otus.gpb.klunichenko.questionnaire.service;

import ru.otus.gpb.klunichenko.questionnaire.domain.User;

public interface UserService {
    void login();
    User getUser();
}
