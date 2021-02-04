package com.retailshop.action.admin;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.retailshop.service.IAdminService;
import com.retailshop.service.impl.AdminServiceImpl;
import com.retailshop.util.DBConnection;
import com.retailshop.util.DBUtil;

public class ResolveImageAction implements ServletRequestAware, ServletResponseAware, SessionAware{

	private static final Logger logger = Logger.getLogger(ResolveImageAction.class.getName());
	
	private Map session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public String checkAllImages(){
		
		Connection connection = null;
		
		IAdminService objAdminService = new AdminServiceImpl();
		
		try{
			connection = DBConnection.getConnection();
			ArrayList al = (ArrayList)objAdminService.checkAllImages(connection);
			Iterator itr = al.iterator();
			while(itr.hasNext()){
				String[] arr = (String[])itr.next();
				String imagePath = arr[3];
				File f1 = new File(request.getRealPath("") + File.separator + imagePath);
				if(f1.exists()){
					itr.remove();
				}
			}
			
			request.setAttribute("prodDetail", al);
			
		}
		catch (Exception e) {
			logger.error(""+e);
		}
		finally{
			DBUtil.closeConnection(connection);
		}
		
		return "success";
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
}
