package com.epam.kgd.victory.dao.impl;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.kgd.victory.bean.User;
import com.epam.kgd.victory.dao.UserDAO;
import com.epam.kgd.victory.dao.exception.DAOException;

import com.epam.kgd.victory.dao.pool.exception.ConnectionPoolException;
import com.epam.kgd.victory.dao.pool_alternative.ConnectionPool;
import com.epam.kgd.victory.dao.pool_alternative.ProxyConnection;

public class SQLUserDAO implements UserDAO {

	static Logger logger = Logger.getLogger(SQLUserDAO.class);

	private static final String SQL_TAKE_ALL_USERS = "SELECT `u_id`, `u_role_id`, `u_login`, `u_password`, `u_first_name`, `u_last_name`, `u_email`, `u_phone`, `u_registr_date` FROM `user` WHERE `u_role_id`='2'";
	private static final String SQL_TAKE_USER_BY_ID = "SELECT `u_id`, `u_role_id`, `u_login`, `u_password`, `u_first_name`, `u_last_name`, `u_email`, `u_phone`, `u_registr_date` FROM `user` WHERE `u_id`=?";
	private static final String SQL_ADD_USER = "INSERT INTO `user` (`u_role_id`, `u_login`, `u_password`, `u_first_name`, `u_last_name`, `u_email`, `u_phone`, `u_registr_date`) VALUES ('2', ?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_DELETE_USER = "DELETE FROM `user` WHERE `u_id`=?";
	private static final String SQL_CHANGE_USER = "UPDATE `user` SET `u_role_id`=?, `u_login`=?, `u_password`=?, `u_first_name`=?, `u_last_name`=?, `u_email`=?, `u_phone`=?, `u_registr_date`=? WHERE `u_id`=?";
	private static final String SQL_TAKE_USER_BY_LOGIN_PASSWORD = "SELECT `u_id`, `u_role_id`, `u_login`, `u_password`, `u_first_name`, `u_last_name`, `u_email`, `u_phone`, `u_registr_date` FROM `user` WHERE `u_login`=? AND `u_password` = ?";
	private static final String SQL_LOGIN_PASSWORD_CHECK = "SELECT `u_id` FROM `user` WHERE `u_login`=? AND `u_password`=?";
	private static final String SQL_TAKE_USER_BY_EMAIL = "SELECT `u_id`, `u_role_id`, `u_login`, `u_password`, `u_first_name`, `u_last_name`, `u_email`, `u_phone`, `u_registr_date` FROM `user` WHERE `u_email`=?";
	private static final String SQL_TAKE_USER_ID_BY_EMAIL = "SELECT `u_id` FROM `user` WHERE `u_email`=?";

	private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

	@Override
	public List<User> getAllUsers() throws DAOException {
		List<User> result = new ArrayList<>();

		ProxyConnection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			connection = CONNECTION_POOL.takeConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL_TAKE_ALL_USERS);

			while (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt(1));
				user.setRole(resultSet.getInt(2));
				user.setLogin(resultSet.getString(3));
				user.setPassword(resultSet.getString(4));
				user.setFirstName(resultSet.getString(5));
				user.setLastName(resultSet.getString(6));
				user.setEmail(resultSet.getString(7));
				user.setPhone(resultSet.getString(8));
				user.setRegistrationDate(resultSet.getString(9));

				result.add(user);

			}
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			closeResources(resultSet, statement, connection);
		}
		return result;
	}

	@Override
	public User getUserById(int userId) throws DAOException {
		User result = new User();

		ProxyConnection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = CONNECTION_POOL.takeConnection();
			preparedStatement = connection.prepareStatement(SQL_TAKE_USER_BY_ID);
			preparedStatement.setInt(1, userId);
			resultSet = preparedStatement.executeQuery();

			resultSet.next();
			result.setId(resultSet.getInt(1));
			result.setRole(resultSet.getInt(2));
			result.setLogin(resultSet.getString(3));
			result.setPassword(resultSet.getString(4));
			result.setFirstName(resultSet.getString(5));
			result.setLastName(resultSet.getString(6));
			result.setEmail(resultSet.getString(7));
			result.setPhone(resultSet.getString(8));
			result.setRegistrationDate(resultSet.getString(9));

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			closeResources(resultSet, preparedStatement, connection);
		}
		return result;
	}

	@Override
	public void addUser(User user) throws DAOException {
		ProxyConnection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = CONNECTION_POOL.takeConnection();
			preparedStatement = connection.prepareStatement(SQL_ADD_USER);

			preparedStatement.setString(1, user.getLogin());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getFirstName());
			preparedStatement.setString(4, user.getLastName());
			preparedStatement.setString(5, user.getEmail());
			preparedStatement.setString(6, user.getPhone());
			preparedStatement.setString(7, user.getRegistrationDate());

			preparedStatement.executeUpdate();

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			closeResources(resultSet, preparedStatement, connection);
		}

	}

	@Override
	public void deleteUser(int userId) throws DAOException {
		ProxyConnection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = CONNECTION_POOL.takeConnection();
			preparedStatement = connection.prepareStatement(SQL_DELETE_USER);
			preparedStatement.setInt(1, userId);
			preparedStatement.executeUpdate();

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			closeResources(resultSet, preparedStatement, connection);
		}

	}

	@Override
	public void changeUser(int userId, User updatedUser) throws DAOException {
		ProxyConnection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = CONNECTION_POOL.takeConnection();
			preparedStatement = connection.prepareStatement(SQL_CHANGE_USER);

			preparedStatement.setInt(1, updatedUser.getRole());
			preparedStatement.setString(2, updatedUser.getLogin());
			preparedStatement.setString(3, updatedUser.getPassword());
			preparedStatement.setString(4, updatedUser.getFirstName());
			preparedStatement.setString(5, updatedUser.getLastName());
			preparedStatement.setString(6, updatedUser.getEmail());
			preparedStatement.setString(7, updatedUser.getPhone());
			preparedStatement.setString(8, updatedUser.getRegistrationDate());

			preparedStatement.setInt(9, userId);

			preparedStatement.executeUpdate();

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			closeResources(resultSet, preparedStatement, connection);
		}

	}

	@Override
	public User getUserByLoginAndPassword(String login, String password) throws DAOException {
		User result = new User();

		ProxyConnection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = CONNECTION_POOL.takeConnection();
			preparedStatement = connection.prepareStatement(SQL_TAKE_USER_BY_LOGIN_PASSWORD);
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();

			resultSet.next();
			result.setId(resultSet.getInt(1));
			result.setRole(resultSet.getInt(2));
			result.setLogin(resultSet.getString(3));
			result.setPassword(resultSet.getString(4));
			result.setFirstName(resultSet.getString(5));
			result.setLastName(resultSet.getString(6));
			result.setEmail(resultSet.getString(7));
			result.setPhone(resultSet.getString(8));
			result.setRegistrationDate(resultSet.getString(9));

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			closeResources(resultSet, preparedStatement, connection);
		}
		return result;
	}

	public boolean checkLoginAndPassword(String login, String password) throws DAOException {

		ProxyConnection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = CONNECTION_POOL.takeConnection();
			preparedStatement = connection.prepareStatement(SQL_LOGIN_PASSWORD_CHECK);
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();

			return resultSet.next();

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			closeResources(resultSet, preparedStatement, connection);
		}
	}

	private void closeResources(ResultSet rs, Statement st, ProxyConnection con) throws DAOException {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DAOException("Can't close ResultSet", e);
			}
		}
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DAOException("Can't close Statement", e);
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				throw new DAOException("Can't close Connection", e);
			}
		}
	}

}
