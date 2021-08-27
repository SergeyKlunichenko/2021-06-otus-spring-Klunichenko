package ru.otus.spring.bookstore.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.bookstore.models.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class GenreRepositoryJpa implements GenreRepository{
    @PersistenceContext
    private final EntityManager em;

    public GenreRepositoryJpa(EntityManager em){
        this.em = em;
    }


    @Override
    public List<Genre> findAll() {
        TypedQuery<Genre> query = em.createQuery("Select a from Genre a", Genre.class);
        return query.getResultList();
    }

    @Override
    public Genre findById(long id) {
        return Optional.ofNullable(em.find(Genre.class, id)).get();
    }

    @Override
    public Genre findByName(String name) {
        TypedQuery<Genre> query = em.createQuery("Select g from Genre g where g.name = :name", Genre.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }


    @Override
    public Genre save(Genre genre) {
        if(genre.getId() == 0){
            em.persist(genre);
            return genre;
        }
        return em.merge(genre);
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete from Genre a where a.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
