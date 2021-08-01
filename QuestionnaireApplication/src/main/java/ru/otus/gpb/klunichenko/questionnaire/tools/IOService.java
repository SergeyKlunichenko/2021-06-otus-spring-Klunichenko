package ru.otus.gpb.klunichenko.questionnaire.tools;

import java.util.Scanner;

public interface IOService {

    void println(String message);
    void printf(String format, Object  ... args);
    String readLine();
}
