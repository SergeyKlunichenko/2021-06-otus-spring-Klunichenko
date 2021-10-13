package ru.otus.spring.bookstore.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.bookstore.models.Autor;
import ru.otus.spring.bookstore.models.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class AutorRepositoryJpa implements AutorRepository{
    @PersistenceContext
    private final EntityManager em;

    public AutorRepositoryJpa(EntityManager em){
        this.em = em;
    }


    @Override
    public List<Autor> findAll() {
        TypedQuery<Autor> query = em.createQuery("Select a from Autor a", Autor.class);
        return query.getResultList();
    }

    @Override
    public Autor findById(long id) {
        return em.find(Autor.class, id);
    }

    @Override
    public Autor findByName(String name) {
        TypedQuery<Autor> query = em.createQuery("Select a from Autor a where a.name = :name", Autor.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }


    @Override
    public Autor save(Autor autor) {
        if(autor.getId() == 0){
            em.persist(autor);
            return autor;
        }

        return em.merge(autor);
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete from Autor a where a.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }


}
