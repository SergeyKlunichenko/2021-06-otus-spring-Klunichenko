package ru.otus.spring.bookstore.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.bookstore.models.Book;
import ru.otus.spring.bookstore.models.Note;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryJpa implements BookRepository{
    @PersistenceContext
    private final EntityManager em;

    public BookRepositoryJpa(EntityManager em){
        this.em = em;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findAll() {
        TypedQuery query = em.createQuery("Select b from Book b join fetch b.genre join fetch b.autor", Book.class);

        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Book findById(long id) {
        return Optional.ofNullable(em.find(Book.class, id)).get();
    }

    @Override
    @Transactional
    public Book updateBook(Book book) {
        if(book.getId() == 0){
            em.persist(book);
            return book;
        }

        return em.merge(book);
    }


    @Override
    @Transactional
    public Note addNoteToBookById(long id, String noteText) {
        Book book = findById(id);
        Note note = new Note(0, noteText);
        book.getNotes().add(note);
        em.flush();
        return note;
    }

    @Override
    @Transactional
    public void deleteBookById(long id) {
        Query query = em.createQuery("delete from Book b where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    @Transactional
    public void deleteNoteFromBookById(long id) {
        Query  query = em.createQuery("delete from Note n where n.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }


}

