package ru.otus.bookstore.springbatch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="books")
public class Book {
    @Id
    private Long id;
    private String name;

    @ManyToOne(targetEntity = Autor.class)
    @JoinColumn(name="autorid")
    private Autor autor;

    @ManyToOne(targetEntity = Genre.class)
    @JoinColumn(name = "genreid")
    private Genre genre;

}
