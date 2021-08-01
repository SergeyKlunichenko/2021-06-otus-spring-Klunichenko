package ru.otus.gpb.klunichenko.questionnaire.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Scanner;

@Service
public class IOServiceConsole implements IOService {
    private  final  Scanner scanner;

    /*
    public IOServiceConsole(Scanner scaner){
        this.scanner = scaner;
    }
    */

    public IOServiceConsole(){
        scanner = new Scanner(System.in);

    }

/*
    public IOServiceConsole(InputStream in){
        scanner = new Scanner(in);

    }
*/

    public void println(String message){
        System.out.println(message);
    }

    public void printf(String format, Object  ... args){
        System.out.printf(format, args);
    }

    public String readLine(){
        return scanner.nextLine();
    }
}
