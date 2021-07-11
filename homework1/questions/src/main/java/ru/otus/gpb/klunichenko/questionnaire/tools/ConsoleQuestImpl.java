package ru.otus.gpb.klunichenko.questionnaire.tools;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.PrintStream;

@Configuration
@Service
public class ConsoleQuestImpl implements ConsoleQuest {
    private PrintStream printStream;
    private InputStream intputStream;
    public ConsoleQuestImpl(){
        this.printStream    =   System.out;
        this.intputStream   =   System.in;
    }

    public ConsoleQuestImpl(PrintStream printStream){
        this.printStream    =   printStream;
        this.intputStream   =   System.in;
    }

    public ConsoleQuestImpl(InputStream intputStream){
        this.printStream    =   System.out;
        this.intputStream   =   intputStream;
    }

    public ConsoleQuestImpl(InputStream intputStream, PrintStream printStream){
        this.intputStream   =   intputStream;
        this.printStream    =   printStream;

    }

    public void println(String message){
        this.printStream.println(message);
    }
    public void printf(String format, Object ... args ){
        this.printStream.printf(format, args);
    }





}
