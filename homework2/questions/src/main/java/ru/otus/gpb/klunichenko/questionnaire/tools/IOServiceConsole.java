package ru.otus.gpb.klunichenko.questionnaire.tools;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class IOServiceConsole implements IOService {
    Scanner scanner;
    public IOServiceConsole(){
        scanner = new Scanner(System.in);

    }
    public void println(String message){
        System.out.println(message);
    }

    public void printf(String format, String  ... args){
        System.out.printf(format, args);
    }

    public String readLine(){
        return scanner.nextLine();
    }
}
