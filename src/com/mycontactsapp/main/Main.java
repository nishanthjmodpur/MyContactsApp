package com.mycontactsapp.main;

import java.util.Scanner;

import com.mycontactsapp.authentication.Authentication;
import com.mycontactsapp.authentication.BasicAuth;
import com.mycontactsapp.registration.Registration;

/*
 * UC02: User Authentication
 * 
 * Allow new users to create an account with email, password, and profile info
 * 
 * @author Developer
 * @version 2.0
 */

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Registration registration = new Registration();
		
		System.out.println("Enter email:");
        String email = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        System.out.println("Enter name:");
        String name = scanner.nextLine();
        System.out.println("Enter user type (FREE/PREMIUM):");
        String type = scanner.nextLine();
        
        try {
        	registration.registerUser(email, password, name, type);
        } catch (IllegalArgumentException e) {
			System.out.println("Registration failed: " + e.getMessage());
		}
        
        System.out.print("Enter email: ");
        String loginEmail = scanner.nextLine();
        System.out.print("Enter password: ");
        String loginPassword = scanner.nextLine();
        
        Authentication authentication = new Authentication(new BasicAuth());
        boolean login = authentication.login(loginEmail, loginPassword);
        
        if (login) {
        	System.out.println("Login successful");
        } else {
        	System.out.println("Login failed");
        }
        
        authentication.logout();
        
		scanner.close();
		
	}
}


