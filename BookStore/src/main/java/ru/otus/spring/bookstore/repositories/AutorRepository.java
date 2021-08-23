package ru.otus.spring.bookstore.repositories;

import ru.otus.spring.bookstore.models.Autor;

import java.util.List;
import java.util.Optional;

public interface AutorRepository {
    List<Autor> findAll();
    Autor findById(long id);
    Autor findByName(String name);
    Autor save(Autor autor);
    void deleteById(long id);
}
