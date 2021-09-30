package ru.otus.spring.bookstore.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document (collection = "autors")
@ToString
public class Autor {
    @Id
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Autor() {
    }

    public Autor(String name) {
        this.name = name;
    }

    public Autor(String id, String name) {
        this.id = id;
        this.name = name;
    }

}
