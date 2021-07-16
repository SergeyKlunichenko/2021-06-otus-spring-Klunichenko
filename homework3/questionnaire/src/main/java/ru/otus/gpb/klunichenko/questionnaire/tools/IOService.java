package ru.otus.gpb.klunichenko.questionnaire.tools;

public interface IOService {
    void println(String message);
    void printf(String format, String  ... args);
    String readLine();
}
