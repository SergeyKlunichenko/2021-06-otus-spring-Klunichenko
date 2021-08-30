package ru.otus.spring.bookstore.dto;

import lombok.Data;
import ru.otus.spring.bookstore.models.Autor;
import ru.otus.spring.bookstore.models.Genre;
import ru.otus.spring.bookstore.models.Note;

import java.util.List;
@Data
public class BookDto {
    private long id;
    private String name;
    private Autor autor;
    private Genre genre;
    private List<Note>  notes;
}
