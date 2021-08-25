package ru.otus.spring.bookstore.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
@NamedEntityGraph(name="genres-entity-graph", attributeNodes = {@NamedAttributeNode("genre"), @NamedAttributeNode("autor") })
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

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(targetEntity = Note.class, cascade = CascadeType.ALL,  orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name="bookid")
    private List<Note> notes;

}
