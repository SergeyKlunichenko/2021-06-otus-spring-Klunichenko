package ru.otus.spring.bookstore.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.bookstore.models.Genre;
import ru.otus.spring.bookstore.services.BookStoreService;

import java.util.List;

@Controller
public class GenreController {
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
    public String editGenre(Genre genre, Model model) {
        genre = bookStoreService.saveGenre(genre);
        model.addAttribute("genre", genre);
        return "redirect:/genres";

    }


}
