package ru.otus.spring.bookstore.tools;

import org.springframework.lang.Nullable;

import java.util.Scanner;

public interface IOService {

    void println(@Nullable Object object);
    void println(Throwable throwable);
    void printf(String format, Object  ... args);
    String readLine(@Nullable String title);
}
