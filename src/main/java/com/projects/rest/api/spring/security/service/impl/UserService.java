package com.projects.rest.api.spring.security.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.projects.rest.api.spring.security.model.User;
import com.projects.rest.api.spring.security.repository.IUserRepository;
import com.projects.rest.api.spring.security.service.IUserService;

public class UserService implements IUserService {
	
	@Autowired
	private IUserRepository userRepo;
	
	@Autowired
	private BCryptPasswordEncoder bcryp;

	@Override
	public List<User> getUsers() {
		Iterable<User> users = userRepo.findAll();
		List<User> list = new ArrayList<User>();
        users.forEach(list::add);
		return list;
	}

	@Override
	public User addNewUser(User user) {		
		user.setPassword(bcryp.encode(user.getPassword()));		
		return userRepo.save(user);
	}

	@Override
	public Boolean deleteUser(User user) {	
		
		if (existUser(user)) {
			userRepo.delete(user);
			return true;
		}else {
			return false;
		}		
		
	}

	@Override
	public User updateUser(User user) {
		return userRepo.save(user);
	}

	@Override
	public Boolean existUser(User user) {
		if (userRepo.existsById(user.getId())) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public User findById(Integer id) {		
		Optional<User> userOpentional = userRepo.findById(id);		
		if (userOpentional.isPresent()) {
			User user = userOpentional.get();
			return user;
		}		
		return new User();
	}

}
