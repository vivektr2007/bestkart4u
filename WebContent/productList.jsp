<%@page import="com.retailshop.util.GeneralUtil"%>
<%@page import="java.net.InetAddress"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.retailshop.util.DBConnection"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%!
	private static final Logger logger = Logger.getLogger("productList.jsp");
%>
<nav id="uiv2-main-menu">
				<ul>
				<%
				Connection conn = null;
				Statement stmt = null;
				ResultSet rs = null;
				Statement logUserStmt = null;
				try{
					conn = DBConnection.getConnection();
					/* InetAddress IP=InetAddress.getLocalHost();
					String IPAddress = GeneralUtil.getClientIpAddr(request);
					logUserStmt = conn.createStatement();
					logUserStmt.executeUpdate("insert into user_access_log(canonical_host_name, host_name,user_ip, access_time) values('"+IP.getCanonicalHostName()+"','"+IP.getHostName()+"','"+IP.getHostAddress()+"',now())");
					 */
					stmt = conn.createStatement();
					rs = stmt.executeQuery("SELECT * FROM master_category ORDER BY master_category_name LIMIT 0 , 9");
					if(rs.isBeforeFirst()){
					while(rs.next()){
						String categoryId = rs.getString("master_category_id");
						String categoryName = rs.getString("master_category_name");
					
				%>
					<li class="normal"><a href="ViewProductsForcategory.action?rootCategory=<%=categoryId%>&startIndex=1&categoryName=<%=categoryName.replaceAll("&", ",,")%>" class="top-category"><%=categoryName %><span class="new">&nbsp;</span></a>
						
						<%
										Statement stmt1 = null;
										ResultSet rs1 = null;
										try{
											stmt1 = conn.createStatement();
											rs1 = stmt1.executeQuery("select * from sub_category1 where master_category_id = '"+categoryId+"' order by sub_category_name");
											if(rs1.isBeforeFirst()){
										%>
						<div class="uiv2-dropdown-box">
							<div class="uiv2-dropdown-inner">
								<div class="uiv2-dp-heading"><a href="javascript:void();"><%=categoryName %></a></div>
								<div class="uiv2-dropdown-column">
									
										<!-- <li><a href="javascript:void();">Cut Vegetables</a></li> -->
										
										<ul>
										<%		
											while(rs1.next()){
											String subCategoryID = rs1.getString("Sub_category_id");
											String subCategoryName = rs1.getString("sub_category_name"); 
											 %>	
										<li class="uiv2-submenu"> <span class="uiv2-white-bg"></span> <a href="javascript:void();"><%=subCategoryName %> <img alt="arrow" src="images/ArrowOrange.png" /></a>
											<%
											Statement stmt2 = null;
											ResultSet rs2 = null;
												try{
													stmt2 = conn.createStatement();
													rs2 = stmt2.executeQuery("select * from sub_category2 where master_category_id = '"+categoryId+"' and sub_category_id1='"+subCategoryID+"' order by sub_category_desc");
													if(rs2.isBeforeFirst()){
													%>
											<div class="uiv2-sub-dropdown-column">
												<ul>
												<%
													while(rs2.next()){
														String subCategoryId2 = rs2.getString("sub_category_id2");
														String subCategoryName1 = rs2.getString("sub_category_desc");
													%>
													
													<li>
													<a href="ViewProducts.action?startIndex=1&categoryName=<%=subCategoryName1.replaceAll("&", ",,") %>&categoryId=<%=subCategoryId2%>&rootCategory=<%=categoryId%>&subRootCategory=<%=subCategoryID%>"><%=subCategoryName1 %></a>
													</li>
													<%
													}
												%>
												</ul>
											</div>
												<%
												}
													%>
												
											 <%
												}
												catch(Exception e){
													logger.error("Exception in getting subcategory 2 : ", e);
												}
												finally{
													rs2.close();
													stmt2.close();
												}
													 %>
										</li>
										<%
														}
										%>
										</ul>
										
									
								</div>
								<!-- <div class="uiv2-dropdown-column"><span>Popular Brands</span>
									<ul>
										<li><a href="javascript:void();">Fresho</a></li>
										<li><a href="javascript:void();">Gopalan Organic</a></li>
										<li><a href="javascript:void();">Ambrosia</a></li>
										<li><a href="javascript:void();">Cookup</a></li>
										<li><a href="javascript:void();">TVS Organics</a></li>
										<li><a href="javascript:void();">First Agro</a></li>
									</ul>
								</div> -->
							</div>
							</div>
							
							<%
										}
										}catch(Exception e){
											logger.error("Exception in getting subcategory 1 : ", e);
										}
										finally{
											rs1.close();
											stmt1.close();
										}
										%>
							
							
							<!-- <a class="uiv2-left-nav-category-img" href="javascript:void();" style="width: 560px; height: 400px; background: url('images/cut_veggies1.png') no-repeat right bottom;"></a> </div> -->
					</li>					 <%
					}
				}
				}
				catch(Exception e){
					logger.error("Exception in getting Category : ", e);
				}
				finally{
					rs.close();
					stmt.close();
					conn.close();
					logUserStmt.close();
				}
					 %>
				</ul>
				<a href="complete-shop.jsp" class="uiv2-view-complete-link">VIEW COMPLETE SHOP</a>
				</nav>

</body>
</html>