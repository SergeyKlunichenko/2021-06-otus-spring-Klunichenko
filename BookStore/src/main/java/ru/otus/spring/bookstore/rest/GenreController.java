package ru.otus.spring.bookstore.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.bookstore.models.Genre;
import ru.otus.spring.bookstore.repositories.GenreRepository;

import java.util.List;

@Controller
public class GenreController {
    private  final GenreRepository genreRepository;

    public GenreController(GenreRepository genreRepository){
        this.genreRepository = genreRepository;
    }

    @GetMapping("/genres")
    public String listGenres(Model model){
        List<Genre> genres = genreRepository.findAll();
        model.addAttribute("genres", genres);

        return "genres";
    }

    @GetMapping("/editGenre")
    public String editGenre(@RequestParam("id") long id, Model model){
        Genre genre ;
        if(id== 0) {
            genre = new Genre(0, "");
        } else {
            genre = genreRepository.findById(id);
        }
        model.addAttribute("genre", genre);
        return "editGenre";
    }

    @PostMapping("/editGenre")
    public String editGenre(Genre genre, Model model){
        genre = genreRepository.save(genre);
        model.addAttribute("genre", genre);
        return "editGenre";

    }


}
