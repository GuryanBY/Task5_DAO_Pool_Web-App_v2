package com.epam.kgd.victory.dao;

import java.util.List;

import com.epam.kgd.victory.bean.User;
import com.epam.kgd.victory.dao.exception.DAOException;

public interface UserDAO {
	
	List<User> getAllUsers() throws DAOException;
	User getUserById(int userId) throws DAOException;
	void addUser(User user) throws DAOException;
	void deleteUser(int userId) throws DAOException;
	void changeUser(int userId, User updatedUser) throws DAOException;
	User getUserByLoginAndPassword(String login, String password) throws DAOException;
	boolean checkLoginAndPassword(String login, String password) throws DAOException;

}
