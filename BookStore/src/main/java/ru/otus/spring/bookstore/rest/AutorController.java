package ru.otus.spring.bookstore.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.bookstore.models.Autor;
import ru.otus.spring.bookstore.services.BookStoreService;

import java.util.List;

@Controller
public class AutorController {
    private final BookStoreService bookStoreService;

    @Autowired
    public AutorController(BookStoreService bookStoreService) {
        this.bookStoreService = bookStoreService;
    }

    @GetMapping("/autors")
    public String listAutors(Model model) {
        List<Autor> autors = bookStoreService.findAllAutors();
        model.addAttribute("autors", autors);

        return "autors";
    }


    @GetMapping("/editAutor")
    public String edit(@RequestParam("id") long id, Model model) {
        Autor autor;
        if (id == 0) {
            autor = new Autor(0, "");
        } else {
            autor = bookStoreService.findAutorById(id);//autorRepository.findById(id);
        }
        model.addAttribute("autor", autor);
        return "editAutor";
    }

    @PostMapping("/editAutor")
    public String edit(Autor autor, Model model) {
        autor = bookStoreService.saveAutor(autor);
        model.addAttribute("autor", autor);
        return "redirect:/autors";
    }


}
