package com.epam.kgd.victory.dao;

import java.util.List;

import com.epam.kgd.victory.bean.Good;
import com.epam.kgd.victory.dao.exception.DAOException;

public interface GoodDAO {

	List<Good> getAllGoods() throws DAOException;

	Good getGoodById(int goodId) throws DAOException;

	List<Good> goodsInLimit(int minPrice, int maxPrice) throws DAOException;

	void addGood(Good good) throws DAOException;

	void deleteGood(int goodId) throws DAOException;

	void changeGood(int goodId, Good updatedGood) throws DAOException;

}
