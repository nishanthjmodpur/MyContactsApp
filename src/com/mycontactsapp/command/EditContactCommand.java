package com.mycontactsapp.command;

import com.mycontactsapp.contact.Contact;
import com.mycontactsapp.memento.ContactMemento;
import com.mycontactsapp.validation.Validator;

public class EditContactCommand implements Command {
    private final Contact contact;
    private final String newName;
    private final String newPhone;
    private final String newEmail;
    private ContactMemento backup;

    public EditContactCommand(Contact contact, String newName, String newPhone, String newEmail) {
        this.contact = contact;
        this.newName = newName;
        this.newPhone = newPhone;
        this.newEmail = newEmail;
    }

    @Override
    public void execute() {
        backup = new ContactMemento(contact);

        if (newName != null && !newName.isBlank()) {
            Validator.validateName(newName);
            contact.setName(newName);
        }
        if (newPhone != null && !newPhone.isBlank()) {
            contact.getPhones().clear();
            contact.addPhone(newPhone);
        }
        if (newEmail != null && !newEmail.isBlank()) {
            Validator.validateEmail(newEmail);
            contact.getEmails().clear();
            contact.addEmail(newEmail);
        }
        System.out.println("Contact updated: " + contact);
    }

    @Override
    public void undo() {
        if (backup != null) {
            contact.setName(backup.getName());
            contact.getPhones().clear();
            contact.getPhones().addAll(backup.getPhones());
            contact.getEmails().clear();
            contact.getEmails().addAll(backup.getEmails());
            System.out.println("Undo: Contact reverted to previous state.");
        }
    }
}
