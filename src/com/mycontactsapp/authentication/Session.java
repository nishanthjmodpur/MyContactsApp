package com.mycontactsapp.authentication;

import com.mycontactsapp.user.User;

public class Session {
	private static Session session;
	private User user;
	
	private Session() {}
	
	public static Session getSession() {
		if (session == null) {
			session = new Session();
		}
		return session;
	}
	
	public void login(User user) {
		this.user = user;
		System.out.println("Log in successful");
	}
	
	public void logout() {
		this.user = null;
		System.out.println("Log out successful");
	}
	
	public User getLoggedInUser() {
		return user;
	}
	

}
