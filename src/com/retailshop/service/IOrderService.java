package com.retailshop.service;

import java.sql.Connection;
import java.util.Map;

public interface IOrderService {

	public Map getOrderDetail(Connection connection, String loginId);
	
	public Map getOrderDetail(Connection connection, String loginId, String orderId);
	
	public Map getPreviousOrderDetail(Connection connection, String loginId);
	
	public Map getPreviousOrderDetail(Connection connection, String loginId, String orderId);

	public boolean submitFeedback(Connection connection, String question, String answer, String loginId, String orderId);
	
	public boolean submitCustomerFeedback(Connection connection, Map parameters);
	
	public boolean subscribe(Connection connection, String emailId);
	
	public boolean contactUs(Connection connection, Map parameters);
	
}

