package ru.otus.spring.bookstore.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.bookstore.domain.Autor;
import ru.otus.spring.bookstore.domain.Book;
import ru.otus.spring.bookstore.domain.Genre;

public interface BookRepository extends ReactiveMongoRepository<Book, String > {
    Flux<Book> findByName(String name);
}
