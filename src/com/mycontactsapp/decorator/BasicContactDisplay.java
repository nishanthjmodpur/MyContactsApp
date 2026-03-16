package com.mycontactsapp.decorator;

import com.mycontactsapp.contact.Contact;

public class BasicContactDisplay implements ContactDisplay {
    @Override
    public String format(Contact contact) {
        return contact.toString(); // uses Contact’s toString()
    }
}