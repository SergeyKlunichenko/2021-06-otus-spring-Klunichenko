package ru.otus.spring.bookstore.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.bookstore.domain.Autor;
import ru.otus.spring.bookstore.repository.BookRepository;
import ru.otus.spring.bookstore.restcontroller.dto.AutorDto;
import ru.otus.spring.bookstore.restcontroller.dto.GenreDto;
import ru.otus.spring.bookstore.domain.Genre;
import ru.otus.spring.bookstore.repository.GenreRepository;

@RestController
public class GenreController {
    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;

    public GenreController(GenreRepository genreRepository, BookRepository bookRepository) {
        this.genreRepository = genreRepository;
        this.bookRepository = bookRepository;
    }

    @GetMapping("/api/genre")
    public Flux<Genre> findAll(){
        return genreRepository.findAll();
    }
    @GetMapping("/api/genre/{id}")
    public Mono<Genre> findById(@PathVariable("id") String id){
        return genreRepository.findById(id);
    }
    @GetMapping("/api/genre/")
    public Mono<Genre> findByName(@RequestParam("name") String name){
        return genreRepository.findByName(name);
    }

    @PutMapping("/api/genre")
    public Mono<Genre> savePut(@RequestBody  GenreDto genreDto) {
        Genre genre = GenreDto.toGenre(genreDto);
        return genreRepository.save(genre);
    }

    @PostMapping(path="/api/genre")
    public Mono<Genre> savePost(@RequestBody GenreDto genreDto) {
        Genre genre = GenreDto.toGenre(genreDto);
        genre.setId(null );
        return genreRepository.save(genre);

    }

    @DeleteMapping(path="/api/genre")
    public Mono<Void> delete(@RequestBody GenreDto genreDto){
        Genre genre = GenreDto.toGenre(genreDto);
        return bookRepository.existsByGenre(genre).flatMap(b -> {if(!b) return genreRepository.delete(genre); else  throw new ResponseStatusException(HttpStatus.FOUND, "У жанра имеются книги");   } );
    }
    
    
}
