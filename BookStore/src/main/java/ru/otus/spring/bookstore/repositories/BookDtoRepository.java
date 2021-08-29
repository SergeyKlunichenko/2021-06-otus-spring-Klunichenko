package ru.otus.spring.bookstore.repositories;

import ru.otus.spring.bookstore.dto.BookDto;
import ru.otus.spring.bookstore.models.Note;

public interface BookDtoRepository {
    BookDto findById(long id);
    Note    addNote(BookDto bookDto, String value);
    void deteleNoteById(BookDto bookDto, long noteid);
}
