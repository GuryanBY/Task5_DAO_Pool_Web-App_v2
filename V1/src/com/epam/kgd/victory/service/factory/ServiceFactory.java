package com.epam.kgd.victory.service.factory;

import com.epam.kgd.victory.service.UserService;
import com.epam.kgd.victory.service.impl.UserServiceImpl;

public final class ServiceFactory {
	private static final ServiceFactory INSTANCE = new ServiceFactory();

	private ServiceFactory() {
	}

	private final UserService clientService = new UserServiceImpl();

	public static ServiceFactory getInstance() {
		return INSTANCE;
	}

	public UserService getClientService() {
		return clientService;
	}

}
