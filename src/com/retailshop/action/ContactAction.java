package com.retailshop.action;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.retailshop.service.IOrderService;
import com.retailshop.service.impl.OrderServiceImpl;
import com.retailshop.util.DBConnection;

public class ContactAction implements ServletRequestAware{

	private static final Logger logger = Logger.getLogger(ContactAction.class.getName());
	
	private HttpServletRequest request;
	
	public String contactUs(){
		
		String name = request.getParameter("eq_name");
		String emailId = request.getParameter("eq_email");
		String contactNumber = request.getParameter("eq_contact");
		String subject = request.getParameter("eq_subject");
		String message = request.getParameter("eq_query");
		
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("name", name);
		parameters.put("emailId", emailId);
		parameters.put("contactNumber", contactNumber);
		parameters.put("subject", subject);
		parameters.put("message", message);
		
		Connection connection = null;
		try{
			connection = DBConnection.getConnection();
			IOrderService objOrderService = new OrderServiceImpl();
			boolean flag = objOrderService.contactUs(connection, parameters);
			if(flag){
				request.setAttribute("contactUsMsg", "Your enquiry has been submitted successfully.");
			}
			else{
				request.setAttribute("contactUsMsg", "There is some internal error. Please try again later.");
			}
		}
		catch (Exception e) {
		logger.error("",e);		
		}
		return "success";
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
}
