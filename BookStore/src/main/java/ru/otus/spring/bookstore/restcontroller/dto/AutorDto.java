package ru.otus.spring.bookstore.restcontroller.dto;

import ru.otus.spring.bookstore.domain.Autor;

@SuppressWarnings("all")
public class AutorDto {
    private  String id;
    private  String name;

    public AutorDto(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public AutorDto() {
    }

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

    public static  Autor toAutor(AutorDto autorDto){
        return new Autor(autorDto.getId(), autorDto.getName() );
    }



/*
    public  final AutorDto toDto(Mono<Autor> mAutor){

        AtomicReference<AutorDto> autorDto ;
        mAutor.subscribe(value->{
            autorDto.set(new AutorDto(value.getId(), value.getName()));
        });
        return new AutorDto(autor.getId(), autor.getName())
    }
 */
}
