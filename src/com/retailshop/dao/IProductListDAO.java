package com.retailshop.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface IProductListDAO {
	
	String PRODUCT_LISTQUERY_FOR_CATEGORY = "SELECT * FROM product_info WHERE ROOT_CATEGORY=?  limit ?,?";
	
	public Map<String, ArrayList<String>> getProductListForCategory(Connection connection,String categoryId, int startIndex);
	
	/*
	 * 
	 */
	String PRODUCT_LISTQUERY = "SELECT * FROM product_info WHERE CATEGORY_ID=? AND ROOT_CATEGORY=? AND SUB_ROOT_CATEGORY=? limit 0,12";
	
	/*
	 * 
	 */
	public Map<String, ArrayList<String>> getProductList(Connection connection,String categoryId, String rootCategory, String subRootCategory);
	
	/*
	 * 
	 */
	public ArrayList<String[]> getPriceDetail(Connection connection, String categoryId, String rootCategory, String subRootCategory);
	
	
	/*
	 * 
	 */
	String PRODUCT_LISTQUERY_pagination = "SELECT * FROM product_info WHERE CATEGORY_ID=? AND ROOT_CATEGORY = ? AND SUB_ROOT_CATEGORY = ?  limit ?,?";
	
	/*
	 * 
	 */
	public Map<String, ArrayList<String>> getProductListPagination(Connection connection,String categoryId, int startIndex, int endIndex, String rootCategory, String subRootCategory);
	
	
	/*
	 * 
	 */
	String PRODUCT_COUNT = "SELECT COUNT(*) FROM product_info WHERE CATEGORY_ID = ? AND ROOT_CATEGORY = ? AND SUB_ROOT_CATEGORY = ?";
	/*
	 * 
	 */
	public int getProductCount(Connection connection, String categoryId, String rootCategory, String subRootCategory);
	
	/*
	 * 
	 */
	
	/*
	 * 
	 */
	public Map productDetail(Connection connection, String productId, String rootCategory, String subRootCategory);
	
	String PRODUCT_PRICE_DETAIL_FOR_CATEGORY = "SELECT PRODUCT_INFO, PRODUCT_PRICE FROM product_price_detail WHERE PRODUCT_ID = ?";
	
	public Map productDetailForCategory(Connection connection, String productId);
	
	/*
	 * 
	 */
	String PRODUCT_LIST_QUERY = "SELECT * FROM product_info WHERE CATEGORY_ID=? AND ROOT_CATEGORY = ? AND SUB_ROOT_CATEGORY = ?";
	
	public Map<String,ArrayList> getRelatedProduct(Connection connection,String productId, String rootCategory, String subRootCategory);
	
	/*
	 * 
	 */
	String PRODUCT_LIST_FOR_BRAND_QUERY = "SELECT * FROM product_info WHERE upper(PRODUCT_BRAND_NAME) like ?";
	/*
	 * 
	 */
	public Map<String,ArrayList> getRelatedBrandProduct(Connection connection,String productId, String rootCategory, String subRootCategory);
	
	/*
	 * 
	 */
	String CATEGORY_LIST_SIDE_BAR_QUERY = "SELECT SUB_CATEGORY_ID, SUB_CATEGORY_NAME FROM sub_category1 WHERE MASTER_CATEGORY_ID = ?";
	String CATEGORY_LIST_SIDE_BAR_COUNT = "SELECT SC.SUB_CATEGORY_ID2 AS SUB_CATEGORY_ID2,SC.SUB_CATEGORY_DESC AS SUB_CATEGORY_DESC,(SELECT COUNT(*) FROM product_info PI WHERE PI.CATEGORY_ID = SC.SUB_CATEGORY_ID2 AND PI.ROOT_CATEGORY = SC.MASTER_CATEGORY_ID AND PI.SUB_ROOT_CATEGORY = SC.SUB_CATEGORY_ID1) AS P_COUNT FROM sub_category2 SC WHERE SC.MASTER_CATEGORY_ID = ? AND SC.SUB_CATEGORY_ID1 = ? ";
	
	/*
	 * 
	 */
	public Map getSibeBarProducts(Connection connection, String rootCategory, String subRootCategory);
	
	
	/*
	 * 
	 */
	String GET_DELIVERY_DETAIL = "select * from user_detail where emailid=?";
	/*
	 * 
	 */
	public Map getDeliveryDetail(Connection connection, String userId);
	
	String moveAddress = "UPDATE user_detail SET mobile_no=delivery_contact_no, house_no = delivery_houseno ,street_detail = delivery_street_detail ,residential_complex = delivery_residential_complex, landmark = delivery_landmark,"+
			"area = delivery_area, pin_code = delivery_pincode, city = delivery_city WHERE upper(EMAILID) = ?";
	
	String UPDATE_USER_ADDRESS = "UPDATE user_detail SET DELIVERY_NICKNAME = ?,delivery_firstname = ?,delivery_lastname = ? ,delivery_contact_no = ?," +
			"delivery_houseno = ?,delivery_street_detail = ? ,delivery_residential_complex = ?,delivery_landmark = ?," +
			"delivery_area = ?,delivery_pincode = ?,delivery_city = ? WHERE EMAILID = ?";
	/*
	 * 
	 */
	public boolean addAddress(Connection connection, String userId,Map parameters);
	
	/*
	 * 
	 */
	String SUBMIT_CART = "INSERT INTO order_detail(EMAILID, DELIVERY_ADDRESS, SELECTED_SLOT,PAYMENT_MODE, CART_ID, PRODUCT_ID,PRODUCT_NAME,PRODUCT_BRAND_NAME, OFFER_PRICE, NO_OF_ITEM, TOTAL_AMOUNT,IMAGE_PATH,PRODUCT_SIZE,ORDER_STATUS, ORDER_DATE, delivery_date, order_id) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,now(),STR_TO_DATE(?, '%d/%m/%Y'),?)";
	/*
	 * 
	 */
	public boolean submitCart(Connection connection, String loginId, Map parameters);
	
	/*
	 * 
	 */
	String GET_CART_ID = "SELECT MAX(CART_ID) FROM order_detail";
	/*
	 * 
	 */
	public long getCartId(Connection connection);
		
	/*
	 * 
	 */
	String GET_SUBMIT_CART_DETAIL = "SELECT * FROM order_detail WHERE CART_ID = ? AND EMAILID = ?";
	/*
	 * 
	 */
	public Map getSubmitCartDetail(Connection connection, String userId,long cartId);
	
	/*
	 * 
	 */
	String PRODUCT_LISTQUERY_SIDEBAR = "SELECT * FROM product_info WHERE CATEGORY_ID=? AND ROOT_CATEGORY=? AND SUB_ROOT_CATEGORY=? limit 0,12";
	
	/*
	 * 
	 */
	public Map<String, ArrayList<String>> getProductListSideBar(Connection connection,String categoryId, String rootCategory, String subRootCategory);
	
	public boolean addToWishlist(Connection connection, int productId, String loginId, int quantity);
	

	public String getWishlistProducts(Connection connection, String loginId);
	
	public boolean removeProductFromWishlist(Connection connection, String loginId, String productId);
	
	public List getAllBrandForProduct(Connection connection, String rootCategory, String subRootCategory, String childCategory);
	
	
	public Map<String, ArrayList<String>> getProductListForBrand(Connection connection, String rootCategory, String subRootCategory,String childCategory, String brandName,int startIndex, int endIndex, String fromfooter);

	public Map<String, ArrayList<String>> getProductListForBrand(Connection connection, String rootCategory, String subRootCategory,String childCategory, String brandName,int startIndex, int endIndex, String fromfooter, String productName);
	
	public int getProductCountForBrand(Connection connection, String categoryId, String rootCategory, String subRootCategory, String allBrandIn, String fromFooter);
	
	public int getProductCountForBrand(Connection connection, String categoryId, String rootCategory, String subRootCategory, String allBrandIn, String fromFooter, String productName);
	
	public List getDetailsForAutoComplete(Connection connection, String searchKey);
	
	public String checkCouponCode(Connection connection, String couponCode, String cartTotalAmount);
	
	public boolean submitcartSummary(Connection connection, Map values);

	public ArrayList<String> getOrderIDAutocomplete(Connection connection, String orderId, String loginId);

	public ArrayList<String> getPreviousOrderIDAutocomplete(Connection connection, String orderId, String loginId);
	
}
