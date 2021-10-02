package ru.otus.spring.bookstore.pagecontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class GenrePageControll {

    @GetMapping("/genre")
    public String findAll(){
        return "genres";
    }

    @GetMapping("/genre/{id}")
    public String findByid(@PathVariable("id") String id){
        if (id.equals("0") ){
            return "newGenre";
        }
        return "editGenre";
    }

    @PutMapping("/genre/")
    public String save(){
        return "redirect:/genre";
    }

}
