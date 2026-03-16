package com.mycontactsapp.command;

import com.mycontactsapp.user.User;

public class UpdateNameCommand implements Command {
	 private final User user;
	    private final String newName;

	    public UpdateNameCommand(User user, String newName) {
	        this.user = user;
	        this.newName = newName;
	    }

	    @Override
	    public void execute() {
	        user.setName(newName);
	        System.out.println("Name updated to: " + newName);
	    }
}
