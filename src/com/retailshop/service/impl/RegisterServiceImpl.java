package com.retailshop.service.impl;

import java.sql.Connection;
import java.util.Map;

import com.retailshop.action.RegisterBean;
import com.retailshop.dao.ILoginDAO;
import com.retailshop.dao.IRegisterDAO;
import com.retailshop.dao.impl.LoginDaoImpl;
import com.retailshop.dao.impl.RegisterDAOImpl;
import com.retailshop.service.IRegisterService;

public class RegisterServiceImpl implements IRegisterService {

	@Override
	public boolean registerUser(Connection connection, Map parameters) {
		
		IRegisterDAO objRegisterDAO = new RegisterDAOImpl();
		boolean flag = objRegisterDAO.registerUser(connection, parameters);
		
		return flag;
	}

	@Override
	public boolean registerUserForFB(Connection connection, Map parameters) {
		
		IRegisterDAO objRegisterDAO = new RegisterDAOImpl();
		boolean flag = objRegisterDAO.registerUserForFb(connection, parameters);
		
		return flag;
	}
	
	@Override
	public boolean checkEmailID(Connection connection, String emailId) {
		
		IRegisterDAO objRegisterDAO = new RegisterDAOImpl();
		boolean flag = objRegisterDAO.checkEmailID(connection, emailId);
		
		return flag;
	}

	@Override
	public RegisterBean getUpdateProfileForm(Connection connection, String emailId) {
		IRegisterDAO objRegisterDAO = new RegisterDAOImpl();
		return objRegisterDAO.getUpdateProfileForm(connection,emailId);
	}
	
	@Override
	public boolean updateProfile(Connection connection, RegisterBean regBean, String emailId){
		IRegisterDAO objRegisterDAO = new RegisterDAOImpl();
		return objRegisterDAO.updateProfile(connection,regBean,emailId);
	}
	
}
