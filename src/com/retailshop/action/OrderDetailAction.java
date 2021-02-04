package com.retailshop.action;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.retailshop.service.IOrderService;
import com.retailshop.service.IProductService;
import com.retailshop.service.impl.OrderServiceImpl;
import com.retailshop.service.impl.ProductServiceImpl;
import com.retailshop.util.DBConnection;
import com.retailshop.util.DBUtil;

public class OrderDetailAction implements SessionAware, ServletRequestAware, ServletResponseAware{

	private Map session ;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String msg;
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	private static final Logger logger = Logger.getLogger(OrderDetailAction.class.getName());
	
	public String getPendingOrderDetail(){
		
		String loginId = session.get("loginId").toString();
		Connection connection = null;
		try{
			connection = DBConnection.getConnection();
			IOrderService objOrderService = new OrderServiceImpl();
			Map pendingOrderMap = objOrderService.getOrderDetail(connection, loginId);
			request.setAttribute("pendingOrderMap",pendingOrderMap);
			
		}
		catch (Exception e) {
			logger.error("" + e);
		}
		finally{
			DBUtil.closeConnection(connection);
		}
		return "success";
	}
	
	public String searchPendingOrderDetail(){
		
		String loginId = session.get("loginId").toString();
		String orderId = request.getParameter("orderId");
		Connection connection = null;
		try{
			connection = DBConnection.getConnection();
			IOrderService objOrderService = new OrderServiceImpl();
			Map pendingOrderMap = objOrderService.getOrderDetail(connection, loginId, orderId);
			request.setAttribute("pendingOrderMap",pendingOrderMap);
			
		}
		catch (Exception e) {
			logger.error("" + e);
		}
		finally{
			DBUtil.closeConnection(connection);
		}
		return "success";
	}
	
	public String searchPreviousOrderDetail(){
		String loginId = session.get("loginId").toString();
		String orderId = request.getParameter("orderId");
		Connection connection = null;
		try{
			connection = DBConnection.getConnection();
			IOrderService objOrderService = new OrderServiceImpl();
			Map pendingOrderMap = objOrderService.getPreviousOrderDetail(connection, loginId, orderId);
			request.setAttribute("pendingOrderMap",pendingOrderMap);
			
		}
		catch (Exception e) {
			logger.error("" + e);
		}
		finally{
			DBUtil.closeConnection(connection);
		}
		return "success";
	}
	
	public String getPreviousOrderDetail(){
		String loginId = session.get("loginId").toString();
		Connection connection = null;
		try{
			connection = DBConnection.getConnection();
			IOrderService objOrderService = new OrderServiceImpl();
			Map pendingOrderMap = objOrderService.getPreviousOrderDetail(connection, loginId);
			request.setAttribute("pendingOrderMap",pendingOrderMap);
			
		}
		catch (Exception e) {
			logger.error("" + e);
		}
		finally{
			DBUtil.closeConnection(connection);
		}
		return "success";
	}
	
	public String submitProductFeedback(){
		
		String loginId = session.get("loginId").toString();
		Connection connection = null;
		
		String selectedOrderId = request.getParameter("selectedOrderId");
		try{
			connection = DBConnection.getConnection();
			IOrderService objOrderService = new OrderServiceImpl();
			Enumeration<String> allNames = request.getParameterNames();
			while(allNames.hasMoreElements()){
				String question = allNames.nextElement();
				if(question.indexOf("Answer") != -1 || question.indexOf("selectedOrderId") != -1){
					continue;
				}
				else{
					String questionText = request.getParameter(question);
					String answer = request.getParameter(question+"Answer");
					boolean flag = objOrderService.submitFeedback(connection, questionText, answer, loginId,selectedOrderId);
					
				}
			}
			setMsg("Your feedback submitted successfully.");
		}
		catch (Exception e) {
			logger.error("" + e);
		}
		finally{
			DBUtil.closeConnection(connection);
		}
		
		return "success";
	}
	
	public String pendingOrderAutocomplete(){
		
		Connection connection = null;
		try{
		response.setContentType("application/json");
		String searchKey = request.getParameter("name");
		JSONArray jsonArr = new JSONArray();
		
		connection = DBConnection.getConnection();
		IProductService objProductService = new ProductServiceImpl();
		ArrayList al = (ArrayList)objProductService.getDetailsForAutoComplete(connection, searchKey);
		Iterator itr = al.iterator();
		while(itr.hasNext()){
			String arr[] = (String[])itr.next();
			JSONObject json=new JSONObject();
			json.put("name",arr[1] + " - " + arr[0]);
	        json.put("value",arr[1] + " - " + arr[0]);
	        
	        jsonArr.put(json);
		}
		
		
		PrintWriter out = response.getWriter();
        out.println(jsonArr);
		}
		catch (Exception e) {
			logger.error("",e);
		}
		finally{
			DBUtil.closeConnection(connection);
		}
		return "none";
		
	}
	
	
	@Override
	public void setSession(Map session) {
		this.session = session;
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
