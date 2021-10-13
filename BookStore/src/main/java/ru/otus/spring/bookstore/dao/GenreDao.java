package ru.otus.spring.bookstore.dao;

import ru.otus.spring.bookstore.domain.Book;
import ru.otus.spring.bookstore.domain.Genre;
import ru.otus.spring.bookstore.exceptions.BookStoreException;

import java.util.List;

public interface GenreDao {
    int  count();
    List<Genre> getAll();
    Genre findById(long id) throws BookStoreException;;
    Genre findByName(String name) throws BookStoreException;
    long insert(Genre book);
    void deleteById(long id);
}
