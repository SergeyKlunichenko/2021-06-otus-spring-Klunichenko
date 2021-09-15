package ru.otus.spring.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class BookStoreApplication {

	public static void main(String[] args) throws  Exception{
		ApplicationContext context = SpringApplication.run(BookStoreApplication.class, args);
	}

}
