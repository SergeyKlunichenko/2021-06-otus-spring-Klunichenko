package ru.otus.spring.bookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.bookstore.models.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
    User save(User user);
    User findById(long id);
    User findByLogin(String login);
}
