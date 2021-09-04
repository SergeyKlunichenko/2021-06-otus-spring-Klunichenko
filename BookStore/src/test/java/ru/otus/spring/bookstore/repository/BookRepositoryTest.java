package ru.otus.spring.bookstore.repository;

import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.config.ImmutableMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.distribution.IFeatureAwareVersion;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.spring.bookstore.model.Autor;
import ru.otus.spring.bookstore.model.Book;
import ru.otus.spring.bookstore.model.Genre;

import java.util.List;
import java.util.Map;
import java.util.OptionalLong;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class BookRepositoryTest {

    private static final int EXPECTED_COUNT_BOOK = 4;
    private static final String EXPECTED_BOOK_NAME = "Пикник на обочине";
    private static final String NEW_BOOK_NAME = "Белая гвардия";
    private static final String NEW_GENRE_NAME = "драма";
    private static final String NEW_AUTOR_NAME = "Булгаков Михаил Афанасьевич";


    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private GenreRepository genreRepository;


    @Test
    public void testDb(@Autowired MongoTemplate mongoTemplate){
        DBObject objectToSave = BasicDBObjectBuilder.start()
                .add("key", "value")
                .get();

        mongoTemplate.save(objectToSave, "collection");

        assertThat(mongoTemplate.findAll(DBObject.class, "collection")).extracting("key")
                .containsOnly("value");

    }

    @Test
    void findAll() {
        List<Book> books = bookRepository.findAll();
        assertThat(books.size()).isEqualTo(EXPECTED_COUNT_BOOK);
    }


    @Test
    void findByName() {
        Book book = bookRepository.findByName(EXPECTED_BOOK_NAME).get(0);
        assertThat(book.getName()).isEqualTo(EXPECTED_BOOK_NAME);
    }


    @Test
    void deleteByName() {
        Book book = bookRepository.findByName(EXPECTED_BOOK_NAME).get(0);
        bookRepository.delete(book);
        Book newBook  = bookRepository.findBookById(book.getId());
        assertThat(newBook).isNull();

        bookRepository.save(book);

    }

    @Test
    @DisplayName("добавит новую книгу")
    void shullAddBook(){
        Autor autor = autorRepository.findByName(NEW_AUTOR_NAME);
        Genre genre = genreRepository.findByName(NEW_GENRE_NAME);
        Book  book  = new Book(NEW_BOOK_NAME, genre, autor);
        book = bookRepository.save(book);
        assertThat(book).usingRecursiveComparison().isEqualTo(bookRepository.findById(book.getId()).get());
        bookRepository.delete(book);
    }
}