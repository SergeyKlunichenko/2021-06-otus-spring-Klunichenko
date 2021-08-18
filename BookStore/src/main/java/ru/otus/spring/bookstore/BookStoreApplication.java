package ru.otus.spring.bookstore;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.bookstore.dao.AutorDao;
import ru.otus.spring.bookstore.dao.BookDao;
import ru.otus.spring.bookstore.dao.GenreDao;
import ru.otus.spring.bookstore.domain.Autor;
import ru.otus.spring.bookstore.domain.Book;
import ru.otus.spring.bookstore.domain.Genre;

import java.util.stream.Collectors;


@SpringBootApplication
public class BookStoreApplication {

	public static void main(String[] args) throws  Exception{
		ApplicationContext context = SpringApplication.run(BookStoreApplication.class, args);
	}

}
