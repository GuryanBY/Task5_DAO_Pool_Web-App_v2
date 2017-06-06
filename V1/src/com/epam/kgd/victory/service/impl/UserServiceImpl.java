package com.epam.kgd.victory.service.impl;

import com.epam.kgd.victory.bean.User;
import com.epam.kgd.victory.dao.UserDAO;
import com.epam.kgd.victory.dao.exception.DAOException;
import com.epam.kgd.victory.dao.factory.DAOFactory;
import com.epam.kgd.victory.service.UserService;
import com.epam.kgd.victory.service.exception.ServiceException;

public class UserServiceImpl implements UserService {

	@Override
	public String getPageByRole(String login,String password ) throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO daoUser = daoFactory.getUserDAO();
		int role;
		String pageURL = null;

		try {
			User user = daoUser.getUserByLoginAndPassword(login, password);
			role = user.getRole();

			switch (role) {
			case 1:
				pageURL = "/jsp/admin_page.jsp";
				break;
			case 2:
				pageURL = "/jsp/client_page.jsp";
				break;
			default:
				pageURL = "/jsp/error_page.jsp";
				break;

			}

		} catch (DAOException e) {
			throw new ServiceException("Problem take user by login and password", e);
			
		}

		return pageURL;
	}

}
