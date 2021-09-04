package ru.otus.spring.bookstore.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.bookstore.model.Book;
import ru.otus.spring.bookstore.model.Note;

import java.util.List;

public interface NoteRepository extends MongoRepository <Note, String> {
    List<Note> findAllByBook(Book book);

    void deleteByBook(Book book);
}
