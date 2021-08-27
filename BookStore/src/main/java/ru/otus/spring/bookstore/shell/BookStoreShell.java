package ru.otus.spring.bookstore.shell;

import org.h2.tools.Console;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.bookstore.models.Autor;
import ru.otus.spring.bookstore.models.Book;
import ru.otus.spring.bookstore.models.Genre;
import ru.otus.spring.bookstore.models.Note;
import ru.otus.spring.bookstore.services.BookStoreService;
import ru.otus.spring.bookstore.tools.IOService;

import java.util.List;
import java.util.stream.Collectors;

@ShellComponent
public class BookStoreShell {

    private  final BookStoreService bookStoreService;
    private  final IOService ioService;

    public BookStoreShell(BookStoreService bookStoreService, IOService ioService) {
        this.bookStoreService = bookStoreService;
        this.ioService = ioService;
    }

    @ShellMethod(key={"c","console"}, value="H2 Console")
    public String execConsole(){
        String[] arg = {};
        try {
            Console.main(arg);
        }catch (Exception e){
            e.printStackTrace(System.out);
        }
        return "OK";
    }


    //**************************************************************//
    //************************* Книги    ***************************//
    @ShellMethod(key={"lb", "listofbooks"} , value="List of books")
    public String listOfBooks(){
        List<Book> books = bookStoreService.getAllBooks();
        ioService.println(books.stream().map(Book::toString).collect(Collectors.joining("\n")));
        return "OK";
    }

    @ShellMethod(key={"fbi", "findbookbyid"} , value="Find a books by id")
    public String findBookById(long id){
        Book book = bookStoreService.findBookById(id);
        //ioService.println(books.stream().map(Book::toString).collect(Collectors.joining("\n")));
        return book.toString();
    }

    @ShellMethod(key={"fbn", "findbookbyname"} , value="List of books by id")
    public String findBookByName(String name){
        List<Book> books = bookStoreService.findBookByName(name);
        //ioService.println(books.stream().map(Book::toString).collect(Collectors.joining("\n")));
        return books.stream().map(Book::toString).collect(Collectors.joining("\n"));
    }



    @ShellMethod(key={"lbn", "listofnotesbooks"} , value="List of notes from books by id")
    public String listOfNotesById(long id){
        List<Note> notes = bookStoreService.getNotesBookById(id);
        ioService.println(notes.stream().map(Note::toString).collect(Collectors.joining("\n")));
        return "OK";
    }

    @ShellMethod(key={"ab", "addbook"} , value="Add a book")
    public String addBook(String name, String genrename, String autorname){
        Book book =  bookStoreService.addBook(name, genrename, autorname);
        ioService.println(book);
        return "OK";
    }

    @ShellMethod(key={"abn", "addnotetobook"} , value="Add a note to the book (abn 1  \"text with a note\")")
    public String addNoteToBook(long id, String noteText){
//        List<Note> notes = bookStoreService.addNoteToBookById(id, noteText);
//        return notes.stream().map(Note::toString).collect(Collectors.joining("\n"));
        return bookStoreService.addNoteToBookById(id, noteText).toString();
    }

    @ShellMethod(key={"ub", "updatebook"} , value="Update a book(id, \"название\", \"жанр\", \"автор\")")
    public String updateBook(long id, String name, String genrename, String autorname){
        Book book =  bookStoreService.updateBook(id, name, genrename, autorname);
        ioService.println(book);
        return "OK";
    }


    @ShellMethod(key={"dbi", "delbook"} , value="Delete a  book by id")
    public String deleteBookById(long id){
        bookStoreService.deleteBookById(id);
        return "OK";
    }

    @ShellMethod(key={"dbni", "delnotefrombookbyid"} , value="Delete a note from a book by id")
    public String deleteNoteById(long id){
        bookStoreService.deleteNoteById(id);
        return "OK";
    }


    //*************************************************************************//
    //****************************   Автор  ***********************************//
    @ShellMethod(key={"la", "listofautors"} , value="List of autors")
    public String listOfAutrors(){
        List<Autor> autors = bookStoreService.getAllAutors();
        ioService.println(autors.stream().map(Autor::toString).collect(Collectors.joining("\n")));
        return "OK";
    }
//
    @ShellMethod(key={"faid", "findautorbyid"} , value="Finding an autor by id")
    public String findAutorById(long id){
        Autor autor = bookStoreService.findAutorById(id);
        return autor.toString();
    }

    @ShellMethod(key={"aa", "addautor"} , value="Add the autor")
    public String addAutor(String name){
        Autor autor = bookStoreService.addAutor(name);
        return autor.toString();
    }

    @ShellMethod(key={"ea", "editautorbyid"} , value="Edit the autor")
    public String editAutor(long id, String name){
        Autor autor = bookStoreService.editAutor(id, name);
        return autor.toString();
    }


    @ShellMethod(key={"dai", "delautorbyid"} , value="Delete an autor by id")
    public String deleteAutorById(long id){
        bookStoreService.delAutorById(id);
        return "OK";
    }

    //************************************************************************//
    //****************************   Жанр  ***********************************//
    @ShellMethod(key={"lg", "listofgenres"} , value="List of genres")
    public String listOfGenres(){
        List<Genre> genres = bookStoreService.getAllGenres();
        ioService.println(genres.stream().map(Genre::toString).collect(Collectors.joining("\n")));
        return "OK";
    }

    @ShellMethod(key={"fgid", "findgenrebyid"} , value="Finding an genre by id")
    public String findGenreById(long id){
        Genre genre = bookStoreService.findGenreById(id);
        return genre.toString();
    }

    @ShellMethod(key={"ag", "addgenre"} , value="Add the genre")
    public String addGenre(){
        Genre genre = bookStoreService.addGenre();
        return genre.toString();
    }

    @ShellMethod(key={"eg", "editgenrebyid"} , value="Edit the genre")
    public String editGenre(long id){
        Genre genre = bookStoreService.editGenre(id);
        return genre.toString();
    }

    @ShellMethod(key={"dgi", "delgenrebyid"} , value="Delete an genre by id")
    public String deleteGenreById(long id){
        bookStoreService.delGenreById(id);
        return "OK";
    }
}
