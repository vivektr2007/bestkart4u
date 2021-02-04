<!doctype html>
<%@page import="java.util.Map"%>
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
<link href="css/stylesheet.css" rel="stylesheet" type="text/css" />
<!--[if lt IE 9]><link href="//d3oi2nue850v1c.cloudfront.net/static/uiv2/css/ie8styles.css" rel="stylesheet" type="text/css">

<script src="//d3oi2nue850v1c.cloudfront.net/static/uiv2/js/html5shiv.js"></script>

<![endif]-->
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="js/jquery-ui.min.js" type="text/javascript"></script>
<link rel="stylesheet" href="css/jquery-ui-autocomplete.css" />
<%-- <script src="js/jquery.min.js" type="text/javascript"></script> --%>
<script src="js/jquery.cycle.js" type="text/javascript"></script>
<%-- <script src="js/jquery-ui.min.js" type="text/javascript"></script>

 --%>
<script src="js/uiv2_main.min.js" type="text/javascript"></script>
<script src="js/common.js" type="text/javascript"></script>
<script type="text/javascript">

       $(document).ready(function () {


    	   $("#deliveryDate").datepicker({
    		    // The hidden field to receive the date
    		    altField: "#deliveryDate",
    		    // The format you want
    		    altFormat: "dd/mm/yy",
    		    // The format the user actually sees
    		    dateFormat: "dd/mm/yy",
    		    onSelect: function (date) {
    		    	$("#deliveryDateError").text("");        
    		    }
    		});
    	   

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



             if(v1 === v2){



                 return options.fn(this);



             }



             return options.inverse(this);



         });







       });



   </script>
<script type="text/javascript">



	$(document).ready(function(){



		$('.uiv2-all-slot-timings div').click(function(){



			$('.uiv2-all-slot-timings div').removeClass('uiv2-selected');



			if($(this).hasClass('uiv2-selected')){



			}else{



				$(this).addClass('uiv2-selected');



			}



		});

		

		$('#place_order_cancel').click(function(){

			$('#place_order_content').css('display','none');

		});

		$('#coupon_code_cancel').click(function(){

			$('#coupon_code_success').css('display','none');

		});

		

		$('#selected_slot_cancel').click(function(){

			$('#selected_slot_success').css('display','none');

		});

		

	});



</script>
<script src="js/validation.js" type="text/javascript"></script>
<script type="text/javascript">







	function cancelBlock(id){



		document.getElementById(id+"-block").style.display	=	"none";



	}







	function add_new_address(){



		var failFlag = 0;



		$('[required]').each(function() {



			pass = validateRequired(this);



			if (!pass) {



				failFlag++;



			}



 		});

		if(parseInt(failFlag) > 0){



    		//$("#emailError").text("");



    		return false;



    	}



		document.addaddressform.submit();



	}



	function checkDAddress(){



		$("#deliverySpan").text('Selected');



		$("#selectedAdd").val($("#deliveryAdd").val());



		$("#permanentSpan").text('');



	}



	function checkPAddress(){



		$("#deliverySpan").text('');



		$("#selectedAdd").val($("#permanentAdd").val());



		$("#permanentSpan").text('Selected');



	}



	function submitOrder(){

		var selectedAdd = $("#selectedAdd").val();

		if(selectedAdd == '' || selectedAdd == " "){

			//alert("Please click on one address to select.");

			$('#place_order_content').css('display','block');

			return false;

		}

		var dateValid = validateDate();

		if(!dateValid){

			return false;

		}

		var flag = validateDateFromCurrentDate('deliveryDate');

		if(!flag){

			return false;

		}

		

		//validateTimeSlot();

		var currentdate = new Date(); 

		

		var formattedCurrentDate = new Date();

		formattedCurrentDate.setFullYear(currentdate.getFullYear());

		formattedCurrentDate.setMonth(currentdate.getMonth());

		formattedCurrentDate.setDate(currentdate.getDate());

		formattedCurrentDate.setHours(currentdate.getHours());

		formattedCurrentDate.setMinutes(currentdate.getMinutes());

		

		var selectedDeliveryDate = $("#deliveryDate").val();

		var deliveryDateArr = selectedDeliveryDate.split("/");

		

		var selectedSlot = $("input[type='radio'][name='available_slots']:checked").val();

		if(selectedSlot == 'undefined' || typeof selectedSlot === 'undefined' ){

        	$('#selected_slot_success p').text("Please select your delivery slot.");

			$('#selected_slot_success').css('display','block');

             return false;  

        }

		var deliveryDateFormated = new Date();

		deliveryDateFormated.setFullYear(deliveryDateArr[2]);

		deliveryDateFormated.setMonth(parseInt(deliveryDateArr[1])-1);

		deliveryDateFormated.setDate(deliveryDateArr[0]);

		

		var splitSlot = selectedSlot.split(":");

		deliveryDateFormated.setHours(splitSlot[0].trim());

		deliveryDateFormated.setMinutes(splitSlot[1].trim());

		

		var difference = deliveryDateFormated - formattedCurrentDate;

		var differenceMin = parseInt(difference)/(1000*60);

		

		

		if(differenceMin < 0){

			$('#selected_slot_success p').text("Please change your delivery slot or delivery date to continue.");

			$('#selected_slot_success').css('display','block');

			return false;

		}

		

		document.submitCart.action="SubmitCart.action";

		document.submitCart.submit();

		

	}

</script>
<style type="text/css">
.uiv2-slider-wrapper {
	margin-bottom: 0;
}

.uiv2-header-navigation-links {
	border-bottom: 1px solid #d9d9d7;
}
</style>
<script type="text/javascript">







	function changeSlot(obj){



		var val = $("#"+obj).html();

		$('#uiv2-uiv2-selected-slot-display').html(val);



	}

	function validateDate(){

		var dateOfBirth = $("#deliveryDate").val();
		$("#deliveryDateHidden").val(dateOfBirth);
		
		if(dateOfBirth != ''){
			$("#deliveryDateError").text("");
			var flag = isDate(dateOfBirth);

			if(!flag){

				$("#deliveryDateError").text("Delivery Date is not Valid.");

				return false;

			}

			else{

				$("#deliveryDateError").text("");

				return true;

			}

		}

		else{

			$("#deliveryDateError").text("Please enter Delivery Date.");

			return false;

		}

	}

	function isDate(txtDate)

	{

	    var currVal = txtDate;

	    if(currVal == ''){

	        return false;

	    }

	    var rxDatePattern = /^(\d{1,2})(\/|-)(\d{1,2})(\/|-)(\d{4})$/; 

	    var dtArray = currVal.match(rxDatePattern); 

	    if (dtArray == null){ 

	        return false;

	    }

	    dtMonth = dtArray[3];

	    dtYear= dtArray[5];

	    dtDay = dtArray[1];        

	    if (dtMonth < 1 || dtMonth > 12){ 

	        return false;

	    }

	    else if (dtDay < 1 || dtDay> 31){ 

	        return false;

	    }

	    else if ((dtMonth==4 || dtMonth==6 || dtMonth==9 || dtMonth==11) && dtDay ==31){ 

	        return false;

	    }

	    else if (dtMonth == 2) 

	    {

	        var isleap = (dtYear % 4 == 0 && (dtYear % 100 != 0 || dtYear % 400 == 0));

	        if (dtDay> 29 || (dtDay ==29 && !isleap)){ 

	                return false;

	        }

	    }

	    return true;

	}



</script>
<!-- Customised select box starts here -->
<link rel="stylesheet" type="text/css" href="css/select_custom.css" />
<script type="text/javascript" src="js/jquery.custom_radio_checkbox.js"></script>
<script type="text/javascript" language="javascript">



	function checkCouponCode(){

		var evoucher_code_input = $("#evoucher_code_input").val().trim();

		$("#couponCodeHidden").val(evoucher_code_input);

		var allAmount = $("#totalAmount").text().trim();

		$.get(

			    "CheckCouponCode.action",

			    {couponCode:evoucher_code_input,cartTotalAmount:allAmount},

			    function(data) { 

			    	if(data.indexOf("success") != -1){

			    		var couponCodeDetail = data.split(":");

			    		var disType = couponCodeDetail[1];

			    		if(disType != '' && disType.trim() == "%"){

			    			var discountedValue = (parseFloat(allAmount)*parseFloat(couponCodeDetail[2]))/100;

			    			var newAmount = parseFloat(allAmount) - parseFloat(discountedValue);

			    			$("#discount").text(discountedValue);

			    			$("#amountToPay").text(newAmount);

			    			$("#discountHidden").val(discountedValue);

			    			$("#amountToPayHidden").val(newAmount);

			    			

			    			$('#coupon_code_success p').text("Coupon Code applied successfully.");

			    			$('#coupon_code_success').css('display','block');

			    			

			    			

			    		}

			    		else if(disType != '' && disType.trim() == "-"){

			    			var discountedValue = (parseFloat(allAmount) - parseFloat(couponCodeDetail[2]));

			    			var newAmount = parseFloat(allAmount) - parseFloat(discountedValue);

			    			$("#discount").text(discountedValue);

			    			$("#discountHidden").val(discountedValue);

			    			$("#amountToPayHidden").val(newAmount);

			    			$("#amountToPay").text(newAmount);

			    			

			    			$('#coupon_code_success p').text("Coupon Code applied successfully.");

			    			$('#coupon_code_success').css('display','block');

			    		}

			    	}

			    	else{

			    		//alert(data);

			    		$('#coupon_code_success p').text(data);

			    		$('#coupon_code_success').css('display','block');

			    	}

			    },

			    "html"

			);

		

	}

	

</script>
<script type="text/javascript">



/* 	$(function(){



		$('select.styled').customSelect();



	}); */



</script>
<script type="text/javascript">



function orderSummary(){

	var c = document.cookie;

	if(c != "" || c != ''){

		var allValCookie = c.split(";");

		var total = 0;

		var saveValue = 0.0;

		var itemCount = 0;

		for(var i = 0; i<allValCookie.length; i++){

			if(allValCookie[i].indexOf("=") != -1){

				var allVal1 = allValCookie[i].split("=");

				if(allVal1[1].indexOf("-") != -1 && allVal1[1].split("-").length >= 10){

					itemCount = parseInt(itemCount)+1;		

					var allVal1 = allValCookie[i].split("=");

					var allVal = allVal1[1].split("-");

					total = total + parseFloat(allVal[2]);

					saveValue = saveValue + parseFloat(allVal[6]);

				}

			}

		}

		var deliveryCharge = "0.00";

		if(parseFloat(total) < 299){

			deliveryCharge = "25.00";

		}

		var allTotal = parseFloat(total) + parseFloat(deliveryCharge);

		$("#subTotal").text(total);

		$("#deliveryCharge").text(deliveryCharge);

		$("#deliveryChargeHidden").val(deliveryCharge);

		$("#totalAmount").text(allTotal);

		$("#amountToPay").text(allTotal);

		$("#saveAmount").text(saveValue);

		$("#discountHidden").val("0.00");

		$("#amountToPayHidden").val(allTotal);

		

		}

	}

</script>
<!-- Customised Select Box Ends Here -->
<style type="text/css"></style>
<script type="text/javascript">



	function show_promo(id){



		document.getElementById(id+'_block').style.display	=	"block";



		document.getElementById('shadow-mask').style.display	=	"block";



	}



	function hide_promo(id){



		document.getElementById(id).style.display	=	"none";



		document.getElementById('shadow-mask').style.display	=	"none";



	}

	function getCookies(){

		$("#myBasket").html("");

		var c = document.cookie;

		if(c != "" || c != ''){

			var allValCookie = c.split(";");

			var total = 0.0;

			var saveValue = 0.0;

			var itemCount = 0;

			for(var i = 0; i<allValCookie.length; i++){

				if(allValCookie[i].indexOf("=") != -1){

					var allVal1 = allValCookie[i].split("=");

					if(allVal1[1].indexOf("-") != -1 && allVal1[1].split("-").length >= 10){

						itemCount = parseInt(itemCount)+1;		

						var allVal = allVal1[1].split("-");

						total = total + parseFloat(allVal[2]);

						saveValue = saveValue + parseFloat(allVal[6]);

						var imgArr = allVal[7].split("#");

						var imagePath = "";

						for(var p = 0; p<imgArr.length;p++){

							imagePath=imagePath+"-"+imgArr[p];

						}

						imagePath = imagePath.substring(1);		

						var h = '<li id="cart1-block">'+'<div class="uiv2-items-img"><a href="javascript:void();"> <img src="'+imagePath+'" alt="'+allVal[5]+' - '+allVal[0]+'" width="65" height="61" /></a></div>'+

						'<div class="uiv2-items-content-wrapper"><div class="uiv2-items-content"><span><a href="javascript:void();">'+allVal[4]+'</a></span>'+

								'<p><a href="javascript:void();">'+allVal[5]+' - '+allVal[0]+'</a></p>'+

							'</div><div class="uiv2-items-button-block"><div class="uiv2-grid-count-btn">'+

									'<input type="text" name="quantity1" id="quantity1'+allVal1[0].trim()+'" value="'+allVal[3]+'" class="qty" />'+

									'</div><span class="uiv2-items-rate"><span class="WebRupee">Rs.</span> '+allVal[2]+'</span>'+

								'<div class="uiv2-items-saved-rate"><span>SAVED</span><span><span class="WebRupee">Rs.</span> '+allVal[6]+'</span></div>'+

							'</div></div></li>';

						h = $("#myBasket").html()+h;

						$("#myBasket").html(h);

						$("#quantity"+allVal1[0].trim()).val(allVal[3]);

						$("#quantityShow"+allVal1[0].trim()).val(allVal[3]);

				}

			}

			}

			$("#basketCount").text(itemCount + " items");

			$("#basketCount1").text(itemCount + " items");

			$("#totalBasket").html(total);

			$("#saveValue").html(saveValue);

			if(parseInt(itemCount) == 0){

				$("#myBasket").html('<div class="uiv2-items-content-wrapper empty_cart"><div class="uiv2-items-content"><span><a href="javascript:void();"></a></span>'+

				'<p><a href="javascript:void();"><br/>No Item in Kart. Please shop.</a></p>'+

				'</div></div>');

				$("#totalBasket").html("0.0");

				$("#saveValue").html("0.0");

				$("#basketCount").text("0 item");

				$("#basketCount1").text("0 item");

			}

		}

		else{

			$("#myBasket").html('<div class="uiv2-items-content-wrapper empty_cart"><div class="uiv2-items-content"><span><a href="javascript:void();"></a></span>'+

			'<p><a href="javascript:void();"><br/>No Item in Kart. Please shop.</a></p>'+

			'</div></div>');

			$("#totalBasket").html("0.0");

			$("#saveValue").html("0.0");

			$("#basketCount").text("0 item");

			$("#basketCount1").text("0 item");

		}

	}



</script>
<style type="text/css">
.review_order {
	background: #eaeaea;
	background: -webkit-gradient(linear, 0 0, 0 bottom, from(#FFFFFF 52%),
		to(#eaeaea 80%) );
	background: -webkit-linear-gradient(#FFF 52%, #EAEAEA 80%) );
	background: -moz-linear-gradient(#FFF 52%, #EAEAEA 80%);
	background: -ms-linear-gradient(#FFF 52%, #EAEAEA 80%);
	background: -o-linear-gradient(#FFF 52%, #EAEAEA 80%);
	background: linear-gradient(#FFF 52%, #EAEAEA 80%);
	-pie-background: linear-gradient(#FFF 52%, #EAEAEA 80%);
	margin: 23px 0px;
	padding: 13px 57px 13px 37px;
	font: 400 19px 'Roboto', serif;
	color: #555555;
	position: relative;
	border: 1px solid #EAEAEA;
	border-radius: 5px;
	-o-border-radius: 5px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
}

.review_order span {
	width: 33px;
	height: 24px;
	position: absolute;
	right: 15px;
	top: 13px;
	background: url(../images/main_sprite.png) no-repeat scroll left top
		rgba(0, 0, 0, 0);
	background-position: 14px -1799px;
}
</style>
<link rel="stylesheet" type="text/css" media="all"
	href="css/jsDatePick_ltr.min.css" />
<script type="text/javascript" src="js/jsDatePick.min.1.3.js"></script>
<script type="text/javascript">
	/* window.onload = function(){
		new JsDatePick({
			useMode:2,
			target:"deliveryDate",
			dateFormat:"%d/%m/%Y"
			/*selectedDate:{				This is an example of what the full configuration offers.
				day:5,						For full documentation about these settings please see the full version of the code.
				month:9,
				year:2006
			},
			yearsRange:[1978,2020],
			limitToToday:false,
			cellColorScheme:"beige",
			dateFormat:"%m-%d-%Y",
			imgPath:"img/",
			weekStartDay:1*/
		});
	}; */
</script>
<style type="text/css">
input.inputboxapply,textarea.inputaddress {
	font-family: Tahoma, Geneva, sans-serif;
	font-size: 12px;
	color: #000000;
}
</style>
<%-- <script src="js/cart.js" type="text/javascript"></script> --%>
</head>
<body onLoad="getCookies(),orderSummary()">
	<%-- <div id="popup_available_promos_block" class="uiv2-popup">



		<h3>The following promos are available for you</h3>



		<a href="javascript:void();" class="uiv2-popupclose"

			onClick="hide_promo('popup_available_promos_block');"></a>



		<div class="uiv2-popup-wrapper">



			<div id="promo_available_div" class="uiv2-div100">



				<div class="promo_offers">



					<div class="promo_offers_heading">



						<h2>

							<a href="javascript:void();">Amazing April Offers</a>

						</h2>



						<p>Enjoy your April Shopping with BB Offers</p>



					</div>



					<div class="promo_offers_content">

						<span class="uiv2-div100">Tramontina offers !!</span> <span

							class="uiv2-div100">Buy Tramontina Product Rs.600/- and</span> <span

							class="uiv2-div100">Get 10% Discount on MRP!</span>

					</div>



				</div>



				<div class="promo_offers">



					<div class="promo_offers_heading">



						<h2>

							<a href="javascript:void();">Amazing April Offers</a>

						</h2>



						<p>Enjoy your April Shopping with BB Offers</p>



					</div>



					<div class="promo_offers_content">

						<span class="uiv2-div100">Tramontina offers !!</span> <span

							class="uiv2-div100">Buy Tramontina Product Rs.600/- and</span> <span

							class="uiv2-div100">Get 10% Discount on MRP!</span>

					</div>



				</div>



				<div class="promo_offers">



					<div class="promo_offers_heading">



						<h2>

							<a href="javascript:void();">Amazing April Offers</a>

						</h2>



						<p>Enjoy your April Shopping with BB Offers</p>



					</div>



					<div class="promo_offers_content">

						<span class="uiv2-div100">Tramontina offers !!</span> <span

							class="uiv2-div100">Buy Tramontina Product Rs.600/- and</span> <span

							class="uiv2-div100">Get 10% Discount on MRP!</span>

					</div>



				</div>



			</div>



		</div>



	</div> --%>
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
					<div class="uiv2-basket-container">
						<div class="uiv2-title-basketwrap">
							<h4>Checkout</h4>
						</div>
						<div class="uiv2-basket">
							<div class="uiv2-leftcolumn">
								<div id="uiv2-checkout-delivery-address-container"
									class="checkout-border">
									<div id="uiv2-checkout-delivery-address-heading">
										<span>01 &nbsp;Delivery address</span>
										<div id="uiv2-btn-add-new-address">
											<button class="uiv2-continue-shopping-button"
												onClick="show_code('new_address');">ADD/CHANGE
												Delivery Address</button>
										</div>
									</div>
									<form action="#" method="post" name="submitCart">
										<input type="hidden" id="deliveryChargeHidden"
											name="deliveryChargeHidden" /> <input type="hidden"
											id="discountHidden" name="discountHidden" /> <input
											type="hidden" id="amountToPayHidden" name="amountToPayHidden" />
										<input type="hidden" id="couponCodeHidden"
											name="couponCodeHidden" /> <input type="hidden"
											name="selectedAdd" id="selectedAdd"> <input
											type="hidden" name="selectedAdd" id="selectedAdd">
										<div id="uiv2-checkout-delivery-address">
											<ul>
												<%
													Map address = (Map) request.getAttribute("address");

													//System.out.println("---------------"+address);

													Map permanentAddress = (Map) address.get("PERMANENTADDRESS");

													Map deliveryAddress = (Map) address.get("DELIVERYADDRESS");

													if (permanentAddress.get("HOUSENO") != null

													&& !permanentAddress.get("HOUSENO").equals("")) {

														String permAddr = "";
														if (permanentAddress.get("FIRSTNAME") != null) {
															permAddr = permAddr
																	+ permanentAddress.get("FIRSTNAME").toString();
														}
														if (permanentAddress.get("LASTNAME") != null) {
															permAddr = permAddr + " "
																	+ permanentAddress.get("LASTNAME").toString();
														}
														if (permanentAddress.get("HOUSENO") != null
																&& ((String) permanentAddress.get("HOUSENO")).length() != 0) {
															permAddr = permAddr + ","
																	+ permanentAddress.get("HOUSENO").toString();
														}
														if (permanentAddress.get("RESIDENTIALCOMPLEX") != null
																&& ((String) permanentAddress.get("RESIDENTIALCOMPLEX"))
																		.length() != 0) {
															permAddr = permAddr
																	+ ", "
																	+ permanentAddress.get("RESIDENTIALCOMPLEX")
																			.toString();
														}
														if (permanentAddress.get("STREETDETAIL") != null) {
															permAddr = permAddr + ", "
																	+ permanentAddress.get("STREETDETAIL").toString();
														}
														if (permanentAddress.get("CITY") != null
																&& ((String) permanentAddress.get("CITY")).length() != 0) {
															permAddr = permAddr + ", "
																	+ permanentAddress.get("CITY").toString();
														}
														if (permanentAddress.get("AREA") != null
																&& ((String) permanentAddress.get("AREA")).length() != 0) {
															permAddr = permAddr + ", "
																	+ permanentAddress.get("AREA").toString();
														}
														if (permanentAddress.get("PINCODE") != null) {
															permAddr = permAddr + ",Pin Code : "
																	+ permanentAddress.get("PINCODE").toString();
														}
														if (permanentAddress.get("LANDMARK") != null
																&& ((String) permanentAddress.get("LANDMARK")).length() != 0) {
															permAddr = permAddr + ",Near "
																	+ permanentAddress.get("LANDMARK").toString();
														}
														if (permanentAddress.get("CONTACTNO") != null
																&& ((String) permanentAddress.get("CONTACTNO"))
																		.length() != 0) {
															permAddr = permAddr + ",Contact No.  "
																	+ permanentAddress.get("CONTACTNO").toString();
														}
												%>

												<input type="hidden" name="permanentAddHidden"
													id="permanentAdd" value="<%=permAddr%>" />
												<li class="uiv2-selected"><a onClick="checkPAddress()"
													href="javascript:void()"> <span> <s:property
																value="address['PERMANENTADDRESS']['FIRSTNAME']" />
															&nbsp; <s:property
																value="address['PERMANENTADDRESS']['LASTNAME']" /> <br />
															<s:property
																value="address['PERMANENTADDRESS']['HOUSENO']" />
															&nbsp; <s:property
																value="address['PERMANENTADDRESS']['RESIDENTIALCOMPLEX']" />
															<br /> <s:property
																value="address['PERMANENTADDRESS']['STREETDETAIL']" />
															<br /> <s:property
																value="address['PERMANENTADDRESS']['AREA']" /> &nbsp; <s:property
																value="address['PERMANENTADDRESS']['CITY']" /> <br />
															<s:property
																value="address['PERMANENTADDRESS']['PINCODE']" /> <br />
															<s:property
																value="address['PERMANENTADDRESS']['LANDMARK']" /> <BR />
															# <s:property
																value="address['PERMANENTADDRESS']['CONTACTNO']" /> </span> <span
														class="uiv2-selected-span" id="permanentSpan"
														style="visibility: visible;"></span> </a></li>
												<%
													}

													if (deliveryAddress.get("DELIVERY_HOUSENO") != null

													&& !deliveryAddress.get("DELIVERY_HOUSENO").equals("")) {

														String delvAddr = "";
														if (deliveryAddress.get("DELIVERY_FIRSTNAME") != null) {
															delvAddr = delvAddr
																	+ deliveryAddress.get("DELIVERY_FIRSTNAME")
																			.toString();
														}
														if (deliveryAddress.get("DELIVERY_LASTNAME") != null) {
															delvAddr = delvAddr
																	+ " "
																	+ deliveryAddress.get("DELIVERY_LASTNAME")
																			.toString();
														}
														if (deliveryAddress.get("DELIVERY_HOUSENO") != null
																&& ((String) deliveryAddress.get("DELIVERY_HOUSENO"))
																		.length() != 0) {
															delvAddr = delvAddr
																	+ ", "
																	+ deliveryAddress.get("DELIVERY_HOUSENO")
																			.toString();
														}
														if (deliveryAddress.get("DELIVERY_RESIDENTIALCOMPLEX") != null
																&& ((String) deliveryAddress
																		.get("DELIVERY_RESIDENTIALCOMPLEX")).length() != 0) {
															delvAddr = delvAddr
																	+ ", "
																	+ deliveryAddress
																			.get("DELIVERY_RESIDENTIALCOMPLEX")
																			.toString();
														}
														if (deliveryAddress.get("DELIVERY_STREETDETAIL") != null
																&& ((String) deliveryAddress
																		.get("DELIVERY_STREETDETAIL")).length() != 0) {
															delvAddr = delvAddr
																	+ ","
																	+ deliveryAddress.get("DELIVERY_STREETDETAIL")
																			.toString();
														}
														if (deliveryAddress.get("DELIVERY_CITY") != null
																&& ((String) deliveryAddress.get("DELIVERY_CITY"))
																		.length() != 0) {
															delvAddr = delvAddr + ",  "
																	+ deliveryAddress.get("DELIVERY_CITY").toString();
														}
														if (deliveryAddress.get("DELIVERY_AREA") != null
																&& ((String) deliveryAddress.get("DELIVERY_AREA"))
																		.length() != 0) {
															delvAddr = delvAddr + ", "
																	+ deliveryAddress.get("DELIVERY_AREA").toString();
														}
														if (deliveryAddress.get("DELIVERY_PINCODE") != null) {
															delvAddr = delvAddr
																	+ ",Pin Code : "
																	+ deliveryAddress.get("DELIVERY_PINCODE")
																			.toString();
														}
														if (deliveryAddress.get("DELIVERY_LANDMARK") != null
																&& ((String) deliveryAddress.get("DELIVERY_LANDMARK"))
																		.length() != 0) {
															delvAddr = delvAddr
																	+ ",Near "
																	+ deliveryAddress.get("DELIVERY_LANDMARK")
																			.toString();
														}
														if (deliveryAddress.get("DELIVERY_CONTACTNO") != null
																&& ((String) deliveryAddress.get("DELIVERY_CONTACTNO"))
																		.length() != 0) {
															delvAddr = delvAddr
																	+ ",Contact No.  "
																	+ deliveryAddress.get("DELIVERY_CONTACTNO")
																			.toString();
														}
												%>
												<input type="hidden" name="deliveryAddHidden"
													id="deliveryAdd" value='<%=delvAddr%>'>
												<li class="uiv2-selected"><a onClick="checkDAddress()"
													href="javascript:void()"> <span> <s:property
																value="address['DELIVERYADDRESS']['DELIVERY_FIRSTNAME']" />
															&nbsp; <s:property
																value="address['DELIVERYADDRESS']['DELIVERY_LASTNAME']" />
															( <s:property
																value="address['DELIVERYADDRESS']['DELIVERY_NICKNAME']" />
															)<br /> <s:property
																value="address['DELIVERYADDRESS']['DELIVERY_HOUSENO']" />
															&nbsp; <s:property
																value="address['DELIVERYADDRESS']['DELIVERY_RESIDENTIALCOMPLEX']" />
															<br /> <s:property
																value="address['DELIVERYADDRESS']['DELIVERY_STREETDETAIL']" />
															<br /> <s:property
																value="address['DELIVERYADDRESS']['DELIVERY_AREA']" />
															&nbsp; <s:property
																value="address['DELIVERYADDRESS']['DELIVERY_CITY']" />
															<br /> <s:property
																value="address['DELIVERYADDRESS']['DELIVERY_PINCODE']" />
															<br /> <s:property
																value="address['DELIVERYADDRESS']['DELIVERY_LANDMARK']" />
															<BR /> # <s:property
																value="address['DELIVERYADDRESS']['DELIVERY_CONTACTNO']" />
													</span> <span class="uiv2-selected-span" id="deliverySpan"
														style="visibility: visible;"></span> </a></li>
												<%
													}
												%>
												<%
													if ((permanentAddress.get("HOUSENO") == null || permanentAddress

													.get("HOUSENO").equals(""))

															&& (deliveryAddress.get("DELIVERY_HOUSENO") == null || deliveryAddress

															.get("DELIVERY_HOUSENO").equals(""))) {
												%>
												<li><font color="red">Please add delivery

														address to continue.</font></li>
												<%
													}
												%>
											</ul>
										</div>
								</div>
								<div id="uiv2-checkout-choose-delivery-slot-container"
									class="checkout-border">
									<div id="uiv2-checkout-choose-delivery-slot-heading">
										<span>02 &nbsp;Choose your delivery slot</span>
									</div>
									<div id="uiv2-checkout-choose-delivery-slot">
										<div id="uiv2-next-available-slots">
											<div id="top_slots">
												<div id="uiv2-uiv2-selected-slot">
													Selected Slot: <span id="uiv2-uiv2-selected-slot-display">
														7:00 AM - 9:30 AM </span>
												</div>
												<div class="uiv2-next-n-slots">
													<p>
														Delivery Date (dd/mm/yyyy) : <input type="hidden"
															name="deliveryDateHidden" id="deliveryDateHidden" /> <input
															type="text" name="deliveryDate" class="inputboxapply"
															onBlur="validateDate()" readonly="readonly" id="deliveryDate" size="5" /> <!-- <img
															src="images/calendar.png" class="calendar-click" /> -->

														<!--<input type="text" name="deliveryDate" class="inputboxapply" style=" border:1px solid #cccccc"

															id="deliveryDate" onBlur="validateDate()" />
															
															<input type="text" name="inputField" size="12" id="inputField" globalnumber="710" />-->
														<span id="deliveryDateError" class="error"
															style="color: #BD4247; float: right; font-size: 12px;"></span>
													</p>
													<div class="uiv2-help-text">Select Time Slot</div>
													<!-- <form id="available-slots">

 -->
													<ul>
														<!-- <li>

														<input name="available_slots"

																onClick="changeSlot('slot2')" type="radio" value="9:30" />

														<label id="slot2"> 9:30 AM - 12:00 PM </label>

													</li>

													<li>

														<input name="available_slots"

																onClick="changeSlot('slot3')" type="radio" value="17:00" />

														<label id="slot3"> 5:00 PM - 7:30 PM</label>

													</li> -->
														<li><input name="available_slots"
															onClick="changeSlot('slot1')" type="radio" value="7:00"
															checked="checked" /> <label id="slot1"> 7:00 AM
																- 9:30 AM </label></li>
														<li><input name="available_slots"
															onClick="changeSlot('slot2')" type="radio" value="9:30" />
															<label id="slot2"> 9:30 AM - 12:00 PM </label></li>
														<li><input name="available_slots"
															onClick="changeSlot('slot3')" type="radio" value="17:00" />
															<label id="slot3"> 5:00 PM - 7:30 PM</label></li>
														<li><input name="available_slots"
															onClick="changeSlot('slot4')" type="radio" value="19:50" />
															<label id="slot4"> 7:30 PM - 10:00 PM</label></li>
													</ul>
													<!-- </form> -->
												</div>
											</div>
											<%-- <div class="uiv2-all-slots-box" id="uiv2-all-slots-box">



												<div id="uiv2-uiv2-selected-slot1">

													Selected Slot: <span id="uiv2-uiv2-selected-slot-display1">

														7:00 AM - 9:30 AM </span>

												</div>



												<div

													style="margin-left: 20px; margin-right: 15px; margin-top: 10px;">



													<div class="uiv2-all-slots">



														<div class="uiv2-all-slots-heading">



															<div>Day / Date</div>



															<div></div>



															<div>Time Slots</div>



															<div></div>



															<div></div>



														</div>



														<div class="uiv2-all-slot-timings">



															<div>Tomorrow</div>



															<div class="uiv2-not-available"

																id="Tomorrow 7:00 AM - 9:30 AM">7:00 AM - 9:30 AM</div>



															<div class="uiv2-not-available"

																id="Tomorrow 9:30 AM - 12:00 PM">9:30 AM - 12:00

																PM</div>



															<div class="uiv2-selected"

																id="Tomorrow 5:00 PM - 7:30 PM">5:00 PM - 7:30 PM</div>



															<div id="Tomorrow 7:30 PM - 10:00 PM">7:30 PM -

																10:00 PM</div>



														</div>



														<div class="uiv2-all-slot-timings">



															<div>03 Jun, Tuesday</div>



															<div id="03 Jun, Tuesday 7:00 AM - 9:30 AM">7:00 AM

																- 9:30 AM</div>



															<div id="03 Jun, Tuesday 9:30 AM - 12:00 PM">9:30

																AM - 12:00 PM</div>



															<div id="03 Jun, Tuesday 5:00 PM - 7:30 PM">5:00 PM

																- 7:30 PM</div>



															<div id="03 Jun, Tuesday 7:30 PM - 10:00 PM">7:30

																PM - 10:00 PM</div>



														</div>



														<div class="uiv2-all-slot-timings">



															<div>04 Jun, Wednesday</div>



															<div id="04 Jun, Wednesday 7:00 AM - 9:30 AM">7:00

																AM - 9:30 AM</div>



															<div id="04 Jun, Wednesday 9:30 AM - 12:00 PM">9:30

																AM - 12:00 PM</div>



															<div id="04 Jun, Wednesday 5:00 PM - 7:30 PM">5:00

																PM - 7:30 PM</div>



															<div id="04 Jun, Wednesday 7:30 PM - 10:00 PM">7:30

																PM - 10:00 PM</div>



														</div>



														<div class="uiv2-all-slot-timings">



															<div>05 Jun, Thursday</div>



															<div id="05 Jun, Thursday 7:00 AM - 9:30 AM">7:00

																AM - 9:30 AM</div>



															<div id="05 Jun, Thursday 9:30 AM - 12:00 PM">9:30

																AM - 12:00 PM</div>



															<div id="05 Jun, Thursday 5:00 PM - 7:30 PM">5:00

																PM - 7:30 PM</div>



															<div id="05 Jun, Thursday 7:30 PM - 10:00 PM">7:30

																PM - 10:00 PM</div>



														</div>



														<div class="uiv2-all-slot-timings">



															<div>06 Jun, Friday</div>



															<div id="06 Jun, Friday 7:00 AM - 9:30 AM">7:00 AM

																- 9:30 AM</div>



															<div id="06 Jun, Friday 9:30 AM - 12:00 PM">9:30 AM

																- 12:00 PM</div>



															<div id="06 Jun, Friday 5:00 PM - 7:30 PM">5:00 PM

																- 7:30 PM</div>



															<div id="06 Jun, Friday 7:30 PM - 10:00 PM">7:30 PM

																- 10:00 PM</div>



														</div>



														<div class="uiv2-all-slot-timings">



															<div>07 Jun, Saturday</div>



															<div id="07 Jun, Saturday 7:00 AM - 9:30 AM">7:00

																AM - 9:30 AM</div>



															<div id="07 Jun, Saturday 9:30 AM - 12:00 PM">9:30

																AM - 12:00 PM</div>



															<div id="07 Jun, Saturday 5:00 PM - 7:30 PM">5:00

																PM - 7:30 PM</div>



															<div id="07 Jun, Saturday 7:30 PM - 10:00 PM">7:30

																PM - 10:00 PM</div>



														</div>



													</div>



												</div>



												<div class="uiv2-time-slots-legends"

													style="margin-left: 20px; margin-right: 15px;">



													<span class="uiv2-not-available"></span><span>Not

														Available</span> <span class="uiv2-selected"></span><span>Selected</span>



												</div>



											</div> --%>
											<%-- <div id="uiv2-view-all-slots"><span>LOOKING FOR ANOTHER SLOT ?</span>



											<button class="uiv2-continue-shopping-button" id="uiv2-continue-shopping-button">VIEW ALL SLOTS</button>



										</div>



										 --%>
										</div>
									</div>
								</div>
								<div id="uiv2-checkout-choose-your-payment-container"
									class="checkout-border">
									<div id="uiv2-checkout-choose-your-payment-heading">
										<span>03 &nbsp;Choose your payment options</span>
									</div>
									<div id="uiv2-pay-remaining-amount-options">
										<p>Select option from below to pay the remaining amount :</p>
										<div>
											<input name="payment_type" type="radio" value="cod"
												checked=""> <label>Pay By Cash (or Sodexo
												coupons) on delivery</label>
										</div>
										<%-- <div>



										<input name="payment_type" type="radio" value="payu">



										<label>Pay By PayUMoney Wallet* / Credit Card / Debit Card / Net Banking</label>



									</div>



									<div>



										<input name="payment_type" type="radio" value="amex">



										<label>Pay By American Express Credit Card</label>



									</div>



									<div>



										<input name="payment_type" type="radio" value="ezeclick">



										<label>Pay By American Express ezeClick</label>



									</div>



									<span style="font-size:12px;">* When you pay by PayUMoney Wallet, 5% cashback added to your PayUMoney Wallet (subject to maximum Rs 50 per transaction). 



									See <a href="javascript:void();">terms and conditions</a>



									</span> --%>
									</div>
								</div>
								<a href="javascript:void();" onClick="submitOrder()"
									class="uiv2-checkout-button this_form place-order"
									style="float: right;">
									<p>PLACE ORDER</p> <span class="icon icon-navigating-right"></span>
								</a> <a href="index.jsp" class="review_order" style="float: left;">
									<p>REVIEW ORDER</p> <span class="icon icon-navigating-right"></span>
								</a>
							</div>
							</form>
							<div class="uiv2-right-column">
								<!-- Start - Right Column -->
								<div id="uiv2-evoucher-code" class="checkout-border">
									<div id="uiv2-evoucher-title">Coupon Code (optional)</div>
									<div id="uiv2-evoucher-input">
										<input name="evoucherCode" id="evoucher_code_input"
											type="text">
										<!-- onclick="show_code('evoucher');" -->
										<button class="uiv2-continue-shopping-button"
											onClick="checkCouponCode();">APPLY</button>
									</div>
								</div>
								<div id="uiv2-order-summary-box">
									<style type="text/css">
.uiv2-delivery-charge,.uiv2-delivery-token {
	width: 90%;
	display: block;
	padding: 0 0 22px 0;
	margin: 0 0 0 7px;
}

.uiv2-delivery-charge span,.uiv2-delivery-token span {
	text-align: left;
	float: left;
	clear: right;
}

.uiv2-delivery-charge span+span,.uiv2-delivery-token span+span {
	text-align: right;
	float: right;
}

.uiv2-line-separator {
	border-top: 1px solid #C5C6C9;
	margin: 2px 10px;
}

.uiv2-total-amount {
	border: 0
}
</style>
									<div class="uiv2-order-summary">
										<div class="uiv2-order-summary-title">Your Order Summary</div>
										<div class="uiv2-sub-total">
											<span>Subtotal</span><span><span class="WebRupee">Rs.</span>
												<span id="subTotal"></span> </span>
										</div>
										<div class="uiv2-delivery-charge">
											<span>Delivery Charge</span><span><span
												class="WebRupee">Rs.</span><span id="deliveryCharge"></span>
											</span>
										</div>
										<div id="voucher-discount"></div>
										<div class="uiv2-line-separator"></div>
										<div id="total-amount" class="uiv2-total-amount">
											<span>Total Amount</span><span><span class="WebRupee">Rs.</span><span
												id="totalAmount"></span> </span>
										</div>
										<%-- <div id="total-amount" class="uiv2-total-amount">

											<span>You Saved!</span><span><span class="WebRupee">Rs.</span><span

												id="saveAmount"></span> </span>

										</div>

 --%>
										<div id="total-amount" class="uiv2-total-amount">
											<span>Discount!</span><span><span class="WebRupee">Rs.</span><span
												id="discount">0.00</span> </span>
										</div>
										<div class="uiv2-line-separator"></div>
										<div class="uiv2-amount-to-Pay-total">
											<span>Amount to Pay</span><span><span class="WebRupee">Rs.</span>
												<span id="amountToPay"></span> </span>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="main_wrapper">
		<div class="wrapper"></div>
	</div>
	<div class="footer_wrapper">
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
	<div id="evoucher" style="display: none;">
		<div class="uiv2-popup" style="display: block;">
			<h3>Error in assigning this voucher</h3>
			<a href="javascript:void();" class="uiv2-popupclose"
				onClick="hide_code('evoucher');">&nbsp;</a>
			<div class="uiv2-popup-wrapper">
				<p>Your checkout session has expired. Please start the checkout

					process again.</p>
			</div>
		</div>
		<div id="shadow-mask" style="display: block; z-index: 999;">&nbsp;</div>
	</div>
	<form action="AddAddress.action" method="post" name="addaddressform">
		<div id="new_address" style="display: none;">
			<div id="uiv2-new-address-form" class="uiv2-popup"
				style="display: block;">
				<h3>Add New Address</h3>
				<a href="javascript:void();" class="uiv2-popupclose"
					onClick="hide_code('new_address');">&nbsp;</a>
				<div id="uiv2-new-address-popup" class="uiv2-popup-wrapper">
					<div id="address_error"></div>
					<div class="uiv2-div100">
						<div class="uiv2-div100">
							<div class="uiv2-div40">
								<span class="uiv2-details-label"> <label>Address

										Nickname</label> </span>
							</div>
							<div class="uiv2-div60">
								<input type="text" required='required' name="nickName"
									maxlength="250" /> <span class="uiv2-example-r"></span>
							</div>
						</div>
					</div>
					<div class="uiv2-div100">
						<div class="uiv2-div100">
							<div class="uiv2-div40">
								<span class="uiv2-details-label"> <label>Contact

										First Name</label> </span>
							</div>
							<div class="uiv2-div60">
								<input id="" type="text" required='required'
									name="deliveryFirstName" value="" maxlength="250" /> <span
									class="uiv2-field-required">*</span>
							</div>
						</div>
					</div>
					<div class="uiv2-div100">
						<div class="uiv2-div100">
							<div class="uiv2-div40">
								<span class="uiv2-details-label"> <label>Contact

										Last Name</label> </span>
							</div>
							<div class="uiv2-div60">
								<input type="text" name="deliverLastName" required='required'
									value="" maxlength="250" /> <span class="uiv2-field-required">*</span>
							</div>
						</div>
					</div>
					<div class="uiv2-div100">
						<div class="uiv2-div100">
							<div class="uiv2-div40">
								<span class="uiv2-details-label"> <label>Contact

										number</label> </span>
							</div>
							<div class="uiv2-div60">
								<label style="margin-left: -22px;">+91</label> <input
									type="text" name="deliveryContactNo"
									onkeypress="return isNumberKey(event)" required='required'
									value="" maxlength="10" /> <span class="uiv2-field-required">*</span>
							</div>
						</div>
					</div>
					<%-- <div class="uiv2-div100 uiv2-make-default-address-container">



				<div class="uiv2-div100">



					<div class="uiv2-div60 uiv2-make-default-address-rbtn">



						<input type="checkbox" name="use_contact_number" id="id_use_contact_number" />



						<span id="error_use_contact_number" class="uiv2-example-r"></span>



					</div>



					<div class="uiv2-div40 uiv2-make-default-address-label">



						<span class="uiv2-details-label"><label style="line-height:36px;">Call above number for communication</label></span>



					</div>



				</div>



			</div> --%>
					<div class="uiv2-div100">
						<div class="uiv2-div100">
							<div class="uiv2-div40">
								<span class="uiv2-details-label"> <label>House no

										&amp; details</label> </span>
							</div>
							<div class="uiv2-div60">
								<input type="text" required='required' name="deliveryHouseNo"
									maxlength="250" /> <span class="uiv2-field-required">*</span>
							</div>
						</div>
					</div>
					<div class="uiv2-div100">
						<div class="uiv2-div100">
							<div class="uiv2-div40">
								<span class="uiv2-details-label"> <label>Street

										details</label> </span>
							</div>
							<div class="uiv2-div60">
								<input id="" type="text" name="deliveryStreeDetail"
									maxlength="250" />
							</div>
						</div>
					</div>
					<div class="uiv2-div100">
						<div class="uiv2-div100">
							<div class="uiv2-div40">
								<span class="uiv2-details-label"> <label>Residential

										Complex</label> </span>
							</div>
							<div class="uiv2-div60">
								<input type="text" name="deliveryResidentialComplex"
									maxlength="100" />
							</div>
						</div>
					</div>
					<div class="uiv2-div100">
						<div class="uiv2-div100">
							<div class="uiv2-div40">
								<span class="uiv2-details-label"> <label>Landmark</label>
								</span>
							</div>
							<div class="uiv2-div60">
								<input type="text" name="deliveryLandmark" maxlength="100" />
							</div>
						</div>
					</div>
					<div class="uiv2-div100">
						<div class="uiv2-div100">
							<div class="uiv2-div40">
								<span class="uiv2-details-label"> <label>Area</label> </span>
							</div>
							<div class="uiv2-div60">
								<input type="text" name="deliveryArea" maxlength="250"
									autocomplete="off" /> <span class="uiv2-field-required">*</span>
							</div>
						</div>
					</div>
					<div class="uiv2-div100">
						<div class="uiv2-div100">
							<div class="uiv2-div40">
								<span class="uiv2-details-label"> <label>Pin Code</label>
								</span>
							</div>
							<div class="uiv2-div60">
								<input type="text" required='required'
									onkeypress="return isNumberKey(event);" name="deliveryPincode" />
								<span class="uiv2-field-required">*</span>
							</div>
						</div>
					</div>
					<div class="uiv2-div100">
						<div class="uiv2-div100">
							<div class="uiv2-div40">
								<span class="uiv2-details-label"> <label>City</label> </span>
							</div>
							<div class="uiv2-div60">
								<input readonly="readonly" required='required' type="text"
									name="deliveryCity" value="Dwarka" id="id_city" /> <span
									class="uiv2-field-required">*</span>
							</div>
						</div>
					</div>
					<%-- <div class="uiv2-div100 uiv2-make-default-address-container">



				<div class="uiv2-div100">



					<div class="uiv2-div60 uiv2-make-default-address-rbtn">



						<input type="checkbox" name="" id="" />



					</div>



					<div class="uiv2-div40 uiv2-make-default-address-label">



						<span class="uiv2-details-label"><label style="line-height:36px;">Make this my default delivery address</label></span>



					</div>



				</div>



			</div> --%>
					<div class="uiv2-div60" style="margin-left: 10px;">
						<a id="id-add-new-address" href="javascript:void(0);"
							class="uiv2-button-default" onClick="add_new_address();">
							<p>CREATE ADDRESS</p> </a>
					</div>
				</div>
			</div>
			<div id="shadow-mask" style="display: block; z-index: 999;">&nbsp;</div>
		</div>
	</form>
	<div class="uiv2-copyright">
		<p>
			Copyright &copy; 2014-<span id="current-year"></span> Supermarket

			Grocery Supplies Pvt Ltd
		</p>
	</div>
	<div id="place_order_content" style="display: none;">
		<div class="show_payment">
			<div class="payment_content_sub">
				<div class="payment_content1">
					<div class="feedback_block">
						<h1>Delivery address missing</h1>
						<p>Please click on an address to select a delivery address
							before we proceed.</p>
					</div>
					<a class="popup-close" href="javascript:void():"
						id="place_order_cancel">&nbsp;</a>
				</div>
			</div>
		</div>
		<div class="payment_overlay">&nbsp;</div>
	</div>
	<div id="coupon_code_success" style="display: none;">
		<div class="show_payment">
			<div class="payment_content_sub">
				<div class="payment_content1">
					<div class="feedback_block">
						<h1>Coupon Code</h1>
						<p>Coupon Code applied successfully.</p>
					</div>
					<a class="popup-close" href="javascript:void():"
						id="coupon_code_cancel">&nbsp;</a>
				</div>
			</div>
		</div>
		<div class="payment_overlay">&nbsp;</div>
	</div>
	<div id="selected_slot_success" style="display: none;">
		<div class="show_payment">
			<div class="payment_content_sub">
				<div class="payment_content1">
					<div class="feedback_block">
						<h1>Delivery Date/time</h1>
						<p>Coupon Code applied successfully.</p>
					</div>
					<a class="popup-close" href="javascript:void():"
						id="selected_slot_cancel">&nbsp;</a>
				</div>
			</div>
		</div>
		<div class="payment_overlay">&nbsp;</div>
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
