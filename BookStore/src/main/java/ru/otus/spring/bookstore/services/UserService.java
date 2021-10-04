package ru.otus.spring.bookstore.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.bookstore.models.User;
import ru.otus.spring.bookstore.repositories.UserRepository;

import java.util.List;

@Service
public class UserService {
    private  final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User findById(long id){
        return userRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public User findByLogin(String login){
        return userRepository.findByLogin(login);
    }


    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

}
