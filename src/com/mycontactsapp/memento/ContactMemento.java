package com.mycontactsapp.memento;

import java.util.ArrayList;

import com.mycontactsapp.contact.Contact;

public class ContactMemento {
    private final String name;
    private final java.util.List<String> phones;
    private final java.util.List<String> emails;

    public ContactMemento(Contact contact) {
        this.name = contact.getName();
        this.phones = new ArrayList<>(contact.getPhones()); // defensive copy
        this.emails = new ArrayList<>(contact.getEmails());
    }

    public String getName() { return name; }
    public java.util.List<String> getPhones() { return phones; }
    public java.util.List<String> getEmails() { return emails; }
}