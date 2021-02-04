package com.retailshop.action.admin;

import com.retailshop.service.IAdminService;
import com.retailshop.service.impl.AdminServiceImpl;
import com.retailshop.util.DBConnection;
import com.retailshop.util.DBUtil;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Session;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

public class AddProductAction implements ServletResponseAware, ServletRequestAware, SessionAware{

	private static final Logger logger = Logger.getLogger(AddProductAction.class.getName());
	
	
	private File productImage[];
	private String productImageContentType[];
	private String productImageFileName[];
	private Map session;
	private Map<String, String> products;
	
    public Map<String, String> getProducts() {
		return products;
	}

	public void setProducts(Map<String, String> products) {
		this.products = products;
	}

	public File[] getProductImage() {
		return productImage;
	}

	public void setProductImage(File[] productImage) {
		this.productImage = productImage;
	}

	public String[] getProductImageContentType() {
		return productImageContentType;
	}

	public void setProductImageContentType(String productImageContentType[]) {
		this.productImageContentType = productImageContentType;
	}

	public String[] getProductImageFileName() {
		return productImageFileName;
	}

	public void setProductImageFileName(String[] productImageFileName) {
		this.productImageFileName = productImageFileName;
	}

	private String categoryName;
    private long categoryId;
    private String subCategoryId;
    private String subCategoryName;
    private Map categoryDetail;
    private String childCategoryName;
    private HttpServletResponse response;
    private HttpServletRequest request;
    private String successMsg;

    public AddProductAction()
    {
    }

    public String getChildCategoryName()
    {
        return childCategoryName;
    }

    public void setChildCategoryName(String childCategoryName)
    {
        this.childCategoryName = childCategoryName;
    }

    public String getSubCategoryId()
    {
        return subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId)
    {
        this.subCategoryId = subCategoryId;
    }

    public Map getCategoryDetail()
    {
        return categoryDetail;
    }

    public void setCategoryDetail(Map categoryDetail)
    {
        this.categoryDetail = categoryDetail;
    }

    public long getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(long categoryId)
    {
        this.categoryId = categoryId;
    }

    public String getSubCategoryName()
    {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName)
    {
        this.subCategoryName = subCategoryName;
    }

    public String getSuccessMsg()
    {
        return successMsg;
    }

    public void setSuccessMsg(String successMsg)
    {
        this.successMsg = successMsg;
    }

    public String getCategoryName()
    {
        return categoryName;
    }

    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }

    public String addMainCategory()
    {
    	ArrayList<String> messages = new ArrayList<String>();
    	
        Connection connection = DBConnection.getConnection();
        IAdminService objAdminService = new AdminServiceImpl();
        String categoryName[] = request.getParameterValues("categoryName");
        for(int i = 0; i< categoryName.length; i++){
        	
        	boolean duplicateFlag = objAdminService.checkDuplicateCategory(connection, categoryName[i]);
        	if(duplicateFlag){
        		messages.add("<font color=\"red\">Category : " + categoryName[i] + " already exists.</font>");
        	}
        	else{
        		boolean flag = objAdminService.addCategory(connection, categoryName[i]);
	        	if(flag)
	            {
	                messages.add("<font color=\"green\">Category : " + categoryName[i] + " added Successfully.</font>");
	            }
	        	else
	            {
	                messages.add("<font color=\"red\">Category : " + categoryName[i] + " has not been added.</font>");
	            }
        	}
        }
        
        request.setAttribute("messages", messages);
        
        DBUtil.closeConnection(connection);
        return "success";
    }

    public String getSubCategoryForm()
    {
        Connection connection = DBConnection.getConnection();
        IAdminService objAdminService = new AdminServiceImpl();
        setCategoryDetail(objAdminService.getAllCategory(connection));
        DBUtil.closeConnection(connection);
        return "success";
    }

    public String addSubCategory()
    {
    	
    	String subCategoryNames[] = request.getParameterValues("subCategoryName");
    	String categoryId = request.getParameter("categoryId");
    	
    	ArrayList<String> messages = new ArrayList<String>();
    	
        Connection connection = DBConnection.getConnection();
        IAdminService objAdminService = new AdminServiceImpl();
        for(int i = 0; i< subCategoryNames.length; i++){
        	
        	boolean duplicateFlag = objAdminService.checkDuplicateSubCategory(connection, subCategoryNames[i]);
        	if(duplicateFlag){
        		messages.add("<font color=\"red\">Sub Category : " + subCategoryNames[i] + " already exists.</font>");
        	}
        	else{
        		boolean flag = objAdminService.addSubCategory(connection, Long.parseLong(categoryId), subCategoryNames[i]);
	        	if(flag)
	            {
	                messages.add("<font color=\"green\">Sub Category : " + subCategoryNames[i] + " added Successfully.</font>");
	            }
	        	else
	            {
	                messages.add("<font color=\"red\">Sub Category : " + subCategoryNames[i] + " has not been added.</font>");
	            }
        	}
        }
        setCategoryDetail(objAdminService.getAllCategory(connection));
        request.setAttribute("messages", messages);
        
        DBUtil.closeConnection(connection);
        return "success";
    }

    public String getChildCategoryForm()
    {
        Connection connection = DBConnection.getConnection();
        IAdminService objAdminService = new AdminServiceImpl();
        setCategoryDetail(objAdminService.getAllCategory(connection));
        DBUtil.closeConnection(connection);
        return "success";
    }

    public String getAllSubcategory()
        throws IOException
    {
        PrintWriter out;
        String val;
        Connection connection;
        out = response.getWriter();
        val = "";
        connection = DBConnection.getConnection();
        IAdminService objAdminService = new AdminServiceImpl();
        val = objAdminService.getAllSubCategory(connection, (new StringBuilder()).append(getCategoryId()).toString());
        DBUtil.closeConnection(connection);
        out.println(val);
        return "none";
    }

    public String getAllChildCategory()
            throws IOException
        {
            PrintWriter out;
            String val;
            Connection connection;
            out = response.getWriter();
            val = "";
            connection = DBConnection.getConnection();
            IAdminService objAdminService = new AdminServiceImpl();
            val = objAdminService.getAllChildCategory(connection, ""+getCategoryId(),getSubCategoryId());
            DBUtil.closeConnection(connection);
            out.println(val);
            return "none";
        }
    
    
    public String addChildCategoryForm()
    {
        Connection connection = DBConnection.getConnection();
        IAdminService objAdminService = new AdminServiceImpl();
        setCategoryDetail(objAdminService.getAllCategory(connection));
        ArrayList<String> messages = new ArrayList<String>();
        
        String categoryId = request.getParameter("categoryId");
        String subCategoryId = request.getParameter("subCategoryId");
        String childCategoryName[] = request.getParameterValues("childCategoryName");
        for(int i =0; i< childCategoryName.length; i++){
        	boolean duplicateFlag = objAdminService.checkDuplicateChildCategory(connection, childCategoryName[i]);
        	if(duplicateFlag){
        		messages.add("<font color=\"red\">Sub Category : " + childCategoryName[i] + " already exists.</font>");
        	}
        	else{
        		boolean flag = objAdminService.addChildCategory(connection, categoryId, subCategoryId, childCategoryName[i]);
	        	if(flag)
	            {
	                messages.add("<font color=\"green\">Child Category : " + childCategoryName[i] + " added Successfully.</font>");
	            }
	        	else
	            {
	                messages.add("<font color=\"red\">Child Category : " + childCategoryName[i] + " has not been added.</font>");
	            }
        	}
        }
        request.setAttribute("messages", messages);
        DBUtil.closeConnection(connection);
        return "success";
    }

    public String getProductForm(){
    	
    	Connection connection = DBConnection.getConnection();
    	try{
	    	IAdminService objAdminService = new AdminServiceImpl();
	        setCategoryDetail(objAdminService.getAllCategory(connection));
    	}
    	finally{
    		DBUtil.closeConnection(connection);
    	}
    	
    	return "success";
    }
    
    public String getProductVariantsForm(){
    	
    	Connection connection = null;
    	try{
    		connection = DBConnection.getConnection();
    		
	    	IAdminService objAdminService = new AdminServiceImpl();
	        setProducts(objAdminService.getAllProducts(connection));
    	}
    	finally{
    		DBUtil.closeConnection(connection);
    	}
    	
    	return "success";
    }
    public String addProductVariants(){
    	
    	ArrayList messages = new ArrayList();
    	String productId = request.getParameter("productId");
    	String productPrices[] = request.getParameterValues("productPrices");
    	String offerPrices[] = request.getParameterValues("offerPrices");
    	String productSizes[] = request.getParameterValues("productSizes");
    	
    	
    	String loginId = (String)session.get("loginId");
    	
    	Connection connection = null;
    	try{
    		connection = DBConnection.getConnection();
    		IAdminService objAdminService = new AdminServiceImpl();
    		
    		for(int i = 0; i<productPrices.length; i++){
    			
    			Map allIdAndDetail = objAdminService.getAllIdForProduct(connection, productId);
    			allIdAndDetail.put("productPrice", productPrices[i]);
    			allIdAndDetail.put("offerPrices", offerPrices[i]);
    			allIdAndDetail.put("productSize", productSizes[i]);
    			allIdAndDetail.put("productId", productId);
    			
    			
    			boolean duplicateFlag = objAdminService.checkDuplicateProductVariants(connection, allIdAndDetail);
    			if(duplicateFlag){
    				messages.add("<font color='red'>Product with size : " + productSizes[i] + " and price : " + productPrices[i] + " Already Exists.</font>");
    			}
    			else{
	    			boolean flag = objAdminService.addProductVariants(connection, productId, productPrices[i], productSizes[i],loginId,offerPrices[i]);
	    			if(flag){
	    				messages.add("<font color='green'>Product with size : " + productSizes[i] + " and price : " + productPrices[i] + " has been added successfully.</font>");
	    			}
	    			else{
	    				messages.add("<font color = 'red'>Product with size : " + productSizes[i] + " and price : " + productPrices[i] + " has not been added.</font>");
	    			}
    			}
    		}
	    	
	    	request.setAttribute("messages", messages);
	        setProducts(objAdminService.getAllProducts(connection));
    	}
    	finally{
    		DBUtil.closeConnection(connection);
    	}
    	
    	
    	return "success";
    }
    
    
    public String addProduct(){
    	
    	ArrayList<String> msg = new ArrayList<String>();
    	
    	String categoryId[] = request.getParameterValues("categoryId");
    	String subCategoryId[] = request.getParameterValues("subCategoryId");
    	String childCategoryName[] = request.getParameterValues("childCategoryName");
    	String productNames[] = request.getParameterValues("productName");
    	String productBrandNames[] = request.getParameterValues("productBrandName");
    	String actualPrices[] = request.getParameterValues("actualPrice");
    	String offerPrices[] = request.getParameterValues("offerPrice");
    	String productSizes[] = request.getParameterValues("productSize");
    	String aboutProducts[] = request.getParameterValues("aboutProduct");
    	String ingredients[] = request.getParameterValues("ingredient");
    	
    	Connection connection = DBConnection.getConnection();
    	try{
	    	for(int i = 0 ; i< productNames.length; i++){
	    		
	    		Map arguments = new HashMap();
		    	arguments.put("categoryId", categoryId[i]);
		    	arguments.put("subCategoryId", subCategoryId[i]);
		    	arguments.put("childCategoryId", childCategoryName[i]);
		    	arguments.put("productName", productNames[i]);
		    	arguments.put("productBrandName", productBrandNames[i]);
		    	arguments.put("actualPrice", actualPrices[i]);
		    	arguments.put("offerPrice", offerPrices[i]);
		    	arguments.put("productSize", productSizes[i]);
		    	arguments.put("aboutProduct", aboutProducts[i]);
		    	arguments.put("ingredient", ingredients[i]);
		    	arguments.put("loginId", session.get("loginId").toString());
	    		
	    		IAdminService objAdminService = new AdminServiceImpl();
	    		boolean duplicateFlag = objAdminService.checkDuplicateProduct(connection, arguments);
	    		if(duplicateFlag){
	    			msg.add("Product Name : " + productNames[i] + " of size : " + productSizes[i] + " already exists");
	    		}
	    		else{
			    	
			    	File fileToCreate = null;
			    	try {
			            String path = request.getRealPath("")+ File.separator + "productimages";
			            logger.info("path : " + path);
			            String DBPath = "productimages"+ File.separator +this.productImageFileName[i].replaceAll(" ", "_").replaceAll("%", "_");
			            logger.info("DBPath : " + DBPath + " ,image name : " + this.productImageFileName[i]);
			            arguments.put("imagePath", DBPath);
			            fileToCreate = new File(path, this.productImageFileName[i].replaceAll(" ", "_").replaceAll("%", "_"));
			            
			            productImage[i].renameTo(fileToCreate);
			        } catch (Exception e) {
			            e.printStackTrace();
			
			        }
			    	connection.setAutoCommit(false);
		    		
			        
			        setCategoryDetail(objAdminService.getAllCategory(connection));
			        boolean flag = objAdminService.addProduct(connection, arguments);
			        if(flag)
			        {
			        	msg.add("<font color=\"green\">Product Name : " + productNames[i] + " of size : " + productSizes[i] + " has been added successfully</font>");
			        	connection.commit();
			        }
			        else{
			        	msg.add("<font color=\"red\">Product Name : " + productNames[i] + " of size : " + productSizes[i] + " has not been added.</font>");
			        	fileToCreate.delete();
			        	connection.rollback();
			        }
			    	
	    		}
	    	}
    	}
    	catch(Exception e){
    		logger.error("", e);
    	}
    	finally{
    		DBUtil.closeConnection(connection);
    	}
    	
    	request.setAttribute("messages", msg);
    	
        return "success";
    }
    
    public String getAllCategoryAjax() throws IOException{
    	
    	Connection connection = null;
    	
    	String allCategoryString = "";
    	
    	try{
    		connection = DBConnection.getConnection();
	    	IAdminService objAdminService = new AdminServiceImpl();
	        Map allCategory = objAdminService.getAllCategory(connection);
	        
	        Set s = allCategory.entrySet();
	        Iterator itr = s.iterator();
	        while(itr.hasNext()){
	        	Map.Entry me = (Map.Entry)itr.next();
	        	allCategoryString = allCategoryString + "#" + me.getKey().toString() + ":" + me.getValue().toString();
	        }
    	}
    	catch(Exception e){
    		logger.error("", e);
    	}
    	finally{
    		DBUtil.closeConnection(connection);
    	}
    	if(allCategoryString.length() > 0){
    		allCategoryString = allCategoryString.substring(1);
    	}
    	
    	PrintWriter out = response.getWriter();
    	out.println(allCategoryString);
    	
    	return "none";
    	
    }
    
    public void setServletResponse(HttpServletResponse response){
        this.response = response;
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
