package ru.otus.spring.bookstore.services;

import org.springframework.stereotype.Service;
import ru.otus.spring.bookstore.dto.BookDto;
import ru.otus.spring.bookstore.models.*;
import ru.otus.spring.bookstore.repositories.*;
import ru.otus.spring.bookstore.tools.IOService;

import java.util.List;


@Service
public class BookStoreService {

    private final BookDtoRepository bookDtoRepository;
    private final BookRepository bookRepository;
    private final AutorRepository autorRepository;
    private final GenreRepository genreRepository;
    private final NoteRepository noteRepository;
    private final IOService ioService;



    public BookStoreService(BookRepository bookRepository, BookDtoRepositoryImpl bookDtoRepository, AutorRepository autorRepository, GenreRepository genreRepository, NoteRepository noteRepository, IOService ioService) {
        this.bookRepository = bookRepository;
        this.bookDtoRepository = bookDtoRepository;
        this.autorRepository = autorRepository;
        this.genreRepository = genreRepository;
        this.noteRepository = noteRepository;
        this.ioService = ioService;
    }

    //**************************************************************//
    //************************* Книги    ***************************//
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book findBookById(long id){
        return bookRepository.findById(id);
    }

    public List<Book> findBookByName(String name){
        return bookRepository.findByName(name);
    }


    public List<Note> getNotesBookById(long id){
        //BookDto book = new BookDto(bookRepository.findById(id), noteRepository);
        //BookDto book = MapperService.mapToDto(bookDtoRepository.findById(id), )
        //return book.getNotes();

        BookDto bookDto = bookDtoRepository.findById(id);
        return bookDto.getNotes();
    }

    public Book addBook(String name, String genrename, String autorname) {
        Genre genre = genreRepository.findByName(genrename).get(0);
        Autor autor = autorRepository.findByName(autorname).get(0);
        Book book = new Book(0, name, autor, genre);
        book = bookRepository.save(book);
        return book;
    }

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

    public Note addNoteToBookById(long id, String value){
        //BookDto book = bookDtoRepository.findById(id);//new BookDto(bookDtoRepository.findById(id), noteRepository);
        //return book.addNote(noteText);
        Note note = new Note(0, id, value);
        note = noteRepository.save(note);
        return note;
    }

    public void deleteBookById(long id){
        noteRepository.deleteById(id);
    }

    public void deleteNoteById(long id){
        noteRepository.deleteById(id);
    }

    //**************************************************************//
    //*************************   Автор  ***************************//
    public List<Autor> getAllAutors(){
        return autorRepository.findAll();
    }

    public Autor findAutorById(long id){
        return autorRepository.findById(id);
    }

    public Autor addAutor(String name){
        Autor  autor = new Autor(0, name);
        return autorRepository.save(autor);
    }

    public Autor editAutor(long id, String name){
        Autor autor = autorRepository.findById(id);
        autor.setName(name);
        return autorRepository.save(autor);
    }

    public void delAutorById(long id){
        autorRepository.deleteById(id);
    }

    //**************************************************************//
    //************************** Жанры  ****************************//
    public List<Genre> getAllGenres(){
        return genreRepository.findAll();
    }

    public Genre findGenreById(long id){
        return genreRepository.findById(id);
    }

    public Genre addGenre(){
        String name = ioService.readLine("Жанр:");
        Genre  genre = new Genre(0, name);
        return genreRepository.save(genre);
    }

    public Genre editGenre(long id){
        Genre genre = genreRepository.findById(id);
        String name = ioService.readLine("Изменить жанр \""+genre.getName()+"\":");
        genre.setName(name);
        return genreRepository.save(genre);
    }

    public void delGenreById(long id){
        genreRepository.deleteById(id);
    }

}
