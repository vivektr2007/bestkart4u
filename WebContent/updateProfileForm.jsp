<!doctype html>

<%@page import="com.retailshop.action.CaptchaAction"%>

<%@page import="java.io.IOException"%>

<%@page import="javax.imageio.ImageIO"%>

<%@page import="java.io.File"%>

<%@page import="java.awt.image.BufferedImage"%>

<%@page import="com.retailshop.util.MyCaptchaClass"%>

<%@taglib uri="/struts-tags" prefix = "s" %>

<html>

<head>

<meta charset="utf-8">

<meta http-equiv="X-UA-Compatible" content="IE-EmulateIE8">

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

<title>BestKart4U : Best online Store and Supermarket in India</title>

<link href="http://fonts.googleapis.com/css?family=Roboto:400,300" rel="stylesheet" type="text/css" />

<link href="http://fonts.googleapis.com/css?family=Lato:600,300,400,700" rel="stylesheet" type="text/css" />

<link href="http://fonts.googleapis.com/css?family=Roboto+Slab:700,500,400,300,100" rel="stylesheet" type="text/css" />
<link rel="icon" type="image/png" href="images/favicon.ico" />
<!--<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/themes/overcast/jquery-ui.min.css" type="text/css" media="all" />-->

<link href="css/stylesheet.css" rel="stylesheet" type="text/css" />

<!--[if lt IE 9]><link href="//d3oi2nue850v1c.cloudfront.net/static/uiv2/css/ie8styles.css" rel="stylesheet" type="text/css">

<script src="//d3oi2nue850v1c.cloudfront.net/static/uiv2/js/html5shiv.js"></script>

<![endif]-->

<%-- <script src="js/jquery.min.js" type="text/javascript"></script>
 --%>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="js/jquery.cycle.js" type="text/javascript"></script>
<script src="js/jquery-ui.min.js" type="text/javascript"></script>
<link rel="stylesheet" href="css/jquery-ui-autocomplete.css" />
<script src="js/passwordStrength.js" type="text/javascript"></script>

<%-- <script src="js/jquery-ui.min.js" type="text/javascript"></script>
 --%>
<script src="js/uiv2_main.min.js" type="text/javascript"></script>

<script type="text/javascript">
$(document).ready(function(){
	$('.add_this_content').hide();
});
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

                if(v1 === v2){

                    return options.fn(this);

                }

                return options.inverse(this);

            });



        });

    </script>

    <script type="text/javascript">

    	function reloadCaptcha(){	

    		$("#imgcaptcha").attr("src","CaptchaAction.action?num="+Math.random());

    	}

    	

    </script>

    

<script src="js/facets.min.js" type="text/javascript"></script>

<script src="js/validation.js" type="text/javascript"></script>

<style type="text/css">

.uiv2-slider-wrapper {

	margin-bottom: 0;

}

.uiv2-header-navigation-links {

	border-bottom:1px solid #d9d9d7;

}

</style>

<!-- Customised select box starts here -->

<link rel="stylesheet" type="text/css" href="css/select_custom.css" />

<link rel="stylesheet" type="text/css" href="css/passwordStrength.css" />
<script type="text/javascript" src="js/jquery.custom_radio_checkbox.js"></script>
<script type="text/javascript" language="javascript">

	$(document).ready(function(){

		

		/* $("#datepicker").datepicker({

			showOn : "button",

			buttonImage : "images/icons/calendar.gif",

			buttonImageOnly : true,

			changeMonth : true,

			changeYear : true,

			showButtonPanel : true,

			yearRange : "-100:+0",

			dateFormat : "dd/mm/yy",

			onSelect : function() {

				$("#datepicker").blur();

			}

		}); */

		

		$(".radio").dgStyle();

		$(".checkbox").dgStyle();

	});

	

	

	

	

</script>

<script type="text/javascript">

	/* $(function(){

		$('select.styled').customSelect();

	});
 */
</script>

<!-- Customised Select Box Ends Here -->



<link rel="stylesheet" type="text/css" href="css/form.css" />

<link rel="stylesheet" type="text/css" media="all" href="jsDatePick_ltr.min.css" />

<%-- <script type="text/javascript" src="jsDatePick.min.1.3.js"></script>

<script type="text/javascript">

	window.onload = function(){

		new JsDatePick({

			useMode:2,

			target:"inputField",

			dateFormat:"%d-%M-%Y",

			selectedDate:{				//This is an example of what the full configuration offers.

				day:5,					//	For full documentation about these settings please see the full version of the code.

				month:9,

				year:2006

			},

			yearsRange:[1978,2020],

			limitToToday:false,

			cellColorScheme:"beige",

			dateFormat:"%m-%d-%Y",

			imgPath:"img/",

			weekStartDay:1

		});

	};

</script> --%>

<script src="js/cart.js" type="text/javascript"></script>
<script src="js/common.js" type="text/javascript"></script>
<script type="text/javascript">

	function regiserAction(){

		

		var failFlag = 0;

		$('[required]').each(function() {

			pass = validateRequired(this);

			if (!pass) {

				failFlag++;

			}

 		});

		

		var pass1 = $("#id_password2").val();

		var pass2 = $("#password").val();

		var passwordStrength = $("#result").text().trim();

		if(passwordStrength == 'Weak' || passwordStrength == 'Too short'){

			$("#passError").text("");

			$("#passError").text("Password can't be Too short or Weak.");

			failFlag++;

		}

		if(pass1 != pass2){

			$("#passError").text("");

			$("#passError").text("Password and Confirm-Password must be same.");

			failFlag++;

		}

		else{

			$("#passError").text("");

		}

		

		/* var valu = $("#id_terms").is(":checked");

		if(valu == 'false' || valu == false){

			$("#checkboxError").text("");

			$("#checkboxError").text("Please accept terms and conditions.");

			failFlag++;

		}

		else{

			$("#checkboxError").text("");

		}
 */
		var dateOfBirth = $("#dob").val();

		if(dateOfBirth != ''){

			var flag = isDate(dateOfBirth);

			if(!flag){

				failFlag++;

				$("#dob").next("div").remove();

				$("#dob").addClass("error");

				$("#dob").after($("<div id='errorDiv' class='error' style='color: #BD4247;'> Date of Birth is not Valid. </div>"));

				return false;

			}

			flag = checkAge(dateOfBirth);

			if(!flag){

				failFlag++;

				$("#dob").next("div").remove();

				$("#dob").addClass("error");

				$("#dob").after($("<div id='errorDiv' class='error' style='color: #BD4247;'> Age must be greater than 18 years. </div>"));

				return false;

			}  

		} 

		

    	if(parseInt(failFlag) > 0){

    		$("#emailError").text("");

    		return false;

    	}

    	else{

    		$("#emailError").text("");

    		document.registerform.submit();

    	}

		

	}

	function checkEmail(){

		var email = $("#id_email").val();

		$.get(

		    "checkEmailID.action",

		    {emailId:email},

		    function(data) { 

		    	 if(data == 'true' || data == true){

		    		$("#emailError").text("Email Id is already registered"); 

		    		

		    		return false;

		    	}

		    	 else{

		    		 $("#emailError").text("");

		    	 }

			});

	}	

	/* function acceptTerms(){

		

		var valu = $("#id_terms").is(":checked");

		if(valu == 'false' || valu == false){

			$("#checkboxError").text("");

			$("#checkboxError").text("Please accept terms and conditions.");

		}

		else{

			$("#checkboxError").text("");

		}

	}	
 */
</script>

<script>



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



function checkAge(value){

	var selectedDate = value.split("/");

	var day = selectedDate[0];

	var month = selectedDate[1];

	var year = selectedDate[2];

	var age = 18;

	var mydate = new Date();

	mydate.setFullYear(year, month-1, day);



	var currdate = new Date();

	currdate.setFullYear(currdate.getFullYear() - age);

	if ((currdate - mydate) < 0){

		return false;

	}

	return true;

}



function validateDate(){

	

	var dateOfBirth = $("#dob").val();

	if(dateOfBirth != ''){

		var flag = isDate(dateOfBirth);

		if(!flag){

			$("#dobError").text("Date of Birth is not Valid.");

			return false;

		}

		else{

			$("#dobError").text("");

		}

		flag = checkAge(dateOfBirth);

		if(!flag){

			$("#dobError").text("Age must be greater than 18 years.");

			return false;

		}  

		else{

			$("#dobError").text("");

		}

	} 

	

}



</script>

</head>

<body onLoad="getCookies()">

<div id="shadow-mask">&nbsp;</div>



<jsp:include page="header.jsp"></jsp:include>



<div class="main_wrapper">

	<div class="uiv2-navigation-slider">

		<div class="uiv2-left-navigation">

			<div class="uiv2-nav uiv2-pointer" id="uiv2-menu-bar">SHOP <span class="uiv2-nav-icon"></span></div>

			<!-- Navigation starts here -->

			

			<jsp:include page="productListInner.jsp"></jsp:include>

			

			<!-- Navigation ends here -->

		</div>

		

		<jsp:include page="menuPage.jsp"></jsp:include>

		

		

		<div class="uiv2-content-wrapper">

			<div class="uiv2-content-wrapper">

				<div class="uiv2-padding-vertical-60" style=" padding:60px 30px;">

					<form name="registerform" method="POST" action="UpdateProfile.action" class="uiv2-form" id="id_register_form">

						<fieldset>

							<div class="legend">Login Details</div>

							

							<div class="uiv2-form-row" style="text-align:center">

								<font color="red"><s:property value="errorMsg"/></font>

							</div>

							

							<div class="uiv2-form-row">

								<span class="uiv2-form-label">Email Address <span class="uiv2-field-required">*</span> </span>

								<div class="uiv2-form-input">

									<span><input id="id_email" type="email" name="emailId" readonly="readonly" value='<s:property value="regBean.getEmailId()"/>' size="20" /></span>

									<span id="emailError" class='error' style='color: #BD4247;'></span>

								</div>

							</div>

							<div class="uiv2-form-row">

								<span class="uiv2-form-label">Mobile Number <span class="uiv2-field-required">*</span> </span>

								<div class="uiv2-form-input">

									<span class="uiv2-input-prepend">+91</span>

									<span class="uiv2-input-with-prepend">

										<input name="mobileNo" required="required" maxlength="10" value='<s:property value="regBean.getMobileNo()"/>'  onkeypress="return isNumberKey(event)" type="text" id="id_mobile_no" size="15" />

									</span>

								</div>

							</div>

						</fieldset>

						<fieldset>

							<div class="legend">Personal Details</div>

							<div class="uiv2-form-row">

								<span class="uiv2-form-label">Salutation <span class="uiv2-field-required">*</span> </span>

								<div class="uiv2-form-input">

									<span class="uiv2-mar-t-8">

											<s:select list="#{'Mr.':'Mr.','Mrs.':'Mrs.','Ms.':'Ms.'}" headerKey="" headerValue="Select" cssStyle=" width:212px;"  value='<s:property value="regBean.getSalutaion()"/>' key="salutation" required="required" id="salutaion"/>

									</span>

								</div>

							</div>

							<div class="uiv2-form-row">

								<span class="uiv2-form-label">First Name <span class="uiv2-field-required">*</span> </span>

								<div class="uiv2-form-input">

									<span>

										<input id="id_first_name" value='<s:property value="regBean.getFirstName()"/>'  required="required" type="text" name="firstName" size="30" />

									</span>

								</div>

							</div>

							<div class="uiv2-form-row">

								<span class="uiv2-form-label">Last Name <span class="uiv2-field-required">*</span> </span>

								<div class="uiv2-form-input">

									<span>

										<input id="id_last_name" value='<s:property value="regBean.getLastName()"/>'  required="required" type="text" name="lastName" size="30" />

									</span>

								</div>

							</div>

							<div class="uiv2-form-row">

								<span class="uiv2-form-label">Date Of Birth <span class="uiv2-field-required">*</span> </span>

								<div class="uiv2-form-input">

							

										<input type="text" value='<s:property value="regBean.getDob()"/>'  required="required" class="inputboxapply" onChange="validateDate()" style="border:1px solid #cccccc" name="dob" size="12" id="dob" />

										

										<span id="dobError" class='error' style='color: #BD4247;'></span>

										<div class="uiv2-help-text">(dd/mm/yyyy)</div>

								</div>

							</div>

							<div class="uiv2-form-row">

								<span class="uiv2-form-label">Telephone Number</span>

								<div class="uiv2-form-input">

									<span>

										<input id="id_phone_no" value='<s:property value="regBean.getTelephoneNo()"/>'  type="text" name="telephoneNo" size="15" />

									</span>

								</div>

							</div>

						</fieldset>

						<fieldset>

						<div class="legend">Address Details</div>

						<div class="uiv2-form-row">

							<span class="uiv2-form-label">House no &amp; Details <span class="uiv2-field-required">*</span> </span>

							<div class="uiv2-form-input">

								<span>

									<input id="id_address1" value='<s:property value="regBean.getHouseDetail()"/>'  required="required" type="text" name="houseDetail" size="30" />

								</span>

							</div>

						</div>

						<div class="uiv2-form-row">

							<span class="uiv2-form-label">Street Details</span>

							<div class="uiv2-form-input">

								<span>

									<input id="id_address2" value='<s:property value="regBean.getStreetDetail()"/>'  type="text" name="streetDetail" size="30" />

								</span>

							</div>

						</div>

						<div class="uiv2-form-row">

							<span class="uiv2-form-label">Area <span class="uiv2-field-required">*</span> </span>

							<div class="uiv2-form-input">

								<span><span role="status" aria-live="polite" class="ui-helper-hidden-accessible"></span>

									<input id="id_area" value='<s:property value="regBean.getArea()"/>' type="text" name="area" required="required" size="30" class="ui-autocomplete-input" autocomplete="off" />

								</span>

							</div>

						</div>

						<div class="uiv2-form-row">

							<span class="uiv2-form-label">Residential Complex</span>

							<div class="uiv2-form-input">

								<span>

									<input id="id_residential_complex" value='<s:property value="regBean.getResidentComplex()"/>'  type="text" name="residentComplex" size="30" />

								</span>

							</div>

						</div>

						<div class="uiv2-form-row">

							<span class="uiv2-form-label">Landmark</span>

							<div class="uiv2-form-input">

								<span>

									<input id="id_landmark" value='<s:property value="regBean.getLandmark()"/>'  type="text" name="landmark" size="30" />

								</span>

							</div>

						</div>

						<div class="uiv2-form-row">

							<span class="uiv2-form-label">City <span class="uiv2-field-required">*</span> </span>

							<div class="uiv2-form-input">

								<span class="uiv2-push-left uiv2-mar-t-8">

									<select style=" width:212px;" id="city" name="city" required>

										<option value="">Select</option>

										<option value="Mr.">Dwarka(New Delhi)</option>

									</select>

								</span>

								<span class="uiv2-push-left">

									<script type="text/javascript"></script>

									<div class="uiv2-small-pop-up" id="small_popup_pin_info" style="display: none;">

										<span class="uiv2-pop-up-pointer"></span>

										<div class="uiv2-div100">

											<div class="uiv2-div20"><span class="icon icon-bulb-pop-up uiv2-push-left"></span></div>

											<div class="uiv2-div80">

												<%-- <span class="uiv2-pop-up-text">Currently we serve in Dwarka(New Delhi). <br />

													<br />

													Once registered, the delivery city on the account cannot be changed.

													In case you relocate a new city you will have to register afresh e.g.

													if you have registered in Bangalore city and if you relocate to Hyderabad,

													you will have to register afresh in Hyderabad with your Hyderabad address. <br>

													<br>

													Also if you are registered in one city and would like us to deliver

													in other cities you will have to register in each city with a different

													email id and the delivery address in that city. 

												</span> --%>

											</div>

										</div>

									</div>

								</span>

							</div>

						</div>

						<div class="uiv2-form-row"><span class="uiv2-form-label">Pin Code <span class="uiv2-field-required">*</span>  </span>

							<div class="uiv2-form-input"><span>

								<input name="pincode" value='<s:property value="regBean.getPincode()"/>'  maxlength="6" required="required" onKeyPress="return isNumberKey(event)" type="text" id="id_zipcode" size="15">

							</div>

						</div>

						</fieldset>

						

						<%-- <div class="uiv2-form-row uiv2-captcha-container uiv2-terms">

							<span class="uiv2-form-label" style=" width:100%;">Type the characters shown in the box<span class="uiv2-field-required">*</span>

						</span>

							<div class="uiv2-form-input" id="captcha"><span>

							<img alt="captcha" id="imgcaptcha" src="CaptchaAction.action" onclick="reloadCaptcha()">

							

								<!-- <img src="http://localhost:8989/RetailShop/CaptchaServlet"/>

								 --></span>

								 <input type="button" onclick="reloadCaptcha()" value="Refresh"/>

						

								</div>

						</div> --%>

						

						

						<%-- <div class="uiv2-row-border"><input id="id_captcha_1" value="" type="text" required="required" name="captcha_1" size="6" /><br/>

							<span class='error' style='color: #BD4247;'><s:property value="errorMsg"/></span>

								</div> --%>

						<!-- <p>&nbsp;</p>

						

						<div class="uiv2-row-border"></div> -->

						<!--<div class="uiv2-para-text uiv2-clear uiv2-terms" style="padding-top: 16px; margin-left: 8px">-->
						<!--<div class="uiv2-para-text uiv2-clear uiv2-terms" style="padding-top: 16px; margin-left: 8px">-->
						<div class="uiv2-para-text uiv2-clear uiv2-terms" style="padding-top: 16px; margin-left: 0px">

							<!-- <p>Where did you hear about us?</p>

							<p>Your answer will help us focus better and improve our services.</p><p>&nbsp;</p> -->

							<!--<div>-->

								<%-- <select style=" width:212px;">

												<option value="" selected="selected" required>Select</option>

												<option value="Mr.">Mr.</option>

												<option value="Mrs.">Mrs.</option>

												<option value="Ms.">Ms.</option>

											</select> --%>

							<!--</div>-->

							<div class="uiv2-form-button-wrapper uiv2-mar-t-10" style=" margin:0px;">

							<span class="uiv2-push-left" style=" height:61px; margin-left:222px;">

								<a onClick="regiserAction()" href="javascript:void();" class="uiv2-checkout-button"><p>UPDATE PROFILE</p></a>

							</span>

							<%-- <span class="uiv2-push-left" style=" height:61px;">

								<a href="javascript:void();" class="uiv2-cancel-button"><p>cancel</p></a>

							</span> --%>

						</div>
							
						</div>

						<!-- <div class="uiv2-row-border"></div> -->
<%-- 
						<div class="uiv2-para-text uiv2-clear uiv2-terms-container uiv2-terms" style="padding-top: 16px; margin-left: 8px">

							<div class="uiv2-input">

								<input type="checkbox" value="accept" name="terms" id="id_terms" onClick="acceptTerms()">

							</div>

							<p class="uiv2-input-desc">I accept the <a href="javascript:void();" target="_blank" title="Please read the Terms and Conditions">Terms and Conditions</a><span class="uiv2-field-required">*</span></p><br /><br />

							<!-- <p class="uiv2-clear">Your answer will help us focus better and improve our services.</p> -->

							<div id="checkboxError" class='error' style='color: #BD4247;'></div>

						</div> --%>

						
					</form>

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

