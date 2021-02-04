<!doctype html>



<%@page import="java.util.Iterator"%>

<%@page import="java.util.Set"%>

<%@page import="java.util.ArrayList"%>

<%@taglib uri="/struts-tags" prefix="s" %>

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

<%-- <script src="js/validation.js" type="text/javascript"></script> --%>



<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

<script src="js/jquery-ui.min.js" type="text/javascript"></script>

<link rel="stylesheet" href="css/jquery-ui-autocomplete.css" />



<%-- <script src="js/jquery.min.js" type="text/javascript"></script> --%>



<script src="js/jquery.cycle.js" type="text/javascript"></script>

<script src="js/common.js" type="text/javascript"></script>

<%-- <script src="js/jquery-ui.min.js" type="text/javascript"></script> --%>

<script src="js/validation.js" type="text/javascript"></script>

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







function show_feedback_form(id, orderId){



	document.getElementById("selectedOrderId").value=document.getElementById("orderId"+orderId).value;

	document.getElementById(id).style.display	=	"block";

}



function hide_code(id){



	document.getElementById(id).style.display	=	"none";



}



	

	function submitFeedback(){

		

		$("#productQualityAnswerError").html("");

		$("#packagingQualityAnswerError").html("");

		$("#deliveryServiceAnswerError").html("");

		$("#shopMoreAnswerError").html("");

		$("#suggesionAnswerError").html("");

		

		var count = 0;

		var flag = validateRequiredRadio('productQualityAnswer');

		if(!flag){

			count++;

			$("#productQualityAnswerError").html("<font color='red'>Please select answer.</font>");

		}

		var flag = validateRequiredRadio('packagingQualityAnswer');

		if(!flag){

			count++;

			$("#packagingQualityAnswerError").html("<font color='red'>Please select answer.</font>");

		}

		var flag = validateRequiredRadio('deliveryServiceAnswer');

		if(!flag){

			count++;

			$("#deliveryServiceAnswerError").html("<font color='red'>Please select answer.</font>");

		}

		var flag = validateRequiredRadio('shopMoreAnswer');

		if(!flag){

			count++;

			$("#shopMoreAnswerError").html("<font color='red'>Please select answer.</font>");

		}

		var suggesionAnswer = $('#suggesionAnswer').val().trim();

		if (suggesionAnswer == '') {

        	count++;

            $("#suggesionAnswerError").html("<font color='red'>Please enter Suggession.</font>");

        }

		

		if(parseInt(count) > 0){

			return false;

		}

		document.feedbackForm.submit();

	}

	

</script>

<script src="js/cart.js" type="text/javascript"></script>

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

					<div class="uiv2-basket-container" style=" position:relative;">

						<div class="uiv2-title-basketwrap">

							<h4><b>Previous Order Detail</b></h4>

						</div>
						<form action="SearchPreviousOrderAction.action" method="post">
						<div class="orders_search">
							<input type="text" name="orderId" id="SearchPreviousOrder" value="" placeholder="Search for a product id..." />
							<input type="submit" name="" value="" id="" />
						</div>
						</form>
						<div class="uiv2-basket">

							<div id="showCart" class="uiv2-yourbasketitems-gridlist">

								<%if(request.getAttribute("msg") != null ){ %>

								<div class="row" id="cart-product2-block">

									<ul class="uiv2-griditems-details">

										<li class="uiv2-padding-left" style="width: 440px;"><b>

											<font color="red"><s:property value="msg"/></font>

										</b></li>

										<li>&nbsp;</li>

										<li>&nbsp;</li>

										<li>&nbsp;</li>

										<li>&nbsp;</li>

										<li>&nbsp;</li>

									</ul>

									</div>	

								<%} %>
								

								
								<ul class="uiv2-griditems">

									<li class="uiv2-padding-left" style="width: 451px;">ITEM

										DESCRIPTION</li>

									<li>UNIT PRICE</li>

									<li>QUANTITY</li>

									<li>STATUS</li>

									<li>&nbsp;</li>

									<li>TOTAL</li>

								</ul>

									<%

										int i = 0;

										Map value = (Map) request.getAttribute("pendingOrderMap");

										if(value != null && value.size() > 0){

											Set s = value.entrySet();

											Iterator itr = s.iterator();

											while(itr.hasNext()){

												i++;

												Map.Entry me = (Map.Entry)itr.next();

												String key = (String)me.getKey();

												ArrayList allDetail = (ArrayList)me.getValue();

												String []keyArr=key.split("#");

												if(allDetail != null && allDetail.size() > 0){

												

									%>

									<input type="hidden" name="orderId" id="orderId<%=i%>" value="<%=keyArr[0]%>">

									<div class="row" id="cart-product2-block" style="max-height: 360px;
overflow-y: auto;">

									<ul class="uiv2-griditems-details" style="height: 50px; padding-top:10px;">

										<li class="uiv2-padding-left" style="width: 440px;"><b><%=keyArr[0]%></b></li>

										<li>&nbsp;</li>

										<li>&nbsp;</li>


										<li style=" width:300px; padding:0px; height:36px;"><button name="<%=i%>" class="uiv2-continue-shopping-button" style=" float:right; width:200px; outline:none; text-transform:uppercase; font-weight:500; margin:0px;"

												onClick="show_feedback_form('feedback_form','<%=i%>');">Share your feedback</button></li>

									</ul>

									<%

									Iterator detailItr = allDetail.iterator();

									while(detailItr.hasNext()){

										String detailItems[] = (String[])detailItr.next();

										String orderStatus = detailItems[11];

					                    if(orderStatus.equalsIgnoreCase("R")){

					                    	orderStatus = "<span class=\"uiv2-savings-rate\"><b>Rejected</b></span>";

					                    }

					                    else if(orderStatus.equalsIgnoreCase("D")){

					                    	orderStatus = "<font color=\"green\"><b>Delivered</b></font>";

					                    }

					                    

									%>

									<ul class="uiv2-griditems-details">

										<li class="uiv2-padding-left" style="width: 440px;"><a

											class="uiv2-fresho-title" href="javascript:void();"><%=detailItems[5] %></a>

											<a href="javascript:void();" class="uiv2-items-title"><%=detailItems[6] %></a>

										</li>

										<li><span class="uiv2-unit-price"><span

												class="WebRupee">Rs.</span><%=detailItems[7] %></span>

										</li>

										<li><div class="uiv2-grid-count-btn">

											<input type="text" readonly="readonly" name="quantity5"

													value="<%=detailItems[8] %>" class="qty">

											</div>

										</li>

										<li><%=orderStatus %></li>

										<li>&nbsp;</li>

										<li><span class="uiv2-savings-rate">Rs. <%=detailItems[9] %></span>

										</li>

									</ul>

									<%	

									}

									%>

									<table cellpadding="0" cellspacing="0" width="100%" border="0"><tbody>
									<tr><td>&nbsp;</td><td>&nbsp;</td></tr>
									<tr>
										<td style=" font:400 13px/14px 'Roboto', serif; text-align:right; padding:6px 0px;">Total Amount : <span class="WebRupee">Rs. </span> <%=keyArr[3]%></td>
										<td width="30px">&nbsp;</td>
									</tr>
									<tr>
										<td style=" font:400 13px/14px 'Roboto', serif; text-align:right; padding:6px 0px;">Delivery Charge : <span class="WebRupee">Rs. </span><%=keyArr[2]%> </td>
										<td width="30px">&nbsp;</td>
									</tr>
									<tr>
										<td style=" font:400 13px/14px 'Roboto', serif; text-align:right; padding:6px 0px;">Discount : <span class="WebRupee">Rs. </span><%=keyArr[5]%></td>
										<td width="30px">&nbsp;</td>
									</tr>
									<tr>
										<td style=" font:400 13px/14px 'Roboto', serif; text-align:right; padding:6px 0px;">Amount To Pay : <span class="WebRupee">Rs. </span><%=keyArr[4]%> </td>
										<td width="30px">&nbsp;</td>
									</tr>
									<tr><td>&nbsp;</td><td>&nbsp;</td></tr>
								</tbody></table>
										

									</div>

									<%

									}

									}

										}

										else{

											%>			

											<div class="row" id="cart-product2-block">

									<ul class="uiv2-griditems-details">

										<li class="uiv2-padding-left" style="width: 440px;"><b><font color ="red">No previous Order Available.</font></b></li>

										<li>&nbsp;</li>

										<li>&nbsp;</li>

										<li>&nbsp;</li>

										<li>&nbsp;</li>

										<li>&nbsp;</li>

									</ul>

									</div>		

											<%

										}

									%>

							</div>

						</div>



						<!--Shopping Cart Content-->



						<section class="uiv2-shoppingcartblock">

							<div class="uiv2-enter-your-evoucher-left">

								<div style="margin-top: 240px; float: left;">

								</div>

							</div>

							<div class="uiv2-checkoutblock-right">

							</div>

							<div id="shoplist-mask"

								style="display: none; width: 100%; height: 100%;"></div>

						</section>

					</div>

				</div>

			</div>

		</div>

	</div>

<form action="SubmitProductFeedback.action" name="feedbackForm" method="post">

	<div id="feedback_form" style="display: none;">



			<div id="uiv2-new-address-form" class="uiv2-popup"

				style="display: block;">



				<h3>Feedback Form</h3>

				<input type="hidden" name="selectedOrderId" id="selectedOrderId"/>



				<a href="javascript:void();" class="uiv2-popupclose"

					onClick="hide_code('feedback_form');">&nbsp;</a>



				<div id="uiv2-new-address-popup" class="uiv2-popup-wrapper">



					<div id="address_error"></div>



					<div class="uiv2-div100">



						<div class="uiv2-div100">



							<input type="hidden" name="productQuality" value="How is our product Quality?"/>

							<div class="uiv2-div40">

								<span class="uiv2-details-label"><label><b>How is our product Quality?</b></label> </span>

							</div>

							<div class="uiv2-div60">

								<input type="radio" required="required" name="productQualityAnswer" id="productQualityAnswer" value="very good"/>Very Good&nbsp;&nbsp;

								<input type="radio" required="required" name="productQualityAnswer" id="productQualityAnswer" value="good"/>Good&nbsp;&nbsp;

								<input type="radio" required="required" name="productQualityAnswer" id="productQualityAnswer" value="average"/>Average&nbsp;&nbsp;

								<input type="radio" required="required" name="productQualityAnswer" id="productQualityAnswer" value="low"/>Low<br/><br/>

							</div>

							<div class="uiv2-div40">

								<span class="uiv2-details-label" id="productQualityAnswerError"></span>

							</div>

							

						</div>

						<div class="uiv2-div100">

							<input type="hidden" name="packagingQuality" value="How is our packaging Service?"/>

							<div class="uiv2-div40">

								<span class="uiv2-details-label"><label><b>How is our packaging Service?</b></label> </span>

							</div>

							<div class="uiv2-div60">

								<input type="radio" required="required" name="packagingQualityAnswer" id="packagingQualityAnswer" value="very good"/>Very Good&nbsp;&nbsp;

								<input type="radio" required="required" name="packagingQualityAnswer" id="packagingQualityAnswer" value="good"/>Good&nbsp;&nbsp;

								<input type="radio" required="required" name="packagingQualityAnswer" id="packagingQualityAnswer" value="average"/>Average&nbsp;&nbsp;

								<input type="radio" required="required" name="packagingQualityAnswer" id="packagingQualityAnswer" value="low"/>Low<br/><br/>

							</div>

							<div class="uiv2-div40">

								<span class="uiv2-details-label" id="packagingQualityAnswerError"></span>

							</div>

						</div>

						<div class="uiv2-div100">

							<input type="hidden" name="deliveryService" value="How is our delivery Service?"/>

							<div class="uiv2-div40">

								<span class="uiv2-details-label"><label><b>How is our delivery Service?</b></label> </span>

							</div>

							<div class="uiv2-div60">

								<input type="radio" required="required" id="deliveryServiceAnswer" name="deliveryServiceAnswer" value="very good"/>Very Good&nbsp;&nbsp;

								<input type="radio" required="required" id="deliveryServiceAnswer" name="deliveryServiceAnswer" value="good"/>Good&nbsp;&nbsp;

								<input type="radio" required="required" id="deliveryServiceAnswer" name="deliveryServiceAnswer" value="average"/>Average&nbsp;&nbsp;

								<input type="radio" required="required" id="deliveryServiceAnswer" name="deliveryServiceAnswer" value="low"/>Low<br/><br/>

							</div>

							<div class="uiv2-div40">

								<span class="uiv2-details-label" id="deliveryServiceAnswerError"></span>

							</div>

						</div>

						<div class="uiv2-div100">

							<input type="hidden" name="shopMore" value="Would you like to shop more?"/>

							<div class="uiv2-div40">

								<span class="uiv2-details-label"><label><b>Would you like to shop more?</b></label> </span>

							</div>

							<div class="uiv2-div60">

								<input type="radio" required="required" id="shopMoreAnswer" name="shopMoreAnswer" value="Yes"/>Yes&nbsp;&nbsp;

								<input type="radio" required="required" id="shopMoreAnswer" name="shopMoreAnswer" value="No"/>No<br/><br/>

							</div>

							<div class="uiv2-div40">

								<span class="uiv2-details-label" id="shopMoreAnswerError"></span>

							</div>

						</div>

						<div class="uiv2-div100">

							<input type="hidden" name="suggesion" value="Any suggession to increase our quality of service?"/>

							<div class="uiv2-div40">

								<span class="uiv2-details-label"><label><b>Any suggession to increase our quality of service?</b></label> </span>

							</div>

							<div class="uiv2-div60">

								<textarea rows="5" cols="20" name="suggesionAnswer" id="suggesionAnswer"></textarea>

							</div>

							<div class="uiv2-div40">

								<span class="uiv2-details-label" id="suggesionAnswerError"></span>

							</div>

						</div>

					</div>

					<div class="uiv2-div60" style="margin-left: 10px;">



						<a id="id-add-new-address" href="javascript:void(0);"

							class="uiv2-button-default" onClick="submitFeedback();"><p>Submit Feedback</p> </a>

					</div>

				</div>

			</div>



			<div id="shadow-mask" style="display: block; z-index: 999;">&nbsp;</div>

		</div>

	</form>

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



