package com.retailshop.action.admin;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.retailshop.service.IMassUploadService;
import com.retailshop.service.impl.MassUploadServiceImpl;
import com.retailshop.util.DBConnection;
import com.retailshop.util.DBUtil;

public class MassUpload implements SessionAware, ServletResponseAware{

	private static final Logger logger = Logger.getLogger(MassUpload.class.getName());
	
	private Map session;
	private HttpServletResponse response;
	
	private File[] attachmentFile;
	private String[] attachmentFileName; 
	private String[] attachmentContentType;

	public File[] getAttachmentFile() {
		return attachmentFile;
	}

	public void setAttachmentFile(File[] attachmentFile) {
		this.attachmentFile = attachmentFile;
	}

	public String[] getAttachmentFileName() {
		return attachmentFileName;
	}

	public void setAttachmentFileName(String[] attachmentFileName) {
		this.attachmentFileName = attachmentFileName;
	}

	public String[] getAttachmentContentType() {
		return attachmentContentType;
	}

	public void setAttachmentContentType(String[] attachmentContentType) {
		this.attachmentContentType = attachmentContentType;
	}

	public String getUploadProductForm(){
		
		return "success";
	}
	
	public String downloadTemplate(){
		
		XSSFWorkbook workbookCreate = new XSSFWorkbook(); 
        XSSFSheet sheetCreate = workbookCreate.createSheet("Product Data");
        
        Row rowHeader = sheetCreate.createRow(0);
		//rowCreate
        
        /*XSSFFont font = workbookCreate.createFont();
		font.setColor(IndexedColors.WHITE.getIndex());
	    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	    
	    
        XSSFCellStyle cellStyle= workbookCreate.createCellStyle();
        cellStyle.setFont(font);
       
        cellStyle.setFillForegroundColor(IndexedColors.BLACK.getIndex());
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
         
        cellStyle.setWrapText(true);*/
        
        //return cellStyle;
        
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
        
		
		try{
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
			workbookCreate.write(baos); //XSSFWorkbook here
			ByteArrayInputStream bias = new ByteArrayInputStream(baos.toByteArray());
			
			response.setContentType("application/vnd.ms-excel");
			
			response.setHeader("Content-disposition", "attachment;filename=product_add_template.xlsx");
			
			ServletOutputStream out = response.getOutputStream();
			
	        byte b[] = new byte[bias.available()];
	        bias.read(b);
	        out.write(b);
			
			out.close();
		}
		catch(Exception e){
			logger.error(e);
		}
		
		return "none";
	}
	
	public String uploadProduct(){
		
		Connection connection = null;
		
		try{
			
			connection = DBConnection.getConnection();
			connection.setAutoCommit(false);
			
			//--------------to read excel files-------------
			
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
	        
	        rowHeader.setHeight((short)1000); 
			
			XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(attachmentFile[0]));
			XSSFSheet sheet = workbook.getSheetAt(0);
			int rows = sheet.getPhysicalNumberOfRows();
			
			long successCount = 0;
			long failCount = 0;
			CreationHelper createHelper = workbookCreate.getCreationHelper();
			for(int i = 1; i < rows; i++) { 
				
				XSSFRow row = sheet.getRow(i); 
				String mainCategory = cellToString(row.getCell(1));
				String subCategory = cellToString(row.getCell(2));
				String childCategory = cellToString(row.getCell(3));
				String productName = cellToString(row.getCell(4));
				String productBrandName = cellToString(row.getCell(5));
				String actualPrice = cellToString(row.getCell(6));
				
				boolean actionFlag = true;
				
				boolean actualPriceFlag = true;
				
				try{
					double d1 = Double.parseDouble(actualPrice);
				}
				catch (Exception e) {
					actualPriceFlag = false;
					actionFlag = false;
				}
				
				String offerPrice = cellToString(row.getCell(7));
				boolean offerPriceFlag = true;
				try{
					double d = Double.parseDouble(offerPrice);
				}
				catch (Exception e) {
					actionFlag = false;
					offerPriceFlag = false;
				}
				String size = cellToString(row.getCell(8));
				String aboutProduct = cellToString(row.getCell(9));
				String ingredient = cellToString(row.getCell(10));
				String imagePath = cellToString(row.getCell(11));
				String action = cellToString(row.getCell(12));
				
				Map parameters = new HashMap();
				parameters.put("rootcategory", mainCategory);
				parameters.put("subrootcategory", subCategory);
				parameters.put("childcategory", childCategory);
				parameters.put("productname", productName);
				parameters.put("productbrandname", productBrandName);
				parameters.put("actualprice", actualPrice);
				parameters.put("offerprice", offerPrice);
				parameters.put("size", size);
				parameters.put("aboutproduct", aboutProduct);
				parameters.put("ingredient", ingredient);
				parameters.put("imagepath", imagePath);
				parameters.put("action", action);
				
				String msg = "";
				String msgarr[];
				
				String result = "fail";
				//int failCount = 0;
				if(actionFlag){
					try{
						IMassUploadService objMassUploadService = new MassUploadServiceImpl();
						String s = objMassUploadService.uploadProduct(connection, session.get("loginId").toString(), parameters);
						msgarr = s.split("==");
						
						msg = msgarr[1].trim();
						result = msgarr[0].trim();
						
					}
					catch (Exception e) {
						msg = "Product has not been added due to some error.";
						logger.error(""+e);
					}
					
				}
				else{
					msg = "Product has not beet added.";
				}
				
				Row rowCreate = sheetCreate.createRow(i);

				CreationHelper factory = workbookCreate.getCreationHelper();
				Drawing drawing = sheetCreate.createDrawingPatriarch();
				
				CellStyle style = fillColor(workbookCreate, result);
				
				Cell cell0 = rowCreate.createCell(0);
				cell0.setCellValue(i);
				cell0.setCellStyle(style);
				
				Cell cell1 = rowCreate.createCell(1);
				cell1.setCellValue(createHelper.createRichTextString(mainCategory));
				cell1.setCellStyle(style);
				
				Cell cell2 = rowCreate.createCell(2);
				cell2.setCellValue(subCategory);
				cell2.setCellStyle(style);
				
				Cell cell3 = rowCreate.createCell(3);
				cell3.setCellValue(childCategory);
				cell3.setCellStyle(style);
				
				Cell cell4 = rowCreate.createCell(4);
				cell4.setCellValue(productName);
				cell4.setCellStyle(style);
				
				Cell cell5 = rowCreate.createCell(5);
				cell5.setCellValue(productBrandName);
				cell5.setCellStyle(style);
				
				Cell actualPriceCell = rowCreate.createCell(6);
				if(!actualPriceFlag){
					actualPriceCell.setCellValue(actualPrice);
					
					ClientAnchor anchorActPrice = factory.createClientAnchor();
					anchorActPrice.setCol1(actualPriceCell.getColumnIndex());
					anchorActPrice.setCol2(actualPriceCell.getColumnIndex()+3);
					anchorActPrice.setRow1(rowCreate.getRowNum());
					anchorActPrice.setRow2(rowCreate.getRowNum()+2);
					
					Comment commentActPrice = drawing.createCellComment(anchorActPrice);
				    RichTextString strActPrice = factory.createRichTextString("Actual Price is not Valid.");
				    commentActPrice.setString(strActPrice);
				    commentActPrice.setAuthor("BestKart4U.com");
				    actualPriceCell.setCellComment(commentActPrice);
				}
				else{
					actualPriceCell.setCellValue(Double.parseDouble(actualPrice));
				}
				actualPriceCell.setCellStyle(style);
				
				Cell offerPriceCell = rowCreate.createCell(7);
				if(!offerPriceFlag){
					offerPriceCell.setCellValue(offerPrice);
					
					ClientAnchor anchorOfferPrice = factory.createClientAnchor();
					anchorOfferPrice.setCol1(actualPriceCell.getColumnIndex());
					anchorOfferPrice.setCol2(actualPriceCell.getColumnIndex()+3);
					anchorOfferPrice.setRow1(rowCreate.getRowNum());
					anchorOfferPrice.setRow2(rowCreate.getRowNum()+2);
					
					Comment commentOfferPrice = drawing.createCellComment(anchorOfferPrice);
				    RichTextString strOfferPrice = factory.createRichTextString("Offer Price is not Valid.");
				    commentOfferPrice.setString(strOfferPrice);
				    commentOfferPrice.setAuthor("BestKart4U.com");
				    offerPriceCell.setCellComment(commentOfferPrice);
				}
				else{
					offerPriceCell.setCellValue(Double.parseDouble(offerPrice));
				}
				offerPriceCell.setCellStyle(style);
				
				Cell cell8 = rowCreate.createCell(8);
				cell8.setCellValue(size);
				cell8.setCellStyle(style);
				
				Cell cell9 = rowCreate.createCell(9);
				cell9.setCellValue(aboutProduct);
				cell9.setCellStyle(style);
				
				Cell cell10 = rowCreate.createCell(10);
				cell10.setCellValue(ingredient);
				cell10.setCellStyle(style);
				
				Cell cell11 = rowCreate.createCell(11);
				cell11.setCellValue(imagePath);
				cell11.setCellStyle(style);
				
				Cell actionCell = rowCreate.createCell(12);
				actionCell.setCellValue(action);
				actionCell.setCellStyle(style);
				
				//String validActionmsg = "";
				if(action == null || action.trim().equals("") || (!action.equalsIgnoreCase("A") && !action.equalsIgnoreCase("U") && !action.equalsIgnoreCase("D") && !action.equalsIgnoreCase("S"))){
					//validActionmsg = "Action is Not Valid.";
					msg = "1. Action is Not Valid.\n" + "2. "+msg;
				}
				else{
					msg = "1. "+msg;
				}
				
				ClientAnchor anchor = factory.createClientAnchor();
			    anchor.setCol1(actionCell.getColumnIndex());
			    anchor.setCol2(actionCell.getColumnIndex()+3);
			    anchor.setRow1(rowCreate.getRowNum());
			    anchor.setRow2(rowCreate.getRowNum()+2);
				
				Comment comment = drawing.createCellComment(anchor);
			    RichTextString str = factory.createRichTextString(msg);
			    comment.setString(str);
			    comment.setAuthor("BestKart4U.com");
			    actionCell.setCellComment(comment);
				
			    if(result.equalsIgnoreCase("success")){
					successCount = successCount + 1;
				}
			    else{
					failCount = failCount + 1;
				}
				
			    rowCreate.setHeight((short)800); 
			    
			}

/*			if(failCount > 0 ){
				connection.rollback();
			}
			else{
				connection.commit();
			}*/
			
			Row rowSummary = sheetCreate.createRow((int)(successCount + failCount + 2));
			//createHelper.
			rowSummary.createCell(11).setCellValue(createHelper.createRichTextString("Number of success Record : "));
			rowSummary.createCell(12).setCellValue(successCount);
			
			rowSummary.setHeight((short)1000); 
			
			Row rowSummary1 = sheetCreate.createRow((int)(successCount + failCount + 3));
			
			rowSummary1.createCell(11).setCellValue("Number of fail Record : ");
			rowSummary1.createCell(12).setCellValue(failCount);
			
			if(failCount > 0){
				Row rowSummary2 = sheetCreate.createRow((int)(successCount + failCount + 4));
				rowSummary2.createCell(11).setCellValue("Please correct values for red rows and try uploading again");
			}
			//rowSummary2.createCell(12).setCellValue(failCount);
			
			//rowSummary1.setHeight((short)1500); 
			
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
			
			response.setHeader("Content-disposition", "attachment;filename=product_add_result_file.xls");
			
			ServletOutputStream out = response.getOutputStream();
			
            byte b[] = new byte[bias.available()];
            bias.read(b);
            out.write(b);
			
			out.close();
		}
		catch (Exception e) {
			logger.error("",e);
		}
		finally{
			DBUtil.closeConnection(connection);
		}
		
		return "none";
	}


	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setSession(Map session) {
		this.session = session;
	}

	public static String cellToString(Cell cell) {  
	    int type;
	    Object result = "";
	    if(cell != null){
		    type = cell.getCellType();
		    try{
			    switch (type) {
		
			        case Cell.CELL_TYPE_NUMERIC: // numeric value in Excel
			        	result = cell.getNumericCellValue();
			            break;
			        case Cell.CELL_TYPE_FORMULA: // precomputed value based on formula
			            result = cell.getNumericCellValue();
			            break;
			        case Cell.CELL_TYPE_STRING: // String Value in Excel 
			            result = cell.getStringCellValue();
			            break;
			        case Cell.CELL_TYPE_BLANK:
			            result = "";
			        case Cell.CELL_TYPE_BOOLEAN: //boolean value 
			            //result = cell.getBooleanCellValue();
			        	result = "";
			            break;
			        case Cell.CELL_TYPE_ERROR:
			        	result = "";
			        default: 
			        	result = "";
			            //throw new RuntimeException("There is no support for this type of cell");                        
			    }
		    }
		    catch (Exception e) {
				result = "0.0";
			}
	    }
	    return result.toString();
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
	public static CellStyle fillColor(XSSFWorkbook wb, String status){
		
		CellStyle style = wb.createCellStyle();
		if(status != null && status.equalsIgnoreCase("success")){
			//style.setFillBackgroundColor(IndexedColors.LIGHT_GREEN.getIndex());
		}
		else if(status != null && status.equalsIgnoreCase("fail")){
			style.setFillBackgroundColor(IndexedColors.PINK.getIndex());
		}
		else{
			style.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());
		}
		style.setFillPattern(CellStyle.ALIGN_FILL);
		return style;
	}
}
