package com.retailshop.service.impl;

import com.retailshop.dao.IAdminDAO;
import com.retailshop.dao.impl.AdminDaoImpl;
import com.retailshop.service.IAdminService;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class AdminServiceImpl implements IAdminService
{

    public AdminServiceImpl()
    {
    }

    public Map getOrderDetail(Connection connection)
    {
        IAdminDAO objAdminDao = new AdminDaoImpl();
        return objAdminDao.getOrderDetail(connection);
    }

    public Map getOrderDetailForInvoice(Connection connection)
    {
        IAdminDAO objAdminDao = new AdminDaoImpl();
        return objAdminDao.getOrderDetailForInvoice(connection);
    }
    
    public boolean addCategory(Connection connection, String categoryName)
    {
        IAdminDAO objAdminDao = new AdminDaoImpl();
        return objAdminDao.addCategory(connection, categoryName);
    }

    public boolean addSubCategory(Connection connection, long categiryId, String subCategoryName)
    {
        IAdminDAO objAdminDao = new AdminDaoImpl();
        return objAdminDao.addSubCategory(connection, categiryId, subCategoryName);
    }

    public Map getAllCategory(Connection connection)
    {
        IAdminDAO objAdminDao = new AdminDaoImpl();
        return objAdminDao.getAllCategory(connection);
    }

    public boolean adminLoginCheck(Connection connection, String adminId, String password)
    {
        IAdminDAO objAdminDao = new AdminDaoImpl();
        return objAdminDao.adminLoginCheck(connection, adminId, password);
    }

    public String getAllSubCategory(Connection connection, String masterCategoryId)
    {
        IAdminDAO objAdminDao = new AdminDaoImpl();
        return objAdminDao.getAllSubCategory(connection, masterCategoryId);
    }

    public boolean addChildCategory(Connection connection, String masterCategoryId, String subCategoryId, String childCategoryName)
    {
        IAdminDAO objAdminDao = new AdminDaoImpl();
        return objAdminDao.addChildCategory(connection, masterCategoryId, subCategoryId, childCategoryName);
    }

	@Override
	public String getAllChildCategory(Connection connection,
			String categoryId, String subCategoryId) {
		IAdminDAO objAdminDao = new AdminDaoImpl();
        return objAdminDao.getAllChildCategory(connection, categoryId, subCategoryId);
	}

	@Override
	public boolean addProduct(Connection connection, Map parameters) {
		IAdminDAO objAdminDao = new AdminDaoImpl();
        return objAdminDao.addProduct(connection, parameters);
	}

	@Override
	public boolean deleteCategory(Connection connection, String categoryId){
		
		IAdminDAO objAdminDao = new AdminDaoImpl();
        return objAdminDao.deleteCategory(connection, categoryId);
		
	}

	@Override
	public boolean deleteSubCategory(Connection connection, String categoryId,
			String subCategoryId) {
		IAdminDAO objAdminDao = new AdminDaoImpl();
        return objAdminDao.deleteSubCategory(connection, categoryId, subCategoryId);
	}

	@Override
	public boolean deleteChildCategory(Connection connection,
			String categoryId, String subCategoryId, String childCategoryId) {
		IAdminDAO objAdminDao = new AdminDaoImpl();
        return objAdminDao.deleteChildCategory(connection, categoryId, subCategoryId, childCategoryId);
	}
	@Override
	public String getAllProductAjax(Connection connection, String categoryId, String subCategoryId, String childCategoryId){
		IAdminDAO objAdminDao = new AdminDaoImpl();
        return objAdminDao.getAllProductAjax(connection, categoryId, subCategoryId, childCategoryId);
	}
	@Override
	public boolean deleteProduct(Connection connection, String categoryId, String subCategoryId, String childCategoryId, String productId[], String loginId){
		IAdminDAO objAdminDao = new AdminDaoImpl();
        return objAdminDao.deleteProduct(connection, categoryId, subCategoryId, childCategoryId,productId, loginId);
	}

	@Override
	public boolean editCategory(Connection connection, String categoryId,
			String categoryText) {
		IAdminDAO objAdminDao = new AdminDaoImpl();
        return objAdminDao.editCategory(connection, categoryId, categoryText);
	}
	
	@Override
	public boolean editSubCategory(Connection connection, String categoryId,String subCategoryId,
			String categoryText) {
		IAdminDAO objAdminDao = new AdminDaoImpl();
        return objAdminDao.editSubCategory(connection, categoryId,subCategoryId, categoryText);
	}
	
	@Override
	public boolean editChildCategory(Connection connection, String categoryId,String subCategoryId,String childCategoryId,
			String categoryText) {
		IAdminDAO objAdminDao = new AdminDaoImpl();
        return objAdminDao.editChildCategory(connection, categoryId,subCategoryId,childCategoryId, categoryText);
	}

	@Override
	public Map<String, String> getProductInfo(Connection connection,
			String categoryId, String subCategoryId, String childCategoryId,
			String productId) {
		IAdminDAO objAdminDao = new AdminDaoImpl();
        return objAdminDao.getProductInfo(connection, categoryId,subCategoryId,childCategoryId, productId);
	}

	@Override
	public boolean editProduct(Connection connection, Map arguments) {
		IAdminDAO objAdminDao = new AdminDaoImpl();
        return objAdminDao.editProduct(connection, arguments);
	}
	
	@Override
	public boolean checkDuplicateProduct(Connection connection, Map arguments){
		IAdminDAO objAdminDao = new AdminDaoImpl();
        return objAdminDao.checkDuplicateProduct(connection, arguments);
	}

	@Override
	public boolean checkDuplicateCategory(Connection connection,
			String categoryName) {
		IAdminDAO objAdminDao = new AdminDaoImpl();
        return objAdminDao.checkDuplicateCategory(connection, categoryName);
	}

	@Override
	public boolean checkDuplicateSubCategory(Connection connection,
			String subCategoryName) {
		IAdminDAO objAdminDao = new AdminDaoImpl();
        return objAdminDao.checkDuplicateSubCategory(connection, subCategoryName);
	}

	@Override
	public boolean checkDuplicateChildCategory(Connection connection,
			String categoryName) {
		IAdminDAO objAdminDao = new AdminDaoImpl();
        return objAdminDao.checkDuplicateChildCategory(connection, categoryName);
	}

	@Override
	public Map<String, String> getAllProducts(Connection connection) {
		IAdminDAO objAdminDao = new AdminDaoImpl();
        return objAdminDao.getAllProducts(connection);
	}

	@Override
	public boolean addProductVariants(Connection connection, String productId,
			String productPrices, String productSizes,String loginId, String offerPrice) {
		IAdminDAO objAdminDao = new AdminDaoImpl();
        return objAdminDao.addProductVariants(connection, productId, productPrices,productSizes, loginId, offerPrice);
	}

	@Override
	public boolean checkDuplicateProductVariants(Connection connection, Map allIdAndDetail) {
		IAdminDAO objAdminDao = new AdminDaoImpl();
        return objAdminDao.checkDuplicateProductVariants(connection, allIdAndDetail);
	}
	@Override
	public Map getAllIdForProduct(Connection connection,String productId){
		IAdminDAO objAdminDao = new AdminDaoImpl();
		return objAdminDao.getAllIdForProduct(connection, productId);
	}

	@Override
	public boolean checkOldPassword(Connection connection, String loginId,
			String oldPassword) {
		IAdminDAO objAdminDAO = new AdminDaoImpl();
		return objAdminDAO.checkOldPassword(connection,loginId,oldPassword);
	}

	@Override
	public boolean updatePassword(Connection connection, String loginId,
			String newPassword) {
		IAdminDAO objAdminDAO = new AdminDaoImpl();
		return objAdminDAO.updatePassword(connection,loginId,newPassword);
	}

	@Override
	public boolean acceptRejectPendingOrder(Connection connection,
			String cartId, String action) {
		IAdminDAO objAdminDAO = new AdminDaoImpl();
		return objAdminDAO.acceptRejectPendingOrder(connection, cartId, action);
	}

	@Override
	public String getEmailIdForOrderId(Connection connection, String orderId) {
		IAdminDAO objAdminDao = new AdminDaoImpl();
        return objAdminDao.getEmailIdForOrderId(connection,orderId);
	}

	@Override
	public List<String> getAllImages(Connection connection) {
		IAdminDAO objAdminDao = new AdminDaoImpl();
        return objAdminDao.getAllImages(connection);
	}

	@Override
	public List checkAllImages(Connection connection) {
		IAdminDAO objAdminDao = new AdminDaoImpl();
		return objAdminDao.checkAllImages(connection);
	}

	@Override
	public Map getOrderDetailAfterDeliver(Connection connection) {
		IAdminDAO objAdminDao = new AdminDaoImpl();
        return objAdminDao.getOrderDetailAfterDeliver(connection);
	}
	
	
}
