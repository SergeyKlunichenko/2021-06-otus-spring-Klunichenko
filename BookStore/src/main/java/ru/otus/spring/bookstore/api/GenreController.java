package ru.otus.spring.bookstore.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.bookstore.models.Genre;
import ru.otus.spring.bookstore.api.dto.GenreDto;
import ru.otus.spring.bookstore.services.GenreService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GenreController {
    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService  = genreService;
    }


    @GetMapping("/api/genre")
    public List<GenreDto> getAllGenres() {
        List<Genre> genres = genreService.findAll();

        System.out.println("genres=>"+genres.toString());
        List<GenreDto> genreDtos = genres.stream().map(GenreDto::toDto).collect(Collectors.toList());
        return  genreDtos;
    }

    @GetMapping("/api/genre/{id}")
    public GenreDto getGenreById(@PathVariable long id){
        return GenreDto.toDto(genreService.findById(id));
    }


    @PostMapping("/api/genre/")
    public String postGenre(GenreDto genreDto){
        System.out.println("post genre: name "+genreDto.getName() + " " + genreDto.getId());
        genreService.save(GenreDto.toGenre(genreDto));
        return "OK";
    }
}
