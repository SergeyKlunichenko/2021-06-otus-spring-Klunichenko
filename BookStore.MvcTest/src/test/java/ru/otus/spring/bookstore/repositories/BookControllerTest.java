package ru.otus.spring.bookstore.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.otus.spring.bookstore.models.Autor;
import ru.otus.spring.bookstore.models.Book;
import ru.otus.spring.bookstore.models.Genre;
import ru.otus.spring.bookstore.rest.BookController;
import ru.otus.spring.bookstore.services.BookService;
import ru.otus.spring.bookstore.services.BookStoreService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@WebMvcTest(BookController.class)
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookController bookController;

    @MockBean
    private BookService bookService;


    private static final long EXPECTED_BOOK_ID =1;

    @Test
    public void getBookByIdTest() throws Exception {
//        Autor autor =  service.findAutorById(1);
//        Genre genre =  service.findGenreById(1);
    //    Book book = bookService.findById(EXPECTED_BOOK_ID);
    //    when(bookService.findById(EXPECTED_BOOK_ID)).thenReturn(book);
        //mockMvc.perform(MockMvcRequestBuilders.get("/editBook?id=1")).andExpect(status().);


    }


}
