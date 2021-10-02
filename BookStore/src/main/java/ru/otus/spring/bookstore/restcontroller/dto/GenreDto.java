package ru.otus.spring.bookstore.restcontroller.dto;

import lombok.*;
import ru.otus.spring.bookstore.domain.Genre;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Setter
@Getter
@ToString
public class GenreDto {
    private String id;
    private String name;

    public static Genre toGenre(GenreDto genreDto){
        return new Genre(genreDto.getId(), genreDto.getName());
    }
}
