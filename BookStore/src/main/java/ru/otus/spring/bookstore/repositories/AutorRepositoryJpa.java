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
    @Transactional(readOnly = true)
    public List<Autor> findAll() {
        TypedQuery query = em.createQuery("Select a from Autor a", Autor.class);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Autor findById(long id) {
        return Optional.ofNullable(em.find(Autor.class, id)).get();
    }

    @Override
    public Autor findByName(String name) {
        TypedQuery<Autor> query = em.createQuery("Select a from Autor a where a.name = :name", Autor.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }


    @Override
    @Transactional
    public Autor save(Autor autor) {
        if(autor.getId() == 0){
            em.persist(autor);
            return autor;
        }

        return em.merge(autor);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        Query query = em.createQuery("delete from Autor a where a.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }


}
