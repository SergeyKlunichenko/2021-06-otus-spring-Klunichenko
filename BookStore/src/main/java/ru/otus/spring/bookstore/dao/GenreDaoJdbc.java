package ru.otus.spring.bookstore.dao;

import org.springframework.boot.autoconfigure.batch.BatchProperties;
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
public class GenreDaoJdbc implements GenreDao{
    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations jdbcnp;

    public GenreDaoJdbc(NamedParameterJdbcOperations jdbcnp) {
        this.jdbcnp =   jdbcnp;
        this.jdbc   =   jdbcnp.getJdbcOperations();
    }

    @Override
    public int count() {
        return jdbc.queryForObject("select count(*) from genres", Integer.class);
    }

    @Override
    public List<Genre> getAll() {
        return jdbc.query("select id, name from genres", new Mapper());
    }

    @Override
    public Genre findById(long id)  throws BookStoreException {
        try {
            return  jdbcnp.queryForObject("select id, name from genres where id=:id", Map.of("id", id), new Mapper());
        } catch(EmptyResultDataAccessException e){
            throw new BookStoreException("Не найден жанр по id=%s", id);
        }

    }

    @Override
    public Genre findByName(String name) throws  BookStoreException{
        try {
            return  jdbcnp.queryForObject("select id, name from genres where name=:name", Map.of("name", name), new Mapper());
        } catch(EmptyResultDataAccessException e){
            throw new BookStoreException("Не найден жанр \"%s\"", name);
        }
    }

    @Override
    public long insert(Genre genre) {

        MapSqlParameterSource params = new MapSqlParameterSource().addValue("name", genre.getName());
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcnp.update("insert into genres(name) values ( :name)", params, keyHolder );

        return keyHolder.getKey().longValue();
    }

    @Override
    public void deleteById(long id) {
        jdbcnp.update("delete from genres where id =:id", Map.of("id", id));
    }


    private static class Mapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Genre(resultSet.getLong("id"), resultSet.getString("name"));
        }
    }




}
