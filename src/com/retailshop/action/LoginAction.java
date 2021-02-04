package com.retailshop.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.retailshop.service.ILoginService;
import com.retailshop.service.IRegisterService;
import com.retailshop.service.impl.LoginServiceImpl;
import com.retailshop.service.impl.RegisterServiceImpl;
import com.retailshop.util.DBConnection;
import com.retailshop.util.DBUtil;
import com.retailshop.util.EmailUtil;
import com.retailshop.util.T_Scram;

public class LoginAction implements ServletResponseAware,ServletRequestAware, SessionAware{

	private static final Logger logger = Logger.getLogger(LoginAction.class.getName());
	
	private HttpServletResponse response;
	private HttpServletRequest request;
	
	private String email;
	private String password;
	private String loginErrorMsg;
	private String successMsg;
	private Map session;
	
	
	public String getSuccessMsg() {
		return successMsg;
	}

	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}

	public String getLoginErrorMsg() {
		return loginErrorMsg;
	}

	public void setLoginErrorMsg(String loginErrorMsg) {
		this.loginErrorMsg = loginErrorMsg;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String execute(){
		String from = request.getParameter("from");
		request.setAttribute("from", from);
		
		String brandName = request.getParameter("brandName");
		String branNameIn = "";
		if(brandName != null && !brandName.equals("")){
			String brandNameArr[] = brandName.split(",");
			for(int i = 0; i< brandNameArr.length; i++){
				branNameIn = branNameIn + "," + brandNameArr[i] + ""; 
			}
			branNameIn = branNameIn.substring(1);		
		}
		request.setAttribute("startIndex",request.getParameter("startIndexWishlist"));
		request.setAttribute("brandName", branNameIn);
		request.setAttribute("childCategory",request.getParameter("childCategoryWishlist"));
		request.setAttribute("rootCategory",request.getParameter("rootCategoryWishlist"));
		request.setAttribute("subRootCategory",request.getParameter("subRootCategoryWishlist"));
		request.setAttribute("categoryNameWishlist",request.getParameter("categoryNameWishlist"));
		request.setAttribute("productId",request.getParameter("productIdWishlist"));
		request.setAttribute("quantity",request.getParameter("quantityWishlist"));
		request.setAttribute("startIndexWishlist",request.getParameter("startIndexWishlist"));
		request.setAttribute("type", request.getParameter("typeWishlist"));
		
		
		
		String result = "error";
		String name = "";
		Connection connection = null;
		if(session != null && session.size() > 0 && session.containsKey("loginId")){
			result = "success";
		}else{
			ILoginService objLoginService = new LoginServiceImpl();
			try{	
				connection = DBConnection.getConnection();
				String decPass = T_Scram.encrypt(getPassword());
				name = objLoginService.loginCheck(connection, getEmail(), decPass);
			}
			catch(Exception sqe){
				logger.error("Unexpected Exception while getting login detail  ", sqe);
			}
			finally{
				DBUtil.closeConnection(connection);
			}
			if(name != null && !name.equals("")){
				Cookie c = new Cookie("loginId",getEmail());
				session.put("loginId", getEmail());
				session.put("loginName", name);
				session.put("source", "WEB");
				response.addCookie(c);
				
				if(from != null && from.equalsIgnoreCase("main")){
					result = "successToIndex";
				}
				else if(from != null && from.equalsIgnoreCase("cartHeader")){
					result = "success";
				}
				else if(from != null && from.equalsIgnoreCase("wishlist")){
					String type = request.getParameter("typeWishlist");
					
					request.setAttribute("typeWishlist", type);
					result = "successToWishListforProducts";
				}
				else{
					result = "error";
				}
			}
			else{
				setLoginErrorMsg("Email Id or Password entered is incorrect");
				result = "login";
			}
			
		}
		return result;
	}
	
	public String fbLogin(){
		
		String brandName = request.getParameter("brandName");
		String branNameIn = "";
		if(brandName != null && !brandName.equals("")){
			String brandNameArr[] = brandName.split(",");
			for(int i = 0; i< brandNameArr.length; i++){
				branNameIn = branNameIn + "," + brandNameArr[i] + ""; 
			}
			branNameIn = branNameIn.substring(1);		
		}
		
		request.setAttribute("brandName", branNameIn);
		
		request.setAttribute("brandName", request.getParameter("brandName"));
		request.setAttribute("childCategory",request.getParameter("childCategoryWishlist"));
		request.setAttribute("rootCategory",request.getParameter("rootCategoryWishlist"));
		request.setAttribute("subRootCategory",request.getParameter("subRootCategoryWishlist"));
		request.setAttribute("categoryNameWishlist",request.getParameter("categoryNameWishlist"));
		request.setAttribute("productId",request.getParameter("productIdWishlist"));
		request.setAttribute("quantity",request.getParameter("quantityWishlist"));
		request.setAttribute("startIndexWishlist",request.getParameter("startIndexWishlist"));
		request.setAttribute("type", request.getParameter("typeWishlist"));
		
		
		if(session != null && session.size() > 0 && session.containsKey("loginId")){
			return "success";
		}else{
			
			String from = request.getParameter("from");
			request.setAttribute("from", from);
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String id = request.getParameter("id");
			String first_name = request.getParameter("first_name");
			String gender = request.getParameter("gender");
			String last_name = request.getParameter("last_name");
			String link = request.getParameter("link");
			
			Map parameters = new HashMap();
			parameters.put("EMAILID", email);
			
			final int max = 99999;
			final int min = 11111; 
			Random rand = new Random();
		    int randomPass = rand.nextInt((max - min) + 1) + min;
		    String encPass = T_Scram.encrypt(""+randomPass);
		    
			
			parameters.put("PASSWORD", encPass);
			if(gender.equalsIgnoreCase("male")){
				parameters.put("SALUTATION", "Mr.");
			}
			else if(gender.equalsIgnoreCase("female")){
				parameters.put("SALUTATION", "Mrs.");
			}
			else{
				parameters.put("SALUTATION", "");
			}
			parameters.put("FIRSTNAME", first_name);
			parameters.put("LASTNAME", last_name);
			parameters.put("FBID", id);
			
			IRegisterService objRegisterService = new RegisterServiceImpl();
			Connection connection = DBConnection.getConnection();
			
			boolean flag = objRegisterService.checkEmailID(connection, email);
			if(!flag){
				objRegisterService.registerUserForFB(connection, parameters);
				
				final String to[] = {email};
				ILoginService objLoginService = new LoginServiceImpl();
				boolean status = objLoginService.resetPassword(connection, getEmail(), encPass);
				
				//final String msg = "Hi "+email+",\n 		You has been successfully registered with us. \n\n\n Your login id : "+email+"\n\n Password : "+randomPass+"\n\n Thanks & regards,\n BestKart4U Team";
				
				String msg = "<table width='100%' border='0' cellspacing='0' cellpadding='0' bgcolor='#f7f5ee'>"+
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
													"<td width='92%' align='left' valign='middle'><font style='font-family:'Trebuchet MS', Helvetica, Arial, sans-serif; color:#181818; font-size:12px; line-height:22px;'>Dear <b>"+email+"</b>,<br />"+
														"Greetings from <b>Bestkart4u.com</b><br />"+
														"You has been successfully registered with us. Your login credentaisl are below:</font></td>"+
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
																					"<td bgcolor='#f9f9f9' style=' text-indent:10px; line-height:32px; border-bottom:1px dotted #e2dfdf;'>Login ID:</td>"+
																					"<td bgcolor='#f9f9f9' style=' text-indent:10px; line-height:22px; border-bottom:1px dotted #e2dfdf;'><b>"+email+"</b></td>"+
																				"</tr>"+
																				"<tr>"+
																					"<td bgcolor='#f9f9f9' style=' text-indent:10px; line-height:32px; border-bottom:1px dotted #e2dfdf;'>Password</td>"+
																					"<td bgcolor='#f9f9f9' style=' text-indent:10px; line-height:22px; border-bottom:1px dotted #e2dfdf;'><b>"+randomPass+"</b></td>"+
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
				
				
				final String subject = "Registration";
				
				try {
					EmailUtil.sendEmail(to, msg, subject);
				} catch (Exception e) {
					logger.error("Unexpected error while sending mail", e);
				}
				finally{
					DBUtil.closeConnection(connection);
				}
				
			}
			
			if(name != null && !name.equals("")){
				Cookie c = new Cookie("loginId",getEmail());
				session.put("loginId", email);
				session.put("loginName", name);
				session.put("source", "FB");
				response.addCookie(c);
				if(from != null && from.equalsIgnoreCase("cartHeader")){
					return "success";
				}
				else if(from != null && from.equalsIgnoreCase("main")){
					return "successToIndex";
				}
				else if(from != null && from.equalsIgnoreCase("wishlist")){
					String type = request.getParameter("typeWishlist");
					
					request.setAttribute("typeWishlist", type);
					return "successToWishListforProducts";
				}
				else{
					return "error";
				}
			}
			else{
				setLoginErrorMsg("Email Id or Password entered is incorrect");
				return "login";
			}
		}
		
	}
	
	public String forgetPassword() throws IOException{
		
		if(getEmail() == null || getEmail().equals("")){
			setSuccessMsg("Please enter your email Id");
		}
		else{
			boolean flag = false;
			Connection connection = DBConnection.getConnection();
			IRegisterService objRegisterService = new RegisterServiceImpl();
			flag = objRegisterService.checkEmailID(connection, getEmail());
			
			
			if(flag){
				
				final String to[] = {getEmail()};
				
				final int max = 99999;
				final int min = 11111; 
				
				Random rand = new Random();
	
			    int randomPass = rand.nextInt((max - min) + 1) + min;
				
			    String encPass = T_Scram.encrypt(""+randomPass);
			    
				ILoginService objLoginService = new LoginServiceImpl();
				boolean status = objLoginService.resetPassword(connection, getEmail(), encPass);
				
				//final String msg = "Hi "+getEmail()+",\n 		Your Password has been successfully changed. \n\n\n Your New Password is : "+randomPass+"\n\n Thanks & regards,\n BestKart4U Team";
				final String subject = "Password reset";
				
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
													"<td width='92%' align='left' valign='middle'><font style='font-family:'Trebuchet MS', Helvetica, Arial, sans-serif; color:#181818; font-size:12px; line-height:22px;'>Dear <b>"+getEmail()+"</b>,<br />"+
														"Greetings from <b>Bestkart4u.com</b><br />"+
														"Your password has been reset successfully. Your new login credentials are below:</font></td>"+
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
																					"<td bgcolor='#f9f9f9' style=' text-indent:10px; line-height:32px; border-bottom:1px dotted #e2dfdf;'>Login ID:</td>"+
																					"<td bgcolor='#f9f9f9' style=' text-indent:10px; line-height:22px; border-bottom:1px dotted #e2dfdf;'><b>"+getEmail()+"</b></td>"+
																				"</tr>"+
																				"<tr>"+
																					"<td bgcolor='#f9f9f9' style=' text-indent:10px; line-height:32px; border-bottom:1px dotted #e2dfdf;'>New Password</td>"+
																					"<td bgcolor='#f9f9f9' style=' text-indent:10px; line-height:22px; border-bottom:1px dotted #e2dfdf;'><b>"+randomPass+"</b></td>"+
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
				
				
				try {
					EmailUtil.sendEmail(to, msg, subject);
				} catch (Exception e) {
					logger.error("Unexpected error while sending mail", e);
				}
				finally{
					DBUtil.closeConnection(connection);
				}
				setSuccessMsg("Please check your mail for the password");
				
			}
			else{
				setSuccessMsg("Email Id entered doesn't exists in our database.");
			}
		}
		return "success";
	}

	public String changePassword(){
		
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		/*String cNewPassword = request.getParameter("cNewPassword");
		
		oldPassword = T
		
		String name = "";
		*/
		oldPassword = T_Scram.encrypt(oldPassword);
		newPassword = T_Scram.encrypt(newPassword);
		Connection connection = null;
		boolean flag = false;
		boolean flag1 = false;
		
		ILoginService objLoginService = new LoginServiceImpl();
		try{	
			connection = DBConnection.getConnection();
			flag = objLoginService.checkPassword(connection, request.getSession().getAttribute("loginId").toString(), oldPassword);
		}
		catch(Exception sqe){
			logger.error("Unexpected Exception while getting login detail  ", sqe);
		}
		
		if(flag){
			
			flag1 = objLoginService.changePassword(connection, request.getSession().getAttribute("loginId").toString(), newPassword);
			DBUtil.closeConnection(connection);
			if(flag1){
				setLoginErrorMsg("Password changed successfully.");
				return "success";
			}
			else{
				setLoginErrorMsg("There is some error. Please try again later.");
				return "error";
			}
		}
		else{
			setLoginErrorMsg("Old Password entered is incorrect");
			return "error";
		}
		
		
		
	}
	
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setSession(Map session) {
		this.session = session;
		
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
}
