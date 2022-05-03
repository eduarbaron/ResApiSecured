package com.projects.rest.api.spring.security.model;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Role {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;	
	
	private String name;
	
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "roles")
	private Set<User> user = new HashSet<>();	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<User> getUser() {
		return user;
	}
	public void setUser(Set<User> user) {
		this.user = user;
	}	

}
