package com.epam.kgd.victory.dao.inspector_must_be_deleted;

import java.util.List;

import com.epam.kgd.victory.bean.User;
import com.epam.kgd.victory.dao.UserDAO;
import com.epam.kgd.victory.dao.exception.DAOException;
import com.epam.kgd.victory.dao.factory.DAOFactory;

public class UserDAOInspector {

	public static void main(String[] args) throws DAOException {
		DAOFactory factory = DAOFactory.getInstance();
		UserDAO sqlUserImpl = factory.getUserDAO();

		List<User> allUsers = sqlUserImpl.getAllUsers();

		for (User u : allUsers) {
			//System.out.println(u.toString());
		}
		User userById = sqlUserImpl.getUserById(10);
		//System.out.println(userById.toString());

		User userForAdd = new User();
		userForAdd.setRole(2);
		userForAdd.setLogin("loginEclipse");
		userForAdd.setPassword("passwordEclipse");
		userForAdd.setFirstName("Ivan");
		userForAdd.setLastName("Ivanov");
		userForAdd.setEmail("noemail@mail.ru");
		userForAdd.setPhone("+375298564847");
		userForAdd.setRegistrationDate("2017-05-05");
		
		//sqlUserImpl.addUser(userForAdd);
		//sqlUserImpl.deleteUser(12);
		userForAdd.setLogin("NEWloginEclipse");
		userForAdd.setPassword("NEWpasswordEclipse");
		
		//sqlUserImpl.changeUser(13, userForAdd);
		
		User userFromLogin = sqlUserImpl.getUserByLoginAndPassword("NEWloginEclipse", "NEWpasswordEclipse");
		
		//System.out.println(userFromLogin.toString());
		
		System.out.println(sqlUserImpl.checkLoginAndPassword("sim", "asdf"));
		

	}

}
