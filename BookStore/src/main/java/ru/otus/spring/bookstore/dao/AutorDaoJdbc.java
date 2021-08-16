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
import ru.otus.spring.bookstore.exceptions.BookStoreException;

import java.sql.ParameterMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class AutorDaoJdbc implements AutorDao{
    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations jdbcnp;

    public AutorDaoJdbc(NamedParameterJdbcOperations jdbcnp) {
        this.jdbcnp =   jdbcnp;
        this.jdbc   =   jdbcnp.getJdbcOperations();
    }

    @Override
    public int count() {
        return jdbc.queryForObject("select count(*) from autors", Integer.class);
    }

    @Override
    public List<Autor> getAll() {
        return jdbc.query("select id, name from autors", new Mapper());
    }

    @Override
    public Autor findById(long id)  throws BookStoreException{
        try {
            return  jdbcnp.queryForObject("select id, name from autors where id=:id", Map.of("id", id), new Mapper());
        } catch(EmptyResultDataAccessException e){
            throw new BookStoreException("Не найден автор с id %s", id);
        }
    }

    @Override
    public Autor findByName(String name) throws BookStoreException{
        try {
            return jdbcnp.queryForObject("select id, name from autors where name=:name", Map.of("name", name), new Mapper());
        } catch(EmptyResultDataAccessException e){
            throw new BookStoreException("Не найден автор \"%s\"", name);
        }
    }

    @Override
    public long insert(Autor autor) {
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("name", autor.getName());
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcnp.update("insert into autors(name) values ( :name)", params, keyHolder );

        return keyHolder.getKey().longValue();
    }

    @Override
    public void deleteById(long id) {
        jdbcnp.update("delete from autors where id =:id", Map.of("id", id));
    }

    private static class Mapper implements RowMapper<Autor> {

        @Override
        public Autor mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Autor(resultSet.getLong("id"), resultSet.getString("name"));
        }
    }

}
