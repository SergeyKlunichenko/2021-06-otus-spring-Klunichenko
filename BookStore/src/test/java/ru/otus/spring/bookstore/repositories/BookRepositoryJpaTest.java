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
@Import({BookRepositoryJpa.class, GenreRepositoryJpa.class, AutorRepositoryJpa.class})
//@Import(GenreRepositoryJpa.class)
class BookRepositoryJpaTest {

    private static final long EXPECTED_NOTE_ID = 1;
    private static final String NEW_NOTE_TEXT = "Второй коммент к белой гвардии";
    public static final int NEW_NUMBER_OF_NOTES = 2;

    public static final String NEW_BOOK_NAME = "Мастер и Маргарита";
    public static final String NEW_BOOK_GENRE = "драма";
    public static final String NEW_BOOK_AUTOR = "Булгаков Михаил Афанасьевич";

    public static final String UPDATE_BOOK_NAME = "Мастер и Маргарита";
    public static final String UPDATE_BOOK_GENRE = "фантастика";
    public static final String UPDATE_BOOK_AUTOR = "Булгаков Михаил Афанасьевич";
    public static final long   UPDATE_BOOK_ID = 1;

    private static final long EXPECTED_BOOK_ID = 1;
    private static final int EXPECTED_COUNT_NOTE_OF_BOOK = 1;
    private static final int EXPECTED_NUMBER_OF_BOOKS = 2;

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
        List<Note> notes = new ArrayList<>();
        Book book = new Book(0, NEW_BOOK_NAME, autor, genre, notes);
        book  = bookRepositoryJpa.updateBook(book);
        Book  expectedBook = bookRepositoryJpa.findById(book.getId());

        assertThat(book).usingRecursiveComparison().isEqualTo(expectedBook);


    }

    @DisplayName("Книга должна быть изменена")
    @Test
    void updateBookTest(){
        Book book = bookRepositoryJpa.findById(UPDATE_BOOK_ID);
        Genre genre = genreRepository.findByName(UPDATE_BOOK_GENRE);
        Autor autor = autorRepository.findByName(UPDATE_BOOK_AUTOR);

        System.out.printf("\n\n\n\0 book.name=%s\n", book.getName());


        book.setGenre(genre);
        book.setAutor(autor);
        book.setName(UPDATE_BOOK_NAME);

        System.out.printf("1 book.name=%s\n", book.getName());

        Book updatedBook = bookRepositoryJpa.updateBook(book);

        System.out.printf("2 book.name=%s\n", book.getName());
        System.out.printf("2 updatedBook.name=%s\n\n\n\n\n",updatedBook.getName());


        assertThat(book).usingRecursiveComparison().isEqualTo(updatedBook);


    }


    @DisplayName("Количество примечаний должно быть 2 штуки")
    @Test
    void addNoteToBookByIdTest() {
        bookRepositoryJpa.addNoteToBookById(EXPECTED_BOOK_ID, NEW_NOTE_TEXT);
        Book book = bookRepositoryJpa.findById(EXPECTED_BOOK_ID);
        assertThat(book.getNotes().size()).isEqualTo(NEW_NUMBER_OF_NOTES);

    }



    @DisplayName("После удаления книги книги не должно быть в базе")
    @Test
    void deleteBookById() {
        //bookRepositoryJpa.deleteBookById(EXPECTED_BOOK_ID);
        //Book boo = bookRepositoryJpa.findById(EXPECTED_BOOK_ID);
        assertThatCode(()->bookRepositoryJpa.findById(EXPECTED_BOOK_ID)).doesNotThrowAnyException();
        bookRepositoryJpa.deleteBookById(EXPECTED_BOOK_ID);
        assertThatCode(()->bookRepositoryJpa.findById(EXPECTED_BOOK_ID)).withThreadDumpOnError();


    }
    @DisplayName("после удаления примечания список примечаний должен стать пустым")
    @Test
    void deleteNoteFromBookById() {
        bookRepositoryJpa.deleteNoteFromBookById(EXPECTED_NOTE_ID);
        Book book =  bookRepositoryJpa.findById(EXPECTED_BOOK_ID);
        assertThat(book.getNotes().size()).isEqualTo(0);

    }
}