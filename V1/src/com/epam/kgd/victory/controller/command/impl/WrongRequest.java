package com.epam.kgd.victory.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.kgd.victory.controller.command.Command;
import com.epam.kgd.victory.controller.exception.ControllerException;

public class WrongRequest implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		// TODO Auto-generated method stub
		
	}

}
