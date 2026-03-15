package com.mycontactsapp.authentication;

import com.mycontactsapp.user.User;

public interface AuthenticationStrategy {
	boolean authenticate(String email, String password, User user);
}
