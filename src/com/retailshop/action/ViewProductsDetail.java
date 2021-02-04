package com.retailshop.action;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.retailshop.service.IProductService;
import com.retailshop.service.impl.ProductServiceImpl;
import com.retailshop.util.DBConnection;
import com.retailshop.util.DBUtil;

public class ViewProductsDetail extends ActionSupport{

	private String productId;
	private Map productDetail;
	private Map<String, ArrayList> relatedProducts;
	private Map<String, ArrayList> relatedBrandProducts;
	private String subRootCategory;
	private String rootCategory;
	
	public String getSubRootCategory() {
		return subRootCategory;
	}
	public void setSubRootCategory(String subRootCategory) {
		this.subRootCategory = subRootCategory;
	}
	public String getRootCategory() {
		return rootCategory;
	}
	public void setRootCategory(String rootCategory) {
		this.rootCategory = rootCategory;
	}
	public Map<String, ArrayList> getRelatedBrandProducts() {
		return relatedBrandProducts;
	}
	public void setRelatedBrandProducts(Map<String, ArrayList> relatedBrandProducts) {
		this.relatedBrandProducts = relatedBrandProducts;
	}
	public Map<String, ArrayList> getRelatedProducts() {
		return relatedProducts;
	}
	public void setRelatedProducts(Map<String, ArrayList> relatedProducts) {
		this.relatedProducts = relatedProducts;
	}
	public Map getProductDetail() {
		return productDetail;
	}
	public void setProductDetail(Map productDetail) {
		this.productDetail = productDetail;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}


	public String getProductDetails(){
		
		Connection connection = null;
		try{
			
			connection = DBConnection.getConnection();
			IProductService objProductService = new ProductServiceImpl();
			productDetail = objProductService.productDetail(connection, getProductId(),getRootCategory(),getSubRootCategory());
			
			relatedProducts = objProductService.getRelatedProduct(connection, getProductId(),getRootCategory(),getSubRootCategory());
			
			relatedBrandProducts = objProductService.getRelatedBrandProduct(connection, getProductId(), getRootCategory(), getSubRootCategory());
		}
		finally{
			DBUtil.closeConnection(connection);
		}
		return SUCCESS;
	}
	
	public String getProductDetailsForCategory(){
		
		Connection connection = null;
		try{
			
			connection = DBConnection.getConnection();
			IProductService objProductService = new ProductServiceImpl();
			productDetail = objProductService.productDetailForCategory(connection, getProductId());
			
			relatedProducts = objProductService.getRelatedProduct(connection, getProductId(),getRootCategory(),getSubRootCategory());
			
			relatedBrandProducts = objProductService.getRelatedBrandProduct(connection, getProductId(), getRootCategory(), getSubRootCategory());
		}
		finally{
			DBUtil.closeConnection(connection);
		}
		return SUCCESS;
	}
	
	
}
