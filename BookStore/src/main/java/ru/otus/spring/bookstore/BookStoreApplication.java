package ru.otus.spring.bookstore;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.bookstore.model.Autor;
import ru.otus.spring.bookstore.model.Genre;
import ru.otus.spring.bookstore.repository.AutorRepository;
import ru.otus.spring.bookstore.repository.BookRepository;
import ru.otus.spring.bookstore.repository.GenreRepository;

import java.util.List;

@SpringBootApplication
@EnableMongock
@EnableConfigurationProperties
public class BookStoreApplication {

	public static void main(String[] args) throws InterruptedException{
		ApplicationContext context = SpringApplication.run(BookStoreApplication.class, args);

//		AutorRepository autorRepository = context.getBean(AutorRepository.class);
//		GenreRepository	genreRepository = context.getBean(GenreRepository.class);
//		BookRepository	bookRepository	= context.getBean(BookRepository.class);
//
//		System.out.println("----------------------------------------------");
//		System.out.println("Авторы в БД:");
//		autorRepository.findAll().forEach(p -> System.out.println(p.getName()));
//		System.out.println("----------------------------------------------");
//		System.out.println("Жанры в БД:");
//		genreRepository.findAll().forEach(p -> System.out.println(p.getName()));
//
//		System.out.println("----------------------------------------------");
//		System.out.println("Книги в БД:");
//		bookRepository.findAll().forEach(p -> System.out.println(p));






	}

}
