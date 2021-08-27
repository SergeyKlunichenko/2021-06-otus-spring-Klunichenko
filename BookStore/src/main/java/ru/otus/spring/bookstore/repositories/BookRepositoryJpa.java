package ru.otus.spring.bookstore.repositories;

import org.springframework.beans.factory.annotation.Autowired;
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

    //@Autowired
    private final NoteRepository noteRepository;

//    public BookRepositoryJpa(EntityManager em){
//        this.em = em;
//    }

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
        return Optional.ofNullable(em.find(Book.class, id)).get();
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
    @Override
    public List<Note> getNotesByBookId(long id) {
        return noteRepository.findAllByBookId(id);
    }

    @Override
    public Note addNoteByBookId(long id, String value) {
        Note note = new Note(0, id, value);
        return noteRepository.save(note);
    }


}

