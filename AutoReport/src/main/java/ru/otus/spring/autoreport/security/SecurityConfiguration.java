package ru.otus.spring.autoreport.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final CustomAuthenticationProvider authenticationProvider;
    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfiguration(CustomAuthenticationProvider authenticationProvider, CustomUserDetailsService customUserDetailsService) {
        this.authenticationProvider = authenticationProvider;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .authorizeRequests().antMatchers("/").hasAnyRole("READER")
                .and()
                .authorizeRequests().antMatchers("/user/**").hasAnyRole("ADMIN")
                .and()
                .formLogin()
                ;
    }

    @Override
    public void configure( AuthenticationManagerBuilder auth ) throws Exception {
        auth.userDetailsService(customUserDetailsService);
        auth.authenticationProvider(authenticationProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder;
    }
}
