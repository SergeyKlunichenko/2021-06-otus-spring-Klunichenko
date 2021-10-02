package ru.otus.spring.bookstore.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import reactor.core.publisher.Mono;
import ru.otus.spring.bookstore.domain.Autor;
import ru.otus.spring.bookstore.domain.Genre;

import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "books")
public class Book {
    @Id
    private String id;
    private String name;
    private Genre genre;
    private Autor autor;

    public Book(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Book(String id, String name, Genre genre, Autor autor) {
        this.id   = id;
        this.name = name;
        this.genre = genre;
        this.autor = autor;
    }

    public Book(String name, Genre genre, Autor autor) {
        this.name = name;
        this.genre = genre;
        this.autor = autor;
    }

}
