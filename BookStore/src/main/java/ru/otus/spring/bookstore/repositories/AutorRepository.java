package ru.otus.spring.bookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.bookstore.models.Autor;

import java.util.List;

public interface AutorRepository extends JpaRepository <Autor, Long> {
    List<Autor> findAll();
    Autor findById(long id);
    Autor findByName(String name);
    Autor save(Autor autor);
    void deleteById(long id);
    //void delete(Autor autor);
}
