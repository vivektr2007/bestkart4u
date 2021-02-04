package com.retailshop.action.admin;

import com.retailshop.service.IAdminService;
import com.retailshop.service.impl.AdminServiceImpl;
import com.retailshop.util.DBConnection;
import com.retailshop.util.DBUtil;
import java.sql.Connection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

public class AdminLoginAction implements SessionAware, ServletRequestAware
{

    private String emailId;
    private String password;
    public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	Map session;
    private HttpServletRequest request;
    private String msg;
    
    public AdminLoginAction()
    {
    }

    public String getEmailId()
    {
        return emailId;
    }

    public void setEmailId(String emailId)
    {
        this.emailId = emailId;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String execute()
    {
        boolean flag = false;
        Connection connection = null;
        try{
	        connection = DBConnection.getConnection();
	        IAdminService objAdminService = new AdminServiceImpl();
	        flag = objAdminService.adminLoginCheck(connection, getEmailId(), getPassword());
        }
        finally{
        	DBUtil.closeConnection(connection);
        }
        if(flag)
        {
        	session.put("loginId", getEmailId());
        	return "success";
        } else
        {
        	setMsg("User Id or password entered is incorrect.");
            return "error";
        }
    }

    public String getChangePasswordForm(){
    	
    	return "success";
    }
    
    public String updatePassword(){
    	String oldPassword = request.getParameter("oldPassword");
    	String newPassword = request.getParameter("newPassword");
    	String loginId = session.get("loginId").toString();
    	
    	Connection connection = null;
    	try{
    		connection = DBConnection.getConnection();
	    	IAdminService objAdminService = new AdminServiceImpl();
	    	boolean flag = objAdminService.checkOldPassword(connection, loginId, oldPassword);
	    	if(flag){
	    		boolean updateFlag = objAdminService.updatePassword(connection, loginId, newPassword);
	    		if(updateFlag){
	    			setMsg("Password update successfully");
	    		}
	    		else{
	    			setMsg("There is some error.");
	    		}
	    	}
	    	else{
	    		setMsg("Old Password enetered is incorrect");
	    	}
	    	
    	}
    	catch (Exception e) {
			
		}
    	finally{
    		DBUtil.closeConnection(connection);
    	}
    	return "success";
    }
    
    public String logout(){
    	
    	session.clear();
    	((SessionMap)session).invalidate();
    	
    	setMsg("You have been logged out successfully.");
    	
    	return "success";
    }
    
	@Override
	public void setSession(Map session) {
		this.session = session;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
}
