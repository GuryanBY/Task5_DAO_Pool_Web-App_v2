package com.epam.kgd.victory.dao.inspector_must_be_deleted;

import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.kgd.victory.dao.pool.exception.ConnectionPoolException;
import com.epam.kgd.victory.dao.pool_alternative.ConnectionPool;
import com.epam.kgd.victory.dao.pool_alternative.ProxyConnection;



public class ConnectionPoolAlternativeTest {
	
private static ConnectionPool connectionPool;
	
	@BeforeClass
	public static void connectionPoolInit() {
		connectionPool = ConnectionPool.getInstance();
	}
	
	
	@Test
	public void takeConnectionTest() throws SQLException, ConnectionPoolException {
		for (int i = 0; i < 100000; i++) {
			ProxyConnection connection = connectionPool.takeConnection();
			Assert.assertNotNull(connection);
			connection.close();
			
		}
	}
	
	@AfterClass
	public static void cleanConnectionPool() throws ConnectionPoolException {
		connectionPool.cleanConnectionPool();
		connectionPool = null;
	}

}
