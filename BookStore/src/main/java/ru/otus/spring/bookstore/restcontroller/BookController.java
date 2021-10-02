package ru.otus.spring.bookstore.restcontroller;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import ru.otus.spring.bookstore.domain.Autor;
import ru.otus.spring.bookstore.domain.Book;
import ru.otus.spring.bookstore.domain.Genre;
import ru.otus.spring.bookstore.repository.AutorRepository;
import ru.otus.spring.bookstore.repository.BookRepository;
import ru.otus.spring.bookstore.repository.GenreRepository;
import ru.otus.spring.bookstore.restcontroller.dto.BookDto;

@RestController
public class BookController {
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final AutorRepository autorRepository;


    public BookController(BookRepository bookRepository, GenreRepository genreRepository, AutorRepository autorRepository) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.autorRepository = autorRepository;
    }


    @GetMapping("/api/book")
    public Flux<Book> findAll(){
        return bookRepository.findAll();
    }

    @GetMapping(value = "/api/book", params = "name")
    public Flux<Book> findByName(@RequestParam("name") String name){
        return bookRepository.findByName(name);
    }


    @GetMapping("/api/book/{id}")
    public Mono<Book> findById(@PathVariable("id") String id){
        return bookRepository.findById(id);
    }

    @PutMapping("/api/book")
    public Mono<Book> savePut(@RequestBody BookDto bookDto) {
        System.out.printf("Put: id=%s, genreid=%s, autorid=%s", bookDto.getId(), bookDto.getGenre().getId(), bookDto.getAutor().getId());
        Mono<Genre> genreMono = genreRepository.findById(bookDto.getGenre().getId());
        Mono<Autor> autorMono = autorRepository.findById(bookDto.getAutor().getId());
        Mono<Tuple2<Genre, Autor>> gaZip = Mono.zip(genreMono, autorMono);
        Mono<Book> bookMono = gaZip.map(t-> new Book(bookDto.getId(), bookDto.getName(), t.getT1(), t.getT2()));

        return bookMono.flatMap(bookRepository::save);
    }


    @PostMapping("/api/book")
    public Mono<Book> savePost(@RequestBody BookDto bookDto) {
        System.out.printf("Post: id=%s, genreid=%s, autorid=%s", bookDto.getId(), bookDto.getGenre().getId(), bookDto.getAutor().getId());

        return genreRepository.findById(bookDto.getGenre().getId())
                .zipWith(autorRepository.findById(bookDto.getAutor().getId()))
                .map(t -> new Book(null, bookDto.getName(), t.getT1(), t.getT2()))
                .flatMap(bookRepository::save);

    }

    @DeleteMapping(path="/api/book")
    public Mono<Void> delete(@RequestBody BookDto bookDto){
        return bookRepository.deleteById(bookDto.getId());
    }

}
