<!doctype html>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
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
<link href="css/stylesheet.css" rel="stylesheet" type="text/css" />
<!--[if lt IE 9]><link href="//d3oi2nue850v1c.cloudfront.net/static/uiv2/css/ie8styles.css" rel="stylesheet" type="text/css">

<script src="//d3oi2nue850v1c.cloudfront.net/static/uiv2/js/html5shiv.js"></script>

<![endif]-->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="js/jquery-ui.min.js" type="text/javascript"></script>
<link rel="stylesheet" href="css/jquery-ui-autocomplete.css" />
<script src="js/jquery.cycle.js" type="text/javascript"></script>
<script src="js/common.js" type="text/javascript"></script>
<!-- <script src="js/jquery-ui.min.js" type="text/javascript"></script>
 -->
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
<script type="text/javascript">
	function deleteFromCart(productId) {

		$.get(

		"DeleteFromCart.action",

		{
			productId : productId
		},

		function(data) {

			getCookies();

		},

		"html"

		);

	}

	function getCookies() {

		$("#myBasket").html("");

		var c = document.cookie;

		if (c != "" || c != '') {
			var allValCookie = c.split(";");
			var total = 0;
			var saveValue = 0.0;
			var itemCount = 0;
			for ( var i = 0; i < allValCookie.length; i++) {

				if (allValCookie[i].indexOf("=") != -1) {

					var allVal1 = allValCookie[i].split("=");

					if (allVal1[1].indexOf("-") != -1
							&& allVal1[1].split("-").length >= 10) {
						itemCount = parseInt(itemCount) + 1;
						var allVal = allVal1[1].split("-");
						total = total + parseInt(allVal[2]);
						saveValue = saveValue + parseFloat(allVal[6]);
						var imgArr = allVal[7].split("#");
						var imagePath = "";
						for ( var p = 0; p < imgArr.length; p++) {
							imagePath = imagePath + "-" + imgArr[p];
						}
						imagePath = imagePath.substring(1);
						var h = '<li id="cart1-block">'
								+ '<div class="uiv2-items-img"><a href="javascript:void();"> <img src="'+imagePath+'" alt="'+allVal[5]+' - '+allVal[0]+'" width="65" height="61" /></a></div>'
								+ '<div class="uiv2-items-content-wrapper"><div class="uiv2-items-content"><span><a href="javascript:void();">'
								+ allVal[4]
								+ '</a></span>'
								+ '<p><a href="javascript:void();">'
								+ allVal[5]
								+ ' - '
								+ allVal[0]
								+ '</a></p>'
								+ '</div><div class="uiv2-items-button-block"><div class="uiv2-grid-count-btn">'
								+ '<input type="button" value="-" class="qtyminus" id="decreaseQuantity" onclick="decreaseQuan('
								+ allVal1[0]
								+ ')" field="quantity1" />'
								+ '<input type="text" name="quantity1" id="quantity1'
								+ allVal1[0].trim()
								+ '" value="'
								+ allVal[3]
								+ '" class="qty" />'
								+ '<input type="button" value="+" id="increaseQuantity" onclick="increaseQuan('
								+ allVal1[0]
								+ ')" class="qtyplus" field="quantity1" />'
								+ '</div><span class="uiv2-items-rate"><span class="WebRupee">Rs.</span> '
								+ allVal[2]
								+ '</span>'
								+ '<div class="uiv2-items-saved-rate"><span>SAVED</span><span><span class="WebRupee">Rs.</span> '
								+ allVal[6]
								+ '</span></div>'
								+ '</div></div><a href="javascript:void();" class="uiv2-remove-product" id="cart1" onClick="deleteFromCart('
								+ allVal1[0] + ');">&nbsp;</a></li>';
						h = $("#myBasket").html() + h;
						$("#myBasket").html(h);
						$("#quantity" + allVal1[0].trim()).val(allVal[3]);
						$("#quantityShow" + allVal1[0].trim()).val(allVal[3]);
					}
				}
			}
			$("#basketCount").text(itemCount + " items");
			$("#basketCount1").text(itemCount + " items");
			$("#totalBasket").html(total);
			$("#saveValue").html(saveValue);
			$("#checkOutAnchorP").show();
			$("#checkOutAnchor").show();
			
			if (parseInt(itemCount) == 0) {
				$("#myBasket")
						.html(
								'<div class="uiv2-items-content-wrapper empty_cart"><div class="uiv2-items-content"><span><a href="javascript:void();"></a></span>'
										+ '<p><a href="javascript:void();"><br/>No Item in Kart. Please shop.</a></p>'
										+ '</div></div>');
				$("#totalBasket").html("0.0");
				$("#saveValue").html("0.0");
				$("#basketCount").text("0 item");
				$("#basketCount1").text("0 item");
				$("#checkOutAnchor").hide();
				$("#checkOutAnchorP").hide();
			}

		} else {
			$("#myBasket")
					.html(
							'<div class="uiv2-items-content-wrapper empty_cart"><div class="uiv2-items-content"><span><a href="javascript:void();"></a></span>'
									+ '<p><a href="javascript:void();"><br/>No Item in Kart. Please shop.</a></p>'
									+ '</div></div>');
			$("#totalBasket").html("0.0");
			$("#saveValue").html("0.0");
			$("#basketCount").text("0 item");
			$("#basketCount1").text("0 item");
			$("#checkOutAnchor").hide();
			$("#checkOutAnchorP").hide();
			/* $("#finalSubTotal").text("Rs. 0.0");

			$("#deliveryCharge").text("Rs. 0.0");

			$("#finalTotal").text("Rs. 0.0");

			$("#totalSaving").text("Rs. 0.0"); */
		}

		//showCart();

	}
	function increaseQuan(productId) {

		var val = document.getElementById("quantity1" + productId + "").value;

		$.get(

		"IncreaseQuantity.action",

		{
			productId : productId,
			quantity : val
		},

		function(data) {

			getCookies();

		},

		"html"

		);

	}

	function decreaseQuan(productId) {

		var val = document.getElementById("quantity1" + productId + "").value;

		$.get(

		"DecreaseQuantity.action",

		{
			productId : productId,
			quantity : val
		},

		function(data) {

			getCookies();

		},

		"html"

		);

	}

	function quantityAdd(productId) {

		var arr = $("#quantityShow" + productId).val().trim().split(" ");

		var quan = arr[0];

		if (arr.length < 3) {

			if (arr.length == 1) {

				$("#quantity" + productId).val(quan);

			}

			else {

				 $('#quantity_success').css('display','block');

				return false;

			}

		}

		$("#quantity" + productId).val(quan);

	}

	function showCart() {

		var val = '<ul class="uiv2-griditems"><li class="uiv2-padding-left" style="width:451px;">ITEM DESCRIPTION</li>'
				+

				'<li>UNIT PRICE</li><li>QUANTITY</li><li>SUBTOTAL</li><li>&nbsp;</li><li> SAVINGS</li></ul>';

		var c = document.cookie;

		if (c != "" || c != '') {

			var allValCookie = c.split(";");

			$("#basketCount").text(allValCookie.length + " items");

			$("#basketCount1").text(allValCookie.length + " items");

			$("#noItems").text(allValCookie.length);

			var total = 0;

			var saveValue = 0.0;

			for ( var i = 0; i < allValCookie.length; i++) {

				var allVal1 = allValCookie[i].split("=");

				var allVal = allVal1[1].split("-");

				total = total + parseInt(allVal[2]);

				saveValue = saveValue + parseFloat(allVal[6]);

				val = val
						+ '<div class="row" id="cart-product2-block">'
						+

						'<ul class="uiv2-griditems-details">'
						+

						'<li class="uiv2-padding-left" style="width:440px;"> <a class="uiv2-fresho-title" href="javascript:void();">'
						+ allVal[4]
						+ '</a> <a href="javascript:void();" class="uiv2-items-title">'
						+ allVal[5]
						+ ' - '
						+ allVal[0]
						+ '</a> </li>'
						+

						'<li><span class="uiv2-unit-price"><span class="WebRupee">Rs.</span> '
						+ allVal[1]
						+ '</span></li>'
						+

						'<li><div class="uiv2-grid-count-btn">'
						+

						'<input type="text" name="quantity5" value="'+allVal[3]+'" class="qty">'
						+

						'</div></li><li><span class="uiv2-subtotal-rate"><span class="WebRupee">Rs.</span> <span>'
						+ allVal[2] + '</span></span></li>' +

						'<li>&nbsp;</li>' +

						'<li><span class="uiv2-savings-rate">' + allVal[6]
						+ '</span></li>	</ul></div>';

			}

			var deliveryCharge = "25.00";

			var allTotal = parseFloat(total) + parseFloat(deliveryCharge);

			$("#finalSubTotal").text("Rs. " + total);

			$("#deliveryCharge").text("Rs. " + deliveryCharge);

			$("#finalTotal").text("Rs. " + allTotal);

			$("#totalSaving").text("Rs. " + saveValue);

		}

		else {

			val = val
					+ '<div class="row" id="cart-product2-block"><ul class="uiv2-griditems-details"><li class="uiv2-padding-left" style="width:440px;">No Item in Kart. Please click on continue shopping and add some itme in your cart.</li><li>&nbsp;</li><li>&nbsp;</li><li>&nbsp;</li><li>&nbsp;</li><li>&nbsp;</li></ul></div>';

		}

		$("#showCart").html(val);

	}
</script>
</head>
<body onLoad="getCookies()">
<div id="shadow-mask">&nbsp;</div>
<jsp:include page="header.jsp"></jsp:include>
<div class="main_wrapper">
	<div class="uiv2-navigation-slider">
		<div class="uiv2-left-navigation">
			<div class="uiv2-nav uiv2-pointer" id="uiv2-menu-bar"> SHOP <span class="uiv2-nav-icon"></span> </div>
			<!-- Navigation starts here -->
			<jsp:include page="productListInner.jsp"></jsp:include>
			<!-- Navigation ends here -->
		</div>
		<jsp:include page="menuPage.jsp"></jsp:include>
		<div class="uiv2-content-wrapper">
			<div class="uiv2-content-wrapper">
				<div class="sysMessage"></div>
				<div class="uiv2-basket-container">
					<div class="uiv2-title-basketwrap">
						<h4>Your Order has been booked successfully. We will reach you as soon as possible. Feel free to contact us.</h4>
					</div>
					<!-- 

					<div id="mainEligiblePromo" class="uiv2-viewaviled-button-wrap"> <a class="uiv2-viewavilable-button" href="javascript:void();" id="popup_available_promos" onClick="show_promo('popup_available_promos');"> <span class="icon icon-promo-orange"></span>

						<p>VIEW AVAILABLE PROMOS</p>

						</a> </div> -->
					<div class="uiv2-basket">
						<div id="showCart" class="uiv2-yourbasketitems-gridlist">
							<ul class="uiv2-griditems">
								<li class="uiv2-padding-left" style="width: 451px;">ITEM DESCRIPTION</li>
								<li>UNIT PRICE</li>
								<li>QUANTITY</li>
								<li>&nbsp;</li>
								<li>&nbsp;</li>
								<li>TOTAL</li>
							</ul>
							<div class="row" id="cart-product2-block">
								<%
										Map value = (Map) request.getAttribute("productDetail");

										ArrayList cartDetail = (ArrayList) value.get("PRODUCTDETAIL");

										if (cartDetail != null && cartDetail.size() > 0) {

											for (int i = 0; i < cartDetail.size(); i++) {

												String allVal[] = (String[]) cartDetail.get(i);
									%>
								<ul class="uiv2-griditems-details">
									<li class="uiv2-padding-left" style="width: 440px;"><a
											class="uiv2-fresho-title" href="javascript:void();"><%=allVal[1]%></a> <a href="javascript:void();" class="uiv2-items-title"><%=allVal[0] + " - " + allVal[2]%></a> </li>
									<li><span class="uiv2-unit-price"><span
												class="WebRupee">Rs.</span> <%=allVal[3]%></span> </li>
									<li>
										<div class="uiv2-grid-count-btn">
											<input type="text" readonly="readonly" name="quantity5"
													value="<%=allVal[4]%>" class="qty">
										</div>
									</li>
									<li><span class="uiv2-subtotal-rate"><span
												class="WebRupee">&nbsp;</span> </span> </li>
									<li>&nbsp;</li>
									<li><span class="uiv2-savings-rate">Rs. <%=allVal[5]%></span> </li>
								</ul>
								<%
										}
											
											Map orderSummary = (Map)value.get("orderSummary");
									%>
								
								<table cellpadding="0" cellspacing="0" width="100%" border="0"><tbody>
									<tr><td>&nbsp;</td><td>&nbsp;</td></tr>
									<tr>
										<td style=" font:400 13px/14px 'Roboto', serif; text-align:right; padding:6px 0px;">Total Amount : <span class="WebRupee">Rs. </span> <%=orderSummary.get("totalAmount") %></td>
										<td width="30px">&nbsp;</td>
									</tr>
									<tr>
										<td style=" font:400 13px/14px 'Roboto', serif; text-align:right; padding:6px 0px;">Delivery Charge : <span class="WebRupee">Rs. </span> <%=orderSummary.get("deliveryCharge") %></td>
										<td width="30px">&nbsp;</td>
									</tr>
									<tr>
										<td style=" font:400 13px/14px 'Roboto', serif; text-align:right; padding:6px 0px;">Discount : <span class="WebRupee">Rs. </span><%=orderSummary.get("discount") %></td>
										<td width="30px">&nbsp;</td>
									</tr>
									<tr>
										<td style=" font:400 13px/14px 'Roboto', serif; text-align:right; padding:6px 0px;">Amount To Pay : <span class="WebRupee">Rs. </span> <%=orderSummary.get("amountToPay") %></td>
										<td width="30px">&nbsp;</td>
									</tr>
									<tr><td>&nbsp;</td><td>&nbsp;</td></tr>
								</tbody></table>
								<%
										}
									%>
							</div>
						</div>
						<!--Column -->
						<!-- <div id="top_promo" style="display:none"> <a class="uiv2-tea-set-block" href="javascript:void(0)" title="" onClick="show_promo('popup_promo');">

							<p><b>Tramontina offers !!</b><br>

								<br>

								Buy Tramontina Product Rs.600/- and above Get 10% Discount...</p>

							</a> </div> -->
					</div>
					<!--Shopping Cart Content-->
					<section class="uiv2-shoppingcartblock">
						<div class="uiv2-enter-your-evoucher-left">
							<div style="margin-top: 240px; float: left;">
								<!-- <a href="index.jsp" class="uiv2-continue-shopping-button">CONTINUE SHOPPING</a> -->
							</div>
						</div>
						<div class="uiv2-checkoutblock-right">
							<!-- <div id="topPromo"> <a class="uiv2-tea-set-block" href="javascript:void(0)" title="" id="popup_offers" onClick="show_promo('popup_promo');">

								<p><b>Tramontina<br />

									offers !!</b><br />

									<br />

									Buy Tramontina Product Rs.600/- and above Get 10% Discount...</p>

								</a> </div> -->
							<!-- <div class="uiv2-deliver-charges-wrapper">

								<div class="uiv2-deliver-charges-totalamount">

									<div class="uiv2-subtotal-listblock">

										<div class="uiv2-sub-total-list">

											<p>Subtotal </p>

											<span id="finalSubTotal"> </span></div>

										<div class="uiv2-sub-total-list">

											<p>Delivery Charges </p>

										<span id="deliveryCharge"> </span></div>

										<b>TOTAL <span id="finalTotal"> </span></b></div>

									<div class="uiv2-yousaved-wrap"><span>You saved!</span><span id="totalSaving"> </span></div>

								</div>

								<div class="uiv2-avial-freedelivery "><span class="icon icon-delivery-van"></span>

									<p><span id="freeDeliveryNote"> Shop for <span class="WebRupee">Rs.</span> 500 more to avail Free Delivery! </span></p>

								</div>

								<div class="uiv2-for-this-order">* For this order:  Accepted food coupon is <span id="foodTotal">Rs. 196.00</span></div>

								 -->
							<!-- <a id="checkOutAnchor" href="CheckOutAction.action" class="uiv2-checkout-button">

								<p id="checkOutAnchorP">CHECKOUT</p>

								<span class="icon icon-navigating-right"></span></a> </div> -->
						</div>
						<div id="shoplist-mask"
								style="display: none; width: 100%; height: 100%;"></div>
					</section>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="footer_wrapper">
	<jsp:include page="footer.jsp"></jsp:include>
</div>
<div class="uiv2-copyright">
	<p> Copyright &copy; 2014-<span id="current-year"></span> All rights
		reserved. </p>
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