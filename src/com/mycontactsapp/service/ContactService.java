package com.mycontactsapp.service;

import com.mycontactsapp.builder.ContactBuilder;
import com.mycontactsapp.contact.Contact;
import com.mycontactsapp.contact.ContactRepository;

public class ContactService {
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
}
