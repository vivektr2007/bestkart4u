package com.retailshop.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.retailshop.service.IProductService;
import com.retailshop.service.impl.ProductServiceImpl;
import com.retailshop.util.DBConnection;
import com.retailshop.util.DBUtil;

public class WishlistAction implements ServletRequestAware, ServletResponseAware{
	private static final Logger LOG = Logger.getLogger(WishlistAction.class.getName());
	private HttpServletRequest request;
	private HttpServletResponse response;
	private int startIndex;
	
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public String removeFromWishlist(){
		String loginId = request.getSession().getAttribute("loginId").toString();
		String productId = request.getParameter("productId");
		
		Connection connection = null;
		try{
			connection = DBConnection.getConnection();
			IProductService objProductService = new ProductServiceImpl();
			boolean flag = objProductService.removeProductFromWishlist(connection, loginId,productId);
			
			PrintWriter out = response.getWriter();
			
			if(flag){
				out.print("Item removed from wishlist.");
			}
			else{
				out.print("There is some internal error..");
			}
		}
		catch(Exception  e){
			LOG.error("", e);
		}
		
		return "none";
	}
	public String addToCartFromWishlist(){
		String productId = request.getParameter("productId");
		String productSize = request.getParameter("productSize");
		String price = request.getParameter("price");
		String actualPrice = request.getParameter("actualPrice");
		String quantity = request.getParameter("quantity");
		String brandName = request.getParameter("brandName");
		String productName = request.getParameter("productName");
		String imagePath = request.getParameter("imagePath");
		
		Double saveValue = Double.parseDouble(actualPrice) - Double.parseDouble(price);
		String subRootCategory = request.getParameter("subRootCategory");
		String rootCategory = request.getParameter("rootCategory");
		
		
		String allValue = productSize + "-" + price +"-"+Double.parseDouble(price)*Integer.parseInt(quantity)+"-"+quantity+"-"+brandName+"-"+productName+"-"+saveValue*Integer.parseInt(quantity)+"-"+imagePath.replaceAll("-", "#")+"-"+rootCategory+"-"+subRootCategory;
		
		Cookie c = new Cookie(productId,allValue);
		c.setMaxAge(60*60*12);
		response.addCookie(c);
		
		return "none";
	}
	public String getProductsForWishlist(){
		
		String loginId = request.getSession().getAttribute("loginId").toString();
		
		Connection connection = null;
		try{
			connection = DBConnection.getConnection();
			IProductService objProductService = new ProductServiceImpl();
			String wishListProducts = objProductService.getWishlistProducts(connection, loginId);
			
			PrintWriter out = response.getWriter();
			
			out.print(wishListProducts);
			
		}
		catch(Exception  e){
			LOG.error("", e);
		}
		
		return "none";
	}
	

	public String addToWishList() throws IOException{
		
		PrintWriter out = response.getWriter();
		
		int productId = Integer.parseInt(request.getParameter("productId"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		String subRootCategory = request.getParameter("subRootCategory");
		String rootCategory = request.getParameter("rootCategory");
		String productName = request.getParameter("productName");
		String childCategory = request.getParameter("childCategory");
		String startIndex = request.getParameter("startIndex");
		
		String brandNameArr[] = request.getParameterValues("brandName");
		
		String allBrandIn = "";
		
		if(brandNameArr != null && brandNameArr.length > 0){
			for(int i = 0; i < brandNameArr.length; i++){
				allBrandIn =allBrandIn + ",'" + brandNameArr[i].toUpperCase() + "'";
			}
		}
		if(allBrandIn.length() > 0){
			allBrandIn = allBrandIn.substring(1);
		}
		
		request.setAttribute("brandName", allBrandIn);
		
		HttpSession session = request.getSession();
		Connection connection = null;
		
		if(session != null && session.getAttribute("loginId") != null){
			String loginId = session.getAttribute("loginId").toString();
			try{
				connection = DBConnection.getConnection();
				IProductService objProductService = new ProductServiceImpl();
				boolean flag = objProductService.addToWishlist(connection, productId, loginId, quantity);
				
				if(flag){
					out.println("success");
				}
				else{
					out.println("error");
				}
			}
			catch (Exception e) {
				LOG.error("",e);
			}
			finally{
				DBUtil.closeConnection(connection);
			}	
		}
		else{
			
			request.setAttribute("subRootCategory", subRootCategory);
			request.setAttribute("rootCategory", rootCategory);
			request.setAttribute("productId", productId);
			request.setAttribute("productName", productName);
			request.setAttribute("childCategory", childCategory);
			request.setAttribute("startIndex", startIndex);
			request.setAttribute("from", "wishlist");
			
			
			
			out.println(rootCategory + ":" + subRootCategory  + ":" + 
			childCategory + ":" + productName + ":" + startIndex + ":" + productId + ":"
			+ "gotTOLogin"+":"+quantity);
		}
		return "none";
	}
	
	public String wishlistLogin(){
		
		request.setAttribute("rootCategory", request.getParameter("rootCategoryWishlist"));
		request.setAttribute("subRootCategory", request.getParameter("subRootCategoryWishlist"));
		request.setAttribute("productId", request.getParameter("productIdWishlist"));
		request.setAttribute("categoryNameWishlist", request.getParameter("categoryNameWishlist"));
		
		request.setAttribute("childCategory", request.getParameter("childCategoryWishlist"));
		request.setAttribute("startIndex", request.getParameter("startIndexWishlist"));
		request.setAttribute("type", request.getParameter("type"));
		request.setAttribute("quantity", request.getParameter("quantityWishlist"));
		request.setAttribute("brandName", request.getParameter("brandName"));
		
		request.setAttribute("from", "wishlist");
		
		request.setAttribute("wishListLoginMsg", "Please log in to add product in your wishlist.");
		
		
		return "success";
	}
	
	public String wishlistLoginSuccess(){
		HttpSession session = request.getSession();
		
		String loginId = session.getAttribute("loginId").toString();
		
		int productId = Integer.parseInt(request.getAttribute("productId").toString());
		int quantity = Integer.parseInt(request.getAttribute("quantity")!=null?(String)request.getAttribute("quantity"):"1");
		Connection connection = null;
		try{
			connection = DBConnection.getConnection();
			IProductService objProductService = new ProductServiceImpl();
			boolean flag = objProductService.addToWishlist(connection, productId, loginId, quantity);
			
			if(flag){
				request.setAttribute("wishlistSuccess", "Product added to wishlist.");
			}			
		}
		catch (Exception e) {
			LOG.error("",e);
		}
		finally{
			DBUtil.closeConnection(connection);
		}
		
		String type = (String)request.getAttribute("typeWishlist");
		if(type!= null && type.equalsIgnoreCase("productsForCategory")){
			return "successForProductCategory";
		}
		else if(type!= null && type.equalsIgnoreCase("productsForBrandSearch")){
			return "successForProductBrandSearch";
		}
		else if(type!= null && type.equalsIgnoreCase("productsForSearchHeader")){
			return "SearchProductActionHeader";
		}
		else{
			return "success";
		}
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
