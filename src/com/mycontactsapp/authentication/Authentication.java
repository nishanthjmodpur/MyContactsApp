package com.mycontactsapp.authentication;

import java.util.List;

import com.mycontactsapp.registration.UserRepository;
import com.mycontactsapp.user.User;

public class Authentication {
	private final AuthenticationStrategy strategy;
	
	public Authentication(AuthenticationStrategy strategy) {
		this.strategy = strategy;
	}
	
	public boolean login(String email, String password) {
		List<User> allUsers = UserRepository.getAllUsers();
		User user = null;
		for (User u : allUsers) {
			if (u.getEmail().equalsIgnoreCase(email)) {
				user = u;
			}
		}
		boolean success = strategy.authenticate(email, password, user);
		if (success) {
			Session.getSession().login(user);
		} else {
			System.out.println("Login failed");
		}
		return success;	
	}
	
	public void logout() {
		Session.getSession().logout();
	}
}
