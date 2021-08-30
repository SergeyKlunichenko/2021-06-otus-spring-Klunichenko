package ru.otus.spring.bookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.spring.bookstore.models.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("Select b from Book b join fetch b.genre join fetch b.autor")
    List<Book> findAll();

    @Query("Select b from Book b join fetch b.genre join fetch b.autor where b.id = :id")
    Book findById(@Param("id") long id);

    @Query("Select b from Book b join fetch b.genre join fetch b.autor where b.name = :name")
    List<Book> findByName(@Param("name") String name);
    Book save(Book book);
    void deleteById(long id);


    @Query("Select b from Book b join fetch b.genre join fetch b.autor where b.genre.name = :name")
    List<Book> findByGenre_Name(@Param("name") String name);


    @Query("Select b from Book b join fetch b.genre join fetch b.autor where b.autor.name = :name")
    List<Book> findByAutor_Name(@Param("name") String name);

    @Query("select b from Book b join fetch b.genre join fetch b.autor where b.autor.name = :autor and b.genre.name = :genre")
    List<Book> findByAutorAndGenre(@Param("autor") String nameAutor, @Param("genre") String nameGenre);

    //void deteteNoteById(long id);

}
