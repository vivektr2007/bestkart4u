package com.retailshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.retailshop.dao.ILoginDAO;
import com.retailshop.util.DBUtil;

public class LoginDaoImpl implements ILoginDAO {

	private static final Logger logger = Logger.getLogger(LoginDaoImpl.class.getName());
	
	@Override
	public String loginCheck(Connection connection, String emailId,
			String password) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String name = "";
		
		try{
			pstmt = connection.prepareStatement(loginQuery);
			pstmt.setString(1, emailId);
			pstmt.setString(2, password);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				String firstName = rs.getString("FIRST_NAME");
				String lastName = rs.getString("LAST_NAME");
				name = firstName + " " + lastName;
				
				/*
				flag = true;*/
			}
		}
		catch(SQLException e){
			logger.error("There is some error while getting login detail",e);
		}
		catch(Exception e){
			logger.error("Unexpected error while getting login detail",e);
		}
		finally{
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(pstmt);
		}
		
		return name;
	}

	@Override
	public boolean resetPassword(Connection connection, String email,
			String newPass) {

		PreparedStatement pstmt = null;
		boolean flag = false;
		
		try{
			pstmt = connection.prepareStatement(RESET_PASS_QRY);
			pstmt.setString(1, newPass);
			pstmt.setString(2, email);
			
			int i = pstmt.executeUpdate();
			if(i>0){
				flag = true;
			}
		}
		catch(SQLException e){
			logger.error("There is some error while password reset ",e);
		}
		catch(Exception e){
			logger.error("Unexpected error while password reset",e);
		}
		finally{
			DBUtil.closePreparedStatement(pstmt);
		}
		
		return flag;
	}

	@Override
	public boolean checkPassword(Connection connection, String loginId,
			String oldPassword) {

		
		PreparedStatement pstmt = null;
		boolean flag = false;
		
		try{
			pstmt = connection.prepareStatement(PASSWORD_CHECK);
			pstmt.setString(1, loginId);
			pstmt.setString(2, oldPassword);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				flag = true;
			}
		}
		catch(SQLException e){
			logger.error("There is some error while password change ",e);
		}
		catch(Exception e){
			logger.error("Unexpected error while password change",e);
		}
		finally{
			DBUtil.closePreparedStatement(pstmt);
		}
		
		return flag;
		
	}

	@Override
	public boolean changePassword(Connection connection, String loginId,
			String newPassword) {
		PreparedStatement pstmt = null;
		boolean flag = false;
		
		try{
			pstmt = connection.prepareStatement(CHANGE_PASSWORD);
			pstmt.setString(1, newPassword);
			pstmt.setString(2, loginId);
			
			int i = pstmt.executeUpdate();
			if(i>0){
				flag = true;
			}
		}
		catch(SQLException e){
			logger.error("There is some error while password change ",e);
		}
		catch(Exception e){
			logger.error("Unexpected error while password change",e);
		}
		finally{
			DBUtil.closePreparedStatement(pstmt);
		}
		
		return flag;
	}

	
	
}
