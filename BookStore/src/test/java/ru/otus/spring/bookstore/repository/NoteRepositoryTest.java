package ru.otus.spring.bookstore.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.otus.spring.bookstore.model.Book;
import ru.otus.spring.bookstore.model.Note;

import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;


@DataMongoTest
@DisplayName("Тестирование примечаниий")
class NoteRepositoryTest {
    private static final String EXPECTED_BOOK_NAME = "Пикник на обочине";
    private static final int EXPECTED_NOTE_COUNT = 2;

    private static final String OTHER_BOOK_NAME = "Мастер и Маргарита";
    private static final int OTHER_NOTE_COUNT = 2;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private NoteRepository noteRepository;

    @DisplayName("должен получить "+EXPECTED_NOTE_COUNT+ " примечания для книги "+ EXPECTED_BOOK_NAME)
    @Test
    void findAllByBook() {
        Book book = bookRepository.findByName(EXPECTED_BOOK_NAME);
        List<Note> notes = noteRepository.findAllByBook(book);
        assertThat(notes.size()).isEqualTo(EXPECTED_NOTE_COUNT);

    }

    @Test
    @DisplayName("должен удалить все примечания для "+OTHER_BOOK_NAME)
    void deleteByBook() {
        Book book = bookRepository.findByName(OTHER_BOOK_NAME);
        noteRepository.deleteByBook(book);
        assertThat(noteRepository.findAllByBook(book).size()).isEqualTo(0);


    }
}