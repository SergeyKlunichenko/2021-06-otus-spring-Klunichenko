package ru.otus.spring.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "books")
public class Book {
    @Id
    private String id;
    private String name;
    private Genre  genre;
    private Autor  autor;
    //private List<Note> notes;

    public Book(String name, Genre genre, Autor autor) {
        this.name = name;
        this.genre = genre;
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", genre=" + genre +
                ", autor=" + autor +
                '}';
    }
}
