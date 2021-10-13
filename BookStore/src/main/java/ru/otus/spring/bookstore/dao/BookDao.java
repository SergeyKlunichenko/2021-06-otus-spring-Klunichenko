package ru.otus.spring.bookstore.dao;

import ru.otus.spring.bookstore.domain.Book;
import ru.otus.spring.bookstore.exceptions.BookStoreException;

import java.util.List;

public interface BookDao {
    int  count();
    List <Book>getAll();
    Book findById(long id) throws BookStoreException;
    Book findByName(String name) throws BookStoreException;
    Book insert(Book book) throws BookStoreException;
    Book update(Book book) throws BookStoreException;
    void deleteById(long id) throws BookStoreException;
    void deleteByName(String name) throws BookStoreException;

}
