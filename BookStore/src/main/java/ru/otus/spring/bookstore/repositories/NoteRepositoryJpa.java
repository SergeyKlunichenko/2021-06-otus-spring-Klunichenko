package ru.otus.spring.bookstore.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.spring.bookstore.models.Note;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class NoteRepositoryJpa implements NoteRepository {
    @PersistenceContext
    private final EntityManager em;

    public NoteRepositoryJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public Note findById(long id) {
        return em.find(Note.class, id);
    }

    @Override
    public List<Note> findAll() {
        TypedQuery<Note> query = em.createQuery("select n from Note n", Note.class);
        return query.getResultList();
    }

    @Override
    public List<Note> findAllByBookId(long bookid) {
        TypedQuery<Note> query = em.createQuery("select n from Note n where n.bookid =  :bookid", Note.class);
        query.setParameter("bookid", bookid);
        return query.getResultList();
    }

    @Override
    public Note save(Note note) {
        if(note.getId() == 0){
            em.persist(note);
            return note;
        }
        return em.merge(note);
    }

    @Override
    public void deteteById(long id) {
        Query query =  em.createQuery("delete from Note n where n.id = :id ");
        query.setParameter("id", id);
        query.executeUpdate();
    }


}
