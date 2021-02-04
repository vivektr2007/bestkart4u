package com.retailshop.action.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.log4j.Logger;

import com.retailshop.util.DBConnection;
import com.retailshop.util.DBUtil;


/**
 * Servlet implementation class AjaxUploadServlet
 */
public class AjaxUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(AjaxUploadServlet.class.getName());   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ajaxUpdateResult = "";
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		String productId = "";
		String fileName = "";
		String fileNameDB = "";
        try {

            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);            

            for (FileItem item : items) {

                if (item.isFormField()) {
                	productId = item.getString();	

                } else {
                    fileName = "productimages" + File.separator + item.getName();
                    fileNameDB = "productimages" + "/" + item.getName();;
                    InputStream content = item.getInputStream();
                    File f1 = new File(request.getRealPath("") + File.separator + fileName);
                  
                    OutputStream out = new FileOutputStream(f1);
			        int read = 0;
			        final byte[] bytes = new byte[1024];

			        while ((read = content.read(bytes)) != -1) {
			            out.write(bytes, 0, read);
			        }
                    ajaxUpdateResult += "File " + fileName + " is successfully uploaded\n\r";

                }
            }
            
            connection = DBConnection.getConnection();
            pstmt = connection.prepareStatement("update product_info set image_path = ? where product_id = ?");
            pstmt.setString(1, fileNameDB);
            pstmt.setString(2, productId);
            
            int i = pstmt.executeUpdate();
            
        } catch (FileUploadException e) {
            logger.error(""+e);
        }
        catch (Exception e) {
        	logger.error(""+e);
		}
        finally{
        	DBUtil.closePreparedStatement(pstmt);
        	DBUtil.closeConnection(connection);
        }
        response.getWriter().print(ajaxUpdateResult);
	}

}
