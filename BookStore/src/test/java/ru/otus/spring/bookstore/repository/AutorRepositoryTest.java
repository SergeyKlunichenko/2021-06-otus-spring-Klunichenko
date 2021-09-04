package ru.otus.spring.bookstore.repository;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
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
    private   BookRepository bookRepository;
    @Autowired
    public    AutorRepository autorRepository;

    @Test
    @DisplayName("Должны получить  авторов в количестве "+EXPECTED_COUNT + " штук")
    @Order(1)
    void findAllTest() {
        List<Autor> autors = autorRepository.findAll();
        assertThat(autors.size()).isEqualTo(EXPECTED_COUNT);
    }

    @Test
    @DisplayName("Должен найтись автор "+EXPECTED_AUTOR_NAME)
    @Order(2)
    void findByNameTest() {

        Autor autor = autorRepository.findByName(EXPECTED_AUTOR_NAME);
        assertThat(autor).isNotNull();
        assertThat(autor.getName()).isEqualTo(EXPECTED_AUTOR_NAME);

    }
    @DisplayName("Автор "+EXPECTED_AUTOR_NAME+" не должен быть удален т.к. имеются его книги")
    @Test
    @Order(3)
    void shoulNotDeleteAutorBecausThereHisBooks() {
        Autor autor = autorRepository.findByName(EXPECTED_AUTOR_NAME);
        assertThat(autor).isNotNull();
        String id = autor.getId();
        assertThatCode(()->autorRepository.deleteById(id)).hasNoSuppressedExceptions();
    }

    @DisplayName("Автор "+NEW_AUTOR_NAME + " должен удалиться, т.к. у него нет книг")
    @Test
    @Order(4)
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
    @Order(5)
    void addAutorTest(){
        Autor autor = new Autor(NEW_AUTOR_NAME);
        autor = autorRepository.save(autor);
        assertThat(autorRepository.findByName(NEW_AUTOR_NAME)).usingRecursiveComparison().isEqualTo(autor);
    }


}