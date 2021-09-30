package ru.otus.spring.bookstore.restcontroller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.otus.spring.bookstore.domain.Autor;

@SuppressWarnings("all")
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AutorDto {
    private  String id;
    private  String name;
    public static  Autor toAutor(AutorDto autorDto){
        return new Autor(autorDto.getId(), autorDto.getName() );
    }


}
