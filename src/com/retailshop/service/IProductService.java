package com.retailshop.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface IProductService {
	
	public Map<String, ArrayList<String>> getProductList(Connection connection,String categoryId, String rootCategory, String subRootCategory);
	
	public Map<String, ArrayList<String>> getProductListPagination(Connection connection,String categoryId,int startIndex, String rootCategory, String subRootCategory);
	
	public int getProductCount(Connection connection, String categoryId, String rootCategory, String subRootCategory);
	
	public int getProductCountForBrand(Connection connection, String categoryId, String rootCategory, String subRootCategory, String allBrandIn, String fromFooter);
	
	public int getProductCountForBrand(Connection connection, String categoryId, String rootCategory, String subRootCategory, String allBrandIn, String fromFooter, String productName);
	
	public Map productDetail(Connection connection, String productId, String rootCategory, String subRootCategory);
	
	public Map productDetailForCategory(Connection connection, String productId);
	
	public Map<String,ArrayList> getRelatedProduct(Connection connection,String productId, String rootCategory, String subRootCategory);
	
	public Map<String,ArrayList> getRelatedBrandProduct(Connection connection,String productId, String rootCategory, String subRootCategory);
	
	public Map getSibeBarProducts(Connection connection,String rootCategory, String subRootCategory);
	
	public Map getDeliveryDetail(Connection connection, String userId);
	
	public boolean addAddress(Connection connection, String userId, Map parameters);
	
	public boolean submitCart(Connection connection, String userId, Map parameters);
	
	public long getCartId(Connection connection);
	
	public Map getSubmitCartDetail(Connection connection, String userId, long cartId);
	
	public Map<String, ArrayList<String>> getProductListSideBar(Connection connection,String categoryId, String rootCategory, String subRootCategory);
	
	public Map<String, ArrayList<String>> getProductListForCategory(Connection connection,String categoryId, int startIndex);
	
	public boolean addToWishlist(Connection connection, int productId, String loginId, int quantity);
	
	public String getWishlistProducts(Connection connection, String loginId);
	
	public boolean removeProductFromWishlist(Connection connection, String loginId, String productId);
	
	public List getAllBrandForProduct(Connection connection, String rootCategory, String subRootCategory, String childCategory);
	
	public Map<String, ArrayList<String>> getProductListForBrand(Connection connection, String rootCategory, String subRootCategory,String childCategory,String brandName,int startIndex, String fromfooter);

	public Map<String, ArrayList<String>> getProductListForBrand(Connection connection, String rootCategory, String subRootCategory,String childCategory,String brandName,int startIndex, String fromfooter, String productName);
	
	public List getDetailsForAutoComplete(Connection connection, String searchKey);
	
	public String checkCouponCode(Connection connection, String couponCode, String cartTotalAmount);
	
	public boolean submitcartSummary(Connection connection, Map values);

	public ArrayList<String> getOrderIDAutocomplete(Connection connection, String orderId, String loginId);
	
	public ArrayList<String> getPreviousOrderIDAutocomplete(Connection connection, String orderId, String loginId);
	
}

