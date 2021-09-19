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
import ru.otus.spring.bookstore.rest.dto.GenreDto;
import ru.otus.spring.bookstore.services.AutorService;
import ru.otus.spring.bookstore.services.BookStoreService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AutorController {
    private final AutorService autorService;

    @Autowired
    public AutorController(AutorService autorService) {
        this.autorService  = autorService;
    }


    @GetMapping("/api/autor")
    public List<AutorDto> getAllAutors() {
        List<Autor> autors = autorService.findAll();

        System.out.println("autors=>"+autors.toString());

        List<AutorDto> autorDtos = autors.stream().map(AutorDto::toDto).collect(Collectors.toList());
        return  autorDtos;
    }

    @GetMapping("/api/autor/{id}")
    public AutorDto getAutorById(@PathVariable long id){
        return AutorDto.toDto(autorService.findById(id));
    }

    @PostMapping("/api/autor/")
    public String postAutor(AutorDto autorDto){
        System.out.println("post autor: name "+autorDto.getName() + " " + autorDto.getId());
        autorService.save(AutorDto.toAutor(autorDto));
        return "OK";
    }
}
