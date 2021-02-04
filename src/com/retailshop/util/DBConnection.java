package com.retailshop.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class DBConnection {
	private static final Logger LOG = Logger.getLogger(DBConnection.class.getName());
	public static Connection getConnection(){

		LOG.info("****** in getConnection method ******");
		Connection connection = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/retailshop", "root", "");
			//connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/apostle_retailshop", "apostle_retail", "Retail@12345");
		}
		catch(ClassNotFoundException ce){
			LOG.error("Exception in getConnection method ", ce);
		}
		catch(SQLException se){
			LOG.error("SQL Error in getConnection method ", se);
		}
		catch(Exception e){
			LOG.error("Unexpected Error in getConnection method ", e);
		}
		return connection;
	}
}
