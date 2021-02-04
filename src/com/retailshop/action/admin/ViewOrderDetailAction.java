package com.retailshop.action.admin;

import com.retailshop.service.IAdminService;
import com.retailshop.service.IProductService;

import com.retailshop.service.impl.AdminServiceImpl;
import com.retailshop.service.impl.ProductServiceImpl;
import com.retailshop.util.DBConnection;
import com.retailshop.util.DBUtil;
import com.retailshop.util.EmailUtil;
import com.retailshop.util.GeneralUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

public class ViewOrderDetailAction implements ServletRequestAware, ServletResponseAware
{
	private HttpServletResponse response;
	private static final Logger logger = Logger.getLogger(ViewOrderDetailAction.class.getName());
	
	private ArrayList customOrderDetailList = new ArrayList();
	
	public ArrayList getCustomOrderDetailList() {
		return customOrderDetailList;
	}

	public void setCustomOrderDetailList(ArrayList customOrderDetailList) {
		this.customOrderDetailList = customOrderDetailList;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	private String msg;
	private HttpServletRequest request;
    Map orderDetail;

    public ViewOrderDetailAction()
    {
    }

    public Map getOrderDetail()
    {
        return orderDetail;
    }

    public void setOrderDetail(Map orderDetail)
    {
        this.orderDetail = orderDetail;
    }

    public String execute() throws SQLException
    {
        Connection connection = DBConnection.getConnection();
        IAdminService objAdminService = new AdminServiceImpl();
        setOrderDetail(objAdminService.getOrderDetail(connection));
        connection.close();
        return "success";
    }
    

	public String getUpdatedDeliveredProductForm() throws SQLException{

		Connection connection = DBConnection.getConnection();
        IAdminService objAdminService = new AdminServiceImpl();
        setOrderDetail(objAdminService.getOrderDetailAfterDeliver(connection));
        connection.close();

		
		return "success";
	}
	
	public String updatedDeliveredProductForm() throws SQLException{
		
		String allCartId = request.getParameter("allCartId");
    	if(allCartId != null && allCartId.indexOf(":") != -1){
    		allCartId = allCartId.substring(1);
    	}
    	String allCartIdArr[] = allCartId.split(":");
    	List<String> cartIdList = Arrays.asList(allCartIdArr);
    	
    	Connection connection = null;
    	ArrayList allEmail = new ArrayList();
    	try{
    		connection = DBConnection.getConnection();
	    	HashSet<String> hs = new HashSet<String>(cartIdList);
	    	Iterator<String> itr = hs.iterator();
	    	while(itr.hasNext()){
	    		String cartId = itr.next();
	    		String action = request.getParameter("action"+cartId);
	    		
	    		String orderStatus = "";
	    		if(action != null && action.equalsIgnoreCase("C")){
	    			orderStatus= "Confirmed";
	    		}
	    		else if(action != null && action.equalsIgnoreCase("R")){
	    			orderStatus = "Rejected";
	    		}
	    		else if(action != null && action.equalsIgnoreCase("D")){
	    			orderStatus = "Delivered";
	    		}
	    		else if(action != null && action.equalsIgnoreCase("ND")){
	    			orderStatus = "Not been Delivered";
	    		}
	    		
	    		IAdminService objAdminService = new AdminServiceImpl();
	    		boolean b = objAdminService.acceptRejectPendingOrder(connection, cartId, action);
	    		if(b){
	    			String emailId = objAdminService.getEmailIdForOrderId(connection, cartId);
	    			//allEmail.add(emailId);
	    			final String msg = "<table width='100%' border='0' cellspacing='0' cellpadding='0' bgcolor='#f7f5ee'>"+
							"<tr>"+
								"<td><table width='600' border='0' cellspacing='0' cellpadding='0' bgcolor='#FFFFFF' align='center'>"+
										"<tr>"+
											"<td><table width='100%' border='0' cellspacing='0' cellpadding='0'>"+
													"<tr>"+
														"<td width='215' colspan='2' align='right'><a href='http://bestkart4u.com/' target='_blank'> <img src='http://www.bestkart4u.com/images/logo.png' width='205' height='82' alt='' title='' border='0' /></a></td>"+
														"<td width='383' align='right'><font style='font-family:Trebuchet MS; color:#181818; font-size:11px;'><a href='javascript:void();' style='color:#181818; text-decoration:none'>Call Us: <b>+91-999-920-33 89</b></a></font>&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;<font style='font-family:'Trebuchet MS', Helvetica, Arial, sans-serif; color:#181818; font-size:11px;'><a href= 'http://bestkart4u.com/contacts.jsp' target='_blank' style='color:#181818; text-decoration:none'>Customer Support</a></font>&nbsp;&nbsp;&nbsp;</td>"+
													"</tr>"+
												"</table></td>"+
										"</tr>"+
										"<tr>"+
											"<td align='center'><a href='http://bestkart4u.com/' target='_blank'><img src='http://www.bestkart4u.com/images/banner.jpg' width='590' height='' alt='' title='' border='0' /></a></td>"+
										"</tr>"+
										"<tr>"+
											"<td align='center' valign='middle'><table width='100%' border='0' cellspacing='0' cellpadding='0'>"+
													"<tr>"+
														"<td width='2%'>&nbsp;</td>"+
														"<td width='96%' align='center' style='border-bottom:1px dotted #d61c13; padding-top:20px;' height='30'><font style='font-family:'Trebuchet MS', Helvetica, Arial, sans-serif; color:#d61c13; font-size:20px; text-transform:uppercase'>Welcome to Bestkart4U</font></td>"+
														"<td width='2%'>&nbsp;</td>"+
													"</tr>"+
												"</table></td>"+
										"</tr>"+
										"<tr>"+
											"<td>&nbsp;</td>"+
										"</tr>"+
										"<tr>"+
											"<td bgcolor='#f1efef' style=' padding:10px 0px;'><table width='100%' border='0' cellspacing='0' cellpadding='0'>"+
													"<tr>"+
														"<td width='4%'>&nbsp;</td>"+
														"<td width='92%' align='left' valign='middle'><font style='font-family:'Trebuchet MS', Helvetica, Arial, sans-serif; color:#181818; font-size:12px; line-height:22px;'>Dear <b>"+emailId+"</b>,<br />"+
															"Greetings from <b>Bestkart4u.com</b><br />"+
															"Your Order has been acknowledge with us. Order status is below:</font></td>"+
														"<td width='4%'>&nbsp;</td>"+
													"</tr>"+
												"</table></td>"+
										"</tr>"+
										"<tr>"+
											"<td style=' padding:5px 0px;'><table width='100%' border='0' cellspacing='0' cellpadding='0'>"+
													"<tr>"+
														"<td colspan='3'>&nbsp;</td>"+
													"</tr>"+
													"<tr>"+
														"<td width='4%'>&nbsp;</td>"+
														"<td style='font-family:'Trebuchet MS', Helvetica, Arial, sans-serif; color:#181818; font-size:12px; line-height:22px;'><table width='100%' border='0' cellspacing='0' cellpadding='0'>"+
																"<tbody>"+
																	"<tr>"+
																		"<td width='66%'><table width='100%' border='0' cellspacing='0' cellpadding='0'>"+
																				"<tbody>"+
																					"<tr>"+
																						"<td bgcolor='#f9f9f9' style=' text-indent:10px; line-height:32px; border-bottom:1px dotted #e2dfdf;'>Order ID:</td>"+
																						"<td bgcolor='#f9f9f9' style=' text-indent:10px; line-height:22px; border-bottom:1px dotted #e2dfdf;'><b>"+cartId+"</b></td>"+
																					"</tr>"+
																					"<tr>"+
																						"<td bgcolor='#f9f9f9' style=' text-indent:10px; line-height:32px; border-bottom:1px dotted #e2dfdf;'>Order Status</td>"+
																						"<td bgcolor='#f9f9f9' style=' text-indent:10px; line-height:22px; border-bottom:1px dotted #e2dfdf;'><b>"+orderStatus+"</b></td>"+
																					"</tr>"+
																				"</tbody>"+
																			"</table></td>"+
																		"<td width='4%'>&nbsp;</td>"+
																		"<td width='30%' valign='top'>&nbsp;</td>"+
																	"</tr>"+
																"</tbody>"+
															"</table></td>"+
														"<td width='4%'>&nbsp;</td>"+
													"</tr>"+
												"</table></td>"+
										"</tr>"+
										"<tr>"+
											"<td>&nbsp;</td>"+
										"</tr>"+
										"<tr>"+
											"<td><img src='http://www.bestkart4u.com/images/PROMO-GREEN2_07.jpg' width='598' height='7' style='display:block' border='0' alt=''/></td>"+
										"</tr>"+
										"<tr>"+
											"<td>&nbsp;</td>"+
										"</tr>"+
										"<tr>"+
											"<td><table width='100%' border='0' cellspacing='0' cellpadding='0'>"+
													"<tr>"+
														"<td width='8%' align='center'><font style='font-family:'Trebuchet MS', Helvetica, Arial, sans-serif; color:#181818; font-size:10px; text-transform:uppercase'><a href='http://bestkart4u.com/' target='_blank' style='color:#010203; text-decoration:none'><strong>HOME</strong></a></font></td>"+
														"<td width='2%' align='center'><font style='font-family:'Trebuchet MS', Helvetica, Arial, sans-serif; color:#181818; font-size:8px; text-transform:uppercase'><strong>|</strong></font></td>"+
														"<td width='12%' align='center'><font style='font-family:'Trebuchet MS', Helvetica, Arial, sans-serif; color:#181818; font-size:10px; text-transform:uppercase'><a href='http://bestkart4u.com/aboutUs.jsp' target='_blank' style='color:#010203; text-decoration:none'><strong>WHO WE ARE</strong></a></font></td>"+
														"<td width='2%' align='center'><font style='font-family:'Trebuchet MS', Helvetica, Arial, sans-serif; color:#181818; font-size:8px; text-transform:uppercase'><strong>|</strong></font></td>"+
														"<td width='8%' align='center'><font style='font-family:'Trebuchet MS', Helvetica, Arial, sans-serif; color:#181818; font-size:10px; text-transform:uppercase'><a href='http://bestkart4u.com/contacts.jsp' target='_blank' style='color:#010203; text-decoration:none'><strong>CONTACT</strong></a></font></td>"+
														"<td width='22%' align='center'>&nbsp;</td>"+
														"<td width='3%' align='right'><a href='https://www.facebook.com/bestkart4u/timeline' target='_blank'><img src='http://www.bestkart4u.com/images/PROMO-GREEN2_09_01.jpg' alt='facebook' width='21' height='19' border='0' /></a></td>"+
														"<td width='3%' align='center'><a href='https://twitter.com/bestkart4u' target='_blank'><img src='http://www.bestkart4u.com/images/PROMO-GREEN2_09_02.jpg' alt='twitter' width='21' height='19' border='0' /></a></td>"+
														"<td width='2%'>&nbsp;</td>"+
													"</tr>"+
												"</table></td>"+
										"</tr>"+
										"<tr>"+
											"<td>&nbsp;</td>"+
										"</tr>"+
										"<tr>"+
											"<td align='left'><font style='font-family:'Trebuchet MS', Helvetica, Arial, sans-serif; color:#181818; font-size:11px'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>Call Us: +91-999-920-33 89</strong>&nbsp;&nbsp;|&nbsp;&nbsp;<strong>Support:<a href='mailto:support@bestkart4u.com' style=' text-decoration:none; color:#181818;'>support@bestkart4u.com</a></strong>&nbsp;&nbsp;|&nbsp;&nbsp;<strong>Sales:<a href='mailto:sales@bestkart4u.com' style=' text-decoration:none; color:#181818;'>sales@bestkart4u.com</a></strong></font></td>"+
										"</tr>"+
										"<tr>"+
											"<td>&nbsp;</td>"+
										"</tr>"+
									"</table></td>"+
							"</tr>"+
						"</table>";
	    			
	    			System.out.println(emailId);
	    			
	    			String sendTo [] = {emailId}; 
	    			EmailUtil.sendEmail(sendTo, msg, "Order Acknowledgement");
	    			
	    		}
	    	}
    	}
    	catch (Exception e) {
			logger.error("",e);
		}
    	finally{
    		DBUtil.closeConnection(connection);
    	}
    	
    	setMsg("Order status has been changed");
    	
    	return "success";
    	
	}
    
    public String getOrdersForInvoice() throws SQLException
    {
        Connection connection = DBConnection.getConnection();
        IAdminService objAdminService = new AdminServiceImpl();
        setOrderDetail(objAdminService.getOrderDetailForInvoice(connection));
        connection.close();
        return "success";
    }
    
    public String generateReceipt() throws SQLException, JRException, IOException{
    	String orderId = request.getParameter("orderId");
    	Connection connection = DBConnection.getConnection();
    	InputStream input = this.getClass().getClassLoader().getResourceAsStream("com/retailshop/util/cartreciept.jrxml");
    	
        Map m = new HashMap();
        m.put("order_id",orderId);
        m.put("image_path",request.getRealPath("images")+File.separator+"logo.png");
        
        /*InputStream imgInputStream = 
        	    this.getClass().getResourceAsStream("logo.jpg");
        
        m.put("image_path",imgInputStream);
        */
        JasperDesign jasperDesign = JRXmlLoader.load(input);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, m, connection);
        jasperPrint.setName(orderId + "-Invoice.pdf");

        response.setContentType("APPLICATION/OCTET-STREAM");
        String disHeader = "Attachment;Filename=\""+orderId + "-Invoice.pdf";

        ServletOutputStream os = response.getOutputStream();

        response.setHeader("Content-Disposition", disHeader);

        byte reportBytes[] = JasperExportManager.exportReportToPdf(jasperPrint);
        os.write(reportBytes);
        os.flush(); 
        connection.close();
    	return"none";
    }
    
    public String acceptRejectPendingOrder(){
    	
    	String allCartId = request.getParameter("allCartId");
    	if(allCartId != null && allCartId.indexOf(":") != -1){
    		allCartId = allCartId.substring(1);
    	}
    	String allCartIdArr[] = allCartId.split(":");
    	List<String> cartIdList = Arrays.asList(allCartIdArr);
    	
    	Connection connection = null;
    	ArrayList allEmail = new ArrayList();
    	try{
    		connection = DBConnection.getConnection();
	    	HashSet<String> hs = new HashSet<String>(cartIdList);
	    	Iterator<String> itr = hs.iterator();
	    	while(itr.hasNext()){
	    		String cartId = itr.next();
	    		String action = request.getParameter("action"+cartId);
	    		
	    		String orderStatus = "";
	    		if(action != null && action.equalsIgnoreCase("C")){
	    			orderStatus= "Confirmed";
	    		}
	    		else if(action != null && action.equalsIgnoreCase("R")){
	    			orderStatus = "Rejected";
	    		}
	    		
	    		
	    		IAdminService objAdminService = new AdminServiceImpl();
	    		boolean b = objAdminService.acceptRejectPendingOrder(connection, cartId, action);
	    		if(b){
	    			String emailId = objAdminService.getEmailIdForOrderId(connection, cartId);
	    			//allEmail.add(emailId);
	    			final String msg = "<table width='100%' border='0' cellspacing='0' cellpadding='0' bgcolor='#f7f5ee'>"+
							"<tr>"+
								"<td><table width='600' border='0' cellspacing='0' cellpadding='0' bgcolor='#FFFFFF' align='center'>"+
										"<tr>"+
											"<td><table width='100%' border='0' cellspacing='0' cellpadding='0'>"+
													"<tr>"+
														"<td width='215' colspan='2' align='right'><a href='http://bestkart4u.com/' target='_blank'> <img src='http://www.bestkart4u.com/images/logo.png' width='205' height='82' alt='' title='' border='0' /></a></td>"+
														"<td width='383' align='right'><font style='font-family:Trebuchet MS; color:#181818; font-size:11px;'><a href='javascript:void();' style='color:#181818; text-decoration:none'>Call Us: <b>+91-999-920-33 89</b></a></font>&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;<font style='font-family:'Trebuchet MS', Helvetica, Arial, sans-serif; color:#181818; font-size:11px;'><a href= 'http://bestkart4u.com/contacts.jsp' target='_blank' style='color:#181818; text-decoration:none'>Customer Support</a></font>&nbsp;&nbsp;&nbsp;</td>"+
													"</tr>"+
												"</table></td>"+
										"</tr>"+
										"<tr>"+
											"<td align='center'><a href='http://bestkart4u.com/' target='_blank'><img src='http://www.bestkart4u.com/images/banner.jpg' width='590' height='' alt='' title='' border='0' /></a></td>"+
										"</tr>"+
										"<tr>"+
											"<td align='center' valign='middle'><table width='100%' border='0' cellspacing='0' cellpadding='0'>"+
													"<tr>"+
														"<td width='2%'>&nbsp;</td>"+
														"<td width='96%' align='center' style='border-bottom:1px dotted #d61c13; padding-top:20px;' height='30'><font style='font-family:'Trebuchet MS', Helvetica, Arial, sans-serif; color:#d61c13; font-size:20px; text-transform:uppercase'>Welcome to Bestkart4U</font></td>"+
														"<td width='2%'>&nbsp;</td>"+
													"</tr>"+
												"</table></td>"+
										"</tr>"+
										"<tr>"+
											"<td>&nbsp;</td>"+
										"</tr>"+
										"<tr>"+
											"<td bgcolor='#f1efef' style=' padding:10px 0px;'><table width='100%' border='0' cellspacing='0' cellpadding='0'>"+
													"<tr>"+
														"<td width='4%'>&nbsp;</td>"+
														"<td width='92%' align='left' valign='middle'><font style='font-family:'Trebuchet MS', Helvetica, Arial, sans-serif; color:#181818; font-size:12px; line-height:22px;'>Dear <b>"+emailId+"</b>,<br />"+
															"Greetings from <b>Bestkart4u.com</b><br />"+
															"Your Order has been acknowledge with us. Order status is below:</font></td>"+
														"<td width='4%'>&nbsp;</td>"+
													"</tr>"+
												"</table></td>"+
										"</tr>"+
										"<tr>"+
											"<td style=' padding:5px 0px;'><table width='100%' border='0' cellspacing='0' cellpadding='0'>"+
													"<tr>"+
														"<td colspan='3'>&nbsp;</td>"+
													"</tr>"+
													"<tr>"+
														"<td width='4%'>&nbsp;</td>"+
														"<td style='font-family:'Trebuchet MS', Helvetica, Arial, sans-serif; color:#181818; font-size:12px; line-height:22px;'><table width='100%' border='0' cellspacing='0' cellpadding='0'>"+
																"<tbody>"+
																	"<tr>"+
																		"<td width='66%'><table width='100%' border='0' cellspacing='0' cellpadding='0'>"+
																				"<tbody>"+
																					"<tr>"+
																						"<td bgcolor='#f9f9f9' style=' text-indent:10px; line-height:32px; border-bottom:1px dotted #e2dfdf;'>Order ID:</td>"+
																						"<td bgcolor='#f9f9f9' style=' text-indent:10px; line-height:22px; border-bottom:1px dotted #e2dfdf;'><b>"+cartId+"</b></td>"+
																					"</tr>"+
																					"<tr>"+
																						"<td bgcolor='#f9f9f9' style=' text-indent:10px; line-height:32px; border-bottom:1px dotted #e2dfdf;'>Order Status</td>"+
																						"<td bgcolor='#f9f9f9' style=' text-indent:10px; line-height:22px; border-bottom:1px dotted #e2dfdf;'><b>"+orderStatus+"</b></td>"+
																					"</tr>"+
																				"</tbody>"+
																			"</table></td>"+
																		"<td width='4%'>&nbsp;</td>"+
																		"<td width='30%' valign='top'>&nbsp;</td>"+
																	"</tr>"+
																"</tbody>"+
															"</table></td>"+
														"<td width='4%'>&nbsp;</td>"+
													"</tr>"+
												"</table></td>"+
										"</tr>"+
										"<tr>"+
											"<td>&nbsp;</td>"+
										"</tr>"+
										"<tr>"+
											"<td><img src='http://www.bestkart4u.com/images/PROMO-GREEN2_07.jpg' width='598' height='7' style='display:block' border='0' alt=''/></td>"+
										"</tr>"+
										"<tr>"+
											"<td>&nbsp;</td>"+
										"</tr>"+
										"<tr>"+
											"<td><table width='100%' border='0' cellspacing='0' cellpadding='0'>"+
													"<tr>"+
														"<td width='8%' align='center'><font style='font-family:'Trebuchet MS', Helvetica, Arial, sans-serif; color:#181818; font-size:10px; text-transform:uppercase'><a href='http://bestkart4u.com/' target='_blank' style='color:#010203; text-decoration:none'><strong>HOME</strong></a></font></td>"+
														"<td width='2%' align='center'><font style='font-family:'Trebuchet MS', Helvetica, Arial, sans-serif; color:#181818; font-size:8px; text-transform:uppercase'><strong>|</strong></font></td>"+
														"<td width='12%' align='center'><font style='font-family:'Trebuchet MS', Helvetica, Arial, sans-serif; color:#181818; font-size:10px; text-transform:uppercase'><a href='http://bestkart4u.com/aboutUs.jsp' target='_blank' style='color:#010203; text-decoration:none'><strong>WHO WE ARE</strong></a></font></td>"+
														"<td width='2%' align='center'><font style='font-family:'Trebuchet MS', Helvetica, Arial, sans-serif; color:#181818; font-size:8px; text-transform:uppercase'><strong>|</strong></font></td>"+
														"<td width='8%' align='center'><font style='font-family:'Trebuchet MS', Helvetica, Arial, sans-serif; color:#181818; font-size:10px; text-transform:uppercase'><a href='http://bestkart4u.com/contacts.jsp' target='_blank' style='color:#010203; text-decoration:none'><strong>CONTACT</strong></a></font></td>"+
														"<td width='22%' align='center'>&nbsp;</td>"+
														"<td width='3%' align='right'><a href='https://www.facebook.com/bestkart4u/timeline' target='_blank'><img src='http://www.bestkart4u.com/images/PROMO-GREEN2_09_01.jpg' alt='facebook' width='21' height='19' border='0' /></a></td>"+
														"<td width='3%' align='center'><a href='https://twitter.com/bestkart4u' target='_blank'><img src='http://www.bestkart4u.com/images/PROMO-GREEN2_09_02.jpg' alt='twitter' width='21' height='19' border='0' /></a></td>"+
														"<td width='2%'>&nbsp;</td>"+
													"</tr>"+
												"</table></td>"+
										"</tr>"+
										"<tr>"+
											"<td>&nbsp;</td>"+
										"</tr>"+
										"<tr>"+
											"<td align='left'><font style='font-family:'Trebuchet MS', Helvetica, Arial, sans-serif; color:#181818; font-size:11px'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>Call Us: +91-999-920-33 89</strong>&nbsp;&nbsp;|&nbsp;&nbsp;<strong>Support:<a href='mailto:support@bestkart4u.com' style=' text-decoration:none; color:#181818;'>support@bestkart4u.com</a></strong>&nbsp;&nbsp;|&nbsp;&nbsp;<strong>Sales:<a href='mailto:sales@bestkart4u.com' style=' text-decoration:none; color:#181818;'>sales@bestkart4u.com</a></strong></font></td>"+
										"</tr>"+
										"<tr>"+
											"<td>&nbsp;</td>"+
										"</tr>"+
									"</table></td>"+
							"</tr>"+
						"</table>";
	    			String sendTo [] = {emailId}; 
	    			EmailUtil.sendEmail(sendTo, msg, "Order Acknowledgement");
	    			
	    		}
	    	}
    	}
    	catch (Exception e) {
			logger.error("",e);
		}
    	finally{
    		DBUtil.closeConnection(connection);
    	}
    	
    	setMsg("Order status has been changed");
    	
    	return "success";
    }

    public String getCustomInvoiceForm(){
    	
    	return "success";
    }
    
    public String generateCustomInvoice(){
    	
    	try
        {
    		
    		String productName[] = request.getParameterValues("prodName");
    		String productBrandName[] = request.getParameterValues("prodBrandName");
    		String productPrice[] = request.getParameterValues("unitPrice");
    		String quantity[] = request.getParameterValues("quantity");
    		String size[] = request.getParameterValues("size");
    		String totalAmount[] = request.getParameterValues("totalPrice");
    		String mrp[] = request.getParameterValues("mrp");
    		
    		String address = request.getParameter("address");
    		String discount = request.getParameter("discount");
    		String deliveryCharge = request.getParameter("deliveryCharge");
    		String name = request.getParameter("name");
    		String amountToPay = request.getParameter("amountToPay");
    		
    		String finalAmount = request.getParameter("finalAmount");
    		
    		String orderDate = request.getParameter("orderDate");
    		String deliveryDate = request.getParameter("deliveryDate");
    		
    		ArrayList al = new ArrayList();
    		
    		Connection connection = null;
    		long cartId = 0;
    		try{
    			IProductService objProductService = new ProductServiceImpl(); 
    			connection = DBConnection.getConnection();
    			objProductService = new ProductServiceImpl();
    			
    			cartId = objProductService.getCartId(connection);
    			
    		}
    		catch(Exception e){
    			logger.error("unexpected Error while creating connection" , e);
    		}
    		
    		String orderId = GeneralUtil.getOrderId(cartId, 5);
    		
    		for(int i = 0; i<productName.length; i++){
    			CustomInvoiceBean c = new CustomInvoiceBean();
    			c.setProductName(productName[i]);
    			c.setProductBrandName(productBrandName[i]);
    			c.setAddress(address);
    			c.setAmountToPay(amountToPay);
    			c.setDeliveryCharge(deliveryCharge);
    			c.setDiscount(discount);
    			c.setName(name);
    			c.setPrice(productPrice[i]);
    			c.setQuantity(quantity[i]);
    			c.setSize(size[i]);
    			c.setTotalPrice(totalAmount[i]);
    			c.setFinalAmount(finalAmount);
    			c.setMrp(mrp[i]);
    			c.setSrNo(i+1);
    			c.setOrderId(orderId);
    			
    			c.setInvoiceDate(deliveryDate);
    			c.setOrderDate(orderDate);
    			
    			al.add(c);
    			
    		}
    		
    		setCustomOrderDetailList(al);
    		
            JasperReport jasperReport = null;
            JasperPrint jasperPrint = null;
            JasperDesign jasperDesign = null;
            Map parameters = new HashMap();
            
            parameters.put("image_path",request.getRealPath("images")+File.separator+"logo.png");
            
            InputStream input = this.getClass().getClassLoader().getResourceAsStream("com/retailshop/util/cartrecieptcustom.jrxml");
            jasperDesign = JRXmlLoader.load(input);
            jasperReport = JasperCompileManager.compileReport(jasperDesign);
            jasperPrint  = JasperFillManager.fillReport(jasperReport, parameters, new JRBeanCollectionDataSource(al));
            
            jasperPrint.setName("Invoice.pdf");

            response.setContentType("APPLICATION/OCTET-STREAM");
            String disHeader = "Attachment;Filename=Invoice.pdf";

            ServletOutputStream os = response.getOutputStream();

            response.setHeader("Content-Disposition", disHeader);

            byte reportBytes[] = JasperExportManager.exportReportToPdf(jasperPrint);
            os.write(reportBytes);
            os.flush(); 
            
        }
        catch(Exception ex)
        {
        	logger.error("", ex);
        }
    	
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
