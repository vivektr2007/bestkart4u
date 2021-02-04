<!doctype html>
<html xmlns:fb="http://ogp.me/ns/fb#">
<%@taglib uri="/struts-tags" prefix="s" %>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE-EmulateIE8">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Contact Us</title>
<meta name="keywords" content=""/>
<meta name="description" content=""/>
<link href="http://fonts.googleapis.com/css?family=Roboto:400,500,300" rel="stylesheet" type="text/css" />
<link href="http://fonts.googleapis.com/css?family=Lato:600,300,400,700" rel="stylesheet" type="text/css" />
<link href="http://fonts.googleapis.com/css?family=Roboto+Slab:700,500,400,300,100" rel="stylesheet" type="text/css" />
<link rel="icon" type="image/png" href="images/favicon.ico" />
<!--<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/themes/overcast/jquery-ui.min.css" type="text/css" media="all" />-->
<link href="css/stylesheet.css" rel="stylesheet" type="text/css" />
<%-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script> --%>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="js/jquery-ui.min.js" type="text/javascript"></script>
<script src="js/sliding-banner-sub.js" type="text/javascript"></script>
<script src="js/sliding-banner.js" type="text/javascript"></script>
<script src="js/common.js" type="text/javascript"></script>

<link rel="stylesheet" href="css/jquery-ui-autocomplete.css" />

<script type="text/javascript">

        $(document).ready(function(){

            $('#uiv2-menu-bar').unbind("click");

            $('.seo-read-more').click(function(){

                $('.seo-test').show();

                $('.seo-read-more').hide();

            });

            $('#uiv2-menu-bar').removeClass('uiv2-pointer');

        });

    </script>
<script src="js/cart.js" type="text/javascript"></script>
<style type="text/css">
nav {
	display: block!important;/*  font-family: 'Roboto', 'sans-serif';*/
}
.uiv2-slider-wrapper {
	margin-bottom: 0;
}
#slideshow3 {
	width:270px!important;
}
</style>
<script type="text/javascript" src="js/validation_form.js"></script>
<script type="text/javascript" src="js/testimonials.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
			
		jQuery("#slideshow3").cycle({
        timeout:1, // no autoplay
        //fx: 'scrollLeft', //
        next: '#next3',
        prev: '#prev3',
            continuous:0,
            timeout:'7000'
        });
	});
</script>
<style type="text/css">
.pages_content {
	float:left;
	width:98%;
	padding:25px 1%;
}
.pages_content b {
	font-weight:500;
}
.pages_content h1 {
	font: 300 22px/55px 'Roboto', serif;
	border-bottom:1px dotted rgba(68, 68, 68, 0.7);
	color: #181818;
	margin:0px 0px 15px 0px;
	text-transform:uppercase;
}
.pages_content p {
	line-height:21px;
	padding:0px 0px 20px 0px;
	font-family:'Roboto', serif;
	font-weight:400;
	font-size:12px;
}
</style>
</head>
<body onLoad="getCookies()">
<div id="shadow-mask">&nbsp;</div>
<jsp:include page="header.jsp"></jsp:include>
<div class="main_wrapper">
<div class="uiv2-navigation-slider">
<div class="uiv2-left-navigation">
<div class="uiv2-nav uiv2-pointer" id="uiv2-menu-bar">SHOP BY CATEGORIES <span class="uiv2-nav-icon"></span></div>
<!-- Navigation starts here -->
<jsp:include page="productList.jsp"></jsp:include>
<!-- Navigation ends here -->
</div>
<!--<jsp:include page="menuPage.jsp"></jsp:include>-->
<div class="uiv2-slider-wrapper">
	<div class="uiv2-header-navigation-links main_menu">
		<ul>
			<li><a href="index.jsp">home</a></li>
			<li><a href="aboutUs.jsp">who we are</a></li>
			<li><a href="contacts.jsp" class="current">contact us</a></li>
			<li><a href="privacyPolicy.jsp">privacy policy</a></li>
		</ul>
	</div>
</div>

<div class="uiv2-content-wrapper">
	<div class="sysMessage"></div>
	<!-- Banner Starts here -->
	<div class="uiv2-slider-carousel">
				<div id="uiv2-slideshow">
					<div class="uiv2-slides banner-images">
						<ul>
							<li id="uiv2-slide-one"><a href="javascript:void();">
								<div class="uiv2-slider-block-one" style="background:url(images/banner-new.jpg) center left no-repeat;"></div>
								</a>
							</li>
							<li id="uiv2-slide-two"><a href="javascript:void();">
								<div class="uiv2-slider-block-two" style="background:url(images/banner1.jpg) center left no-repeat;"></div>
								</a>
							</li>
							<li id="uiv2-slide-three"><a href="javascript:void();">
								<div class="uiv2-slider-block-three" style="background: url(images/banner2.jpg) center left no-repeat;"></div>
								</a>
							</li>
							<li id="uiv2-slide-four"><a href="javascript:void();">
								<div class="uiv2-slider-block-four" style="background: url(images/banner3.jpg) center left no-repeat;"></div>
								</a>
							</li>
							<li id="uiv2-slide-five"><a href="javascript:void();">
								<div class="uiv2-slider-block-five" style="background: url(images/banner4.jpg) center left no-repeat;"></div>
								</a>
							</li>
							<li id="uiv2-slide-six"><a href="javascript:void();">
								<div class="uiv2-slider-block-six" style="background: url(images/banner5.jpg) center left no-repeat;"></div>
								</a>
							</li>							
						</ul>
					</div>
					<ul class="uiv2-slides-nav list_icons ">
						<li class="on">
							<span class="arrow caption-four" style=" display:none;"></span>
							<a href="#uiv2-slide-one" style=" display:none;">Chef's Basket</a>
							<span style=" display:none;">600 ml Coke FREE</span>
						</li>
						<li>
							<span class="arrow caption-four" style=" display:none;"></span>
							<a href="#uiv2-slide-two" style=" display:none;">Easy &amp; Convenient </a>
							<span style=" display:none;">Online Grocery Shopping</span>
						</li>
						<li>
							<span class="arrow caption-four" style=" display:none;"></span>
							<a href="#uiv2-slide-three" style=" display:none;">Easy, Quick and Simple</a>
							<span style=" display:none;">Grocery Shopping</span>
						</li>
						<li>
							<span class="arrow caption-four" style=" display:none;"></span>
							<a href="#uiv2-slide-four" style=" display:none;">Surf Excel Liquid</a>
							<span style=" display:none;">Removes Stain 2X</span>
						</li>
						<li>
							<span class="arrow caption-five" style=" display:none;"></span>
							<a href="#uiv2-slide-five" style=" display:none;">Surf Excel Liquid</a>
							<span style=" display:none;">Removes Stain 2X</span>
						</li>
						<li>
							<span class="arrow caption-six" style=" display:none;"></span>
							<a href="#uiv2-slide-six" style=" display:none;">Surf Excel Liquid</a>
							<span style=" display:none;">Removes Stain 2X</span>
						</li>
					</ul>
				</div>
			</div>
	<!-- Banner ends here -->
	<div class="main_wrapper">
		<div class="wrapper">
			<div class="uiv2-content-wrapper">
				<div class="uiv2-super-market" style=" border:none;">
					<div class="pages_content" style=" padding:25px 1% 0px 1%;">
						<h1>contact us</h1>
					</div>
					<div class="contact_us">
						<div class="contact_us_left"><img src="images/contact_us.jpg" />
							<p>Call Us: +91-999-920-33 89</p>
							<p>Support:<a href="mailto:support@bestkart4u.com">support@bestkart4u.com</a></p>
							<p>Sales:<a href="mailto:sales@bestkart4u.com">sales@bestkart4u.com</a></p>
						</div>
						<div class="contact_us_right">
							<div class="eq_block"><h4>Fill up the enquiry form below</h4></div>
							<form action="ContactAction.action" method="post" name="contact_form_bestkart">
							<div class="eq_block">
								<div class="eq_left">
									<div class="eq_name">
										<input type="text" maxlength="100" name="eq_name" id="eq_name" value="Full Name" class="eq_input" onBlur="javascript:if(this.value=='') this.value = 'Full Name';" autocomplete="off" onFocus="javascript:if(this.value=='Full Name') this.value = '';" />
									</div>
									<div class="eq_name">
										<input type="text" maxlength="100" name="eq_email" id="eq_email" value="Email Address" class="eq_input" onBlur="javascript:if(this.value=='') this.value = 'Email Address';" autocomplete="off" onFocus="javascript:if(this.value=='Email Address') this.value = '';" />
									</div>
									<div class="eq_name">
										<input type="text" maxlength="10" name="eq_contact" id="eq_contact" value="Contact Number" class="eq_input" maxlength="10" onBlur="javascript:if(this.value=='') this.value = 'Contact Number';" autocomplete="off" onFocus="javascript:if(this.value=='Contact Number') this.value = '';" onKeyPress="return isNumber(event)" />
									</div>
									<div class="eq_name">
										<input type="text" maxlength="2000" value="Subject" id="eq_subject" name="eq_subject" class="eq_input" onBlur="javascript:if(this.value=='') this.value = 'Subject';" autocomplete="off" onFocus="javascript:if(this.value=='Subject') this.value = '';">
									</div>
								</div>
								<div class="eq_right">
									<div class="eq_name">
										<textarea name="eq_query" class="eq_mess" id="eq_query" onFocus="javascript:if(this.value=='Query') this.value = '';" autocomplete="off" onBlur="javascript:if(this.value=='') this.value = 'Query';" rows="" cols="">Query</textarea>
									</div>
								</div>
							</div>
							<div class="eq_button">
								<!-- <input type="button" name="submit" id="submit" value="Send Message" class="gbtn_button" onClick="validateForm();" /> -->
								<input type="button" value="Send Message" class="gbtn_button" onClick="validateForm();">
							</div>
							</form>
						</div>
					</div>
					<div class="contact_us"><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p></div>
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
	<p>Copyright &copy; 2014-<span id="current-year"></span>All rights reserved.</p>
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
	var data = '<%=request.getAttribute("contactUsMsg")%>';
	if(data != null && data != '' && data != 'null' && data != 'NULL'){
		$('.add_this_content').fadeOut(-1);
		$("#productForSuccessMSG").html("Your query have been submitted successfully.");
        $('.add_this_content').fadeIn('slow').delay(1500).fadeOut('slow');
	}
</script>
