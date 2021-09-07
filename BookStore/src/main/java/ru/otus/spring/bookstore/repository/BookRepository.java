package ru.otus.spring.bookstore.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.bookstore.model.Autor;
import ru.otus.spring.bookstore.model.Book;
import ru.otus.spring.bookstore.model.Genre;

import java.util.List;


public interface BookRepository extends MongoRepository<Book, String> {

    Book findByName(String name);
    List<Book> findByGenreName(String name);
    List<Book> findByGenre(Genre genre);
    boolean existsByGenre(Genre genre);
    List<Book> findByAutorName(String name);
    List<Book> findByAutor(Autor autor);
    boolean existsByAutor(Autor autor);
    void deleteByName(String name);

}
