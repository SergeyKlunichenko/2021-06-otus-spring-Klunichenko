package ru.otus.spring.bookstore.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.otus.spring.bookstore.exceptions.BookStoreException;
import ru.otus.spring.bookstore.model.Autor;
import ru.otus.spring.bookstore.model.Book;
import ru.otus.spring.bookstore.model.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DataMongoTest
@DisplayName("Тестирование репозитория книг")
class BookRepositoryTest {

    private static final int EXPECTED_COUNT_BOOK = 4;
    private static final String EXPECTED_BOOK_NAME = "Пикник на обочине";
    private static final String EXPECTED_GENRE_NAME = "фантастика";
    private static final int    EXPECTED_GENRE_BOOK_COUNT = 2;
    private static final int    EXPECTED_AUTOR_BOOK_COUNT = 2;

    private static final String EXPECTED_AUTOR_NAME ="Булгаков Михаил Афанасьевич";
    private static final String NEW_BOOK_NAME = "Белая гвардия";
    private static final String NEW_GENRE_NAME = "драма";
    private static final String NEW_AUTOR_NAME = "Булгаков Михаил Афанасьевич";


    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    @DisplayName("получит список книг из "+EXPECTED_COUNT_BOOK + " книг")
    void findAll() {
        List<Book> books = bookRepository.findAll();
        assertThat(books.size()).isEqualTo(EXPECTED_COUNT_BOOK);
    }


    @Test
    @DisplayName("найдет книгу "+EXPECTED_BOOK_NAME)
    void FindByName() {
        Book book = bookRepository.findByName(EXPECTED_BOOK_NAME);
        assertThat(book.getName()).isEqualTo(EXPECTED_BOOK_NAME);
    }


    @Test
    @DisplayName("Удалит книгу "+EXPECTED_BOOK_NAME)
    void DeleteByName() {
        Book book = bookRepository.findByName(EXPECTED_BOOK_NAME);
        String id = book.getId();
        bookRepository.delete(book);
        assertThatCode(()->bookRepository.findById(id).orElseThrow(()->new BookStoreException("Книга по ид %s не найдена", id))).hasNoSuppressedExceptions();
        bookRepository.save(book);

    }

    @Test
    @DisplayName("добавит новую книгу")
    void AddBook(){
        Autor autor = autorRepository.findByName(NEW_AUTOR_NAME);
        Genre genre = genreRepository.findByName(NEW_GENRE_NAME);
        Book  book  = new Book(NEW_BOOK_NAME, genre, autor);
        book = bookRepository.save(book);
        String id = book.getId();
        assertThat(book).usingRecursiveComparison().isEqualTo(bookRepository.findById(id).orElseThrow(() -> new BookStoreException("Книга по ид %s не найдена", id)));
        bookRepository.delete(book);
    }


    @Test
    @DisplayName("Получит список книг по наименованию жанра ")
    void findBookByGenreName(){
        List<Book> books = bookRepository.findByGenreName(EXPECTED_GENRE_NAME);
        assertThat(books.size()).isEqualTo(2);
    }
    @Test
    @DisplayName("Получит список книг по жанру ")
    void findBookByGenre(){
        Genre genre =genreRepository.findByName(EXPECTED_GENRE_NAME);
        List<Book> books = bookRepository.findByGenre(genre);
        assertThat(books.size()).isEqualTo(EXPECTED_GENRE_BOOK_COUNT);
    }

    @Test
    @DisplayName("Получит список книг по имени автора")
    void findBookByAutorName(){
        List<Book> books = bookRepository.findByAutorName(EXPECTED_AUTOR_NAME);
        assertThat(books.size()).isEqualTo(EXPECTED_AUTOR_BOOK_COUNT);
    }
    @Test
    @DisplayName("Получит список книг по автору")
    void findBookByAutor(){
        Autor autor =autorRepository.findByName(EXPECTED_AUTOR_NAME);
        List<Book> books = bookRepository.findByAutor(autor);
        assertThat(books.size()).isEqualTo(EXPECTED_AUTOR_BOOK_COUNT);
    }

}