<!doctype html>

<%@page import="java.util.*"%>

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
	href="http://fonts.googleapis.com/css?family=Roboto+Slab:700,500,400,300,100" rel="stylesheet" type="text/css" />
<link rel="icon" type="image/png" href="images/favicon.ico" />
<!--<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/themes/overcast/jquery-ui.min.css" type="text/css" media="all" />-->

<link href="css/stylesheet.css" rel="stylesheet" type="text/css" />

<!--[if lt IE 9]><link href="//d3oi2nue850v1c.cloudfront.net/static/uiv2/css/ie8styles.css" rel="stylesheet" type="text/css">

<script src="//d3oi2nue850v1c.cloudfront.net/static/uiv2/js/html5shiv.js"></script>

<![endif]-->

<%-- <script src="js/jquery.min.js" type="text/javascript"></script>
 --%>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="js/jquery-ui.min.js" type="text/javascript"></script>
<link rel="stylesheet" href="css/jquery-ui-autocomplete.css" />


<script src="js/jquery.cycle.js" type="text/javascript"></script>
<%-- 
<script src="js/jquery-ui.min.js" type="text/javascript"></script>
 --%>
 
 
<script src="js/uiv2_main.min.js" type="text/javascript"></script>

<script type="text/javascript">
	$(document).ready(
			function() {

				init_a2c(25);

				init_view_basket('25', 1);

				init_display_autovoucheroffers();

				init_close_autovoucheroffer_dialog();

				parse_utm_cookies();

				on_register_free_delivery_token_dialog();

				// Resize banner bars

				var banner_ele = $(".uiv2-latest-basket-link");

				banner_ele.hover(function() {

					$(this).banner_resize(".uiv2-img-nav-banner",
							".uiv2-banner-caption", 20);

				});

				banner_ele.click(function() {

					$(this).banner_resize(".uiv2-img-nav-banner",
							".uiv2-banner-caption", 20);

				});

				var script = document.createElement("script");

				script.type = "text/javascript";

				script.src = "http://cdn.webrupee.com/js";

				document.getElementsByTagName("head")[0].appendChild(script);

				Handlebars.registerHelper('ifCond', function(v1, v2, options) {

					if (v1 === v2) {

						return options.fn(this);

					}

					return options.inverse(this);

				});

			});
</script>

<script src="js/facets.min.js" type="text/javascript"></script>

<script src="js/cart.js" type="text/javascript"></script>
<script src="js/common.js" type="text/javascript"></script>


<style type="text/css">
.uiv2-slider-wrapper {
	margin-bottom: 0;
}

.uiv2-header-navigation-links {
	border-bottom: 1px solid #d9d9d7;
}


</style>



<!-- Customised select box starts here -->

<%-- 

<link rel="stylesheet" type="text/css" href="css/select_custom.css" />

<script type="text/javascript" src="js/jquery.custom_radio_checkbox.js"></script> --%>

<%-- <script type="text/javascript" language="javascript">

	$(document).ready(function(){

		$(".radio").dgStyle();

		$(".checkbox").dgStyle();

	});

</script> --%>



<%-- <script type="text/javascript">

	$(function(){

		$('select.styled').customSelect();

	});

</script> --%>

<script type="text/javascript">
	function searchForBrand() {
		
		document.searchForm.submit();

	}
	
	function searchForBrandPagination(value){
		$("#searchStartIndex").val(value);
		document.searchForm.submit();
	}
	
</script>

</head>

<body onLoad="getCookies()">

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

				<div class="uiv2-content-wrapper">

					<div class="sysMessage"></div>

					<div class="uiv2-shopping-list-bredcom">

						<%-- <div class="breadcrumb-item"><a href="javascript:void();"><span>HOME</span></a>

						<label>&gt;</label>

					</div>

					<div class="breadcrumb-item"><a href="javascript:void();"><span>Grocery &amp; Staples</span></a>

						<label>&gt;</label>

					</div>

					<div class="breadcrumb-item"><a href="javascript:void();"><span>Dals &amp; Pulses</span></a></div> --%>

					</div>

					<div class="uiv2-shopping-list-wrapper">

						<!--Left Column -->

						<div class="uiv2-shopping-list-left-column">

							<div class="uiv2-facet-categories">

								<ul>

									<%
									String subRootCategory = (String) request.getAttribute("subRootCategory");
									String categoryName = ((String) request.getAttribute("categoryName")).replaceAll("&", ",,");
							
										String rootCategory = (String) request.getAttribute("rootCategory");

										String categoryId = (String) request.getAttribute("categoryId");

										Map sideBarProducts = (Map) request.getAttribute("sideBarProducts");
										if(sideBarProducts != null && sideBarProducts.size() > 0){
										Set sideBarSet = sideBarProducts.entrySet();

										Iterator sideBarProductsItr = sideBarSet.iterator();

										while (sideBarProductsItr.hasNext()) {

											Map.Entry mapEntry = (Map.Entry) sideBarProductsItr.next();

											String sideBarKey = (String) mapEntry.getKey();

											String sibeBarArr[] = sideBarKey.split("#");

											String subCatId = sibeBarArr[0];
									%>

									<li><a
										href="GetListFromSideBar.action?startIndex=1&categoryName=<%=sibeBarArr[1].replaceAll("&", ",,")%>&rootCategory=<%=rootCategory%>&subRootCategory=<%=subCatId%>"
										class="active"><label>&gt;</label>&nbsp;&nbsp;<B><%=sibeBarArr[1]%></B>
											<span class="count"></span>
									</a>

										<ul>

											<%
												ArrayList sideBarP = (ArrayList) mapEntry.getValue();

													Iterator it = sideBarP.iterator();

													while (it.hasNext()) {

														String sideBarProductsArr[] = (String[]) it.next();

														String subChldId = sideBarProductsArr[0];
											%>

											<li><a
												href="GetListFromSideBar.action?startIndex=1&categoryName=<%=sideBarProductsArr[2].replaceAll("&", ",,")%>&rootCategory=<%=rootCategory%>&subRootCategory=<%=subCatId%>&categoryId=<%=subChldId%>"><label>&gt;</label>&nbsp;&nbsp;<%=sideBarProductsArr[2]%>
													<span class="count">(<%=sideBarProductsArr[1]%>)</span>
											</a>
											</li>

											<%
												}
											%>



										</ul></li>

									<%
										}
										}
									%>

								</ul>

							</div>

							<!-- <div class="uiv2-refine-by">

							<h4>Refine By</h4>

							<div class="facetsList">

								<ul>

									<li>

										<div class="selection"><div class="checkbox" style="background-position: center -50px;"><input type="checkbox" value="" name=""></div></div>

										<div class="selection_text">Products on Offer</div>

									</li>

								</ul>

							</div>

						</div> -->

							<%-- <div class="uiv2-brands">

							<h4>Brands</h4>

							<div class="uiv2-brands_Checkbox">

								<input type="text" id="filter_brands">

								<input type="button">

								<script type="text/javascript">

                    				$("#filter_brands").keyup(function(){

										filter_facets("filter_brands"); 

									});

                				</script>

							</div>

							<div class="uiv2-brands_Listing">

								<ul id="filter_brands_list">

									<div class="jspContainer" style="width: 150px;">

										<div class="jspPane" style="padding: 0px; top: 0px; width: 150px;">

											<li class="filter_brands_item">

												<div class="selection"><div class="checkbox" style="background-position: center -50px;"><input type="checkbox" value="" name=""></div></div>

												<label class="label">Bb popular</label>

											</li>

											<li class="filter_brands_item">

												<div class="selection"><div class="checkbox" style="background-position: center -50px;"><input type="checkbox" value="" name=""></div></div>

												<label class="label">Bb popular</label>

											</li>

											<li class="filter_brands_item">

												<div class="selection"><div class="checkbox" style="background-position: center -50px;"><input type="checkbox" value="" name=""></div></div>

												<label class="label">Manna </label>

											</li>

											<li class="filter_brands_item">

												<div class="selection"><div class="checkbox" style="background-position: center -50px;"><input type="checkbox" value="" name=""></div></div>

												<label class="label">Nutrela</label>

											</li>

											<li class="filter_brands_item">

												<div class="selection"><div class="checkbox" style="background-position: center -50px;"><input type="checkbox" value="" name=""></div></div>

												<label class="label">Safe harvest</label>

											</li>

											<li class="filter_brands_item">

												<div class="selection"><div class="checkbox" style="background-position: center -50px;"><input type="checkbox" value="" name=""></div></div>

												<label class="label">Shivling </label>

											</li>

											<li class="filter_brands_item">

												<div class="selection"><div class="checkbox" style="background-position: center -50px;"><input type="checkbox" value="" name=""></div></div>

												<label class="label">Sri bhagyalakshmi</label>

											</li>

										</div>

									</div>

								</ul>

							</div>

						</div> --%>

						<div class="uiv2-price-listing">

							<h4>Brand Wise Search</h4>

							<form action="ViewProductsForBrand.action" name="searchForm" method="post">
							
							<input type="hidden" name="searchRootCategory" id="searchRootCategory" value="<%=rootCategory%>" /> 
							<input type="hidden" name="searchSubRootCategory" id="searchSubRootCategory" value="<%=subRootCategory%>" />
							<input type="hidden" name="searchChildCategory" id="searchChildCategory" value="<%=categoryId%>" />
							
							<%
							
							String startIndexForSearch = request.getAttribute("startIndex") != null ? (String)request
									.getAttribute("startIndex") : "1";
							%>
							
							<input type="hidden" name="searchStartIndex" id="searchStartIndex" value="<%=startIndexForSearch%>">
							<input type="hidden" name="categoryName" id="searchCategoryName" value="<%=categoryName%>">
								<ul id="filter_prices_list">
								<%
									ArrayList allBrand = (ArrayList)request.getAttribute("allBrandList");
									String allSelectedBrand = request.getAttribute("allSelectedBrand") != null ? request.getAttribute("allSelectedBrand").toString() : "";
									if(allBrand != null && allBrand.size() > 0){
										
										Iterator allBrandIterator = allBrand.iterator();
										while(allBrandIterator.hasNext()){
											String brandName = (String)allBrandIterator.next();
											if(allSelectedBrand.indexOf(brandName) != -1){
								%>
								
									<li class="filter_prices_item">
										<div class="selection"><div class="checkbox" style="background-position: center -50px;"><input onClick="searchForBrand()" id="brandName" type="checkbox" checked="checked" value="<%=brandName %>" name="brandName"></div></div>
										<label class="label"><%-- <span class="WebRupee">Rs</span>  --%><%=brandName %> </label>
									</li>
									<%
											}
											else{
											%>
									<li class="filter_prices_item">
										<div class="selection"><div class="checkbox" style="background-position: center -50px;"><input onClick="searchForBrand()" id="brandName" type="checkbox" value="<%=brandName %>" name="brandName"></div></div>
										<label class="label"><%-- <span class="WebRupee">Rs</span>  --%><%=brandName %> </label>
									</li>
									
									
									
									<% 	
											}
										}
									}
									%>									
								</ul>

							</form>

						</div>

							<%-- <div class="uiv2-price-listing">

							<h4>Pack Size</h4>

							<div class="uiv2-brands_Checkbox">

								<input type="text" placeholder="Search pack sizes" id="filter_packsizes">

								<input type="button">

								<script type="text/javascript">

									$("#filter_packsizes").keyup(function(){ filter_facets("filter_packsizes"); });

								</script>

							</div>

							<div class="uiv2-brands_Listing">

								<ul id="filter_packsizes_list" class="jspScrollable" tabindex="0" style="overflow: hidden; padding: 0px; width: 150px;">

									<div class="jspContainer" style="width: 150px;">

										<div class="jspPane" style="padding: 0px; width: 145px; top: 0px;">

											<li class="filter_packsizes_item">

												<div class="selection"><div class="checkbox" style="background-position: center -50px;"><input type="checkbox" value="" name=""></div></div>

												<label class="label">200 gm Carton (3)</label>

											</li>

											<li class="filter_packsizes_item">

												<div class="selection"><div class="checkbox" style="background-position: center -50px;"><input type="checkbox" value="" name=""></div></div>

												<label class="label">200 gm Carton Carton (1)</label>

											</li>

											<li class="filter_packsizes_item">

												<div class="selection"><div class="checkbox" style="background-position: center -50px;"><input type="checkbox" value="" name=""></div></div>

												<label class="label">200 gm Pouch (14)</label>

											</li>

											<li class="filter_packsizes_item">

												<div class="selection"><div class="checkbox" style="background-position: center -50px;"><input type="checkbox" value="" name=""></div></div>

												<label class="label">200 gm Tetrapack (1)</label>

											</li>

											<li class="filter_packsizes_item">

												<div class="selection"><div class="checkbox" style="background-position: center -50px;"><input type="checkbox" value="" name=""></div></div>

												<label class="label">500 gm Pouch (31)</label>

											</li>

											<li class="filter_packsizes_item">

												<div class="selection"><div class="checkbox" style="background-position: center -50px;"><input type="checkbox" value="" name=""></div></div>

												<label class="label">1 Kg Pouch (1)</label>

											</li>

											<li class="filter_packsizes_item">

												<div class="selection"><div class="checkbox" style="background-position: center -50px;"><input type="checkbox" value="" name=""></div></div>

												<label class="label">1 kg Pouch (14)</label>

											</li>

											<li class="filter_packsizes_item">

												<div class="selection"><div class="checkbox" style="background-position: center -50px;"><input type="checkbox" value="" name=""></div></div>

												<label class="label">2 kg Pouch (2)</label>

											</li>

											<li class="filter_packsizes_item">

												<div class="selection"><div class="checkbox" style="background-position: center -50px;"><input type="checkbox" value="" name=""></div></div>

												<label class="label">5 kg Pouch (1)</label>

											</li>

										</div>

									</div>

								</ul>

							</div>

						</div> --%>

							<!-- <div class="uiv2-price-listing">

							<h4>Discounts</h4>

							<ul>

								<li>

									<div class="selection"><div class="checkbox" style="background-position: center -50px;"><input type="checkbox" value="" name=""></div></div>

									<label class="label">Upto 5% (1)</label>

								</li>

								<li>

									<div class="selection"><div class="checkbox" style="background-position: center -50px;"><input type="checkbox" value="" name=""></div></div>

									<label class="label">5% - 10% (8)</label>

								</li>

								<li>

									<div class="selection"><div class="checkbox" style="background-position: center -50px;"><input type="checkbox" value="" name=""></div></div>

									<label class="label">10% - 15% (7)</label>

								</li>

								<li>

									<div class="selection"><div class="checkbox" style="background-position: center -50px;"><input type="checkbox" value="" name=""></div></div>

									<label class="label">15% - 25% (2)</label>

								</li>

								<li>

									<div class="selection"><div class="checkbox" style="background-position: center -50px;"><input type="checkbox" value="" name=""></div></div>

									<label class="label">More than 25% (7)</label>

								</li>

							</ul>

						</div> -->

							<!-- <div class="uiv2-price-listing">

							<h4>Product Availability</h4>

							<ul>

								<li>

									<div class="selection"><div class="checkbox" style="background-position: center -50px;"><input type="checkbox" value="" name=""></div></div>

									<label class="label">Exclude out of stocks</label>

								</li>

							</ul>

						</div> -->

						</div>

						<!--Left Column -->

						<%
							Map<String, ArrayList<String>> values = (Map<String, ArrayList<String>>) request.getAttribute("productList");
							int total = values.size();
							int displaySize = 12;
							
							//int startIndex = (Integer.parseInt(request.getParameter("startIndex"))-1) * displaySize;
							String startIndexVal = request.getParameter("startIndex") != null ? request
									.getParameter("startIndex") : (String) request
									.getAttribute("startIndex");
							int startIndex = (Integer.parseInt(startIndexVal) - 1)* displaySize;
							
							 int count = (Integer) request.getAttribute("productCount");
							int pageNum = count / displaySize;
							if (count > pageNum * displaySize) {
								pageNum = pageNum + 1;
							}
							int selectedIndex = (Integer.parseInt(request.getAttribute("startIndex").toString()));
							int endIndex = (selectedIndex - 1) * displaySize + displaySize;
							if (endIndex > count) {
								endIndex = count;
							}
							int startIndexShow = 0;
							if (selectedIndex == 1) {
								startIndexShow = 1;
							} else {
								startIndexShow = (selectedIndex - 1) * displaySize + 1;
							}
							if (endIndex == 0) {
								startIndexShow = 0;
							}
						%>
						<input type="hidden" name="rootCategory" id="rootCategory"
							value="<%=rootCategory%>" /> <input type="hidden" name="type"
							id="type" value="productsForBrandSearch"> <input type="hidden"
							name="subRootCategory" id="subRootCategory"
							value="<%=subRootCategory%>" /> <input type="hidden"
							name="childCategory" id="childCategory" value="<%=categoryId%>" />
						<input type="hidden" name="categoryName" id="categoryName"
							value="<%=categoryName.replaceAll("&", ",,")%>"> <input type="hidden"
							name="startIndex" id="startIndex" value="<%=startIndexVal%>">
						<input type="hidden" name="type" id="type" value="products">
						<div class="uiv2-shopping-list-right-column">
							<div class="uiv2-title-breads-wrap">
								<h4>
								<%=request.getAttribute("categoryName") != null ? request.getAttribute("categoryName").toString().replaceAll(",,", "&") : "" %>
									
									(<span id="uiv2-num-products"><s:property
											value="productCount" />
									</span>)
								</h4>

							</div>

							<div class="paginationArea">

								<div class="noItems">
									Showing <span class="bFont"><%=startIndexShow%> - <%=endIndex%></span>
									of <span class="bFont"><%=count%> products</span>
								</div>

								<%-- <div class="sortOptions">

								<select>

									<option value="popularity" selected="selected">Popularity</option>

									<option value="pricelth">Price - Low to High</option>

									<option value="pricehtl">Price - High to Low</option>

									<option value="alpha">Alphabetical</option>

									<option value="offers">Offers</option>

								</select>

							</div>

							 --%>
								<div class="pagination">

									<ul class="pages">



										<%
											for (int x = 1; x <= pageNum; x++) {

												if (x == selectedIndex) {
										%>

										<li><a href="javascript:void();" onclick="searchForBrandPagination('<%=x%>')" class="selected"><%=x%></a>
										</li>&nbsp;

										<%
											}

												else {
										%>

										<li><a href="javascript:void();" onclick="searchForBrandPagination('<%=x%>')"><%=x%></a>
										</li>&nbsp;

										<%
											}

											}
										%>

										<%-- if(pageNum>4){

									%>

									<li><a href="ViewProductsPagination.action?categoryName=<%=categoryName %>&startIndex=3&categoryId=<%=categoryId %>" class="num">Next</a></li>

									<%

									}

									%> --%>

									</ul>

								</div>

								<div class="pageTop"></div>

							</div>

							<div id="filter-bar-container"></div>

							<div class="uiv2-div100" id="facet-products-wrapper">

								<div id="facet-loading-wrap">

									<div id="facet-loading">
										<img src="images/loading_brown.gif" /><span>Loading...</span>
									</div>

									<div id="facet-loading-mask"></div>

								</div>

								<!-- end facet-loading-wrap -->

								<div id="products-container">

									<div class="uiv2-shopping-lis-sku-cards">

										<ul class="uiv2-our-recommendations-list-box">

											<%-- <s:iterator value="productList" id="product">

										<s:set name="productInfo" value="product"/>

										<s:property value="#productInfo[0]"/>

									 --%>
											<%
												Set keys = values.entrySet();
												Iterator itr = keys.iterator();

												while (itr.hasNext()) {

													/* for(int index = startIndex;index<endIndex;index++){ */

													Map.Entry<String, ArrayList<String>> me = (Map.Entry<String, ArrayList<String>>) itr
															.next();

													ArrayList al = (ArrayList) me.getValue();

													double saveValue = Double.parseDouble((String) al.get(3))
															- Double.parseDouble((String) al.get(4));

													String imagePath = (String) al.get(5);

													String key = me.getKey();

													String alreadyInBasket = "1";

													String alreadyInBasketView = "1";

													ArrayList<String[]> priceDetail = (ArrayList<String[]>) al
															.get(6);

													Cookie c[] = request.getCookies();

													for (int cookieIndex = 0; cookieIndex < c.length; cookieIndex++) {

														String cookieName = c[cookieIndex].getName();

														if (al.get(0).equals(cookieName)) {

															String allValues[] = (c[cookieIndex].getValue())
																	.split("-");

															String quan = allValues[3];

															alreadyInBasketView = quan;

															alreadyInBasket = quan;

															break;

														}

													}
											%>

											<%-- <s:iterator id="pInfo" value="#productInfo">

										<s:property value="#pInfo[0]"/>

										 --%>

											<li>

												<div class="uiv2-combo-block">

													<span class="icon icon-save"></span> <span>SAVE <span
														class="WebRupee">Rs.</span><span id='<%="saveValueShow" + al.get(0)%>'> <%=saveValue%></span></span>

												</div>

												<div class="uiv2-list-box-img-block">

													<a
														href="ProductsDetail.action?productId=<%=al.get(0)%>&subRootCategory=<%=subRootCategory%>&rootCategory=<%=rootCategory%>"
														title=""><img title="" alt="" width="150" height="200"
														src="<%=imagePath%>" />
													</a>

												</div>

												<div class="uiv2-list-box-img-title">

													<span class="uiv2-title-tool-tip"> <a
														href="javascript:void();"><span
															class="uiv2-brand-title"> <input type="hidden"
																name="" value="<%=al.get(2)%>"
																id='<%="brandName" + al.get(0)%>' /> <input type="hidden"
																name="" value="<%=al.get(1)%>"
																id='<%="productName" + al.get(0)%>' /> <input
																type="hidden" name="" value="<%=imagePath%>"
																id='<%="imagePath" + al.get(0)%>' /> <input type="hidden"
																name="" value="<%=saveValue%>"
																id='<%="saveValue" + al.get(0)%>' /> <%=al.get(2)%></span> <%=al.get(1)%>
													</a> <span class="uiv2-tool-tip-hover "><%=al.get(2)%>
															&nbsp;&nbsp; <%=al.get(1)%></span>
													</span>

												</div>

												<div class="uiv2-field-wrap mt10">

													<%
														if(priceDetail.size() >1){
													%>
													<select onchange="changeSaveValue('<%=al.get(0) %>')" name='<%="dropDown" + al.get(0)%>'
														id='<%="dropDown" + al.get(0)%>'>
														<%
															for (int i = 0; i < priceDetail.size(); i++) {
																	String arr[] = (String[]) priceDetail.get(i);
																	String val = arr[0] + " - " + arr[2] + " Rs";
																	if(arr[2].equalsIgnoreCase(((String)al.get(4)))){
														%>
														<option selected="selected" offerprice="<%=arr[1]%>" value="<%=val%>"><%=val%>
														</option>
														<%
															}
																	else{
																		%>		
																		<option offerprice="<%=arr[1]%>" value="<%=val%>"><%=val%>
															</option>
																		<%
																	}
															}
														%>
													</select>
													<%
														}
														else{
															String arr[] = (String[]) priceDetail.get(0);
															String val = arr[0] + " - " + arr[2] + " Rs";
													%>
														<input type="hidden" offerprice="<%=arr[1]%>" value="<%=val%>" name='<%="dropDown" + al.get(0)%>' id='<%="dropDown" + al.get(0)%>'/>
													
														<%=val%>

													<%
														}
													%>

												</div>

												<div class="uiv2-list-box-drop-rate-block">

													<div class="ListBox_Rate_count">

														<div class="Rate_count_low">
															MRP: <span><span class="WebRupee">Rs.</span>
															<span id='<%="actualPriceShow" + al.get(0)%>' ><%=al.get(3)%></span></span>
														</div>

														<div class="uiv2-rate-count-avial">
															<span class="WebRupee">Rs.</span><input type="hidden"
																id='<%="offerPrice" + al.get(0)%>' value="<%=al.get(4)%>"
																name='<%="offerPrice" + al.get(0)%>' />
															<span id='<%="offerPriceShow" + al.get(0)%>' ><%=al.get(4)%></span></div>

													</div>

													<div class="uiv2-rate-button-block">

														<div class="uiv2-add-to-basket">

															<div class="uiv2-rate-count-btn">
																<span class="uiv2-qty-label">Qty</span> <input
																	value="<%=alreadyInBasket%>" type="hidden"
																	name='<%="quantity" + al.get(0)%>'
																	id='<%="quantity" + al.get(0)%>' size="10"> <input
																	value="<%=alreadyInBasketView%>" type="text"
																	name='<%="quantityShow" + al.get(0)%>'
																	id='<%="quantityShow" + al.get(0)%>'
																	onBlur="quantityAdd('<%=al.get(0)%>')" size="10">

															</div>

															<a class="uiv2-add-button a2c add_this"
																onClick="javascript:addToCart('<%=al.get(0)%>')"
																href="javascript:void();"> ADD<span
																class="icon icon-basket-gray"></span>
															</a>

														</div>

													</div>
													<div class="uiv2-rate-button-block">
														<a href="javascript:void();"
															onclick="addToWishList('<%=al.get(0)%>')"
															class="wishlist">add to wishlist</a>
													</div>
												</div> 
												
											</li>

											<%
												}
											%>

											<%-- 										</s:iterator>

 --%>

										</ul>

									</div>

								</div>

							</div>

							<div class="paginationArea">

								<div class="noItems">
									Showing <span class="bFont"><%=startIndexShow%> - <%=endIndex%></span>
									of <span class="bFont"><%=count%> products</span>
								</div>

								<%-- <div class="sortOptions">

								<select>

									<option value="popularity" selected="selected">Popularity</option>

									<option value="pricelth">Price - Low to High</option>

									<option value="pricehtl">Price - High to Low</option>

									<option value="alpha">Alphabetical</option>

									<option value="offers">Offers</option>

								</select>

							</div> --%>

								<div class="pagination">

									<ul class="pages">

										<%
											for (int y = 1; y <= pageNum; y++) {

												if (y == selectedIndex) {
										%>

										<li><a href="javascript:void();" onclick="searchForBrandPagination('<%=y%>')" class="selected"><%=y%></a>
										</li> &nbsp;

										<%
											}

												else {
										%>

										<li><a href="javascript:void();" onclick="searchForBrandPagination('<%=y%>')"><%=y%></a>
										</li>&nbsp;

										<%
											}

											}
										%>



									</ul>

								</div>

								<div class="pageTop"></div>

							</div>

						</div>

					</div>

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
<script type="text/javascript">
	var data = '<%=request.getAttribute("wishlistSuccess")%>';
	if(data != null && data != '' && data != 'null' && data != 'NULL'){
		$('.add_this_content').fadeOut(-1);
		$("#productForSuccessMSG").html("Item added to wishlist successfully.");
        $('.add_this_content').fadeIn('slow').delay(1500).fadeOut('slow');
	}
</script>
