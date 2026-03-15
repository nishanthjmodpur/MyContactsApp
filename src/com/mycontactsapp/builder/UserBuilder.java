package com.mycontactsapp.builder;

import com.mycontactsapp.factory.UserFactory;
import com.mycontactsapp.user.User;
import com.mycontactsapp.validation.HashPassword;
import com.mycontactsapp.validation.Validator;

public class UserBuilder {
	private String email;
	private String password;
	private String name;
	
	public UserBuilder setEmail(String email) {
		this.email = email;
		return this;
	}
	
	public UserBuilder setPassword(String password) {
		this.password = password;
		return this;
	}
	
	public UserBuilder setName(String name) {
		this.name = name;
		return this;
	}
	
	public User build(String type) {
		Validator.validateEmail(email);
		Validator.validatePassword(password);
		Validator.validateName(name);
		
		String hashedPassword = HashPassword.hash(password);
		return UserFactory.createUser(type, email, hashedPassword, name);
	}
}
