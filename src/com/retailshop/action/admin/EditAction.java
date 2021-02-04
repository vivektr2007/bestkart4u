package com.retailshop.action.admin;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.retailshop.service.IAdminService;
import com.retailshop.service.impl.AdminServiceImpl;
import com.retailshop.util.DBConnection;
import com.retailshop.util.DBUtil;

public class EditAction implements ServletRequestAware, SessionAware{
	
	private Map<String, String> allCategory;
	private String msg;
	private HttpServletRequest request;
	
	private File productImage;
	private String productImageContentType;
	private String productImageFileName;
	Map session;
	
	public File getProductImage() {
		return productImage;
	}

	public void setProductImage(File productImage) {
		this.productImage = productImage;
	}

	public String getProductImageContentType() {
		return productImageContentType;
	}

	public void setProductImageContentType(String productImageContentType) {
		this.productImageContentType = productImageContentType;
	}

	public String getProductImageFileName() {
		return productImageFileName;
	}

	public void setProductImageFileName(String productImageFileName) {
		this.productImageFileName = productImageFileName;
	}

	public Map<String, String> getAllCategory() {
		return allCategory;
	}

	public void setAllCategory(Map<String, String> allCategory) {
		this.allCategory = allCategory;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String editCategoryForm(){
		
		Connection connection = DBConnection.getConnection();
        IAdminService objAdminService = new AdminServiceImpl();
        setAllCategory(objAdminService.getAllCategory(connection));
        DBUtil.closeConnection(connection);
		
        return "success";
	}

	public String editCategory(){
		
		Connection connection = DBConnection.getConnection();
		String category = request.getParameter("categoryId");
		String categoryTxt = request.getParameter("categoryText");
		
        IAdminService objAdminService = new AdminServiceImpl();
        boolean flag = objAdminService.editCategory(connection, category, categoryTxt);
        
        setAllCategory(objAdminService.getAllCategory(connection));
        
        if(flag){
        	setMsg("Category edited Successfully.");
        }
        else{
        	setMsg("There is some error while updating category.");
        }
        
        DBUtil.closeConnection(connection);
		
        return "success";
	}
	
	
	public String editChildCategoryForm(){
		
		Connection connection = DBConnection.getConnection();
        IAdminService objAdminService = new AdminServiceImpl();
        setAllCategory(objAdminService.getAllCategory(connection));
        DBUtil.closeConnection(connection);
		
        return "success";
	}

	public String editChildCategory(){
		
		Connection connection = DBConnection.getConnection();
		String category = request.getParameter("categoryId");
		String subCategoryId = request.getParameter("subCategoryId");
		String childCategoryId = request.getParameter("childCategoryId");
		String categoryTxt = request.getParameter("categoryText");
		
		
		
        IAdminService objAdminService = new AdminServiceImpl();
        boolean flag = objAdminService.editChildCategory(connection, category,subCategoryId, childCategoryId, categoryTxt);
        
        setAllCategory(objAdminService.getAllCategory(connection));
        
        if(flag){
        	setMsg("Sub Category edited Successfully.");
        }
        else{
        	setMsg("There is some error while updating category.");
        }
        
        DBUtil.closeConnection(connection);
		
        return "success";
	}
	
	public String editSubCategoryForm(){
		
		Connection connection = DBConnection.getConnection();
        IAdminService objAdminService = new AdminServiceImpl();
        setAllCategory(objAdminService.getAllCategory(connection));
        DBUtil.closeConnection(connection);
		
        return "success";
	}

	public String editSubCategory(){
		
		Connection connection = DBConnection.getConnection();
		String category = request.getParameter("categoryId");
		String subCategoryId = request.getParameter("subCategoryId");
		String categoryTxt = request.getParameter("categoryText");
		
		
		
        IAdminService objAdminService = new AdminServiceImpl();
        boolean flag = objAdminService.editSubCategory(connection, category,subCategoryId, categoryTxt);
        
        setAllCategory(objAdminService.getAllCategory(connection));
        
        if(flag){
        	setMsg("Sub Category edited Successfully.");
        }
        else{
        	setMsg("There is some error while updating category.");
        }
        
        DBUtil.closeConnection(connection);
		
        return "success";
	}
	
	
	public String getAllProductForm(){
		
		Connection connection = DBConnection.getConnection();
        IAdminService objAdminService = new AdminServiceImpl();
        setAllCategory(objAdminService.getAllCategory(connection));
        DBUtil.closeConnection(connection);
        
        return "success";
	}
	
	public String editProductForm(){
		
		Connection connection = DBConnection.getConnection();
        IAdminService objAdminService = new AdminServiceImpl();
        setAllCategory(objAdminService.getAllCategory(connection));
        
        String categoryId = request.getParameter("rootCategory");
        String subCategoryId = request.getParameter("subRootCategory");
        String childCategoryId = request.getParameter("childCategory");
        String productId = request.getParameter("productId");
        
        
        request.setAttribute("categoryId", categoryId);
        Map<String, String> productInfo = objAdminService.getProductInfo(connection, categoryId, subCategoryId, childCategoryId, productId);
        request.setAttribute("productInfo", productInfo);
        
        
        DBUtil.closeConnection(connection);
		
        return "success";
	}

	public String editProduct(){
		
		Connection connection = DBConnection.getConnection();
		
		Map arguments = new HashMap();
		
		String loginId = session.get("loginId").toString();
		
		arguments.put("CATEGORYID", request.getParameter("categoryId"));
		arguments.put("SUBCATEGORYID", request.getParameter("subCategoryId"));
		arguments.put("CHILDCATEGORYID", request.getParameter("childCategoryName"));
		arguments.put("PRODUCTNAME", request.getParameter("productName"));
		arguments.put("PRODUCTBRANDNAME",  request.getParameter("productBrandName"));
		arguments.put("ACTUALPRICE", request.getParameter("actualPrice"));
		arguments.put("OFFERPRICE",  request.getParameter("offerPrice"));
		arguments.put("SIZE", request.getParameter("productSize"));
		arguments.put("ABOUTPRODUCT",  request.getParameter("aboutProduct"));
		arguments.put("INGREDIENT",  request.getParameter("ingredient"));
		arguments.put("PRODUCTID", request.getParameter("productId"));
		arguments.put("loginId", loginId);
		
		if(this.productImageFileName != null && !("".equals(this.productImageFileName.trim()))){
		
			File fileToCreate = null;
	    	try {
	            String path = request.getRealPath("")+ File.separator + "productimages" + File.separator +this.productImageFileName;
	            String DBPath = "productimages"+"/"+this.productImageFileName;
	            arguments.put("IMAGEPATH", DBPath);
	            fileToCreate = new File(path);
	
	            FileUtils.copyFile(this.productImage, fileToCreate);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		}
		else{
			arguments.put("IMAGEPATH", request.getParameter("imagePathHidden"));
		}
		
        IAdminService objAdminService = new AdminServiceImpl();
        boolean flag = objAdminService.editProduct(connection, arguments);
        
        setAllCategory(objAdminService.getAllCategory(connection));
        if(flag){
        	setMsg("Product edited Successfully.");
        }
        else{
        	setMsg("There is some error while updating product.");
        }
        DBUtil.closeConnection(connection);
		
        return "success";
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setSession(Map session) {
		this.session = session;
	}
	
}
