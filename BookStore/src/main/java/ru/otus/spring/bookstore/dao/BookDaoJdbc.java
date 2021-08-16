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
    private final AutorDao autorDao;

    public BookDaoJdbc(NamedParameterJdbcOperations jdbcnp,  GenreDao  genreDao, AutorDao autorDao) {
        this.jdbcnp     = jdbcnp;
        this.jdbc       = jdbcnp.getJdbcOperations();
        this.genreDao   = genreDao;
        this.autorDao   = autorDao;
    }

    @Override
    public int count() {
        return jdbc.queryForObject("select count(*) from books", Integer.class);
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query("select id, name, autorid, genreid from books", new Mapper(genreDao, autorDao)) ; //jdbc.query();
    }

    @Override
    public Book findById(long id) throws BookStoreException{
        try {
            return jdbcnp.queryForObject("select id, name, genreid, autorid from books where id = :id", Map.of("id", id), new Mapper(genreDao, autorDao));
        } catch(EmptyResultDataAccessException e){
            throw new BookStoreException("Не найдена книга по id=%s", id);
    }


    }

    @Override
    public long insert(Book book) {

        MapSqlParameterSource params = new MapSqlParameterSource()
                                        .addValue("name", book.getName())
                                        .addValue("autorid", book.getAutor().getId())
                                        .addValue("genreid", book.getGenre().getId());


        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcnp.update("insert into books(name, genreid, autorid) values (:name, :genreid, :autorid)", params, keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public void deleteById(long id) {
        jdbcnp.update("delete from books where id =:id", Map.of("id", id));
    }

    @Override
    public void deleteByName(String name) {
         jdbcnp.update("delete from books where name =:name", Map.of("name", name));
    }

    private static class Mapper implements RowMapper<Book>{
        private final GenreDao genreDao;
        private final AutorDao autorDao;
        public Mapper( GenreDao  genreDao, AutorDao autorDao){
            this.genreDao   = genreDao;
            this.autorDao   = autorDao;
        }

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            Genre genre = null;
            Autor autor = null;
            try {
                genre = genreDao.findById(resultSet.getLong("genreid"));
                autor = autorDao.findById(resultSet.getLong("autorid"));
            } catch (BookStoreException e) {
                e.printStackTrace();
            }
            return new Book(resultSet.getLong("id"), resultSet.getString("name"), autor, genre);
        }
    }

}
