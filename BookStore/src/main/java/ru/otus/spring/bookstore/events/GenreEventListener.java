package ru.otus.spring.bookstore.events;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;
import ru.otus.spring.bookstore.model.Autor;
import ru.otus.spring.bookstore.model.Book;
import ru.otus.spring.bookstore.model.Genre;
import ru.otus.spring.bookstore.repository.AutorRepository;
import ru.otus.spring.bookstore.repository.BookRepository;
import ru.otus.spring.bookstore.repository.GenreRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GenreEventListener extends AbstractMongoEventListener<Genre> {
    private  final BookRepository bookRepository;
    private  final GenreRepository genreRepository;

    public void onBeforeDelete(BeforeDeleteEvent<Genre> event) {
        super.onBeforeDelete(event);
        var src = event.getSource();
        var id = src.get("_id").toString();
        Genre genre = genreRepository.findById(id).get();
        if(bookRepository.existsByGenre(genre))
            throw new Error("В магазине имеются книги с удаляемым жанром. Удалять нельзя!!!");
    }

}
