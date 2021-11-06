package ru.otus.spring.autoreport.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.otus.spring.autoreport.security.CustomAuthenticationProvider;
import ru.otus.spring.autoreport.security.CustomUserDetailsService;

@EnableWebSecurity
public class SecurityConfiguration {

    //private  final CustomUserDetailsService userDetailService;
    @Autowired
    private CustomAuthenticationProvider authenticationProvider;
    @Autowired
    private   BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private   CustomUserDetailsService customUserDetailsService;

    public void configure(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .authorizeRequests().antMatchers("/").hasAnyRole("ADMIN")
                .and()
                .authorizeRequests().antMatchers("/useredit").hasAnyRole("ADMIN")
                .and()
                .formLogin()
                ;
    }

    @Autowired
    public void configure( AuthenticationManagerBuilder auth ) throws Exception {
        authenticationProvider.setbCryptPasswordEncoder(bCryptPasswordEncoder);
        auth.userDetailsService(customUserDetailsService);
        auth.authenticationProvider(authenticationProvider);
    }


    @SuppressWarnings("deprecation")
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
