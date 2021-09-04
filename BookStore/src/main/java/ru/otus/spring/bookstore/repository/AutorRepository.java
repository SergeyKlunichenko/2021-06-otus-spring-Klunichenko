package ru.otus.spring.bookstore.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.bookstore.model.Autor;

import java.util.List;

public interface AutorRepository extends MongoRepository <Autor, String>{
    List<Autor> findAll();

    Autor findByName(String name);

    void deleteByName(String name);
    void deleteById(String id);

}
