package ru.otus.spring.bookstore.restcontroller;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.bookstore.restcontroller.dto.AutorDto;
import ru.otus.spring.bookstore.domain.Autor;
import ru.otus.spring.bookstore.repository.AutorRepository;

@RestController
public class AutorController {
    private final AutorRepository autorRepository;

    public  AutorController(AutorRepository autorRepository){
        this.autorRepository = autorRepository;
    }


    @GetMapping("/api/autor")
    public Flux<Autor> findAll(){
        return autorRepository.findAll();
    }
    @GetMapping("/api/autor/{id}")
    public Mono<Autor> findById(@PathVariable("id") String id){
        return autorRepository.findById(id);
    }
    @GetMapping("/api/autor/byname")
    public  Mono<Autor> findByName(@RequestParam("name") String name){
        return autorRepository.findByName(name);
    }

    @PutMapping("/api/autor")
    public Mono<Autor> savePut( AutorDto autorDto) {
        Autor autor = AutorDto.toAutor(autorDto);
        return autorRepository.save(autor);
    }

    @PostMapping(path="/api/autor")
    public Mono<Autor> savePost(AutorDto autorDto) {
        Autor autor = AutorDto.toAutor(autorDto);
        autor.setId(null );
        return autorRepository.save(autor);

    }

    @DeleteMapping(path="/api/autor")
    public Mono<Void> delete(AutorDto autorDto){
        Autor autor = AutorDto.toAutor(autorDto);
        return autorRepository.delete(autor);
    }

}
