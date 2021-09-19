package ru.otus.spring.bookstore.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.spring.bookstore.rest.dto.AutorDto;
import ru.otus.spring.bookstore.rest.dto.GenreDto;
import ru.otus.spring.bookstore.services.AutorService;
import ru.otus.spring.bookstore.services.GenreService;

@Controller
public class GenrePageController {
    private  final GenreService genreService;

    public GenrePageController(GenreService genreService){
        this.genreService = genreService;
    }

    @GetMapping("/genre")
    public String listGenrePage(){
        return "genres";
    }
    @GetMapping("/genre/{id}")
    public String editGenre(){
        return "editGenre";
    }

    @PostMapping("/genre/")
    public String postGenre(){
        return "redirect:/genres;";
    }

}
