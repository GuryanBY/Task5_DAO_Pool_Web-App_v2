package com.epam.kgd.victory.service;

import com.epam.kgd.victory.service.exception.ServiceException;

public interface UserService {
	String getPageByRole(String login, String password) throws ServiceException;
	

}
