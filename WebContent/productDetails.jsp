<!doctype html>

<%@page import="java.util.Iterator"%>

<%@page import="java.util.Set"%>

<%@page import="java.util.ArrayList"%>

<%@page import="java.util.Map"%>

<html xmlns:fb="http://ogp.me/ns/fb#">

<head>

<meta charset="utf-8">

<meta http-equiv="X-UA-Compatible" content="IE-EmulateIE8">

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

<title>BestKart4U : Best online Store and Supermarket in India</title>

<link href="http://fonts.googleapis.com/css?family=Roboto:400,300" rel="stylesheet" type="text/css" />

<link href="http://fonts.googleapis.com/css?family=Lato:600,300,400,700" rel="stylesheet" type="text/css" />

<link href="http://fonts.googleapis.com/css?family=Roboto+Slab:700,500,400,300,100" rel="stylesheet" type="text/css" />

<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/themes/overcast/jquery-ui.min.css" type="text/css" media="all" />
<link rel="icon" type="image/png" href="images/favicon.ico" />
<!--<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/themes/overcast/jquery-ui.min.css" type="text/css" media="all" />-->

<link href="css/stylesheet.css" rel="stylesheet" type="text/css" />

<!--[if lt IE 9]><link href="//d3oi2nue850v1c.cloudfront.net/static/uiv2/css/ie8styles.css" rel="stylesheet" type="text/css">

<script src="//d3oi2nue850v1c.cloudfront.net/static/uiv2/js/html5shiv.js"></script>

<![endif]-->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="js/jquery-ui.min.js" type="text/javascript"></script>
<link rel="stylesheet" href="css/jquery-ui-autocomplete.css" />


<script src="js/common.js" type="text/javascript"></script>
<script src="js/jquery.cycle.js" type="text/javascript"></script>


<!-- <script src="js/jquery-ui.min.js" type="text/javascript"></script>
 -->
<script src="js/uiv2_main.min.js" type="text/javascript"></script>

<script src="js/cart.js" type="text/javascript"></script>



<script>

        $(document).ready(function () {

             init_a2c(25);

            init_view_basket('25', 1);

            init_display_autovoucheroffers();

            init_close_autovoucheroffer_dialog();

            parse_utm_cookies();

            on_register_free_delivery_token_dialog();

            // Resize banner bars

            var banner_ele = $(".uiv2-latest-basket-link");

            banner_ele.hover(function () {

                $(this).banner_resize(".uiv2-img-nav-banner", ".uiv2-banner-caption", 20);

            });



            banner_ele.click(function () {

                $(this).banner_resize(".uiv2-img-nav-banner", ".uiv2-banner-caption", 20);

            });



            







                var script = document.createElement("script");

                script.type = "text/javascript";

                script.src = "http://cdn.webrupee.com/js";

                document.getElementsByTagName("head")[0].appendChild(script);

            

            Handlebars.registerHelper('ifCond', function(v1, v2, options) {

                if(v1 === v2) {

                    return options.fn(this);

                }

                return options.inverse(this);

            });





        });

    </script>

<link rel="stylesheet" type="text/css" href="css/jquery.jqzoom.css" />

<script type="text/javascript" src="js/jquery.jqzoom-core.min.js"></script>

<script type="text/javascript" src="js/jquery_slider.js"></script>

<script type="text/javascript" src="js/home.js"></script>

<script type="text/javascript">

        $(document).ready(function () {

            $(".jqzoom").jqzoom({

                yOffset: 0,

                preloadText: ''

            });



            //Add Navigation Context to the URL of the product when it is accessed from Related Products

             $('.related_products a[href*="/pd/"]').click(function () {

                this.href = this.href + "?nc=rp";

            });

            $('.also_bought a[href*="/pd/"]').click(function () {

                this.href = this.href + "?nc=ab";

            });

            $('.weightIcon').hover(function () {

                $('.weightToolTipContainer').toggle();

            });

            set_pd_cart_nav_ctx();



            //Carousel bug - product deck height hack

            $('.caroufredsel_wrapper').css('height', '385px');

            $('.uiv2-carousel-holder').css('height', '384px');

 

        });

    </script>
<script type="text/javascript">
function subtractQty(id){
	if(document.getElementById(id).value - 1 < 1)
		return;
	else 
		 document.getElementById(id).value--;
}
function addQty(id){
		document.getElementById(id).value++;
}
</script>
</head>

<body onLoad="getCookies()">

<div id="shadow-mask">&nbsp;</div>



<jsp:include page="header.jsp"></jsp:include>



<%

	Map m = (Map)request.getAttribute("productDetail");

	String productName = (String)m.get("productName");

	String productBrandName = (String)m.get("productBrandName");

	String actualPrice = (String)m.get("actualPrice");

	String offerPrice = (String)m.get("offerPrice");

	String imagePath = (String)m.get("imagePath");

	String productId = (String)m.get("productId");

	String aboutProduct = (String)m.get("aboutProduct");

	String ingredient = (String)m.get("ingredient");

	ArrayList al = (ArrayList)m.get("productPriceDetail");

	String rootCategory = (String)request.getAttribute("rootCategory");

	String subRootCategory = (String)request.getAttribute("subRootCategory");



%>



<div class="uiv2-navigation-slider">

	<div class="uiv2-left-navigation">

		<div class="uiv2-nav uiv2-pointer" id="uiv2-menu-bar">SHOP <span class="uiv2-nav-icon"></span></div>

			<jsp:include page="productListInner.jsp"></jsp:include>

	</div>

	

	<jsp:include page="menuPage.jsp"></jsp:include>

	

	

	<div class="uiv2-content-wrapper">

		<div class="sysMessage"></div>

		<div class="uiv2-div100">

			<div class="uiv2-div1000px uiv2-clear-b">

				<div class="uiv2-div100 uiv2-white">

					<div id="uiv2-shopping-list" class="uiv2-div962px uiv2-clear-b">

						<!-- <div class="uiv2-shopping-list-bredcom">

							<div class="breadcrumb-item"><a href="javascript:void();"><span>HOME</span></a><label>&gt;</label></div>

							<div class="breadcrumb-item"><a href="javascript:void();"><span>Grocery &amp; Staples</span></a><label>&gt;</label></div>

							<div class="breadcrumb-item"><a href="javascript:void();"><span>Dals &amp; Pulses</span></a></div>

						</div> -->

						

<!-- var drop = document.getElementById('dropDown'+productId).value;

		//var price = document.getElementById('offerPrice'+productId).value;

		//var quantity = document.getElementById('quantity'+productId).value;

		//var brandName = document.getElementById('brandName'+productId).value;

		//var productName = document.getElementById('productName'+productId).value;

		//var imagePath = document.getElementById('imagePath'+productId).value;

		//var saveValue =  document.getElementById('saveValue'+productId).value; -->

						<input type="hidden" value="<%=offerPrice %>" id='<%="offerPrice"+productId%>' />

						<input type="hidden" value="<%=productBrandName %>" id='<%="brandName"+productId%>'/>

						<input type="hidden" value="<%=productName %>" id='<%="productName"+productId%>'/>

						<input type="hidden" value="<%=imagePath %>" id='<%="imagePath"+productId%>' />

						<input type="hidden" value="<%=Double.parseDouble(actualPrice)-Double.parseDouble(offerPrice)%>" id='<%="saveValue"+productId%>' />

						

						

						<div class="uiv2-product-info-wrapper">

							<div class="uiv2-product-large-img-container">

								<a href="<%=imagePath %>" class="jqzoom" title="Spices - Coriander Powder">

									<img src="<%=imagePath %>" width="410" height="390" class="40009485_uiv2-product-large-img-display uiv2-product-large-img-display" alt="<%=productName %>" />

								</a>

								<img width="33" height="32" class="uiv2-lens-icon" alt="" src="images/lensIcon.png" />

								<div id="uiv2-product-caption"><%=productName %></div>

							</div>

							<div class="uiv2-product-detail-content">

								<div class="uiv2-brand-name"><a href="javascript:void();"><%=productBrandName %></a></div>

								<div class="uiv2-product-name"  itemprop="name">

									<h1><%=productName %></h1>

								</div>

								<div class="uiv2-product-value">

									<div class="uiv2-price" itemprop="price">
									<%if(!actualPrice.equalsIgnoreCase(offerPrice)){ %>
									Rs. <strike><span id='<%="actualPriceShow" + productId%>' >
									<%=actualPrice %></span></strike> - 
									<%} %>
									Rs. <span id='<%="offerPriceShow" + productId%>' ><%=offerPrice %></span><br/>

										<select onChange="changeSaveValue('<%=productId %>')" name='<%="dropDown"+productId%>' id='<%="dropDown"+productId%>'>

													<%

														for(int i = 0; i<al.size();i++){

															String arr[] = (String[])al.get(i);

															String val = arr[0] + " - " +arr[2]+" Rs";

													%>

													<option offerprice="<%=arr[1]%>" value="<%=val %>"><%=val %> </option>

													<%

														}

													%>

												</select>

										

									</div>

									<div class="uiv2-add-product-it-basket">

										<li name="widget_40009485">

											<div class="uiv2-add-to-basket">
												<div style=" float:left; line-height:; padding-right:10px;"><span>Quanitity:</span></div>
												<div class="uiv2-rate-count-btn"><!--<span class="uiv2-qty-label">Qty</span>-->
													
													<input type="button" name="subtract" onclick="javascript: subtractQty('<%="quantity"+productId%>');" value="-" />
													<input value="1" name="qty" id="<%="quantity"+productId%>" onBlur="quantityAdd('<%=productId %>')" type="text" />
													<input type="button" name="add" onclick="javascript: addQty('<%="quantity"+productId%>');" value="+" />
													
													<!--<input type="text" name="qty" id="qty1" value="1" />-->
													
													
<%-- 													<input value="1" id="<%="quantity"+productId%>" type="hidden" />
 --%>
												</div>

												<a class="uiv2-add-button a2c" href="javascript:void(0)" onClick="javascript:addToCart('<%=productId%>')"> ADD TO CART<span class="icon icon-basket-gray"></span></a>

											</div>

										</li>

									</div>

								</div>

								<!-- fdpSkuPriceArea -->

							</div>

						</div>

						<div class="uiv2-content-wrapper uiv2-clear-b" style="width:958px;">

							<hr class="gray"/>

							<div class="uiv2-div100 uiv2-product-heading-h2-section">

								<h2><%=productBrandName%> - <%=productName %></h2>

							</div>

							<%-- <div id="uiv2-tabbed-content-wrapper">

								<div id="uiv2-tab-container">

									<ul class="tabs">

										<li><a href="javascript:void(0);" id="uiv2-tab1_link" class="uiv2-tab" >About</a></li>

										<li><a href="javascript:void(0);" id="uiv2-tab2_link" class="uiv2-tab" >Ingredients:</a></li>

									</ul>

									<div id="uiv2-tab1" class="uiv2-boxsty uiv2-tab-content">

										<p> <%=aboutProduct %> </p>

									</div>

									<div id="uiv2-tab2" class="uiv2-boxsty uiv2-tab-content">

										<p> <%=ingredient %> </p>

									</div>

								</div>

							</div> --%>
<!-- 
							<script type="text/javascript">

                                    var tabs = $('.uiv2-tab');

                                    tabs.each(function(ele){

                                        $(this).click(function(e){

                                            tabs.removeClass('active');

                                            $('.uiv2-tab-content').hide();

                                            var tab_ele = e.target;

                                            var tab_id = $(tab_ele).attr('id').split('_')[0];

                                            $(tab_ele).addClass('active');

                                            $('#'+tab_id).show();

                                        });

                                    });

                                    tabs[0].click(); // Click on the first tab

                                </script>
 -->
							<div class="uiv2-clear-b">&nbsp;</div>

							<div class="product-slider">
								<%
								Map<String, ArrayList> allRelatedProducts = (Map<String, ArrayList>)request.getAttribute("relatedProducts");
								if(allRelatedProducts != null && allRelatedProducts.size() > 0){
								
								%>


								<div class="transparent p1 category_rp">

									<div class="uiv2-title-2">

										<h3 class="uiv2-tiltle-2">Products similar to</h3> <a href="javascript:void();"><%=productBrandName %> - <%=productName %></a>

									</div>

									<div class="uiv2-clear-b"></div>

									<div class="image_carousel">

										<div class="caroufredsel_wrapper uiv2-carousel-top related_products">

											<div class="uiv2-shopping-lis-sku-cards uiv2-div100 uiv2-border-none">

												<ul class="uiv2-our-recommendations-list-box">

													<div class="uiv2-carousel-holder" id="carouSlide_1" >

														<%

														
														Set keys = allRelatedProducts.entrySet();

														

														Iterator productListItr = keys.iterator();

														while(productListItr.hasNext()){

															

															Map.Entry me = (Map.Entry)productListItr.next();

															ArrayList productInfo = (ArrayList)me.getValue();

															

														%>

														

														<li>

															<div class="uiv2-list-box-img-block">

																<a href="ProductsDetail.action?productId=<%=productInfo.get(0)%>&rootCategory=<%=rootCategory%>&subRootCategory=<%=subRootCategory%>" title=""><img title="" width="150" height="200" alt="" src="<%=productInfo.get(5) %>" /></a>

															</div>

															<div class="uiv2-list-box-img-title">

																<span class="uiv2-title-tool-tip">

																	<a href="javascript:void();"><span class="uiv2-brand-title"><%=productInfo.get(2) %></span> <%=productInfo.get(1) %> </a>

																	<span class="uiv2-tool-tip-hover"><%=productInfo.get(2) %> &nbsp;&nbsp; <%=productInfo.get(1) %></span>

																</span>

															</div>

															<div class="uiv2-field-wrap mt10">

																<select name='<%="dropDown"+productInfo.get(0)%>' id='<%="dropDown"+productInfo.get(0)%>'>

																	<%

																		ArrayList priceDetail = (ArrayList)productInfo.get(6);

																		for(int i = 0; i<priceDetail.size();i++){

																			String arr[] = (String[])priceDetail.get(i);

																			String val = arr[0] + " - " +arr[1]+" Rs";

																	%>

																	<option value="<%=val %>"><%=val %> </option>

																	<%

																		}

																	%>

																</select>

															

															</div>

															<div class="uiv2-list-box-drop-rate-block">

																<div class="ListBox_Rate_count">

																	<div class="uiv2-rate-count-avial"><span class="WebRupee">Rs.</span> <strike><%=productInfo.get(3) %></strike> </div>

																</div>

																<div class="uiv2-rate-button-block">

																	<div class="uiv2-add-to-basket">

																		<div class="uiv2-product-notify"><span>Rs.<%=productInfo.get(4) %></span></div>

																	</div>

																</div>

															</div>

														</li>

														<%

														}

														%>

													</div>

												</ul>

											</div>

										</div>

										<a style="display: block;" class="previous disabled" id="carouSlide_1_previtem" href="javascript:void();"><span>prev</span></a>

										<a style="display: block;" class="next" id="carouSlide_1_nextitem" href="javascript:void();"><span>next</span></a>

									</div>

									<!-- /image_carousel -->

								</div>
								<%
								}
								Map<String, ArrayList> allRelatedBrandProducts = (Map<String, ArrayList>)request.getAttribute("relatedBrandProducts");
								if(allRelatedBrandProducts != null && allRelatedBrandProducts.size() > 1){
								%>
								
								<div class="transparent p1 related_brand">

									<div class="uiv2-title-2">

										<h3 class="uiv2-tiltle-2">More products from </h3><a href="javascript:void();"><%=productBrandName %></a></div>

									<div class="uiv2-clear-b"></div>

									<div class="image_carousel">

										<div class="caroufredsel_wrapper uiv2-carousel-top related_products">

											<div class="uiv2-shopping-lis-sku-cards uiv2-div100 uiv2-border-none">

												<ul class="uiv2-our-recommendations-list-box">

													<div class="uiv2-carousel-holder" id="carouSlide_2" >

													<%

														Set keysBrand = allRelatedBrandProducts.entrySet();

														

														Iterator productBrandListItr = keysBrand.iterator();

														while(productBrandListItr.hasNext()){

															

															Map.Entry me1 = (Map.Entry)productBrandListItr.next();

															ArrayList productBrandInfo = (ArrayList)me1.getValue();

															

														%>

														

														<li>

															<div class="uiv2-list-box-img-block">

																<a href="ProductsDetail.action?productId=<%=productBrandInfo.get(0)%>&rootCategory=<%=rootCategory%>&subRootCategory=<%=subRootCategory%>" title=""><img title="" alt="" width="150" height="200" src="<%=productBrandInfo.get(5) %>" /></a>

															</div>

															<div class="uiv2-list-box-img-title">

																<span class="uiv2-title-tool-tip">

																	<a href="javascript:void();"><span class="uiv2-brand-title"><%=productBrandInfo.get(2) %></span> <%=productBrandInfo.get(1) %> </a>

																	<span class="uiv2-tool-tip-hover"><%=productBrandInfo.get(2) %> &nbsp;&nbsp; <%=productBrandInfo.get(1) %></span>

																</span>

															</div>

															<div class="uiv2-field-wrap mt10">

																<select name='<%="dropDown"+productBrandInfo.get(0)%>' id='<%="dropDown"+productBrandInfo.get(0)%>'>

																	<%

																		ArrayList priceDetail1 = (ArrayList)productBrandInfo.get(6);

																		for(int i = 0; i<priceDetail1.size();i++){

																			String arr[] = (String[])priceDetail1.get(i);

																			String val = arr[0] + " - " +arr[1]+" Rs";

																	%>

																	<option value="<%=val %>"><%=val %> </option>

																	<%

																		}

																	%>

																</select>

															

															</div>

															<div class="uiv2-list-box-drop-rate-block">

																<div class="ListBox_Rate_count">

																	<div class="uiv2-rate-count-avial"><span class="WebRupee">Rs.</span> <strike><%=productBrandInfo.get(3) %></strike> </div>

																</div>

																<div class="uiv2-rate-button-block">

																	<div class="uiv2-add-to-basket">

																		<div class="uiv2-product-notify"><span>Rs.<%=productBrandInfo.get(4) %></span></div>

																	</div>

																</div>

															</div>

														</li>

														<%

														}

														%>

													<!-- 

														<li>

															<div class="uiv2-list-box-img-block">

																<a href="javascript:void();" title=""><img title="" alt="" src="images/patanjali-cow-ghee.jpg"></a></div>

															<div class="uiv2-list-box-img-title">

																<span class="uiv2-title-tool-tip">

																	<a href="javascript:void();"><span class="uiv2-brand-title">PATANJALI</span> Amla - Candy </a>

																	<span class="uiv2-tool-tip-hover">Patanjali Amla - Candy 500 gm Bottle </span>

																</span>

															</div>

															<div class="uiv2-field-wrap">500 gm Bottle</div>

															<div class="uiv2-list-box-drop-rate-block">

																<div class="ListBox_Rate_count">

																	<div class="uiv2-rate-count-avial"><span class="WebRupee">Rs.</span> 130.00</div>

																</div>

																<div class="uiv2-rate-button-block">

																	<div class="uiv2-add-to-basket">

																		<div class="uiv2-rate-count-btn"><span class="uiv2-qty-label">Qty</span>

																			<input  value="1"  type="text" maxlength="2" />

																		</div>

																		<a class="uiv2-add-button a2c" href="javascript:void();">ADD<span class="icon icon-basket-gray"></span></a>

																	</div>

																</div>

															</div>

														</li>

													 -->	

													</div>

												</ul>

											</div>

										</div>

										<a style="display: block;" class="previous disabled" id="carouSlide_2_previtem" href="javascript:void();"><span>prev</span></a>

										<a style="display: block;" class="next" id="carouSlide_2_nextitem" href="javascript:void();"><span>next</span></a>

									</div>

									<!-- /image_carousel -->

								</div>

								<%
								}
								%>

											</div>

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

	<p>Copyright &copy; 2014-<span id="current-year"></span> All rights reserved.</p>

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