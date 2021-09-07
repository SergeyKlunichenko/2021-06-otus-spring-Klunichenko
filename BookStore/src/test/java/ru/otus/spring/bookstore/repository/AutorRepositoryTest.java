package ru.otus.spring.bookstore.repository;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.bookstore.events.AutorEventListener;
import ru.otus.spring.bookstore.model.Autor;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DataMongoTest
@DisplayName("Тестирование авторов")
@Import(AutorEventListener.class)
class AutorRepositoryTest {
    private  final int EXPECTED_COUNT = 2;
    private  final String EXPECTED_AUTOR_NAME= "Стругацкий Аркадий Натанович";
    private  final String NEW_AUTOR_NAME="Лев Николаевич Толстой";

    @Autowired
    public    AutorRepository autorRepository;

    @Test
    @DisplayName("Должны получить  авторов в количестве "+EXPECTED_COUNT + " штук")
    void findAllTest() {
        List<Autor> autors = autorRepository.findAll();
        assertThat(autors.size()).isEqualTo(EXPECTED_COUNT);
    }

    @Test
    @DisplayName("Должен найтись автор "+EXPECTED_AUTOR_NAME)
    void findByNameTest() {

        Autor autor = autorRepository.findByName(EXPECTED_AUTOR_NAME);
        assertThat(autor).isNotNull();
        assertThat(autor.getName()).isEqualTo(EXPECTED_AUTOR_NAME);

    }


    @DisplayName("Автор "+EXPECTED_AUTOR_NAME+" не должен быть удален т.к. имеются его книги")
    @Test
    void shoulNotDeleteAutorBecausThereHisBooks() {
        Autor autor = autorRepository.findByName(EXPECTED_AUTOR_NAME);
        assertThat(autor).isNotNull();
        String id = autor.getId();
        assertThatCode(()->autorRepository.deleteById(id)).hasNoSuppressedExceptions();
    }

    @DisplayName("Автор "+NEW_AUTOR_NAME + " должен удалиться, т.к. у него нет книг")
    @Test
    void shoulDeleteAutorWithEmptyListOfBooks() {
        //добавить автора
        Autor autor = new Autor(NEW_AUTOR_NAME);
        autor = autorRepository.save(autor);
        assertThat(autor.getName()).isEqualTo(NEW_AUTOR_NAME);

        autorRepository.deleteById(autor.getId());
        autor = autorRepository.findByName(NEW_AUTOR_NAME);

        assertThat(autor).isNull();
    }

    @DisplayName("Должен добавиться автор "+NEW_AUTOR_NAME)
    @Test
    void addAutorTest(){
        Autor autor = new Autor(NEW_AUTOR_NAME);
        autor = autorRepository.save(autor);
        assertThat(autorRepository.findByName(NEW_AUTOR_NAME)).usingRecursiveComparison().isEqualTo(autor);
    }

    @Test
    @DisplayName("автор не должен изменится, т.к. в магазине есть книги автора")
    void shouldNotEditAutorBecauseThereIsBooks(){
        Autor autor = autorRepository.findByName(EXPECTED_AUTOR_NAME);
        autor.setName(NEW_AUTOR_NAME);
        assertThatCode(()->autorRepository.save(autor)).hasNoSuppressedExceptions();
        //autorRepository.save(autor);

    }


}