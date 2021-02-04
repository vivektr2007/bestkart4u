<!doctype html>
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE-EmulateIE8">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>BestKart4U : Best online Store and Supermarket in India</title>
<link href="http://fonts.googleapis.com/css?family=Roboto:400,300" rel="stylesheet" type="text/css" />
<link href="http://fonts.googleapis.com/css?family=Lato:600,300,400,700" rel="stylesheet" type="text/css" />
<link href="http://fonts.googleapis.com/css?family=Roboto+Slab:700,500,400,300,100" rel="stylesheet" type="text/css" />
<!--<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/themes/overcast/jquery-ui.min.css" type="text/css" media="all" />-->
<link href="css/stylesheet.css" rel="stylesheet" type="text/css" />
<link rel="icon" type="image/png" href="images/favicon.ico" />
<!--[if lt IE 9]><link href="//d3oi2nue850v1c.cloudfront.net/static/uiv2/css/ie8styles.css" rel="stylesheet" type="text/css">

<script src="//d3oi2nue850v1c.cloudfront.net/static/uiv2/js/html5shiv.js"></script>

<![endif]-->

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="js/jquery-ui.min.js" type="text/javascript"></script>
<link rel="stylesheet" href="css/jquery-ui-autocomplete.css" />

<%-- <script src="js/jquery.min.js" type="text/javascript"></script> --%>
<script src="js/jquery.cycle.js" type="text/javascript"></script>
<%-- <script src="js/jquery-ui.min.js" type="text/javascript"></script> --%>
<script src="js/uiv2_main.min.js" type="text/javascript"></script>
<script src="js/facets.min.js" type="text/javascript"></script>
<script src="js/common.js" type="text/javascript"></script>
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
<script type="text/javascript" src="js/jquery.custom_radio_checkbox.js"></script>
<script type="text/javascript" language="javascript">

	$(document).ready(function(){

		$(".radio").dgStyle();

		$(".checkbox").dgStyle();

	});

</script>
<script type="text/javascript">

/* 	$(function(){

		$('select.styled').customSelect();

	});
 */
	function changePassword(){
		
		var password = $("#uiv2-login-page-email").val();
		var newPassword = $("#uiv2-login-page-passwd").val();
		var cnewPassword = $("#uiv2-login-page-passwd1").val();
		if(password == ''){
			$("#errorDiv").html("<font color='red'>Please enter Password</font>");
			return false;
		}
		if(newPassword == ''){
			$("#errorDiv").html("<font color='red'>Please enter New Password</font>");
			return false;
		}
		if(cnewPassword == ''){
			$("#errorDiv").html("<font color='red'>Please enter Confirm New Password</font>");
			return false;
		}
		if(newPassword != cnewPassword){
			$("#errorDiv").html("<font color='red'>New Password and Confirm New Password must be same.</font>");
			return false;
		}
		document.contact_form.submit();
		
	}
	
</script>
<!-- Customised Select Box Ends Here -->
<script src="js/cart.js" type="text/javascript"></script>
</head>
<body onLoad="getCookies();">
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
				<div class="sysMessage"></div>
				<div class="uiv2-padding-vertical-60">
					<div class="uiv2-login-block">
						<form action="ChangePasswordAction.action" method="post" name="contact_form">
							<div class="uiv2-login-block-left">
								<h1>Change Password</h1>
								<p class="uiv2-mar-t-10" id="errorMsg"><font color="red">
									<s:property value="loginErrorMsg"/>
									</font></p>
								<p>&nbsp;</p>
								<font color="red">
								<span id="errorDiv"><s:property value="successMsg"/></span>
								</font>
								<div class="uiv2-mar-t-20 text_blocked"> 
									<div class="labelled"><span class="uiv2-left"><label for="uiv2-login-page-email">Old Password</label></span></div>
									<div class="labelled_text">
										<input type="password" id="uiv2-login-page-email" name="oldPassword" />
									</div>
								</div>
								<div class="uiv2-mar-t-20 text_blocked"> 
									<div class="labelled"><span class="uiv2-left"><label for="uiv2-login-page-email">New Password</label></span></div> 
									<div class="labelled_text">
										<input type="password" name="newPassword" id="uiv2-login-page-passwd" />
									</div> 
								</div>
								<div class="uiv2-mar-t-20 text_blocked"> 
									<div class="labelled"><span class="uiv2-left"><label for="uiv2-login-page-email">Confirm New Password</label></span></div> 
									<div class="labelled_text">
										<input type="password" name="cNewPassword" id="uiv2-login-page-passwd1" />
									</div>
								</div>
								<div id="id_login_page_error" class="uiv2-overflow-auto uiv2-mar-t-10"> 
									<span class="uiv2-err-text"></span> 
								</div>
								<div class="uiv2-mar-t-10 uiv2-login-button-block text_blocked">
									<input type="button" value="CHANGE PASSWORD" onclick="changePassword()" id="id_page_login_btn" />
									<input type="reset" value="RESET" id="id_page_login_reset_btn" />
								</div>
							</div>
							<%-- <div class="uiv2-login-block-left">

							<h1>Change Password</h1>

							<p class="uiv2-mar-t-10" id="errorMsg"><font color="red"><s:property value="loginErrorMsg"/></font></p>

							<p>&nbsp;</p>

							<div class="uiv2-mar-t-20">

								<span class="uiv2-left">

									<label for="uiv2-login-page-email">Old Password</label>

								</span>

								<span class="uiv2-left uiv2-email">

									<span class="uiv2-email-icon uiv2-left"></span>

									<input type="password" id="uiv2-login-page-email" name="oldPassword" />

								</span>

							</div>

							<p>&nbsp;</p>

							<div class="uiv2-mar-t-20">

								<span class="uiv2-left">

									<label for="uiv2-login-page-passwd">New Password</label>

								</span>

								<span class="uiv2-left uiv2-pwd">

									<span class="uiv2-pwd-icon uiv2-left"></span>

									<input type="password" name="newPassword" id="uiv2-login-page-passwd" />

								</span>

							</div>

							<p>&nbsp;</p>

							<div class="uiv2-mar-t-20">

								<span class="uiv2-left">

									<label for="uiv2-login-page-passwd">Confirm New Password</label>

								</span>

								<span class="uiv2-left uiv2-pwd">

									<span class="uiv2-pwd-icon uiv2-left"></span>

									<input type="password" name="cNewPassword" id="uiv2-login-page-passwd" />

								</span>

							</div>

							<div id="id_login_page_error" class="uiv2-overflow-auto uiv2-mar-t-10">

								<span class="uiv2-err-text"></span>

							</div>

							<div class="uiv2-mar-t-10 uiv2-login-button-block">

								<input type="submit" value="LOGIN" id="id_page_login_btn" />

								<input type="reset" value="RESET" id="id_page_login_reset_btn" />

							</div>

						</div> --%>
						</form>
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
