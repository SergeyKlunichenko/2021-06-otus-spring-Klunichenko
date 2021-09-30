package ru.otus.spring.bookstore.pagecontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class BookPageControll {

    @GetMapping("/book")
    public String findAll(){
        return "books";
    }

    @GetMapping("/book/{id}")
    public String editBook(@PathVariable("id") String id){
        if (id.equals("0") ){
            return "newBook";
        }
        return "editBook";
    }

    @PutMapping("/book/")
    public String save(){
        return "redirect:/book";
    }

}
