package ru.otus.spring.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.bookstore.component.InitData;

import static org.springframework.web.reactive.function.server.RequestPredicates.queryParam;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@SpringBootApplication
public class BookStore {

    public static void main(String[] args) {

        System.out.println("Start BookStore application");
        ApplicationContext context = SpringApplication.run(BookStore.class);

    }

}


