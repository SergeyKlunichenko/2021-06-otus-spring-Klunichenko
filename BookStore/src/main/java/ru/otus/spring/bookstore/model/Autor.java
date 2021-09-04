package ru.otus.spring.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "autors")
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

    public Autor(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
