package com.epam.kgd.victory.dao.factory;

import com.epam.kgd.victory.dao.UserDAO;
import com.epam.kgd.victory.dao.GoodDAO;
import com.epam.kgd.victory.dao.LotDAO;
import com.epam.kgd.victory.dao.impl.SQLUserDAO;
import com.epam.kgd.victory.dao.impl.SQLGoodDAO;
import com.epam.kgd.victory.dao.impl.SQLLotDAO;

public final class DAOFactory {

	private static final DAOFactory INSTANCE = new DAOFactory();
	private final LotDAO sqlLotImpl = new SQLLotDAO();
	private final GoodDAO sqlGoodImpl = new SQLGoodDAO();
	private final UserDAO sqlUserImpl = new SQLUserDAO();

	private DAOFactory() {
	}

	public static DAOFactory getInstance() {
		return INSTANCE;
	}

	public LotDAO getLotDAO() {
		return sqlLotImpl;
	}

	public GoodDAO getGoodDAO() {
		return sqlGoodImpl;
	}

	public UserDAO getUserDAO() {
		return sqlUserImpl;
	}
}
