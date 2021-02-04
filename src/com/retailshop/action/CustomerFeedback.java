package com.retailshop.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.retailshop.service.IOrderService;
import com.retailshop.service.impl.OrderServiceImpl;
import com.retailshop.util.DBConnection;
import com.retailshop.util.DBUtil;

public class CustomerFeedback implements ServletRequestAware, ServletResponseAware{

	private Logger logger = Logger.getLogger(CustomerFeedback.class.getName());
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public String submitFeedback() throws IOException{
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String contact = request.getParameter("contact");
		String subject = request.getParameter("subject");
		String message = request.getParameter("message");
		
		Map parameters = new HashMap();
		
		parameters.put("name",name);
		parameters.put("email",email);
		parameters.put("contactnumber",contact);
		parameters.put("subject",subject);
		parameters.put("message",message);
        
		PrintWriter out = response.getWriter();
		Connection connection = null;
		try{
			connection = DBConnection.getConnection();
			IOrderService objOrderService = new OrderServiceImpl();
			boolean flag = objOrderService.submitCustomerFeedback(connection, parameters);
			if(flag){
				out.print("success");
			}
			else{
				out.print("error");
			}
		}
		catch(Exception e){
			logger.error("", e);
		}
		finally{
			DBUtil.closeConnection(connection);
		}
		
		return "none";
	}

	public String subscribe() throws IOException{
		
		String emailId = request.getParameter("emailId");
		
		PrintWriter out = response.getWriter();
		Connection connection = null;
		try{
			connection = DBConnection.getConnection();
			IOrderService objOrderService = new OrderServiceImpl();
			boolean flag = objOrderService.subscribe(connection, emailId);
			if(flag){
				out.print("success");
			}
			else{
				out.print("error");
			}
		}
		catch(Exception e){
			logger.error("", e);
		}
		finally{
			DBUtil.closeConnection(connection);
		}
		
		return "none";
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
}

