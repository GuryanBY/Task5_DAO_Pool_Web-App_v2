package com.epam.kgd.victory.dao.inspector_must_be_deleted;

import java.util.List;

import com.epam.kgd.victory.bean.Good;
import com.epam.kgd.victory.dao.GoodDAO;
import com.epam.kgd.victory.dao.exception.DAOException;
import com.epam.kgd.victory.dao.factory.DAOFactory;

public class GoodDAOInspector {

	public static void main(String[] args) throws DAOException {
		DAOFactory factory = DAOFactory.getInstance();
		GoodDAO sqlGoodDAOImpl = factory.getGoodDAO();
		
		List<Good> allGoods = sqlGoodDAOImpl.getAllGoods();
		
		for(Good g : allGoods){
			System.out.println(g.toString());
		}
		
		Good goodById = sqlGoodDAOImpl.getGoodById(3);
		//System.out.println(goodById.toString());
		
		List<Good> goodsInLimit = sqlGoodDAOImpl.goodsInLimit(50, 50);
		for(Good g : goodsInLimit){
			//System.out.println(g.toString());
		}
		
		Good goodForAdd = new Good();
		
		goodForAdd.setCategoryId(3); 
		goodForAdd.setConditionId(3);
		goodForAdd.setName("Updated Good from Eclipse"); 
		goodForAdd.setDescription("LoremIpsum"); 
		goodForAdd.setStartPrice(55555.5); 
			
		//sqlGoodDAOImpl.addGood(goodForAdd);
		
		//sqlGoodDAOImpl.deleteGood(15);
		
		//sqlGoodDAOImpl.changeGood(16, goodForAdd);

	}

}
