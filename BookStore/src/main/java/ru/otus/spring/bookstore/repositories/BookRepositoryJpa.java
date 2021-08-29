package ru.otus.spring.bookstore.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.bookstore.dto.BookDto;
import ru.otus.spring.bookstore.models.Book;
import ru.otus.spring.bookstore.models.Note;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryJpa implements BookRepository{
    @PersistenceContext
    private final EntityManager em;

    private final NoteRepository noteRepository;

    public BookRepositoryJpa(EntityManager em, NoteRepository noteRepository){
        this.em = em;
        this.noteRepository = noteRepository;
    }

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery("Select b from Book b join fetch b.genre join fetch b.autor", Book.class);

        return query.getResultList();
    }

    @Override
    public Book findById(long id) {
        Book book = em.find(Book.class, id);
        return book;
    }

    @Override
    public Book save(Book book) {
        if(book.getId() == 0){
            em.persist(book);
            return book;
        }

        return em.merge(book);
    }

    @Override
    public void deleteBookById(long id) {
        Query query = em.createQuery("delete from Book b where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

}

