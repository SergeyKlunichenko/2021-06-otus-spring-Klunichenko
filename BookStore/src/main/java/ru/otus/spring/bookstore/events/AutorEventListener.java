package ru.otus.spring.bookstore.events;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;
import ru.otus.spring.bookstore.model.Autor;
import ru.otus.spring.bookstore.model.Book;
import ru.otus.spring.bookstore.repository.AutorRepository;
import ru.otus.spring.bookstore.repository.BookRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AutorEventListener extends AbstractMongoEventListener<Autor> {
    private  final BookRepository bookRepository;
    private  final AutorRepository autorRepository;


    public void onBeforeDelete(BeforeDeleteEvent<Autor> event) {
        super.onBeforeDelete(event);
        var src = event.getSource();
        var id = src.get("_id").toString();
        Autor autor = autorRepository.findById(id).get();
        if(bookRepository.existsByAutor(autor))
            throw new Error("В магазине имеются книги автора. Удалять нельзя!!!");

    }

}
