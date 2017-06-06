package com.epam.kgd.victory.dao.pool_alternative;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

import com.epam.kgd.victory.dao.pool.exception.ConnectionPoolException;

public class ConnectionPool {

	static Logger logger = Logger.getLogger(ConnectionPool.class);

	private static ConnectionPool instance = null;

	private ArrayBlockingQueue<ProxyConnection> connectionQueue;

	private ArrayBlockingQueue<ProxyConnection> givenAwayConnQueue;

	private static Lock lock = new ReentrantLock();

	private static AtomicBoolean isConnectionPoolNull = new AtomicBoolean(true);

	private static AtomicBoolean takeConnection = new AtomicBoolean(true);

	private final static int POOL_SIZE = 10;

	private ConnectionPool() throws ConnectionPoolException {
		init();
	}

	private void init() throws ConnectionPoolException {
		try {
			ResourceBundle bundle = ResourceBundle.getBundle("resources.db1");
			Class.forName(bundle.getString("driver"));
			Properties properties = new Properties();
			properties.setProperty("user", bundle.getString("user"));
			properties.setProperty("password", bundle.getString("password"));
			properties.setProperty("useUnicode", bundle.getString("useUnicode"));
			properties.setProperty("encoding", bundle.getString("encoding"));
			connectionQueue = new ArrayBlockingQueue<>(POOL_SIZE);
			givenAwayConnQueue = new ArrayBlockingQueue<>(POOL_SIZE);
			for (int i = 0; i < POOL_SIZE; i++) {
				Connection connection = DriverManager.getConnection(bundle.getString("url"), properties);
				ProxyConnection proxyConnection = new ProxyConnection(connection);
				connectionQueue.offer(proxyConnection);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new ConnectionPoolException(e);
		}
	}

	public static ConnectionPool getInstance() {

		if (isConnectionPoolNull.get()) {
			lock.lock();
			try {
				if (instance == null) {
					try {
						instance = new ConnectionPool();
						isConnectionPoolNull.set(false);
					} catch (ConnectionPoolException e) {
						logger.warn("Error while Connection Pool initialization. Trying init again...");
						instance = null;
					}
				}
			} finally {
				lock.unlock();
			}
		}

		return instance;
	}

	public ProxyConnection takeConnection() throws ConnectionPoolException {
		ProxyConnection connection = null;
		if (takeConnection.get()) {
			try {
				connection = connectionQueue.take();
				givenAwayConnQueue.add(connection);
			} catch (InterruptedException | IllegalStateException e) {
				throw new ConnectionPoolException("Exception occurred during taking connection" + e);
			}
		}
		return connection;
	}

	void returnConnection(ProxyConnection connection) throws SQLException {
		if (connection.isClosed()) {
			throw new SQLException("Error when trying to return closed connection");
		}
		if (!connection.getAutoCommit()) {
			connection.setAutoCommit(true);
		}
		if (!givenAwayConnQueue.remove(connection)) {
			throw new SQLException("Error while deleting connection from given away connection pool.");
		}
		if (!connectionQueue.offer(connection)) {
			throw new SQLException("Error while allocating connection in the pool.");
		}
	}

	public void cleanConnectionPool() throws ConnectionPoolException {
		takeConnection.set(false);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			throw new ConnectionPoolException("InterruptedException occurred during connection pool cleaning" + e);
		}
		closeConnectionQueue(connectionQueue);
		closeConnectionQueue(givenAwayConnQueue);

	}

	private void closeConnectionQueue(ArrayBlockingQueue<ProxyConnection> connections) throws ConnectionPoolException {
		ProxyConnection connection;
		while ((connection = connections.poll()) != null) {
			try {
				connection.reallyClose();
			} catch (SQLException e) {
				throw new ConnectionPoolException("Error while trying to reallyClose connection" + e);
			}
		}
	}
}