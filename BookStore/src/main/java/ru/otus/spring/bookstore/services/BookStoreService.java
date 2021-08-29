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
    private final BookRepository bookRepository;
    private final AutorRepository autorRepository;
    private  final GenreRepository genreRepository;
    private  final NoteRepository noteRepository;
    private  final BookDtoRepository bookDtoRepositroy;
    private final IOService ioService;

    public BookStoreService(BookRepository bookRepository, BookDtoRepository bookDtoRepositroy, AutorRepository autorRepository, GenreRepository genreRepository, NoteRepository noteRepository, IOService ioService) {
        this.bookRepository = bookRepository;
        this.bookDtoRepositroy = bookDtoRepositroy;
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

    @Transactional
    public Book addBook(String name, String genrename, String autorname) {
        Genre genre = genreRepository.findByName(genrename);
        Autor autor = autorRepository.findByName(autorname);
        Book book = new Book(0, name, autor, genre);
        bookRepository.save(book);
        return book;
    }

    @Transactional
    public Book updateBook(long id, String name, String genrename, String autorname){
        Book book = bookRepository.findById(id);
        Genre genre = genreRepository.findByName(genrename);
        Autor autor = autorRepository.findByName(autorname);
        book.setAutor(autor);
        book.setGenre(genre);
        book.setName(name);
        book = bookRepository.save(book);

        return book;
    }


    @Transactional
    public void deleteBookById(long id){
        bookRepository.deleteBookById(id);
    }

    @Transactional
    public Note addNoteByBookId(long id, String value){
        BookDto bookDto = bookDtoRepositroy.findById(id);
        return bookDtoRepositroy.addNote(bookDto, value);
    }


    @Transactional(readOnly = true)
    public List<Note> getNotesByBookId(long id){
        BookDto book = bookDtoRepositroy.findById(id);
        return book.getNotes();
    }

    public void deleteNoteById(long id){
        noteRepository.deteteById(id);
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
    public Autor addAutor(){
        String name = ioService.readLine("Автор:");
        Autor  autor = new Autor(0, name);
        return autorRepository.save(autor);
    }

    @Transactional
    public Autor editAutor(long id){
        Autor autor = autorRepository.findById(id);
        String name = ioService.readLine("Изменить автора \""+autor.getName()+"\":");
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
    public Genre addGenre(){
        String name = ioService.readLine("Жанр:");
        Genre  genre = new Genre(0, name);
        return genreRepository.save(genre);
    }

    @Transactional
    public Genre editGenre(long id){
        Genre genre = genreRepository.findById(id);
        String name = ioService.readLine("Изменить жанр \""+genre.getName()+"\":");
        genre.setName(name);
        return genreRepository.save(genre);
    }

    @Transactional
    public void delGenreById(long id){
        genreRepository.deleteById(id);
    }

}
