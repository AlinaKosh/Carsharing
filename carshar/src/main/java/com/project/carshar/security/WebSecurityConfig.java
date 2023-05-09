package com.project.carshar.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final DataSource dataSource;

	@Value("${spring.queries.users-query}")
	private String usersQuery;

	@Value("${spring.queries.roles-query}")
	private String rolesQuery;

	//private final UserDetailsService userDetailsService;

	private final PasswordEncoder psw;

	//настраивает аутентификацию
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().usersByUsernameQuery(usersQuery).authoritiesByUsernameQuery(rolesQuery)
				.dataSource(dataSource).passwordEncoder(psw);
	}
	//usersByUsernameQuery - получение учётной записи пользователя по имени пользователя и authoritiesByUsernameQuery
	//получение роли пользователя
	/*
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	 */

	/*
	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}
	*/


	@Bean
	public static PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder(8);
	}



	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().
				authorizeRequests().antMatchers("/login*","/registration*").anonymous()
				.and()
				.formLogin().loginPage("/login").permitAll()
				.failureUrl("/login?error=true").defaultSuccessUrl("/profile")
				.usernameParameter("email").passwordParameter("password")
				.and()
				.logout().logoutSuccessUrl("/").and().exceptionHandling().accessDeniedPage("/access-denied");
	}

}