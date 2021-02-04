package com.retailshop.service.impl;

import java.sql.Connection;

import com.retailshop.dao.ILoginDAO;
import com.retailshop.dao.impl.LoginDaoImpl;
import com.retailshop.service.ILoginService;


public class LoginServiceImpl implements ILoginService{

	@Override
	public String loginCheck(Connection connection, String emailId,
			String password) {
		ILoginDAO objLoginDAO = new LoginDaoImpl();
		
		return objLoginDAO.loginCheck(connection, emailId, password);
	}

	@Override
	public boolean forgetPassword(String email) {
		
		
		
		return false;
	}

	@Override
	public boolean resetPassword(Connection connection, String email,
			String newPass) {
		
		ILoginDAO objLoginDao = new LoginDaoImpl();
		boolean flag = objLoginDao.resetPassword(connection, email, newPass);
		
		return flag;
	}

	@Override
	public boolean checkPassword(Connection connection, String loginId,
			String oldPassword) {
		ILoginDAO objLoginDao = new LoginDaoImpl();
		boolean flag = objLoginDao.checkPassword(connection, loginId, oldPassword);
		
		return flag;
	}

	@Override
	public boolean changePassword(Connection connection, String loginId,
			 String newPassword) {
		ILoginDAO objLoginDao = new LoginDaoImpl();
		boolean flag = objLoginDao.changePassword(connection, loginId,  newPassword);
		
		return flag;
	}

}
