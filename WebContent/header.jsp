<%@taglib uri="/struts-tags" prefix="s" %>

<script>
  function statusChangeCallback(response) { 
    if (response.status === 'connected') {
      
      FB.api('/me',function(response) {
      //	console.log("string :"+JSON.stringify(response));
      	
      	/* console.log("email : "+response.email);
      	console.log("id : "+response.id);
      	 */
      	$("#email").val(response.email);
      	$("#id").val(response.id);
      	$("#first_name").val(response.first_name);
      	$("#gender").val(response.gender);
      	$("#last_name").val(response.last_name);
      	$("#link").val(response.link);
      	$("#name").val(response.name);
    	
      	document.FBLoginForm.submit();      	
       });
      
    } else if (response.status === 'not_authorized') {
      alert("Please log into App");
    } else {
       //alert('Please log into facebook');
    }
  }

  function checkLoginState() {
	  
	/*  FB.getLoginStatus(function(response) {
		 
		console.log('user id : '+response.authResponse.userID);
     statusChangeCallback(response);
    }); */
	 /* FB.login(response){
		 console.log("ttttttttttttt");
	 } */
	 FB.login(function(response) {
		   // handle the response
		   statusChangeCallback(response);
		 //console.log("ttttttttttttt");
		 }, {scope: 'public_profile,email'});
  }

  window.fbAsyncInit = function() {
	  var appIdFB;
	  var link = document.URL;
	  if(link.indexOf("www") != -1){
		  appIdFB = '795011393884173';
	  }
	  else if(link.indexOf("localhost") != -1){
		  appIdFB = '1523303981218063'; 
	  }
	  else{
		  appIdFB = '709627929117963';
	  }
	  
	  
  FB.init({
	appId      : appIdFB,
    cookie     : true,  // enable cookies to allow the server to access 
                        // the session
    xfbml      : true,  // parse social plugins on this page
    version    : 'v2.0' // use version 2.0
  });

  /* FB.getLoginStatus(function(response) {
	  
    statusChangeCallback(response);
  }); */

  /* FB.login(function(response) {
	   // handle the response
	   statusChangeCallback(response);
	 //console.log("ttttttttttttt");
	 }, {scope: 'public_profile,email'});
   */
  };

  // Load the SDK asynchronously
  (function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "//connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
  }(document, 'script', 'facebook-jssdk'));

  // Here we run a very simple test of the Graph API after login is
  // successful.  See statusChangeCallback() for when this call is made.
  function testAPI() {
    //console.log('Welcome!  Fetching your information.... ');
    FB.api('/app',function(response) {
    	 
      console.log('Successful login for: ' + response.name);
     
    });
  }
  /* function postToFb(){
	  var body = 'My Test Feed on facebook from code';
	  FB.api('/me/feed', 'post', { message: body }, function(response) {
	    if (!response || response.error) {
	      alert('Error occured');
	    } else {
	      alert('Post ID: ' + response.id);
	    }
	  });
  } */
  function logout(source){
	  
	  /*  alert(source);
	  if(source == 'FB'){  */
		   /* FB.logout(function(response) {
		     alert(response.status);	
		  }); */ 
	  /*  }
	  else{
		*/  
		  document.logoutForm.submit();
	  /*}  */
	  //window.location.reload(true);
  }
  </script>
  <script>
  $(function() {
	  $("#searchProductAutocomplete").autocomplete({
	  	source: function(request, response) {
	  	$.ajax({
	  	url: "ProductSearchAutocomplete.action",
	      type: "POST",
	  	dataType: "json",
	  	data: {	name: request.term},
	  	success: function( data ) {
	  		response( $.map( data, function( item ) {
	  		return {	
	  			label: item.name,
	  			value: item.value,
	  		};
	  		}));
	  	},
	  	error: function (error) {
	         alert('error: ' + error);
	    }
	  	});
	  	},
	  	minLength: 1
	  	});
	  });
	$(document).ready(function(){

	  $('#quantity_cancel').click(function(){
			$('#quantity_success').css('display','none');
	  });
	  
	  $('#subscribe_cancel').click(function(){
			$('#subscribe_success').css('display','none');
	  });
	  $('#search_header_error_cancel').click(function(){
			$('#search_header_error').css('display','none');
		});
	  $('.uiv2-slots').mouseover(function(){
	  	$('.uiv2-slots div.uiv2-slots-dropdown').addClass('display_dropdown');
	  }).mouseout(function(){
	  	$('.uiv2-slots div.uiv2-slots-dropdown').removeClass('display_dropdown');
	  });
	  
	});
</script>


<div id="fb-root"></div>
<form action="FBLoginAction.action" method="post" name="FBLoginForm">
	
	<input type="hidden" name="email" id="email"/>
	<input type="hidden" name="id" id="id"/>
	<input type="hidden" name="first_name" id="first_name"/>
	<input type="hidden" name="gender" id="gender"/>
	<input type="hidden" name="last_name" id="last_name"/>
	<input type="hidden" name="link" id="link"/>
	<input type="hidden" name="name" id="name"/>
	<input type="hidden" name="from" value="<s:property value='#request.from' default='main'/>">
	
	<input type="hidden" name="productIdWishlist" value='<s:property value="#request.productId"/>'/>
	<input type="hidden" name="rootCategoryWishlist" value='<s:property value="#request.rootCategory"/>'/>
	<input type="hidden" name="subRootCategoryWishlist" value='<s:property value="#request.subRootCategory"/>'/>
	<input type="hidden" name="categoryNameWishlist" value='<s:property value="#request.categoryNameWishlist"/>'/>
	<input type="hidden" name="startIndexWishlist" value='<s:property value="#request.startIndex"/>'/>
	<input type="hidden" name="typeWishlist" value='<s:property value="#request.type"/>'/>
	<input type="hidden" name="quantityWishlist" value='<s:property value="#request.quantity"/>'/>	
	<input type="hidden" name="childCategoryWishlist" value='<s:property value="#request.childCategory"/>'/>
	<input type="hidden" name="brandName" value='<s:property value="#request.brandName"/>'/>
	
</form>

<form action="WishListActionLogin.action" name="wishlistForm">
	<input type="hidden" name="productIdWishlist" id="productIdWishlist" value=''/>
	<input type="hidden" name="rootCategoryWishlist" id="rootCategoryWishlist" value=''/>
	<input type="hidden" name="subRootCategoryWishlist" id="subRootCategoryWishlist" value=''/>
	<input type="hidden" name="categoryNameWishlist" id="productNameWishlist" value=''/>
	<input type="hidden" name="startIndexWishlist" id="startIndexWishlist" value=''/>
	<input type="hidden" name="type" id="typeWishlist" value="">
	<input type="hidden" name="childCategoryWishlist" id="childCategoryWishlist" value="">
	<input type="hidden" name="quantityWishlist" id="quantityWishlist" value="">
	<input type="hidden" name="brandName" id="brandName" value="">
	
</form>

<table width="100%" cellpadding="0" cellspacing="0">
	<tbody>
		<tr>
			<td width="30%"><script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
<!-- sidebarad -->
<ins class="adsbygoogle"
     style="display:block"
     data-ad-client="ca-pub-6345543312254645"
     data-ad-slot="6566491216"
     data-ad-format="auto"></ins>
<script>
(adsbygoogle = window.adsbygoogle || []).push({});
</script></td>
			<td width="1000px" valign="top">
			
			
<div class="uiv2-location-popup">
	<h3>Why choose a location</h3>
	<!-- <a href="javascript:void();" class="uiv2-popupclose" onClick="$('.uiv2-location-popup').hide();$('.uiv2-background-shade').hide();"></a> -->
	<div class="uiv2-popup-wrapper">
		<h3>Currently we serve Dwarka, New Delhi</h3>
		<p>Please make sure that you have the right city selected, as the product selection and promotions are city specific.</p>
		<p>In case you are from a different city and are interested in Best Kart 4 U's service, do write to us at 
			<a href="mailto:customerservice@bestkart4u.com">customerservice@bestkart4u.com</a>.</p>
	</div>
</div>
<!-- <div class="fb-login-button" data-max-rows="1" data-size="medium" data-show-faces="false" data-auto-logout-link="false"></div> -->
<%
	String loginId = (String)session.getAttribute("loginId");
	if(loginId == null || loginId.equals("")){
	
%>

<a href="javascript:void();" class="fb-login" onclick="checkLoginState()">&nbsp;</a>

<%
	}
%>
<a href="javascript:void();" id="feedback" class="feedback_icon">&nbsp;</a>

<jsp:include page="feedback.jsp"></jsp:include>


<div class="uiv2-background-shade">&nbsp;</div>
<div class="uiv2-top-strip">
	<div class="uiv2-top-strip-wrapper">
		<div class="uiv2-top-strip-left">
			<div id="uiv2-location-id" class="uiv2-location" tabindex="1" style=" width:105px;">
				<div class="uiv2-loc-wrapper" style=" width:75px;"><span id="uiv2-selection">Dwarka</span></div>
				<div class="uiv2-location-dropdown">
					<div class="uiv2-urlocation">Location we serve</div>
					<span class="uiv2-currentlocation">Dwarka(New Delhi)</span>
					<!-- <div class="uiv2-changelocation">Change your location to</div>

					<ul id="uiv2-change">

						<li><a href="javascript:void();">Hyderabad</a></li>

						<li><a href="javascript:void();">Mumbai</a></li>

					</ul>

					<a href="javascript:void();" class="uiv2-why-location">Why choose a location?</a> -->
				</div>
			</div>
			<div class="uiv2-tele" style=" padding-left:5px;"><span class="uiv2-tele-icon"></span><span class="uiv2-tele-txt">Call Us: +91-999-920-33 89</span></div>
			<div class="uiv2-slots">
				<div class="uiv2-slots-wrapper"><a href="javascript:void();">Delivery Slots</a></div>
				<div class="uiv2-slots-dropdown" style=" display:none;">
					<div class="uiv2-urlocation">Location</div>
					<div class="uiv2-currentlocation">Dwarka(New Delhi)</div>
					<div class="uiv2-slots-content">
						<ul>
							<li>7:00 AM - 9:30 AM</li>
							<li>9:30 AM - 12:00 PM</li>
							<li>5:00 PM - 7:30 PM</li>
							<li>7:30 PM - 10:00 PM</li>
						</ul>
					</div>
					<!-- <div class="uiv2-changelocation">Change your location to</div>

					<ul id="uiv2-change">

						<li><a href="javascript:void();">Hyderabad</a></li>

						<li><a href="javascript:void();">Mumbai</a></li>

					</ul>

					<a href="javascript:void();" class="uiv2-why-location">Why choose a location?</a> -->
				</div>
			</div>
			<!--<div class="uiv2-deliveryslot-link"><span class="uiv2-delivery-icon"></span>

				<div class="uiv2-deliveryslot-dropdown">

					<div class="uiv2-whiteshade"></div>

					<div class="uiv2-deliveryslot-location">

						<h4>Delivery Slots</h4>

						<div class="uiv2-slotlocation">Location: New Delhi</div>

					</div>

					<ul class="uiv2-deliveryslots-list">

						<li>7:00 AM to 9:30 AM</li>

						<li>10:00 AM to 12:30 PM</li>

						<li>5:00 PM to 7:30 PM</li>

						<li>7:30 PM to 10:00 PM</li>

					</ul>

					<%

						//if(session == null || session.getAttribute("loginId") == null ){

					%>

					<div class="uiv2-deliveryslot-loginbox">

						<div class="uiv2-slotlocation">To check the next available slots</div>

						<a href="login.jsp" class="uiv2-login-link">LOGIN &nbsp;|&nbsp; </a><a href="register.jsp">REGISTER</a></div>

					<%

						//}

					%>

				</div>

				Check our delivery slots <span class="uiv2-delivery-dropdown-icon"></span></div>-->
			<!-- End delivery slots -->
		</div>
		<div class="uiv2-top-strip-right">
			<!--<div class="uiv2-free-delivery">Free delivery for orders <span class="WebRupee">Rs.</span> 1000.00 and above</div>-->
			
			<!-- End new to bigbasket -->
			<%

				if(session == null || session.getAttribute("loginId") == null ){

			%>
			
			<%-- <div class="uiv2-newbasket-link" id="id_top_menu_banner">
				<div class="uiv2-newbasket-dropdown">
					<div class="uiv2-whiteshade"></div>
					<h4>Discover BestKart4U. Take a tour</h4>
					<div class="uiv2-new-basket-list-box why-us"><a href="javascript:void();">Why Choose Us?</a></div>
					<div class="uiv2-new-basket-list-box customer"><a href="javascript:void();">Customer's Zone</a></div>
					<div class="uiv2-new-basket-list-box faq"><a href="javascript:void();">FAQs</a></div>
					<div class="uiv2-new-basket-list-box media"><a href="javascript:void();">Social Media</a></div>
					<div class="uiv2-new-basket-content-box-main">
						<%

						if(session == null || session.getAttribute("loginId") == null ){

					%>
						<div class="uiv2-new-basket-content-box"><span>New to BestKart4U. <a href="register.jsp">Register Now</a></span></div>
						<%

						}

					%>
						<div class="uiv2-new-basket-content-box"><span>Call us between 07:00 am to 10:00 pm - On all days</span></div>
						<div class="uiv2-new-basket-content-box uiv2-border-none"><span>or e-mail us at <a href="mailto:customerservice@bestkart4u.com">customerservice@bestkart4u.com</a></span></div>
						<h4>Online Grocery just a click away! BestKart4U - the best online groceries and online food store delivers all your household needs at your doorstep. Save time and money by shopping at our online supermarket.</h4>
					</div>
				</div>
				<span id="id_new_at_bb_txt">Take a tour with us</span> <span class="uiv2-newbasket-dropdown-icon"></span> </div> --%>
			
			<div class="uiv2-login" style=" position:relative;"> 
				<span class="header_info" style="position:absolute; left:-255px; top:0px; color:#555; line-height:20px; font-size:11px;">Free delivery for orders <b>Rs. 299 and more</b></span>
			<span class="uiv2-login-h">
				<div class="uiv2-login-dropdown-block">
					<div class="uiv2-login-head">Login</div>
					<div class="uiv2-login-dropdown">
						<div class="uiv2-whiteshade"></div>
						<a href="login.jsp" class="uiv2-yellow-btn-small uiv2-login-dropdown-btn" id="id_dropdown_login">LOGIN</a> <a href="forgetPassword.jsp" class="uiv2-forgot-pwd">FORGOT PASSWORD ?</a>
						<p class="login_options">&nbsp;</p>
						<p style=" text-align:center; font-size:12px; padding:0px 0px 6px 0px;">You can now Login with your Facebook Account</p>
						<a href="javascript:void();" onclick="checkLoginState()" style="display:inline-block; text-align:center; width:100%; margin-bottom:7px;"><img src="images/fb-signup.png" width="190" /></a>
					</div>
					<div class="uiv2-register-now">
						<p>New to BestKart4U? &nbsp;&nbsp;<a href="register.jsp">REGISTER NOW</a></p>
					</div>
				</div>
				LOGIN </span>
				<p class="uiv2-separator">|</p>
				<span><a href="register.jsp">REGISTER</a></span><p class="uiv2-separator">|</p>
				<span><a href="CheckOutFromCart.action?from=cartHeader">CHECKOUT</a></span> </div>
			<%

				}else{

				%>
				<div class="wishlist_block">
					<a href="javascript:void();" onclick="viewWishlist()">My Wishlist</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;<a href="CheckOutFromCart.action">Checkout</a>&nbsp;&nbsp;&nbsp;|
				</div>
			<div class="uiv2-newbasket-link" id="loggedin">
				<div class="uiv2-newbasket-dropdown my_profile">
					<ul>
						<li><a href="ViewPendingOrderAction.action">View Pending Order</a></li>
						<li><a href="ViewPreviousOrderAction.action">View Previous Order</a></li>
						<!--<li><a href="CheckOutFromCart.action">Checkout</a></li>-->
						<li><a href="changePassword.jsp">Change Password</a></li>
						<li><a href="GetUpdateProfileForm.action">Update Profile</a></li>
						<!--<li><a href="javascript:void();" onclick="viewWishlist()">View Wishlist</a></li>-->
						<form action="LogoutAction.action" method="post" name="logoutForm"></form>
						<li><a href="#" onclick="logout('FB')">Logout</a></li>
					</ul>
				</div>
				<span id="id_new_at_bb_txt">Welcome <%=session.getAttribute("loginName") %></span> <span class="uiv2-newbasket-dropdown-icon"></span></div>
			<%

				}

				%>
		</div>
		<!-- End guest login -->
	</div>
</div>
<div class="uiv2-header-section">
	<div class="wrapper"><a href="index.jsp" class="uiv2-bigbasket-logo">&nbsp;</a>
		<div class="uiv2-search">
	<form method="get" action="SearchProductActionHeader.action" name="productSearchHeader">
			<input type="text" name="searchProductAutocomplete" id="searchProductAutocomplete"/>
			<input type="button" value="Search" onclick="searchProduct()" />
	</form>
			<div class="uiv2-search-dropdown-block">
				<!-- SEARCH RESULTS STARTS -->
				<div class="uiv2-product-search-list-wrap">
					<div class="uiv2-no-result-found">
						<p id="uiv2-no-result"></p>
						<span id="id_more_suggestion_labels">Some more suggestions</span></div>
					<div id="scrollbar2">
						<div class="scrollbar" style="height: 130px;">
							<div style="height: 0px;" class="track">
								<div class="thumb">
									<div class="end"></div>
								</div>
							</div>
						</div>
						<div class="viewport">
							<div class="overview" style="top: 0px;">
								<div class="uiv2-search-product-list" id="id_autosearch_list_container"></div>
							</div>
						</div>
					</div>
					<a class="uiv2-viewall-product" href="javascript:void();" id="id_search_view_all_link">VIEW ALL PRODUCTS</a></div>
				<!-- SEARCH RESULTS END -><!--Right Column-->
				<div class="uiv2-product-rightlist-wrap">
					<div class="uiv2-product-rightlist-column">
						<div class="uiv2-searched-rightcolumnlist" id="id_common_search_container"><span>COMMONLY SEARCHED</span>
							<ul id="uiv2-search-terms">
								<li>common terms</li>
							</ul>
						</div>
						<div class="uiv2-searched-rightcolumnlist  uiv2-searched-rightcolumnlist_brands" id="id_popular_brand_container"><span>POPULAR BRANDS</span>
							<ul id="uiv2-search-brands">
							</ul>
						</div>
						<div class="uiv2-searched-rightcolumnlist uiv2-searched-rightcolumnlist_brands" id="id_common_categories"><span>CATEGORIES</span>
							<ul id="uiv2-search-categories">
							</ul>
						</div>
					</div>
				</div>
				<!--Search Results  - Right Column-->
			</div>
		</div>
		<div class="uiv2-your-basket uiv2-checkout-basket">
			<jsp:include page="yourCart.jsp"></jsp:include>
		</div>
	</div>
</div>


<div id="quantity_success" style="display: none;">
	<div class="show_payment">
		<div class="payment_content_sub">
			<div class="payment_content1">
				<div class="feedback_block">
					<h1>Quantity</h1>
					<p>Please enter quantity.</p>
				</div>
				<a class="popup-close" href="javascript:void():" id="quantity_cancel">&nbsp;</a> </div>
		</div>
	</div>
	<div class="payment_overlay">&nbsp;</div>
</div>


<div id="search_header_error" style="display: none;">
	<div class="show_payment">
		<div class="payment_content_sub">
			<div class="payment_content1">
				<div class="feedback_block">
					<h1></h1>
					<p>Please enter some text to search.</p>
				</div>
				<a class="popup-close" href="javascript:void():" id="search_header_error_cancel">&nbsp;</a> </div>
		</div>
	</div>
	<div class="payment_overlay">&nbsp;</div>
</div>


<div id="subscribe_success" style="display: none;">
	<div class="show_payment">
		<div class="payment_content_sub">
			<div class="payment_content1">
				<div class="feedback_block">
					<h1>Subscribe</h1>
					<p>Please enter Email Id.</p>
				</div>
				<a class="popup-close" href="javascript:void():" id="subscribe_cancel">&nbsp;</a> </div>
		</div>
	</div>
	<div class="payment_overlay">&nbsp;</div>
</div>

<div id="addedtocartsuccessmsg" class="add_this_content"><p><span id="productForSuccessMSG"></span></p></div>
<div id="wishlist" style="display:none; position:relative;">
			<div id="uiv2-new-address-form" class="uiv2-popup"
				style="display: block;">
				<h3>Wishlist</h3>
				<a href="javascript:void();" class="uiv2-popupclose"
					onClick="hide_code('wishlist');">&nbsp;</a>
				<div id="uiv2-new-address-popup" class="uiv2-popup-wrapper" style=" position:relative; padding-bottom:40px;">
				</div>
			</div>
			<div id="shadow-mask" style="display: block; z-index: 999;">&nbsp;</div>
		</div>