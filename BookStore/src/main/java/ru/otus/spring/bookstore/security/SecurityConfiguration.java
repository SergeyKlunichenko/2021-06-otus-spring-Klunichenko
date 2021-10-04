package ru.otus.spring.bookstore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    /*
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/");
    }
    */

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                //.authorizeRequests().antMatchers( "/public" ).anonymous()
                //.and()
//                .authorizeRequests().antMatchers( "/", "/book/**", "/autor/**", "/user/**", "/genre/**" ).authenticated()
                .authorizeRequests().antMatchers( "/**").authenticated()

                .and()
                // Включает Form-based аутентификацию
                .formLogin();

    }

    @SuppressWarnings("deprecation")
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }


    @Autowired
    public void configure( AuthenticationManagerBuilder auth ) throws Exception {
        /*
        auth.inMemoryAuthentication()
                .withUser( "admin" ).password( "password" ).roles( "ADMIN" );
        */
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select login, password, 'true' from users where login =?")
                .authoritiesByUsernameQuery("select login, role  from users where login =?");



    }

}
