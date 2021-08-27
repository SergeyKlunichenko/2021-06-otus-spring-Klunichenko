package ru.otus.spring.bookstore.mapper;

import org.springframework.stereotype.Service;
import ru.otus.spring.bookstore.dto.BookDto;
import ru.otus.spring.bookstore.models.Book;
import ru.otus.spring.bookstore.models.Note;
import ru.otus.spring.bookstore.repositories.NoteRepository;

import java.util.ArrayList;
import java.util.List;
@Service
public class MapperService {
    public BookDto mapToDto(Book book, List<Note> notes){
        BookDto bookDto = new BookDto();
        bookDto.setName(book.getName());
        bookDto.setId(book.getId());
        bookDto.setGenre(book.getGenre());
        bookDto.setAutor(book.getAutor());
        bookDto.setNotes(notes);
        return  bookDto;
    }


}
