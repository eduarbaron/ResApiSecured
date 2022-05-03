package com.projects.rest.api.spring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.projects.rest.api.spring.security.service.IUserService;
import com.projects.rest.api.spring.security.service.impl.UserService;

@Configuration
public class RestApiConfiguration {
	
	@Bean
	public IUserService userServiceBean() {
		UserService userService = new UserService();
		return userService;		
	}	
}
