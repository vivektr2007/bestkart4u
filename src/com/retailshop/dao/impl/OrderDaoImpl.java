package com.retailshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.retailshop.dao.IOrderDao;
import com.retailshop.util.DBUtil;

public class OrderDaoImpl implements IOrderDao{

	private static final Logger logger = Logger.getLogger(OrderDaoImpl.class.getName());
	
	@Override
	public Map getOrderDetail(Connection connection, String loginId)
    {
        PreparedStatement pstmt1 = null;
        ResultSet rs1 = null;
        Map OrderDetailMap = new HashMap();
        try
        {
            pstmt1 = connection.prepareStatement("SELECT order_id,total_amount, discount,amount_to_pay,delivery_charge FROM  order_detail_summary WHERE order_id IN (SELECT DISTINCT (order_id) FROM order_detail WHERE order_status IN ('P',  'C') and upper(emailid)='"+loginId.toUpperCase()+"' ORDER BY order_date DESC)");
            rs1 = pstmt1.executeQuery();
            while(rs1.next())
            {
            	ArrayList orderDetail = new ArrayList();
                
                //int cartId = rs1.getInt(1);
                String orderId = rs1.getString("order_id");
                String deliveryCharge = rs1.getString("delivery_charge");
                String totalAmount = rs1.getString("total_amount");
                String netPayable = rs1.getString("amount_to_pay");
                String discount = rs1.getString("discount");
                
                
                String address = "";
                PreparedStatement pstmt = connection.prepareStatement("select * from order_detail where order_status in(?,?) and order_id=?");
                pstmt.setString(1, "P");
                pstmt.setString(2, "C");
                pstmt.setString(3, orderId);
                ResultSet rs = pstmt.executeQuery();
                while(rs.next())
                {
                    String arr[] = new String[12];
                    arr[0] = rs.getString("DELIVERY_ADDRESS");
                    arr[1] = rs.getString("SELECTED_SLOT");
                    arr[2] = rs.getString("PAYMENT_MODE");
                    arr[3] = rs.getString("CART_ID");
                    arr[4] = rs.getString("PRODUCT_ID");
                    arr[5] = rs.getString("PRODUCT_NAME");
                    arr[6] = rs.getString("PRODUCT_BRAND_NAME");
                    arr[7] = rs.getString("OFFER_PRICE");
                    arr[8] = rs.getString("NO_OF_ITEM");
                    arr[9] = rs.getString("TOTAL_AMOUNT");
                    arr[10] = rs.getString("PRODUCT_SIZE");
                    arr[11] = rs.getString("order_status");
                    orderDetail.add(arr);
                    address = (new StringBuilder(String.valueOf(arr[0]))).append("$").append(arr[1]).toString();
                }
                rs.close();
                pstmt.close();
                String allAmountSummary = "#"+deliveryCharge+"#"+totalAmount+"#"+netPayable+"#"+discount;
                OrderDetailMap.put((new StringBuilder(String.valueOf(orderId))).append("#").append(address).append(allAmountSummary).toString(), orderDetail);
            }
        }
        catch(Exception e)
        {
            logger.error("Exception in getting order detail for admin", e);
        }
        finally{
	        DBUtil.closeResultSet(rs1);
	        DBUtil.closePreparedStatement(pstmt1);
        }
        return OrderDetailMap;
    }

	
	@Override
	public Map getOrderDetail(Connection connection, String loginId, String orderId)
    {
        PreparedStatement pstmt1 = null;
        ResultSet rs1 = null;
        Map OrderDetailMap = new HashMap();
        try
        {
        	String query = "";
        	if(orderId != null && !"".equalsIgnoreCase(orderId)){
        		query = "SELECT order_id,total_amount, discount,amount_to_pay,delivery_charge FROM  order_detail_summary WHERE order_id IN (SELECT DISTINCT (order_id) FROM order_detail WHERE order_status IN ('P',  'C') and upper(emailid)='"+loginId.toUpperCase()+"' and upper(order_id) ='"+orderId.toUpperCase()+"' ORDER BY order_date DESC)";
        	}
        	else{
        		query = "SELECT order_id,total_amount, discount,amount_to_pay,delivery_charge FROM  order_detail_summary WHERE order_id IN (SELECT DISTINCT (order_id) FROM order_detail WHERE order_status IN ('P',  'C') and upper(emailid)='"+loginId.toUpperCase()+"' ORDER BY order_date DESC)";
        	}
            pstmt1 = connection.prepareStatement(query);
            rs1 = pstmt1.executeQuery();
            while(rs1.next())
            {
            	ArrayList orderDetail = new ArrayList();
                
                //int cartId = rs1.getInt(1);
            	orderId = rs1.getString("order_id");
                String deliveryCharge = rs1.getString("delivery_charge");
                String totalAmount = rs1.getString("total_amount");
                String netPayable = rs1.getString("amount_to_pay");
                String discount = rs1.getString("discount");
                
                
                String address = "";
                PreparedStatement pstmt = connection.prepareStatement("select * from order_detail where order_status in(?,?) and order_id=?");
                pstmt.setString(1, "P");
                pstmt.setString(2, "C");
                pstmt.setString(3, orderId);
                ResultSet rs = pstmt.executeQuery();
                while(rs.next())
                {
                    String arr[] = new String[12];
                    arr[0] = rs.getString("DELIVERY_ADDRESS");
                    arr[1] = rs.getString("SELECTED_SLOT");
                    arr[2] = rs.getString("PAYMENT_MODE");
                    arr[3] = rs.getString("CART_ID");
                    arr[4] = rs.getString("PRODUCT_ID");
                    arr[5] = rs.getString("PRODUCT_NAME");
                    arr[6] = rs.getString("PRODUCT_BRAND_NAME");
                    arr[7] = rs.getString("OFFER_PRICE");
                    arr[8] = rs.getString("NO_OF_ITEM");
                    arr[9] = rs.getString("TOTAL_AMOUNT");
                    arr[10] = rs.getString("PRODUCT_SIZE");
                    arr[11] = rs.getString("order_status");
                    orderDetail.add(arr);
                    address = (new StringBuilder(String.valueOf(arr[0]))).append("$").append(arr[1]).toString();
                }
                rs.close();
                pstmt.close();
                String allAmountSummary = "#"+deliveryCharge+"#"+totalAmount+"#"+netPayable+"#"+discount;
                OrderDetailMap.put((new StringBuilder(String.valueOf(orderId))).append("#").append(address).append(allAmountSummary).toString(), orderDetail);
            }
        }
        catch(Exception e)
        {
            logger.error("Exception in getting order detail for admin", e);
        }
        finally{
	        DBUtil.closeResultSet(rs1);
	        DBUtil.closePreparedStatement(pstmt1);
        }
        return OrderDetailMap;
    }
	
	@Override
	public Map getPreviousOrderDetail(Connection connection, String loginId) {
		PreparedStatement pstmt1 = null;
        ResultSet rs1 = null;
        Map OrderDetailMap = new HashMap();
        try
        {
        	
            pstmt1 = connection.prepareStatement("SELECT order_id,total_amount, discount,amount_to_pay,delivery_charge FROM  order_detail_summary WHERE order_id IN (SELECT DISTINCT (order_id) FROM order_detail WHERE order_status IN ('R',  'D') and upper(emailid)='"+loginId.toUpperCase()+"' ORDER BY order_date DESC)");
            rs1 = pstmt1.executeQuery();
            while(rs1.next())
            {
            	ArrayList orderDetail = new ArrayList();
                
                //int cartId = rs1.getInt(1);
            	String orderId = rs1.getString("order_id");
                String deliveryCharge = rs1.getString("delivery_charge");
                String totalAmount = rs1.getString("total_amount");
                String netPayable = rs1.getString("amount_to_pay");
                String discount = rs1.getString("discount");
                
                String address = "";
                PreparedStatement pstmt = connection.prepareStatement("select * from order_detail where order_status in(?,?) and order_id=?");
                pstmt.setString(1, "R");
                pstmt.setString(2, "D");
                pstmt.setString(3, orderId);
                ResultSet rs = pstmt.executeQuery();
                while(rs.next())
                {
                    String arr[] = new String[12];
                    arr[0] = rs.getString("DELIVERY_ADDRESS");
                    arr[1] = rs.getString("SELECTED_SLOT");
                    arr[2] = rs.getString("PAYMENT_MODE");
                    arr[3] = rs.getString("CART_ID");
                    arr[4] = rs.getString("PRODUCT_ID");
                    arr[5] = rs.getString("PRODUCT_NAME");
                    arr[6] = rs.getString("PRODUCT_BRAND_NAME");
                    arr[7] = rs.getString("OFFER_PRICE");
                    arr[8] = rs.getString("NO_OF_ITEM");
                    arr[9] = rs.getString("TOTAL_AMOUNT");
                    arr[10] = rs.getString("PRODUCT_SIZE");
                    arr[11] = rs.getString("order_status");
                    orderDetail.add(arr);
                    address = (new StringBuilder(String.valueOf(arr[0]))).append("$").append(arr[1]).toString();
                }
                rs.close();
                pstmt.close();
                String allAmountSummary = "#"+deliveryCharge+"#"+totalAmount+"#"+netPayable+"#"+discount;
                OrderDetailMap.put((new StringBuilder(String.valueOf(orderId))).append("#").append(address).append(allAmountSummary).toString(), orderDetail);
            }
        }
        catch(Exception e)
        {
            logger.error("Exception in getting order detail for admin", e);
        }
        finally{
	        DBUtil.closeResultSet(rs1);
	        DBUtil.closePreparedStatement(pstmt1);
        }
        return OrderDetailMap;
	}

	@Override
	public Map getPreviousOrderDetail(Connection connection, String loginId, String orderId) {
		
		PreparedStatement pstmt1 = null;
        ResultSet rs1 = null;
        Map OrderDetailMap = new HashMap();
        try
        {
        	
        	String query = "";
        	if(orderId != null && !"".equalsIgnoreCase(orderId)){
        		query = "SELECT order_id,total_amount, discount,amount_to_pay,delivery_charge FROM  order_detail_summary WHERE order_id IN (SELECT DISTINCT (order_id) FROM order_detail WHERE order_status IN ('R',  'D') and upper(emailid)='"+loginId.toUpperCase()+"' and upper(order_id) = '"+orderId.toUpperCase()+"' ORDER BY order_date DESC)";
        	}
        	else{
        		query = "SELECT order_id,total_amount, discount,amount_to_pay,delivery_charge FROM  order_detail_summary WHERE order_id IN (SELECT DISTINCT (order_id) FROM order_detail WHERE order_status IN ('R',  'D') and upper(emailid)='"+loginId.toUpperCase()+"' ORDER BY order_date DESC)";
        	}
            pstmt1 = connection.prepareStatement(query);
            rs1 = pstmt1.executeQuery();
            while(rs1.next())
            {
            	ArrayList orderDetail = new ArrayList();
                
                //int cartId = rs1.getInt(1);
            	orderId = rs1.getString("order_id");
            	String deliveryCharge = rs1.getString("delivery_charge");
                String totalAmount = rs1.getString("total_amount");
                String netPayable = rs1.getString("amount_to_pay");
                String discount = rs1.getString("discount");
                
                String address = "";
                PreparedStatement pstmt = connection.prepareStatement("select * from order_detail where order_status in(?,?) and order_id=?");
                pstmt.setString(1, "R");
                pstmt.setString(2, "D");
                pstmt.setString(3, orderId);
                ResultSet rs = pstmt.executeQuery();
                while(rs.next())
                {
                    String arr[] = new String[12];
                    arr[0] = rs.getString("DELIVERY_ADDRESS");
                    arr[1] = rs.getString("SELECTED_SLOT");
                    arr[2] = rs.getString("PAYMENT_MODE");
                    arr[3] = rs.getString("CART_ID");
                    arr[4] = rs.getString("PRODUCT_ID");
                    arr[5] = rs.getString("PRODUCT_NAME");
                    arr[6] = rs.getString("PRODUCT_BRAND_NAME");
                    arr[7] = rs.getString("OFFER_PRICE");
                    arr[8] = rs.getString("NO_OF_ITEM");
                    arr[9] = rs.getString("TOTAL_AMOUNT");
                    arr[10] = rs.getString("PRODUCT_SIZE");
                    arr[11] = rs.getString("order_status");
                    orderDetail.add(arr);
                    address = (new StringBuilder(String.valueOf(arr[0]))).append("$").append(arr[1]).toString();
                }
                rs.close();
                pstmt.close();
                String allAmountSummary = "#"+deliveryCharge+"#"+totalAmount+"#"+netPayable+"#"+discount;
                OrderDetailMap.put((new StringBuilder(String.valueOf(orderId))).append("#").append(address).append(allAmountSummary).toString(), orderDetail);
            }
        }
        catch(Exception e)
        {
            logger.error("Exception in getting order detail for admin", e);
        }
        finally{
	        DBUtil.closeResultSet(rs1);
	        DBUtil.closePreparedStatement(pstmt1);
        }
        return OrderDetailMap;
	}
	
	@Override
	public boolean submitFeedback(Connection connection, String question,
			String answer, String loginId, String selectedOrderId) {
		PreparedStatement pstmt = null;
        boolean flag = false;
		try
        {
            pstmt = connection.prepareStatement("insert into product_feedback(login_id, question, answer, order_id) values(?,?,?,?)");
            pstmt.setString(1, loginId);
            pstmt.setString(2, question);
            pstmt.setString(3, answer);
            pstmt.setString(4, selectedOrderId);
            
            int i = pstmt.executeUpdate();
            if(i > 0){
            	flag = true;
            }
        }
        catch(Exception e)
        {
            logger.error("Exception in getting order detail for admin", e);
        }
        finally{
	       DBUtil.closePreparedStatement(pstmt);
        }
        return flag;
	}

	@Override
	public boolean submitCustomerFeedback(Connection connection, Map parameters) {
		PreparedStatement pstmt = null;
        boolean flag = false;
		try
        {
            pstmt = connection.prepareStatement("insert into customer_feedback(name, email_id, contact_number, feedback_type,message ) values(?,?,?,?,?)");
            pstmt.setString(1, (String)parameters.get("name"));
            pstmt.setString(2, (String)parameters.get("email"));
            pstmt.setString(3, (String)parameters.get("contactnumber"));
            pstmt.setString(4, (String)parameters.get("subject"));
            pstmt.setString(5, (String)parameters.get("message"));
            
            int i = pstmt.executeUpdate();
            if(i > 0){
            	flag = true;
            }
        }
        catch(Exception e)
        {
            logger.error("Exception in getting order detail for admin", e);
        }
        finally{
	       DBUtil.closePreparedStatement(pstmt);
        }
        return flag;
	}

	@Override
	public boolean subscribe(Connection connection, String emailId) {
		PreparedStatement pstmt = null;
        boolean flag = false;
		try
        {
            pstmt = connection.prepareStatement("insert into subscriber_list(email_id) values(?)");
            pstmt.setString(1, emailId);
            
            int i = pstmt.executeUpdate();
            if(i > 0){
            	flag = true;
            }
        }
        catch(Exception e)
        {
            logger.error("Exception in getting order detail for admin", e);
        }
        finally{
	       DBUtil.closePreparedStatement(pstmt);
        }
        return flag;
	}

	@Override
	public boolean contactUs(Connection connection, Map parameters) {
		
		PreparedStatement pstmt = null;
		boolean flag = false;
		try{
			
			String query = "insert into contact_us(name, email, contact_number, subject, message, created_date) values(?,?,?,?,?, now())";
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, parameters.get("name").toString());
			pstmt.setString(2, parameters.get("emailId").toString());
			pstmt.setString(3, parameters.get("contactNumber").toString());
			pstmt.setString(4, parameters.get("subject").toString());
			pstmt.setString(5, parameters.get("message").toString());
			
			int i = pstmt.executeUpdate();
			if(i > 0){
				flag = true;
			}
		}
		catch (Exception e) {
			logger.error("",e);	
		}
		
		return flag;
	}
	
}
