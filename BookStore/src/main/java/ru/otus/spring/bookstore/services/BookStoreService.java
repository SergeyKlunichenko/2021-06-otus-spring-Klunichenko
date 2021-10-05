package ru.otus.spring.bookstore.services;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.bookstore.models.*;
import ru.otus.spring.bookstore.repositories.*;

import java.util.List;


@Service
public class BookStoreService {

    private  BookRepository bookRepository;
    private  AutorRepository autorRepository;
    private  GenreRepository genreRepository;
    private  NoteRepository noteRepository;

    public BookStoreService(BookRepository bookRepository, AutorRepository autorRepository, GenreRepository genreRepository, NoteRepository noteRepository) {
        this.bookRepository = bookRepository;
        this.autorRepository = autorRepository;
        this.genreRepository = genreRepository;
        this.noteRepository = noteRepository;
    }

    //**************************************************************//
    //************************* Книги    ***************************//
    @Transactional(readOnly = true)
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Book findBookById(long id) {
        return bookRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Book> findBookByName(String name) {
        return bookRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public List<Book> findBookByGenreName(String name) {
        return bookRepository.findByGenre_Name(name);
    }

    @Transactional
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    public Book addBook(String name, String genrename, String autorname) {
        long id = 0;
        Genre genre = genreRepository.findByName(genrename);
        Autor autor = autorRepository.findByName(autorname);
        Book book = new Book(id, name, autor, genre);
        book = bookRepository.save(book);
        return book;
    }

    @Transactional
    public Book updateBook(long id, String name, String genrename, String autorname) {
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
    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }

    @Transactional
    public void deleteBookById(long id) {
        noteRepository.deleteById(id);
    }


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

    //**************************************************************//
    //*************************   Автор  ***************************//
    @Transactional(readOnly = true)
    public List<Autor> findAllAutors() {
        return autorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Autor findAutorById(long id) {
        return autorRepository.findById(id);
    }

    @Transactional
    public Autor addAutor(String name) {
        Autor autor = new Autor(0, name);
        return autorRepository.save(autor);
    }

    @Transactional
    public Autor editAutor(long id, String name) {
        Autor autor = autorRepository.findById(id);
        autor.setName(name);
        return autorRepository.save(autor);
    }

    @Transactional
    public Autor saveAutor(Autor autor) {
        return autorRepository.save(autor);
    }

    @Transactional
    public void deleteAutorById(long id) {
        autorRepository.deleteById(id);
    }

    @Transactional
    public void deleteAutor(Autor autor) {
        autorRepository.delete(autor);
    }


    //**************************************************************//
    //************************** Жанры  ****************************//
    @Transactional(readOnly = true)
    public List<Genre> findAllGenre() {
        return genreRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Genre findGenreById(long id) {
        return genreRepository.findById(id);
    }

    @Transactional
    public Genre addGenre(String name) {
        Genre genre = new Genre(0, name);
        return genreRepository.save(genre);
    }

    @Transactional
    public Genre saveGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    @Transactional
    public Genre editGenre(long id, String name) {
        Genre genre = genreRepository.findById(id);
        genre.setName(name);
        return genreRepository.save(genre);
    }

    @Transactional
    public void deleteGenreById(long id) {
        genreRepository.deleteById(id);
    }

    @Transactional
    public void deleteGenre(Genre genre) {
        genreRepository.delete(genre);
    }


}
