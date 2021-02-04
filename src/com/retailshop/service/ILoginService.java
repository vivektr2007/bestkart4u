package com.retailshop.service;

import java.sql.Connection;

public interface ILoginService {
	public String loginCheck(Connection connection, String emailId, String password);
	
	public boolean forgetPassword(String email);
	
	public boolean resetPassword(Connection connection, String email, String newPass);
	
	public boolean checkPassword(Connection connection, String loginId, String oldPassword);
	
	public boolean changePassword(Connection connection, String loginId, String newPassword);
}
