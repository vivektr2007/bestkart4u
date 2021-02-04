package com.retailshop.action.admin;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.retailshop.service.IMassUploadService;
import com.retailshop.service.impl.MassUploadServiceImpl;
import com.retailshop.util.DBConnection;
import com.retailshop.util.DBUtil;

public class DownloadAllProductAction implements ServletResponseAware{

	private static final Logger logger = Logger.getLogger(DownloadAllProductAction.class.getName());
	
	private HttpServletResponse response;
	
	public String getDownloadProductForm(){
		
		return "success";
	}
	
	public String execute() throws IOException{
		
		//XSSFWorkbook workbook = null;
        Connection connection = null;
		try{
			
			connection = DBConnection.getConnection();
			
			
			XSSFWorkbook workbookCreate = new XSSFWorkbook(); 
	        XSSFSheet sheetCreate = workbookCreate.createSheet("Product Data");
	        
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
	        
	        rowHeader.createCell(7).setCellValue("OFFER_PRICE");
	        rowHeader.getCell(7).setCellStyle(getHeaderCellStyle(workbookCreate));
	        
	        rowHeader.createCell(8).setCellValue("SIZE");
	        rowHeader.getCell(8).setCellStyle(getHeaderCellStyle(workbookCreate));
	        
	        rowHeader.createCell(9).setCellValue("ABOUT_PRODUCT");
	        rowHeader.getCell(9).setCellStyle(getHeaderCellStyle(workbookCreate));
	        
	        rowHeader.createCell(10).setCellValue("INGREDIENT");
	        rowHeader.getCell(10).setCellStyle(getHeaderCellStyle(workbookCreate));
	        
	        rowHeader.createCell(11).setCellValue("IMAGE_NAME");
	        rowHeader.getCell(11).setCellStyle(getHeaderCellStyle(workbookCreate));
	        
	        rowHeader.createCell(12).setCellValue("ACTION");
	        rowHeader.getCell(12).setCellStyle(getHeaderCellStyle(workbookCreate));
			
	        IMassUploadService objMassUploadService = new MassUploadServiceImpl();
	        ArrayList allProducts = objMassUploadService.downloadProduct(connection);
	        logger.error("size------------"+allProducts.size());
	        Iterator itr = allProducts.iterator();
	        int i = 0;
	        while(itr.hasNext()){
					
	        	HashMap data = (HashMap)itr.next();
	        	
				XSSFRow row = sheetCreate.createRow(i);
				row.createCell(0).setCellValue(i);
				row.createCell(1).setCellValue((String)data.get("root_category"));
				row.createCell(2).setCellValue((String)data.get("sub_root_category"));
				row.createCell(3).setCellValue((String)data.get("category_id"));
				row.createCell(4).setCellValue((String)data.get("product_name"));
				row.createCell(5).setCellValue((String)data.get("product_brand_name"));
				row.createCell(6).setCellValue((String)data.get("product_price"));
				row.createCell(7).setCellValue((String)data.get("offer_price"));
				row.createCell(8).setCellValue((String)data.get("product_info"));
				row.createCell(9).setCellValue((String)data.get("about_product"));
				row.createCell(10).setCellValue((String)data.get("ingredient"));
				row.createCell(11).setCellValue((String)data.get("image_path"));
				row.createCell(12).setCellValue("A");
				
				i++;
			}
			
			sheetCreate.autoSizeColumn(1);
			sheetCreate.autoSizeColumn(2);
			sheetCreate.autoSizeColumn(3);
			sheetCreate.autoSizeColumn(4);
			sheetCreate.autoSizeColumn(5);
			sheetCreate.autoSizeColumn(9);
			sheetCreate.autoSizeColumn(10);
			sheetCreate.autoSizeColumn(11);
			
			sheetCreate.createFreezePane(1, 1, 1, 1);
		    
	        
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        workbookCreate.write(baos); //XSSFWorkbook here
			ByteArrayInputStream bias = new ByteArrayInputStream(baos.toByteArray());
			
			response.setContentType("application/vnd.ms-excel");
			
			response.setHeader("Content-disposition", "attachment;filename=all_product_result_file.xls");
			
			ServletOutputStream out = response.getOutputStream();
			
	        byte b[] = new byte[bias.available()];
	        bias.read(b);
	        out.write(b);
			
			out.close();
	        
		}
		catch (Exception e) {
			logger.error(e);
		}
        finally{
        	DBUtil.closeConnection(connection);
        }
		
		return "none";
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

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
}
