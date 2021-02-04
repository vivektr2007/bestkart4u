package com.retailshop.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface IAdminDAO
{

    public static final String GET_ORDER_DETAIL = "select * from order_detail where order_status = ? and cart_id=?";
    public static final String ADD_CATEGORY = "insert into master_category(master_category_id,master_category_name) values(?,?)";
    public static final String ADD_SUB_CATEGORY = "insert into sub_category1(sub_category_id,sub_category_name, master_category_id)" +
" values(?,?,?)"
;
    public static final String GET_ALL_CATEGORY = "select * from master_category order by master_category_name";
    public static final String GET_ALL_SUB_CATEGORY = "select * from sub_category1 where master_category_id = ?";
    public static final String ADMIN_LOGIN_QUERY = "select * from admin_detail where adminid= ? and password = ?";
    public static final String ADD_CHILD_CATEGORY = "insert into sub_category2(master_category_id,sub_category_id1,sub_category_id2,s" +
"ub_category_desc) values(?,?,?,?)"
;

    public abstract Map getOrderDetail(Connection connection);
    
    public abstract Map getOrderDetailAfterDeliver(Connection connection);
    
    public abstract Map getOrderDetailForInvoice(Connection connection);

    public abstract boolean addCategory(Connection connection, String s);

    public abstract boolean addSubCategory(Connection connection, long l, String s);

    public abstract Map getAllCategory(Connection connection);

    public abstract String getAllSubCategory(Connection connection, String s);

    public abstract boolean adminLoginCheck(Connection connection, String s, String s1);

    public abstract boolean addChildCategory(Connection connection, String s, String s1, String s2);
    
    
    public String getAllChildCategory(Connection connection, String categoryId, String subCategoryId);
    
    public boolean addProduct(Connection connection, Map parameters);
    
    public boolean deleteCategory(Connection connection, String categoryId);
    
    public boolean deleteSubCategory(Connection connection, String categoryId, String subCategoryId);
    
    public boolean deleteChildCategory(Connection connection, String categoryId, String subCategoryId, String childCategoryId);
    
    public boolean deleteProduct(Connection connection, String categoryId, String subCategoryId, String childCategoryId, String productId[], String loginId);
    
    public String getAllProductAjax(Connection connection, String categoryId, String subCategoryId, String childCategoryId);
    
    public boolean editCategory(Connection connection, String categoryId, String categoryText);
    
    public boolean editSubCategory(Connection connection, String categoryId,String subCategoryId, String categoryText);
    
    public boolean editChildCategory(Connection connection, String categoryId,String subCategoryId,String childCategory, String categoryText);
    
    public Map<String, String> getProductInfo(Connection connection, String categoryId,String subCategoryId,String childCategoryId, String productId);
    
    public boolean editProduct(Connection connection, Map arguments);
    
    public String CheckDuplicateProductQuery = "select * from product_info where product_name = upper(?) and product_size = upper(?) and product_brand_name = upper(?)";
    
    public boolean checkDuplicateProduct(Connection connection, Map arguments);
    
    public String CheckDuplicateCategoryQuery = "select * from master_category where master_category_name = upper(?) ";
    
    public boolean checkDuplicateCategory(Connection connection, String categoryName);
    
    public String CheckDuplicateSubCategoryQuery = "select * from sub_category1 where sub_category_name = upper(?) ";
    
    public boolean checkDuplicateSubCategory(Connection connection, String categoryName);
    
    public String CheckDuplicateChildCategoryQuery = "select * from sub_category2 where sub_category_desc = upper(?) ";
    
    public boolean checkDuplicateChildCategory(Connection connection,String categoryName);
    
    public String getAllProducts = "select * from product_info order by product_name desc";
    
    public Map<String, String> getAllProducts(Connection connection);
    
    public String AddProductVariants = "insert into product_price_detail(root_category,sub_root_category,child_category,product_id,product_info,product_price,offer_price,created_by,created_on) values(?,?,?,?,?,?,?,?,now())";
    
    public boolean addProductVariants(Connection connection, String productId, String productPrices, String productSizes, String loginId, String offerPrice);
    
    public String CheckDuplicateProductVariants = "select * from product_price_detail where root_category = ? and sub_root_category = ? and child_category = ? and product_id = ? and upper(product_info) = ? and product_price = ?";
    
    public boolean checkDuplicateProductVariants(Connection connection, Map allIdAndDetail);
    
    public String getAllIDForProduct = "select * from product_info where product_id = ?";
    
    public Map getAllIdForProduct(Connection connection,String productId);
    
    String CHECK_PASSWORD = "SELECT * FROM admin_detail WHERE adminid=? and password=?";
    
    public boolean checkOldPassword(Connection connection, String loginId,	String oldPassword);
    
    String CHANGE_PASSWORD = "update admin_detail set password = ? where adminid = ?";
    
    public boolean updatePassword(Connection connection, String loginId,String newPassword);
    
    public boolean acceptRejectPendingOrder(Connection connection, String cartId, String action);
    
    public String getEmailIdForOrderId(Connection connection, String orderId);
    
    public List<String> getAllImages(Connection connection);

    public List checkAllImages(Connection connection);
    
}
