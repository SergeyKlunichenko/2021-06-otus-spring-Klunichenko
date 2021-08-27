package ru.otus.spring.bookstore.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.spring.bookstore.dto.BookDto;
import ru.otus.spring.bookstore.mapper.MapperService;
import ru.otus.spring.bookstore.models.Book;
import ru.otus.spring.bookstore.models.Note;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Repository
public class BookDtoRepositoryImpl implements BookDtoRepository{
    private  final BookRepository bookRepository;
    private  final NoteRepository noteRepository;
    private  final MapperService   mapperService;
    public BookDtoRepositoryImpl(BookRepository bookRepository, NoteRepository noteRepository, MapperService   mapperService){
        this.bookRepository = bookRepository;
        this.noteRepository = noteRepository;
        this.mapperService = mapperService;
    }
    public BookDto findById(long id){
        return mapperService.mapToDto(bookRepository.findById(id), noteRepository.findAllForBook(id));
    }




}
