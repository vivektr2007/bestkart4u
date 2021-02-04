package com.retailshop.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ModelDriven;
import com.retailshop.service.IRegisterService;
import com.retailshop.service.impl.RegisterServiceImpl;
import com.retailshop.util.DBConnection;
import com.retailshop.util.DBUtil;
import com.retailshop.util.EmailUtil;
import com.retailshop.util.T_Scram;

public class RegisterAction implements ModelDriven,ServletResponseAware,ServletRequestAware, SessionAware{

	private static final Logger logger = Logger.getLogger(RegisterAction.class.getName());
	
	private RegisterBean regBean = new RegisterBean();
	private HttpServletResponse response ;
	private HttpServletRequest request ;
	private String errorMsg;
	private Map session;
	
	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String execute(){
		
		HttpSession session = request.getSession();
		/*
		String sessionCaptcha = (String)session.getAttribute("captcha");
		*//*String userCaptcha = regBean.getCaptcha_1();
		if((sessionCaptcha != null && userCaptcha != null) && !sessionCaptcha.equals("") && !userCaptcha.equals("") && (!sessionCaptcha.equals(userCaptcha))){
			
			setErrorMsg("Image text entered is incorrect.");
			
			return "input";
		}*/
			
		
		Map parameters = new HashMap();
		
		String encPass = T_Scram.encrypt(regBean.getPassword());
		
		parameters.put("EMAILID", regBean.getEmailId());
		parameters.put("PASSWORD", encPass);
		parameters.put("MOBILENO", regBean.getMobileNo());
		parameters.put("FIRSTNAME", regBean.getFirstName());
		parameters.put("LASTNAME", regBean.getLastName());
		/*parameters.put("DOB", regBean.getDob());
		parameters.put("TELEPHONENO", regBean.getTelephoneNo());
		parameters.put("SALUTATION", regBean.getSalutaion());
		parameters.put("HOUSEDETAIL", regBean.getHouseDetail());
		parameters.put("STREETDETAIL", regBean.getStreetDetail());
		parameters.put("AREA", regBean.getArea());
		parameters.put("RESIDENTCOMPLEX", regBean.getResidentComplex());
		parameters.put("LANDMARK", regBean.getLandmark());
		parameters.put("CITY", regBean.getCity());
		parameters.put("PINCODE", regBean.getPincode());
		*/
		if(regBean.getEmailId() == null || regBean.getEmailId().equals("")){
			return "error";
		}
		
		else{
			Connection connection = DBConnection.getConnection();
			try{
				IRegisterService objRegisterService = new RegisterServiceImpl();
				boolean flag = objRegisterService.checkEmailID(connection, regBean.getEmailId());
				if(!flag){
					boolean regFlag = objRegisterService.registerUser(connection, parameters);
					if(!regFlag){
						request.setAttribute("registerErrorMsg", "There is some error while registration. Please try again later.");
						return "input";
					}
					session.setAttribute("loginId", regBean.getEmailId());
					session.setAttribute("loginName", regBean.getFirstName() + " " + regBean.getLastName());
					
					
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
														"<td width='92%' align='left' valign='middle'><font style='font-family:'Trebuchet MS', Helvetica, Arial, sans-serif; color:#181818; font-size:12px; line-height:22px;'>Dear <b>"+regBean.getFirstName() + " " + regBean.getLastName()+"</b>,<br />"+
															"Greetings from <b>Bestkart4u.com</b><br />"+
															"You has been successfully registered with us. Your login credentials are below:</font></td>"+
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
																						"<td bgcolor='#f9f9f9' style=' text-indent:10px; line-height:22px; border-bottom:1px dotted #e2dfdf;'><b>"+regBean.getEmailId()+"</b></td>"+
																					"</tr>"+
																					"<tr>"+
																						"<td bgcolor='#f9f9f9' style=' text-indent:10px; line-height:32px; border-bottom:1px dotted #e2dfdf;'>Password</td>"+
																						"<td bgcolor='#f9f9f9' style=' text-indent:10px; line-height:22px; border-bottom:1px dotted #e2dfdf;'><b>"+regBean.getPassword()+"</b></td>"+
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
					String to[] = {regBean.getEmailId()};
					try {
						EmailUtil.sendEmail(to, msg, subject);
					} catch (Exception e) {
						logger.error("Unexpected error while sending mail", e);
					}
					finally{
						DBUtil.closeConnection(connection);
					}
					
					
				}
				else{
					return "error";
				}
				
				
				
			}
			catch (Exception e) {
				logger.error("There is some error in registering user,",e);
			}
			finally{
				DBUtil.closeConnection(connection);
			}
		}
		return "success";
	}

	public RegisterBean getRegBean() {
		return regBean;
	}

	public void setRegBean(RegisterBean regBean) {
		this.regBean = regBean;
	}

	public String checkEmailID() throws IOException{
		
		boolean flag = false;
		PrintWriter out = response.getWriter();
			if(regBean.getEmailId() != null && !regBean.getEmailId().equals("")){
			
			Connection connection = DBConnection.getConnection();
			try{
				IRegisterService objRegisterService = new RegisterServiceImpl();
				flag = objRegisterService.checkEmailID(connection, regBean.getEmailId());
			}
			finally{
				DBUtil.closeConnection(connection);
			}
			out.print(flag);
		}
		return "none";
	}

	public String getUpdateProfileForm() throws IOException{
		
		String emailId = (String)session.get("loginId");
		Connection connection = DBConnection.getConnection();
		try{
			IRegisterService objRegisterService = new RegisterServiceImpl();
			RegisterBean rb = objRegisterService.getUpdateProfileForm(connection,emailId);
			setRegBean(rb);
			
		}
		finally{
			DBUtil.closeConnection(connection);
		}
		
		return "success";
	}
	
	public String updateProfile(){
		
		boolean flag = false;
		String emailId = (String)session.get("loginId");
		Connection connection = DBConnection.getConnection();
		try{
			IRegisterService objRegisterService = new RegisterServiceImpl();
			flag = objRegisterService.updateProfile(connection,regBean,emailId);
		}
		finally{
			DBUtil.closeConnection(connection);
		}
		if(flag){
			setErrorMsg("Profile Updated Successfully");
			return "success";
		}
		else{
			setErrorMsg("There is some error. Please try again later");
			return "error";
		}
	}
	
	@Override
	public Object getModel() {
		
		return regBean;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
		
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setSession(Map session) {
		this.session = session;
	}
	
}
