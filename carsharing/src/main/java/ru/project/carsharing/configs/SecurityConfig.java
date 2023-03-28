package ru.project.carsharing.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

    /*
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{


        http.
                authorizeRequests()
             //   .antMatchers("/people/registration", "/people/login/*").not().fullyAuthenticated()
           //     .antMatchers("admin/**", "/admin").hasRole("ADMIN")
                .antMatchers("account/**").hasAnyRole()
                .antMatchers("/static/**", "/people/registration", "/people/login", "/people/userInfo").permitAll()
                //.antMatchers("/", "events/**", "/events", "/static/**").permitAll()
                .anyRequest().authenticated()
            .and()
                .formLogin()
                .loginPage("/people/login")
                .loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/people/userInfo", true)
                .permitAll()
            .and()
                .logout()
                .logoutSuccessUrl("/logout/success")
            .permitAll();

        return http.build();
    }

     */

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{


        http.authorizeRequests()
                .antMatchers("/people/registration", "/people/login/*").not().fullyAuthenticated()
                .antMatchers("admin/**", "/admin").hasRole("ADMIN")
                .antMatchers("account/**").hasAnyRole()
                .antMatchers("/", "/people/userInfo/**", "/people/userInfo", "/static/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/people/login")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/logout/success")
                .permitAll();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(8);
    }
}
