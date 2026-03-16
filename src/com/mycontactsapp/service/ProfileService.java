package com.mycontactsapp.service;

import com.mycontactsapp.user.User;
import com.mycontactsapp.validation.Validator;

public class ProfileService {
	public void updateName(User user, String newName) {
		Validator.validateName(newName);
		user.setName(newName);
	}
	
	public void updatePassword(User user, String newPassword) {
		Validator.validatePassword(newPassword);
		user.setPassword(newPassword);
	}
	
	public void updateEmail(User user, String newEmail) {
		Validator.validateEmail(newEmail);
		user.setEmail(newEmail);
	}
}
