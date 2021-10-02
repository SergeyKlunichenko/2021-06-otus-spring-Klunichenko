package ru.otus.spring.bookstore.restcontroller.dto;

import lombok.*;
import ru.otus.spring.bookstore.domain.Book;

@NoArgsConstructor
@Data
@Setter
@Getter
@ToString
public class BookDto {
    private String id;
    private String name;
    private GenreDto genre;
    private AutorDto autor;


    public static Book toBook(BookDto bookDto){
        return new Book(bookDto.getId(), bookDto.getName(), GenreDto.toGenre(bookDto.getGenre()), AutorDto.toAutor(bookDto.getAutor()));
    }

}
