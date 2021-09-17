package ru.otus.spring.bookstore.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.bookstore.exceptions.BookStoreException;
import ru.otus.spring.bookstore.models.Autor;
import ru.otus.spring.bookstore.models.Genre;
import ru.otus.spring.bookstore.rest.dto.AutorDto;
import ru.otus.spring.bookstore.services.AutorService;
import ru.otus.spring.bookstore.services.BookStoreService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AutorController {
    //private final BookStoreService bookStoreService;
    private final AutorService autorService;

    @Autowired
//    public AutorController(BookStoreService bookStoreService) {
//        this.bookStoreService = bookStoreService;
//    }
    public AutorController(AutorService autorService) {
        this.autorService  = autorService;
    }


    @GetMapping("/api/autor")
    public List<AutorDto> getAllAutors() {
        List<Autor> autors = autorService.findAll();
        List<AutorDto> autorDtos = autors.stream().map(AutorDto::toDto).collect(Collectors.toList());
        return  autorDtos;
    }

    @GetMapping("/api/autor/{id}")
    public AutorDto getAutorById(@PathVariable long id){
        return AutorDto.toDto(autorService.findById(id));
    }





/*
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
    public String edit(Autor autor) {
        autor = bookStoreService.saveAutor(autor);
        return "redirect:/autors";
    }

    @GetMapping("/deleteAutor")
    public String deleteGenre(@RequestParam("id") long id, Model model) {
        Autor autor;
        autor = bookStoreService.findAutorById(id);
        model.addAttribute("autor", autor);
        return "deleteAutor";
    }


    @PostMapping("/deleteAutor")
    public String delete(Autor autor) {
        try {
            bookStoreService.deleteAutor(autor);
        } catch (DataIntegrityViolationException ce) {
            throw new BookStoreException("Ошибка удаления автора: имеются книги с таким автором");
        } catch (Exception e) {
            throw new BookStoreException("Ошибка удаления жанра: " + e.getMessage());
        }
        return "redirect:/autors";
    }

    @ExceptionHandler(BookStoreException.class)
    public ResponseEntity<String> handleNotFound(BookStoreException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
*/

}
