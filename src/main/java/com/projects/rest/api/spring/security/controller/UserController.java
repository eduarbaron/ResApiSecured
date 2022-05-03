package com.projects.rest.api.spring.security.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projects.rest.api.spring.security.model.User;
import com.projects.rest.api.spring.security.service.IUserService;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.HttpStatus;

@RestController
public class UserController {
	
	@Autowired
	@Qualifier(value = "userServiceBean")
	private IUserService userServ;
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ResponseEntity<String> Home(){		
		return new ResponseEntity<String>("Welcome a home page aplication", HttpStatus.OK);		
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<String> AddNewUser(@Validated @RequestBody User user) {
				
		userServ.addNewUser(user);
		
		return new ResponseEntity<String>("User " + user.getUsername() + " added", HttpStatus.CREATED);		
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUsers(){		
		List<User> userList = userServ.getUsers();
		return new ResponseEntity<List<User>>(userList, HttpStatus.OK);		
	}
	
	@RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> findUserById(@PathVariable("id") Integer id){		
		User user = userServ.findById(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);		
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteUser(@Validated @RequestBody User user){
		if (userServ.deleteUser(user)) {
			return new ResponseEntity<String>("User " + user.getUsername() + " deleted", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("User " + user.getUsername() + " not deleted", HttpStatus.NOT_FOUND);
		}
		
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<String> UpdateUser(@Validated @RequestBody User user) {
				
		if (userServ.existUser(user)) {
			userServ.updateUser(user);
			return new ResponseEntity<String>("User " + user.getUsername() + " updated", HttpStatus.OK);		
		} else {
			return new ResponseEntity<String>("User " + user.getUsername() + " not updated", HttpStatus.NOT_FOUND);
		}	
		
	}

	public void setUserServ(IUserService userServ) {
		this.userServ = userServ;
	}	
	
}
