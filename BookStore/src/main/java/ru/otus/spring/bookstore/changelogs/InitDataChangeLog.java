package ru.otus.spring.bookstore.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import ru.otus.spring.bookstore.model.Autor;
import ru.otus.spring.bookstore.model.Book;
import ru.otus.spring.bookstore.model.Genre;
import ru.otus.spring.bookstore.model.Note;
import ru.otus.spring.bookstore.repository.AutorRepository;
import ru.otus.spring.bookstore.repository.BookRepository;
import ru.otus.spring.bookstore.repository.GenreRepository;
import ru.otus.spring.bookstore.repository.NoteRepository;

import java.util.List;


@ChangeLog(order = "001")
public class InitDataChangeLog {
    @ChangeSet(order = "001", id="dropDB", author = "", runAlways = true)
    public void dropDB(MongoDatabase database){

        System.out.println("Drop Database");
        database.drop();
    }


    @ChangeSet(order  ="002", id = "autors", author = "")
    public void insertAutors(AutorRepository autorRepository){
        System.out.println("Load autors");
        autorRepository.save(new Autor("Булгаков Михаил Афанасьевич"));
        autorRepository.save(new Autor("Стругацкий Аркадий Натанович"));
    }

    @ChangeSet(order  ="003", id = "genres", author = "")
    public void insertGenres(GenreRepository genreRepository){
        System.out.println("Load genres");
        genreRepository.save(new Genre("драма"));
        genreRepository.save(new Genre("фантастика"));
    }

    @ChangeSet(order  ="004", id = "books", author = "")
    public void insertBooks(BookRepository bookRepository, GenreRepository genreRepository, AutorRepository autorRepository){
        System.out.println("Load books");
        Genre genre = genreRepository.findByName("фантастика");
        Autor autor = autorRepository.findByName("Стругацкий Аркадий Натанович");
        bookRepository.save(new Book("Пикник на обочине", genre, autor));
        bookRepository.save(new Book("Улитка на склоне", genre, autor));

        genre = genreRepository.findByName("драма");
        autor = autorRepository.findByName("Булгаков Михаил Афанасьевич");
        bookRepository.save(new Book("Мастер и Маргарита", genre, autor));
        bookRepository.save(new Book("Удалить", genre, autor));


    }


    @ChangeSet(order  ="005", id = "notes", author = "")
    public void insertNotes(NoteRepository noteRepository,BookRepository bookRepository) {
        System.out.println("Load notes");
        List<Book> books = bookRepository.findAll();
        int counter;
        String text;
        Note note;
        for(Book book:books){
            counter = 1;
            text = String.format("Примечание № %s для %s", counter++,  book.getName());
            note = new Note(text, book);
            noteRepository.save(note);

            text = String.format("Примечание № %s для %s", counter++,  book.getName());
            note = new Note(text, book);
            noteRepository.save(note);
        }

    }


}
