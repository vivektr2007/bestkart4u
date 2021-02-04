package com.retailshop.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public interface IMassUploadService {

	public String uploadProduct(Connection connection, String userName, Map<String, String> parameters);
	
	public ArrayList downloadProduct(Connection connection);
	
	public XSSFWorkbook downloadProductForUpdate(Connection connection, Map parameters);
	
}
