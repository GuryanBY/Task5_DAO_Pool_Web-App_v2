package com.epam.kgd.victory.dao.pool;

import java.util.ResourceBundle;

public class DBResourceManager {
	private final static DBResourceManager INSTANCE = new DBResourceManager();
	private ResourceBundle bundle = ResourceBundle.getBundle("resources.db");
	public static DBResourceManager getInstance(){
		return INSTANCE;
	}
	public String getValue(String key){
		return bundle.getString(key);
	}

}
