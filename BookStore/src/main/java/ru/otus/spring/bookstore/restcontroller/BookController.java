package ru.otus.spring.bookstore.restcontroller;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
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

    Autor autor ;
    Genre genre ;


    public BookController(BookRepository bookRepository, GenreRepository genreRepository, AutorRepository autorRepository) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.autorRepository = autorRepository;
    }


    @GetMapping("/api/book")
    public Flux<Book> findAll(){
        return bookRepository.findAll();
    }

    @GetMapping("/api/book/{id}")
    public Mono<Book> findById(@PathVariable("id") String id){
        return bookRepository.findById(id);
    }

    @PutMapping("/api/book")
    public Mono<Book> savePut(@RequestBody BookDto bookDto) {

        System.out.printf("Put: id=%s, genreid=%s, autorid=%s", bookDto.getId(), bookDto.getGenre().getId(), bookDto.getAutor().getId());

        Book book = BookDto.toBook(bookDto);
        return bookRepository.save(book);
    }


    @PostMapping("/api/book")
    public Mono<Book> savePost(@RequestBody BookDto bookDto) {
        System.out.printf("Post: id=%s, genreid=%s, autorid=%s", bookDto.getId(), bookDto.getGenre().getId(), bookDto.getAutor().getId());

        Book book = BookDto.toBook(bookDto);
        book.setId(null);
        return bookRepository.save(book);
    }

    @DeleteMapping(path="/api/book")
    public Mono<Void> delete(@RequestBody BookDto bookDto){
        return bookRepository.deleteById(bookDto.getId());
    }

    /*

    @GetMapping("/api/book/byname")
    public  Mono<Book> findByName(@RequestParam("name") String name){
        return bookRepository.findByName(name);
    }


    @PostMapping(path="/api/book")
    public Mono<Book> savePost(BookDto bookDto) {
        Book book = BookDto.toBook(bookDto);
        book.setId(null );
        return bookRepository.save(book);

    }

    @DeleteMapping(path="/api/book")
    public Mono<Void> delete(BookDto bookDto){
        Book book = BookDto.toBook(bookDto);
        return bookRepository.delete(book);
    }
    */
}
