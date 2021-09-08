package ru.otus.spring.bookstore.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.bookstore.models.Autor;
import ru.otus.spring.bookstore.models.Book;
import ru.otus.spring.bookstore.models.Genre;
import ru.otus.spring.bookstore.repositories.AutorRepository;
import ru.otus.spring.bookstore.repositories.BookRepository;
import ru.otus.spring.bookstore.repositories.GenreRepository;

import java.util.List;

@Controller
public class BookController{
    private final   BookRepository bookRepository;
    private final   GenreRepository genreRepository;
    private final AutorRepository autorRepository;

    @Autowired
    public BookController(BookRepository bookRepository, GenreRepository genreRepository, AutorRepository autorRepository){
        this.bookRepository = bookRepository;
        this.autorRepository = autorRepository;
        this.genreRepository = genreRepository;
    }


    @GetMapping("/")
    public String listBooks(Model model){
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/editBook")
    public String editGenre(@RequestParam("id") long id, Model model){
        Book book ;
        if(id== 0) {
            Autor autor = new Autor(0, "");
            Genre genre = new Genre(0, "");
            book = new Book(0, "", autor,  genre);
        } else {
            book = bookRepository.findById(id);
        }
        model.addAttribute("book", book);
        return "editBook";
    }

    @PostMapping("/editBook")
    public String edit(Book book, Model model){
        book = bookRepository.save(book);
        model.addAttribute("book", book);
        return "editBook";
    }

    @GetMapping("/deleteBook")
    public String delete(@RequestParam("id") long id, Model model){
        Book book = bookRepository.findById(id);
        model.addAttribute("book", book);
        return "deleteBook";
    }

    @PostMapping("/deleteBook")
    public String delete(Book book, Model model){
        System.out.println("ID:"+book.getId());
        bookRepository.deleteById(book.getId());
        book.setId(0);
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);

        return "books";
    }

}
