package ru.otus.spring.bookstore.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import ru.otus.spring.bookstore.models.Autor;
import ru.otus.spring.bookstore.rest.dto.AutorDto;
import ru.otus.spring.bookstore.services.AutorService;

@Controller
public class AutorPageController {
    private  final AutorService autorService;

    public AutorPageController(AutorService autorService){
        this.autorService = autorService;
    }

    @GetMapping("/autor")
    public String listAutursPage(){
        return "autors";
    }
    @GetMapping("/autor/{id}")
    public String editAutor(){
        return "editAutor";
    }
    @PostMapping("/autor/")
    public String save(AutorDto autorDto){

        System.out.println("Put "+ autorDto.getId() + " " + autorDto.getName());

        autorService.save(AutorDto.toAutor(autorDto));
        return "redirect:/autor";
    }


}
