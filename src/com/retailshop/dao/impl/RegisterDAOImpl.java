package com.retailshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.retailshop.action.RegisterBean;
import com.retailshop.dao.IRegisterDAO;
import com.retailshop.util.DBUtil;

public class RegisterDAOImpl implements IRegisterDAO {

	private static final Logger logger = Logger.getLogger(RegisterDAOImpl.class.getName());
	
	@Override
	public boolean registerUser(Connection connection, Map parameters) {
		
		boolean flag = false;
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(REGISTER_QUERY);
			pstmt.setString(1, (String)parameters.get("EMAILID"));
			pstmt.setString(2, (String)parameters.get("PASSWORD"));
			pstmt.setString(3, (String)parameters.get("MOBILENO"));
			//pstmt.setString(4, (String)parameters.get("SALUTATION"));
			pstmt.setString(4, (String)parameters.get("FIRSTNAME"));
			pstmt.setString(5, (String)parameters.get("LASTNAME"));
			/*pstmt.setString(7, (String)parameters.get("DOB"));
			pstmt.setString(8, (String)parameters.get("TELEPHONENO"));
			pstmt.setString(9, (String)parameters.get("HOUSEDETAIL"));
			pstmt.setString(10, (String)parameters.get("STREETDETAIL"));
			pstmt.setString(11, (String)parameters.get("AREA"));
			pstmt.setString(12, (String)parameters.get("RESIDENTCOMPLEX"));
			pstmt.setString(13, (String)parameters.get("LANDMARK"));
			pstmt.setString(14, (String)parameters.get("CITY"));
			pstmt.setInt(15, (Integer)parameters.get("PINCODE"));*/
			pstmt.setString(6, "WEB");
			
			int i = pstmt.executeUpdate();
			if(i>0){
				flag = true;
			}
			
		} catch (SQLException e) {
			logger.error("There is some error in registering user,",e);
		}catch (Exception e) {
			logger.error("There is some unexpected error in registering user,",e);
		}
		finally{
			DBUtil.closePreparedStatement(pstmt);
		}
		return flag;
	}

	@Override
	public boolean registerUserForFb(Connection connection, Map parameters) {
		
		boolean flag = false;
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(REGISTER_QUERY_FB);
			pstmt.setString(1, (String)parameters.get("EMAILID"));
			pstmt.setString(2, (String)parameters.get("PASSWORD"));
			pstmt.setString(3, (String)parameters.get("SALUTATION"));
			pstmt.setString(4, (String)parameters.get("FIRSTNAME"));
			pstmt.setString(5, (String)parameters.get("LASTNAME"));
			pstmt.setString(6, "FB");
			pstmt.setString(7, (String)parameters.get("FBID"));
			
			int i = pstmt.executeUpdate();
			if(i>0){
				flag = true;
			}
			
		} catch (SQLException e) {
			logger.error("There is some error in registering user,",e);
		}catch (Exception e) {
			logger.error("There is some unexpected error in registering user,",e);
		}
		finally{
			DBUtil.closePreparedStatement(pstmt);
		}
		return flag;
	}
	
	public boolean checkEmailID(Connection connection, String emailId){
		
		boolean flag = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			pstmt = connection.prepareStatement(CHECK_EMAIL_ID_QRY);
			pstmt.setString(1, emailId.toUpperCase());
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				flag = true;
			}
			
		}
		catch(SQLException e){
			logger.error("Error while checking email id in database",e);
		}
		catch (Exception e) {
			logger.error("unexpected Error while checking email id in database",e);
		}
		finally{
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(pstmt);
		}
		
		return flag;
	}

	@Override
	public RegisterBean getUpdateProfileForm(Connection connection, String emailId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		RegisterBean rb = new RegisterBean();
		
		try{
			pstmt = connection.prepareStatement(GET_USER_DETAIL_QUERY);
			pstmt.setString(1, emailId);
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				rb.setEmailId(emailId);
				rb.setMobileNo(rs.getString("mobile_no"));
				rb.setSalutaion(rs.getString("salutation"));
				rb.setFirstName(rs.getString("first_name"));
				rb.setDob(rs.getString("dob"));
				rb.setTelephoneNo(rs.getString("telephone_no"));
				rb.setHouseDetail(rs.getString("house_no"));
				rb.setStreetDetail(rs.getString("street_detail"));
				rb.setArea(rs.getString("area"));
				rb.setResidentComplex(rs.getString("residential_complex"));
				rb.setLandmark(rs.getString("landmark"));
				rb.setCity(rs.getString("city"));
				rb.setPincode(rs.getInt("pin_code"));
				rb.setLastName(rs.getString("last_name"));
				
			}
			
		}
		catch(SQLException e){
			logger.error("Error while update profile in database",e);
		}
		catch (Exception e) {
			logger.error("Error while update profile in database",e);
		}
		finally{
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(pstmt);
		}
		
		return rb;
	}

	@Override
	public boolean updateProfile(Connection connection, RegisterBean regBean, String emailId){
		
		boolean flag = false;
		
		PreparedStatement pstmt = null;
		try{
			pstmt = connection.prepareStatement(UPDATE_USER_PROFILE);
			pstmt.setString(1, regBean.getMobileNo());
			pstmt.setString(2, regBean.getSalutaion());
			pstmt.setString(3, regBean.getFirstName());
			pstmt.setString(4, regBean.getLastName());
			pstmt.setString(5, regBean.getDob());
			pstmt.setString(6, regBean.getTelephoneNo());
			pstmt.setString(7, regBean.getHouseDetail());
			pstmt.setString(8, regBean.getStreetDetail());
			pstmt.setString(9, regBean.getArea());
			pstmt.setString(10, regBean.getResidentComplex());
			pstmt.setString(11, regBean.getLandmark());
			pstmt.setString(12, regBean.getCity());
			pstmt.setInt(13, regBean.getPincode());
			pstmt.setString(14, emailId);
			
			int i = pstmt.executeUpdate();
			if(i>0){
				flag = true;
			}
			
		}
		catch(SQLException e){
			logger.error("Error while update profile in database",e);
		}
		catch (Exception e) {
			logger.error("Error while update profile in database",e);
		}
		finally{
			DBUtil.closePreparedStatement(pstmt);
		}
		
		return flag;
	}
}
