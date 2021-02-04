package com.retailshop.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DBUtil {

	private static final Logger logger = Logger.getLogger(DBUtil.class.getName());
	
	public static void closeResultSet(ResultSet rs){
		
		try{
			if(rs != null){
				rs.close();
			}
		}
		catch(Exception e){
			logger.error("Exception in closing ResultSet, ", e);
		}
	}
	
	public static void closePreparedStatement(PreparedStatement pstmt){
		
		try{
			if(pstmt != null){
				pstmt.close();
			}
		}
		catch(Exception e){
			logger.error("Exception in closing pstmt, ", e);
		}
	}

	public static void closeStatement(Statement statement){
		
		try{
			if(statement != null){
				statement.close();
			}
		}
		catch(Exception e){
			logger.error("Exception in closing statement, ", e);
		}
	}

	public static void closeConnection(Connection connection){
		
		try{
			if(connection != null){
				connection.close();
			}
		}
		catch(Exception e){
			logger.error("Exception in closing connection, ", e);
		}
	}
	
	public static boolean isExists(Connection connection, String tableName, String whereClause){
		
		boolean flag = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			
			String query = "select * from " + tableName + " where " + whereClause;
			pstmt = connection.prepareStatement(query);
			rs = pstmt.executeQuery();
			if(rs.next()){
				flag = true;
			}
			
		}
		catch (Exception e) {
			logger.error("",e);
		}
		finally{
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(pstmt);
		}
		return flag;
	}
	
}
