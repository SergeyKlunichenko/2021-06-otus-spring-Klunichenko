package ru.otus.spring.bookstore.actuators;

import org.apache.logging.log4j.spi.LoggerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;
import ru.otus.spring.bookstore.models.Genre;
import ru.otus.spring.bookstore.repositories.GenreRepository;


@Component
public class GenreHealthInditcator implements HealthIndicator {

    private  final GenreRepository genreRepository;
    private  final Logger logger = LoggerFactory.getLogger(this.getClass());
    public GenreHealthInditcator(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Health health() {
        String name = "контрольный";

        logger.info("Проверка наличия контрольного жанра");

        Genre genre = genreRepository.findByName(name);

        if(genre == null){
            logger.info("Контрольный жанр отсуствует");
            return Health.down()
                    .status(Status.DOWN)
                    .withDetail("message", String.format("Контрольный жанр не найден в списке жанров"))
                    .build();

        }

        logger.info("Контрольный жанр найден");

        return Health.up()
                .status(Status.UP)
                .withDetail("message", "Контрольный жанр найден")
                .build();
    }
}
