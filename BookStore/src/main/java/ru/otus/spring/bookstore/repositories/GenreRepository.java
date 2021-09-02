package ru.otus.spring.bookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.bookstore.models.Genre;

import java.util.List;


public interface GenreRepository extends JpaRepository<Genre, Long> {
    List<Genre> findAll();
    Genre findById(long id);
    List<Genre> findByName(String name);
    Genre save(Genre autor);
    void deleteById(long id);
}
