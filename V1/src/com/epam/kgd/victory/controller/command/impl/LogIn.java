package com.epam.kgd.victory.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.kgd.victory.controller.command.Command;
import com.epam.kgd.victory.controller.exception.ControllerException;
import com.epam.kgd.victory.service.UserService;
import com.epam.kgd.victory.service.exception.ServiceException;
import com.epam.kgd.victory.service.factory.ServiceFactory;

public class LogIn implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getClientService();

		RequestDispatcher dispatcher = null;
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		

		try {
			String page = userService.getPageByRole(login, password);

			HttpSession session = request.getSession(true);
			session.setAttribute("page", page);
			dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);

		} catch (ServiceException | ServletException | IOException e) {
			e.printStackTrace();
		}

	}

}
