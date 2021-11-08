package ru.otus.spring.autoreport.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.autoreport.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
    User findByLogin(String login);
}
