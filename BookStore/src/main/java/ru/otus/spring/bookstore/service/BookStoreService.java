package ru.otus.spring.bookstore.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.bookstore.exceptions.BookStoreException;
import ru.otus.spring.bookstore.model.Autor;
import ru.otus.spring.bookstore.model.Book;
import ru.otus.spring.bookstore.model.Genre;
import ru.otus.spring.bookstore.model.Note;
import ru.otus.spring.bookstore.repository.AutorRepository;
import ru.otus.spring.bookstore.repository.BookRepository;
import ru.otus.spring.bookstore.repository.GenreRepository;
import ru.otus.spring.bookstore.repository.NoteRepository;

import java.util.List;

@Service
public class BookStoreService {
    private final BookRepository bookRepository;
    private final AutorRepository autorRepository;
    private final GenreRepository genreRepository;
    private final NoteRepository noteRepository;

    public BookStoreService(BookRepository bookRepository, AutorRepository autorRepository, GenreRepository genreRepository, NoteRepository noteRepository) {
        this.bookRepository = bookRepository;
        this.autorRepository = autorRepository;
        this.genreRepository = genreRepository;
        this.noteRepository = noteRepository;
    }

    //**************************************************************//
    //************************* Книги    ***************************//
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book findBookByName(String name){
        return bookRepository.findByName(name);
    }

    public List<Book> findBookByGenre(String name){
        return bookRepository.findByGenreName(name);
    }

    public List<Book> findBookByGenreId(String id){
        Genre genre = genreRepository.findById(id).get();
        return bookRepository.findByGenre(genre);
    }

    public List<Book> findBookByAutor(String name){
        return bookRepository.findByAutorName(name);
    }

    public List<Book> findBookByAutorId(String id){
        Autor autor = autorRepository.findById(id).get();
        return bookRepository.findByAutor(autor);
    }

    public Book addBook(String name, String genrename, String autorname) {
        Genre genre = genreRepository.findByName(genrename);
        Autor autor = autorRepository.findByName(autorname);
        Book book = new Book(name, genre, autor);
        book = bookRepository.save(book);
        return book;
    }

    public Book updateBook(String id, String name,  String genrename, String autorname){
        Autor autor = autorRepository.findByName(autorname);
        Genre genre = genreRepository.findByName(genrename);
        Book book = bookRepository.findById(id).get();
        book.setName(name);
        book.setAutor(autor);
        book.setGenre(genre);
        return  bookRepository.save(book);

    }
    public void deleteBookByName(String name){
        bookRepository.deleteByName(name);
    }

    public void deleteBookById(String id){
        bookRepository.deleteById(id);
    }

    //**************************************************************//
    //************************* Примечания    ***************************//
    public List<Note> findAllNotes(){
        return noteRepository.findAll();
    }

    public List<Note> findNotesByBook(String id){
        Book book = bookRepository.findById(id).get();
        return noteRepository.findAllByBook(book);
    }

    public Note addNoteByBook(String id, String value){
        Book book = bookRepository.findById(id).get();
        Note note = new Note(value, book);
        return noteRepository.save(note);
    }

    public void deleteNoteById(String id){
        noteRepository.deleteById(id);
    }


    //*************************************************************************//
    //****************************   Автор  ***********************************//
    public List<Autor> getAutors(){
        return autorRepository.findAll();
    }

    public Autor findAutorById(String id){
        return autorRepository.findById(id).get();
    }

    public Autor addAutor(String name){
        Autor autor = new Autor(name);
        return autorRepository.save(autor);
    }

    public Autor editAutor(String id, String name){
        Autor autor = autorRepository.findById(id).orElseThrow(() -> new BookStoreException("Автор по ид %s не найден", id));
        autor.setName(name);
        return autorRepository.save(autor);
    }

    public String  deleteAutorById(String id){
        try {
            autorRepository.deleteById(id);
        } catch(Error e){
            //System.out.println(e.getMessage());
            return e.getMessage();
        }
        return "OK";
    }

    //************************************************************************//
    //****************************   Жанр  ***********************************//
    public List<Genre> getAllGenres(){
        return genreRepository.findAll();
    }

    public Genre findGenreById(String id){
        return genreRepository.findById(id).orElseThrow(()-> new BookStoreException("Жанр по ид %s не найден", id));
    }

    public Genre addGenre(String name){
        Genre genre = new Genre(name);
        return genreRepository.save(genre);
    }
    public String deleteGenreById(String id){
        try {
            genreRepository.deleteById(id);
        } catch(Error e){
            return e.getMessage();
        }

        return "OK";
    }

    public Genre editGenreById(String id, String name){
        Genre genre = genreRepository.findById(id).orElseThrow(()-> new BookStoreException("Жанр по ид %s не найден", id));
        genre.setName(name);
        return genreRepository.save(genre);
    }


}
