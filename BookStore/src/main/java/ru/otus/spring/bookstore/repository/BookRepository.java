package ru.otus.spring.bookstore.repository;

import com.mongodb.internal.operation.Operations;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.spring.bookstore.model.Autor;
import ru.otus.spring.bookstore.model.Book;
import ru.otus.spring.bookstore.model.Genre;

import java.util.List;
import java.util.Optional;


public interface BookRepository extends MongoRepository<Book, String> {

    List<Book> findAll();



    @Query("{'id' : :#{#id}}")
    Book  findBookById(String id);


    @Query("{'name': :#{#name}}")
    List<Book> findByName(@Param("name") String name);
    @Query("{'genre.name'::#{#name}}")
    List<Book> findByGenre(@Param("name") String name);
    List<Book> findByGenre(Genre genre);
    boolean existsByGenre(Genre genre);



    @Query("{'autor.name'::#{#name}}")
    List<Book> findByAutor(@Param("name") String name);
    List<Book> findByAutor(Autor autor);
    boolean existsByAutor(Autor autor);


    void deleteById(String id);
    void deleteByName(String name);

}
