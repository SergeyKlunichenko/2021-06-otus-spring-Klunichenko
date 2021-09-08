package ru.otus.spring.bookstore.exceptions;

public class BookStoreException extends RuntimeException{

    public BookStoreException(String format, String ... args){
        super(String.format(format, args));
    }

}
