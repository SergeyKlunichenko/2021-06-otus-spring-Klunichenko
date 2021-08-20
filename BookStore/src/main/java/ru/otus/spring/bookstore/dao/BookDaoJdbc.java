package ru.otus.spring.bookstore.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.bookstore.domain.Autor;
import ru.otus.spring.bookstore.domain.Book;
import ru.otus.spring.bookstore.domain.Genre;
import ru.otus.spring.bookstore.exceptions.BookStoreException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class BookDaoJdbc implements BookDao{
    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations jdbcnp;
    private final GenreDao genreDao;

    public BookDaoJdbc(NamedParameterJdbcOperations jdbcnp,  GenreDao  genreDao){  //, AutorDao autorDao) {
        this.jdbcnp     = jdbcnp;
        this.jdbc       = jdbcnp.getJdbcOperations();
        this.genreDao   = genreDao;
    }

    @Override
    public int count() {
        return jdbc.queryForObject("select count(*) from books", Integer.class);
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query("select b.id, b.name, b.genreid,  g.name genre, b.autorid autorid , a.name autor" +
                            "  from books b, genres g , autors a" +
                            "  where   g.id = b.genreid" +
                            "      and a.id = b.autorid", new Mapper()) ; //jdbc.query();
    }

    @Override
    public Book findById(long id) throws BookStoreException{
        try {
            return jdbcnp.queryForObject("select b.id, b.name, b.genreid,  g.name genre, b.autorid autorid , a.name autor" +
                    "  from books b, genres g , autors a" +
                    "  where    g.id = b.genreid" +
                    "       and a.id = b.autorid" +
                    "       and b.id = :id", Map.of("id", id), new Mapper());
        } catch(EmptyResultDataAccessException e){
            throw new BookStoreException("Не найдена книга по id=%s", id);
        }
    }

    @Override
    public Book findByName(String name) throws BookStoreException {
        try {
            return jdbcnp.queryForObject("" +
                    "select b.id, b.name, b.genreid,  g.name genre, b.autorid autorid , a.name autor" +
                    "  from books b" +
                    "  join genres g on g.id = b.genreid" +
                    "  join autors a on a.id = b.autorid" +
                    "  where  b.name = :name"
                    , Map.of("name", name), new Mapper());
        } catch(EmptyResultDataAccessException e){
            throw new BookStoreException("Не найдена книга \"%s\"", name);
        }
    }

    @Override
    public Book insert(Book book) throws BookStoreException{

        MapSqlParameterSource params = new MapSqlParameterSource()
                                        .addValue("name", book.getName())
                                        .addValue("autorid", book.getAutor().getId())
                                        .addValue("genreid", book.getGenre().getId());


        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcnp.update("insert into books(name, genreid, autorid) values (:name, :genreid, :autorid)", params, keyHolder);

        book = findById(keyHolder.getKey().longValue());

        return book;
    }

    @Override
    public Book update(Book book) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", book.getId())
                .addValue("name", book.getName())
                .addValue("autorid", book.getAutor().getId())
                .addValue("genreid", book.getGenre().getId());


        jdbcnp.update("update books set name=:name, autorid=:autorid, genreid=:genreid where id = :id", params);

        return book;
    }

    @Override
    public void deleteById(long id) throws BookStoreException{
        jdbcnp.update("delete from books where id =:id", Map.of("id", id));
    }

    @Override
    public void deleteByName(String name) throws BookStoreException{
        jdbcnp.update("delete from books where name =:name", Map.of("name", name));
    }

    private static class Mapper implements RowMapper<Book>{

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            Genre genre = new Genre(resultSet.getLong("genreid"), resultSet.getString("genre"));//genreDao.findById(resultSet.getLong("genreid"));
            Autor autor = new Autor(resultSet.getLong("autorid"), resultSet.getString("autor"));
            return new Book(resultSet.getLong("id"), resultSet.getString("name"), autor, genre);
        }
    }

}
