package ru.otus.spring.bookstore.repositories;

import ru.otus.spring.bookstore.models.Genre;

import java.util.List;

public interface GenreRepository {
    List<Genre> findAll();
    Genre findById(long id);
    Genre findByName(String name);
    Genre save(Genre genre);
    void deleteById(long id);
}
