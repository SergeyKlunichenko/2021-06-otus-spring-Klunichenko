package ru.otus.spring.bookstore.exceptions;

public class BookStoreException extends RuntimeException {

    public BookStoreException(String message , Object... args){
        super(String.format(message, args));
    }

}
