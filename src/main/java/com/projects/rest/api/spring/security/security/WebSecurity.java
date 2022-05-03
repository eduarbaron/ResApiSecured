package com.projects.rest.api.spring.security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.projects.rest.api.spring.security.security.service.impl.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	@Autowired
	@Qualifier("myUserDetailSerice")
	private MyUserDetailsService userDetailService;
		
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			/*.authorizeRequests()
			.anyRequest()
			.authenticated()
			.and()
			.formLogin();
			.csrf() 
			.disable()*/
			.authorizeRequests()
			.antMatchers("/users").hasAuthority("ADMIN")
			.antMatchers("/delete").hasAnyAuthority("USER", "ADMIN")
			.antMatchers("/find/**").hasAnyAuthority("USER", "ADMIN")
			.antMatchers("/update").hasAuthority("USER")
			.antMatchers("/home").permitAll()
			.antMatchers("/add").permitAll()
            .and()
            .formLogin();
		
//		.csrf().disable().authorizeRequests().anyRequest().permitAll();

	}
	
	@Bean 
	public BCryptPasswordEncoder passwordEncoder() { 
		BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder(); 
		return bcryptPasswordEncoder; 
	}
		

}
