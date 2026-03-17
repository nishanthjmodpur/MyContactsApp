package com.mycontactsapp.service;

import java.util.Optional;
import java.util.Scanner;

import com.mycontactsapp.builder.ContactBuilder;
import com.mycontactsapp.command.CommandManager;
import com.mycontactsapp.command.EditContactCommand;
import com.mycontactsapp.contact.Contact;
import com.mycontactsapp.contact.ContactRepository;
import com.mycontactsapp.decorator.BasicContactDisplay;
import com.mycontactsapp.decorator.ContactDisplay;
import com.mycontactsapp.decorator.PrettyContactDisplay;
import com.mycontactsapp.observer.ContactDeletionObserver;
import com.mycontactsapp.observer.LoggingObserver;

public class ContactService {
	private final ContactDeletionObserver observer = new LoggingObserver();
    public void createContact(String type, String name, String phone, String email) {
        Contact contact = new ContactBuilder()
                .setType(type)
                .setName(name)
                .addPhone(phone)
                .addEmail(email)
                .build();

        ContactRepository.save(contact);
        System.out.println("Created contact: " + contact);
    }

    public void listContacts() {
        System.out.println("\n--- All Contacts ---");
        ContactRepository.getAllContacts().forEach(System.out::println);
    }
    
    public void viewContact(String name) {
        Optional<Contact> contactOpt = ContactRepository.getAllContacts().stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .findFirst();

        if (contactOpt.isPresent()) {
            Contact contact = contactOpt.get();
            ContactDisplay display = new PrettyContactDisplay(new BasicContactDisplay());
            System.out.println(display.format(contact));
        } else {
            System.out.println("Contact not found: " + name);
        }
    }
    
    
    public void editContact(String name, String newName, String newPhone, String newEmail) {
        Optional<Contact> contactOpt = ContactRepository.getAllContacts().stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .findFirst();

        if (contactOpt.isPresent()) {
            Contact contact = contactOpt.get();
            CommandManager manager = new CommandManager();
            EditContactCommand cmd = new EditContactCommand(contact, newName, newPhone, newEmail);
            manager.executeCommand(cmd);
        } else {
            System.out.println("Contact not found: " + name);
        }
    }
    
    public void deleteContact(String name) {
        Optional<Contact> contactOpt = ContactRepository.getAllContacts().stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .findFirst();

        if (contactOpt.isPresent()) {
            Contact contact = contactOpt.get();
            Scanner scanner = new Scanner(System.in);
            System.out.print("Are you sure you want to delete " + contact.getName() + "? (yes/no): ");
            String confirm = scanner.nextLine();

            if ("yes".equalsIgnoreCase(confirm)) {
                boolean removed = ContactRepository.delete(contact);
                if (removed) {
                    observer.onContactDeleted(contact);
                    System.out.println("Contact deleted successfully.");
                } else {
                    System.out.println("Failed to delete contact.");
                }
            } else {
                System.out.println("Deletion cancelled.");
            }
        } else {
            System.out.println("Contact not found: " + name);
        }
    }
}
