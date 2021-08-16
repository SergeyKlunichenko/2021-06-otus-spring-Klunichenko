package ru.otus.spring.bookstore.dao;

import ru.otus.spring.bookstore.domain.Autor;
import ru.otus.spring.bookstore.domain.Book;
import ru.otus.spring.bookstore.exceptions.BookStoreException;

import java.util.List;

public interface AutorDao {
    int  count();
    List<Autor> getAll();
    Autor findById(long id) throws BookStoreException;;
    Autor findByName(String name) throws BookStoreException;
    long insert(Autor book);
    void deleteById(long id);
}
