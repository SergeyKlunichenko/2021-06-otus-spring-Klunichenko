package ru.otus.spring.bookstore.shell;

import org.h2.tools.Console;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.bookstore.dao.AutorDao;
import ru.otus.spring.bookstore.dao.BookDao;
import ru.otus.spring.bookstore.dao.GenreDao;
import ru.otus.spring.bookstore.domain.Autor;
import ru.otus.spring.bookstore.domain.Book;
import ru.otus.spring.bookstore.domain.Genre;
import ru.otus.spring.bookstore.exceptions.BookStoreException;

import java.util.stream.Collectors;

@ShellComponent
public class BookingStoreShell {
    private final BookDao bookDao;
    private final GenreDao genreDao;
    private final AutorDao autorDao;

    public  BookingStoreShell(BookDao bookDao, GenreDao genreDao, AutorDao autorDao){
        this.bookDao = bookDao;
        this.genreDao = genreDao;
        this.autorDao = autorDao;
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
		System.out.println(bookDao.getAll().stream().map(Book::toString).collect(Collectors.joining("\n")));
        return "OK";
    }

    @ShellMethod(key={"la", "listofautors"} , value="List of autors")
    public String listOfAutrors(){
        System.out.println(autorDao.getAll().stream().map(Autor::toString).collect(Collectors.joining("\n")));
        return "OK";
    }

    @ShellMethod(key={"lg", "listofgenres"} , value="List of genres")
    public String listOfGenres(){
        System.out.println(genreDao.getAll().stream().map(Genre::toString).collect(Collectors.joining("\n")));
        return "OK";
    }

    @ShellMethod(key={"ag", "addgenre"} , value="Adding genre")
    public String addGenre(){
        long id = genreDao.insert(new Genre(0, "Проза"));
        Genre genre;
        try {
            genre = genreDao.findById(id);
        } catch (BookStoreException e){
            return e.getMessage();
        }

        return "Добавлен жанр: "+genre;
    }


    @ShellMethod(key ={"ab", "addbook"}, value="Adding book")
    public String addBook(){
        Autor autor ;
        Genre genre ;
        Book book;
        try {
            autor = autorDao.findByName("Булгаков Михаил Афанасьевич");
            genre = genreDao.findByName("Проза");

            book = new Book(0, "Мастер и Маргарита",  autor, genre);
            long id = bookDao.insert(book);

            book = bookDao.findById(id);
        } catch (BookStoreException e) {
            return e.getMessage();
        }

        return "Добавлена книга:"+ book;
    }

    @ShellMethod(key ={"dbn", "delbookname"}, value="Delete a book by name")
    public String delBookByName(){
        //Book book = new Book(3, "Руслан и Людмила",  autorDao.findByName("Пушкин Александр Сергеевич"), genreDao.findByName("Стихи"));
        bookDao.deleteByName("Руслан и Людмила");
        return "OK";
    }

    @ShellMethod(key ={"dbi", "delbookid"}, value="Delete a book by id")
    public String delBookById(){
        //Book book = new Book(3, "Руслан и Людмила",  autorDao.findByName("Пушкин Александр Сергеевич"), genreDao.findByName("Стихи"));
        bookDao.deleteById(1);
        return "OK";
    }

    @ShellMethod(key="init", value="Инициализация")
    public String init(){
        try {
            autorDao.insert(new Autor(3, "Пушкин Александр Сергеевич"));
            System.out.println(autorDao.getAll().stream().map(Autor::toString).collect(Collectors.joining("\n")));

            genreDao.insert(new Genre(3, "Приключения"));
            genreDao.insert(new Genre(4, "Стихи"));
            System.out.println(genreDao.getAll().stream().map(Genre::toString).collect(Collectors.joining("\n")));
            Book book = new Book(0, "Руслан и Людмила", autorDao.findByName("Пушкин Александр Сергеевич"), genreDao.findByName("Стихи"));
            long id = bookDao.insert(book);
            System.out.printf("Добавлена книга с ид="+id);
            System.out.println(bookDao.getAll().stream().map(Book::toString).collect(Collectors.joining("\n")));
        } catch (Exception e){
            e.printStackTrace(System.out);
        }
        return "OK";
    }
}
