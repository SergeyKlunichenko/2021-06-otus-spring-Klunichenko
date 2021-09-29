package ru.otus.spring.bookstore.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.otus.spring.bookstore.domain.Autor;
import ru.otus.spring.bookstore.domain.Genre;
import ru.otus.spring.bookstore.repository.AutorRepository;
import ru.otus.spring.bookstore.repository.GenreRepository;

@Service
public class InitData {
    public final AutorRepository autorRepository;
    public final GenreRepository genreRepository;


    public InitData(AutorRepository autorRepository, GenreRepository genreRepository) {
        this.autorRepository = autorRepository;
        this.genreRepository = genreRepository;
    }


    public void loadData(){
        System.out.println("Load autors");
        autorRepository.save(new Autor("Булгаков Михаил Афанасьевич")).subscribe();
        autorRepository.save(new Autor("Стругацкий Аркадий Натанович")).subscribe();

        System.out.println("Load genres");
        genreRepository.save(new Genre("драма")).subscribe();
        genreRepository.save(new Genre("фантастика")).subscribe();


    }


}
