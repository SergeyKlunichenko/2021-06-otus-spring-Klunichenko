package ru.otus.spring.autoreport.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import ru.otus.spring.autoreport.exception.AutoReportException;
import ru.otus.spring.autoreport.models.User;
import ru.otus.spring.autoreport.repositories.UserRepository;

import java.util.List;

@Service
public class UserService  {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
        System.out.println("save  encode:"+user.getPassword());
        userRepository.save(user);
    }




}
