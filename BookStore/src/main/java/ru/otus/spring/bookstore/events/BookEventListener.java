package ru.otus.spring.bookstore.events;

import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.stereotype.Component;
import ru.otus.spring.bookstore.exceptions.BookStoreException;
import ru.otus.spring.bookstore.model.Book;
import ru.otus.spring.bookstore.repository.BookRepository;
import ru.otus.spring.bookstore.repository.NoteRepository;
@Component
@RequiredArgsConstructor
public class BookEventListener extends AbstractMongoEventListener<Book> {
    private  final NoteRepository noteRepository;
    private  final BookRepository bookRepository;

    public void onBeforeDelete(BeforeDeleteEvent<Book> event){
        super.onBeforeDelete(event);
        Document doc = event.getSource();
        String id =  doc.get("_id").toString();
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookStoreException("Книга по ид %s не найдена", id));
        noteRepository.deleteByBook(book);
    }




}
