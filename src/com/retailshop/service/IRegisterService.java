package com.retailshop.service;

import java.sql.Connection;
import java.util.Map;

import com.retailshop.action.RegisterBean;

public interface IRegisterService {

	public boolean registerUser(Connection connection,Map parameters );
	
	public boolean registerUserForFB(Connection connection,Map parameters );
	
	public boolean checkEmailID(Connection connection, String emailId);
	
	public RegisterBean getUpdateProfileForm(Connection connection, String emailId);
	
	public boolean updateProfile(Connection connection, RegisterBean regBean, String emailId);
}
