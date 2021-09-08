package ru.otus.spring.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "notes")
public class Note {
    @Id
    private String id;
    private String text;
    private Book    book;


    public Note(String text, Book book) {
        this.text = text;
        this.book = book;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", book=" + book +
                '}';
    }
}
