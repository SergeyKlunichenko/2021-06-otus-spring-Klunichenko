package ru.otus.spring.bookstore.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.bookstore.dto.BookDto;
import ru.otus.spring.bookstore.models.*;
import ru.otus.spring.bookstore.repositories.*;
import ru.otus.spring.bookstore.tools.IOService;

import java.util.List;


@Service
public class BookStoreService {

    private final BookDtoService bookDtoService;
    private final BookRepository bookRepository;
    private final AutorRepository autorRepository;
    private final GenreRepository genreRepository;
    private final NoteRepository noteRepository;
    private final IOService ioService;



    public BookStoreService(BookRepository bookRepository, BookDtoService bookDtoService, AutorRepository autorRepository, GenreRepository genreRepository, NoteRepository noteRepository, IOService ioService) {
        this.bookRepository = bookRepository;
        this.bookDtoService = bookDtoService;
        this.autorRepository = autorRepository;
        this.genreRepository = genreRepository;
        this.noteRepository = noteRepository;
        this.ioService = ioService;
    }

    //**************************************************************//
    //************************* Книги    ***************************//
    @Transactional(readOnly = true)
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Book findBookById(long id){
        return bookRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Book> findBookByName(String name){
        return bookRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public List<Book> findBookByGenreName(String name){
    //    return bookRepository.findByName(name);
        return bookRepository.findByGenre_Name(name);
    }


    @Transactional
    public Book addBook(String name, String genrename, String autorname) {
        Genre genre = genreRepository.findByName(genrename).get(0);
        Autor autor = autorRepository.findByName(autorname).get(0);
        Book book = new Book(0, name, autor, genre);
        book = bookRepository.save(book);
        return book;
    }

    @Transactional
    public Book updateBook(long id, String name, String genrename, String autorname){
        Book book = bookRepository.findById(id);
        Genre genre = genreRepository.findByName(genrename).get(0);
        Autor autor = autorRepository.findByName(autorname).get(0);
        book.setAutor(autor);
        book.setGenre(genre);
        book.setName(name);
        book = bookRepository.saveAndFlush(book);
        return book;

    }


    @Transactional
    public void deleteBookById(long id){
        noteRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Note> getNotesBookById(long id){
        return bookDtoService.getNotesBookById(id);
    }

    @Transactional
    public Note addNoteToBookById(long id, String value){
        BookDto bookDto = bookDtoService.findById(id);//new BookDto(bookDtoRepository.findById(id), noteRepository);
        return bookDtoService.addNote(bookDto, value);
    }

    @Transactional
    public void deleteNoteById(long id){
        noteRepository.deleteById(id);
    }

    //**************************************************************//
    //*************************   Автор  ***************************//
    @Transactional(readOnly = true)
    public List<Autor> getAllAutors(){
        return autorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Autor findAutorById(long id){
        return autorRepository.findById(id);
    }

    @Transactional
    public Autor addAutor(String name){
        Autor  autor = new Autor(0, name);
        return autorRepository.save(autor);
    }

    @Transactional
    public Autor editAutor(long id, String name){
        Autor autor = autorRepository.findById(id);
        autor.setName(name);
        return autorRepository.save(autor);
    }

    @Transactional
    public void delAutorById(long id){
        autorRepository.deleteById(id);
    }

    //**************************************************************//
    //************************** Жанры  ****************************//
    @Transactional(readOnly = true)
    public List<Genre> getAllGenres(){
        return genreRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Genre findGenreById(long id){
        return genreRepository.findById(id);
    }

    @Transactional
    public Genre addGenre(String name){
        Genre  genre = new Genre(0, name);
        return genreRepository.save(genre);
    }

    @Transactional
    public Genre editGenre(long id, String name){
        Genre genre = genreRepository.findById(id);
        genre.setName(name);
        return genreRepository.save(genre);
    }

    @Transactional
    public void delGenreById(long id){
        genreRepository.deleteById(id);
    }

}
