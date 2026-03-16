package com.mycontactsapp.command;

import com.mycontactsapp.user.User;
import com.mycontactsapp.validation.HashPassword;

public class UpdatePasswordCommand implements Command {
    private final User user;
    private final String newPassword;

    public UpdatePasswordCommand(User user, String newPassword) {
        this.user = user;
        this.newPassword = newPassword;
    }

    @Override
    public void execute() {
        String hashed = HashPassword.hash(newPassword);
        user.setPassword(hashed);
        System.out.println("Password updated.");
    }
}
