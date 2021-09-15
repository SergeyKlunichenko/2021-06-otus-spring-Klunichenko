package ru.otus.spring.bookstore.rest.dto;

import ru.otus.spring.bookstore.models.Autor;

@SuppressWarnings("all")
public class AutorDto {
    private long id = -1;
    private String name ;

    public AutorDto() {
    }

    public AutorDto(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static AutorDto toDto(Autor autor){
        return new AutorDto(autor.getId(), autor.getName());

    }
}
