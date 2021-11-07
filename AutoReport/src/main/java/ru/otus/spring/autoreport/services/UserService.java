package ru.otus.spring.autoreport.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.autoreport.exception.AutoReportException;
import ru.otus.spring.autoreport.models.User;
import ru.otus.spring.autoreport.repositories.UserRepository;

import java.util.List;

@Service
public class UserService  {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<User> findAll(){
        return  userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User  findById(long id){
        User user = userRepository.findById(id).orElseThrow(()-> new AutoReportException("Пользователь с id %s не найден", id));
        return user;
    }

    @Transactional
    public void save(User user){
        userRepository.save(user);
    }

    @Transactional
    public void deleteById(long id){
        userRepository.deleteById(id);
    }

}
