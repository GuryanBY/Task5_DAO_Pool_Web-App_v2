package com.epam.kgd.victory.dao;

import java.util.List;

import com.epam.kgd.victory.bean.Lot;
import com.epam.kgd.victory.dao.exception.DAOException;



public interface LotDAO {
	List<Lot> getAllLots() throws DAOException;
	List<Lot> getLotsByTypeId(int typeId) throws DAOException;
	Lot getLotById (int lotId) throws DAOException;
	void addLot(Lot lot)throws DAOException;
	void deleteLot(int lotId)throws DAOException;
	void changeLot(int lotId, Lot updatedLot)throws DAOException;
}
