package ru.otus.spring.bookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.spring.bookstore.models.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAll();
    Book findById(long id);
    List<Book> findByName(String name);
    Book save(Book book);
    void deleteById(long id);
    List<Book> findByGenre_Name(String name);
    List<Book> findByAutor_Name(String name);
    @Query("select b from Book b where b.autor.name = :autor and b.genre.name = :genre")
    List<Book> findByAutorAndGenre(@Param("autor") String nameAutor, @Param("genre") String nameGenre);

    void deteteNoteById(long id);

}
