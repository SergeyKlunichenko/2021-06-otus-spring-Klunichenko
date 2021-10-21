package ru.otus.spring.bookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.bookstore.models.Autor;

import java.util.List;

public interface AutorRepository extends JpaRepository <Autor, Long> {
    Autor findByName(String name);
    Autor findById(long id);
}
