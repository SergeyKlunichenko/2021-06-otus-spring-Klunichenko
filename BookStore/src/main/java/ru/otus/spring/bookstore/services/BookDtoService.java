package ru.otus.spring.bookstore.services;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.otus.spring.bookstore.dto.BookDto;
import ru.otus.spring.bookstore.models.Book;
import ru.otus.spring.bookstore.models.Note;
import ru.otus.spring.bookstore.repositories.BookRepository;
import ru.otus.spring.bookstore.repositories.NoteRepository;

import java.util.List;

@Service
public class BookDtoService  {
    private final BookRepository bookRepository;
    private final NoteRepository noteRepository;

    public BookDtoService(BookRepository bookRepository, NoteRepository noteRepository){
        this.bookRepository = bookRepository;
        this.noteRepository = noteRepository;
    }

    public BookDto findById(long id) {

        Book    book    =   bookRepository.findById(id);
        BookDto bookDto =   new BookDto();
        bookDto.setId(book.getId());
        bookDto.setName(book.getName());
        bookDto.setAutor(book.getAutor());
        bookDto.setGenre(book.getGenre());
        bookDto.setNotes(noteRepository.findAllForBook(book));

        return bookDto;
    }

    public List<Note> getNotesByBookId(long id){
        Book    book    =   bookRepository.findById(id);
        return  noteRepository.findAllForBook(book);
    }

    public Note addNote(BookDto bookDto, String noteValue) {
        Book book = bookRepository.findById(bookDto.getId());
        Note note = new Note(0, book, noteValue);
        note = noteRepository.save(note);

        return note;
    }

    public void deteleNoteById(BookDto bookDto, long noteId) {
        noteRepository.deteteById(noteId);
        Book book = bookRepository.findById(bookDto.getId());
        bookDto.setNotes(noteRepository.findAllForBook(book));
    }
}
