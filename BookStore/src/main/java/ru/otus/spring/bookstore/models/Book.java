package ru.otus.spring.bookstore.models;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @ManyToOne(targetEntity = Autor.class)
    @JoinColumn(name="autorid")
    private Autor autor;

    @ManyToOne(targetEntity = Genre.class)
    @JoinColumn(name = "genreid")
    private Genre genre;


}
