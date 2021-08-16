package ru.otus.spring.bookstore.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Autor {
    private final long id;
    private final String name;

}
