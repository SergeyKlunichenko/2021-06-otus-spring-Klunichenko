package ru.otus.spring.bookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.bookstore.models.Genre;

import java.util.List;


public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre findById(long id);
    Genre findByName(String name);

//    List<Genre> findAll();
//    Genre save(Genre autor);
//    void deleteById(long id);
}
