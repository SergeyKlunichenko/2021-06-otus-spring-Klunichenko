package ru.otus.spring.bookstore.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.bookstore.models.Autor;
import ru.otus.spring.bookstore.repositories.AutorRepository;

import java.util.List;

@Service
public class AutorService {
    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository){
        this.autorRepository = autorRepository;
    }

    @Transactional(readOnly = true)
    public List<Autor> findAll(){
        return  autorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Autor findById(long id){
        return autorRepository.findById(id);
    }

    @Transactional
    public Autor save(Autor autor){
        return autorRepository.save(autor);
    }
}
