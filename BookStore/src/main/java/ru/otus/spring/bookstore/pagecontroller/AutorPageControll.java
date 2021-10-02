package ru.otus.spring.bookstore.pagecontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class AutorPageControll {

    @GetMapping("/autor")
    public String findAll(){
        return "autors";
    }


    @GetMapping("/autor/{id}")
    public String findByid(@PathVariable("id") String id){
        if (id.equals("0") ){
            return "newAutor";
        }
        return "editAutor";
    }

    @PutMapping("/autor/")
    public String save(){
        return "redirect:/autor";
    }

}
