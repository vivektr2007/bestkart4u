<!doctype html>

<%@page import="org.apache.log4j.Logger"%>
<%@page import="com.retailshop.util.DBConnection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<html>

<head>

<meta charset="utf-8">

<meta http-equiv="X-UA-Compatible" content="IE-EmulateIE8">

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

<title>BestKart4U : Best online Store and Supermarket in India</title>

<link href="http://fonts.googleapis.com/css?family=Roboto:400,300"
	rel="stylesheet" type="text/css" />

<link href="http://fonts.googleapis.com/css?family=Lato:600,300,400,700"
	rel="stylesheet" type="text/css" />

<link
	href="http://fonts.googleapis.com/css?family=Roboto+Slab:700,500,400,300,100"
	rel="stylesheet" type="text/css" />
<link rel="icon" type="image/png" href="images/favicon.ico" />
<!--<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/themes/overcast/jquery-ui.min.css" type="text/css" media="all" />-->

<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="js/jquery-ui.min.js" type="text/javascript"></script>
<link rel="stylesheet"
	href="css/jquery-ui-autocomplete.css" />

<link href="css/stylesheet.css" rel="stylesheet" type="text/css" />

<!--[if lt IE 9]><link href="//d3oi2nue850v1c.cloudfront.net/static/uiv2/css/ie8styles.css" rel="stylesheet" type="text/css">

<script src="//d3oi2nue850v1c.cloudfront.net/static/uiv2/js/html5shiv.js"></script>

<![endif]-->

<%-- <script src="js/jquery.min.js" type="text/javascript"></script> --%>

<script src="js/jquery.cycle.js" type="text/javascript"></script>
<script src="js/common.js" type="text/javascript"></script>
<%-- <script src="js/jquery-ui.min.js" type="text/javascript"></script> --%>

<script src="js/uiv2_main.min.js" type="text/javascript"></script>



<script src="js/facets.min.js" type="text/javascript"></script>



<style type="text/css">
.uiv2-slider-wrapper {
	margin-bottom: 0;
}

.uiv2-header-navigation-links {
	border-bottom: 1px solid #d9d9d7;
}
</style>

<!-- Customised select box starts here -->

<link rel="stylesheet" type="text/css" href="css/select_custom.css" />

<script type="text/javascript" src="js/jquery.custom_radio_checkbox.js"></script>

<script type="text/javascript" language="javascript">
	$(document).ready(function() {

		$(".radio").dgStyle();

		$(".checkbox").dgStyle();

	});
</script>


<%-- <script type="text/javascript">

	$("#id_page_login_btn").click(function(){

		alert("ssss");

		var email = $("#uiv2-login-page-email").val();

				$.get(

					    "ForgetPassword.action",

					    {email:email},

					    function(data) { 

					    	 if(data == 'true' || data == true){

					    		alert("Your password has been reset successfully. PLease check your email.");

					    	}

					    	 else{

					    		 alert("there is some error");

					    	 }

					    	

				});

	});

</script> --%>





<!-- Customised Select Box Ends Here -->

<script src="js/cart.js" type="text/javascript"></script>

</head>

<body onLoad="getCookies();">

	<%!private static final Logger logger = Logger.getLogger("productList.jsp");%>

	<div id="shadow-mask">&nbsp;</div>



	<jsp:include page="header.jsp"></jsp:include>



	<div class="main_wrapper">

		<div class="uiv2-navigation-slider">

			<div class="uiv2-left-navigation">

				<div class="uiv2-nav uiv2-pointer" id="uiv2-menu-bar">
					SHOP <span class="uiv2-nav-icon"></span>
				</div>

				<!-- Navigation starts here -->



				<jsp:include page="productListInner.jsp"></jsp:include>



				<!-- Navigation ends here -->

			</div>



			<jsp:include page="menuPage.jsp"></jsp:include>





			<div class="uiv2-content-wrapper">

				<div class="uiv2-shopping-list-wrapper">
					<div class="all-store-heading">
						<h1>our complete store</h1>
					</div>

					<%
						Connection conn = null;
						Statement stmt = null;
						ResultSet rs = null;
						try {
							conn = DBConnection.getConnection();
							stmt = conn.createStatement();
							rs = stmt.executeQuery("select * from master_category ORDER BY master_category_name");
							if (rs.isBeforeFirst()) {
								while (rs.next()) {
									String categoryId = rs.getString("master_category_id");
									String categoryName = rs
											.getString("master_category_name");
					%>

					<div class="all-store-content">
						<div class="all-store-content-sub-heading">
							<h1>
								<a
									href="ViewProductsForcategory.action?rootCategory=<%=categoryId%>&startIndex=1&categoryName=<%=categoryName.replaceAll("&", ",,")%>"
									class="top-category"><%=categoryName%></a>
							</h1>
						</div>



						<div class="all-store-content-sub-content">

							<%
								Statement stmt1 = null;
											ResultSet rs1 = null;
											try {
												stmt1 = conn.createStatement();
												rs1 = stmt1
														.executeQuery("select * from sub_category1 where master_category_id = '"
																+ categoryId + "' order by sub_category_name");
												if (rs1.isBeforeFirst()) {
							%>

							<div class="all-store-content-sub-content-block">
								<%
									while (rs1.next()) {
															String subCategoryID = rs1
																	.getString("Sub_category_id");
															String subCategoryName = rs1
																	.getString("sub_category_name");
								%>
								<ul>
									<li class="title"><a href="javascript:void();"><%=subCategoryName%></a>
									</li>
									<%
										Statement stmt2 = null;
																ResultSet rs2 = null;
																try {
																	stmt2 = conn.createStatement();
																	rs2 = stmt2
																			.executeQuery("select * from sub_category2 where master_category_id = '"
																					+ categoryId
																					+ "' and sub_category_id1='"
																					+ subCategoryID + "' order by sub_category_desc");
																	if (rs2.isBeforeFirst()) {
																		while (rs2.next()) {
																			String subCategoryId2 = rs2
																					.getString("sub_category_id2");
																			String subCategoryName1 = rs2
																					.getString("sub_category_desc");
									%>

									<li><a
										href="ViewProducts.action?startIndex=1&categoryName=<%=subCategoryName1
														.replaceAll("&", ",,")%>&categoryId=<%=subCategoryId2%>&rootCategory=<%=categoryId%>&subRootCategory=<%=subCategoryID%>"><%=subCategoryName1%></a>
									</li>
									<%
										}
																	}
																} catch (Exception e) {
																	logger.error(
																			"Exception in getting subcategory 2 : ",
																			e);
																} finally {
																	rs2.close();
																	stmt2.close();
																}
									%>

								</ul>
								<%
									}
								%>
							</div>

							<%
								}
											} catch (Exception e) {
												logger.error(
														"Exception in getting subcategory 1 : ", e);
											} finally {
												rs1.close();
												stmt1.close();
											}
							%>

						</div>

					</div>


					<%
						}
							}
						} catch (Exception e) {
							logger.error("Exception in getting Category : ", e);
						} finally {
							rs.close();
							stmt.close();
							conn.close();
						}
					%>


				</div>

			</div>

		</div>

	</div>



	<div class="footer_wrapper">

		<jsp:include page="footer.jsp"></jsp:include>

	</div>

	<div class="uiv2-copyright">

		<p>
			Copyright &copy; 2014-<span id="current-year"></span> All rights
			reserved.
		</p>

	</div>

	<!-- Handle bar templates -->

	<script id="tmplt-autosearch" type="text/x-handlebars-template">

       {{#if data}}

        <ul id="uiv2-autosearch-list">

       {{/if}}

       {{#each data}}

            {{#if this.name}}

                <li>

                    {{#if this.simg}}

                    <a href="{{this.url}}?nc=as" class="uiv2-img-product-search"><img src="{{this.simg}}" alt="" height="30" width="30"></a>

                    {{else}}

                        <div class="uiv2-push-left" style="width:30px; height:30px;"></div>

                    {{/if}}

                    <a href="{{this.url}}?nc=as" class="uiv2-img-product-name"><p>{{this.brand}}</p><span>{{this.name}}</span></a><div class="uiv2-product-weight"><span>{{this.weight}}</span></div><div class="uiv2-product-cost"><span><span class="WebRupee">Rs.</span> {{this.price}}</span></div><div class="uiv2-rate-count-btn uiv2-search-qty-widget"><span class="uiv2-qty-label">Qty</span><input value="1" type="text" id="uiv2-qty-{{this.pid}}" maxlength="{{max_qty_length}}"></div><div class="uiv2-product-add-btn">

                        {{#ifCond this.availability  2}}

                        <img src="/static/images/searchOutOfStock.png" alt="Out of stock" style="width:60px; margin-top: 10px;" />

                        {{else}}

                        <input class="uiv2-a2c-autosearch" value="ADD" type="button" id="uiv2-a2c-{{this.pid}}">

                        {{/ifCond}}

                    </div><input type="hidden" id="uiv2-pdesc-{{this.pid}}" value="{{this.text}}" /></li>

            {{/if}}

       {{else}}

            <p class="uiv2-commonly-searched">Commonly Searched</p>

            {{#each top_searches}}

            <ul id="uiv2-autosearch-list" class="uiv2-top-searches">

            {{#each this}}

                <li><a href="/ps/?q={{this}}">{{{ this}}}</a></li>

            {{/each}}

            </ul>

            {{/each}}

       {{/each}}

       {{#if data}}

        </ul>

       {{/if}}

    </script>

	<script id="tmplt-cart-items" type="text/x-handlebars-template">

        {{#each items}}

            <li><div class="uiv2-items-img"><a href="javascript:void();"><img src="{{this.simg}}"  alt="{{this.description}}" width="65" height="61" ></a></div><div class="uiv2-items-content-wrapper"><div class="uiv2-items-content"><span><a href="javascript:void();"> {{this.brand}} </a></span><p><a href="javascript:void();">{{this.description}}</a></p></div><div class="uiv2-items-button-block"><div class="uiv2-grid-count-btn"><button class="icon icon-decrease-qty-search-popup">-</button><input type="text" class="text-change-qty-search-popup" id="p_{{this.pid}}" value="{{this.quantity}}" maxlength="{{max_qty_length}}" /><button class="icon icon-increase-qty-search-popup">+</button></div><span class="uiv2-items-rate"><span class="WebRupee">Rs.</span> {{this.mrp}}</span>

                        {{#if this.saving_int}}

                        <div class="uiv2-items-saved-rate"><span>SAVED</span><span><span class="WebRupee">Rs.</span> {{this.saving}}</span></div>

                        {{/if}}

                    </div></div><a href="javascript:void();" id="p_remove_{{this.pid}}" class="uiv2-remove-product"></a></li>

        {{else}}



            <li class="uiv2-cart-placeholder-msg"><p></p></li>



        {{/each}}

    </script>

	<!-- End Handle bar templates -->

</body>

</html>

