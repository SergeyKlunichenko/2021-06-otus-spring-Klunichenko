package ru.otus.spring.bookstore.repositories;

import ru.otus.spring.bookstore.models.Book;
import ru.otus.spring.bookstore.models.Note;

import java.util.List;

public interface BookRepository {
    List<Book> findAll();

    Book findById(long id);
    Book updateBook(Book book);
    Note addNoteToBookById(long id, String note);
    void deleteBookById(long id);
    void deleteNoteFromBookById(long id);
}
