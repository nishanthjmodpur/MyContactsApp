package com.mycontactsapp.factory;

import com.mycontactsapp.contact.Contact;
import com.mycontactsapp.contact.OrganizationContact;
import com.mycontactsapp.contact.PersonContact;

public class ContactFactory {
	public static Contact createContact(String type, String name) {
        if ("PERSON".equalsIgnoreCase(type)) {
            return new PersonContact(name);
        } else if ("ORG".equalsIgnoreCase(type) || "ORGANIZATION".equalsIgnoreCase(type)) {
            return new OrganizationContact(name);
        }
        throw new IllegalArgumentException("Unknown contact type: " + type);
    }
}
