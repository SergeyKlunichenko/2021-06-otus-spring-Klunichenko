package ru.otus.spring.bookstore.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.bookstore.events.GenreEventListener;
import ru.otus.spring.bookstore.model.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DataMongoTest
@DisplayName("Тестирование жанров")
@Import(GenreEventListener.class)

class GenreRepositoryTest {
    private static final int EXPECTED_GENRE_COUNT = 2;
    private static final String EXPECTED_GENRE_NAME = "фантастика";
    private static final String NEW_GENRE_NAME = "приключения";

    @Autowired
    private GenreRepository genreRepository;

    @Test
    @DisplayName("должен добавить жанр "+NEW_GENRE_NAME)
    void shouldAddNewGenre(){
        Genre genre = new Genre(NEW_GENRE_NAME);
        genre = genreRepository.save(genre);
        assertThat(genre.getName()).isEqualTo(NEW_GENRE_NAME);
        genreRepository.delete(genre);

    }

    @Test
    @DisplayName("должны получить список из "+EXPECTED_GENRE_COUNT + " жанров")
    void shoulGetListOfTwoGenred() {
        List<Genre> genres = genreRepository.findAll();
        assertThat(genres.size()).isEqualTo(EXPECTED_GENRE_COUNT);
    }

    @Test
    @DisplayName("должны получить объект с жанром "+EXPECTED_GENRE_NAME)
    void shoulGetAnObjectWithGenre() {
        Genre genre = genreRepository.findByName(EXPECTED_GENRE_NAME);
        assertThat(genre).isNotNull();
        assertThat(genre.getName()).isEqualTo(EXPECTED_GENRE_NAME);

    }

    @Test
    @DisplayName("не должны удалить жанр у которого есть ссылка на книгу")
    void shouldNotDeleteGenreWithBook(){
        Genre genre = genreRepository.findByName(EXPECTED_GENRE_NAME);
        assertThat(genre).isNotNull();
        assertThatCode(()->genreRepository.delete(genre)).hasNoSuppressedExceptions();

    }

    @Test
    @DisplayName("должен удалиться жанр, т.к. на него нет ссылок")
    void shouldDeleteGenreWithoutBook(){
        Genre genre = new Genre(NEW_GENRE_NAME);
        genre = genreRepository.save(genre);
        assertThat(genre).isNotNull();
        String id = genre.getId();
        genreRepository.deleteById(id);

    }

    @Test
    @DisplayName("жанр не должен изменится, т.к. в магазине есть книги с таким жанром")
    void shouldNotEditGenreBecauseThereIsBooks(){
        Genre genre = genreRepository.findByName(EXPECTED_GENRE_NAME);
        genre.setName(NEW_GENRE_NAME);
        assertThatCode(()->genreRepository.save(genre)).hasNoSuppressedExceptions();
    }


}