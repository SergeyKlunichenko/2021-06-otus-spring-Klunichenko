package ru.otus.gpb.klunichenko.questionnaire.tools;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
public class ConsoleQuestImpl implements  ConsoleQuest{
    public void println(String message){
        System.out.println(message);
    }

    public void printf(String format, String  ... args){
        System.out.printf(format, args);
    }

}
