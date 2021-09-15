package ru.otus.spring.bookstore.services;

import org.springframework.stereotype.Service;
import ru.otus.spring.bookstore.models.Autor;
import ru.otus.spring.bookstore.repositories.AutorRepository;

import java.util.List;

@Service
public class AutorService {
    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository){
        this.autorRepository = autorRepository;
    }


    public List<Autor> findAll(){
        return  autorRepository.findAll();
    }
}
