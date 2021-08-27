package ru.otus.spring.bookstore.repositories;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.bookstore.models.Autor;
import ru.otus.spring.bookstore.models.Book;
import ru.otus.spring.bookstore.models.Genre;
import ru.otus.spring.bookstore.models.Note;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("Репозитория для работы с книгами ")
@DataJpaTest
@Import({BookRepositoryJpa.class, GenreRepositoryJpa.class, AutorRepositoryJpa.class, NoteRepositoryJpa.class})
class BookRepositoryJpaTest {

    private static final long EXPECTED_NOTE_ID = 1;
    private static final String NEW_NOTE_TEXT = "Второй коммент к белой гвардии";
    private static final int NEW_NUMBER_OF_NOTES = 2;

    private static final String NEW_BOOK_NAME = "Мастер и Маргарита";
    private static final String NEW_BOOK_GENRE = "драма";
    private static final String NEW_BOOK_AUTOR = "Булгаков Михаил Афанасьевич";

    private static final String UPDATE_BOOK_NAME = "Мастер и Маргарита";
    private static final String UPDATE_BOOK_GENRE = "фантастика";
    private static final String UPDATE_BOOK_AUTOR = "Булгаков Михаил Афанасьевич";
    private static final long   UPDATE_BOOK_ID = 1;

    private static final long EXPECTED_BOOK_ID = 1;
    private static final int EXPECTED_NUMBER_OF_BOOKS = 2;

    @Autowired
    private NoteRepositoryJpa noteRepository;

    @Autowired
    private BookRepositoryJpa bookRepositoryJpa;

    @Autowired
    private GenreRepositoryJpa genreRepository;

    @Autowired
    private AutorRepositoryJpa autorRepository;


    @Autowired
    private TestEntityManager   em;

    @DisplayName("должен загрузить две книги")
    @Test
    void findAllTest() {
        val books = bookRepositoryJpa.findAll();
        assertThat(books.size()).isEqualTo(EXPECTED_NUMBER_OF_BOOKS);
    }

    @DisplayName("Должен найти книгу по id="+EXPECTED_BOOK_ID)
    @Test
    void findByIdTest() {
        val book = bookRepositoryJpa.findById(EXPECTED_BOOK_ID);
        val expectedBook = em.find(Book.class, EXPECTED_BOOK_ID);

        assertThat(book).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("Книга должна быть добавлена")
    @Test
    void addBookTest(){
        Genre genre = genreRepository.findByName(NEW_BOOK_GENRE);
        Autor autor = autorRepository.findByName(NEW_BOOK_AUTOR);
        Book book = new Book(0, NEW_BOOK_NAME, autor, genre);
        book  = bookRepositoryJpa.save(book);
        Book  expectedBook = bookRepositoryJpa.findById(book.getId());

        assertThat(book).usingRecursiveComparison().isEqualTo(expectedBook);


    }

    @DisplayName("Книга должна быть изменена")
    @Test
    void updateBookTest(){
        Book book = bookRepositoryJpa.findById(UPDATE_BOOK_ID);
        Genre genre = genreRepository.findByName(UPDATE_BOOK_GENRE);
        Autor autor = autorRepository.findByName(UPDATE_BOOK_AUTOR);

        book.setGenre(genre);
        book.setAutor(autor);
        book.setName(UPDATE_BOOK_NAME);

        Book updatedBook = bookRepositoryJpa.save(book);

        assertThat(book).usingRecursiveComparison().isEqualTo(updatedBook);
    }



    @DisplayName("После удаления книги книги не должно быть в базе")
    @Test
    void deleteBookById() {
        assertThatCode(()->bookRepositoryJpa.findById(EXPECTED_BOOK_ID)).doesNotThrowAnyException();
        bookRepositoryJpa.deleteBookById(EXPECTED_BOOK_ID);
        assertThatCode(()->bookRepositoryJpa.findById(EXPECTED_BOOK_ID)).withThreadDumpOnError();
    }


    @DisplayName("Количество примечаний должно быть 2 штуки")
    @Test
    void addNoteToBookByIdTest() {
          Note note = bookRepositoryJpa.addNoteByBookId(EXPECTED_BOOK_ID, NEW_NOTE_TEXT);
          assertThat(note).usingRecursiveComparison().isEqualTo(noteRepository.findById(note.getId()));
    }

    @DisplayName("после удаления примечания список примечаний должен стать пустым")
    @Test
    void deleteNoteFromBookById() {
        noteRepository.deteteById(EXPECTED_NOTE_ID);
        assertThat(bookRepositoryJpa.getNotesByBookId(EXPECTED_NOTE_ID).size()).isEqualTo(0);
    }
}