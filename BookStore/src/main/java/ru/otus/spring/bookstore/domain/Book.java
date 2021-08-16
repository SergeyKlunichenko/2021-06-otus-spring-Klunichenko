package ru.otus.spring.bookstore.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Data
public class Book {
    private  final long id;
    private  final String name;
    private  final Autor autor ;
    private  final Genre genre;
}
