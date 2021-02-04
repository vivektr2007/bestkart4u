<!doctype html>
<html xmlns:fb="http://ogp.me/ns/fb#">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE-EmulateIE8">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Privacy Policy</title>
<meta name="keywords" content=""/>
<meta name="description" content=""/>
<link href="http://fonts.googleapis.com/css?family=Roboto:400,500,300" rel="stylesheet" type="text/css" />
<link href="http://fonts.googleapis.com/css?family=Lato:600,300,400,700" rel="stylesheet" type="text/css" />
<link href="http://fonts.googleapis.com/css?family=Roboto+Slab:700,500,400,300,100" rel="stylesheet" type="text/css" />
<link rel="icon" type="image/png" href="images/favicon.ico" />

<!--<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/themes/overcast/jquery-ui.min.css" type="text/css" media="all" />-->

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="js/jquery-ui.min.js" type="text/javascript"></script>
<link rel="stylesheet" href="css/jquery-ui-autocomplete.css" />
<link href="css/stylesheet.css" rel="stylesheet" type="text/css" />
<!-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script> -->
<script src="js/sliding-banner-sub.js" type="text/javascript"></script>
<script src="js/sliding-banner.js" type="text/javascript"></script>


<script src="js/common.js" type="text/javascript"></script>
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
#slideshow3{
	width:270px!important;
}
</style>
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
	.pages_content{
		float:left; width:98%; padding:25px 1%;
	}
	.pages_content b{
		font-weight:500;
	}
	.pages_content h1{
		font: 300 22px/55px 'Roboto', serif; border-bottom:1px dotted rgba(68,68,68,0.7);
		color: #181818; margin:0px 0px 15px 0px; text-transform:uppercase;
	}
	.pages_content p{
		line-height:21px; padding:0px 0px 20px 0px; font-family:'Roboto', serif; font-weight:400; font-size:12px;
	}
	.pages_content a{
		color:#da4446;
	}
	.pages_content a:hover{
		color:#da4446;
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
					<li><a href="contacts.jsp">contact us</a></li>
					<li><a href="privacyPolicy.jsp" class="current">privacy policy</a></li>
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
				<div class="wrapper"><div class="uiv2-content-wrapper"><div class="uiv2-super-market" style=" border:none;">
					<div class="pages_content">
						<h1>privacy policy</h1>
						<p><b>SECTION 1 - WHAT DO WE DO WITH YOUR INFORMATION?</b><br><br>
						When you purchase something from our store, as part of the buying and selling process, we collect the personal information you give us such as your name, address and email address.<br><br>
						When you browse our store, we also automatically receive your computer's internet protocol (IP) address in order to provide us with information that helps us learn about your browser and operating system.<br><br>
						<b>Email marketing (if applicable)</b>: With your permission, we may send you emails about our store, new products and other updates.<br><br>
						</p>
						<p><b>SECTION 2 - CONSENT</b><br><br>
						How do you get my consent?<br><br>
						When you provide us with personal information to complete a transaction, verify your credit card, place an order, arrange for a delivery or return a purchase, we imply that you consent to our collecting it and using it for that specific reason only.<br><br>
						If we ask for your personal information for a secondary reason, like marketing, we will either ask you directly for your expressed consent, or provide you with an opportunity to say no.<br><br>
						<b>How do I withdraw my consent?</b><br><br>
						If after you opt-in, you change your mind, you may withdraw your consent for us to contact you, for the continued collection, use or disclosure of your information, at anytime, by contacting us at <a href="mailto:support@bestkart4u.com">support@bestkart4u.com</a>.<br><br></p>
						<p><b>SECTION 3 - DISCLOSURE</b><br><br>
						We may disclose your personal information if we are required by law to do so or if you violate our <a href="terms-and-conditions.jsp">Terms of Service</a>.
						</p>
						<p><b>SECTION 4 - PAYMENT</b><br><br>
						If you choose a direct payment gateway to complete your purchase, then <b>BestKart4U</b> stores your credit card data. It is encrypted through the Payment Card Industry Data Security Standard (PCI-DSS). Your purchase transaction data is stored only as long as is necessary to complete your purchase transaction. After that is complete, your purchase transaction information is deleted.<br><br>
						All direct payment gateways adhere to the standards set by PCI-DSS as managed by the PCI Security Standards Council, which is a joint effort of brands like Visa, MasterCard, American Express and Discover.<br><br>
						PCI-DSS requirements help ensure the secure handling of credit card information by our store and its service providers.<br><br>
						</p>
						<p><b>SECTION 5 - THIRD-PARTY SERVICES</b><br><br>
						In general, the third-party providers used by us will only collect, use and disclose your information to the extent necessary to allow them to perform the services they provide to us.<br><br>
						However, certain third-party service providers, such as payment gateways and other payment transaction processors, have their own privacy policies in respect to the information we are required to provide to them for your purchase-related transactions.<br><br>
						
For these providers, we recommend that you read their privacy policies so you can understand the manner in which your personal information will be handled by these providers.<br><br>

In particular, remember that certain providers may be located in or have facilities that are located a different jurisdiction than either you or us. So if you elect to proceed with a transaction that involves the services of a third-party service provider, then your information may become subject to the laws of the jurisdiction(s) in which that service provider or its facilities are located.<br><br>

As an example, if you are located in Canada and your transaction is processed by a payment gateway located in the United States, then your personal information used in completing that transaction may be subject to disclosure under United States legislation, including the Patriot Act.<br><br>
Once you leave our store's website or are redirected to a third-party website or application, you are no longer governed by this Privacy Policy or our website's Terms of Service. 
<br><br>
<b>LINKS</b><br><br>

When you click on links on our store, they may direct you away from our site. We are not responsible for the privacy practices of other sites and encourage you to read their privacy statements.<br><br>
</p>
<p><b>SECTION 6 - SECURITY</b><br><br>
To protect your personal information, we take reasonable precautions and follow industry best practices to make sure it is not inappropriately lost, misused, accessed, disclosed, altered or destroyed.<br><br>

If you provide us with your credit card information, the information is encrypted using secure socket layer technology (SSL) and stored with a AES-256 encryption.  Although no method of transmission over the Internet or electronic storage is 100% secure, we follow all PCI-DSS requirements and implement additional generally accepted industry standards.
<br><br>
</p>
<p><b>SECTION 7 - COOKIES</b><br><br>

 <b>BestKart4U</b> receives and stores certain types of information whenever you interact with us through the use of "cookies." Cookies enable our systems to recognize your browser and to provide you with certain shopping features. These "cookies" allow us to give you customized and personalized service and helps us identify ways to improve your shopping experience. The "help" portion of the toolbar on most browsers will tell you how to reject or disable cookies or receive notice when a new cookie appears. If you choose to reject or disable cookies, you will be unable to use those <b>BestKart4U</b> Services that require the use of such cookies. <b>BestKart4U.com</b>'s cookies do not contain any of your personally identifiable information. 
<br><br>
</p>
<p><b>SECTION 8 - AGE OF CONSENT</b><br><br>
 By using this site, you represent that you are at least the age of majority in your state or province of residence, or that you are the age of majority in your state or province of residence and you have given us your consent to allow any of your minor dependents to use this site.
<br><br>
</p>
<p><b>SECTION 9 - CHANGES TO THIS PRIVACY POLICY</b><br><br>



We reserve the right to modify this privacy policy at any time, so please review it frequently. Changes and clarifications will take effect immediately upon their posting on the website. If we make material changes to this policy, we will notify you here that it has been updated, so that you are aware of what information we collect, how we use it, and under what circumstances, if any, we use and/or disclose it.<br><br>

If our online store is acquired or merged with another company, your information may be transferred to the new owners so that we may continue to sell products to you.<br><br>
</p>

<p><b>QUESTIONS AND CONTACT INFORMATION</b><br><br>



If you would like to: access, correct, amend or delete any personal information we have about you, register a complaint, or simply want more information contact our Privacy Compliance Officer at <a href="mailto:support@bestkart4u.com">support@bestkart4u.com</a>.
</p>
					</div>
				</div></div></div>
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