package com.mycontactsapp.authentication;

import com.mycontactsapp.user.User;

public class OAuth implements AuthenticationStrategy {
	@Override
	public boolean authenticate(String email, String password, User user) {
		if (user != null && user.getEmail().equalsIgnoreCase(email)) {
			return true;
		}
		return false;
	}
}
