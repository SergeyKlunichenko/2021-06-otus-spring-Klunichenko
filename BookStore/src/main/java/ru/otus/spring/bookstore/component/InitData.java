package ru.otus.spring.bookstore.component;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.otus.spring.bookstore.domain.Autor;
import ru.otus.spring.bookstore.domain.Book;
import ru.otus.spring.bookstore.domain.Genre;
import ru.otus.spring.bookstore.repository.AutorRepository;
import ru.otus.spring.bookstore.repository.BookRepository;
import ru.otus.spring.bookstore.repository.GenreRepository;

import javax.annotation.PostConstruct;

@Component
public class InitData {
    public final AutorRepository autorRepository;
    public final GenreRepository genreRepository;
    public final BookRepository bookRepository;


    public InitData(AutorRepository autorRepository, GenreRepository genreRepository, BookRepository bookRepository) {
        this.autorRepository = autorRepository;
        this.genreRepository = genreRepository;
        this.bookRepository = bookRepository;
    }

    @PostConstruct
    public void loadData(){
        System.out.println("Load autors");
        autorRepository.save(new Autor("Булгаков Михаил Афанасьевич")).subscribe();
        autorRepository.save(new Autor("Стругацкий Аркадий Натанович")).subscribe();
        autorRepository.save(new Autor("Бормоглот")).subscribe();

        System.out.println("Load genres");
        genreRepository.save(new Genre("драма")).subscribe();
        genreRepository.save(new Genre("фантастика")).subscribe();

        System.out.println("Load books");
        Genre genre = genreRepository.findByName("фантастика").block();
        Autor autor = autorRepository.findByName("Стругацкий Аркадий Натанович").block();

        bookRepository.save(new Book("Пикник на обочине", genre, autor)).subscribe();
        bookRepository.save(new Book("Улитка на склоне", genre, autor)).subscribe();

        genre = genreRepository.findByName("драма").block();
        autor = autorRepository.findByName("Булгаков Михаил Афанасьевич").block();

        bookRepository.save(new Book("Мастер и Маргарита", genre, autor)).subscribe();
        bookRepository.save(new Book("Белая гвардия", genre, autor)).subscribe();

    }


}
