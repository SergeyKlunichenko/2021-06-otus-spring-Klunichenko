package ru.otus.spring.bookstore.exceptions;

public class BookStoreException extends Exception {
    public BookStoreException(){
        super();
    }

    public BookStoreException(String message , Object... args){
        super(String.format(message, args));
    }


}
