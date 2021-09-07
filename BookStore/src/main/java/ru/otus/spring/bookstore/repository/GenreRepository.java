package ru.otus.spring.bookstore.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.bookstore.model.Genre;

import java.util.List;

public interface GenreRepository extends MongoRepository<Genre, String> {
    Genre findByName(String name);
}
