package com.retailshop.action.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.retailshop.service.IAdminService;
import com.retailshop.service.impl.AdminServiceImpl;
import com.retailshop.util.DBConnection;
import com.retailshop.util.DBUtil;


public class ZipUploadAction implements ServletRequestAware {

	private static final Logger logger = Logger.getLogger(ZipUploadAction.class.getName());
	
	private HttpServletRequest request;
	private File zipFile;
	private String zipFileContentType;
	private String zipFileFileName;

	public File getZipFile() {
		return zipFile;
	}

	public void setZipFile(File zipFile) {
		this.zipFile = zipFile;
	}

	public String getZipFileContentType() {
		return zipFileContentType;
	}

	public void setZipFileContentType(String zipFileContentType) {
		this.zipFileContentType = zipFileContentType;
	}

	public String getZipFileFileName() {
		return zipFileFileName;
	}

	public void setZipFileFileName(String zipFileFileName) {
		this.zipFileFileName = zipFileFileName;
	}

	public String getUploadImageZipForm() {

		return "success";
	}

	public String execute() {

		List<String> tempString = new ArrayList<String>();
		InputStream input;
		try {

			input = new FileInputStream(zipFile);
			ZipInputStream zip = new ZipInputStream(input);
			ZipEntry entry = zip.getNextEntry();

			while (entry != null) {
				if (!entry.isDirectory()) {
					if (entry.getName().endsWith(".JPG")
							|| entry.getName().endsWith(".jpeg")
							|| entry.getName().endsWith(".JPEG")
							|| entry.getName().endsWith(".jpg")|| entry.getName().endsWith(".PNG")|| entry.getName().endsWith(".gif") || entry.getName().endsWith(".GIF") || entry.getName().endsWith(".png")) {

						String path = request.getRealPath("") + File.separator
								+  "productimages";//"prodimages";
						String modifiedName = entry.getName().substring(
								entry.getName().lastIndexOf("/") + 1);
						String outpath = path + File.separator + modifiedName;
						File file = new File(outpath);
						if (!file.exists()) {
							file.createNewFile();
						}
						FileOutputStream output = null;
						try {
							byte buffer[] = new byte[10];
							output = new FileOutputStream(file);
							int len = 0;
							while ((len = zip.read(buffer)) > 0) {
								output.write(buffer, 0, len);
							}
							
							String msg = entry.getName() + " has been uploaded successfully";
							tempString.add(msg);
							
						} 
						catch(Exception e){
							String msg = "there is some error while uploading image : " + entry.getName();
							tempString.add(msg);
						}finally {
							if (output != null)
								output.close();
						}

					}
					else{
						String msg = entry.getName() + " is not a valid file. File must be either in jpg or png format";
						tempString.add(msg);
					}
				} else {
					String msg = entry.getName() + " is not a file.";
					tempString.add(msg);
				}
				entry = zip.getNextEntry();
			}
			zip.close();
			input.close();

		} catch (FileNotFoundException e) {
			logger.error("",e);
		} catch (IOException e) {
			logger.error("",e);
		}
		catch (Exception e) {
			logger.error("",e);
		}
		
		request.setAttribute("tempString", tempString);
		
		return "success";

	}

	public String deleteUnusedImages(){
		
		ArrayList msg = new ArrayList();
		
		String path = request.getRealPath("") + File.separator + "productimages";
		
		Connection connection = null;
		
		try{
			connection = DBConnection.getConnection();
			
			IAdminService objAdminService = new AdminServiceImpl();
			List<String> allImages = objAdminService.getAllImages(connection);
			
			File file = new File(path);
			File f[] = file.listFiles();
			for(int i = 0; i<f.length; i++){
				String newFileName = "productimages" + "/" +f[i].getName();
				if(allImages.contains(newFileName.toUpperCase())){
					//System.out.println("File in use " + newFileName.toUpperCase());
				}
				else{
					File f1 = new File(request.getRealPath("") + File.separator + newFileName);
					boolean flag = f1.delete();
					if(flag){
						msg.add(newFileName + " has been deleted successfully.");
					}
					else{
						msg.add(newFileName + " has not been deleted due to some error.");
					}
				}
			}
			
			request.setAttribute("tempString", msg);
			
		}
		catch (Exception e) {
			logger.error("", e);
		}
		finally{
			DBUtil.closeConnection(connection);
		}
		
		return "success";
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

}
