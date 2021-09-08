package ru.otus.spring.bookstore.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.bookstore.models.Autor;
import ru.otus.spring.bookstore.models.Genre;
import ru.otus.spring.bookstore.repositories.AutorRepository;
import ru.otus.spring.bookstore.repositories.GenreRepository;

import java.util.List;

@Controller
public class AutorController {
    private  final AutorRepository autorRepository;

    public AutorController(AutorRepository autorRepository){
        this.autorRepository = autorRepository;
    }

    @GetMapping("/autors")
    public String listAutors(Model model){
        List<Autor> autors = autorRepository.findAll();
        model.addAttribute("autors", autors);

        return "autors";
    }


    @GetMapping("/editAutor")
    public String edit(@RequestParam("id") long id, Model model){
        Autor autor ;
        if(id== 0) {
            autor = new Autor(0, "");
        } else {
            autor = autorRepository.findById(id);
        }
        model.addAttribute("autor", autor);
        return "editAutor";
    }

    @PostMapping("/editAutor")
    public String edit(Autor autor, Model model){
        autor = autorRepository.save(autor);
        model.addAttribute("autor", autor);
        return "editAutor";
    }


}
