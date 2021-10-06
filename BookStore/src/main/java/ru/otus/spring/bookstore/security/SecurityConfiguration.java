package ru.otus.spring.bookstore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.otus.spring.bookstore.services.UserDetailServiceBS;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private  final  UserDetailServiceBS userDetailServiceBS;

    public SecurityConfiguration(UserDetailServiceBS userDetailServiceBS) {
        this.userDetailServiceBS = userDetailServiceBS;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers("/").hasAnyRole("READER", "MANAGER")
                .and()
                .authorizeRequests().antMatchers("/editBook").hasAnyRole("MANAGER")
                .and()
                .authorizeRequests().antMatchers("/deleteBook").hasAnyRole("MANAGER")
                .and()
                .authorizeRequests().antMatchers("/users").hasAnyRole("ADMIN")
                .and()
                .authorizeRequests().antMatchers("/autors").hasAnyRole("MANAGER")
                .and()
                .authorizeRequests().antMatchers("/editAutor").hasAnyRole("MANAGER")
                .and()
                .authorizeRequests().antMatchers("/deleteAutor").hasAnyRole("MANAGER")
                .and()
                .authorizeRequests().antMatchers("/genres").hasAnyRole("MANAGER")
                .and()
                .authorizeRequests().antMatchers("/deleteGenre").hasAnyRole("MANAGER")
                .and()
                .authorizeRequests().antMatchers("/editGenre").hasAnyRole("MANAGER")
                .and()
                .authorizeRequests().antMatchers("/note").hasAnyRole("READER")
                .and()
                .formLogin()
                .and()
                .logout().logoutUrl("/logout");

    }

    @SuppressWarnings("deprecation")
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }


    @Autowired
    public void configure( AuthenticationManagerBuilder auth ) throws Exception {

        auth.userDetailsService(userDetailServiceBS);




    }

}
