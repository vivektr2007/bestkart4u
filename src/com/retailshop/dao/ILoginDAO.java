package com.retailshop.dao;

import java.sql.Connection;

public interface ILoginDAO {
	
	
	String loginQuery = "SELECT * FROM user_detail WHERE EMAILID = ? AND PASSWORD = ?";
	/*
	 * 
	 */
	public String loginCheck(Connection connection, String emailId,String password);
	
	
	/*
	 * 
	 */
	String RESET_PASS_QRY = "UPDATE user_detail SET PASSWORD = ? WHERE EMAILID=?";
	/*
	 * 
	 */
	public boolean resetPassword(Connection connection, String email, String newPass);
	
	/*
	 * 
	 */
	String PASSWORD_CHECK = "SELECT * FROM user_detail WHERE EMAILID = ? AND PASSWORD = ?";
	/*
	 * 
	 */
	public boolean checkPassword(Connection connection, String loginId, String oldPassword);
	
	/*
	 * 
	 */
	String CHANGE_PASSWORD = "UPDATE user_detail SET PASSWORD = ? WHERE EMAILID = ?";
	/*
	 * 
	 */
	public boolean changePassword(Connection connection, String loginId, String newPassword);
}
