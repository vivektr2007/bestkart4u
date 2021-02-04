package com.retailshop.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.retailshop.service.IProductService;
import com.retailshop.service.impl.ProductServiceImpl;
import com.retailshop.util.DBConnection;
import com.retailshop.util.DBUtil;

public class ViewProducts extends ActionSupport implements ServletResponseAware,ServletRequestAware{
	
	private static final Logger LOG = Logger.getLogger(ViewProducts.class.getName());
	
	private HttpServletResponse response;
	private HttpServletRequest request;
	private String categoryId;
	private String categoryName;
	Map<String, ArrayList<String>> productList;
	private int startIndex;
	private int productCount;
	private String productId;
	private String dropDown;
	private String price;
	private String quantity;
	private String brandName;
	private String productName;
	private String imagePath;
	private String saveValue;
	private String rootCategory;
	public List getAllBrandList() {
		return allBrandList;
	}

	public void setAllBrandList(List allBrandList) {
		this.allBrandList = allBrandList;
	}

	private String subRootCategory;
	private Map sideBarProducts;
	private List allBrandList = new ArrayList();
	
	
	public Map getSideBarProducts() {
		return sideBarProducts;
	}

	public void setSideBarProducts(Map sideBarProducts) {
		this.sideBarProducts = sideBarProducts;
	}

	public String getRootCategory() {
		return rootCategory;
	}

	public void setRootCategory(String rootCategory) {
		this.rootCategory = rootCategory;
	}

	public String getSubRootCategory() {
		return subRootCategory;
	}

	public void setSubRootCategory(String subRootCategory) {
		this.subRootCategory = subRootCategory;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getSaveValue() {
		return saveValue;
	}

	public void setSaveValue(String saveValue) {
		this.saveValue = saveValue;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDropDown() {
		return dropDown;
	}

	public void setDropDown(String dropDown) {
		this.dropDown = dropDown;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	
	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Map<String, ArrayList<String>> getProductList() {
		return productList;
	}

	public void setProductList(Map<String, ArrayList<String>> productList) {
		this.productList = productList;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	public String viewProducts(){
		
		Connection connection = null;
		try{
			connection = DBConnection.getConnection();
			
			LOG.info("********** Calling service *********");
			
			IProductService objProductService = new ProductServiceImpl();
			
			LOG.info("********** Calling service END *********");
			
			String from = request.getParameter("typeWishlist") != null ? request.getParameter("typeWishlist").toString() : "";
			if(!from.equals("") && from.equalsIgnoreCase("products")){
				setCategoryId(request.getAttribute("childCategory").toString());
				setRootCategory(request.getAttribute("rootCategory").toString());
				setSubRootCategory(request.getAttribute("subRootCategory").toString());
				
				
				/*request.setAttribute("subRootCategory", subRootCategory);
				request.setAttribute("rootCategory", rootCategory);
				request.setAttribute("productId", productId);
				request.setAttribute("productName", productName);
				request.setAttribute("childCategory", childCategory);
				request.setAttribute("startIndex", startIndex);
				request.setAttribute("from", "wishlist");*/
			}
			
			String startIndex = request.getAttribute("startIndexWishlist") != null ? request.getAttribute("startIndexWishlist").toString() : "1";
			
			String categoryName = getCategoryName() != null ? getCategoryName() : (String)request.getAttribute("categoryNameWishlist");
			
			setCategoryName(categoryName);
			
			request.setAttribute("startIndexWishList", startIndex);
			
			productList = objProductService.getProductList(connection,getCategoryId(),getRootCategory(),getSubRootCategory());
			
			productCount = objProductService.getProductCount(connection, getCategoryId(), getRootCategory(), getSubRootCategory());
			
			sideBarProducts = objProductService.getSibeBarProducts(connection, getRootCategory(), getSubRootCategory()); 
			
			setAllBrandList(objProductService.getAllBrandForProduct(connection, getRootCategory(), getSubRootCategory(), getCategoryId()));
			
		}
		finally{
			DBUtil.closeConnection(connection);
		}
		return SUCCESS;
	}
	
	
	public String viewProductsForCategory(){
		
		Connection connection = null;
		try{
			connection = DBConnection.getConnection();
			
			LOG.info("********** Calling service *********");
			
			IProductService objProductService = new ProductServiceImpl();
			
			LOG.info("********** Calling service END *********");
			
			String rootCategory = request.getAttribute("rootCategory") == null ? request.getParameter("rootCategory") : (String)request.getAttribute("rootCategory");
			
			String startIndex = request.getAttribute("startIndexWishlist") != null ? request.getAttribute("startIndexWishlist").toString() : request.getParameter("startIndex");
			
			if(startIndex == null || startIndex.equals("")){
				startIndex = "1";
			}
			
			String categoryName = getCategoryName() != null ? getCategoryName() : (String)request.getAttribute("categoryNameWishlist");
			
			setCategoryName(categoryName);
			
			request.setAttribute("startIndex", startIndex);
			
			productList = objProductService.getProductListForCategory(connection,rootCategory, Integer.parseInt(startIndex));
			
			productCount = objProductService.getProductCount(connection, getCategoryId(), rootCategory, getSubRootCategory());
			
			sideBarProducts = objProductService.getSibeBarProducts(connection, rootCategory, getSubRootCategory()); 
			
			setAllBrandList(objProductService.getAllBrandForProduct(connection, rootCategory, getSubRootCategory(), getCategoryId()));
			
		}
		finally{
			DBUtil.closeConnection(connection);
		}
		return SUCCESS;
	}
	
	public String viewProductsPagination(){
		
		Connection connection = null;
		try{
			connection = DBConnection.getConnection();
			
			IProductService objProductService = new ProductServiceImpl();
			productList = objProductService.getProductListPagination(connection,getCategoryId(), getStartIndex()-1,getRootCategory(),getSubRootCategory());
			
			productCount = objProductService.getProductCount(connection, getCategoryId(), getRootCategory(), getSubRootCategory());
			
			sideBarProducts = objProductService.getSibeBarProducts(connection, getRootCategory(), getSubRootCategory());
		}
		finally{
			DBUtil.closeConnection(connection);
		}
		return SUCCESS;
	}
	
	public String addToCart(){
		
		String arr[] = getDropDown().split("-");
		String arr1[] = arr[1].trim().split(" ");
		String val = arr[0].trim()+"-"+arr1[0].trim();
		
		String rootCategory = "";
		
		if(getRootCategory() == null || getRootCategory().trim().equals("")){
			rootCategory = " ";
		}
		else{
			rootCategory = getRootCategory();
		}
		
		String subRootCategory = "";
		
		if(getSubRootCategory() == null || getSubRootCategory().trim().equals("")){
			subRootCategory = " ";
		}
		else{
			subRootCategory = getSubRootCategory();
		}
		
		String allValue = val+"-"+Float.parseFloat(arr1[0])*Integer.parseInt(getQuantity())+"-"+getQuantity()+"-"+getBrandName()+"-"+getProductName()+"-"+Float.parseFloat(getSaveValue())*Float.parseFloat(getQuantity())+"-"+getImagePath().replaceAll("-", "#")+"-"+rootCategory+"-"+subRootCategory;
		
		Cookie c = new Cookie(getProductId(),allValue);
		c.setMaxAge(60*60*12);
		response.addCookie(c);
		
		return NONE;
	}

	
	
	public String deleteFromCart(){
		
		Cookie c[] = request.getCookies();
		for(int i = 0; i< c.length; i++){
			String name = c[i].getName();
			if(name.equalsIgnoreCase(getProductId())){
				Cookie newCookie = new Cookie(getProductId(),"");
				newCookie.setMaxAge(0);
				response.addCookie(newCookie);
				break;
			}
			else{
				continue;
			}
		}
		
		return NONE;
	}
	
	public String increaseQuantity(){
		
		Cookie c[] = request.getCookies();
		for(int i = 0; i< c.length; i++){
			String name = c[i].getName();
			if(name.equalsIgnoreCase(getProductId())){
				
				String arr[] = c[i].getValue().split("-");
				
				int newQuantity = Integer.parseInt(getQuantity())+1;
				
				arr[6] = ""+((Float.parseFloat(arr[6])/Integer.parseInt(arr[3]))*newQuantity);
				
				arr[3] = ""+newQuantity;
				float f = Float.parseFloat(arr[2])/Float.parseFloat(getQuantity()) ;
				arr[2] = ""+f*newQuantity;
				
				String allValue = "";
				for(int j = 0;j< arr.length; j++){
					allValue = allValue+"-"+arr[j];
				}
				if(allValue.length() > 1){
					allValue = allValue.substring(1);
				}
				
				Cookie newCookie = new Cookie(getProductId(),allValue);
				newCookie.setMaxAge(60*60*12);
				response.addCookie(newCookie);
				break;
				
			}
			else{
				continue;
			}
		}
		
		return NONE;
	}
	
	
public String decreaseQuantity(){
		
		Cookie c[] = request.getCookies();
		for(int i = 0; i< c.length; i++){
			String name = c[i].getName();
			if(name.equalsIgnoreCase(getProductId())){
				
				String arr[] = c[i].getValue().split("-");
				int newQuantity = 0;
				if(Integer.parseInt(getQuantity()) > 1){
					newQuantity = Integer.parseInt(getQuantity())-1;
				}
				else{
					newQuantity = Integer.parseInt(getQuantity());
				}
				arr[6] = ""+((Float.parseFloat(arr[6])/Integer.parseInt(arr[3]))*newQuantity);
				
				arr[3] = ""+newQuantity;
				float f = Float.parseFloat(arr[2])/Float.parseFloat(getQuantity()) ;
				arr[2] = ""+f*newQuantity;
				
				String allValue = "";
				for(int j = 0;j< arr.length; j++){
					allValue = allValue+"-"+arr[j];
				}
				if(allValue.length() > 1){
					allValue = allValue.substring(1);
				}
				
				Cookie newCookie = new Cookie(getProductId(),allValue);
				response.addCookie(newCookie);
				break;
				
			}
			else{
				continue;
			}
		}
		
		return NONE;
	}
	

	public String getListFromSideBar(){
		
		Connection connection = null;
		
		String rootCategory = request.getParameter("rootCategory");
		String subRootCategory = request.getParameter("subRootCategory");
		String childCategory = request.getParameter("categoryId");
		
		try{
			connection = DBConnection.getConnection();
			
			LOG.info("********** Calling service *********");
			
			IProductService objProductService = new ProductServiceImpl();
			
			LOG.info("********** Calling service END *********");
			
			productList = objProductService.getProductListSideBar(connection,childCategory,rootCategory,subRootCategory);
			
			productCount = objProductService.getProductCount(connection,childCategory , rootCategory, subRootCategory);
			
			sideBarProducts = objProductService.getSibeBarProducts(connection, rootCategory, subRootCategory); 
			
			setAllBrandList(objProductService.getAllBrandForProduct(connection, rootCategory, subRootCategory, getCategoryId()));
			
		}
		finally{
			DBUtil.closeConnection(connection);
		}
		return SUCCESS;
		
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	/*public String viewProductsPaginationSideBar(){
		
		Connection connection = null;
		try{
			connection = DBConnection.getConnection();
			
			IProductService objProductService = new ProductServiceImpl();
			productList = objProductService.getProductListPagination(connection,getCategoryId(), getStartIndex()-1,getRootCategory(),getSubRootCategory());
			
			productCount = objProductService.getProductCount(connection, getCategoryId(), getRootCategory(), getSubRootCategory());
			
			sideBarProducts = objProductService.getSibeBarProducts(connection, getRootCategory(), getSubRootCategory());
		}
		finally{
			DBUtil.closeConnection(connection);
		}
		return SUCCESS;
	}
*/	
	
	
	

}
