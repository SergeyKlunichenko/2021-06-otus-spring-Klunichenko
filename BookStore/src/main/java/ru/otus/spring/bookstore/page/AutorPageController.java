package ru.otus.spring.bookstore.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AutorPageController {
    @GetMapping("/autor")
    public String listAutursPage(){
        return "autors";
    }

}
