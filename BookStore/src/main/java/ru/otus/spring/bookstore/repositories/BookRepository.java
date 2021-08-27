package ru.otus.spring.bookstore.repositories;

import ru.otus.spring.bookstore.models.Book;
import ru.otus.spring.bookstore.models.Note;

import java.util.List;

public interface BookRepository {
    List<Book> findAll();
    Book findById(long id);
    Book save(Book book);
    void deleteBookById(long id);
    List<Note> getNotesByBookId(long id);
    Note addNoteByBookId(long id, String value);
}
