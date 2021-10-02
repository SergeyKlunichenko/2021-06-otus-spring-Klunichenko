package ru.otus.spring.bookstore.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.bookstore.domain.Autor;

public interface AutorRepository extends ReactiveMongoRepository<Autor, String> {
    Mono<Autor> findByName(String  name);
}
