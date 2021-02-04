package com.retailshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.jdt.internal.compiler.ast.Argument;

import sun.security.action.GetLongAction;

import com.retailshop.dao.IMassUploadDao;
import com.retailshop.util.DBUtil;

public class MassUploadDaoImpl implements IMassUploadDao {

	private static final Logger logger = Logger.getLogger(MassUploadDaoImpl.class.getName());
	
	@Override
	public String uploadProduct(Connection connection, String userName,
			Map<String, String> parameters) {
		
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		String result = "fail";
		String msg = "";
		try{
			
			String action = (String)parameters.get("action");
	        if(action != null && !action.equals("") && action.equalsIgnoreCase("A")){
	        
	        	String rotCategoryId = "";
				String subRotCategoryId = "";
				String childCategoryId = "";
				
				if(parameters.get("rootcategory") != null && !parameters.get("rootcategory").trim().toString().equals("")){
					String rootCategoryWhere = "upper(master_category_name) like '"+parameters.get("rootcategory").toString().toUpperCase().trim()+"'";
					rotCategoryId = getSingleColumnValue(connection, "master_category_id", "master_category", rootCategoryWhere);
					
					if(rotCategoryId == null || rotCategoryId.equals("")){
						rotCategoryId = addCategory(connection, parameters.get("rootcategory").toString().toUpperCase().trim());
					}
					
				}
				if(parameters.get("subrootcategory") != null && !parameters.get("subrootcategory").trim().toString().equals("")){
					String subRootCategoryWhere = "master_category_id = '"+rotCategoryId+"' and upper(sub_category_name) like '"+parameters.get("subrootcategory").toString().toUpperCase().trim()+"'";
					subRotCategoryId = getSingleColumnValue(connection, "Sub_category_id", "sub_category1", subRootCategoryWhere);
					
					if(subRotCategoryId == null || subRotCategoryId.equals("")){
						subRotCategoryId = addSubCategory(connection,Long.parseLong(rotCategoryId), parameters.get("subrootcategory").toString().toUpperCase().trim());
					}
				}
				if(parameters.get("childcategory") != null && !parameters.get("childcategory").trim().toString().equals("")){
					String childCategoryWhere = "master_category_id = '"+rotCategoryId+"' and sub_category_id1 ='"+subRotCategoryId+"' and upper(sub_category_desc) like '"+parameters.get("childcategory").toString().toUpperCase().trim()+"'";
					childCategoryId = getSingleColumnValue(connection, "sub_category_id2", "sub_category2", childCategoryWhere);
					
					if(childCategoryId == null || childCategoryId.equals("")){
						childCategoryId = addChildCategory(connection,rotCategoryId,subRotCategoryId, parameters.get("childcategory").toString().toUpperCase().trim());
					}
				}
	        	
	        	Map arguments = new HashMap();
	        	arguments.put("productName",((String)parameters.get("productname")).toUpperCase().trim());
				arguments.put("productSize",(String)parameters.get("size"));
				arguments.put("productBrandName",((String)parameters.get("productbrandname")).toUpperCase().trim());
	        	boolean flag = checkDuplicateProduct(connection, arguments);
				if(flag){
					connection.rollback();
		        	msg = ((String)parameters.get("productname")).toUpperCase().trim() + " - " + ((String)parameters.get("productbrandname")).toUpperCase().trim() +" of size "+ (String)parameters.get("size")+" already exists.";
				}
				else{
					boolean flagVariant = checkForVariant(connection, arguments);
					if(flagVariant){
						int prodId = 0;
						PreparedStatement stmtVar = null;
						PreparedStatement pstmt2 = null;
						ResultSet rsVar = null;
						try{
							stmtVar = connection.prepareStatement(CheckDuplicateProductForVariantQuery);
							stmtVar.setString(1, arguments.get("productName").toString().toUpperCase());
							stmtVar.setString(2, arguments.get("productBrandName").toString().toUpperCase());
							
							rsVar = stmtVar.executeQuery();
							if(rsVar.next()){
								prodId = rsVar.getInt("product_id");
								rotCategoryId = rsVar.getString("root_category");
								subRotCategoryId = rsVar.getString("sub_root_category");
								childCategoryId = rsVar.getString("category_id");
							}
							String whereClauseVariant = "product_id = '" +prodId+"'" ;
							if(rotCategoryId != null && !rotCategoryId.equalsIgnoreCase("null") && !rotCategoryId.equals("")){
								whereClauseVariant = whereClauseVariant + " and root_category = '"+rotCategoryId+"'";
							}
							if(subRotCategoryId != null && !subRotCategoryId.equalsIgnoreCase("null") && !subRotCategoryId.equals("")){
								whereClauseVariant = whereClauseVariant + " and sub_root_category = '"+subRotCategoryId+"'";
							}
							if(childCategoryId != null && !childCategoryId.equalsIgnoreCase("null") && !childCategoryId.equals("")){
								whereClauseVariant = whereClauseVariant + " and child_category = '"+childCategoryId+"'";
							}
							if((String)parameters.get("actualprice") != null && !((String)parameters.get("actualprice")).equalsIgnoreCase("null") && !((String)parameters.get("actualprice")).equals("")){
								whereClauseVariant = whereClauseVariant + " and product_price = '"+(String)parameters.get("actualprice")+"'";
							}
							if((String)parameters.get("offerprice") != null && !((String)parameters.get("offerprice")).equalsIgnoreCase("null") && !((String)parameters.get("offerprice")).equals("")){
								whereClauseVariant = whereClauseVariant + " and offer_price = '"+(String)parameters.get("offerprice")+"'";
							}
							if(((String)parameters.get("size")).toUpperCase() != null && !(((String)parameters.get("size")).toUpperCase()).equalsIgnoreCase("null") && !(((String)parameters.get("size")).toUpperCase()).equals("")){
								whereClauseVariant = whereClauseVariant + " and upper(product_info) = '"+((String)parameters.get("size")).toUpperCase()+"'";
							}
							boolean variantFlag = DBUtil.isExists(connection, "product_price_detail", whereClauseVariant);
							
							if(!variantFlag){
								pstmt2 = connection.prepareStatement("insert into product_price_detail(product_id, product_price, Product_info, root_category, sub_root_category, child_category, created_by, created_on,offer_price) values(?,?,?,?,?,?,?, now(),?)");
						        pstmt2.setString(1, ""+prodId);
						        pstmt2.setString(2, (String)parameters.get("actualprice"));
						        pstmt2.setString(3, ((String)parameters.get("size")).toUpperCase());
						        pstmt2.setString(4, rotCategoryId);
						        pstmt2.setString(5, subRotCategoryId);
						        pstmt2.setString(6, childCategoryId);
						        pstmt2.setString(7, userName);
						        pstmt2.setString(8, (String)parameters.get("offerprice"));
						
						        int j = pstmt2.executeUpdate();
								if(j > 0){
									result = "success";
									msg = "Product variants added Successfully.";
								}
								else{
									msg = "Product variants has not been added.";
								}
							}
							else{
								msg = "Product variants already exists.";
							}
						}
						catch(SQLException e){
							logger.error("sql Exception in deleteCategory", e);
						}
						catch(Exception e){
							logger.error("unexpected Exception in deleteCategory", e);
						}
						finally{
							DBUtil.closeResultSet(rsVar);
							DBUtil.closePreparedStatement(stmtVar);
							DBUtil.closePreparedStatement(pstmt2);
						}
					}
					else{
			        	long productId = 0;
			        	pstmt1 = connection.prepareStatement("select max(product_id) from product_info");
					    ResultSet rs = pstmt1.executeQuery();
				        if(rs.next())
				        {
				            productId = rs.getLong(1) + 1L;
				        }
				        rs.close();
			    		pstmt = connection.prepareStatement("insert into product_info(root_category,sub_root_category,category_id,product_id,product_name," +
				        		"product_brand_name,actual_price,offer_price,image_path,about_product,ingredient,product_size, created_by, created_on) values(?,?,?,?,?,?,?,?,?,?,?,?,?,now())");
				        pstmt.setString(1, rotCategoryId);
				        pstmt.setString(2, subRotCategoryId);
				        pstmt.setString(3, childCategoryId);
				        pstmt.setLong(4, productId);
				        pstmt.setString(5, ((String)parameters.get("productname")).toUpperCase().trim());
				        pstmt.setString(6, ((String)parameters.get("productbrandname")).toUpperCase().trim());
				        pstmt.setString(7, (String)parameters.get("actualprice"));
				        pstmt.setString(8, (String)parameters.get("offerprice"));
				        pstmt.setString(9, "productimages/"+((String)parameters.get("imagepath")).replaceFirst("productimages/", " ").trim());
				        pstmt.setString(10, (String)parameters.get("aboutproduct").trim());
				        pstmt.setString(11, (String)parameters.get("ingredient").trim());
				        pstmt.setString(12, (String)parameters.get("size"));
				        pstmt.setString(13, userName);
				        
				        int i = pstmt.executeUpdate();
				        PreparedStatement pstmt2 = connection.prepareStatement("insert into product_price_detail(product_id, product_price, Product_info, root_category, sub_root_category, child_category, created_by, created_on,offer_price) values(?,?,?,?,?,?,?, now(),?)");
				        pstmt2.setString(1, ""+productId);
				        pstmt2.setString(2, (String)parameters.get("actualprice"));
				        pstmt2.setString(3, ((String)parameters.get("size")).toUpperCase());
				        pstmt2.setString(4, rotCategoryId);
				        pstmt2.setString(5, subRotCategoryId);
				        pstmt2.setString(6, childCategoryId);
				        pstmt2.setString(7, userName);
				        pstmt2.setString(8, (String)parameters.get("offerprice"));
				
				        int j = pstmt2.executeUpdate();
						if(i > 0 && j > 0){
				        	result = "success";
				        	connection.commit();
				        	msg = "Product added Successfully.";
				        }
				        else{
				        	connection.rollback();
				        	msg = "Product has not been added.";
				        }
					}
				}
		        
	        }
	        else if(action != null && !action.equals("") && action.equalsIgnoreCase("U")){
	        	
	        	String rotCategoryId = "";
				String subRotCategoryId = "";
				String childCategoryId = "";
				
	        	
	        	Map arguments = new HashMap();
	        	arguments.put("productName",((String)parameters.get("productname")).toUpperCase().trim());
				arguments.put("productSize",(String)parameters.get("size"));
				arguments.put("productBrandName",((String)parameters.get("productbrandname")).toUpperCase().trim());
	        	boolean flag = checkDuplicateProductForUpdate(connection, arguments);
	        	
				if(flag){
					int prodId = 0;
					PreparedStatement stmtVar = null;
					PreparedStatement pstmt2 = null;
					ResultSet rsVar = null;
					try{
						stmtVar = connection.prepareStatement(CheckDuplicateProductForVariantQuery);
						stmtVar.setString(1, arguments.get("productName").toString().toUpperCase());
						stmtVar.setString(2, arguments.get("productBrandName").toString().toUpperCase());
						
						rsVar = stmtVar.executeQuery();
						if(rsVar.next()){
							prodId = rsVar.getInt("product_id");
							rotCategoryId = rsVar.getString("root_category");
							subRotCategoryId = rsVar.getString("sub_root_category");
							childCategoryId = rsVar.getString("category_id");
						}
						
						String whereClauseVariant = " product_id = '" +prodId+"'" ;
						if(rotCategoryId != null && !rotCategoryId.equalsIgnoreCase("null") && !rotCategoryId.equals("")){
							whereClauseVariant = whereClauseVariant + " and root_category = '"+rotCategoryId+"'";
						}
						if(subRotCategoryId != null && !subRotCategoryId.equalsIgnoreCase("null") && !subRotCategoryId.equals("")){
							whereClauseVariant = whereClauseVariant + " and sub_root_category = '"+subRotCategoryId+"'";
						}
						if(childCategoryId != null && !childCategoryId.equalsIgnoreCase("null") && !childCategoryId.equals("")){
							whereClauseVariant = whereClauseVariant + " and child_category = '"+childCategoryId+"'";
						}
						if((String)parameters.get("actualprice") != null && !((String)parameters.get("actualprice")).equalsIgnoreCase("null") && !((String)parameters.get("actualprice")).equals("")){
							whereClauseVariant = whereClauseVariant + " and product_price = '"+(String)parameters.get("actualprice")+"'";
						}
						if((String)parameters.get("offerprice") != null && !((String)parameters.get("offerprice")).equalsIgnoreCase("null") && !((String)parameters.get("offerprice")).equals("")){
							whereClauseVariant = whereClauseVariant + " and offer_price = '"+(String)parameters.get("offerprice")+"'";
						}
						if(((String)parameters.get("size")).toUpperCase() != null && !(((String)parameters.get("size")).toUpperCase()).equalsIgnoreCase("null") && !(((String)parameters.get("size")).toUpperCase()).equals("")){
							whereClauseVariant = whereClauseVariant + " and upper(product_info) = '"+((String)parameters.get("size")).toUpperCase()+"'";
						}
						boolean variantFlag = DBUtil.isExists(connection, "product_price_detail", whereClauseVariant);
						
						if(variantFlag){
							
							String query = "update product_price_detail set product_price = ?, offer_price = ? where product_id = ? and product_price = ? and offer_price = ? and " +
									" upper(product_info) = ?";
							
							if(rotCategoryId != null && !rotCategoryId.equalsIgnoreCase("null") && !rotCategoryId.equals("")){
								query = query + " and root_category = '"+rotCategoryId+"'";
							}
							if(subRotCategoryId != null && !subRotCategoryId.equalsIgnoreCase("null") && !subRotCategoryId.equals("")){
								query = query + " and sub_root_category = '"+subRotCategoryId+"'";
							}
							if(childCategoryId != null && !childCategoryId.equalsIgnoreCase("null") && !childCategoryId.equals("")){
								query = query + " and child_category = '"+childCategoryId+"'";
							}
							
							pstmt2 = connection.prepareStatement(query);
							pstmt2.setString(1, (String)parameters.get("newactualprice"));
							pstmt2.setString(2, (String)parameters.get("newofferprice"));
							pstmt2.setString(3, ""+prodId);
					        pstmt2.setString(4, (String)parameters.get("actualprice"));
					        pstmt2.setString(5, ((String)parameters.get("offerprice")));
					        pstmt2.setString(6, ((String)parameters.get("size").toUpperCase()));
					
					        int j = pstmt2.executeUpdate();
					  
					        String query1 = "update product_info set actual_price = ?, offer_price = ? where product_id = ? and actual_price = ? and offer_price = ? and " +
									" upper(product_size) = ?";
							
							if(rotCategoryId != null && !rotCategoryId.equalsIgnoreCase("null") && !rotCategoryId.equals("")){
								query1 = query1 + " and root_category = '"+rotCategoryId+"'";
							}
							if(subRotCategoryId != null && !subRotCategoryId.equalsIgnoreCase("null") && !subRotCategoryId.equals("")){
								query1 = query1 + " and sub_root_category = '"+subRotCategoryId+"'";
							}
							if(childCategoryId != null && !childCategoryId.equalsIgnoreCase("null") && !childCategoryId.equals("")){
								query1 = query1 + " and category_id = '"+childCategoryId+"'";
							}
							
							PreparedStatement pstmt3 = connection.prepareStatement(query1);
							pstmt3.setString(1, (String)parameters.get("newactualprice"));
							pstmt3.setString(2, (String)parameters.get("newofferprice"));
							pstmt3.setString(3, ""+prodId);
					        pstmt3.setString(4, (String)parameters.get("actualprice"));
					        pstmt3.setString(5, ((String)parameters.get("offerprice")));
					        pstmt3.setString(6, ((String)parameters.get("size").toUpperCase()));
					        int k = pstmt3.executeUpdate();
					        pstmt3.close();
					        
							connection.commit();
							result = "success";
							msg = "Product variants Updated Successfully.";
						}
						else{
							msg = "Product variants does not exists.";
						}
						
					}
					catch (Exception e) {
						connection.rollback();
						logger.error("",e);
					}
				}
				else{
					msg = "Product does not exists";
				}
	        	
	        }
	        else if(action != null && !action.equals("") && action.equalsIgnoreCase("D")){
	        		
	        	String rotCategoryId = "";
				String subRotCategoryId = "";
				String childCategoryId = "";
				
	        	Map arguments = new HashMap();
	        	arguments.put("productName",((String)parameters.get("productname")).toUpperCase().trim());
				arguments.put("productSize",(String)parameters.get("size"));
				arguments.put("productBrandName",((String)parameters.get("productbrandname")).toUpperCase().trim());
	        	boolean flag = checkDuplicateProduct(connection, arguments);
				if(flag){
					int prodId = 0;
					PreparedStatement stmtVar = null;
					PreparedStatement pstmt2 = null;
					ResultSet rsVar = null;
					try{
						stmtVar = connection.prepareStatement(CheckDuplicateProductForVariantQuery);
						stmtVar.setString(1, arguments.get("productName").toString().toUpperCase());
						stmtVar.setString(2, arguments.get("productBrandName").toString().toUpperCase());
						
						rsVar = stmtVar.executeQuery();
						if(rsVar.next()){
							prodId = rsVar.getInt("product_id");
							rotCategoryId = rsVar.getString("root_category");
							subRotCategoryId = rsVar.getString("sub_root_category");
							childCategoryId = rsVar.getString("category_id");
						}
						
						String whereClauseVariant = " product_id = '" +prodId+"'" ;
						if(rotCategoryId != null && !rotCategoryId.equalsIgnoreCase("null") && !rotCategoryId.equals("")){
							whereClauseVariant = whereClauseVariant + " and root_category = '"+rotCategoryId+"'";
						}
						if(subRotCategoryId != null && !subRotCategoryId.equalsIgnoreCase("null") && !subRotCategoryId.equals("")){
							whereClauseVariant = whereClauseVariant + " and sub_root_category = '"+subRotCategoryId+"'";
						}
						if(childCategoryId != null && !childCategoryId.equalsIgnoreCase("null") && !childCategoryId.equals("")){
							whereClauseVariant = whereClauseVariant + " and child_category = '"+childCategoryId+"'";
						}
						if((String)parameters.get("actualprice") != null && !((String)parameters.get("actualprice")).equalsIgnoreCase("null") && !((String)parameters.get("actualprice")).equals("")){
							whereClauseVariant = whereClauseVariant + " and product_price = '"+(String)parameters.get("actualprice")+"'";
						}
						if((String)parameters.get("offerprice") != null && !((String)parameters.get("offerprice")).equalsIgnoreCase("null") && !((String)parameters.get("offerprice")).equals("")){
							whereClauseVariant = whereClauseVariant + " and offer_price = '"+(String)parameters.get("offerprice")+"'";
						}
						if(((String)parameters.get("size")).toUpperCase() != null && !(((String)parameters.get("size")).toUpperCase()).equalsIgnoreCase("null") && !(((String)parameters.get("size")).toUpperCase()).equals("")){
							whereClauseVariant = whereClauseVariant + " and upper(product_info) = '"+((String)parameters.get("size")).toUpperCase()+"'";
						}
						
						String query1 = "delete from product_info where product_id = ? and actual_price = ? and offer_price = ? and " +
								" upper(product_size) = ?";
						
						if(rotCategoryId != null && !rotCategoryId.equalsIgnoreCase("null") && !rotCategoryId.equals("")){
							query1 = query1 + " and root_category = '"+rotCategoryId+"'";
						}
						if(subRotCategoryId != null && !subRotCategoryId.equalsIgnoreCase("null") && !subRotCategoryId.equals("")){
							query1 = query1 + " and sub_root_category = '"+subRotCategoryId+"'";
						}
						if(childCategoryId != null && !childCategoryId.equalsIgnoreCase("null") && !childCategoryId.equals("")){
							query1 = query1 + " and category_id = '"+childCategoryId+"'";
						}
						
						PreparedStatement pstmt3 = connection.prepareStatement(query1);
						pstmt3.setString(1, ""+prodId);
				        pstmt3.setString(2, (String)parameters.get("actualprice"));
				        pstmt3.setString(3, ((String)parameters.get("offerprice")));
				        pstmt3.setString(4, ((String)parameters.get("size").toUpperCase()));
				
				        msg = "";
				        
				        int k = pstmt3.executeUpdate();
						if(k>0){
							msg = "Product has been deleted Successfully";
						}
						else{
							msg = "Product does not exists";
						}
				        
						boolean variantFlag = DBUtil.isExists(connection, "product_price_detail", whereClauseVariant);
						if(variantFlag){
							
							String query = "delete from product_price_detail where product_id = ? and product_price = ? and offer_price = ? and " +
									" upper(product_info) = ?";
							
							if(rotCategoryId != null && !rotCategoryId.equalsIgnoreCase("null") && !rotCategoryId.equals("")){
								query = query + " and root_category = '"+rotCategoryId+"'";
							}
							if(subRotCategoryId != null && !subRotCategoryId.equalsIgnoreCase("null") && !subRotCategoryId.equals("")){
								query = query + " and sub_root_category = '"+subRotCategoryId+"'";
							}
							if(childCategoryId != null && !childCategoryId.equalsIgnoreCase("null") && !childCategoryId.equals("")){
								query = query + " and child_category = '"+childCategoryId+"'";
							}
							
							pstmt2 = connection.prepareStatement(query);
							pstmt2.setString(1, ""+prodId);
					        pstmt2.setString(2, (String)parameters.get("actualprice"));
					        pstmt2.setString(3, ((String)parameters.get("offerprice")));
					        pstmt2.setString(4, ((String)parameters.get("size").toUpperCase()));
					
					        int j = pstmt2.executeUpdate();
					        
					        if(j>0){
					        	msg = msg + "\nProduct Variant has been deleted successfully.";
					        }
					        else{
					        	msg = msg + "\n Product variant does not exists";
					        }
					        
					  	}
						else{
							msg = msg + "Product variants does not exists.";
						}
						connection.commit();
						result = "success";
					}
					catch (Exception e) {
						connection.rollback();
						logger.error("",e);
					}
				}
				else{
					msg = "Product does not exists";
				}
	        	
	        }
	        else if(action != null && !action.equals("") && action.equalsIgnoreCase("S")){
	        	msg = "Operation has been skipped for this record.";
	        }
	        else{
	        	msg = "Action is not Valid.";
	        }
		}
		catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				logger.error("",e1);
			}
			msg = "There is some error while adding product."+e.getMessage();
			logger.error("",e);
		}
		finally{
			DBUtil.closePreparedStatement(pstmt1);
			DBUtil.closePreparedStatement(pstmt);	
		}
		return result + " == "+msg;
		
	}

	public String getSingleColumnValue(Connection connection, String targetColumnName, String TableName, String whereClause){
		
		Statement pstmt = null;
		String query = "select "+ targetColumnName +" from " + TableName + " where " + whereClause;
		
		String value = "";
		
		try{
			pstmt = connection.createStatement();
			ResultSet rs = pstmt.executeQuery(query);
			while(rs.next()){
				value = rs.getString(targetColumnName);
			}
		}
		catch (Exception e) {
			logger.error(e);
		}
		finally{
			DBUtil.closeStatement(pstmt);
		}
		
		return value;
		
	}
	
	public String addCategory(Connection connection, String categoryName)
    {
        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
        long categoryId = 1L;
        try
        {
            pstmt1 = connection.prepareStatement("select max(master_category_id) from master_category");
            ResultSet rs = pstmt1.executeQuery();
            if(rs.next())
            {
                categoryId = rs.getLong(1) + 1L;
            }
            rs.close();
            pstmt = connection.prepareStatement("insert into master_category(master_category_id,master_category_name) values(?,?)");
            pstmt.setLong(1, categoryId);
            pstmt.setString(2, categoryName.toUpperCase());
            int i = pstmt.executeUpdate();
            if(i > 0)
            {
                //flag = true;
            }
        
        }
        catch(SQLException e)
        {
            logger.error("error in inserting category into database", e);
        }
        finally{
	        DBUtil.closePreparedStatement(pstmt1);
	        DBUtil.closePreparedStatement(pstmt);
        }
        return "" + categoryId;
    }
	
	public String addSubCategory(Connection connection, long categiryId, String subCategoryName)
    {
        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
        boolean flag = false;
        long subCategoryId = 1L;
        
        try
        {
            pstmt1 = connection.prepareStatement("select max(sub_category_id) from sub_category1 where master_category_id = ?");
            pstmt1.setLong(1, categiryId);
            ResultSet rs = pstmt1.executeQuery();
            if(rs.next())
            {
                subCategoryId = rs.getLong(1) + 1L;
            }
            rs.close();
            pstmt = connection.prepareStatement("insert into sub_category1(sub_category_id,sub_category_name, master_category_id) values(?,?,?)");
            pstmt.setLong(1, subCategoryId);
            pstmt.setString(2, subCategoryName);
            pstmt.setLong(3, categiryId);
            int i = pstmt.executeUpdate();
            if(i > 0)
            {
                flag = true;
            }
        }
        catch(SQLException e)
        {
            logger.error("error in inserting category into database", e);
        }
        finally{
	        DBUtil.closePreparedStatement(pstmt1);
	        DBUtil.closePreparedStatement(pstmt);
        }
    
        return "" + subCategoryId;
    }
	
	public String addChildCategory(Connection connection, String masterCategoryId, String subCategoryId, String childCategoryName)
    {
        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
        boolean flag = false;
        long childCategoryId=1L;
        try{
	        pstmt1 = connection.prepareStatement("select max(sub_category_id2) from sub_category2 where master_category_id = ? and sub_category_id1=?");
	        pstmt1.setString(1, masterCategoryId);
	        pstmt1.setString(2, subCategoryId);
	        ResultSet rs = pstmt1.executeQuery();
	        if(rs.next())
	        {
	            childCategoryId = rs.getLong(1) + 1L;
	        }
	        rs.close();
	        pstmt = connection.prepareStatement("insert into sub_category2(master_category_id,sub_category_id1,sub_category_id2,sub_category_desc) values(?,?,?,?)");
	        pstmt.setInt(1, Integer.parseInt(masterCategoryId));
	        pstmt.setInt(2, Integer.parseInt(subCategoryId));
	        pstmt.setLong(3, childCategoryId);
	        pstmt.setString(4, childCategoryName);
	        int i = pstmt.executeUpdate();
	        if(i > 0)
	        {
	            flag = true;
	        }
        }
        catch(Exception e){
        	logger.error("error in inserting category into database", e);
        }
        finally{
	        DBUtil.closePreparedStatement(pstmt1);
	        DBUtil.closePreparedStatement(pstmt);
        }
        
        return ""+childCategoryId;
    }
	
	public boolean checkForVariant(Connection connection, Map arguments){
		boolean flag = false;
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			stmt = connection.prepareStatement(CheckDuplicateProductForVariantQuery);
			stmt.setString(1, arguments.get("productName").toString().toUpperCase());
			stmt.setString(2, arguments.get("productBrandName").toString().toUpperCase());
			
			rs = stmt.executeQuery();
			if(rs.next()){
				flag = true;
			}
		}
		catch(SQLException e){
			logger.error("sql Exception in deleteCategory", e);
		}
		catch(Exception e){
			logger.error("unexpected Exception in deleteCategory", e);
		}
		finally{
			DBUtil.closePreparedStatement(stmt);
		}
		return flag;
	}
	
	public boolean addVariant(Connection connection, Map arguments){
		boolean flag = false;
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			stmt = connection.prepareStatement(CheckDuplicateProductQuery);
			stmt.setString(1, arguments.get("productName").toString().toUpperCase());
			stmt.setString(2, arguments.get("productSize").toString().toUpperCase());
			stmt.setString(3, arguments.get("productBrandName").toString().toUpperCase());
			
			rs = stmt.executeQuery();
			if(rs.next()){
				flag = true;
			}
		}
		catch(SQLException e){
			logger.error("sql Exception in deleteCategory", e);
		}
		catch(Exception e){
			logger.error("unexpected Exception in deleteCategory", e);
		}
		finally{
			DBUtil.closePreparedStatement(stmt);
		}
		return flag;
	}
	
	public boolean checkDuplicateProduct(Connection connection, Map arguments){
		boolean flag = false;
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			stmt = connection.prepareStatement(CheckDuplicateProductQuery);
			System.out.println(CheckDuplicateProductQuery);
			System.out.println(arguments.get("productName"));
			System.out.println(arguments.get("productSize"));
			System.out.println(arguments.get("productBrandName"));
			stmt.setString(1, arguments.get("productName").toString().toUpperCase());
			stmt.setString(2, arguments.get("productSize").toString().toUpperCase());
			stmt.setString(3, arguments.get("productBrandName").toString().toUpperCase());
			
			rs = stmt.executeQuery();
			if(rs.next()){
				flag = true;
			}
		}
		catch(SQLException e){
			logger.error("sql Exception in deleteCategory", e);
		}
		catch(Exception e){
			logger.error("unexpected Exception in deleteCategory", e);
		}
		finally{
			DBUtil.closePreparedStatement(stmt);
		}
		
		return flag;
	}

	public boolean checkDuplicateProductForUpdate(Connection connection, Map arguments){
		boolean flag = false;
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			stmt = connection.prepareStatement(CheckDuplicateProductForUpdate);
			
			stmt.setString(1, arguments.get("productName").toString().toUpperCase());
			stmt.setString(2, arguments.get("productBrandName").toString().toUpperCase());
			
			rs = stmt.executeQuery();
			if(rs.next()){
				flag = true;
			}
		}
		catch(SQLException e){
			logger.error("sql Exception in deleteCategory", e);
		}
		catch(Exception e){
			logger.error("unexpected Exception in deleteCategory", e);
		}
		finally{
			DBUtil.closePreparedStatement(stmt);
		}
		
		return flag;
	}
	
	
	@Override
	public XSSFWorkbook downloadProductForUpdate(Connection connection, Map parameters) {
		
		XSSFWorkbook workbookCreate = new XSSFWorkbook(); 
        XSSFSheet sheetCreate = workbookCreate.createSheet("Product Data");
        
        sheetCreate.protectSheet("password");
        
        Row rowHeader = sheetCreate.createRow(0);
		//rowCreate
        rowHeader.createCell(0).setCellValue("SR NO.");
        rowHeader.getCell(0).setCellStyle(getHeaderCellStyle(workbookCreate));
        
        rowHeader.createCell(1).setCellValue("ROOT_CATEGORY");
        rowHeader.getCell(1).setCellStyle(getHeaderCellStyle(workbookCreate));
        
        rowHeader.createCell(2).setCellValue("SUB_ROOT_CATEGORY");
        rowHeader.getCell(2).setCellStyle(getHeaderCellStyle(workbookCreate));
        
        rowHeader.createCell(3).setCellValue("CHILD_CATEGORY");
        rowHeader.getCell(3).setCellStyle(getHeaderCellStyle(workbookCreate));
        
        rowHeader.createCell(4).setCellValue("PRODUCT_NAME");
        rowHeader.getCell(4).setCellStyle(getHeaderCellStyle(workbookCreate));
        
        rowHeader.createCell(5).setCellValue("PRODUCT_BRAND_NAME");
        rowHeader.getCell(5).setCellStyle(getHeaderCellStyle(workbookCreate));
        
        rowHeader.createCell(6).setCellValue("ACTUAL_PRICE");
        rowHeader.getCell(6).setCellStyle(getHeaderCellStyle(workbookCreate));
        
        rowHeader.createCell(7).setCellValue("NEW_ACTUAL_PRICE");
        rowHeader.getCell(7).setCellStyle(getHeaderCellStyle(workbookCreate));
        
        rowHeader.createCell(8).setCellValue("OFFER_PRICE");
        rowHeader.getCell(8).setCellStyle(getHeaderCellStyle(workbookCreate));
        
        rowHeader.createCell(9).setCellValue("NEW_OFFER_PRICE");
        rowHeader.getCell(9).setCellStyle(getHeaderCellStyle(workbookCreate));
        
        rowHeader.createCell(10).setCellValue("SIZE");
        rowHeader.getCell(10).setCellStyle(getHeaderCellStyle(workbookCreate));
        
        rowHeader.createCell(11).setCellValue("ABOUT_PRODUCT");
        rowHeader.getCell(11).setCellStyle(getHeaderCellStyle(workbookCreate));
        
        rowHeader.createCell(12).setCellValue("INGREDIENT");
        rowHeader.getCell(12).setCellStyle(getHeaderCellStyle(workbookCreate));
        
        rowHeader.createCell(13).setCellValue("IMAGE_NAME");
        rowHeader.getCell(13).setCellStyle(getHeaderCellStyle(workbookCreate));
        
        rowHeader.createCell(14).setCellValue("ACTION");
        rowHeader.getCell(14).setCellStyle(getHeaderCellStyle(workbookCreate));
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i =1;
		try{
			String where = "";
			boolean flag = false;
			if(parameters.get("rootcategory") != null && !parameters.get("rootcategory").toString().trim().toString().equals("")){
				where = where + " and pi.root_category = '"+parameters.get("rootcategory").toString()+"'";
				flag = true;
			}
			if(parameters.get("subrootcategory") != null && !parameters.get("subrootcategory").toString().trim().equals("")){
				where = where + " and pi.sub_root_category = '"+parameters.get("subrootcategory").toString()+"'";
				flag = true;
			}
			if(parameters.get("childcategory") != null && !parameters.get("childcategory").toString().trim().equals("")){
				where = where + " and pi.category_id = '"+parameters.get("childcategory").toString()+"'";
				flag = true;
			}
			//String downloadProdForUpd = "select (select master_category_name from master_category mc where mc.master_category_id = pi.root_category) root_category,(select sub_category_name from sub_category1 sb1 where sb1.master_category_id = pi.root_category and sb1.Sub_category_id = pi.sub_root_category)  sub_root_category,(select sub_category_desc from sub_category2 sb2 where sb2.master_category_id = pi.root_category and sb2.sub_category_id1 = pi.sub_root_category and sb2.sub_category_id2 = pi.category_id)  category_id,product_name,product_brand_name,actual_price,offer_price,product_size,about_product,ingredient,image_path from product_info pi";
			
			String downloadProdForUpd = "select (select mc.master_category_name from master_category mc where mc.master_category_id = pi.root_category) root_category,(select sb1.sub_category_name from sub_category1 sb1 where sb1.master_category_id = pi.root_category and sb1.Sub_category_id = pi.sub_root_category)  sub_root_category,(select sb2.sub_category_desc from sub_category2 sb2 where sb2.master_category_id = pi.root_category and sb2.sub_category_id1 = pi.sub_root_category and sb2.sub_category_id2 = pi.category_id)  category_id,pi.product_name,pi.product_brand_name,pi.about_product,pi.ingredient,pi.image_path,pd.product_price,pd.offer_price,pd.product_info from product_info pi, product_price_detail pd where pi.product_id = pd.product_id";
			if(flag){
				//where = where.substring(4);
				downloadProdForUpd = downloadProdForUpd + " " + where; 
			}
			
			System.out.println("downloadProdForUpd : "+downloadProdForUpd);
			
			pstmt = connection.prepareStatement(downloadProdForUpd);
			rs = pstmt.executeQuery();
			while(rs.next()){
				
				XSSFRow row = sheetCreate.createRow(i);
				row.createCell(0).setCellValue(i);
				row.createCell(1).setCellValue(rs.getString("root_category"));
				row.createCell(2).setCellValue(rs.getString("sub_root_category"));
				row.createCell(3).setCellValue(rs.getString("category_id"));
				row.createCell(4).setCellValue(rs.getString("product_name"));
				row.createCell(5).setCellValue(rs.getString("product_brand_name"));
				row.createCell(6).setCellValue(Double.parseDouble(rs.getString("product_price")));
				XSSFCell newActPriceCell = row.createCell(7);
				newActPriceCell.setCellValue(Double.parseDouble(rs.getString("product_price")));
				newActPriceCell.setCellStyle(lockSheetStyle(workbookCreate));
				row.createCell(8).setCellValue(Double.parseDouble(rs.getString("offer_price")));
				XSSFCell offerPriceCell = row.createCell(9);
				offerPriceCell.setCellValue(Double.parseDouble(rs.getString("offer_price")));
				offerPriceCell.setCellStyle(lockSheetStyle(workbookCreate));
				row.createCell(10).setCellValue(rs.getString("product_info"));
				row.createCell(11).setCellValue(rs.getString("about_product"));
				row.createCell(12).setCellValue(rs.getString("ingredient"));
				row.createCell(13).setCellValue(rs.getString("image_path"));
				XSSFCell actionCell = row.createCell(14);
				actionCell.setCellValue("S");
				actionCell.setCellStyle(lockSheetStyle(workbookCreate));
				
				i++;
			}
		}
		catch (Exception e) {
			logger.error(e);
		}
		finally{
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(pstmt);
			
		}
		
		sheetCreate.autoSizeColumn(1);
		sheetCreate.autoSizeColumn(2);
		sheetCreate.autoSizeColumn(3);
		sheetCreate.autoSizeColumn(4);
		sheetCreate.autoSizeColumn(5);
		sheetCreate.autoSizeColumn(6);
		sheetCreate.autoSizeColumn(7);
		sheetCreate.autoSizeColumn(8);
		sheetCreate.autoSizeColumn(9);
		sheetCreate.autoSizeColumn(10);
		sheetCreate.autoSizeColumn(11);
		sheetCreate.autoSizeColumn(12);
		sheetCreate.autoSizeColumn(13);
		
		sheetCreate.createFreezePane(1, 1, 1, 1);
		
		return workbookCreate;
	}
	
	@Override
	public ArrayList downloadProduct(Connection connection) {
		
		ArrayList allData = new ArrayList();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			
			pstmt = connection.prepareStatement(downloadProd);
			rs = pstmt.executeQuery();
			while(rs.next()){
				
				Map data = new HashMap();
				data.put("root_category", rs.getString("root_category"));
				data.put("sub_root_category", rs.getString("sub_root_category"));
				data.put("category_id", rs.getString("category_id"));
				data.put("product_name", rs.getString("product_name"));
				data.put("product_brand_name", rs.getString("product_brand_name"));
				data.put("product_price", rs.getString("product_price"));

				data.put("offer_price", rs.getString("offer_price"));
				data.put("product_info", rs.getString("product_info"));
				data.put("about_product", rs.getString("about_product"));
				data.put("ingredient", rs.getString("ingredient"));
				data.put("image_path", rs.getString("image_path"));
				data.put("action", "A");
				
				allData.add(data);
			}
			
		}
		catch (Exception e) {
			logger.error(e);
		}
		finally{
			
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(pstmt);
			
		}
		
		
		return allData;
	}
	
	public CellStyle getHeaderCellStyle(XSSFWorkbook workbook){
		
		XSSFFont font = workbook.createFont();
		font.setColor(IndexedColors.WHITE.getIndex());
	    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	    
        XSSFCellStyle cellStyle= workbook.createCellStyle();
        cellStyle.setFont(font);
       
        cellStyle.setFillForegroundColor(IndexedColors.BLACK.getIndex());
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
         
        cellStyle.setWrapText(true);
        
        return cellStyle;
	}
	
	public static CellStyle lockSheetStyle(XSSFWorkbook wb){
		CellStyle unlockedCellStyle = wb.createCellStyle();
		unlockedCellStyle.setLocked(false);
		
		return unlockedCellStyle;
		
	}
	
}
