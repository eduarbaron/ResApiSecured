package com.projects.rest.api.spring.security.security.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.projects.rest.api.spring.security.model.Role;
import com.projects.rest.api.spring.security.model.User;
import com.projects.rest.api.spring.security.security.service.impl.MyUserDetailsService;

public class MyUserDetails implements UserDetails {
	
	Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

	private static final long serialVersionUID = 1L;
	
	private String Username;
	private String Password;
	private List<GrantedAuthority> authorities;
	

	public MyUserDetails(User user) {
		this.Username = user.getUsername();
		this.Password = user.getPassword();
		this.authorities = buildAuthorities(user.getRoles());
	}
	
	private List<GrantedAuthority> buildAuthorities(Set<Role> userRoles) {
		
		List<GrantedAuthority> userAuthorities = new ArrayList<>();
		
		for (Role r : userRoles) {
			userAuthorities.add(new	SimpleGrantedAuthority(r.getName())); 
		}
		
		return userAuthorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return Password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return Username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
