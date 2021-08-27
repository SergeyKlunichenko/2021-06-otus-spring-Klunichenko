package ru.otus.spring.bookstore.repositories;

import ru.otus.spring.bookstore.dto.BookDto;

public interface BookDtoRepository {
    public BookDto findById(long id);

}
