package ru.otus.spring.bookstore.dto;

import lombok.Data;
import ru.otus.spring.bookstore.models.Autor;
import ru.otus.spring.bookstore.models.Book;
import ru.otus.spring.bookstore.models.Genre;
import ru.otus.spring.bookstore.models.Note;
import ru.otus.spring.bookstore.repositories.BookRepository;
import ru.otus.spring.bookstore.repositories.NoteRepository;

import java.util.List;
@Data
public class BookDto {
    private long id;
    private String name;
    private Autor autor;
    private Genre genre;
    private List<Note>  notes;

    //private NoteRepository noteRepository;
    /*
    public BookDto(Book book, NoteRepository noteRepository){
        this.noteRepository =  noteRepository;
        this.id  = book.getId();
        this.name = book.getName();
        this.autor = book.getAutor();
        this.genre = book.getGenre();
    }

    public List<Note> getNotes(){
        return noteRepository.findAllForBook(this.id);
    }

    public Note addNote(String value){
        Note note = new Note(0, this.id, value);
        return noteRepository.save(note);
    }

     */
}
