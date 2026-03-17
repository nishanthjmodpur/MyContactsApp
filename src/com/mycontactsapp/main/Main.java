package com.mycontactsapp.main;

import java.util.Scanner;

import com.mycontactsapp.authentication.Authentication;
import com.mycontactsapp.authentication.BasicAuth;
import com.mycontactsapp.authentication.OAuth;
import com.mycontactsapp.authentication.Session;
import com.mycontactsapp.registration.Registration;
import com.mycontactsapp.service.ContactService;
import com.mycontactsapp.service.ProfileService;
import com.mycontactsapp.user.User;

/*
 * UC-07: Delete Contact
 * 
 * Allow new users to create an account with email, password, and profile info
 * Allow logged-in users to update profile details, change passwords, and manage preferences.
 * Allow logged-in users to remove a contact from their list with confirmation.
 * 
 * @author Developer
 * @version 7.0
 */

import java.util.Scanner;

public class Main {
 public static void main(String[] args) {
     Scanner scanner = new Scanner(System.in);
     Registration regService = new Registration();
     ProfileService profileService = new ProfileService();
     ContactService contactService = new ContactService();

     boolean running = true;

     while (running) {
         System.out.println("\n=== MyContactsApp ===");
         System.out.println("1. Register");
         System.out.println("2. Login");
         System.out.println("3. Exit");
         System.out.print("Choose an option: ");
         String choice = scanner.nextLine();

         switch (choice) {
             case "1": // Registration
                 System.out.println("\n--- Registration ---");
                 System.out.print("Enter email: ");
                 String email = scanner.nextLine();
                 System.out.print("Enter password: ");
                 String password = scanner.nextLine();
                 System.out.print("Enter name: ");
                 String name = scanner.nextLine();
                 System.out.print("Enter user type (FREE/PREMIUM): ");
                 String type = scanner.nextLine();

                 try {
                     regService.registerUser(email, password, name, type);
                 } catch (IllegalArgumentException e) {
                     System.out.println("Registration failed: " + e.getMessage());
                 }
                 break;

             case "2": // Login
                 System.out.println("\n--- Login ---");
                 System.out.println("1. BasicAuth (email + password)");
                 System.out.println("2. OAuth (email only)");
                 System.out.print("Choose login method: ");
                 String loginChoice = scanner.nextLine();

                 boolean loggedIn = false;
                 if ("1".equals(loginChoice)) {
                     System.out.print("Enter email: ");
                     String loginEmail = scanner.nextLine();
                     System.out.print("Enter password: ");
                     String loginPassword = scanner.nextLine();
                     Authentication authService = new Authentication(new BasicAuth());
                     loggedIn = authService.login(loginEmail, loginPassword);
                 } else if ("2".equals(loginChoice)) {
                     System.out.print("Enter email: ");
                     String loginEmail = scanner.nextLine();
                     Authentication authService = new Authentication(new OAuth());
                     loggedIn = authService.login(loginEmail, "");
                 }

                 if (loggedIn) {
                     System.out.println("Login successful!");
                     postLoginMenu(scanner, profileService, contactService);
                 } else {
                     System.out.println("Login failed.");
                 }
                 break;

             case "3": // Exit
                 running = false;
                 System.out.println("Exiting MyContactsApp. Goodbye!");
                 break;

             default:
                 System.out.println("Invalid choice. Please try again.");
         }
     }

     scanner.close();
 }

 private static void postLoginMenu(Scanner scanner, ProfileService profileService, ContactService contactService) {
     boolean loggedInMenu = true;
     while (loggedInMenu) {
         User loggedInUser = Session.getSession().getLoggedInUser();
         System.out.println("\n=== Logged-In Menu ===");
         System.out.println("1. Update Profile");
         System.out.println("2. Create Contact");
         System.out.println("3. List Contacts");
         System.out.println("4. View Contact Details");
         System.out.println("5. Edit Contact");
         System.out.println("6. Delete Contact");
         System.out.println("7. Logout");
         System.out.println("8. Exit");
         System.out.print("Choose an option: ");
         String choice = scanner.nextLine();

         switch (choice) {
             case "1":
                 System.out.println("\n--- Profile Update ---");
                 System.out.print("Enter new name (leave blank to skip): ");
                 String newName = scanner.nextLine();
                 if (!newName.isBlank()) profileService.updateName(loggedInUser, newName);

                 System.out.print("Enter new password (leave blank to skip): ");
                 String newPass = scanner.nextLine();
                 if (!newPass.isBlank()) profileService.updatePassword(loggedInUser, newPass);

                 System.out.print("Enter new email (leave blank to skip): ");
                 String newEmail = scanner.nextLine();
                 if (!newEmail.isBlank()) profileService.updateEmail(loggedInUser, newEmail);

                 System.out.println("Updated profile: " + loggedInUser);
                 break;

             case "2":
                 System.out.println("\n--- Create Contact ---");
                 System.out.print("Enter contact type (PERSON/ORG): ");
                 String cType = scanner.nextLine();
                 System.out.print("Enter name: ");
                 String cName = scanner.nextLine();
                 System.out.print("Enter phone: ");
                 String cPhone = scanner.nextLine();
                 System.out.print("Enter email: ");
                 String cEmail = scanner.nextLine();

                 try {
                     contactService.createContact(cType, cName, cPhone, cEmail);
                 } catch (IllegalArgumentException e) {
                     System.out.println("Failed to create contact: " + e.getMessage());
                 }
                 break;

             case "3":
                 contactService.listContacts();
                 break;

             case "4":
                 System.out.print("Enter contact name to view: ");
                 String searchName = scanner.nextLine();
                 contactService.viewContact(searchName);
                 break;

             case "5":
                 System.out.print("Enter contact name to edit: ");
                 String editName = scanner.nextLine();
                 System.out.print("Enter new name (leave blank to skip): ");
                 String editNewName = scanner.nextLine();
                 System.out.print("Enter new phone (leave blank to skip): ");
                 String editNewPhone = scanner.nextLine();
                 System.out.print("Enter new email (leave blank to skip): ");
                 String editNewEmail = scanner.nextLine();
                 contactService.editContact(editName, editNewName, editNewPhone, editNewEmail);
                 break;

             case "6":
                 System.out.print("Enter contact name to delete: ");
                 String delName = scanner.nextLine();
                 contactService.deleteContact(delName);
                 break;

             case "7":
                 new Authentication(new BasicAuth()).logout();
                 loggedInMenu = false;
                 break;

             case "8":
                 System.out.println("Exiting MyContactsApp. Goodbye!");
                 System.exit(0);
                 break;

             default:
                 System.out.println("Invalid choice. Please try again.");
         }
     }
     
     scanner.close();
 }
}

