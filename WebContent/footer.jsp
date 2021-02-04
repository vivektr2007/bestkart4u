<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.retailshop.util.DBConnection"%>
<%@page import="com.retailshop.util.DBUtil"%>
<div class="newsletter-signup">
	<div class="signup-news">
		<h4>Sign Up For Newsletter</h4>
		<input type="text" name="" value="Enter your email address" id="subscriberEmail" autocomplete="off" onblur="javascript:if(this.value=='') this.value = 'Enter your email address';" onfocus="javascript:if(this.value=='Enter your email address') this.value = '';" />
		<input type="button" name=""  value="subscribe" id="subscribe" onclick="subscribe()"/>
	</div>
	<div class="signup-social">
		<h4>Follow Us</h4>
		<ul>
			<li>
			
			 <!-- <div class="fb-like" data-href="https://www.facebook.com/bestkart4u?ref=br_tf" data-layout="button" data-action="like" data-show-faces="true" ></div>
			  -->
			  <div class="fb-like" data-href="https://www.facebook.com/bestkart4u" data-layout="box_count" data-action="like" data-show-faces="true" data-share="false"></div>
			<!-- <a data-action="like" href="https://www.facebook.com/bestkart4u?ref=br_tf" data-show-faces="true" class="fb-like">&nbsp;</a>  --></li>
			<li><a href="https://twitter.com/bestkart4u" target="_blank" rel="nofollow" class="twitter">&nbsp;</a></li>
			<li><a href="https://plus.google.com/u/1/102684221643482026771/" target="_blank" rel="nofollow" class="gplus">&nbsp;</a></li>
		</ul>
	</div>
</div>
<div class="uiv2-footer-wrap uiv2-clear-b">
		<div class="uiv2-footer-list-wrapper">
			<div class="uiv2-footer-colum"><span>Popular Brands</span>
				<ul>
					<!-- <li class="allBrands"><a href="javascript:void();">ALL BRANDS</a></li>
					 -->
					<li><a href="ViewProductsForBrand.action?searchStartIndex=1&categoryName=&NATURE FRESH&brandName=NATURE FRESH&from=footer">NATURE FRESH</a></li>
					<li><a href="ViewProductsForBrand.action?searchStartIndex=1&categoryName=&BRITANNIA&brandName=BRITANNIA&from=footer">BRITANNIA</a></li>
					<li><a href="ViewProductsForBrand.action?searchStartIndex=1&categoryName=&MDH&brandName=MDH&from=footer">MDH</a></li>
					<li><a href="ViewProductsForBrand.action?searchStartIndex=1&categoryName=&NESTLE&brandName=NESTLE&from=footer">NESTLE</a></li>
				</ul>
			</div>
			<div class="uiv2-footer-colum"><span>Popular Categories</span>
				<ul>
				<%
				Connection conn = null;
				Statement stmt = null;
				ResultSet rs = null;
				try{
					conn = DBConnection.getConnection();
					stmt = conn.createStatement();
					rs = stmt.executeQuery("select * from master_category");
					if(rs.isBeforeFirst()){
					while(rs.next()){
						String categoryId = rs.getString("master_category_id");
						String categoryName = rs.getString("master_category_name");
					
				%>
					<li><a href="ViewProductsForcategory.action?rootCategory=<%=categoryId%>&startIndex=1&categoryName=<%=categoryName.replaceAll("&", ",,")%>" class="top-category"><%=categoryName %></a></li>
					
				<%
					}
					}
				}
				catch(Exception e){
					
				}
				finally{
					DBUtil.closeResultSet(rs);
					DBUtil.closeStatement(stmt);
					DBUtil.closeConnection(conn);
				}
				%>
				</ul>
			</div>
			<div class="uiv2-footer-colum"><span>Information &amp; Services</span>
				<ul>
					<li><a href="aboutUs.jsp">About us</a></li>
					<!-- <li><a href="javascript:void();">Careers at BestKart4U</a></li> -->
					<li><a href="privacyPolicy.jsp">Privacy Policy</a></li>
					<li><a href="terms-and-conditions.jsp">Terms &amp; Conditions</a></li>
				</ul>
			</div>
			<div class="uiv2-footer-colum">
				<p>If you have a question about our service or have an issue to report</p>
				<ul class="uiv2-contact">
					<li><a href="mailto:support@bestkart4u.com">Write to us</a></li>
					<!-- <li><a href="javascript:void();">FAQs</a></li> -->
					<li><a href="contacts.jsp">Contact Us</a></li>
				</ul>
				<div class="uiv2-social-btn-container">
					<div class="uiv2-follow-us">Follow us</div>
					<div class="uiv2-social">
						<div class="icon fb"><a id="fb_a" href="https://www.facebook.com/bestkart4u/timeline" target="_blank" rel="nofollow"></a></div>
						<div class="icon tw"><a id="twit_a" href="https://twitter.com/bestkart4u" target="_blank" rel="nofollow"></a></div>
					</div>
				</div>
			</div>
		</div>
		<div class="uiv2-footer-bb-desc"><span> Started in 2014, <a href="index.jsp">BestKart4U.com</a> is present in Dwarka(New Delhi). <a href="index.jsp">BestKart4U.com</a> offers a wide variety of products across various categories including: Fresh Fruits &amp; Vegetables, Grocery and Staples, Beverages, Confectionery &amp; Dairy products, Fresh Meats, Personal Care and Household products among others.</span></div>
	</div>
	<a href="javascript:void();" class="move-to-top">Top</a>

	<script type="text/javascript">            
	jQuery(document).ready(function() {
		var offset = 220;
		var duration = 500;
		jQuery(window).scroll(function() {
			if (jQuery(this).scrollTop() > offset) {
				jQuery('.move-to-top').fadeIn(duration);
			}else{
				jQuery('.move-to-top').fadeOut(duration);
			}
		});
						
		jQuery('.move-to-top').click(function(event) {
			event.preventDefault();
			jQuery('html, body').animate({scrollTop: 0}, duration);
			return false;
		})
	});
</script>
</td>
			<td width="30%">&nbsp;</td>
		</tr>
	</tbody>
</table>