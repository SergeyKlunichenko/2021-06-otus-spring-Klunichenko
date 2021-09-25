package ru.otus.spring.bookstore.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.bookstore.api.dto.AutorDto;

@Controller
public class AutorPageController {
    @GetMapping("/autor")
    public String listAutorsPage(){
        return "autors";
    }
    @GetMapping("/autor/{id}")
    public String editAutor(@PathVariable(name = "id") long id){
        if (id != 0) {
            return "editAutor";
        } else {
            return "newAutor";
        }
    }
    @PostMapping("/autor/")
    public String save(AutorDto autorDto){
        return "redirect:/autor";
    }


}
