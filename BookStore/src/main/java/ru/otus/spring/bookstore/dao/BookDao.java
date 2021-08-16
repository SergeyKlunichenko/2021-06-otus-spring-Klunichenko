package ru.otus.spring.bookstore.dao;

import ru.otus.spring.bookstore.domain.Book;
import ru.otus.spring.bookstore.exceptions.BookStoreException;

import java.util.List;

public interface BookDao {
    int  count();
    List <Book>getAll();
    Book findById(long id) throws BookStoreException;
    long insert(Book book);
    void deleteById(long id);
    void deleteByName(String name);

}
