package com.mycontactsapp.registration;

import com.mycontactsapp.builder.UserBuilder;
import com.mycontactsapp.user.User;

public class Registration {
    public void registerUser(String email, String password, String name, String type) {
        User user = new UserBuilder()
                .setEmail(email)
                .setPassword(password)
                .setName(name)
                .build(type);

        UserRepository.save(user);
        System.out.println("Registration successful: " + user);
    }

}
