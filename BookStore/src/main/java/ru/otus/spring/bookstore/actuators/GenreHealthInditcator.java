package ru.otus.spring.bookstore.actuators;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;
import ru.otus.spring.bookstore.models.Genre;
import ru.otus.spring.bookstore.repositories.GenreRepository;


@Component
public class GenreHealthInditcator implements HealthIndicator {

    private  final GenreRepository genreRepository;

    public GenreHealthInditcator(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Health health() {
        String name = "контрольный";

        Genre genre = genreRepository.findByName(name);

        if(genre == null){
            return Health.down()
                    .status(Status.DOWN)
                    .withDetail("message", String.format("Контрольный жанр не найден в списке жанров"))
                    .build();

        }

        return Health.up()
                .status(Status.UP)
                .withDetail("message", "Контрольный жанр найден")
                .build();
    }
}
