package ru.otus.spring.bookstore.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.spring.bookstore.dto.BookDto;
import ru.otus.spring.bookstore.models.Book;
import ru.otus.spring.bookstore.models.Note;

@Repository
public class BookDtoRepositoryImpl implements BookDtoRepository {

    private final BookRepository bookRepository;
    private final NoteRepository noteRepository;

    public BookDtoRepositoryImpl(BookRepository bookRepository, NoteRepository noteRepository){
        this.bookRepository = bookRepository;
        this.noteRepository = noteRepository;
    }
    @Override
    public BookDto findById(long id) {

        Book    book    =   bookRepository.findById(id);
        BookDto bookDto =   new BookDto();
        bookDto.setId(book.getId());
        bookDto.setName(book.getName());
        bookDto.setAutor(book.getAutor());
        bookDto.setGenre(book.getGenre());
        bookDto.setNotes(noteRepository.findAllByBookId(bookDto.getId()));

        return bookDto;
    }

    @Override
    public Note addNote(BookDto bookDto, String noteValue) {
        Note note = new Note(0, bookDto.getId(), noteValue);
        note = noteRepository.save(note);

        return note;
    }

    @Override
    public void deteleNoteById(BookDto bookDto, long noteId) {
        noteRepository.deteteById(noteId);
        bookDto.setNotes(noteRepository.findAllByBookId(bookDto.getId()));
    }
}
