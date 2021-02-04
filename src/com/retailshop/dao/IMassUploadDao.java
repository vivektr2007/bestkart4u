package com.retailshop.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public interface IMassUploadDao {
	public String uploadProduct(Connection connection, String userName, Map<String, String> parameters);
	
	public String CheckDuplicateProductQuery = "select * from product_info where upper(product_name) = upper(?) and upper(product_size) = upper(?) and upper(product_brand_name) = upper(?)";
	
	String CheckDuplicateProductForUpdate = "select * from product_info where upper(product_name) = upper(?) and upper(product_brand_name) = upper(?)";
	
	public String CheckDuplicateProductForVariantQuery = "select * from product_info where upper(product_name) = upper(?) and upper(product_brand_name) = upper(?)";
	
	public boolean checkDuplicateProduct(Connection connection, Map arguments);
	
	public String getSingleColumnValue(Connection connection, String targetColumnName, String TableName, String whereClause);
	
	public String addCategory(Connection connection, String categoryName);
	
	public String addSubCategory(Connection connection, long categiryId, String subCategoryName);
	
	public String addChildCategory(Connection connection, String masterCategoryId, String subCategoryId, String childCategoryName);
	
	//String downloadProd = "select (select master_category_name from master_category mc where mc.master_category_id = pi.root_category) root_category,(select sub_category_name from sub_category1 sb1 where sb1.master_category_id = pi.root_category and sb1.Sub_category_id = pi.sub_root_category)  sub_root_category,(select sub_category_desc from sub_category2 sb2 where sb2.master_category_id = pi.root_category and sb2.sub_category_id1 = pi.sub_root_category and sb2.sub_category_id2 = pi.category_id)  category_id,product_name,product_brand_name,actual_price,offer_price,product_size,about_product,ingredient,image_path from product_info pi";
	
	//String downloadProd = "select (select mc.master_category_name from master_category mc where mc.master_category_id = pi.root_category) root_category,(select sb1.sub_category_name from sub_category1 sb1 where sb1.master_category_id = pi.root_category and sb1.Sub_category_id = pi.sub_root_category)  sub_root_category,(select sb2.sub_category_desc from sub_category2 sb2 where sb2.master_category_id = pi.root_category and sb2.sub_category_id1 = pi.sub_root_category and sb2.sub_category_id2 = pi.category_id)  category_id,pi.product_name,pi.product_brand_name,pi.about_product,pi.ingredient,pi.image_path,pd.product_price,pd.offer_price,pd.product_info from product_info pi, product_price_detail pd";
	String downloadProd = "select (select mc.master_category_name from master_category mc where mc.master_category_id = pi.root_category) root_category,(select sb1.sub_category_name from sub_category1 sb1 where sb1.master_category_id = pi.root_category and sb1.Sub_category_id = pi.sub_root_category)  sub_root_category,(select sb2.sub_category_desc from sub_category2 sb2 where sb2.master_category_id = pi.root_category and sb2.sub_category_id1 = pi.sub_root_category and sb2.sub_category_id2 = pi.category_id)  category_id,pi.product_name,pi.product_brand_name,pi.about_product,pi.ingredient,pi.image_path,pd.product_price,pd.offer_price,pd.product_info from product_info pi, product_price_detail pd where pi.product_id = pd.product_id";
	public ArrayList downloadProduct(Connection connection);
	
	public XSSFWorkbook downloadProductForUpdate(Connection connection, Map parameters);
	
}
