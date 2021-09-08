package ru.otus.spring.bookstore.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.bookstore.model.Autor;
import ru.otus.spring.bookstore.model.Book;
import ru.otus.spring.bookstore.model.Genre;
import ru.otus.spring.bookstore.model.Note;
import ru.otus.spring.bookstore.service.BookStoreService;

import java.util.List;
import java.util.stream.Collectors;

@ShellComponent
public class BookStoreShell {

    private  final BookStoreService bookStoreService;

    public BookStoreShell(BookStoreService bookStoreService) {
        this.bookStoreService = bookStoreService;
    }

    //**************************************************************//
    //************************* Книги    ***************************//
    @ShellMethod(key={"lb", "listofbooks"} , value="List of books")
    public String listOfBooks(){
        List<Book> books = bookStoreService.getAllBooks();
        return books.stream().map(Book::toString).collect(Collectors.joining("\n"));
    }

    @ShellMethod(key={"fbn", "findbookbyname"} , value="Get list of books by book name ")
    public String findBookByName(String name){
        return bookStoreService.findBookByName(name).toString();
    }

    @ShellMethod(key={"fbg", "findbookbygenrename"} , value="Get list of books by genre name")
    public String findBookByGenreName(String name){
        List<Book> books = bookStoreService.findBookByGenre(name);
        return books.stream().map(Book::toString).collect(Collectors.joining("\n"));
    }

    @ShellMethod(key={"fbgi", "findbookbygenreid"} , value="Get list of books by  genre id")
    public String findBookByGenreId(String id){
        List<Book> books = bookStoreService.findBookByGenreId(id);
        return books.stream().map(Book::toString).collect(Collectors.joining("\n"));
    }


    @ShellMethod(key={"fban", "findbookbyautorname"} , value="Get list of books by name of autor")
    public String findBookByAutor(String name){
        List<Book> books = bookStoreService.findBookByAutor(name);
        return books.stream().map(Book::toString).collect(Collectors.joining("\n"));
    }

    @ShellMethod(key={"fbai", "findbookbyautorid"} , value="Get list of books by id of autor")
    public String findBookByAutorId(String id){
        List<Book> books = bookStoreService.findBookByAutorId(id);
        return books.stream().map(Book::toString).collect(Collectors.joining("\n"));
    }


    @ShellMethod(key={"ab", "addbook"} , value="Add a book")
    public String addBook(String name, String genrename, String autorname){
        Book book =  bookStoreService.addBook(name, genrename, autorname);
        return book.toString();
    }

    @ShellMethod(key={"ub", "updatebook"} , value="Update a book(id,  \"название\", \"жанр\", \"автор\")")
    public String updateBook( String id, String name,  String genrename, String autorname){
        Book book =  bookStoreService.updateBook(id, name, genrename, autorname);
        return book.toString();
    }

    @ShellMethod(key={"dbn", "delbookbyname"} , value="Delete a  book by name")
    public String deleteBookByName(String name){
        bookStoreService.deleteBookByName(name);
        return "OK";
    }

    @ShellMethod(key={"dbi", "delbookbyid"} , value="Delete a  book by name")
    public String deleteBookById(String id){
        bookStoreService.deleteBookById(id);
        return "OK";
    }

    //**************************************************************//
    //************************* Примечания *************************//

    @ShellMethod(key={"ln", "listofallnotes"} , value="List of all notes")
    public String listOfAllNotes(){
        List<Note> notes = bookStoreService.findAllNotes();
        return notes.stream().map(Note::toString).collect(Collectors.joining("\n"));

    }

    @ShellMethod(key={"lbn", "listofnotesbooks"} , value="List of notes from books by id")
    public String listOfNotesById(String id){
        List<Note> notes = bookStoreService.findNotesByBook(id);
        return notes.stream().map(Note::toString).collect(Collectors.joining("\n"));
    }


    @ShellMethod(key={"abn", "addnotetobook"} , value="Add a note to the book (abn 1  \"text with a note\")")
    public String addNoteToBook(String  id, String noteText){
        return bookStoreService.addNoteByBook(id, noteText).toString();
    }


    @ShellMethod(key={"dni", "delnotefrombookbyid"} , value="Delete a note from a book by id")
    public String deleteNoteById(String id){
        bookStoreService.deleteNoteById(id);
        return "OK";
    }


    //*************************************************************************//
    //****************************   Автор  ***********************************//
    @ShellMethod(key={"la", "listofautors"} , value="List of autors")
    public String listOfAutrors(){
        List<Autor> autors = bookStoreService.getAutors();
        return autors.stream().map(Autor::toString).collect(Collectors.joining("\n"));
    }
    @ShellMethod(key={"faid", "findautorbyid"} , value="Finding an autor by id")
    public String findAutorById(String id){
        Autor autor = bookStoreService.findAutorById(id);
        return autor.toString();
    }

    @ShellMethod(key={"aa", "addautor"} , value="Add the autor")
    public String addAutor(String name){
        Autor autor = bookStoreService.addAutor(name);
        return autor.toString();
    }

    @ShellMethod(key={"ea", "editautorbyid"} , value="Edit the autor")
    public String editAutor(String id, String name){
        Autor autor = bookStoreService.editAutor(id, name);
        return autor.toString();
    }


    @ShellMethod(key={"dai", "delautorbyid"} , value="Delete an autor by id")
    public String deleteAutorById(String id){
        return bookStoreService.deleteAutorById(id);
    }

    //************************************************************************//
    //****************************   Жанр  ***********************************//
    @ShellMethod(key={"lg", "listofgenres"} , value="List of genres")
    public String listOfGenres(){
        List<Genre> genres = bookStoreService.getAllGenres();
        return genres.stream().map(Genre::toString).collect(Collectors.joining("\n"));
    }

    @ShellMethod(key={"fgid", "findgenrebyid"} , value="Finding an genre by id")
    public String findGenreById(String id){
        Genre genre = bookStoreService.findGenreById(id);
        return genre.toString();
    }

    @ShellMethod(key={"ag", "addgenre"} , value="Add the genre")
    public String addGenre(String name){
        Genre genre = bookStoreService.addGenre(name);
        return genre.toString();
    }

    @ShellMethod(key={"eg", "editgenrebyid"} , value="Edit the genre")
    public String editGenre(String id, String name){
        Genre genre = bookStoreService.editGenreById(id, name);
        return genre.toString();
    }

    @ShellMethod(key={"dgi", "delgenrebyid"} , value="Delete an genre by id")
    public String deleteGenreById(String id){
        return bookStoreService.deleteGenreById(id);
    }
}
