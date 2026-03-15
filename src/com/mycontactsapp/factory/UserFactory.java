package com.mycontactsapp.factory;

import com.mycontactsapp.user.FreeUser;
import com.mycontactsapp.user.PremiumUser;
import com.mycontactsapp.user.User;

public class UserFactory {
	public static User createUser(String type, String email, String password, String name) {
		if ("FREE".equalsIgnoreCase(type)) {
			return new FreeUser(email, password, name);
		}
		return new PremiumUser(email, password, name);
	}
}
