package ru.otus.spring.bookstore.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.bookstore.models.Genre;
import ru.otus.spring.bookstore.repositories.GenreRepository;

import java.util.List;

@Service
public class GenreService {
    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository){
        this.genreRepository = genreRepository;
    }

    @Transactional(readOnly = true)
    public List<Genre> findAll(){
        return  genreRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Genre findById(long id){
        return genreRepository.findById(id);
    }

    @Transactional
    public Genre save(Genre genre){
        return genreRepository.save(genre);
    }
}
