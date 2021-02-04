package com.retailshop.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.retailshop.dao.IProductListDAO;
import com.retailshop.dao.impl.ProductListDAOImpl;
import com.retailshop.service.IProductService;

public class ProductServiceImpl implements IProductService {

	@Override
	public Map<String, ArrayList<String>> getProductList(Connection connection,String categoryId, String rootCategory, String subRootCategory) {

		IProductListDAO objProductListDao = new ProductListDAOImpl();
		
		Map<String, ArrayList<String>> productList=objProductListDao.getProductList(connection, categoryId, rootCategory, subRootCategory); 
		
		return productList;
	}

	
	@Override
	public Map<String, ArrayList<String>> getProductListForCategory(Connection connection,String categoryId, int startIndex) {

		IProductListDAO objProductListDao = new ProductListDAOImpl();
		
		Map<String, ArrayList<String>> productList=objProductListDao.getProductListForCategory(connection, categoryId, startIndex); 
		
		return productList;
	}
	
	@Override
	public Map<String, ArrayList<String>> getProductListPagination(Connection connection,String categoryId, int StartIndex, String rootCategory, String subRootCategory) {

		int displaySize = 12;
		//int endIndex = StartIndex*displaySize + displaySize;
		StartIndex = StartIndex*displaySize;
		int endIndex = displaySize;
		
		IProductListDAO objProductListDao = new ProductListDAOImpl();
		
		Map<String, ArrayList<String>> productList=objProductListDao.getProductListPagination(connection, categoryId,StartIndex,endIndex, rootCategory, subRootCategory); 
		
		return productList;
	}


	@Override
	public int getProductCount(Connection connection, String categoryId, String rootCategory, String subRootCategory) {
		
		IProductListDAO objProductListDao = new ProductListDAOImpl();
		int count = objProductListDao.getProductCount(connection,categoryId, rootCategory, subRootCategory);
		
		return count;
	}


	@Override
	public Map productDetail(Connection connection, String productId, String rootCategory, String subRootCategory) {
		IProductListDAO objProductListDao = new ProductListDAOImpl();
		Map details = objProductListDao.productDetail(connection, productId,rootCategory,subRootCategory);
		return details;
	}
	
	@Override
	public Map productDetailForCategory(Connection connection, String productId) {
		IProductListDAO objProductListDao = new ProductListDAOImpl();
		Map details = objProductListDao.productDetailForCategory(connection, productId);
		return details;
	}
	
	@Override
	public Map<String,ArrayList> getRelatedProduct(Connection connection,String productId, String rootCategory, String subRootCategory){
		
		IProductListDAO objProductListDao = new ProductListDAOImpl();
		Map<String, ArrayList> details = objProductListDao.getRelatedProduct(connection, productId,rootCategory, subRootCategory);
		
		return details;
	}
	
	@Override
	public Map<String,ArrayList> getRelatedBrandProduct(Connection connection,String productId, String rootCategory, String subRootCategory){
		IProductListDAO objProductListDao = new ProductListDAOImpl();
		Map<String, ArrayList> details = objProductListDao.getRelatedBrandProduct(connection, productId, rootCategory, subRootCategory);
		
		return details;
	}


	@Override
	public Map getSibeBarProducts(Connection connection, String rootCategory, String subRootCategory) {
		IProductListDAO objProductListDao = new ProductListDAOImpl();
		Map details = objProductListDao.getSibeBarProducts(connection, rootCategory, subRootCategory);
		
		return details;
	}


	@Override
	public Map getDeliveryDetail(Connection connection, String userId) {
		
		IProductListDAO objProductListDao = new ProductListDAOImpl();
		Map address = objProductListDao.getDeliveryDetail(connection, userId);
		
		return address;
	}


	@Override
	public boolean addAddress(Connection connection, String userId,
			Map parameters) {
		
		IProductListDAO objProductListDao = new ProductListDAOImpl();
		boolean flag = objProductListDao.addAddress(connection, userId, parameters);
		
		return flag;
	}


	@Override
	public boolean submitCart(Connection connection, String userId,
			Map parameters) {
		IProductListDAO objProductListDao = new ProductListDAOImpl();
		boolean flag = objProductListDao.submitCart(connection, userId, parameters);
		
		return flag;
	}
	
	@Override
	public long getCartId(Connection connection){
		
		IProductListDAO objProductListDao = new ProductListDAOImpl();
		long cartId = objProductListDao.getCartId(connection);
		
		return cartId;
	}


	@Override
	public Map getSubmitCartDetail(Connection connection, String userId,
			long cartId) {
		IProductListDAO objProductListDao = new ProductListDAOImpl();
		
		return objProductListDao.getSubmitCartDetail(connection, userId, cartId);
	}
	
	@Override
	public Map<String, ArrayList<String>> getProductListSideBar(Connection connection,String categoryId, String rootCategory, String subRootCategory) {

		IProductListDAO objProductListDao = new ProductListDAOImpl();
		
		Map<String, ArrayList<String>> productList=objProductListDao.getProductListSideBar(connection, categoryId, rootCategory, subRootCategory); 
		
		return productList;
	}


	@Override
	public boolean addToWishlist(Connection connection, int productId,
			String loginId, int quantity) {
		IProductListDAO objProductListDao = new ProductListDAOImpl();
		return objProductListDao.addToWishlist(connection,productId,loginId, quantity);
	}


	@Override
	public String getWishlistProducts(Connection connection, String loginId) {
		IProductListDAO objProductListDao = new ProductListDAOImpl();
		return objProductListDao.getWishlistProducts(connection, loginId);
	}


	@Override
	public boolean removeProductFromWishlist(Connection connection,
			String loginId, String productId) {
		IProductListDAO objProductListDao = new ProductListDAOImpl();
		return objProductListDao.removeProductFromWishlist(connection, loginId, productId);
	}


	@Override
	public List getAllBrandForProduct(Connection connection,
			String rootCategory, String subRootCategory, String childCategory) {
		IProductListDAO objProductListDao = new ProductListDAOImpl();
		return objProductListDao.getAllBrandForProduct(connection, rootCategory, subRootCategory, childCategory);
	}


	@Override
	public Map<String, ArrayList<String>> getProductListForBrand(
			Connection connection, String rootCategory, String subRootCategory,
			String childCategory,String brandName, int startIndex, String fromfooter) {
		int displaySize = 12;
		startIndex = startIndex-1;
		startIndex = startIndex*displaySize;
		int endIndex = startIndex*displaySize + displaySize;
		
		IProductListDAO objProductListDao = new ProductListDAOImpl();
		
		Map<String, ArrayList<String>> productList=objProductListDao.getProductListForBrand(connection, rootCategory, subRootCategory, childCategory,brandName,startIndex,endIndex, fromfooter); 
		
		return productList;
	}

	
	@Override
	public Map<String, ArrayList<String>> getProductListForBrand(
			Connection connection, String rootCategory, String subRootCategory,
			String childCategory,String brandName, int startIndex, String fromfooter, String productName) {
		int displaySize = 12;
		startIndex = startIndex-1;
		startIndex = startIndex*displaySize;
		int endIndex = startIndex*displaySize + displaySize;
		
		IProductListDAO objProductListDao = new ProductListDAOImpl();
		
		Map<String, ArrayList<String>> productList=objProductListDao.getProductListForBrand(connection, rootCategory, subRootCategory, childCategory,brandName,startIndex,endIndex, fromfooter, productName); 
		
		return productList;
	}

	@Override
	public int getProductCountForBrand(Connection connection,
			String categoryId, String rootCategory, String subRootCategory,
			String allBrandIn, String fromFooter) {
		IProductListDAO objProductListDao = new ProductListDAOImpl();
		int count = objProductListDao.getProductCountForBrand(connection,categoryId, rootCategory, subRootCategory, allBrandIn, fromFooter);
		
		return count;
	}

	@Override
	public int getProductCountForBrand(Connection connection,
			String categoryId, String rootCategory, String subRootCategory,
			String allBrandIn, String fromFooter, String productName) {
		IProductListDAO objProductListDao = new ProductListDAOImpl();
		int count = objProductListDao.getProductCountForBrand(connection,categoryId, rootCategory, subRootCategory, allBrandIn, fromFooter, productName);
		
		return count;
	}
	

	@Override
	public List getDetailsForAutoComplete(Connection connection,
			String searchKey) {
		IProductListDAO objProductListDao = new ProductListDAOImpl();
		return objProductListDao.getDetailsForAutoComplete(connection, searchKey);
	}


	@Override
	public String checkCouponCode(Connection connection, String couponCode, String cartTotalAmount) {
		IProductListDAO objProductListDao = new ProductListDAOImpl();
		
		String couponCodeVal = objProductListDao.checkCouponCode(connection, couponCode, cartTotalAmount);
		return couponCodeVal;
	}


	@Override
	public boolean submitcartSummary(Connection connection, Map values) {
		IProductListDAO objProductListDao = new ProductListDAOImpl();
		
		boolean flag = objProductListDao.submitcartSummary(connection, values);
		return flag;
	}


	@Override
	public ArrayList<String> getOrderIDAutocomplete(Connection connection,
			String orderId, String loginId) {
		IProductListDAO objProductListDao = new ProductListDAOImpl();
		return objProductListDao.getOrderIDAutocomplete(connection, orderId, loginId);
	}


	@Override
	public ArrayList<String> getPreviousOrderIDAutocomplete(
			Connection connection, String orderId, String loginId) {
		IProductListDAO objProductListDao = new ProductListDAOImpl();
		return objProductListDao.getPreviousOrderIDAutocomplete(connection, orderId, loginId);
	}
	

}

