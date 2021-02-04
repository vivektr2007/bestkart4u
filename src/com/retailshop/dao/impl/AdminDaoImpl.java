package com.retailshop.dao.impl;

import com.retailshop.dao.IAdminDAO;
import com.retailshop.util.DBUtil;
import java.io.PrintStream;
import java.sql.*;
import java.util.*;

import org.apache.log4j.Logger;

public class AdminDaoImpl implements IAdminDAO
{

    private static final Logger logger = Logger.getLogger(AdminDaoImpl.class.getName());

    public AdminDaoImpl()
    {
    }

    public Map getOrderDetail(Connection connection)
    {
        PreparedStatement pstmt1 = null;
        ResultSet rs1 = null;
        Map OrderDetailMap = new HashMap();
        try
        {
            pstmt1 = connection.prepareStatement("select distinct(order_id) from order_detail where order_status='P' order by order_date");
            rs1 = pstmt1.executeQuery();
            while(rs1.next())
            {
            	ArrayList orderDetail = new ArrayList();
                
                //int cartId = rs1.getInt(1);
                String orderId = rs1.getString(1);
                String address = "";
                PreparedStatement pstmt = connection.prepareStatement("select * from order_detail where order_status = ? and order_id=?");
                pstmt.setString(1, "P");
                pstmt.setString(2, orderId);
                ResultSet rs = pstmt.executeQuery();
                while(rs.next())
                {
                    String arr[] = new String[11];
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
                    
                    
                    orderDetail.add(arr);
                    address = (new StringBuilder(String.valueOf(arr[0]))).append("#").append(arr[1]).append("#").append(rs.getString("ORDER_DATE")).toString();
                }
                rs.close();
                pstmt.close();
                OrderDetailMap.put((new StringBuilder(String.valueOf(orderId))).append("#").append(address).toString(), orderDetail);
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

    public Map getOrderDetailForInvoice(Connection connection)
    {
        PreparedStatement pstmt1 = null;
        ResultSet rs1 = null;
        Map OrderDetailMap = new HashMap();
        try
        {
            pstmt1 = connection.prepareStatement("select distinct(order_id) from order_detail where order_status='C' order by order_date");
            rs1 = pstmt1.executeQuery();
            while(rs1.next())
            {
            	ArrayList orderDetail = new ArrayList();
                
                //int cartId = rs1.getInt(1);
                String orderId = rs1.getString(1);
                String address = "";
                PreparedStatement pstmt = connection.prepareStatement("select * from order_detail where order_status = ? and order_id=?");
                pstmt.setString(1, "C");
                pstmt.setString(2, orderId);
                ResultSet rs = pstmt.executeQuery();
                while(rs.next())
                {
                    String arr[] = new String[11];
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
                    orderDetail.add(arr);
                    address = (new StringBuilder(String.valueOf(arr[0]))).append("#").append(arr[1]).toString();
                }
                rs.close();
                pstmt.close();
                OrderDetailMap.put((new StringBuilder(String.valueOf(orderId))).append("#").append(address).toString(), orderDetail);
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
    
    public boolean addCategory(Connection connection, String categoryName)
    {
        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
        boolean flag = false;
        long categoryId = 1L;
        try
        {
            pstmt1 = connection.prepareStatement("select max(master_category_id) from master_category");
            ResultSet rs = pstmt1.executeQuery();
            if(rs.next())
            {
                categoryId = rs.getLong(1) + 1L;
            }
            rs.close();
            pstmt = connection.prepareStatement("insert into master_category(master_category_id,master_category_name) values(?,?)");
            pstmt.setLong(1, categoryId);
            pstmt.setString(2, categoryName.toUpperCase());
            int i = pstmt.executeUpdate();
            if(i > 0)
            {
                flag = true;
            }
        
        }
        catch(SQLException e)
        {
            logger.error("error in inserting category into database", e);
        }
        finally{
	        DBUtil.closePreparedStatement(pstmt1);
	        DBUtil.closePreparedStatement(pstmt);
        }
        return flag;
    }

    public boolean addSubCategory(Connection connection, long categiryId, String subCategoryName)
    {
        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
        boolean flag = false;
        long subCategoryId = 1L;
        
        try
        {
            pstmt1 = connection.prepareStatement("select max(sub_category_id) from sub_category1 where master_category_id = ?");
            pstmt1.setLong(1, categiryId);
            ResultSet rs = pstmt1.executeQuery();
            if(rs.next())
            {
                subCategoryId = rs.getLong(1) + 1L;
            }
            rs.close();
            pstmt = connection.prepareStatement("insert into sub_category1(sub_category_id,sub_category_name, master_category_id) values(?,?,?)");
            pstmt.setLong(1, subCategoryId);
            pstmt.setString(2, subCategoryName);
            pstmt.setLong(3, categiryId);
            int i = pstmt.executeUpdate();
            if(i > 0)
            {
                flag = true;
            }
        }
        catch(SQLException e)
        {
            logger.error("error in inserting category into database", e);
        }
        finally{
	        DBUtil.closePreparedStatement(pstmt1);
	        DBUtil.closePreparedStatement(pstmt);
        }
        return flag;
    }

    public Map getAllCategory(Connection connection)
    {
        PreparedStatement pstmt = null; 
        Map CategoryDetail = new HashMap();
        
        try
        {
            pstmt = connection.prepareStatement("select * from master_category order by master_category_name");
            String masterCategoryId;
            String masterCategoryName;
            for(ResultSet rs = pstmt.executeQuery(); rs.next(); CategoryDetail.put(masterCategoryId, masterCategoryName))
            {
                masterCategoryId = rs.getString("master_category_id");
                masterCategoryName = rs.getString("master_category_name");
                
            }
        }
        catch(SQLException e)
        {
            logger.error("error in inserting category into database", e);
        }
        finally{
        	DBUtil.closePreparedStatement(pstmt);
        }
        return CategoryDetail;
    }

    public boolean adminLoginCheck(Connection connection, String adminId, String password)
    {
        PreparedStatement pstmt1 = null;
        boolean flag = false;
        ResultSet rs = null;
        try
        {
            pstmt1 = connection.prepareStatement("select * from admin_detail where adminid= ? and password = ?");
            pstmt1.setString(1, adminId);
            pstmt1.setString(2, password);
            rs = pstmt1.executeQuery();
            if(rs.next())
            {
                flag = true;
            }
            
        }
        catch(SQLException e)
        {
            logger.error("error in inserting category into database", e);
        }
        DBUtil.closePreparedStatement(pstmt1);
        return flag;
    }

    public String getAllSubCategory(Connection connection, String masterCategory)
    {
        PreparedStatement pstmt = null;
        String allSubCategory = "";
        try
        {
            pstmt = connection.prepareStatement("select * from sub_category1 where master_category_id = ?");
            pstmt.setString(1, masterCategory);
            for(ResultSet rs = pstmt.executeQuery(); rs.next();)
            {
                String masterCategoryId = rs.getString("sub_category_id");
                String masterCategoryName = rs.getString("sub_category_name");
                allSubCategory = (new StringBuilder(String.valueOf(allSubCategory))).append("#").append(masterCategoryId).append("-").append(masterCategoryName).toString();
            }

        }
        catch(SQLException e)
        {
            logger.error("error in inserting category into database", e);
        }
        finally{
        	DBUtil.closePreparedStatement(pstmt);
        }
        if(allSubCategory.length() > 1)
        {
            allSubCategory = allSubCategory.substring(1);
        }
        return allSubCategory;
    }

    public boolean addChildCategory(Connection connection, String masterCategoryId, String subCategoryId, String childCategoryName)
    {
        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
        boolean flag = false;
        long childCategoryId=1L;
        try{
	        pstmt1 = connection.prepareStatement("select max(sub_category_id2) from sub_category2 where master_category_id = ? and sub_category_id1=?");
	        pstmt1.setString(1, masterCategoryId);
	        pstmt1.setString(2, subCategoryId);
	        ResultSet rs = pstmt1.executeQuery();
	        if(rs.next())
	        {
	            childCategoryId = rs.getLong(1) + 1L;
	        }
	        rs.close();
	        pstmt = connection.prepareStatement("insert into sub_category2(master_category_id,sub_category_id1,sub_category_id2,sub_category_desc) values(?,?,?,?)");
	        pstmt.setInt(1, Integer.parseInt(masterCategoryId));
	        pstmt.setInt(2, Integer.parseInt(subCategoryId));
	        pstmt.setLong(3, childCategoryId);
	        pstmt.setString(4, childCategoryName);
	        int i = pstmt.executeUpdate();
	        if(i > 0)
	        {
	            flag = true;
	        }
        }
        catch(Exception e){
        	logger.error("error in inserting category into database", e);
        }
        finally{
	        DBUtil.closePreparedStatement(pstmt1);
	        DBUtil.closePreparedStatement(pstmt);
        }
        
        return flag;
    }

	@Override
	public String getAllChildCategory(Connection connection,
			String categoryId, String subCategoryId) {
		PreparedStatement pstmt = null;
        String allSubCategory = "";
        try
        {
            pstmt = connection.prepareStatement("select * from sub_category2 where master_category_id = ? and sub_category_id1 = ?");
            pstmt.setString(1, categoryId);
            pstmt.setString(2, subCategoryId);
            
            for(ResultSet rs = pstmt.executeQuery(); rs.next();)
            {
                String masterCategoryId = rs.getString("sub_category_id2");
                String masterCategoryName = rs.getString("sub_category_desc");
                allSubCategory = (new StringBuilder(String.valueOf(allSubCategory))).append("#").append(masterCategoryId).append("-").append(masterCategoryName).toString();
            }

        }
        catch(SQLException e)
        {
            logger.error("error in inserting category into database", e);
        }
        finally{
        	DBUtil.closePreparedStatement(pstmt);
        }
        if(allSubCategory.length() > 1)
        {
            allSubCategory = allSubCategory.substring(1);
        }
        return allSubCategory;
	}

	@Override
	public boolean addProduct(Connection connection, Map parameters) {
		PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
        boolean flag = false;
        long productId=1L;
        try{
	        pstmt1 = connection.prepareStatement("select max(product_id) from product_info");
	       
	        ResultSet rs = pstmt1.executeQuery();
	        if(rs.next())
	        {
	            productId = rs.getLong(1) + 1L;
	        }
	        rs.close();
	        
	        String subcategoryId = parameters.get("subCategoryId") != null && !parameters.get("subCategoryId").toString().trim().equals("") ? parameters.get("subCategoryId").toString() : "-1";
	        String childCategoryId = parameters.get("childCategoryId") != null && !parameters.get("childCategoryId").toString().trim().equals("")? parameters.get("childCategoryId").toString() : "-1";
	        
	        
	        pstmt = connection.prepareStatement("insert into product_info(root_category,sub_root_category,category_id,product_id,product_name," +
	        		"product_brand_name,actual_price,offer_price,image_path,about_product,ingredient,product_size, created_by, created_on) values(?,?,?,?,?,?,?,?,?,?,?,?,?,now())");
	        pstmt.setString(1, (String)parameters.get("categoryId"));
	        pstmt.setLong(2, Long.parseLong(subcategoryId));
	        pstmt.setLong(3, Long.parseLong(childCategoryId));
	        pstmt.setLong(4, productId);
	        pstmt.setString(5, ((String)parameters.get("productName")).toUpperCase());
	        pstmt.setString(6, ((String)parameters.get("productBrandName")).toUpperCase());
	        pstmt.setString(7, (String)parameters.get("actualPrice"));
	        pstmt.setString(8, (String)parameters.get("offerPrice"));
	        pstmt.setString(9, (String)parameters.get("imagePath"));
	        pstmt.setString(10, (String)parameters.get("aboutProduct"));
	        pstmt.setString(11, (String)parameters.get("ingredient"));
	        pstmt.setString(12, (String)parameters.get("productSize"));
	        pstmt.setString(13, parameters.get("loginId").toString());
	        
	        
	        int i = pstmt.executeUpdate();
	        PreparedStatement pstmt2 = connection.prepareStatement("insert into product_price_detail(product_id, product_price, Product_info, root_category, sub_root_category, child_category, created_by, created_on,offer_price) values(?,?,?,?,?,?,?, now(),?)");
	        pstmt2.setString(1, ""+productId);
	        pstmt2.setString(2, (String)parameters.get("actualPrice"));
	        pstmt2.setString(3, ((String)parameters.get("productSize")).toUpperCase());
	        pstmt2.setString(4, (String)parameters.get("categoryId"));
	        pstmt2.setString(5, (String)parameters.get("subCategoryId"));
	        pstmt2.setString(6, (String)parameters.get("childCategoryId"));
	        pstmt2.setString(7, parameters.get("loginId").toString());
	        pstmt2.setString(8, (String)parameters.get("offerPrice"));
	        
	        
	        int j = pstmt2.executeUpdate();
	        
	        if(i > 0 && j > 0)
	        {
	            flag = true;
	        }
        }
        catch(Exception e){
        	logger.error("error in inserting category into database", e);
        }
        finally{
	        DBUtil.closePreparedStatement(pstmt1);
	        DBUtil.closePreparedStatement(pstmt);
        }
        
        return flag;
	}

	@Override
	public boolean deleteCategory(Connection connection, String categoryId) {
		
		boolean flag = false;
		Statement stmt = null;
		
		try{
			stmt = connection.createStatement();
			
			stmt.addBatch("delete from master_category where master_category_id = '"+categoryId+"'");
			stmt.addBatch("delete from sub_category1 where master_category_id = '"+categoryId+"'");
			stmt.addBatch("delete from sub_category2 where master_category_id = '"+categoryId+"'");
			stmt.addBatch("delete from product_info where root_category = '"+categoryId+"'");
			stmt.addBatch("delete from product_price_detail where root_category = '"+categoryId+"'");
			
			int []a = stmt.executeBatch();
			
			flag = true;
			
		}
		catch(SQLException e){
			logger.error("sql Exception in deleteCategory", e);
		}
		catch(Exception e){
			logger.error("unexpected Exception in deleteCategory", e);
		}
		finally{
			DBUtil.closeStatement(stmt);
		}
		
		return flag;
	}

	@Override
	public boolean deleteSubCategory(Connection connection, String categoryId,
			String subCategoryId) {
		boolean flag = false;
		Statement stmt = null;
		try{
			stmt = connection.createStatement();
			
			stmt.addBatch("delete from sub_category1 where master_category_id = '"+categoryId+"' and Sub_category_id = '"+subCategoryId+"'");
			stmt.addBatch("delete from sub_category2 where master_category_id = '"+categoryId+"' and Sub_category_id1 = '"+subCategoryId+"'");
			stmt.addBatch("delete from product_info where root_category = '"+categoryId+"' and sub_root_category = '"+subCategoryId+"'");
			stmt.addBatch("delete from product_price_detail where root_category = '"+categoryId+"' and sub_root_category = '"+subCategoryId+"'");
			
			int []a = stmt.executeBatch();
			flag = true;
			
		}
		catch(SQLException e){
			logger.error("sql Exception in deleteCategory", e);
		}
		catch(Exception e){
			logger.error("unexpected Exception in deleteCategory", e);
		}
		finally{
			DBUtil.closeStatement(stmt);
		}
		return flag;
	}

	@Override
	public boolean deleteChildCategory(Connection connection,
			String categoryId, String subCategoryId, String childCategoryId) {
		boolean flag = false;
		Statement stmt = null;
		try{
			stmt = connection.createStatement();
			
			stmt.addBatch("delete from sub_category2 where master_category_id = '"+categoryId+"' and Sub_category_id1 = '"+subCategoryId+"' and Sub_category_id2 = '"+childCategoryId+"'");
			stmt.addBatch("delete from product_info where root_category = '"+categoryId+"' and sub_root_category = '"+subCategoryId+"'  and category_id = '"+childCategoryId+"'");
			stmt.addBatch("delete from product_price_detail where root_category = '"+categoryId+"' and sub_root_category = '"+subCategoryId+"' and child_category = '"+childCategoryId+"'");
			
			int []a = stmt.executeBatch();
			flag = true;
			
		}
		catch(SQLException e){
			logger.error("sql Exception in deleteCategory", e);
		}
		catch(Exception e){
			logger.error("unexpected Exception in deleteCategory", e);
		}
		finally{
			DBUtil.closeStatement(stmt);
		}
		return flag;
	}

	@Override
	public boolean deleteProduct(Connection connection, String categoryId,
			String subCategoryId, String childCategoryId, String[] productId, String loginId) {
		boolean flag = false;
		Statement stmt = null;
		
		String productIdIn = "";
		String productIdHistory = "";
		for(int i = 0; i < productId.length; i++){
			productIdIn = productIdIn + ","+productId[i];
			productIdHistory = productIdHistory + ", " + productId[i]; 
		}
		if(productIdIn.indexOf(",") != -1){
			productIdIn = productIdIn.substring(1);
			productIdHistory = productIdHistory.substring(1);
		}
		
		try{
			stmt = connection.createStatement();
			
			String qry = "delete from product_info where root_category = '"+categoryId+"' and sub_root_category = '"+subCategoryId+"'  and category_id = '"+childCategoryId+"' and product_id in ("+productIdIn+") ";
			String qry1 = "delete from product_price_detail where root_category = '"+categoryId+"' and sub_root_category = '"+subCategoryId+"' and child_category= '"+childCategoryId+"' and product_id in ("+productIdIn+")";
			String qry2 = "insert into product_delete_history(root_category, sub_root_category,child_category, product_id,deleted_by, deleted_on) values('"+categoryId+"','"+subCategoryId+"','"+childCategoryId+"','"+productIdHistory+"','"+loginId+"',now())";
			
			stmt.addBatch(qry);
			stmt.addBatch(qry1);
			stmt.addBatch(qry2);
			
			int []a = stmt.executeBatch();
			flag = true;
			
		}
		catch(SQLException e){
			logger.error("sql Exception in deleteCategory", e);
		}
		catch(Exception e){
			logger.error("unexpected Exception in deleteCategory", e);
		}
		finally{
			DBUtil.closeStatement(stmt);
		}
		return flag;
	}
	
	@Override
	public String getAllProductAjax(Connection connection, String categoryId, String subCategoryId, String childCategoryId){
		
		PreparedStatement pstmt = null;
        String allProduct = "";
        ResultSet rs = null;
        
        try
        {
            pstmt = connection.prepareStatement("select * from product_info where root_category = ? and sub_root_category = ? and category_id= ? ");
            pstmt.setString(1, categoryId);
            pstmt.setString(2, subCategoryId);
            pstmt.setString(3, childCategoryId);
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                String productName = rs.getString("product_name");
                String productId = rs.getString("product_id");
                String productBrandName = rs.getString("product_brand_name");
                String productSize = rs.getString("product_size");
                String image = rs.getString("image_path");
            	
                allProduct = allProduct + "$" + productName + "#" +productBrandName + "#" +productSize + "#" + image + "#" + productId + "#" +categoryId + "#" + subCategoryId + "#" + childCategoryId;
            }

        }
        catch(SQLException e)
        {
            logger.error("error in inserting category into database", e);
        }
        finally{
        	DBUtil.closeResultSet(rs);
        	DBUtil.closePreparedStatement(pstmt);
        }
        if(allProduct.length() > 1)
        {
        	allProduct = allProduct.substring(1);
        }
        else{
        	allProduct = "null";
        }
        return allProduct;
		
	}

	@Override
	public boolean editCategory(Connection connection, String categoryId,
			String categoryText) {
		boolean flag = false;
		Statement stmt = null;
		
		try{
			stmt = connection.createStatement();
			stmt.executeUpdate("update master_category set master_category_name = '"+categoryText+"' where master_category_id = '"+categoryId+"'");
			flag = true;
			
		}
		catch(SQLException e){
			logger.error("sql Exception in deleteCategory", e);
		}
		catch(Exception e){
			logger.error("unexpected Exception in deleteCategory", e);
		}
		finally{
			DBUtil.closeStatement(stmt);
		}
		
		return flag;
	}
	
	@Override
	public boolean editSubCategory(Connection connection, String categoryId,String subCategoryId,
			String categoryText) {
		boolean flag = false;
		Statement stmt = null;
		
		try{
			stmt = connection.createStatement();
			stmt.executeUpdate("update sub_category1 set sub_category_name = '"+categoryText+"' where master_category_id = '"+categoryId+"' and Sub_category_id = '"+subCategoryId+"'");
			flag = true;
			
		}
		catch(SQLException e){
			logger.error("sql Exception in deleteCategory", e);
		}
		catch(Exception e){
			logger.error("unexpected Exception in deleteCategory", e);
		}
		finally{
			DBUtil.closeStatement(stmt);
		}
		
		return flag;
	}
	
	@Override
	public boolean editChildCategory(Connection connection, String categoryId,String subCategoryId,String childCategory,
			String categoryText) {
		boolean flag = false;
		Statement stmt = null;
		
		try{
			stmt = connection.createStatement();
			stmt.executeUpdate("update sub_category2 set sub_category_desc = '"+categoryText+"' where master_category_id = '"+categoryId+"' and sub_category_id1 = '"+subCategoryId+"' and sub_category_id2 = '"+childCategory+"'");
			flag = true;
			
		}
		catch(SQLException e){
			logger.error("sql Exception in deleteCategory", e);
		}
		catch(Exception e){
			logger.error("unexpected Exception in deleteCategory", e);
		}
		finally{
			DBUtil.closeStatement(stmt);
		}
		
		return flag;
	}

	@Override
	public Map<String, String> getProductInfo(Connection connection,
			String categoryId, String subCategoryId, String childCategoryId,
			String productId) {
		PreparedStatement pstmt = null;
        Map<String, String> productInfo = new HashMap<String, String>();
        ResultSet rs = null;
        
        try
        {
        	
        	String query = "SELECT (select sc2.sub_category_desc from sub_category2 sc2 where sc2.master_category_id = pi.root_category and sc2.sub_category_id1 = pi.sub_root_category and sc2.sub_category_id2 = pi.category_id) child_category_name,(select sc1.sub_category_name from sub_category1 sc1 where sc1.master_category_id = pi.root_category and sc1.Sub_category_id = pi.sub_root_category) sub_category_name,pi.category_id, pi.product_id, pi.product_name, pi.product_brand_name, pi.actual_price, pi.offer_price, pi.image_path, pi.about_product, pi.ingredient, pi.root_category, pi.sub_root_category, pi.product_size FROM product_info pi WHERE root_category = ? and sub_root_category = ? and category_id= ? and product_id = ? ";
        	
        	//String query = "SELECT pi.category_id, pi.product_id, pi.product_name, pi.product_brand_name, pi.actual_price, pi.offer_price, pi.image_path, pi.about_product, pi.ingredient, pi.root_category, pi.sub_root_category, pi.product_size FROM product_info pi WHERE root_category = ? and sub_root_category = ? and category_id= ? and product_id = ?  ";
        	
        	//String query = "SELECT category_id, product_id, product_name, product_brand_name, actual_price, offer_price, image_path, about_product, ingredient, root_category, sub_root_category, product_size FROM product_info where root_category = ? and sub_root_category = ? and category_id= ? and product_id = ? ";
        	
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, categoryId);
            pstmt.setString(2, subCategoryId);
            pstmt.setString(3, childCategoryId);
            pstmt.setString(4, productId);
            rs = pstmt.executeQuery();
            while(rs.next())
            {
            	productInfo.put("CHILDCATEGORYNAME", rs.getString("child_category_name"));
            	productInfo.put("SUBCATEGORYNAME", rs.getString("sub_category_name"));
            	
                productInfo.put("PRODUCTNAME", rs.getString("product_name"));
                productInfo.put("PRODUCTBRANDNAME", rs.getString("product_brand_name"));
                productInfo.put("ACTUALPRICE", rs.getString("actual_price"));
                productInfo.put("OFFERPRICE", rs.getString("offer_price"));
                productInfo.put("IMAGEPATH", rs.getString("image_path"));
                productInfo.put("ABOUTPRODUCT", rs.getString("about_product"));
                productInfo.put("PRODUCTSIZE", rs.getString("product_size"));
                productInfo.put("INGREDIENT", rs.getString("ingredient"));
                productInfo.put("ROOTCATEGORYID", rs.getString("root_category"));
                productInfo.put("SUBROOTCATEGORYID", rs.getString("sub_root_category"));
                productInfo.put("CHILDCATEGORYID", rs.getString("category_id"));
                productInfo.put("PRODUCTID", rs.getString("product_id"));
                
            }

        }
        catch(SQLException e)
        {
            logger.error("error in inserting category into database", e);
        }
        finally{
        	DBUtil.closeResultSet(rs);
        	DBUtil.closePreparedStatement(pstmt);
        }
        
        return productInfo;
	}

	@Override
	public boolean editProduct(Connection connection, Map arguments) {
		boolean flag = false;
		PreparedStatement stmt = null;
		
		String query  = "update product_info set category_id = ?, product_name = ?, product_brand_name = ?, actual_price = ? , offer_price = ?, image_path = ?, about_product = ?, ingredient = ?, root_category = ?, sub_root_category = ?,	product_size = ?, modified_by = ?, modified_on = now() where product_id = ?";
		
		try{
			stmt = connection.prepareStatement(query);
			stmt.setLong(1, Long.parseLong((String)arguments.get("CHILDCATEGORYID")));
			stmt.setString(2, (String)arguments.get("PRODUCTNAME"));
			stmt.setString(3, (String)arguments.get("PRODUCTBRANDNAME"));
			stmt.setString(4, (String)arguments.get("ACTUALPRICE"));
			stmt.setString(5, (String)arguments.get("OFFERPRICE"));
			stmt.setString(6, (String)arguments.get("IMAGEPATH"));
			stmt.setString(7, (String)arguments.get("ABOUTPRODUCT"));
			stmt.setString(8, (String)arguments.get("INGREDIENT"));
			stmt.setLong(9, Long.parseLong((String)arguments.get("CATEGORYID")));
			stmt.setLong(10, Long.parseLong((String)arguments.get("SUBCATEGORYID")));
			stmt.setString(11, (String)arguments.get("SIZE"));
			stmt.setString(12, (String)arguments.get("loginId"));
			stmt.setInt(13, Integer.parseInt((String)arguments.get("PRODUCTID")));
			
			stmt.executeUpdate();
			flag = true;
			
		}
		catch(SQLException e){
			logger.error("sql Exception in deleteCategory", e);
		}
		catch(Exception e){
			logger.error("unexpected Exception in deleteCategory", e);
		}
		finally{
			DBUtil.closePreparedStatement(stmt);
		}
		
		return flag;
	}
	
	public boolean checkDuplicateProduct(Connection connection, Map arguments){
		boolean flag = false;
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			stmt = connection.prepareStatement(CheckDuplicateProductQuery);
			stmt.setString(1, arguments.get("productName").toString().toUpperCase());
			stmt.setString(2, arguments.get("productSize").toString().toUpperCase());
			stmt.setString(3, arguments.get("productBrandName").toString().toUpperCase());
			
			rs = stmt.executeQuery();
			if(rs.next()){
				flag = true;
			}
		}
		catch(SQLException e){
			logger.error("sql Exception in deleteCategory", e);
		}
		catch(Exception e){
			logger.error("unexpected Exception in deleteCategory", e);
		}
		finally{
			DBUtil.closePreparedStatement(stmt);
		}
		
		return flag;
	}

	@Override
	public boolean checkDuplicateCategory(Connection connection,
			String categoryName) {
		boolean flag = false;
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			stmt = connection.prepareStatement(CheckDuplicateCategoryQuery);
			stmt.setString(1, categoryName.toUpperCase());
			
			rs = stmt.executeQuery();
			if(rs.next()){
				flag = true;
			}
		}
		catch(SQLException e){
			logger.error("sql Exception in deleteCategory", e);
		}
		catch(Exception e){
			logger.error("unexpected Exception in deleteCategory", e);
		}
		finally{
			DBUtil.closePreparedStatement(stmt);
		}
		
		return flag;
	}

	@Override
	public boolean checkDuplicateSubCategory(Connection connection,
			String categoryName) {
		boolean flag = false;
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			stmt = connection.prepareStatement(CheckDuplicateSubCategoryQuery);
			stmt.setString(1, categoryName.toUpperCase());
			
			rs = stmt.executeQuery();
			if(rs.next()){
				flag = true;
			}
		}
		catch(SQLException e){
			logger.error("sql Exception in deleteCategory", e);
		}
		catch(Exception e){
			logger.error("unexpected Exception in deleteCategory", e);
		}
		finally{
			DBUtil.closePreparedStatement(stmt);
		}
		
		return flag;
	}
	
	@Override
	public boolean checkDuplicateChildCategory(Connection connection,
			String categoryName) {
		boolean flag = false;
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			stmt = connection.prepareStatement(CheckDuplicateChildCategoryQuery);
			stmt.setString(1, categoryName.toUpperCase());
			
			rs = stmt.executeQuery();
			if(rs.next()){
				flag = true;
			}
		}
		catch(SQLException e){
			logger.error("sql Exception in deleteCategory", e);
		}
		catch(Exception e){
			logger.error("unexpected Exception in deleteCategory", e);
		}
		finally{
			DBUtil.closePreparedStatement(stmt);
		}
		
		return flag;
	}

	@Override
	public Map<String, String> getAllProducts(Connection connection) {
		PreparedStatement pstmt = null; 
        Map<String, String> allProducts = new HashMap<String, String>();
        
        try
        {
            pstmt = connection.prepareStatement(getAllProducts);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
            	String product_id = rs.getString("product_id");
            	String product_name = rs.getString("product_name");
                
            	allProducts.put(product_id, product_name);
            }
        }
        catch(SQLException e)
        {
            logger.error("error in inserting category into database", e);
        }
        finally{
        	DBUtil.closePreparedStatement(pstmt);
        }
        return allProducts;
	}

	@Override
	public boolean addProductVariants(Connection connection, String productId,
			String productPrices, String productSizes, String loginId,String offerPrice) {
		PreparedStatement pstmt = null; 
        
		boolean flag = false;
		
        try
        {
        	Map allId = getAllIdForProduct(connection, productId);
            
            pstmt = connection.prepareStatement(AddProductVariants);
            pstmt.setString(1, allId.get("rootCategory").toString());
            pstmt.setString(2, allId.get("subRootCategory").toString());
            pstmt.setString(3, allId.get("childCategory").toString());
            pstmt.setString(4, productId);
            pstmt.setString(5, productSizes);
            pstmt.setString(6, productPrices);
            pstmt.setString(7, offerPrice);
            pstmt.setString(8, loginId);
            
            int i = pstmt.executeUpdate();
            if(i>0){
            	flag = true;
            }
            
        }
        catch(SQLException e)
        {
            logger.error("error in inserting category into database", e);
        }
        finally{
        	DBUtil.closePreparedStatement(pstmt);
        }
        
		return flag;
	}

	public boolean checkDuplicateProductVariants(Connection connection,Map allIdAndDetail){
		
		boolean flag = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
        {
            pstmt = connection.prepareStatement(CheckDuplicateProductVariants);
            pstmt.setString(1, allIdAndDetail.get("rootCategory").toString());
            pstmt.setString(2, allIdAndDetail.get("subRootCategory").toString());
            pstmt.setString(3, allIdAndDetail.get("childCategory").toString());
            pstmt.setString(4, allIdAndDetail.get("productId").toString());
            pstmt.setString(5, allIdAndDetail.get("productSize").toString().toUpperCase());
            pstmt.setString(6, allIdAndDetail.get("productPrice").toString());
            //pstmt.setString(7, loginId);
            
            rs = pstmt.executeQuery();
            if(rs.next()){
            	flag = true;
            }
            
        }
        catch(SQLException e)
        {
            logger.error("error in inserting category into database", e);
        }
        finally{
        	DBUtil.closeResultSet(rs);
        	DBUtil.closePreparedStatement(pstmt);
        }
		
		return flag;
	}
	
	public Map<String, String> getAllIdForProduct(Connection connection,String productId){
		
		Map<String, String> allId = new HashMap<String, String>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
        {
            pstmt = connection.prepareStatement(getAllIDForProduct);
            pstmt.setString(1, productId);
            rs = pstmt.executeQuery();
            while(rs.next()){
            	allId.put("rootCategory", rs.getString("root_category"));
            	allId.put("subRootCategory", rs.getString("sub_root_category"));
            	allId.put("childCategory", rs.getString("category_id"));
            	
            }
        }
		catch (Exception e) {
			logger.error("", e);
		}
		finally{
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(pstmt);
		}
		
		return allId;
	}

	@Override
	public boolean checkOldPassword(Connection connection, String loginId,
			String oldPassword) {
		boolean flag = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			pstmt = connection.prepareStatement(CHECK_PASSWORD);
			pstmt.setString(1, loginId);
			pstmt.setString(2, oldPassword);
			rs = pstmt.executeQuery();
			if(rs.next()){
				flag = true;			
			}
			
		}
		catch (Exception e) {
			logger.error("",e);
		}
		finally{
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(pstmt);
		}
		return flag;
	}

	@Override
	public boolean updatePassword(Connection connection, String loginId,
			String newPassword) {
		boolean flag = false;
		PreparedStatement pstmt = null;
		
		try{
			pstmt = connection.prepareStatement(CHANGE_PASSWORD);
			pstmt.setString(1, newPassword);
			pstmt.setString(2, loginId);
			int i = pstmt.executeUpdate();
			if(i>0){
				flag = true;			
			}
			
		}
		catch (Exception e) {
			logger.error("",e);	
		}
		finally{
			DBUtil.closePreparedStatement(pstmt);
		}
		return flag;
	}

	@Override
	public boolean acceptRejectPendingOrder(Connection connection,
			String cartId, String action) {
		boolean flag = false;
		Statement stmt = null;
		
		try{
			stmt = connection.createStatement();
			int i = stmt.executeUpdate("update order_detail set order_status = '"+action+"' where order_id = '"+cartId+"'");
			if(i>0)
				flag = true;
			
		}
		catch(SQLException e){
			logger.error("sql Exception in deleteCategory", e);
		}
		catch(Exception e){
			logger.error("unexpected Exception in deleteCategory", e);
		}
		finally{
			DBUtil.closeStatement(stmt);
		}
		
		return flag;
	}

	@Override
	public String getEmailIdForOrderId(Connection connection, String orderId) {

		String emailId = "";
		PreparedStatement pstmt = null;
		try{
			pstmt = connection.prepareStatement("select emailid from order_detail where order_id = ?");
			pstmt.setString(1, orderId);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				emailId = rs.getString("emailid");
			}
			
		}
		catch (Exception e) {
			logger.error("", e);	
		}
		return emailId;
	}

	@Override
	public List<String> getAllImages(Connection connection) {
		
		List<String> imagesNames = new ArrayList<String>();
		PreparedStatement pstmt = null;
		try{
			pstmt = connection.prepareStatement("select image_path from product_info");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				String imageName = rs.getString(1);
				imagesNames.add(imageName.toUpperCase());
			}
		}
		catch (Exception e) {
			logger.error("",e);
		}
		
		return imagesNames;
	}

	@Override
	public List checkAllImages(Connection connection) {
		
		ArrayList al = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = connection.prepareStatement("select * from product_info");
			rs = pstmt.executeQuery();
			while(rs.next()){
				String imagePath = rs.getString("image_path");
				String productId = rs.getString("product_id");
				String productName = rs.getString("product_name");
				String productBrandNam = rs.getString("product_brand_name");
				
				String prodDetail[] = new String[4];
				prodDetail[0] = productId;
				prodDetail[1] = productName;
				prodDetail[2] = productBrandNam;
				prodDetail[3] = imagePath;
				
				al.add(prodDetail);
			}
			
		}
		catch (Exception e) {
			logger.error("Exception in checkAllImages"+e);
		}
		finally{
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(pstmt);
		}
		
		return al;
	}

	@Override
	public Map getOrderDetailAfterDeliver(Connection connection) {

        PreparedStatement pstmt1 = null;
        ResultSet rs1 = null;
        Map OrderDetailMap = new HashMap();
        try
        {
            pstmt1 = connection.prepareStatement("select distinct(order_id) from order_detail where order_status='C' order by order_date");
            rs1 = pstmt1.executeQuery();
            while(rs1.next())
            {
            	ArrayList orderDetail = new ArrayList();
                
                //int cartId = rs1.getInt(1);
                String orderId = rs1.getString(1);
                String address = "";
                PreparedStatement pstmt = connection.prepareStatement("select * from order_detail where order_status = ? and order_id=?");
                pstmt.setString(1, "C");
                pstmt.setString(2, orderId);
                ResultSet rs = pstmt.executeQuery();
                while(rs.next())
                {
                    String arr[] = new String[11];
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
                    orderDetail.add(arr);
                    address = (new StringBuilder(String.valueOf(arr[0]))).append("#").append(arr[1]).append("#").append(rs.getString("ORDER_DATE")).toString();
                }
                rs.close();
                pstmt.close();
                OrderDetailMap.put((new StringBuilder(String.valueOf(orderId))).append("#").append(address).toString(), orderDetail);
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
	
	
	
}

