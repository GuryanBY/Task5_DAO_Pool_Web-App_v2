package com.epam.kgd.victory.controller;

import java.util.HashMap;
import java.util.Map;

import com.epam.kgd.victory.controller.command.Command;
import com.epam.kgd.victory.controller.command.CommandName;
import com.epam.kgd.victory.controller.command.impl.LogIn;
import com.epam.kgd.victory.controller.command.impl.Registration;
import com.epam.kgd.victory.controller.command.impl.WrongRequest;
import com.epam.kgd.victory.controller.exception.ControllerException;

final class CommandProvider {
	
	private final Map<CommandName, Command> repository = new HashMap<>();
	
	CommandProvider(){
		
		repository.put(CommandName.LOG_IN, new LogIn());
		repository.put(CommandName.REGISTRATION, new Registration());
		repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
		
	}
	Command getCommand(String name) throws ControllerException {
		try {
			CommandName commandName = CommandName.valueOf(name.toUpperCase());
			return repository.get(commandName);
		} catch (IllegalArgumentException | NullPointerException e) {
			throw new ControllerException("Incorrect command", e);
		}

	}

}
