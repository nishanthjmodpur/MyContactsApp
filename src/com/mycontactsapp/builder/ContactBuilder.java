package com.mycontactsapp.builder;

import java.util.*;

import com.mycontactsapp.contact.Contact;
import com.mycontactsapp.factory.ContactFactory;

public class ContactBuilder {
    private String type;
    private String name;
    private List<String> phones = new ArrayList<>();
    private List<String> emails = new ArrayList<>();

    public ContactBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public ContactBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ContactBuilder addPhone(String phone) {
        phones.add(phone);
        return this;
    }

    public ContactBuilder addEmail(String email) {
        emails.add(email);
        return this;
    }

    public Contact build() {
        Contact contact = ContactFactory.createContact(type, name);
        phones.forEach(contact::addPhone);
        emails.forEach(contact::addEmail);
        return contact;
    }

}
