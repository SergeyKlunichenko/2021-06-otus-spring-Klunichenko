package ru.otus.spring.bookstore.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.spring.bookstore.models.Autor;
import ru.otus.spring.bookstore.models.Book;
import ru.otus.spring.bookstore.models.Genre;
import ru.otus.spring.bookstore.models.Note;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("Репозитарий для работы с книгами")
@DataJpaTest
class BookRepositoryTest {

    private static final long   EXPECTED_BOOK_ID = 1;
    private static final long   SECOND_BOOK_ID = 2;
    private static final String SECOND_BOOK_NAME = "";
    private static final String EXPECTED_BOOK_NAME = "Белая гвардия";
    private static final String EXPECTED_GENRE_NAME = "драма";
    private static final String  EXPECTED_AUTOR_NAME = "Булгаков Михаил Афанасьевич";
    private static final int    EXPECTED_NOTE_COUNT = 1;

    private static final String NEW_BOOK_NAME = "Мастер и Маргарита";
    private static final String NEW_BOOK_GENRE = "драма";
    private static final String NEW_BOOK_AUTOR = "Булгаков Михаил Афанасьевич";

    private static final String NEW_BOOK_NOTE = "Второй комментарий для Белой гвардии";
    private static final int    COUNT_NOTE_AFTER_ADD_NOTE = 2;
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("должны загрузиться две книги "+EXPECTED_BOOK_NAME+ " и " + SECOND_BOOK_NAME)
    void findAllTest() {
        Book expectedBook =   bookRepository.findById(EXPECTED_BOOK_ID);
        Book secondBook     = bookRepository.findById(SECOND_BOOK_ID);

        List<Book> books = bookRepository.findAll();
        assertThat(books)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expectedBook, secondBook);
    }

    @Test
    @DisplayName("найдет книгу "+EXPECTED_BOOK_NAME + " по ид")
    void findByIdTest() {
        assertThatCode(()->bookRepository.findById(EXPECTED_BOOK_ID)).doesNotThrowAnyException();
        Book book = bookRepository.findById(EXPECTED_BOOK_ID);
        assertThat(book.getName()).isEqualTo(EXPECTED_BOOK_NAME);
    }

    @Test
    @DisplayName("найдет книгу по наименованию "+EXPECTED_BOOK_NAME)
    void findByNameTest() {
        Book expectedBook = bookRepository.findById(EXPECTED_BOOK_ID);
        List<Book> books = bookRepository.findByName(EXPECTED_BOOK_NAME);
        assertThat(books.get(0)).usingRecursiveComparison().isEqualTo(expectedBook);

    }

    @Test
    @DisplayName("добавит новую книгу "+ NEW_BOOK_NAME)
    void  addTest(){
        Genre genre = genreRepository.findByName(NEW_BOOK_GENRE);
        Autor autor = autorRepository.findByName(NEW_BOOK_AUTOR);
        Book  newBook  = new Book(0, NEW_BOOK_NAME,  autor, genre);
        Book  checkBook = new Book(0, NEW_BOOK_NAME,  autor, genre);
        newBook = bookRepository.saveAndFlush(newBook);
        checkBook.setId(newBook.getId());

        //newBook.setId(savedBook.getId());
        assertThat(checkBook).usingRecursiveComparison().isEqualTo(bookRepository.findById(checkBook.getId()));
    }


    @Test
    @DisplayName("изменить книгу на"+NEW_BOOK_NAME)
    void  editTest(){
        Genre genre =   genreRepository.findByName(NEW_BOOK_GENRE);
        Autor autor =   autorRepository.findByName(NEW_BOOK_AUTOR);
        Book  book  =   bookRepository.findById(EXPECTED_BOOK_ID);
        book.setName(NEW_BOOK_NAME);
        book.setAutor(autor);
        book.setGenre(genre);

        Book checkBook = new Book(book.getId(), book.getName(), book.getAutor(), book.getGenre());
        assertThat(checkBook).usingRecursiveComparison().isEqualTo(bookRepository.findById(checkBook.getId()));

    }



    @Test
    @DisplayName("удалит книгу с ид="+EXPECTED_BOOK_ID)
    void deleteByIdTest(){
        assertThatCode(()->bookRepository.findById(EXPECTED_BOOK_ID)).doesNotThrowAnyException();
        bookRepository.deleteById(EXPECTED_BOOK_ID);
        assertThatCode(()->bookRepository.findById(EXPECTED_BOOK_ID)).withThreadDumpOnError();


    }

    @Test
    @DisplayName("найдет книгу "+EXPECTED_BOOK_NAME+" по жанру "+EXPECTED_GENRE_NAME)
    void findByGenreTest(){
        List <Book> books = bookRepository.findByGenre_Name(EXPECTED_GENRE_NAME);
        Book expectedBook = bookRepository.findById(EXPECTED_BOOK_ID);
        assertThat(books)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expectedBook);
    }

    @Test
    @DisplayName("найдет книгу "+EXPECTED_BOOK_NAME+" по автору "+EXPECTED_AUTOR_NAME)
    void findByAutorTest(){
        List <Book> books = bookRepository.findByAutor_Name(EXPECTED_AUTOR_NAME);
        Book expectedBook = bookRepository.findById(EXPECTED_BOOK_ID);
        assertThat(books)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expectedBook);
    }

    @Test
    @DisplayName("найдет книгу "+EXPECTED_BOOK_NAME + " по автору "+ EXPECTED_AUTOR_NAME + " и жанру "+ EXPECTED_GENRE_NAME)
    void findByAutorAndGenre(){
        List<Book> books = bookRepository.findByAutorAndGenre(EXPECTED_AUTOR_NAME, EXPECTED_GENRE_NAME);
        Book expectedBook = bookRepository.findById(EXPECTED_BOOK_ID);

        assertThat(books)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expectedBook);

    }

    @Test
    @DisplayName("получить один комментарий")
    void getNotesBookByIdTest(){
        Book book = bookRepository.findById(EXPECTED_BOOK_ID);
        List<Note> notes = noteRepository.findAllForBook(book);
        assertThat(notes.size()).isEqualTo(EXPECTED_NOTE_COUNT);
    }

    @Test
    @DisplayName("добавит  второй комментари в  книгу ")
    void addNoteToBook(){
        Book book = bookRepository.findById(EXPECTED_BOOK_ID);
        Note note = new Note(0, book, NEW_BOOK_NOTE);
        note = noteRepository.save(note);
        List<Note> notes = noteRepository.findAllForBook(book);//new BookDto(bookRepository.findById(EXPECTED_BOOK_ID), noteRepository);


        assertThat(notes.size()).isEqualTo(COUNT_NOTE_AFTER_ADD_NOTE);


    }

}