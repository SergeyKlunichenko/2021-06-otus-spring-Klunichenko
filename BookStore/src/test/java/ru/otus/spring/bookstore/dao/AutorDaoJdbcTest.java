package ru.otus.spring.bookstore.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import ru.otus.spring.bookstore.domain.Autor;
import ru.otus.spring.bookstore.exceptions.BookStoreException;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


@DisplayName("Тестирование авторов должно будет")
@JdbcTest
@Import(AutorDaoJdbc.class)
class AutorDaoJdbcTest {

    public static final int ACTUAL_AUTOR_COUNT=2;
    public static final String INSERT_AUTOR_NAME="Тургенев Иван Сергеевич";
    public static final String ACTUAL_AUTOR_NAME="Булгаков Михаил Афанасьевич";
    public static final String ACTUAL_AUTOR_NAME2="Стругацкий Аркадий Натанович";


    @Autowired
    private AutorDao autorDao;

    @DisplayName("возвращать "+ACTUAL_AUTOR_COUNT+" автора")
    @Test
    void countTest() {
        assertThat(ACTUAL_AUTOR_COUNT).isEqualTo(autorDao.count());

    }

    @DisplayName("получить список из двух авторов " + ACTUAL_AUTOR_NAME + " и "+ACTUAL_AUTOR_NAME2)
    @Test
    void getAllTest() throws BookStoreException{
        List<Autor> autorsList = autorDao.getAll();
        assertThat(autorsList).containsExactlyInAnyOrder(autorDao.findByName(ACTUAL_AUTOR_NAME), autorDao.findByName(ACTUAL_AUTOR_NAME2));

    }


    @DisplayName("найти автора по имени "+ACTUAL_AUTOR_NAME)
    @Test
    void findByNameTest() throws Exception{
        assertThatCode(()->autorDao.findByName(ACTUAL_AUTOR_NAME)).doesNotThrowAnyException();
        assertThat(autorDao.findByName(ACTUAL_AUTOR_NAME).getName()).isEqualTo(ACTUAL_AUTOR_NAME);

    }

    @DisplayName("добавлять автора "+INSERT_AUTOR_NAME+"в БД")
    @Test
    void insertTest() throws BookStoreException {
        var expectedAutor = new Autor(0, "Тургенев Иван Сергеевич" );
        long id = autorDao.insert(expectedAutor);
        expectedAutor =  new Autor(id, expectedAutor.getName());
        assertThat(autorDao.findById(id)).usingRecursiveComparison().isEqualTo(expectedAutor);

    }
    @DisplayName("вернуть ожидаемого автора "+ACTUAL_AUTOR_NAME)
    @Test
    void findByIdTest() throws BookStoreException{
        assertThatCode(()->autorDao.findByName(ACTUAL_AUTOR_NAME)).doesNotThrowAnyException();
        Autor expectedAutor = autorDao.findByName(ACTUAL_AUTOR_NAME);
        expectedAutor = new Autor(expectedAutor.getId(), ACTUAL_AUTOR_NAME);

        assertThat(autorDao.findById(expectedAutor.getId())).usingRecursiveComparison().isEqualTo(expectedAutor);

    }

    @DisplayName("Удалить автора "+ACTUAL_AUTOR_NAME)
    @Test
    void deleteByIdTest() throws  BookStoreException{
        assertThatCode(()->autorDao.findByName(ACTUAL_AUTOR_NAME)).doesNotThrowAnyException();
        Autor autor = autorDao.findByName(ACTUAL_AUTOR_NAME);
        autorDao.deleteById(autor.getId());
        assertThatCode(()->autorDao.findById(autor.getId())).withThreadDumpOnError();

    }
}