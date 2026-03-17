package com.mycontactsapp.observer;

import com.mycontactsapp.contact.Contact;

public interface ContactDeletionObserver {
    void onContactDeleted(Contact contact);
}
