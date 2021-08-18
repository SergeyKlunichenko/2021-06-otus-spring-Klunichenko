package ru.otus.spring.bookstore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import ru.otus.spring.bookstore.dao.AutorDao;
import ru.otus.spring.bookstore.dao.BookDao;
import ru.otus.spring.bookstore.dao.GenreDao;
import ru.otus.spring.bookstore.domain.Autor;
import ru.otus.spring.bookstore.domain.Book;
import ru.otus.spring.bookstore.domain.Genre;
import ru.otus.spring.bookstore.exceptions.BookStoreException;
import ru.otus.spring.bookstore.tools.IOService;

import java.util.List;
import java.util.Scanner;

@Service
public class BookStoreService {
    @Autowired
    BookDao bookDao;
    @Autowired
    GenreDao genreDao;
    @Autowired
    AutorDao autorDao;

    @Autowired
    IOService ioService;
    private String name;

    public List<Book> getAllBooks() {
        return bookDao.getAll();
    }

    public List<Genre> getAllGenres(){
        return genreDao.getAll();
    }

    public List<Autor> getAllAutors(){
        return autorDao.getAll();
    }

    public Book addBook() throws BookStoreException{
        Genre genre = genreDao.findByName(ioService.readLine("Жанр:"));
        Autor autor = autorDao.findByName(ioService.readLine("Авто:"));
        Book book   = new Book(0, ioService.readLine("Книга:"), autor , genre);
        return bookDao.insert(book);
    }

    public Book updateBook(long id) throws BookStoreException{
        Book book = bookDao.findById(id);
        ioService.println("Редактирование книги:" + book);
        book = new Book(book.getId(), ioService.readLine("Новое наименование книги:"), book.getAutor(), book.getGenre());
        return bookDao.update(book);
    }
    public Book findBookById() throws BookStoreException{
        return bookDao.findById(Long.parseLong(ioService.readLine("Найти книгу с ид:")));
    }

    public Book findBookById(String id) throws BookStoreException{
        return bookDao.findById(Long.parseLong(id));
    }

    public Book findBookByName() throws BookStoreException{
        return bookDao.findByName(ioService.readLine("Найти книгу - "));
    }

    public Book findBookByName(String name) throws BookStoreException{
        return bookDao.findByName(name);
    }

    public void deleteBookByName() throws BookStoreException {
        bookDao.deleteByName(ioService.readLine("Удалить книгу:"));
    }

    public void deleteBookByName(String name) throws BookStoreException {
        bookDao.deleteByName(name);
    }


    public void deleteBookById() throws BookStoreException {
        bookDao.deleteById(Long.parseLong(ioService.readLine("Удалить книгу c ID:")));
    }

    public void deleteBookById(long id) throws BookStoreException {
        bookDao.deleteById(id);
    }

    public Genre addGenre() throws BookStoreException{
        return genreDao.findById(genreDao.insert(new Genre(0, ioService.readLine("Жанр:"))));
    }

    public Genre addGenre(String name) throws BookStoreException{
        return genreDao.findById(genreDao.insert(new Genre(0, name == null?ioService.readLine("Жанр:"):name)));
    }

    public Autor addAutor() throws BookStoreException{
        return autorDao.findById(autorDao.insert(new Autor(0, ioService.readLine("Автор:"))));
    }

    public Autor addAutor(String name) throws BookStoreException{
        return autorDao.findById(autorDao.insert(new Autor(0, name)));
    }


}
