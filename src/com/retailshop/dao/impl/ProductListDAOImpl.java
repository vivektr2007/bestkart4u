package com.retailshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.retailshop.dao.IProductListDAO;
import com.retailshop.util.DBUtil;

public class ProductListDAOImpl implements IProductListDAO {

	private static final Logger logger = Logger.getLogger(ProductListDAOImpl.class.getName());
	
	
	
	@Override
	public Map<String, ArrayList<String>> getProductListForCategory(Connection connection,String categoryId, int startIndex) {
		
		Map<String,ArrayList<String>> productList = new HashMap<String, ArrayList<String>>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		if(startIndex == 0 || startIndex == 1){
			startIndex = 0;
		}
		else{
			startIndex = (startIndex-1)*12;
		}
		try{
			stmt = connection.prepareStatement(PRODUCT_LISTQUERY_FOR_CATEGORY);
			stmt.setString(1, categoryId);
			stmt.setInt(2, startIndex);
			stmt.setInt(3, 12);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				
				String productId = rs.getString("product_id");
				String productName = rs.getString("product_name");
				String productBrandName = rs.getString("product_brand_name");
				String actualPrice = rs.getString("actual_price");
				String offerPrice = rs.getString("offer_price");
				String imagePath = rs.getString("image_path");
				
				ArrayList al = new ArrayList();
				al.add(productId);
				al.add(productName);
				al.add(productBrandName);
				al.add(actualPrice);
				al.add(offerPrice);
				al.add(imagePath);
				
				ArrayList<String[]> priceDetail = getPriceDetail(connection, productId);
				al.add(priceDetail);
				
				productList.put(productName+"#"+productId, al);
			}
			
		}
		catch(SQLException se){
			logger.error("Exception while getting productList for categoryId ["+categoryId+"]",se);
		}
		catch(Exception e){
			logger.error("Unexpected Exception while getting productList for categoryId ["+categoryId+"]",e);
		}
		finally{
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(stmt);
		}
		
		return productList;
	}
	
	public ArrayList<String[]> getPriceDetail(Connection connection,
			String productId) {
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<String[]> al = new ArrayList<String[]>();
		
		String PRODUCT_PRICE_DETAIL_FOR_CATEGORY = "SELECT PRODUCT_INFO, PRODUCT_PRICE, OFFER_PRICE FROM product_price_detail WHERE PRODUCT_ID = ? ";
		
		try{
			stmt = connection.prepareStatement(PRODUCT_PRICE_DETAIL_FOR_CATEGORY);
			stmt.setString(1, productId);
			//stmt.setString(4, childCategory);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				
				String productInfo = rs.getString("product_info");
				String productPrice = rs.getString("product_price");
				String offerPrice = rs.getString("offer_price");
				
				String arr[] = new String[3];
				arr[0] = productInfo;
				arr[1] = productPrice;
				arr[2] = offerPrice;
				
				al.add(arr);
			}
			
		}
		catch(SQLException se){
			logger.error("Exception while getting priceDetail for productId ["+productId+"]",se);
		}
		catch(Exception e){
			logger.error("Unexpected Exception while getting priceDetail for productId ["+productId+"]",e);
		}
		finally{
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(stmt);
		}
		
		return al;
	}
	
	@Override
	public Map<String, ArrayList<String>> getProductList(Connection connection,String categoryId, String rootCategory, String subRootCategory) {
		
		Map<String,ArrayList<String>> productList = new HashMap<String, ArrayList<String>>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try{
			stmt = connection.prepareStatement(PRODUCT_LISTQUERY);
			stmt.setString(1, categoryId);
			stmt.setString(2, rootCategory);
			stmt.setString(3, subRootCategory);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				
				String productId = rs.getString("product_id");
				String productName = rs.getString("product_name");
				String productBrandName = rs.getString("product_brand_name");
				String actualPrice = rs.getString("actual_price");
				String offerPrice = rs.getString("offer_price");
				String imagePath = rs.getString("image_path");
				
				ArrayList al = new ArrayList();
				al.add(productId);
				al.add(productName);
				al.add(productBrandName);
				al.add(actualPrice);
				al.add(offerPrice);
				al.add(imagePath);
				
				ArrayList<String[]> priceDetail = getPriceDetail(connection, productId,rootCategory, subRootCategory);
				al.add(priceDetail);
				
				productList.put(productName+"#"+productId, al);
			}
			
		}
		catch(SQLException se){
			logger.error("Exception while getting productList for categoryId ["+categoryId+"]",se);
		}
		catch(Exception e){
			logger.error("Unexpected Exception while getting productList for categoryId ["+categoryId+"]",e);
		}
		finally{
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(stmt);
		}
		
		return productList;
	}

	@Override
	public ArrayList<String[]> getPriceDetail(Connection connection,
			String categoryId, String rootCategory, String subRootCategory) {
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<String[]> al = new ArrayList<String[]>();
		
		try{
			String PRODUCT_PRICE_DETAIL = "SELECT PRODUCT_INFO, PRODUCT_PRICE, OFFER_PRICE FROM product_price_detail WHERE PRODUCT_ID = '"+categoryId+"'";
			/*if(rootCategory != null && !rootCategory.equals("") && !rootCategory.equalsIgnoreCase("null")){
				PRODUCT_PRICE_DETAIL = PRODUCT_PRICE_DETAIL + " and root_category = '"+rootCategory+"' ";
			}
			if(subRootCategory != null && !subRootCategory.equals("") && !subRootCategory.equalsIgnoreCase("null")){
				PRODUCT_PRICE_DETAIL = PRODUCT_PRICE_DETAIL + " and sub_root_category = '"+subRootCategory+"' ";
			}*/
			
		//	System.out.println(PRODUCT_PRICE_DETAIL);
			
			stmt = connection.prepareStatement(PRODUCT_PRICE_DETAIL);
			/*stmt.setString(1, categoryId);
			stmt.setString(2, rootCategory);
			stmt.setString(3, subRootCategory);
			*///stmt.setString(4, childCategory);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				
				String productInfo = rs.getString("product_info");
				String productPrice = rs.getString("product_price");
				String offerPrice = rs.getString("OFFER_PRICE");
				
				
				String arr[] = new String[3];
				arr[0] = productInfo;
				arr[1] = productPrice;
				arr[2] = offerPrice;
				
				al.add(arr);
			}
			
		}
		catch(SQLException se){
			logger.error("Exception while getting priceDetail for productId ["+categoryId+"]",se);
		}
		catch(Exception e){
			logger.error("Unexpected Exception while getting priceDetail for productId ["+categoryId+"]",e);
		}
		finally{
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(stmt);
		}
		
		return al;
	}

	
	@Override
	public Map<String, ArrayList<String>> getProductListPagination(Connection connection,String categoryId,int startIndex, int endIndex, String rootCategory, String subRootCategory) {
		
		Map<String,ArrayList<String>> productList = new HashMap<String, ArrayList<String>>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String PRODUCT_LISTQUERY_pagination = "SELECT * FROM product_info WHERE ROOT_CATEGORY = '"+rootCategory+"' ";
		if(categoryId != null && !categoryId.equals("") && !categoryId.equalsIgnoreCase("null") ){
			PRODUCT_LISTQUERY_pagination = PRODUCT_LISTQUERY_pagination + " and CATEGORY_ID='"+categoryId+"'";
		}
		if(subRootCategory != null && !subRootCategory.equals("") && !subRootCategory.equalsIgnoreCase("null") ){
			PRODUCT_LISTQUERY_pagination = PRODUCT_LISTQUERY_pagination + " AND SUB_ROOT_CATEGORY ='"+subRootCategory+"'";
		}
		
		PRODUCT_LISTQUERY_pagination = PRODUCT_LISTQUERY_pagination + " limit "+startIndex+","+endIndex;
		
		try{
			stmt = connection.prepareStatement(PRODUCT_LISTQUERY_pagination);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				
				String productId = rs.getString("product_id");
				String productName = rs.getString("product_name");
				String productBrandName = rs.getString("product_brand_name");
				String actualPrice = rs.getString("actual_price");
				String offerPrice = rs.getString("offer_price");
				String imagePath = rs.getString("image_path");
				
				ArrayList al = new ArrayList();
				al.add(productId);
				al.add(productName);
				al.add(productBrandName);
				al.add(actualPrice);
				al.add(offerPrice);
				al.add(imagePath);
				
				ArrayList<String[]> priceDetail = getPriceDetail(connection, productId, rootCategory, subRootCategory);
				al.add(priceDetail);
				
				productList.put(productName+"#"+productId, al);
			}
			
		}
		catch(SQLException se){
			logger.error("Exception while getting productListPagination for categoryId ["+categoryId+"]",se);
		}
		catch(Exception e){
			logger.error("Unexpected Exception while getting productListPagination for categoryId ["+categoryId+"]",e);
		}
		finally{
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(stmt);
		}
		
		return productList;
	}

	@Override
	public int getProductCount(Connection connection, String categoryId, String rootCategory, String subRootCategory) {
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int count = 0;
		
		String PRODUCT_COUNT_SIDEBAR = "SELECT COUNT(*) FROM product_info WHERE ROOT_CATEGORY = '"+rootCategory+"'";
		if(subRootCategory != null && !subRootCategory.equals("") && !subRootCategory.equalsIgnoreCase("null")){
			PRODUCT_COUNT_SIDEBAR = PRODUCT_COUNT_SIDEBAR + " AND SUB_ROOT_CATEGORY = '"+subRootCategory+"'";
		}
		if(categoryId != null && !categoryId.equals("") && !categoryId.equalsIgnoreCase("null")){
			
			PRODUCT_COUNT_SIDEBAR = PRODUCT_COUNT_SIDEBAR + " AND CATEGORY_ID = '"+categoryId+"'";
		}
		try{
			stmt = connection.prepareStatement(PRODUCT_COUNT_SIDEBAR);
			/*stmt.setString(1, categoryId);
			stmt.setString(2, rootCategory);
			stmt.setString(3, subRootCategory);
			*/
			rs = stmt.executeQuery();
			while(rs.next()){
				count = rs.getInt(1);
			}
			
		}
		catch(SQLException se){
			logger.error("Exception while getting productCount for categoryId ["+categoryId+"]",se);
		}
		catch(Exception e){
			logger.error("Unexpected Exception while getting productCount for categoryId ["+categoryId+"]",e);
		}
		finally{
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(stmt);
		}
		
		return count;
	}

	@Override
	public Map productDetail(Connection connection, String productId, String rootCategory, String subRootCategory) {
		
		PreparedStatement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		Map productDetails = new HashMap();
		
		String PRODUCT_DETAIL = "SELECT PI.PRODUCT_NAME,PI.PRODUCT_BRAND_NAME,PI.ACTUAL_PRICE,PI.OFFER_PRICE,PI.IMAGE_PATH, PI.ABOUT_PRODUCT,PI.INGREDIENT FROM product_info PI ";
		if(productId != null && !productId.equals("")){
			PRODUCT_DETAIL = PRODUCT_DETAIL + " where PRODUCT_ID ='" + productId+"'" ;
		}
		/*if(rootCategory != null && !rootCategory.equals("")){
			PRODUCT_DETAIL = PRODUCT_DETAIL + " and ROOT_CATEGORY ='" + rootCategory+"'" ;
		}
		if(subRootCategory != null && !subRootCategory.equals("")){
			PRODUCT_DETAIL = PRODUCT_DETAIL + " and SUB_ROOT_CATEGORY ='" + subRootCategory+"'" ;
		}*/
		try{
			stmt = connection.prepareStatement(PRODUCT_DETAIL);
			//stmt.setString(1, productId);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				productDetails.put("productName", rs.getString("PRODUCT_NAME"));
				productDetails.put("productBrandName", rs.getString("PRODUCT_BRAND_NAME"));
				productDetails.put("actualPrice", rs.getString("ACTUAL_PRICE"));
				productDetails.put("offerPrice", rs.getString("OFFER_PRICE"));
				productDetails.put("imagePath", rs.getString("IMAGE_PATH"));
				productDetails.put("productId", productId);
				productDetails.put("aboutProduct", rs.getString("about_product"));
				productDetails.put("ingredient", rs.getString("ingredient"));
				
			}
			
			String PRODUCT_PRICE_DETAIL = "SELECT PRODUCT_INFO, PRODUCT_PRICE, offer_price FROM product_price_detail WHERE PRODUCT_ID = '"+productId+"'";
			/*if(rootCategory != null && !rootCategory.equals("") && !rootCategory.trim().equalsIgnoreCase(null)){
				PRODUCT_PRICE_DETAIL = PRODUCT_PRICE_DETAIL + " and root_category = '"+rootCategory+"' "; 
			}
			if(subRootCategory != null && !subRootCategory.equals("") && !subRootCategory.trim().equalsIgnoreCase(null)){
				PRODUCT_PRICE_DETAIL = PRODUCT_PRICE_DETAIL + " and sub_root_category ='"+subRootCategory+"'"; 
			}*/
			pstmt = connection.prepareStatement(PRODUCT_PRICE_DETAIL);
			//pstmt.setString(4, childCategory);
			
			
			rs1 = pstmt.executeQuery();
			
			ArrayList al = new ArrayList();
			
			while(rs1.next()){
				String arr[] = new String[3];
				arr[0] = rs1.getString("PRODUCT_INFO");
				arr[1] = rs1.getString("product_price");
				arr[2] = rs1.getString("offer_price");
				
				al.add(arr);
				
			}
			productDetails.put("productPriceDetail", al);
		}
		catch(SQLException se){
			logger.error("Exception while getting productDetail for productId ["+productId+"]",se);
		}
		catch(Exception e){
			logger.error("Unexpected Exception while getting productDetail for productId ["+productId+"]",e);
		}
		finally{
			DBUtil.closeResultSet(rs);
			DBUtil.closeResultSet(rs1);
			DBUtil.closePreparedStatement(stmt);
			DBUtil.closePreparedStatement(pstmt);
		}
		
		return productDetails;
	}
	
	
	public Map<String,ArrayList> getRelatedProduct(Connection connection,String productId, String rootCategory, String subRootCategory){
		
		String categoryId="";
		Map<String,ArrayList> productList = new HashMap<String, ArrayList>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try{
			
			Statement stmt1 = connection.createStatement();
			String GET_CATEGORY_ID = "SELECT DISTINCT(CATEGORY_ID) FROM product_info WHERE PRODUCT_ID = '"+productId+"'";
			
			
			ResultSet rs1 = stmt1.executeQuery(GET_CATEGORY_ID);
			if(rs1.next()){
				categoryId = rs1.getString("CATEGORY_ID");
			}
			
			stmt = connection.prepareStatement(PRODUCT_LIST_QUERY);
			stmt.setString(1, categoryId);
			stmt.setString(2, rootCategory);
			stmt.setString(3, subRootCategory);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				productId = rs.getString("product_id");
				String productName = rs.getString("product_name");
				String productBrandName = rs.getString("product_brand_name");
				String actualPrice = rs.getString("actual_price");
				String offerPrice = rs.getString("offer_price");
				String imagePath = rs.getString("image_path");
				
				ArrayList al = new ArrayList();
				al.add(productId);
				al.add(productName);
				al.add(productBrandName);
				al.add(actualPrice);
				al.add(offerPrice);
				al.add(imagePath);
				
				ArrayList<String[]> priceDetail = getPriceDetail(connection, productId, rootCategory, subRootCategory);
				al.add(priceDetail);
				
				productList.put(productName+"#"+productId, al);
			}
			
		}
		catch(SQLException se){
			logger.error("Exception while getting productList for categoryId ["+categoryId+"]",se);
		}
		catch(Exception e){
			logger.error("Unexpected Exception while getting productList for categoryId ["+categoryId+"]",e);
		}
		finally{
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(stmt);
		}
		
		return productList;
	}
	
	@Override
	public Map<String,ArrayList> getRelatedBrandProduct(Connection connection,String productId, String rootCategory, String subRootCategpry){
		
		String brandName="";
		Map<String,ArrayList> productList = new HashMap<String, ArrayList>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try{
			
			Statement stmt1 = connection.createStatement();
			String GET_CATEGORY_ID = "SELECT DISTINCT(PRODUCT_BRAND_NAME) FROM product_info WHERE PRODUCT_ID = '"+productId+"'";
			
			
			ResultSet rs1 = stmt1.executeQuery(GET_CATEGORY_ID);
			if(rs1.next()){
				brandName = rs1.getString("PRODUCT_BRAND_NAME");
			}
			
			stmt = connection.prepareStatement(PRODUCT_LIST_FOR_BRAND_QUERY);
			stmt.setString(1, brandName.toUpperCase());
			
			rs = stmt.executeQuery();
			while(rs.next()){
				productId = rs.getString("product_id");
				String productName = rs.getString("product_name");
				String productBrandName = rs.getString("product_brand_name");
				String actualPrice = rs.getString("actual_price");
				String offerPrice = rs.getString("offer_price");
				String imagePath = rs.getString("image_path");
				
				ArrayList al = new ArrayList();
				al.add(productId);
				al.add(productName);
				al.add(productBrandName);
				al.add(actualPrice);
				al.add(offerPrice);
				al.add(imagePath);
				
				ArrayList<String[]> priceDetail = getPriceDetail(connection, productId, rootCategory, subRootCategpry);
				al.add(priceDetail);
				
				productList.put(productName+"#"+productId, al);
			}
			
		}
		catch(SQLException se){
			logger.error("Exception while getting productList for categoryId ["+productId+"]",se);
		}
		catch(Exception e){
			logger.error("Unexpected Exception while getting productList for categoryId ["+productId+"]",e);
		}
		finally{
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(stmt);
		}
		
		return productList;
	}

	@Override
	public Map getSibeBarProducts(Connection connection, String rootCategory, String subRootCategory) {
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		Map product = new HashMap();
		try {
			pstmt = connection.prepareStatement(CATEGORY_LIST_SIDE_BAR_QUERY);
			pstmt.setString(1, rootCategory);
			//pstmt.setString(2, subRootCategory);
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				String categoryId = rs.getString("SUB_CATEGORY_ID");
				String categoryDesc = rs.getString("SUB_CATEGORY_NAME");
				
				ArrayList al = new ArrayList();
				
				pstmt1 = connection.prepareStatement(CATEGORY_LIST_SIDE_BAR_COUNT);
				pstmt1.setString(1, rootCategory);
				pstmt1.setString(2, categoryId);
				//pstmt1.setString(3, categoryId);
				
				rs1 = pstmt1.executeQuery();
				
				while(rs1.next()){
					String productCount = rs1.getString("P_COUNT");
					String id = rs1.getString("SUB_CATEGORY_ID2");
					String categoryName = rs1.getString("SUB_CATEGORY_DESC");
					
					String arr[] = new String[3];
					arr[0] = id;
					arr[1] = productCount;
					arr[2] = categoryName;
					
					al.add(arr);
				}
				product.put(categoryId+"#"+categoryDesc, al);
			}
		} catch (SQLException e) {
			logger.error("Exception i getting sidebar products",e);
		}
		finally{
			DBUtil.closeResultSet(rs1);
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(pstmt1);
			DBUtil.closePreparedStatement(pstmt);
		}
		return product;
	}

	@Override
	public Map getDeliveryDetail(Connection connection, String userId) {
		
		PreparedStatement pstmt = null;
		Map address = new HashMap();		
		Map deliveryAddress = new HashMap();
		Map allAddress = new HashMap();
		
		ResultSet rs = null;
		
		try {
			pstmt = connection.prepareStatement(GET_DELIVERY_DETAIL);
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				address.put("FIRSTNAME", rs.getString("first_name"));
				address.put("LASTNAME", rs.getString("last_name"));
				address.put("CONTACTNO", rs.getString("mobile_no"));
				address.put("HOUSENO", rs.getString("HOUSE_NO"));
				address.put("STREETDETAIL", rs.getString("STREET_DETAIL"));
				address.put("AREA", rs.getString("AREA"));
				address.put("RESIDENTIALCOMPLEX", rs.getString("RESIDENTIAL_COMPLEX"));
				address.put("LANDMARK", rs.getString("LANDMARK"));
				address.put("CITY", rs.getString("CITY"));
				address.put("PINCODE", rs.getString("PIN_CODE"));
				
				deliveryAddress.put("DELIVERY_NICKNAME", rs.getString("delivery_nickname"));
				deliveryAddress.put("DELIVERY_FIRSTNAME",rs.getString("delivery_firstname"));
				deliveryAddress.put("DELIVERY_LASTNAME",rs.getString("delivery_lastname"));
				deliveryAddress.put("DELIVERY_CONTACTNO",rs.getString("delivery_contact_no"));
				deliveryAddress.put("DELIVERY_HOUSENO",rs.getString("delivery_houseno"));
				deliveryAddress.put("DELIVERY_STREETDETAIL",rs.getString("delivery_street_detail"));
				deliveryAddress.put("DELIVERY_RESIDENTIALCOMPLEX",rs.getString("delivery_residential_complex"));
				deliveryAddress.put("DELIVERY_LANDMARK",rs.getString("delivery_landmark"));
				deliveryAddress.put("DELIVERY_AREA",rs.getString("delivery_area"));
				deliveryAddress.put("DELIVERY_PINCODE",rs.getString("delivery_pincode"));
				deliveryAddress.put("DELIVERY_CITY",rs.getString("delivery_city"));
				
				
			}
			
			allAddress.put("PERMANENTADDRESS", address);
			allAddress.put("DELIVERYADDRESS", deliveryAddress);
			
		} catch (SQLException e) {
			logger.error("error in getting delivery detail", e);
		}
		finally{
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(pstmt);
		}
		
		return allAddress;
	}

	@Override
	public boolean addAddress(Connection connection, String userId,
			Map parameters) {
		
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		boolean flag = false;
		
		try{
			
			pstmt1 = connection.prepareStatement(moveAddress);
			pstmt1.setString(1, userId.toUpperCase());
			
			int x = pstmt1.executeUpdate();
			
			pstmt = connection.prepareStatement(UPDATE_USER_ADDRESS);
			
			pstmt.setString(1, (String)parameters.get("DELIVERY_NICKNAME"));
			pstmt.setString(2, (String)parameters.get("DELIVERY_FIRSTNAME"));
			pstmt.setString(3, (String)parameters.get("DELIVERY_LASTNAME"));
			pstmt.setString(4, (String)parameters.get("DELIVERY_CONTACTNO"));
			pstmt.setString(5, (String)parameters.get("DELIVERY_HOUSENO"));
			pstmt.setString(6, (String)parameters.get("DELIVERY_STREETDETAIL"));
			pstmt.setString(7, (String)parameters.get("DELIVERY_RESIDENTIALCOMPLEX"));
			pstmt.setString(8, (String)parameters.get("DELIVERY_LANDMARK"));
			pstmt.setString(9, (String)parameters.get("DELIVERY_AREA"));
			pstmt.setString(10, (String)parameters.get("DELIVERY_PINCODE"));
			pstmt.setString(11, (String)parameters.get("DELIVERY_CITY"));
			pstmt.setString(12, userId);
			
			int i = pstmt.executeUpdate();
			if(i>0){
				flag = true;
			}
			
		}
		catch(SQLException se){
			logger.error("error in inserting address : ",se);
		}
		catch(Exception e){
			logger.error("unexpected error : ", e);
		}
		finally{
			DBUtil.closePreparedStatement(pstmt);
			DBUtil.closePreparedStatement(pstmt1);
		}
		return flag;
		
	}

	@Override
	public boolean submitCart(Connection connection, String loginId,
			Map parameters) {
		PreparedStatement pstmt = null;
		boolean flag = false;
		
		try{
			
			pstmt = connection.prepareStatement(SUBMIT_CART);
			
			pstmt.setString(1, (String)parameters.get("EMAILID"));
			pstmt.setString(2, (String)parameters.get("DELIVERY_ADDRESS"));
			pstmt.setString(3, (String)parameters.get("SELECTED_SLOT"));
			pstmt.setString(4, (String)parameters.get("PAYMENT_MODE"));
			pstmt.setLong(5, (Long)parameters.get("CART_ID"));
			pstmt.setString(6, (String)parameters.get("PRODUCT_ID"));
			pstmt.setString(7, (String)parameters.get("PRODUCT_NAME"));
			pstmt.setString(8, (String)parameters.get("PRODUCT_BRAND_NAME"));
			//pstmt.setString(9, (String)parameters.get("ORIGINAL_PRICE"));
			pstmt.setString(9, (String)parameters.get("OFFER_PRICE"));
			pstmt.setString(10, (String)parameters.get("NO_OF_ITEM"));
			pstmt.setString(11, (String)parameters.get("TOTAL_AMOUNT"));
			pstmt.setString(12, (String)parameters.get("IMAGE_PATH"));
			pstmt.setString(13, (String)parameters.get("PRODUCT_SIZE"));
			pstmt.setString(14, "P");
			pstmt.setString(15, (String)parameters.get("DELIVERY_DATE"));
			pstmt.setString(16, (String)parameters.get("ORDERID"));
			
			
			int i = pstmt.executeUpdate();
			if(i>0){
				flag = true;
			}
			
		}
		catch(SQLException se){
			logger.error("error in inserting address : ",se);
		}
		catch(Exception e){
			logger.error("unexpected error : ", e);
		}
		
		return flag;
	}
	
	public long getCartId(Connection connection){
		
		PreparedStatement pstmt = null;
		long cartId = 1;
		
		try{
			pstmt = connection.prepareStatement(GET_CART_ID);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				cartId = rs.getLong(1)+1;
			}
			
		}
		catch(SQLException se){
			logger.error("error in inserting address : ",se);
		}
		catch(Exception e){
			logger.error("unexpected error : ", e);
		}
		
		return cartId;
	}

	@Override
	public Map getSubmitCartDetail(Connection connection, String userId,
			long cartId) {
		PreparedStatement pstmt = null;
		Map submitCartDetail = new HashMap();		
		ArrayList cartDetail = new ArrayList();
		String address = "";
		ResultSet rs = null;
		
		try {
			pstmt = connection.prepareStatement(GET_SUBMIT_CART_DETAIL);
			pstmt.setLong(1, cartId);
			pstmt.setString(2, userId);
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				
				String arr[] = new String[7];
				
				arr[0] = rs.getString("PRODUCT_NAME");
				arr[1] = rs.getString("PRODUCT_BRAND_NAME");
				arr[2] = rs.getString("PRODUCT_SIZE");
				arr[3] = rs.getString("OFFER_PRICE");
				arr[4] = rs.getString("NO_OF_ITEM");
				arr[5] = rs.getString("TOTAL_AMOUNT");
				arr[6] = rs.getString("order_id");
				
				address = rs.getString("DELIVERY_ADDRESS");
				
				cartDetail.add(arr);
			}
			
			Map orderSummary = new HashMap();
			
			pstmt = connection.prepareStatement("select * from order_detail_summary where upper(user_id)= ? and cart_id = ?");
			pstmt.setString(1, userId.toUpperCase());
			pstmt.setLong(2, cartId);
			
			
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				
				orderSummary.put("orderId", rs.getString("order_id"));
				orderSummary.put("totalAmount", rs.getString("total_amount"));
				orderSummary.put("discount", rs.getString("discount"));
				orderSummary.put("couponCode", rs.getString("coupon_code"));
				orderSummary.put("amountToPay", rs.getString("amount_to_pay"));
				orderSummary.put("deliveryCharge", rs.getString("delivery_charge"));
				
			}
			
			submitCartDetail.put("ADDRESS", address);
			submitCartDetail.put("PRODUCTDETAIL", cartDetail);
			submitCartDetail.put("orderSummary", orderSummary);
			
		} catch (SQLException e) {
			logger.error("error in getting delivery detail", e);
		}
		finally{
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(pstmt);
		}
		
		return submitCartDetail;
	}
	
	@Override
	public Map<String, ArrayList<String>> getProductListSideBar(Connection connection,String categoryId, String rootCategory, String subRootCategory) {
		
		Map<String,ArrayList<String>> productList = new HashMap<String, ArrayList<String>>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String PRODUCT_LISTQUERY_SIDEBAR = "SELECT * FROM product_info WHERE ROOT_CATEGORY='"+rootCategory+"'"; //limit 0,5";
		
		try{
			//stmt.setString(1, rootCategory);
			if(subRootCategory != null && !subRootCategory.equals("") && !subRootCategory.equalsIgnoreCase("null") ){
				PRODUCT_LISTQUERY_SIDEBAR = PRODUCT_LISTQUERY_SIDEBAR + " AND SUB_ROOT_CATEGORY='"+subRootCategory+"'";
			}
			if(categoryId != null && !categoryId.equals("") && !categoryId.equalsIgnoreCase("null") ){
				PRODUCT_LISTQUERY_SIDEBAR = PRODUCT_LISTQUERY_SIDEBAR + " AND CATEGORY_ID='"+categoryId+"'";
			}
			PRODUCT_LISTQUERY_SIDEBAR = PRODUCT_LISTQUERY_SIDEBAR + " limit 0,12";
			stmt = connection.prepareStatement(PRODUCT_LISTQUERY_SIDEBAR);
			
			/*
			stmt.setString(1, categoryId);
			
			stmt.setString(3, subRootCategory);
			*/
			rs = stmt.executeQuery();
			while(rs.next()){
				
				String productId = rs.getString("product_id");
				String productName = rs.getString("product_name");
				String productBrandName = rs.getString("product_brand_name");
				String actualPrice = rs.getString("actual_price");
				String offerPrice = rs.getString("offer_price");
				String imagePath = rs.getString("image_path");
				
				ArrayList al = new ArrayList();
				al.add(productId);
				al.add(productName);
				al.add(productBrandName);
				al.add(actualPrice);
				al.add(offerPrice);
				al.add(imagePath);
				
				ArrayList<String[]> priceDetail = getPriceDetailSideBar(connection, productId,rootCategory, subRootCategory);
				al.add(priceDetail);
				
				productList.put(productName+"#"+productId, al);
			}
			
		}
		catch(SQLException se){
			logger.error("Exception while getting productList for categoryId ["+categoryId+"]",se);
		}
		catch(Exception e){
			logger.error("Unexpected Exception while getting productList for categoryId ["+categoryId+"]",e);
		}
		finally{
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(stmt);
		}
		
		return productList;
	}
	
	public ArrayList<String[]> getPriceDetailSideBar(Connection connection,
			String categoryId, String rootCategory, String subRootCategory) {
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<String[]> al = new ArrayList<String[]>();
		
		String PRODUCT_PRICE_SIDEBAR = "SELECT PRODUCT_INFO, PRODUCT_PRICE, OFFER_PRICE FROM product_price_detail WHERE PRODUCT_ID = '"+categoryId+"' and ROOT_CATEGORY='"+rootCategory+"'"; //limit 0,5";
		
		//stmt.setString(1, rootCategory);
		if(subRootCategory != null && !subRootCategory.equals("") && !subRootCategory.equalsIgnoreCase("null") ){
			PRODUCT_PRICE_SIDEBAR = PRODUCT_PRICE_SIDEBAR + " AND SUB_ROOT_CATEGORY='"+subRootCategory+"'";
		}
		/*if(categoryId != null && !categoryId.equals("")  ){
			PRODUCT_PRICE_SIDEBAR = PRODUCT_PRICE_SIDEBAR + " AND CATEGORY_ID='"+categoryId+"'";
		}*/
		
		try{
			stmt = connection.prepareStatement(PRODUCT_PRICE_SIDEBAR);
			/*stmt.setString(1, categoryId);
			stmt.setString(2, rootCategory);
			stmt.setString(3, subRootCategory);
			*///stmt.setString(4, childCategory);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				
				String productInfo = rs.getString("product_info");
				String productPrice = rs.getString("product_price");
				String offerPrice = rs.getString("OFFER_PRICE");
				
				
				
				String arr[] = new String[3];
				arr[0] = productInfo;
				arr[1] = productPrice;
				arr[2] = offerPrice;
				
				al.add(arr);
			}
			
		}
		catch(SQLException se){
			logger.error("Exception while getting priceDetail for productId ["+categoryId+"]",se);
		}
		catch(Exception e){
			logger.error("Unexpected Exception while getting priceDetail for productId ["+categoryId+"]",e);
		}
		finally{
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(stmt);
		}
		
		return al;
	}

	@Override
	public Map productDetailForCategory(Connection connection, String productId) {
		PreparedStatement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		Map productDetails = new HashMap();
		
		String PRODUCT_DETAIL = "SELECT PI.PRODUCT_NAME,PI.PRODUCT_BRAND_NAME,PI.ACTUAL_PRICE,PI.OFFER_PRICE,PI.IMAGE_PATH, PI.ABOUT_PRODUCT,PI.INGREDIENT FROM product_info PI ";
		if(productId != null && !productId.equals("")){
			PRODUCT_DETAIL = PRODUCT_DETAIL + " where PRODUCT_ID ='" + productId+"'" ;
		}
		try{
			stmt = connection.prepareStatement(PRODUCT_DETAIL);
			//stmt.setString(1, productId);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				productDetails.put("productName", rs.getString("PRODUCT_NAME"));
				productDetails.put("productBrandName", rs.getString("PRODUCT_BRAND_NAME"));
				productDetails.put("actualPrice", rs.getString("ACTUAL_PRICE"));
				productDetails.put("offerPrice", rs.getString("OFFER_PRICE"));
				productDetails.put("imagePath", rs.getString("IMAGE_PATH"));
				productDetails.put("productId", productId);
				productDetails.put("aboutProduct", rs.getString("about_product"));
				productDetails.put("ingredient", rs.getString("ingredient"));
				
			}
			pstmt = connection.prepareStatement(PRODUCT_PRICE_DETAIL_FOR_CATEGORY);
			pstmt.setString(1, productId);
			//pstmt.setString(4, childCategory);
			
			
			rs1 = pstmt.executeQuery();
			
			ArrayList al = new ArrayList();
			
			while(rs1.next()){
				String arr[] = new String[2];
				arr[0] = rs1.getString("PRODUCT_INFO");
				arr[1] = rs1.getString("product_price");
				al.add(arr);
				
			}
			productDetails.put("productPriceDetail", al);
		}
		catch(SQLException se){
			logger.error("Exception while getting productDetail for productId ["+productId+"]",se);
		}
		catch(Exception e){
			logger.error("Unexpected Exception while getting productDetail for productId ["+productId+"]",e);
		}
		finally{
			DBUtil.closeResultSet(rs);
			DBUtil.closeResultSet(rs1);
			DBUtil.closePreparedStatement(stmt);
			DBUtil.closePreparedStatement(pstmt);
		}
		
		return productDetails;
	}

	@Override
	public boolean addToWishlist(Connection connection, int productId,
			String loginId, int quantity) {
		PreparedStatement stmt = null;
		boolean flag = false;
		
		try{
			
			String query = "insert into wishlist(user_id, product_id, quantity) values(?,?,?)";
			
			stmt = connection.prepareStatement(query);
			stmt.setString(1, loginId);
			stmt.setInt(2, productId);
			stmt.setInt(3, quantity);
			
			int i = stmt.executeUpdate();
			if(i>0){
				flag = true;
			}
		}
		catch(SQLException se){
			logger.error("Exception while getting productDetail for productId ["+productId+"]",se);
		}
		catch(Exception e){
			logger.error("Unexpected Exception while getting productDetail for productId ["+productId+"]",e);
		}
		finally{
			DBUtil.closePreparedStatement(stmt);
		}
		
		return flag;
	}

	@Override
	public String getWishlistProducts(Connection connection, String loginId) {
		PreparedStatement stmt = null;
		boolean flag = false;
		ArrayList products = new ArrayList();
		String value = "";
		try{
			String query = "select distinct(product_id), quantity from wishlist where upper(user_id) = ?";
			
			stmt = connection.prepareStatement(query);
			stmt.setString(1, loginId.toUpperCase());
			ResultSet productIdRs = stmt.executeQuery();
			while(productIdRs.next()){
			
				int quantity = productIdRs.getInt(2);
				int productId = productIdRs.getInt(1);
				
				
				String detailQuery  = "select * from product_info where product_id='"+productId+"'";
				PreparedStatement stmt1 = connection.prepareStatement(detailQuery);
				/*stmt1.setString(1, ""+productId);*/
				ResultSet productDetailRs = stmt1.executeQuery(detailQuery);
				while(productDetailRs.next()){
					
					String rootCategory = productDetailRs.getString("root_category");
					String subRootCategory = productDetailRs.getString("sub_root_category");
					String childCategory = productDetailRs.getString("category_id");
					String productImage = productDetailRs.getString("image_path");
					String productName = productDetailRs.getString("product_name");
					String productBrandName = productDetailRs.getString("product_brand_name");
					String actualPrice = productDetailRs.getString("actual_price");
					String offerPrice = productDetailRs.getString("offer_price");
					String productSize = productDetailRs.getString("product_size");
					
					String productVariantsQuery = "select * from product_price_detail where product_id = ?";
					value = value + rootCategory + "#" + subRootCategory + "#" + childCategory + "#" + productImage + "#" + productName + "#" +
					productBrandName + "#" + actualPrice + "#" + offerPrice + "#" + productSize + "#" +quantity;
					PreparedStatement stmt2 = connection.prepareStatement(productVariantsQuery);
					stmt2.setInt(1, productId);
					ResultSet rs = stmt2.executeQuery();
					String productPriceDetail = "";
					while(rs.next()){
						String productInfo = rs.getString("product_info");
						String productPrice = rs.getString("product_price");
						
						productPriceDetail = "$$" + productPriceDetail + "$" + productInfo + "$" + productPrice;
					}
					
					if(productPriceDetail.indexOf("$$") != -1){
						productPriceDetail = productPriceDetail.substring(3);
					}
					value = value + "#" + productPriceDetail +"#"+productId+ "##";
					
					DBUtil.closeResultSet(rs);
					DBUtil.closePreparedStatement(stmt2);
					
				}
				DBUtil.closeResultSet(productDetailRs);
				DBUtil.closePreparedStatement(stmt1);
				
			}
		}
		catch(SQLException se){
			logger.error("Exception while getting productDetail for productId ",se);
		}
		catch(Exception e){
			logger.error("Unexpected Exception while getting productDetail for productId ",e);
		}
		finally{
			DBUtil.closePreparedStatement(stmt);
		}
		if(value != null && value.length() > 2){
			value = value.substring(0, value.length()-2);
		}
		return value;
	}

	@Override
	public boolean removeProductFromWishlist(Connection connection,
			String loginId, String productId) {
		PreparedStatement pstmt = null;
		boolean flag = false;
		
		try{
			String removeFromWishllist = "delete from wishlist where product_id = ? and upper(user_id) = ?";
			
			pstmt = connection.prepareStatement(removeFromWishllist);
			
			pstmt.setString(1, productId);
			pstmt.setString(2, loginId.toUpperCase());
			
			int i = pstmt.executeUpdate();
			if(i>0){
				flag = true;
			}
			
		}
		catch(SQLException se){
			logger.error("error in inserting address : ",se);
		}
		catch(Exception e){
			logger.error("unexpected error : ", e);
		}
		return flag;
	}

	@Override
	public List getAllBrandForProduct(Connection connection,
			String rootCategory, String subRootCategory, String childCategory) {
		String getAllBrandForProductQuery = "select distinct(upper(product_brand_name)) brand_name from product_info";
		
		if(rootCategory != null && !rootCategory.equals("") && !rootCategory.equals("0") && !rootCategory.equalsIgnoreCase("null")){
			getAllBrandForProductQuery = getAllBrandForProductQuery + " where root_category = '"+rootCategory+"'";
		}
		if(subRootCategory != null && !subRootCategory.equals("") && !subRootCategory.equals("0")  && !subRootCategory.equalsIgnoreCase("null")){
			getAllBrandForProductQuery = getAllBrandForProductQuery + " and sub_root_category = '"+subRootCategory+"'";
		}
		if(childCategory != null && !childCategory.equals("") && !childCategory.equals("0") &&!childCategory.equalsIgnoreCase("null")){
			getAllBrandForProductQuery = getAllBrandForProductQuery + " and category_id = '"+childCategory+"'";
		}
		
		ArrayList allBrand = new ArrayList();
		
		Statement stmt = null;
		ResultSet rs = null;
		try{
			stmt = connection.createStatement();
			rs = stmt.executeQuery(getAllBrandForProductQuery);
			while(rs.next()){
				String brandName = rs.getString(1);
				allBrand.add(brandName);
			}
			
		}
		catch (Exception e) {
			logger.error("", e);
		}
		finally{
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(stmt);
		}
		return allBrand;
	}

	@Override
	public Map<String, ArrayList<String>> getProductListForBrand(
			Connection connection, String rootCategory, String subRootCategory,
			String childCategory, String brandName, int startIndex, int endIndex, String fromfooter) {
		Map<String,ArrayList<String>> productList = new HashMap<String, ArrayList<String>>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String PRODUCT_LISTQUERY_pagination = "SELECT * FROM product_info ";
		
		String whereClause= "";
		
		if(rootCategory != null && !rootCategory.equals("") && !rootCategory.equalsIgnoreCase("null") ){
			//PRODUCT_LISTQUERY_pagination = PRODUCT_LISTQUERY_pagination + " and CATEGORY_ID='"+childCategory+"'";
			whereClause = whereClause + " and ROOT_CATEGORY = '"+rootCategory+"'";
		}
		
		if(childCategory != null && !childCategory.equals("") && !childCategory.equalsIgnoreCase("null") ){
			//PRODUCT_LISTQUERY_pagination = PRODUCT_LISTQUERY_pagination + " and CATEGORY_ID='"+childCategory+"'";
			whereClause = whereClause + " and CATEGORY_ID='"+childCategory+"'";
		}
		if(subRootCategory != null && !subRootCategory.equals("") && !subRootCategory.equalsIgnoreCase("null") ){
			//PRODUCT_LISTQUERY_pagination = PRODUCT_LISTQUERY_pagination + " AND SUB_ROOT_CATEGORY ='"+subRootCategory+"'";
			whereClause = whereClause + " AND SUB_ROOT_CATEGORY ='"+subRootCategory+"'";
		}
		
		if(brandName != null && !brandName.equals("") && !brandName.equalsIgnoreCase("null") ){
			//PRODUCT_LISTQUERY_pagination = PRODUCT_LISTQUERY_pagination + " AND upper(product_brand_name) in("+brandName+")";
			whereClause = whereClause + " AND upper(product_brand_name) in("+brandName+")";
		}
		
		if(whereClause != null && !whereClause.trim().equals("") && whereClause.length() > 0){
			whereClause = whereClause.trim().substring(3);
			PRODUCT_LISTQUERY_pagination = PRODUCT_LISTQUERY_pagination + " where " + whereClause;
		}
		
		if(fromfooter != null && !fromfooter.equals("") && fromfooter.equalsIgnoreCase("footer")){
			PRODUCT_LISTQUERY_pagination = "SELECT * FROM product_info WHERE upper(product_brand_name) in("+brandName+")";
		}
		
		
		PRODUCT_LISTQUERY_pagination = PRODUCT_LISTQUERY_pagination + " limit "+startIndex+","+endIndex;
		
		try{
			stmt = connection.prepareStatement(PRODUCT_LISTQUERY_pagination);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				
				String productId = rs.getString("product_id");
				String productName = rs.getString("product_name");
				String productBrandName = rs.getString("product_brand_name");
				String actualPrice = rs.getString("actual_price");
				String offerPrice = rs.getString("offer_price");
				String imagePath = rs.getString("image_path");
				
				ArrayList al = new ArrayList();
				al.add(productId);
				al.add(productName);
				al.add(productBrandName);
				al.add(actualPrice);
				al.add(offerPrice);
				al.add(imagePath);
				
				ArrayList<String[]> priceDetail = getPriceDetail(connection, productId, rootCategory, subRootCategory);
				al.add(priceDetail);
				
				productList.put(productName+"#"+productId, al);
			}
			
		}
		catch(SQLException se){
			logger.error("Exception while getting productListPagination for categoryId ["+childCategory+"]",se);
		}
		catch(Exception e){
			logger.error("Unexpected Exception while getting productListPagination for categoryId ["+childCategory+"]",e);
		}
		finally{
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(stmt);
		}
		
		return productList;
	}

	@Override
	public Map<String, ArrayList<String>> getProductListForBrand(
			Connection connection, String rootCategory, String subRootCategory,
			String childCategory, String brandName, int startIndex, int endIndex, String fromfooter, String productNameEntered) {
		Map<String,ArrayList<String>> productList = new HashMap<String, ArrayList<String>>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String PRODUCT_LISTQUERY_pagination = "SELECT * FROM product_info ";
		
		String whereClause= "";
		
		if(rootCategory != null && !rootCategory.equals("") && !rootCategory.equalsIgnoreCase("null") ){
			//PRODUCT_LISTQUERY_pagination = PRODUCT_LISTQUERY_pagination + " and CATEGORY_ID='"+childCategory+"'";
			whereClause = whereClause + " and ROOT_CATEGORY = '"+rootCategory+"'";
		}
		
		if(childCategory != null && !childCategory.equals("") && !childCategory.equalsIgnoreCase("null") ){
			//PRODUCT_LISTQUERY_pagination = PRODUCT_LISTQUERY_pagination + " and CATEGORY_ID='"+childCategory+"'";
			whereClause = whereClause + " and CATEGORY_ID='"+childCategory+"'";
		}
		if(subRootCategory != null && !subRootCategory.equals("") && !subRootCategory.equalsIgnoreCase("null") ){
			//PRODUCT_LISTQUERY_pagination = PRODUCT_LISTQUERY_pagination + " AND SUB_ROOT_CATEGORY ='"+subRootCategory+"'";
			whereClause = whereClause + " AND SUB_ROOT_CATEGORY ='"+subRootCategory+"'";
		}
		
		if(brandName != null && !brandName.trim().equals("") && !brandName.equalsIgnoreCase("null") ){
			//PRODUCT_LISTQUERY_pagination = PRODUCT_LISTQUERY_pagination + " AND upper(product_brand_name) in("+brandName+")";
			whereClause = whereClause + " AND upper(product_brand_name) in("+brandName+")";
		}
		if(productNameEntered != null && !productNameEntered.equals("") && !productNameEntered.equalsIgnoreCase("null") ){
			//PRODUCT_LISTQUERY_pagination = PRODUCT_LISTQUERY_pagination + " AND upper(product_brand_name) in("+brandName+")";
			whereClause = whereClause + " AND upper(product_name) like '%"+productNameEntered.toUpperCase()+"%'";
		}
		
		if(whereClause != null && !whereClause.trim().equals("") && whereClause.length() > 0){
			whereClause = whereClause.trim().substring(3);
			PRODUCT_LISTQUERY_pagination = PRODUCT_LISTQUERY_pagination + " where " + whereClause;
		}
		
		if(fromfooter != null && !fromfooter.equals("") && fromfooter.equalsIgnoreCase("footer")){
			PRODUCT_LISTQUERY_pagination = "SELECT * FROM product_info WHERE upper(product_brand_name) in("+brandName+")";
		}
		
		
		PRODUCT_LISTQUERY_pagination = PRODUCT_LISTQUERY_pagination + " limit "+startIndex+","+endIndex;
		
		try{
			stmt = connection.prepareStatement(PRODUCT_LISTQUERY_pagination);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				
				String productId = rs.getString("product_id");
				String productName = rs.getString("product_name");
				String productBrandName = rs.getString("product_brand_name");
				String actualPrice = rs.getString("actual_price");
				String offerPrice = rs.getString("offer_price");
				String imagePath = rs.getString("image_path");
				
				ArrayList al = new ArrayList();
				al.add(productId);
				al.add(productName);
				al.add(productBrandName);
				al.add(actualPrice);
				al.add(offerPrice);
				al.add(imagePath);
				
				ArrayList<String[]> priceDetail = getPriceDetail(connection, productId, rootCategory, subRootCategory);
				al.add(priceDetail);
				
				productList.put(productName+"#"+productId, al);
			}
			
		}
		catch(SQLException se){
			logger.error("Exception while getting productListPagination for categoryId ["+childCategory+"]",se);
		}
		catch(Exception e){
			logger.error("Unexpected Exception while getting productListPagination for categoryId ["+childCategory+"]",e);
		}
		finally{
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(stmt);
		}
		
		return productList;
	}
	
	@Override
	public int getProductCountForBrand(Connection connection,
			String categoryId, String rootCategory, String subRootCategory,
			String allBrandIn, String fromfooter) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int count = 0;
		
		String PRODUCT_COUNT_SIDEBAR = "SELECT COUNT(*) FROM product_info ";
		
		String whereClause= "";
		
		if(rootCategory != null && !rootCategory.equals("") && !rootCategory.equalsIgnoreCase("null") ){
			//PRODUCT_LISTQUERY_pagination = PRODUCT_LISTQUERY_pagination + " and CATEGORY_ID='"+childCategory+"'";
			whereClause = whereClause + " and ROOT_CATEGORY = '"+rootCategory+"'";
		}
		
		if(categoryId != null && !categoryId.equals("") && !categoryId.equalsIgnoreCase("null") ){
			//PRODUCT_LISTQUERY_pagination = PRODUCT_LISTQUERY_pagination + " and CATEGORY_ID='"+childCategory+"'";
			whereClause = whereClause + " and CATEGORY_ID='"+categoryId+"'";
		}
		if(subRootCategory != null && !subRootCategory.equals("") && !subRootCategory.equalsIgnoreCase("null") ){
			//PRODUCT_LISTQUERY_pagination = PRODUCT_LISTQUERY_pagination + " AND SUB_ROOT_CATEGORY ='"+subRootCategory+"'";
			whereClause = whereClause + " AND SUB_ROOT_CATEGORY ='"+subRootCategory+"'";
		}
		
		if(allBrandIn != null && !allBrandIn.equals("") && !allBrandIn.equalsIgnoreCase("null") ){
			//PRODUCT_LISTQUERY_pagination = PRODUCT_LISTQUERY_pagination + " AND upper(product_brand_name) in("+brandName+")";
			whereClause = whereClause + " AND upper(product_brand_name) in("+allBrandIn+")";
		}
		
		if(whereClause != null && !whereClause.trim().equals("") && whereClause.length() > 0){
			whereClause = whereClause.trim().substring(3);
			PRODUCT_COUNT_SIDEBAR = PRODUCT_COUNT_SIDEBAR + " where " + whereClause;
		}
		/*if(subRootCategory != null && !subRootCategory.equals("") && !subRootCategory.equalsIgnoreCase("null")){
			PRODUCT_COUNT_SIDEBAR = PRODUCT_COUNT_SIDEBAR + " AND SUB_ROOT_CATEGORY = '"+subRootCategory+"'";
		}
		if(categoryId != null && !categoryId.equals("") && !categoryId.equalsIgnoreCase("null")){
			PRODUCT_COUNT_SIDEBAR = PRODUCT_COUNT_SIDEBAR + " AND CATEGORY_ID = '"+categoryId+"'";
		}
		if(allBrandIn != null && !allBrandIn.equals("") && !allBrandIn.equalsIgnoreCase("null")){
			PRODUCT_COUNT_SIDEBAR = PRODUCT_COUNT_SIDEBAR + " AND upper(product_brand_name) in("+allBrandIn+")";
		}
		
		if(fromfooter != null && !fromfooter.equals("") && fromfooter.equalsIgnoreCase("footer")){
			PRODUCT_COUNT_SIDEBAR = "SELECT * FROM product_info WHERE upper(product_brand_name) in("+allBrandIn+")";
		}*/
		
		try{
			stmt = connection.prepareStatement(PRODUCT_COUNT_SIDEBAR);
			rs = stmt.executeQuery();
			while(rs.next()){
				count = rs.getInt(1);
			}
			
		}
		catch(SQLException se){
			logger.error("Exception while getting productCount for categoryId ["+categoryId+"]",se);
		}
		catch(Exception e){
			logger.error("Unexpected Exception while getting productCount for categoryId ["+categoryId+"]",e);
		}
		finally{
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(stmt);
		}
		
		return count;
	}

	@Override
	public int getProductCountForBrand(Connection connection,
			String categoryId, String rootCategory, String subRootCategory,
			String allBrandIn, String fromfooter, String productName) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int count = 0;
		
		String PRODUCT_COUNT_SIDEBAR = "SELECT COUNT(*) FROM product_info ";
		
		String whereClause= "";
		
		if(rootCategory != null && !rootCategory.equals("") && !rootCategory.equalsIgnoreCase("null") ){
			//PRODUCT_LISTQUERY_pagination = PRODUCT_LISTQUERY_pagination + " and CATEGORY_ID='"+childCategory+"'";
			whereClause = whereClause + " and ROOT_CATEGORY = '"+rootCategory+"'";
		}
		
		if(categoryId != null && !categoryId.equals("") && !categoryId.equalsIgnoreCase("null") ){
			//PRODUCT_LISTQUERY_pagination = PRODUCT_LISTQUERY_pagination + " and CATEGORY_ID='"+childCategory+"'";
			whereClause = whereClause + " and CATEGORY_ID='"+categoryId+"'";
		}
		if(subRootCategory != null && !subRootCategory.equals("") && !subRootCategory.equalsIgnoreCase("null") ){
			//PRODUCT_LISTQUERY_pagination = PRODUCT_LISTQUERY_pagination + " AND SUB_ROOT_CATEGORY ='"+subRootCategory+"'";
			whereClause = whereClause + " AND SUB_ROOT_CATEGORY ='"+subRootCategory+"'";
		}
		
		if(allBrandIn != null && !allBrandIn.equals("") && !allBrandIn.equalsIgnoreCase("null") ){
			//PRODUCT_LISTQUERY_pagination = PRODUCT_LISTQUERY_pagination + " AND upper(product_brand_name) in("+brandName+")";
			whereClause = whereClause + " AND upper(product_brand_name) in("+allBrandIn+")";
		}
		
		if(productName != null && !productName.equals("") && !productName.equalsIgnoreCase("null") ){
			//PRODUCT_LISTQUERY_pagination = PRODUCT_LISTQUERY_pagination + " AND upper(product_brand_name) in("+brandName+")";
			whereClause = whereClause + " AND upper(product_name) like '%"+productName.toUpperCase()+"%'";
		}
		
		if(whereClause != null && !whereClause.trim().equals("") && whereClause.length() > 0){
			whereClause = whereClause.trim().substring(3);
			PRODUCT_COUNT_SIDEBAR = PRODUCT_COUNT_SIDEBAR + " where " + whereClause;
		}
		/*if(subRootCategory != null && !subRootCategory.equals("") && !subRootCategory.equalsIgnoreCase("null")){
			PRODUCT_COUNT_SIDEBAR = PRODUCT_COUNT_SIDEBAR + " AND SUB_ROOT_CATEGORY = '"+subRootCategory+"'";
		}
		if(categoryId != null && !categoryId.equals("") && !categoryId.equalsIgnoreCase("null")){
			PRODUCT_COUNT_SIDEBAR = PRODUCT_COUNT_SIDEBAR + " AND CATEGORY_ID = '"+categoryId+"'";
		}
		if(allBrandIn != null && !allBrandIn.equals("") && !allBrandIn.equalsIgnoreCase("null")){
			PRODUCT_COUNT_SIDEBAR = PRODUCT_COUNT_SIDEBAR + " AND upper(product_brand_name) in("+allBrandIn+")";
		}
		
		if(fromfooter != null && !fromfooter.equals("") && fromfooter.equalsIgnoreCase("footer")){
			PRODUCT_COUNT_SIDEBAR = "SELECT * FROM product_info WHERE upper(product_brand_name) in("+allBrandIn+")";
		}*/
		
		try{
			stmt = connection.prepareStatement(PRODUCT_COUNT_SIDEBAR);
			rs = stmt.executeQuery();
			while(rs.next()){
				count = rs.getInt(1);
			}
			
		}
		catch(SQLException se){
			logger.error("Exception while getting productCount for categoryId ["+categoryId+"]",se);
		}
		catch(Exception e){
			logger.error("Unexpected Exception while getting productCount for categoryId ["+categoryId+"]",e);
		}
		finally{
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(stmt);
		}
		
		return count;
	}
	
	@Override
	public List getDetailsForAutoComplete(Connection connection,
			String searchKey) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<String[]> al = new ArrayList<String[]>();
		
		String query = "select * from product_info where lower(product_name) like '%"+searchKey.toLowerCase()+"%' or product_brand_name like '%"+searchKey+"%'  ";

		try{
			stmt = connection.prepareStatement(query);
			
			//stmt.setString(4, childCategory);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				
				String productName = rs.getString("product_name");
				String productBrandName = rs.getString("product_brand_name");
				
				String arr[] = new String[2];
				arr[0] = productName;
				arr[1] = productBrandName;
				
				al.add(arr);
			}
			
		}
		catch(SQLException se){
			logger.error("Exception while getting priceDetail for productId",se);
		}
		catch(Exception e){
			logger.error("Unexpected Exception while getting priceDetail for productId",e);
		}
		finally{
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(stmt);
		}
		
		return al;
	}

	@Override
	public String checkCouponCode(Connection connection, String couponCode, String cartTotalAmount) {
		
		PreparedStatement pstmt = null;
		String couponCodeDetail = "";
		
		try{
			pstmt = connection.prepareStatement("select * from all_coupon_code where upper(coupon_code) = ?");
			pstmt.setString(1, couponCode.trim().toUpperCase());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				String disType = rs.getString("discount_type");
				String disAmount = rs.getString("discount_amount");
				String minimum_for_coupon = rs.getString("minimum_for_coupon") != null && !rs.getString("minimum_for_coupon").trim().equals("") ? rs.getString("minimum_for_coupon") : "0.0";
				if(Float.parseFloat(cartTotalAmount) < Float.parseFloat(minimum_for_coupon.trim())){
					couponCodeDetail = "Your total amount must be minimum : " + minimum_for_coupon + " to get this Coupon Code available";
				}
				else{
					couponCodeDetail = couponCodeDetail + "success "+":" + disType + ":" + disAmount;
				}
			}
			if(couponCodeDetail == null || couponCodeDetail.trim().equals("")){
				couponCodeDetail = "Coupon Code entered is not valid";
			}
			
		}
		catch (Exception e) {
			logger.error("", e);
		}
		
		return couponCodeDetail;
	}

	@Override
	public boolean submitcartSummary(Connection connection, Map values) {

		PreparedStatement pstmt = null;
		boolean flag = false;
		try{
			pstmt = connection.prepareStatement("INSERT INTO order_detail_summary(user_id, order_id, total_amount, discount, coupon_code, amount_to_pay, delivery_charge,cart_id, order_date) VALUES (?,?,?,?,?,?,?,?,now())");
			pstmt.setString(1, values.get("userId").toString());
			pstmt.setString(2, values.get("orderId").toString());
			pstmt.setString(3, values.get("totalAmount").toString());
			pstmt.setString(4, values.get("discount").toString());
			pstmt.setString(5, values.get("couponCode").toString());
			pstmt.setString(6, values.get("amountToPay").toString());
			pstmt.setString(7, values.get("deliveryCharge").toString());
			pstmt.setString(8, values.get("cartId").toString());
			
			int i = pstmt.executeUpdate();
			if(i>0){
				flag = true;
			}
		}
		catch(Exception e){
			logger.error("", e);
		}
		finally{
			DBUtil.closePreparedStatement(pstmt);
		}
		return flag;
	}

	@Override
	public ArrayList<String> getOrderIDAutocomplete(Connection connection,
			String orderId, String loginId) {
		PreparedStatement pstmt = null;
		ArrayList<String> allOrderId = new ArrayList<String>();
		try{
			pstmt = connection.prepareStatement("select distinct(order_id) order_id from order_detail where upper(order_id) like '%"+orderId.toUpperCase()+"%' and upper(emailid) ='"+loginId.toUpperCase()+"' and order_status in('P','C') ");
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				allOrderId.add(rs.getString(1));
			}
			
		}
		catch(Exception e){
			logger.error("", e);
		}
		finally{
			DBUtil.closePreparedStatement(pstmt);
		}
		return allOrderId;
	}
	@Override
	public ArrayList<String> getPreviousOrderIDAutocomplete(Connection connection, String orderId, String loginId){
		PreparedStatement pstmt = null;
		ArrayList<String> allOrderId = new ArrayList<String>();
		try{
			pstmt = connection.prepareStatement("select distinct(order_id) order_id from order_detail where upper(order_id) like '%"+orderId.toUpperCase()+"%' and upper(emailid) ='"+loginId.toUpperCase()+"' and order_status in('R','D') ");
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				allOrderId.add(rs.getString(1));
			}
			
		}
		catch(Exception e){
			logger.error("", e);
		}
		finally{
			DBUtil.closePreparedStatement(pstmt);
		}
		return allOrderId;
	}
}
