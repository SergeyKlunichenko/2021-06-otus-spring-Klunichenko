package ru.otus.spring.bookstore.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.bookstore.models.Autor;
import ru.otus.spring.bookstore.models.Book;
import ru.otus.spring.bookstore.models.Genre;
import ru.otus.spring.bookstore.services.BookStoreService;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    private  BookStoreService bookStoreService  ;

    public BookController(BookStoreService bookStoreService) {
        this.bookStoreService = bookStoreService;
    }


    @GetMapping("/")
    public String listBooks(Model model) {
        List<Book> books = bookStoreService.findAllBooks();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/editBook")
    public String edit(@RequestParam("id") long id, Model model) {
        Book book = null;
        if (id == 0) {
            Autor autor = new Autor(0, "");
            Genre genre = new Genre(0, "");
            book = new Book(0, "", autor, genre);
        } else {
            book = bookStoreService.findBookById(id);//bookRepository.findById(id);
        }

        List<Genre> genres = bookStoreService.findAllGenre();
        List<Autor> autors = bookStoreService.findAllAutors();

        model.addAttribute("book", book);
        model.addAttribute("genres", genres);
        model.addAttribute("autors", autors);
        return "editBook";
    }


    @PostMapping("/editBook")
    public String edit(Book book, long genreid, long autorid) {
        System.out.println("genreid=" + genreid);
        Genre genre = bookStoreService.findGenreById(genreid);
        Autor autor = bookStoreService.findAutorById(autorid);
        book.setGenre(genre);
        book.setAutor(autor);
        bookStoreService.saveBook(book);
        return "redirect:/";
    }

    @GetMapping("/deleteBook")
    public String delete(@RequestParam("id") long id, Model model) {
        Book book = bookStoreService.findBookById(id);
        model.addAttribute("book", book);
        return "deleteBook";
    }

    @PostMapping("/deleteBook")
    public String delete(Book book) {
        bookStoreService.deleteBook(book);
        return "redirect:/";
    }

}
