package ru.otus.spring.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.AppConfig;


import java.io.InputStream;
import java.util.Scanner;

@Service
public class IOServiceConsole implements IOService {
    private  final  Scanner scanner;
    private  final MessageSource messageSource;

    public IOServiceConsole(MessageSource messageSource, AppConfig config){
        scanner = new Scanner(System.in);
        this.messageSource = messageSource;
    }

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
