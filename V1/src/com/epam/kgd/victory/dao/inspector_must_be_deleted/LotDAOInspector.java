package com.epam.kgd.victory.dao.inspector_must_be_deleted;

import java.util.List;

import com.epam.kgd.victory.bean.Lot;
import com.epam.kgd.victory.dao.LotDAO;
import com.epam.kgd.victory.dao.exception.DAOException;
import com.epam.kgd.victory.dao.factory.DAOFactory;

public class LotDAOInspector {
	public static void main(String[] args) throws DAOException {
		DAOFactory factory = DAOFactory.getInstance();
		 LotDAO lotDAO = factory.getLotDAO(); 
		 List<Lot> lots = 
				lotDAO.getAllLots();
				//lotDAO.getLotsByTypeId(2);
		
		
		for(Lot l : lots){
			System.out.println(l.toString());
		}
		
		
		/*Lot lotForAdd = new Lot();
		lotForAdd.setBuyerId(2);
		lotForAdd.setSellerId(2);
		lotForAdd.setGoodId(3);
		lotForAdd.setAuctionTypeId("2");
		lotForAdd.setLotName("Hello from Eclipse");
		lotForAdd.setGoodAmount(2);
		lotForAdd.setStartDate("2017-01-01");
		lotForAdd.setEndDate("2017-02-01");
		lotForAdd.setEndPrice(20.3);*/
		
		/*lotDAO.addLot(lotForAdd);*/
		
		
		//lotDAO.deleteLot(14);
		
		/*lotForAdd.setLotName("Hello from Eclipse after update");
		
		lotDAO.changeLot( 16, lotForAdd);*/
		
		/*Lot lotSearchByID = lotDAO.getLotById(16);
		
		System.out.println(lotSearchByID.toString());*/
		
				

	}

}
