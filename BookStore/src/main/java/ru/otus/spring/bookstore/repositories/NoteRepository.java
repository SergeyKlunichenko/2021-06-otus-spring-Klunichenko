package ru.otus.spring.bookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.spring.bookstore.models.Book;
import ru.otus.spring.bookstore.models.Note;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    Note findById(long id);
    @Query("select n from Note n where book = :book")
    List<Note> findAllForBook(@Param("book") Book book);
    Note save(Note note);
    void deleteById(long id);
}
