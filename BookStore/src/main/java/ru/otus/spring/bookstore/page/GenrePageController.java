package ru.otus.spring.bookstore.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GenrePageController {
    @GetMapping("/genre")
    public String listGenrePage(){
        return "genres";
    }

    @GetMapping("/genre/{id}")
    public String editGenre(@PathVariable(name = "id") long id){
        if (id != 0) {
            return "editGenre";
        } else {
            return "newGenre";
        }
    }
    @PostMapping("/genre/")
    public String postGenre(){
        return "redirect:/genres;";
    }

}
