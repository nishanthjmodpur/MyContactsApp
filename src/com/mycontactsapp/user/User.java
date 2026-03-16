package com.mycontactsapp.user;

public abstract class User {
	private String email;
	private String password;
	private String name;
	
	protected User(String email, String password, String name) {
		this.email = email;
		this.password = password;
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
}