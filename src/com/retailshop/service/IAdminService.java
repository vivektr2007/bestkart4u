package com.retailshop.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface IAdminService
{

    public abstract Map getOrderDetail(Connection connection);
    
    public abstract Map getOrderDetailAfterDeliver(Connection connection);
    
    public abstract Map getOrderDetailForInvoice(Connection connection);

    public abstract boolean addCategory(Connection connection, String s);

    public abstract boolean addSubCategory(Connection connection, long l, String s);

    public abstract Map getAllCategory(Connection connection);

    public abstract boolean adminLoginCheck(Connection connection, String s, String s1);

    public abstract String getAllSubCategory(Connection connection, String s);

    public abstract boolean addChildCategory(Connection connection, String s, String s1, String s2);
    
    public String getAllChildCategory(Connection connection, String categoryId, String subCategoryId);
    
    public boolean addProduct(Connection connection, Map parameters);
    
    public boolean deleteCategory(Connection connection, String categoryId);
    
    public boolean deleteSubCategory(Connection connection, String categoryId, String subCategoryId);
    
    public boolean deleteChildCategory(Connection connection, String categoryId, String subCategoryId, String childCategoryId);
    
    public String getAllProductAjax(Connection connection, String categoryId, String subCategoryId, String childCategoryId);
    
    public boolean deleteProduct(Connection connection, String categoryId, String subCategoryId, String childCategoryId, String productId[], String loginId);
    
    public boolean editCategory(Connection connection, String categoryId, String categoryText);
    
    public boolean editSubCategory(Connection connection, String categoryId,String subCategoryId, String categoryText);
    
    public boolean editChildCategory(Connection connection, String categoryId,String subCategoryId,String childCategoryId, String categoryText);
    
    public Map<String, String> getProductInfo(Connection connection, String categoryId,String subCategoryId,String childCategoryId, String productId);
    
    public boolean editProduct(Connection connection, Map arguments);
    
    public boolean checkDuplicateProduct(Connection connection, Map arguments);
    
    public boolean checkDuplicateCategory(Connection connection, String categoryName);
    
    public boolean checkDuplicateSubCategory(Connection connection, String subCategoryName);
    
    public boolean checkDuplicateChildCategory(Connection connection,String categoryName);
    
    public Map<String, String> getAllProducts(Connection connection);
    
    public boolean addProductVariants(Connection connection,String productId,String productPrices,String productSizes, String loginId, String offerPrice );
    
    public boolean checkDuplicateProductVariants(Connection connection, Map allIdAndDetail);
    
    public Map getAllIdForProduct(Connection connection,String productId);
    
    public boolean checkOldPassword(Connection connection, String loginId, String oldPassword);
    
    public boolean updatePassword(Connection connection, String loginId, String newPassword);
    
    public boolean acceptRejectPendingOrder(Connection connection, String cartId, String action);
    
    public String getEmailIdForOrderId(Connection connection, String orderId);
    
    public List<String> getAllImages(Connection connection);
    
    public List checkAllImages(Connection connection);
    
}
