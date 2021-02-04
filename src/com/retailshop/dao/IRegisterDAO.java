package com.retailshop.dao;

import java.sql.Connection;
import java.util.Map;

import com.retailshop.action.RegisterBean;

public interface IRegisterDAO {

	/*
	 * 
	 */
	String REGISTER_QUERY = "insert into user_detail(emailid, password, mobile_no, first_name, last_name, source) values(?,?,?,?,?,?)";
	/*
	 * 
	 */
	public boolean registerUser(Connection connection, Map parameters);

	/*
	 * 
	 */
	String REGISTER_QUERY_FB = "insert into user_detail(emailid,password, salutation, first_name, last_name, source,fb_id) values(?,?,?,?,?,?,?)";
	/*
	 * 
	 */
	public boolean registerUserForFb(Connection connection, Map parameters);
	
	/*
	 * 
	 */
	String CHECK_EMAIL_ID_QRY = "select * from user_detail where upper(emailid) = ?";
	/*
	 * 
	 */
	public boolean checkEmailID(Connection conection, String emailId);
	
	String GET_USER_DETAIL_QUERY = "select mobile_no,salutation,first_name,last_name,DATE_FORMAT(dob,'%d/%m/%Y') dob,telephone_no,house_no,street_detail,area,residential_complex,landmark,city,pin_code from user_detail where emailid = ?";
	
	public RegisterBean getUpdateProfileForm(Connection conn,String emailId);
	
	String UPDATE_USER_PROFILE= "update user_detail set mobile_no=?,salutation = ?,first_name =?, last_name = ?,dob = STR_TO_DATE(?,'%d/%m/%Y'), " +
			"telephone_no = ?,	house_no =?, " +
			"street_detail=?, area=?, residential_complex = ?,landmark = ?, city=?, pin_code = ? where emailid	=?";
	
	public boolean updateProfile(Connection connection, RegisterBean regBean, String emailId);
}
