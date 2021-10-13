package ru.otus.spring.bookstore.repositories;

import ru.otus.spring.bookstore.models.Book;
import ru.otus.spring.bookstore.models.Note;

import java.util.List;

public interface NoteRepository {
    Note findById(long id);
    List<Note> findAll();
    List<Note> findAllForBook(Book book) ;
     Note save(Note note) ;
     void deteteById(long id);

}
