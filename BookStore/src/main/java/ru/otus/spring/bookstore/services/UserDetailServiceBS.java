package ru.otus.spring.bookstore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.otus.spring.bookstore.models.User;
import ru.otus.spring.bookstore.repositories.UserRepository;

@Service
public class UserDetailServiceBS implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;

    public UserDetailServiceBS(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);

        if(user == null){
            throw new UsernameNotFoundException("Не найден пользователь: "+login);
        }

        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(user.getLogin())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();

        return userDetails;
    }
}
