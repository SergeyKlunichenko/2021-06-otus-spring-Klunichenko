package ru.otus.spring.bookstore.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.bookstore.models.Book;
import ru.otus.spring.bookstore.models.Note;
import ru.otus.spring.bookstore.repositories.BookRepository;
import ru.otus.spring.bookstore.repositories.NoteRepository;

import java.util.List;

@Controller
public class NoteController {
    private final  NoteRepository noteRepository;
    public  final BookRepository bookRepository;

    @Autowired
    public NoteController(NoteRepository noteRepository, BookRepository bookRepository){
        this.noteRepository = noteRepository;
        this.bookRepository = bookRepository;
    }

    @GetMapping("/notes")
    public String getList(@RequestParam("bookid") long bookid, Model model){
        Book book = bookRepository.findById(bookid);
        List<Note> notes = noteRepository.findAllForBook(book);
        model.addAttribute("notes", notes);
        model.addAttribute("book", book);
        return "notes";
    }

}
