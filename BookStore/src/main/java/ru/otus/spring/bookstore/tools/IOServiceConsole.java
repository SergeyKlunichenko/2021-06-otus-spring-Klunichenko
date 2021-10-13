package ru.otus.spring.bookstore.tools;

import org.apache.logging.log4j.spi.ObjectThreadContextMap;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import java.util.Scanner;

@Service
public class IOServiceConsole implements IOService {
    private  final  Scanner scanner;
    public IOServiceConsole(){
        scanner = new Scanner(System.in);
    }

    public void println(@Nullable Object message){
        System.out.println(message);
    }

    @Override
    public void println(Throwable throwable) {
        throwable.printStackTrace(System.out);
    }

    @Override
    public void printf(String format, Object  ... args){
        System.out.printf(format, args);
    }

    @Override
    public String readLine(@Nullable  String title) {
        printf(title);
        return scanner.nextLine();
    }
}
