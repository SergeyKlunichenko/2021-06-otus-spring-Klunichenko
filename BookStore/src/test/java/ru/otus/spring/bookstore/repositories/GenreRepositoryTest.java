package ru.otus.spring.bookstore.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.spring.bookstore.models.Genre;
import ru.otus.spring.bookstore.models.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("Репозиторий для работы с жанрами")
@DataJpaTest
class GenreRepositoryTest {
    private static final long EXPECTED_GENRE_ID = 1;
    private static final String EXPECTED_GENRE_NAME = "драма";
    private static final long SECOND_GENRE_ID =2;

    private static final String NEW_GENRE_NAME = "приключения";

    @Autowired
    private GenreRepository genreRepository;

    @Test
    @DisplayName("должен загрузиться жанр "+ EXPECTED_GENRE_NAME)
    void findByIdTest() {
        assertThatCode(()-> genreRepository.findById(EXPECTED_GENRE_ID)).doesNotThrowAnyException();
        Genre genre = genreRepository.findById(EXPECTED_GENRE_ID);
        assertThat(genre.getName()).isEqualTo(EXPECTED_GENRE_NAME);
    }

    @Test
    @DisplayName("должен загрузиться массив с жанрами по имени жанра "+ EXPECTED_GENRE_NAME)
    void findByNameTest(){
        Genre genre = genreRepository.findByName(EXPECTED_GENRE_NAME).get(0);
        assertThat(genre).usingRecursiveComparison().isEqualTo(genreRepository.findById(genre.getId()));
    }

    @Test
    @DisplayName("Должны загрузиться жанры")
    void findAllTest(){
        Genre expectedGenre = genreRepository.findById(EXPECTED_GENRE_ID);
        Genre secondGenre = genreRepository.findById(SECOND_GENRE_ID);

        List<Genre> genres = genreRepository.findAll();
        assertThat(genres)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expectedGenre, secondGenre);


    }

    @Test
    @DisplayName("должен добавиться жанр"+NEW_GENRE_NAME)
    void addTest(){
        Genre newGenre = new Genre(0, NEW_GENRE_NAME);
        newGenre = genreRepository.save(newGenre);
        Genre savedGenre = genreRepository.findById(newGenre.getId());
        genreRepository.flush();
        assertThat(newGenre).usingRecursiveComparison().isEqualTo(savedGenre);
    }

    @Test
    @DisplayName("должен измениться жанр с " + EXPECTED_GENRE_NAME + " на "+ NEW_GENRE_NAME)
    void editTest(){
        Genre genre = genreRepository.findById(EXPECTED_GENRE_ID);
        genre.setName(NEW_GENRE_NAME);
        genre = genreRepository.save(genre);
        genreRepository.flush();
        assertThat(genre.getName()).isNotEqualTo(EXPECTED_GENRE_NAME);

    }

    @Test
    @DisplayName("жанр с ид="+EXPECTED_GENRE_ID + " не должен быть удален, т.к. у книги есть ссылка на этот жанр ")
    void shouldNotBeDeleted() {
        assertThatCode(() -> genreRepository.findById(EXPECTED_GENRE_ID)).doesNotThrowAnyException();
        genreRepository.deleteById(EXPECTED_GENRE_ID);
        assertThatCode(() ->genreRepository.flush()).withThreadDumpOnError();
        assertThatCode(() -> genreRepository.findById(EXPECTED_GENRE_ID)).withThreadDumpOnError();

    }
}