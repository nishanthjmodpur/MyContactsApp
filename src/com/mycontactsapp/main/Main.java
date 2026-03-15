package com.mycontactsapp.main;

import java.util.Scanner;

import com.mycontactsapp.registration.Registration;

/*
 * UC01: User Registration
 * 
 * Allow new users to create an account with email, password, and profile info
 * 
 * @author Developer
 * @version 1.0
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
        
		scanner.close();
		
	}
}


