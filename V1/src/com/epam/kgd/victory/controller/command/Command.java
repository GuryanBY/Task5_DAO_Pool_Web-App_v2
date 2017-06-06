package com.epam.kgd.victory.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.kgd.victory.controller.exception.ControllerException;

public interface Command {
	void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException;
}
