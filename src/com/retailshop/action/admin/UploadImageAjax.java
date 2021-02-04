package com.retailshop.action.admin;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

public class UploadImageAjax implements ServletRequestAware, ServletResponseAware{

	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public String execute() throws IOException, ServletException{
		String ajaxUpdateResult = "";
		
		Enumeration en = request.getParameterNames();
		while(en.hasMoreElements()){
			String name = (String)en.nextElement();
			System.out.println(name);
			
			System.out.println(request.getParameter(name));
			
		}
        try {
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);      
            System.out.println(items.size());
            for (FileItem item : items) {
            	
            	System.out.println(item.isFormField());
            	
                if (item.isFormField()) {
                	System.out.println("Field " + item.getFieldName() + " with value: " + item.getString() + " is successfully read\n\r");
                    ajaxUpdateResult += "Field " + item.getFieldName() + 
                    " with value: " + item.getString() + " is successfully read\n\r";
                } else {
                    String fileName = item.getName();
                    System.out.println("fileName : "+fileName);
                    InputStream content = item.getInputStream();
                    response.setContentType("text/plain");
                    response.setCharacterEncoding("UTF-8");
                    System.out.println(Streams.asString(content));
                    ajaxUpdateResult += "File " + fileName + " is successfully uploaded\n\r";
                }
            }
        } catch (FileUploadException e) {
            throw new ServletException("Parsing file upload failed.", e);
        }
        response.getWriter().print(ajaxUpdateResult);
		return "none";
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
}
