package com.retailshop.action;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.retailshop.service.IProductService;
import com.retailshop.service.impl.ProductServiceImpl;
import com.retailshop.util.DBConnection;
import com.retailshop.util.DBUtil;

public class ProductSearchAction implements ServletRequestAware, ServletResponseAware, SessionAware{

	private static final Logger logger = Logger.getLogger(ProductSearchAction.class.getName());
	
	private HttpServletResponse response;
	private HttpServletRequest request;
	private String rootCategory;
	private String subRootCategory;
	private String categoryName;
	private String categoryId;
	private List allBrandList;
	private String startIndex;
	private Map session;
	
	
	public String getAllSelectedBrand() {
		return allSelectedBrand;
	}

	public void setAllSelectedBrand(String allSelectedBrand) {
		this.allSelectedBrand = allSelectedBrand;
	}

	private String allSelectedBrand;
	
	public String getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(String startIndex) {
		this.startIndex = startIndex;
	}

	public List getAllBrandList() {
		return allBrandList;
	}

	public void setAllBrandList(List allBrandList) {
		this.allBrandList = allBrandList;
	}
	public Map<String, ArrayList<String>> getProductList() {
		return productList;
	}

	public void setProductList(Map<String, ArrayList<String>> productList) {
		this.productList = productList;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	public Map getSideBarProducts() {
		return sideBarProducts;
	}

	public void setSideBarProducts(Map sideBarProducts) {
		this.sideBarProducts = sideBarProducts;
	}

	Map<String, ArrayList<String>> productList;
	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	private int productCount;
	private Map sideBarProducts;
	
	public String productsForBrand(){
		
		String rootCategory = request.getParameter("searchRootCategory") != null? request.getParameter("searchRootCategory") : request.getAttribute("rootCategory") != null ? request.getAttribute("rootCategory").toString() : "";
		String subRootCategory = request.getParameter("searchSubRootCategory") != null ? request.getParameter("searchSubRootCategory") : request.getAttribute("subRootCategory") != null ? request.getAttribute("subRootCategory").toString() : "";
		String childCategory = request.getParameter("searchChildCategory") != null ? request.getParameter("searchChildCategory") : request.getAttribute("childCategory") != null ? request.getAttribute("childCategory").toString() : "";
		String startIndex = request.getParameter("searchStartIndex") != null ? request.getParameter("searchStartIndex") : request.getAttribute("startIndexWishlist") != null ? request.getAttribute("startIndexWishlist").toString() : "";
		setStartIndex(startIndex);
		String categoryName = request.getParameter("categoryName") != null ? request.getParameter("categoryName") : request.getAttribute("categoryNameWishlist") != null ? request.getAttribute("categoryNameWishlist").toString() : "";
		String brandName[] = request.getParameterValues("brandName");
		
		String fromfooter = request.getParameter("from");
		List<String> categoryNameList = Arrays.asList(categoryName.split(","));
		
		Set<String> allBrandsSet = new HashSet<String>();
		String allBrandIn = "";
		
		String allBrands = "";
		if(brandName != null && brandName.length > 0){
			for(int i = 0; i < brandName.length; i++){
				allBrandsSet.add(brandName[i]);
				allBrands = allBrands + "," + brandName[i];
				allBrandIn =allBrandIn + ",'" + brandName[i].toUpperCase() + "'";
			}
		}
		
		if(fromfooter != null && !fromfooter.trim().equals("") && fromfooter.equalsIgnoreCase("footer")){
			setCategoryName(allBrands.trim().substring(1));
		}
		else{
			if(allBrands.trim().startsWith(",")){
				allBrands = allBrands.substring(1);
			}
			if(allBrandsSet.size() > categoryNameList.size()){
				if(allBrandsSet.containsAll(categoryNameList)){
					setCategoryName(allBrands);
				}
				else{
					setCategoryName(allBrands);
				}
			}
			else if(allBrandsSet.size() < categoryNameList.size()){
				if(categoryNameList.containsAll(allBrandsSet)){
					setCategoryName(allBrands);
				}
				else{
					setCategoryName(categoryName);
				}
			}
			else{
				setCategoryName(allBrands);
			}
		}
		if(allBrandIn.length() > 0){
			allBrandIn = allBrandIn.substring(1);
		}
		/*if(allBrandIn != null && !allBrandIn.equals("") && request.getAttribute("brandName") != null){
			allBrandIn = request.getAttribute("brandName").toString();
		}*/
		
		if(request.getAttribute("brandName") != null && !request.getAttribute("brandName").toString().equals("") && !request.getAttribute("brandName").toString().equalsIgnoreCase("null")){
			String brandNameRequest = allBrandIn = request.getAttribute("brandName").toString();
			String brandNameRequestArr[] = brandNameRequest.split(",");
			allBrandIn = "";
			for(int i = 0; i < brandNameRequestArr.length; i++){
				allBrandIn =allBrandIn + ",'" + brandNameRequestArr[i].toUpperCase() + "'";
			}
			if(allBrandIn.length() > 0){
				allBrandIn = allBrandIn.substring(1);
			}
			
		}
		
		
		setAllSelectedBrand(allBrands);
		
		setRootCategory(rootCategory);
		setSubRootCategory(subRootCategory);
		setCategoryId(childCategory);
		
		Connection connection = null;
		try{
			connection = DBConnection.getConnection();
			IProductService objProductService = new ProductServiceImpl();
			productList = objProductService.getProductListForBrand(connection, rootCategory, subRootCategory, childCategory, allBrandIn, Integer.parseInt(startIndex), fromfooter);
			
			productCount = objProductService.getProductCountForBrand(connection, childCategory, getRootCategory(), getSubRootCategory(), allBrandIn,fromfooter);
			
			sideBarProducts = objProductService.getSibeBarProducts(connection, getRootCategory(), getSubRootCategory());
			
			setAllBrandList(objProductService.getAllBrandForProduct(connection, getRootCategory(), getSubRootCategory(), getCategoryId()));
		}
		catch (Exception e) {
			logger.error("", e);
		}
		return "success";
	}
	
	
	

	public String productsForBrandHeaderSearch(){
		
		String rootCategory = request.getParameter("searchRootCategory") != null? request.getParameter("searchRootCategory") : request.getAttribute("rootCategory") != null ? request.getAttribute("rootCategory").toString() : "";
		String subRootCategory = request.getParameter("searchSubRootCategory") != null ? request.getParameter("searchSubRootCategory") : request.getAttribute("subRootCategory") != null ? request.getAttribute("subRootCategory").toString() : "";
		String childCategory = request.getParameter("searchChildCategory") != null ? request.getParameter("searchChildCategory") : request.getAttribute("childCategory") != null ? request.getAttribute("childCategory").toString() : "";
		String startIndex = request.getParameter("searchStartIndex") != null ? request.getParameter("searchStartIndex") : request.getAttribute("startIndexWishlist") != null ? request.getAttribute("startIndexWishlist").toString() : "1";
		
		setStartIndex(startIndex);
		String categoryName = request.getParameter("categoryName") != null ? request.getParameter("categoryName") : request.getAttribute("categoryNameWishlist") != null ? request.getAttribute("categoryNameWishlist").toString() : "";
		
		String fromfooter = request.getParameter("from");
		
		String allBrands = "";
		
		
		/*if(allBrandIn != null && !allBrandIn.equals("") && request.getAttribute("brandName") != null){
			allBrandIn = request.getAttribute("brandName").toString();
		}*/
		
		
		
		String productName = "";
		String searchProductAutocomplete = request.getParameter("searchProductAutocomplete");
		if(searchProductAutocomplete != null && !searchProductAutocomplete.equals("") && !searchProductAutocomplete.equalsIgnoreCase("null") ){
			
			String arr [] =searchProductAutocomplete.split("-");
			if(arr != null && arr.length > 1){
				productName = arr[1].trim();
				allBrands = arr[1].trim();
			}
			else{
				productName = searchProductAutocomplete;
				allBrands = searchProductAutocomplete;
			}
			setCategoryName(allBrands);
		}
		
		if(productName == null || productName.trim().equals("")){
			productName = categoryName;
			setCategoryName(productName);
		}
		
		setAllSelectedBrand(allBrands);
		
		setRootCategory(rootCategory);
		setSubRootCategory(subRootCategory);
		setCategoryId(childCategory);
		
		Connection connection = null;
		try{
			connection = DBConnection.getConnection();
			IProductService objProductService = new ProductServiceImpl();
			productList = objProductService.getProductListForBrand(connection, rootCategory, subRootCategory, childCategory, "", Integer.parseInt(startIndex), fromfooter,productName);
			
			productCount = objProductService.getProductCountForBrand(connection, childCategory, getRootCategory(), getSubRootCategory(), "",fromfooter, productName);
		
			sideBarProducts = objProductService.getSibeBarProducts(connection, getRootCategory(), getSubRootCategory());
			
			setAllBrandList(objProductService.getAllBrandForProduct(connection, getRootCategory(), getSubRootCategory(), getCategoryId()));
		}
		catch (Exception e) {
			logger.error("", e);
		}
		return "success";
	}
	
	public String searchProductFromAutocomplete(){
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
	
	public String searchPreviousOrderIdAutocomplete(){
		Connection connection = null;
		try{
			
			String loginId = (String)session.get("loginId");
			
			response.setContentType("application/json");
			String searchKey = request.getParameter("name");
			JSONArray jsonArr = new JSONArray();
			
			connection = DBConnection.getConnection();
			IProductService objProductService = new ProductServiceImpl();
			ArrayList<String> al = (ArrayList<String>)objProductService.getPreviousOrderIDAutocomplete(connection, searchKey, loginId);
			Iterator<String> itr = al.iterator();
			while(itr.hasNext()){
				String arr = itr.next();
				JSONObject json=new JSONObject();
				json.put("name",arr);
		        
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
	
	public String searchPendingOrderIdAutocomplete(){
		Connection connection = null;
		try{
			
			String loginId = (String)session.get("loginId");
			
			response.setContentType("application/json");
			String searchKey = request.getParameter("name");
			JSONArray jsonArr = new JSONArray();
			
			connection = DBConnection.getConnection();
			IProductService objProductService = new ProductServiceImpl();
			ArrayList<String> al = (ArrayList<String>)objProductService.getOrderIDAutocomplete(connection, searchKey, loginId);
			Iterator<String> itr = al.iterator();
			while(itr.hasNext()){
				String arr = itr.next();
				JSONObject json=new JSONObject();
				json.put("name",arr);
		        
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
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
