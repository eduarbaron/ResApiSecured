package com.projects.rest.api.spring.security.service;

import java.util.List;

import com.projects.rest.api.spring.security.model.User;

public interface IUserService {
	
	public List<User> getUsers();
	public User addNewUser(User user);
	public Boolean deleteUser(User user);
	public User updateUser(User user);
	public Boolean existUser(User user);
	public User findById(Integer id);

}
