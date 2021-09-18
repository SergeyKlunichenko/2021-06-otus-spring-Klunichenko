package ru.otus.spring.bookstore.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.bookstore.models.Genre;
import ru.otus.spring.bookstore.rest.dto.GenreDto;
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

    /*
    public final BookStoreService bookStoreService;

    public GenreController(BookStoreService bookStoreService) {
        this.bookStoreService = bookStoreService;
    }

    @GetMapping("/genres")
    public String listGenres(Model model) {
        List<Genre> genres = bookStoreService.findAllGenre();
        model.addAttribute("genres", genres);

        return "genres";
    }

    @GetMapping("/editGenre")
    public String editGenre(@RequestParam("id") long id, Model model) {
        Genre genre;
        if (id == 0) {
            genre = new Genre(0, "");
        } else {
            genre = bookStoreService.findGenreById(id);
        }
        model.addAttribute("genre", genre);
        return "editGenre";
    }

    @PostMapping("/editGenre")
    public String editGenre(Genre genre) {
        bookStoreService.saveGenre(genre);
        return "redirect:/genres";
    }

    @GetMapping("/deleteGenre")
    public String deleteGenre(@RequestParam("id") long id, Model model) {
        Genre genre;
        genre = bookStoreService.findGenreById(id);
        model.addAttribute("genre", genre);
        return "deleteGenre";
    }

    @PostMapping("/deleteGenre")
    public String delete(Genre genre) {
        try {
            bookStoreService.deleteGenre(genre);
        } catch (DataIntegrityViolationException ce) {
            throw new BookStoreException("Ошибка удаления жанра: имеются книги с таким жанром");
        } catch (Exception e) {
            throw new BookStoreException("Ошибка удаления жанра: " + e.getClass().getSimpleName());
        }
        return "redirect:/genres";
    }

    @ExceptionHandler(BookStoreException.class)
    public ResponseEntity<String> handleNotFound(BookStoreException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    */
}
