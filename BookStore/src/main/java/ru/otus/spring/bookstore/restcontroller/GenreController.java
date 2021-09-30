package ru.otus.spring.bookstore.restcontroller;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.bookstore.restcontroller.dto.GenreDto;
import ru.otus.spring.bookstore.domain.Genre;
import ru.otus.spring.bookstore.repository.GenreRepository;

@RestController
public class GenreController {
    private final GenreRepository genreRepository;

    public GenreController(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
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
        return genreRepository.delete(genre);
    }
    
    
}
