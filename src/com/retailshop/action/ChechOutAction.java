package com.retailshop.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ModelDriven;
import com.retailshop.service.IProductService;
import com.retailshop.service.impl.ProductServiceImpl;
import com.retailshop.util.DBConnection;
import com.retailshop.util.DBUtil;
import com.retailshop.util.EmailUtil;
import com.retailshop.util.GeneralUtil;

public class ChechOutAction implements SessionAware,ServletRequestAware,ServletResponseAware,ModelDriven{

	private static final Logger logger = Logger.getLogger(ChechOutAction.class.getName());
	
	private Map session;
	private DeliverAddressBean objDeliveryAddressBean = new DeliverAddressBean();
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	
	Map address;
	
	public Map getAddress() {
		return address;
	}

	public void setAddress(Map address) {
		this.address = address;
	}

	public String getCheckoutDetail(){
		
		Connection connection = null;
		try{
			connection = DBConnection.getConnection();
			IProductService objProductService = new ProductServiceImpl();
			setAddress(objProductService.getDeliveryDetail(connection, session.get("loginId").toString()));
		}
		catch(Exception e){
			logger.error("unexpected Error while creating connection" , e);
		}
		finally{
			DBUtil.closeConnection(connection);
		}
		
		return "success";
	}
	
	public String addAddress(){
		
		Map parameters = new HashMap();
		parameters.put("DELIVERY_NICKNAME", objDeliveryAddressBean.getNickName());
		parameters.put("DELIVERY_FIRSTNAME",objDeliveryAddressBean.getDeliveryFirstName());
		parameters.put("DELIVERY_LASTNAME",objDeliveryAddressBean.getDeliverLastName());
		parameters.put("DELIVERY_CONTACTNO",objDeliveryAddressBean.getDeliveryContactNo());
		parameters.put("DELIVERY_HOUSENO",objDeliveryAddressBean.getDeliveryHouseNo());
		parameters.put("DELIVERY_STREETDETAIL",objDeliveryAddressBean.getDeliveryStreeDetail());
		parameters.put("DELIVERY_RESIDENTIALCOMPLEX",objDeliveryAddressBean.getDeliveryResidentialComplex());
		parameters.put("DELIVERY_LANDMARK",objDeliveryAddressBean.getDeliveryLandmark());
		parameters.put("DELIVERY_AREA",objDeliveryAddressBean.getDeliveryArea());
		parameters.put("DELIVERY_PINCODE",objDeliveryAddressBean.getDeliveryPincode());
		parameters.put("DELIVERY_CITY",objDeliveryAddressBean.getDeliveryCity());
		
		
		Connection connection = null;
		try{
			connection = DBConnection.getConnection();
			IProductService objProductService = new ProductServiceImpl();
			objProductService.addAddress(connection, (String)session.get("loginId"), parameters);
		}
		catch(Exception e){
			logger.error("unexpected Error while creating connection" , e);
		}
		finally{
			DBUtil.closeConnection(connection);
		}
		return "success";
	}
	
	private String selectedAdd;
	private String available_slots;
	/*private String deliveryDate;
	
	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	*/
	public String getSelectedAdd() {
		return selectedAdd;
	}

	public void setSelectedAdd(String selectedAdd) {
		this.selectedAdd = selectedAdd;
	}

	public String getAvailable_slots() {
		return available_slots;
	}

	public void setAvailable_slots(String available_slots) {
		this.available_slots = available_slots;
	}

	public String submitCart() throws Exception{
		
		long cartId = 1;
		IProductService objProductService = null;
		
		String deliveryDate = request.getParameter("deliveryDateHidden");
		
		logger.error("deliveryDate : "+deliveryDate);
		Connection connection = null;
		try{
			connection = DBConnection.getConnection();
			objProductService = new ProductServiceImpl();
			
			cartId = objProductService.getCartId(connection);
			
		}
		catch(Exception e){
			logger.error("unexpected Error while creating connection" , e);
		}
		
		String orderId = GeneralUtil.getOrderId(cartId, 5);
		/*
		String EmailContent = "Hi <b>"+session.get("loginName").toString()+"</b>,<br/><br/><br/>Thanks for your order. Your Order id is : <b>"+orderId+"</b> and order detail is :- <br/><br/><br/>";
		String emailCart = EmailContent + "<html><head></head><body><table border='1'><tr><th>Product Name</th><th>Product Brand Name</th><th>Size</th><th>Quantity</th><th>Unit Price</th><th>Total Price</th></tr>";
		*/
		
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String currentDate = sdf.format(d);
		
		String txt = "<table width='100%' border='0' cellspacing='0' cellpadding='0' bgcolor='#f7f5ee'>"+
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
										"<td width='92%' align='left' valign='middle'><font style='font-family:'Trebuchet MS', Helvetica, Arial, sans-serif; color:#181818; font-size:12px; line-height:22px;'>Dear <b>"+session.get("loginName").toString()+"</b>,<br />"+
											"Greetings from <b>Bestkart4u.com</b><br />"+
											"Products bearing order id <b>"+orderId+"</b> have been booked from <b>Bestkart4u.com</b> and this completes your order with us.</font></td>"+
										"<td width='4%'>&nbsp;</td>"+
									"</tr>"+
								"</table></td>"+
						"</tr>"+
						"<tr>"+
							"<td style=' padding:5px 0px;'><table width='100%' border='0' cellspacing='0' cellpadding='0'>"+
									"<tr>"+
										"<td width='4%'>&nbsp;</td>"+
										"<td width='92%' align='left' valign='middle' height='45px'><font style='font-family:'Trebuchet MS', Helvetica, Arial, sans-serif; color:#181818; font-size:12px; line-height:22px;'><b>ORDER DETAILS GOES HERE</b></font></td>"+
										"<td width='4%'>&nbsp;</td>"+
									"</tr>"+
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
																		"<td bgcolor='#f9f9f9' style=' text-indent:10px; line-height:32px; border-bottom:1px dotted #e2dfdf;'>Order Number</td>"+
																		"<td bgcolor='#f9f9f9' style=' text-indent:10px; line-height:22px; border-bottom:1px dotted #e2dfdf;'>"+orderId+"</td>"+
																	"</tr>"+
																	"<tr>"+
																		"<td bgcolor='#f9f9f9' style=' text-indent:10px; line-height:32px; border-bottom:1px dotted #e2dfdf;'>Order Date</td>"+
																		"<td bgcolor='#f9f9f9' style=' text-indent:10px; line-height:22px; border-bottom:1px dotted #e2dfdf;'>"+currentDate+"</td>"+
																	"</tr>"+
																	"<tr>"+
																		"<td bgcolor='#f9f9f9' style=' text-indent:10px; line-height:32px; border-bottom:1px dotted #e2dfdf;'>Delivery Date</td>"+
																		"<td bgcolor='#f9f9f9' style=' text-indent:10px; line-height:22px; border-bottom:1px dotted #e2dfdf;'>"+deliveryDate+"</td>"+
																	"</tr>"+
																"</tbody>"+
															"</table></td>"+
														"<td width='4%'>&nbsp;</td>"+
														"<td width='30%' valign='top'><b>Shipping Address :</b><br />"+
															getSelectedAdd()+"</td>"+
													"</tr>"+
												"</tbody>"+
											"</table></td>"+
										"<td width='4%'>&nbsp;</td>"+
									"</tr>"+
									"<tr>"+
										"<td width='4%'>&nbsp;</td>"+
										"<td width='92%' align='left' valign='middle' height='45px'><font style='font-family:'Trebuchet MS', Helvetica, Arial, sans-serif; color:#181818; font-size:12px; line-height:22px;'><b>ORDER SUMMARY</b></font></td>"+
										"<td width='4%'>&nbsp;</td>"+
									"</tr>"+
									"<tr>"+
									"<td width='4%'>&nbsp;</td>"+
									"<td style='font-family:'Trebuchet MS', Helvetica, Arial, sans-serif; color:#181818; font-size:12px; line-height:22px;'><table width='100%' cellspacing='0' cellpadding='0'>"+
											"<tbody>"+
											"<tr bgcolor='#f1efef'>"+
											"<th width='30px' style=' border:1px solid #dcdbdb; line-height:30px;'><b>Sl.</b></th>"+
											"<th width='190px' style=' border:1px solid #dcdbdb; line-height:30px;'><b>Product Name</b></th>"+
											"<th width='140px' style=' border:1px solid #dcdbdb; line-height:30px;'><b>Brand Name</b></th>"+
											"<th width='50px' style=' border:1px solid #dcdbdb; line-height:30px;'><b>Size</b></th>"+
											"<th width='30px' style=' border:1px solid #dcdbdb; line-height:30px;'><b>Qty</b></th>"+
											"<th width='70px' style=' border:1px solid #dcdbdb; line-height:30px;'><b>Unit Price</b></th>"+
											"<th width='90px' style=' border:1px solid #dcdbdb; line-height:30px;'><b>Total Price</b></th>"+
										"</tr>";
		
		
		int count = 0;
		double total = 0.0;
		Cookie c[] = request.getCookies();
		for(int i = 0;i<c.length; i++){
			Map parameters = new HashMap();			
			Cookie c1 = c[i];
			String allVal[] = c1.getValue().split("-");
			if(allVal.length >= 10 ){
				String imagePath = "";
				String arr[] = allVal[7].split("#");
				for(int x = 0;x<arr.length;x++){
					imagePath = imagePath + "/"+arr[x];
				}
				
				total = total + Double.parseDouble(allVal[2]);
				
				parameters.put("EMAILID", (String)session.get("loginId"));
				parameters.put("DELIVERY_ADDRESS", getSelectedAdd());
				parameters.put("SELECTED_SLOT", getAvailable_slots());
				parameters.put("DELIVERY_DATE", deliveryDate);
				parameters.put("PAYMENT_MODE","CASH");
				parameters.put("CART_ID",cartId);
				parameters.put("PRODUCT_ID",c1.getName());
				parameters.put("PRODUCT_NAME",allVal[5]);
				parameters.put("PRODUCT_BRAND_NAME",allVal[4]);
				//parameters.put("ORIGINAL_PRICE",allVal[5]);
				parameters.put("OFFER_PRICE",""+Float.parseFloat(allVal[2])/Integer.parseInt(allVal[3]));
				parameters.put("NO_OF_ITEM",allVal[3]);
				parameters.put("TOTAL_AMOUNT",allVal[2]);
				parameters.put("IMAGE_PATH",imagePath);
				parameters.put("PRODUCT_SIZE",allVal[0]);
				parameters.put("ORDERID", orderId);
				
				/*emailCart = emailCart +
				"<tr><td>"+allVal[5]+"</td><td>"+allVal[4]+"</td><td>"+allVal[0]+"</td><td>"+allVal[3]+"</td><td>"+
				Float.parseFloat(allVal[2])/Integer.parseInt(allVal[3])+"</td><td>"+allVal[2]+"</td></tr>";
				*/
				
				txt = txt + "<tr>"+
				"<td width='30px' style=' border:1px dotted #f1efef; padding:3px 0px;' align='center'>"+i+"</td>"+
				"<td width='190px' style=' border:1px dotted #f1efef; padding:3px 0px;' align='center'>"+allVal[5]+"</td>"+
				"<td width='140px' style=' border:1px dotted #f1efef; padding:3px 0px;' align='center'>"+allVal[4]+"</td>"+
				"<td width='50px' style=' border:1px dotted #f1efef; padding:3px 0px;' align='center'>"+allVal[0]+"</td>"+
				"<td width='30px' style=' border:1px dotted #f1efef; padding:3px 0px;' align='center'>"+allVal[3]+"</td>"+
				"<td width='70px' style=' border:1px dotted #f1efef; padding:3px 0px;' align='center'>Rs. "+
				Float.parseFloat(allVal[2])/Integer.parseInt(allVal[3])+"</td>"+
				"<td width='90px' style=' border:1px dotted #f1efef; padding:3px 0px;' align='center'>Rs. "+allVal[2]+"</td>"+
			"</tr>";
				
				boolean flag = objProductService.submitCart(connection, (String)session.get("loginId"), parameters );
				count++;
				c1.setMaxAge(0);
				response.addCookie(c1);
				
			}
		}
		

String deliveryCharge = request.getParameter("deliveryChargeHidden") != null && !request.getParameter("deliveryChargeHidden").equals("") ? request.getParameter("deliveryChargeHidden") : "0.0";
String discount = request.getParameter("discountHidden") != null && !request.getParameter("discountHidden").equals("") ? request.getParameter("discountHidden") : "0.0";
String amountToPay = request.getParameter("amountToPayHidden") != null && !request.getParameter("amountToPayHidden").equals("") ? request.getParameter("amountToPayHidden") : "0.0";
String couponCode = request.getParameter("couponCodeHidden") != null && !request.getParameter("couponCodeHidden").equals("") ? request.getParameter("couponCodeHidden") : "0.0";

Map values = new HashMap();
values.put("orderId", orderId);
values.put("userId", (String)session.get("loginId"));
values.put("deliveryCharge", deliveryCharge);
values.put("totalAmount", total);
values.put("discount", discount);
values.put("amountToPay", amountToPay);
values.put("couponCode", couponCode);
values.put("cartId", cartId);
		
	
boolean b = objProductService.submitcartSummary(connection, values);


		txt = txt + "<tr bgcolor='#f9f9f9' align='right'><td colspan='7' style=' padding:10px;'>"+
				"<b>Total: Rs "+total+"&nbsp;&nbsp;</b><br />"+
				"<b>Shipping Charges: Rs	"+request.getParameter("deliveryChargeHidden")+"&nbsp;&nbsp;</b><br />"+
				"<b>Discount: Rs "+discount+"</b><br />"+
				"<b>Net Payable: Rs "+amountToPay+"&nbsp;&nbsp;</b></td></tr>"+
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

		
		/*String totalRow = "<tr><td colspan='6' style='align:right'><b>Total : Rs. "+total+"</b></td></tr>";
		emailCart = emailCart + totalRow + "</table><br/><br/><br/>Thanks,<br/>BestKart4u Team.</body></html>";
		*/
		Map cartDetail = objProductService.getSubmitCartDetail(connection, (String)session.get("loginId"), cartId);
		
		request.setAttribute("productDetail", cartDetail);
		
		DBUtil.closeConnection(connection);
		
		
		
		
		final String to[] = {(String)session.get("loginId")};
		if(count > 0){
			EmailUtil.sendEmail(to, txt, "Your Order Detail");
		}
		return "success";
	}
	
	public String checkOutFromCart(){
		
		String from = request.getParameter("from");
		request.setAttribute("from", from);
		
		if(session != null && session.get("loginId") != null){
			return "GoToCart";
		}
		else{
			return "GoToLogin";
		}
		
	}
	
	public String checkCouponCode() throws IOException{
		
		PrintWriter out = response.getWriter();
		
		IProductService objProductService = new ProductServiceImpl();
		Connection connection = null;
		try{
			connection = DBConnection.getConnection();
			String couponCode = request.getParameter("couponCode");
			String cartTotalAmount = request.getParameter("cartTotalAmount");
			String couponCodeVal = objProductService.checkCouponCode(connection, couponCode,cartTotalAmount);
			out.print(couponCodeVal);
			
		}
		catch (Exception e) {
			logger.error("",e);
		}
		
		
		
		return "none";
	}
	
	
	@Override
	public void setSession(Map session) {
		this.session = session;
	}

	@Override
	public Object getModel() {
		
		return objDeliveryAddressBean;
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
