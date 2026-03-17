package com.mycontactsapp.contact;

import java.util.*;


public abstract class Contact {
    private String name;
    private List<String> phones = new ArrayList<>();
    private List<String> emails = new ArrayList<>();

    protected Contact(String name) {
        this.name = name;
    }

    public Contact(Contact other) {
        this.name = other.name;
        this.phones = new ArrayList<>(other.phones);
        this.emails = new ArrayList<>(other.emails);
    }

    public String getName() { return name; }
    public List<String> getPhones() { return phones; }
    public List<String> getEmails() { return emails; }

    public void setName(String name) { this.name = name; }
    public void setPhones(List<String> phones) {
        this.phones = new ArrayList<>(phones); // defensive copy
    }
    public void setEmails(List<String> emails) {
        this.emails = new ArrayList<>(emails);
    }

    public void addPhone(String phone) { phones.add(phone); }
    public void addEmail(String email) { emails.add(email); }
    
}