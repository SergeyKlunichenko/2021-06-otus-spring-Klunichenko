package ru.otus.spring.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
//@ComponentScan({"ru.otus.spring.bookstore.restcontrollers", "ru.otus.spring.bookstore.services", "ru.otus.spring.bookstore.repositories.BookRepository"})
public class BookStoreApplication {

	public static void main(String[] args) throws  Exception{
		ApplicationContext context = SpringApplication.run(BookStoreApplication.class, args);
	}

}
