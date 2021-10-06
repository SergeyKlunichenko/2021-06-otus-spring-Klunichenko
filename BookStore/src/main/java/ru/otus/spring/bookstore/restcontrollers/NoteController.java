package ru.otus.spring.bookstore.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.bookstore.models.Book;
import ru.otus.spring.bookstore.models.Note;
import ru.otus.spring.bookstore.services.BookStoreService;

import java.util.List;

@Controller
public class NoteController {
    private  final  BookStoreService bookStoreService;

    public NoteController(BookStoreService bookStoreService) {
        this.bookStoreService = bookStoreService;
    }

    @GetMapping("/notes")
    public String getList(@RequestParam("bookid") long bookid, Model model) {
        Book book = bookStoreService.findBookById(bookid);
        List<Note> notes = bookStoreService.findNotesByBook(book);
        model.addAttribute("notes", notes);
        model.addAttribute("book", book);
        return "notes";
    }

}
