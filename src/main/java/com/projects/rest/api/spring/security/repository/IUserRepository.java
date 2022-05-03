package com.projects.rest.api.spring.security.repository;


import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.projects.rest.api.spring.security.model.Role;
import com.projects.rest.api.spring.security.model.User;

public interface IUserRepository extends JpaRepository<User, Integer>{
	
	public User findByUsername(String username);
		
	@Query("SELECT u.roles FROM User u JOIN u.roles WHERE u.id = ?1")
	public Set<Role> findRolesByUser(Integer id);
	

}
