package com.retailshop.service.impl;

import java.sql.Connection;
import java.util.Map;

import com.retailshop.dao.IOrderDao;
import com.retailshop.dao.impl.OrderDaoImpl;
import com.retailshop.service.IOrderService;

public class OrderServiceImpl implements IOrderService {

	@Override
	public Map getOrderDetail(Connection connection, String loginId) {
		IOrderDao objOrderDao = new OrderDaoImpl();
		return objOrderDao.getOrderDetail(connection, loginId);
	}

	@Override
	public Map getOrderDetail(Connection connection, String loginId, String orderId) {
		IOrderDao objOrderDao = new OrderDaoImpl();
		return objOrderDao.getOrderDetail(connection, loginId, orderId);
	}
	
	@Override
	public Map getPreviousOrderDetail(Connection connection, String loginId) {
		IOrderDao objOrderDao = new OrderDaoImpl();
		return objOrderDao.getPreviousOrderDetail(connection, loginId);
	}

	@Override
	public Map getPreviousOrderDetail(Connection connection, String loginId, String orderId) {
		IOrderDao objOrderDao = new OrderDaoImpl();
		return objOrderDao.getPreviousOrderDetail(connection, loginId, orderId);
	}
	
	@Override
	public boolean submitFeedback(Connection connection, String question,
			String answer, String loginId, String selectedOrderId) {
		IOrderDao objOrderDao = new OrderDaoImpl();
		return objOrderDao.submitFeedback(connection, question, answer, loginId, selectedOrderId);
	}

	@Override
	public boolean submitCustomerFeedback(Connection connection, Map parameters) {
		IOrderDao objOrderDao = new OrderDaoImpl();
		return objOrderDao.submitCustomerFeedback(connection, parameters);
	}

	@Override
	public boolean subscribe(Connection connection, String emailId) {
		IOrderDao objOrderDao = new OrderDaoImpl();
		return objOrderDao.subscribe(connection, emailId);
	}

	@Override
	public boolean contactUs(Connection connection, Map parameters) {
		IOrderDao objOrderDao = new OrderDaoImpl();
		return objOrderDao.contactUs(connection, parameters);
	}

}
