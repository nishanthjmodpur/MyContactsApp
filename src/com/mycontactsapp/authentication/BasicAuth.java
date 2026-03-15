package com.mycontactsapp.authentication;

import com.mycontactsapp.user.User;
import com.mycontactsapp.validation.HashPassword;

public class BasicAuth implements AuthenticationStrategy {
	@Override
	public boolean authenticate(String email, String password, User user) {
		if (user == null) return false;
		if (!user.getEmail().equalsIgnoreCase(email)) return false;
		return HashPassword.matches(password, user.getPassword());
	}
}
