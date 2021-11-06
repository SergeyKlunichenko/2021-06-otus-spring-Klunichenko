package ru.otus.spring.autoreport.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.otus.spring.autoreport.models.User;
import ru.otus.spring.autoreport.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private  final UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userRepository.findByLogin(s);
        if(user == null){
            throw new UsernameNotFoundException("Не найден пользователь: " + s);
        }

        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(user.getLogin())
                .password(user.getPassword())
                .roles(user.getRoles())
                .build();

        return userDetails;
    }
}
