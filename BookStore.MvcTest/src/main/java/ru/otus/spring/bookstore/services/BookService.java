package ru.otus.spring.bookstore.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.bookstore.models.Autor;
import ru.otus.spring.bookstore.models.Book;
import ru.otus.spring.bookstore.models.Genre;
import ru.otus.spring.bookstore.models.Note;
import ru.otus.spring.bookstore.repositories.AutorRepository;
import ru.otus.spring.bookstore.repositories.BookRepository;
import ru.otus.spring.bookstore.repositories.GenreRepository;
import ru.otus.spring.bookstore.repositories.NoteRepository;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AutorRepository autorRepository;
    private final GenreRepository genreRepository;
    private final NoteRepository noteRepository;

    public BookService(BookRepository bookRepository, AutorRepository autorRepository, GenreRepository genreRepository, NoteRepository noteRepository) {
        this.bookRepository = bookRepository;
        this.autorRepository = autorRepository;
        this.genreRepository = genreRepository;
        this.noteRepository = noteRepository;
    }

    //**************************************************************//
    //************************* Книги    ***************************//
    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Book findById(long id) {
        return bookRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Book> findByName(String name) {
        return bookRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public List<Book> findByGenreName(String name) {
        return bookRepository.findByGenre_Name(name);
    }

    @Transactional
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    public Book add(String name, String genrename, String autorname) {
        Genre genre = genreRepository.findByName(genrename);
        Autor autor = autorRepository.findByName(autorname);
        Book book = new Book(0, name, autor, genre);
        book = bookRepository.save(book);
        return book;
    }

    @Transactional
    public Book update(long id, String name, String genrename, String autorname) {
        Book book = bookRepository.findById(id);
        Genre genre = genreRepository.findByName(genrename);
        Autor autor = autorRepository.findByName(autorname);
        book.setAutor(autor);
        book.setGenre(genre);
        book.setName(name);
        book = bookRepository.saveAndFlush(book);
        return book;

    }

    @Transactional
    public void delete(Book book) {
        bookRepository.delete(book);
    }

    @Transactional
    public void deleteById(long id) {
        noteRepository.deleteById(id);
    }

    /*
    @Transactional(readOnly = true)
    public List<Note> findNotesByBookId(long id) {
        Book book = bookRepository.findById(id);
        return noteRepository.findAllForBook(book);
    }

    @Transactional(readOnly = true)
    public List<Note> findNotesByBook(Book book) {
        return noteRepository.findAllForBook(book);
    }


    @Transactional
    public Note addNoteToBookById(long id, String value) {
        Book book = bookRepository.findById(id);
        Note note = new Note(0, book, value);
        return noteRepository.save(note);
    }

    @Transactional
    public void deleteNoteById(long id) {
        noteRepository.deleteById(id);
    }

     */


}
