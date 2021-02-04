package com.retailshop.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.retailshop.dao.IMassUploadDao;
import com.retailshop.dao.impl.MassUploadDaoImpl;
import com.retailshop.service.IMassUploadService;

public class MassUploadServiceImpl implements IMassUploadService {

	@Override
	public String uploadProduct(Connection connection, String userName,
			Map<String, String> parameters) {
		IMassUploadDao objMassUploadDao = new MassUploadDaoImpl();
		return objMassUploadDao.uploadProduct(connection, userName, parameters);
	}

	@Override
	public ArrayList downloadProduct(Connection connection) {
		IMassUploadDao objMassUploadDao = new MassUploadDaoImpl();
		return objMassUploadDao.downloadProduct(connection);
	}

	@Override
	public XSSFWorkbook downloadProductForUpdate(Connection connection, Map parameters) {
		IMassUploadDao objMassUploadDao = new MassUploadDaoImpl();
		return objMassUploadDao.downloadProductForUpdate(connection,parameters);
	}
	
}
