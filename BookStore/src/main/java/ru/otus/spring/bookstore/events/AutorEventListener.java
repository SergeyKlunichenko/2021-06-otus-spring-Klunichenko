package ru.otus.spring.bookstore.events;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.stereotype.Component;
import ru.otus.spring.bookstore.exceptions.BookStoreException;
import ru.otus.spring.bookstore.model.Autor;
import ru.otus.spring.bookstore.repository.AutorRepository;
import ru.otus.spring.bookstore.repository.BookRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AutorEventListener extends AbstractMongoEventListener<Autor> {
    private  final BookRepository bookRepository;
    private  final AutorRepository autorRepository;


    public void onBeforeDelete(BeforeDeleteEvent<Autor> event)  {
        super.onBeforeDelete(event);
        var src = event.getSource();
        var id = src.get("_id").toString();
        Autor autor = autorRepository.findById(id).orElseThrow(() -> new BookStoreException("Автор по ид %s не найден", id));

        if(bookRepository.existsByAutor(autor))
            throw new BookStoreException("В магазине имеются книги автора. Удалять нельзя!!!");

    }


    public void onBeforeSave(BeforeSaveEvent<Autor> event){
        super.onBeforeSave(event);
        String id = event.getSource().getId();
        if(id == null){
            return;
        }
        Autor autor = autorRepository.findById(id).orElseThrow(()->new BookStoreException("Не найден автор с ид %s", id));
        if (bookRepository.existsByAutor(autor)){
            throw new BookStoreException("У автора %s в магазине найдены книги. Изменять запрещено!!!", autor.getName());
        }

    }

}
