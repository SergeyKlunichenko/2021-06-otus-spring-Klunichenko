package ru.otus.spring.bookstore.shell;

import org.h2.tools.Console;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.bookstore.domain.Autor;
import ru.otus.spring.bookstore.domain.Book;
import ru.otus.spring.bookstore.domain.Genre;
import ru.otus.spring.bookstore.services.BookStoreService;
import ru.otus.spring.bookstore.tools.IOService;

import java.util.stream.Collectors;

@ShellComponent
public class BookingStoreShell {

    private  final BookStoreService bookStoreService;
    private  final IOService ioService;

    public BookingStoreShell(BookStoreService bookStoreService, IOService ioService) {
        this.bookStoreService = bookStoreService;
        this.ioService = ioService;
    }

    @ShellMethod(key="console", value="H2 Console")
    public String execConsole(){
        String[] arg = {};
        try {
            Console.main(arg);
        }catch (Exception e){
            e.printStackTrace(System.out);
        }
        return "OK";
    }


    @ShellMethod(key={"lb", "listofbooks"} , value="List of books")
    public String listOfBooks(){
        ioService.println(bookStoreService.getAllBooks().stream().map(Book::toString).collect(Collectors.joining("\n")));
        return "OK";
    }


    @ShellMethod(key={"lg", "listofgenres"} , value="List of genres")
    public String listOfGenres(){
        ioService.println(bookStoreService.getAllGenres().stream().map(Genre::toString).collect(Collectors.joining("\n")));
        return "OK";
    }

    @ShellMethod(key={"la", "listofautors"} , value="List of autors")
    public String listOfAutrors(){
        ioService.println(bookStoreService.getAllAutors().stream().map(Autor::toString).collect(Collectors.joining("\n")));
        return "OK";
    }

    @ShellMethod(key ={"ab", "addbook"}, value="Adding book")
    public String addBook() {
        try {
            return "Добавлена книга:" + bookStoreService.addBook();
        } catch (Exception e){
            return e.getMessage();
        }
    }

    @ShellMethod(key ={"eb", "editbook"}, value="Edit a book")
    public String editBook(long id) {
        try {
            return "Книга отредактирована:" + bookStoreService.updateBook(id);
        } catch (Exception e){
            return e.getMessage();
        }
    }


    @ShellMethod(key={"ag", "addgenre"} , value="Adding genre")
    public String addGenre(){
        try {
            return "Добавлен жанр:" + bookStoreService.addGenre();
        } catch (Exception e){
            return e.getMessage();
        }
    }

    @ShellMethod(key={"aa", "addautor"} , value="Adding autor")
    public String addAutor(){
        try {
            return "Добавлен автор:" + bookStoreService.addAutor();
        } catch (Exception e){
            return e.getMessage();
        }
    }


    @ShellMethod(key ={"dbn", "delbookname"}, value="Delete a book by name")
    public String delBookByName() throws Exception{
        bookStoreService.deleteBookByName();
        return "OK";
    }

    @ShellMethod(key ={"dbi", "delbookid"}, value="Delete a book by id")
    public String delBookById(long id) throws Exception{
        bookStoreService.deleteBookById(id);
        return "OK";
    }
}
