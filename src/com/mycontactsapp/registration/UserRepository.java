package com.mycontactsapp.registration;

import java.util.ArrayList;
import java.util.List;

import com.mycontactsapp.user.User;


public class UserRepository {

    private static final List<User> users = new ArrayList<>();

    public static void save(User user) {
        users.add(user);
        System.out.println("User saved to repository.");
    }

    public static List<User> getAllUsers() {
        return users;
    }
}
