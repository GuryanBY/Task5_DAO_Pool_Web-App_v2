package com.epam.kgd.victory.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.kgd.victory.controller.command.Command;
import com.epam.kgd.victory.controller.exception.ControllerException;

public class Registration implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		HttpSession session = request.getSession(true);
		session.setAttribute("url", "/V1/Controller?command=registration");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/registration.jsp");
		//System.out.println(request.getServerName());
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			throw new ControllerException("cant run reg_client.jsp", e);
		}

	}

}
