package com.mycontactsapp.observer;

import com.mycontactsapp.contact.Contact;

public class LoggingObserver implements ContactDeletionObserver {
    @Override
    public void onContactDeleted(Contact contact) {
        System.out.println("[Observer] Contact deleted: " + contact.getName());
    }
}