package com.projects.rest.api.spring.security.security.service.impl;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.projects.rest.api.spring.security.model.Role;
import com.projects.rest.api.spring.security.model.User;
import com.projects.rest.api.spring.security.repository.IUserRepository;
import com.projects.rest.api.spring.security.security.models.MyUserDetails;

@Service("myUserDetailSerice")
public class MyUserDetailsService implements UserDetailsService {

	Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

	@Autowired
	private IUserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User usuario = userRepo.findByUsername(username);
		Set<Role> roles = userRepo.findRolesByUser(usuario.getId());		
		usuario.setRoles(roles);		
		MyUserDetails userDetail = new MyUserDetails(usuario);
	
		return userDetail;
	}

}
