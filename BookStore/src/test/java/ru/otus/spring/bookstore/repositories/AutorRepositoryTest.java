package ru.otus.spring.bookstore.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.spring.bookstore.models.Autor;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;


@DisplayName("Репозитарий для работы с авторами")
@DataJpaTest
class AutorRepositoryTest {
    private static final long EXPECTED_AUTOR_ID = 1;
    private static final String EXPECTED_AUTOR_NAME = "Булгаков Михаил Афанасьевич";
    private static final long SECOND_AUTOR_ID =2;

    private static final String NEW_AUTOR_NAME = "Пушкин Александр Сергеевич";

    @Autowired
    private AutorRepository autorRepository;

    @Test
    @DisplayName("должен загрузиться автор "+EXPECTED_AUTOR_NAME)
    void findByIdTest() {
        assertThatCode(()->autorRepository.findById(EXPECTED_AUTOR_ID)).doesNotThrowAnyException();
        Autor autor = autorRepository.findById(EXPECTED_AUTOR_ID);
        assertThat(autor.getName()).isEqualTo(EXPECTED_AUTOR_NAME);
    }

    @Test
    @DisplayName("должен загрузщиться массив с автором "+EXPECTED_AUTOR_NAME)
    void findByNameTest(){
        Autor autor = autorRepository.findByName(EXPECTED_AUTOR_NAME);
        assertThat(autor).usingRecursiveComparison().isEqualTo(autorRepository.findById(autor.getId()));
    }

    @Test
    @DisplayName("Должны загрузиться авторы")
    void findAllTest(){
        Autor expectedAutor = autorRepository.findById(EXPECTED_AUTOR_ID);
        Autor secondAutor = autorRepository.findById(SECOND_AUTOR_ID);

        List<Autor> autors = autorRepository.findAll();
        assertThat(autors)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expectedAutor, secondAutor);


    }

    @Test
    @DisplayName("должен добавиться "+NEW_AUTOR_NAME)
    void addTest(){
        Autor newAutor = new Autor(0, NEW_AUTOR_NAME);
        newAutor = autorRepository.save(newAutor);
        Autor savedAutor = autorRepository.findById(newAutor.getId());
        autorRepository.flush();
        assertThat(newAutor).usingRecursiveComparison().isEqualTo(savedAutor);
    }

    @Test
    @DisplayName("должен измениться с " + EXPECTED_AUTOR_NAME + " на "+ NEW_AUTOR_NAME)
    void editTest(){
        Autor autor = autorRepository.findById(EXPECTED_AUTOR_ID);
        autor.setName(NEW_AUTOR_NAME);
        autor = autorRepository.save(autor);
        autorRepository.flush();
        assertThat(autor.getName()).isNotEqualTo(EXPECTED_AUTOR_NAME);

    }

    @Test
    @DisplayName("автор с ид="+EXPECTED_AUTOR_ID + " не должен быть удален, т.к. у книги есть ссылка на этого автора ")
    void shouldNotBeDeleted() {
        assertThatCode(() -> autorRepository.findById(EXPECTED_AUTOR_ID)).doesNotThrowAnyException();
        autorRepository.deleteById(EXPECTED_AUTOR_ID);
        assertThatCode(() ->autorRepository.flush()).withThreadDumpOnError();
        assertThatCode(() -> autorRepository.findById(EXPECTED_AUTOR_ID)).withThreadDumpOnError();

    }

}