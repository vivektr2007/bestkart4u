package com.retailshop.action.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.retailshop.service.IAdminService;
import com.retailshop.service.impl.AdminServiceImpl;
import com.retailshop.util.DBConnection;
import com.retailshop.util.DBUtil;

public class DeleteAction implements ServletRequestAware, ServletResponseAware, SessionAware{ 

	private HttpServletRequest request;
	private HttpServletResponse response;
	private Map<String, String> allCategory;
	private String msg;
	private Map session;
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, String> getAllCategory() {
		return allCategory;
	}

	public void setAllCategory(Map<String, String> allCategory) {
		this.allCategory = allCategory;
	}

	public String deleteCategoryForm(){
		
		Connection connection = DBConnection.getConnection();
        IAdminService objAdminService = new AdminServiceImpl();
        setAllCategory(objAdminService.getAllCategory(connection));
        DBUtil.closeConnection(connection);
        
        return "success";
		
	}
	
	public String deleteCategory(){
		
		String categoryId = request.getParameter("categoryId");
		
		Connection connection = DBConnection.getConnection();
        IAdminService objAdminService = new AdminServiceImpl();
        boolean flag = objAdminService.deleteCategory(connection,categoryId);
        
        setAllCategory(objAdminService.getAllCategory(connection));
        
        DBUtil.closeConnection(connection);
		if(flag){
			setMsg("Category Deleted Successfully.");
			return "success";
		}
		else{
			setMsg("There is some error while deleting category.");
			return "error";
		}
	}
	
	
	public String deleteSubCategoryForm(){
		
		Connection connection = DBConnection.getConnection();
        IAdminService objAdminService = new AdminServiceImpl();
        setAllCategory(objAdminService.getAllCategory(connection));
        DBUtil.closeConnection(connection);
        
        return "success";
		
	}
	
	public String deleteSubCategory(){
		
		String categoryId = request.getParameter("categoryId");
		String subCategoryId = request.getParameter("subCategoryId");
		
		Connection connection = DBConnection.getConnection();
        IAdminService objAdminService = new AdminServiceImpl();
        boolean flag = objAdminService.deleteSubCategory(connection,categoryId, subCategoryId);
        
        setAllCategory(objAdminService.getAllCategory(connection));
        
        DBUtil.closeConnection(connection);
		if(flag){
			setMsg("Sub Category Deleted Successfully.");
			return "success";
		}
		else{
			setMsg("There is some error while deleting category.");
			return "error";
		}
	}
	
	
	public String deleteChildCategoryForm(){
		
		Connection connection = DBConnection.getConnection();
        IAdminService objAdminService = new AdminServiceImpl();
        setAllCategory(objAdminService.getAllCategory(connection));
        DBUtil.closeConnection(connection);
        
        return "success";
		
	}
	
	public String deleteChildCategory(){
		
		String categoryId = request.getParameter("categoryId");
		String subCategoryId = request.getParameter("subCategoryId");
		String childCategoryId = request.getParameter("childCategoryId");
		
		Connection connection = DBConnection.getConnection();
        IAdminService objAdminService = new AdminServiceImpl();
        boolean flag = objAdminService.deleteChildCategory(connection,categoryId, subCategoryId, childCategoryId);
        
        setAllCategory(objAdminService.getAllCategory(connection));
        
        DBUtil.closeConnection(connection);
		if(flag){
			setMsg("Sub Category Deleted Successfully.");
			return "success";
		}
		else{
			setMsg("There is some error while deleting category.");
			return "error";
		}
	}
	
	
	public String deleteProductForm(){
		
		Connection connection = DBConnection.getConnection();
        IAdminService objAdminService = new AdminServiceImpl();
        setAllCategory(objAdminService.getAllCategory(connection));
        DBUtil.closeConnection(connection);
        
        return "success";
	}
	
	public String getProductsAjax() throws IOException{
		
		Connection connection = DBConnection.getConnection();
        IAdminService objAdminService = new AdminServiceImpl();
        setAllCategory(objAdminService.getAllCategory(connection));
        
        String categoryId = request.getParameter("categoryId");
        String subCategoryId = request.getParameter("subCategoryId");
        String childCategoryId = request.getParameter("childCategoryId");
        
        String allProduct = objAdminService.getAllProductAjax(connection, categoryId, subCategoryId, childCategoryId);
        
        PrintWriter out = response.getWriter();
        out.println(allProduct);
        
        
        DBUtil.closeConnection(connection);
		
		return "none";
	}
	
	public String deleteProducts(){
		String categoryId = request.getParameter("categoryId");
        String subCategoryId = request.getParameter("subCategoryId");
        String childCategoryId = request.getParameter("childCategoryId");
        String productId[] = request.getParameterValues("productId");
        
        Connection connection = DBConnection.getConnection();
        IAdminService objAdminService = new AdminServiceImpl();
        setAllCategory(objAdminService.getAllCategory(connection));
        
        String loginId = session.get("loginId").toString();
        
        boolean flag = objAdminService.deleteProduct(connection, categoryId, subCategoryId, childCategoryId, productId, loginId);
        
        if(flag){
        	setMsg("Products Deleted Successfully");
        }
        else{
        	setMsg("There is some error while deleting product.");
        }
        DBUtil.closeConnection(connection);
        
		return "success";
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setSession(Map session) {
		this.session = session;
	}
	
}
