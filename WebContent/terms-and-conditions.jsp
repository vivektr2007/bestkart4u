<!doctype html>
<html xmlns:fb="http://ogp.me/ns/fb#">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE-EmulateIE8">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Terms &amp; Conditions</title>
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
<!-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
 --><script src="js/sliding-banner-sub.js" type="text/javascript"></script>
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
		<jsp:include page="menuPage.jsp"></jsp:include>
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
						<h1>Terms &amp; Conditions</h1>
						<p>This website is operated by <a href="http://bestkart4u.com/">BestKart4U.com</a>. Throughout the site, the terms ?we?, ?us? and ?our? refer to <b>BestKart4U.com</b>. <b>BestKart4U.com</b> offers this website, including all information, tools and services available from this site to you, the user, conditioned upon your acceptance of all terms, conditions, policies and notices stated here.
						</p>
						<p>By visiting our site and/ or purchasing something from us, you engage in our ?Service? and agree to be bound by the following terms and conditions (?Terms of Service?, ?Terms?), including those additional terms and conditions and policies referenced herein and/or available by hyperlink. These Terms of Service apply  to all users of the site, including without limitation users who are browsers, vendors, customers, merchants, and/ or contributors of content.</p>
						<p>
Please read these Terms of Service carefully before accessing or using our website. By accessing or using any part of the site, you agree to be bound by these Terms of Service. If you do not agree to all the terms and conditions of this agreement, then you may not access the website or use any services. If these Terms of Service are considered an offer, acceptance is expressly limited to these Terms of Service.</p>
						<p>
Any new features or tools which are added to the current store shall also be subject to the Terms of Service. You can review the most current version of the Terms of Service at any time on this page. We reserve the right to update, change or replace any part of these Terms of Service by posting updates and/or changes to our website. It is your responsibility to check this page periodically for changes. Your continued use of or access to the website following the posting of any changes constitutes acceptance of those changes.</p>
						<p><b>SECTION 1 - ONLINE STORE TERMS</b><br><br>
						By agreeing to these Terms of Service, you represent that you are at least the age of majority in your state or province of residence, or that you are the age of majority in your state or province of residence and you have given us your consent to allow any of your minor dependents to use this site.<br><br>

You may not use our products for any illegal or unauthorized purpose nor may you, in the use of the Service, violate any laws in your jurisdiction (including but not limited to copyright laws).
<br><br>
You must not transmit any worms or viruses or any code of a destructive nature.

A breach or violation of any of the Terms will result in an immediate termination of your Services.
<br><br>
</p>
<p><b>SECTION 2 - GENERAL CONDITIONS</b><br><br>



We reserve the right to refuse service to anyone for any reason at any time.

You understand that your content (not including credit card information), may be transferred unencrypted and involve (a) transmissions over various networks; and (b) changes to conform and adapt to technical requirements of connecting networks or devices. Credit card information is always encrypted during transfer over networks.
<br><br>
You agree not to reproduce, duplicate, copy, sell, resell or exploit any portion of the Service, use of the Service, or access to the Service or any contact on the website through which the service is provided, without express written permission by us.
<br><br>
The headings used in this agreement are included for convenience only and will not limit or otherwise affect these Terms.

<br><br></p>
<p><b>SECTION 3 - ACCURACY, COMPLETENESS AND TIMELINESS OF INFORMATION</b><br><br>



We are not responsible if information made available on this site is not accurate, complete or current. The material on this site is provided for general information only and should not be relied upon or used as the sole basis for making decisions without consulting primary, more accurate, more complete or more timely sources of information. Any reliance on the material on this site is at your own risk.
<br><br>
This site may contain certain historical information. Historical information, necessarily, is not current and is provided for your reference only. We reserve the right to modify the contents of this site at any time, but we have no obligation to update any information on our site. You agree that it is your responsibility to monitor changes to our site.
<br><br></p>
<p><b>SECTION 4 - MODIFICATIONS TO THE SERVICE AND PRICES</b><br><br>



Prices for our products are subject to change without notice.

We reserve the right at any time to modify or discontinue the Service (or any part or content thereof) without notice at any time.

We shall not be liable to you or to any third-party for any modification, price change, suspension or discontinuance of the Service.
<br><br></p>
<p><b>SECTION 5 - PRODUCTS OR SERVICES</b><br><br>

We have made every effort to display as accurately as possible the colors and images of our products that appear at the store. We cannot guarantee that your computer monitor's display of any color will be accurate.<br><br>

We reserve the right, but are not obligated, to limit the sales of our products or Services to any person, geographic region or jurisdiction. We may exercise this right on a case-by-case basis. We reserve the right to limit the quantities of any products or services that we offer. All descriptions of products or product pricing are subject to change at anytime without notice, at the sole discretion of us. We reserve the right to discontinue any product at any time. Any offer for any product or service made on this site is void where prohibited.
<br><br>
We do not warrant that the quality of any products, services, information, or other material purchased or obtained by you will meet your expectations, or that any errors in the Service will be corrected.


<br><br></p>
<p><b>SECTION 6 - ACCURACY OF BILLING AND ACCOUNT INFORMATION</b><br><br>


We reserve the right to refuse any order you place with us. We may, in our sole discretion, limit or cancel quantities purchased per person, per household or per order. These restrictions may include orders placed by or under the same customer account, the same credit card, and/or orders that use the same billing and/or shipping address. In the event that we make a change to or cancel an order, we may attempt to notify you by contacting the e-mail and/or billing address/phone number provided at the time the order was made. We reserve the right to limit or prohibit orders that, in our sole judgment, appear to be placed by dealers, resellers or distributors.

<br><br>

You agree to provide current, complete and accurate purchase and account information for all purchases made at our store. You agree to promptly update your account and other information, including your email address and credit card numbers and expiration dates, so that we can complete your transactions and contact you as needed.

<br><br>

</p>
<p><b>SECTION 7 - OPTIONAL TOOLS</b><br><br>



We may provide you with access to third-party tools over which we neither monitor nor have any control nor input.

You acknowledge and agree that we provide access to such tools ?as is? and ?as available? without any warranties, representations or conditions of any kind and without any endorsement. We shall have no liability whatsoever arising from or relating to your use of optional third-party tools.
<br><br>
Any use by you of optional tools offered through the site is entirely at your own risk and discretion and you should ensure that you are familiar with and approve of the terms on which tools are provided by the relevant third-party provider(s).
<br><br>
We may also, in the future, offer new services and/or features through the website (including, the release of new tools and resources). Such new features and/or services shall also be subject to these Terms of Service.
<br><br>
</p>
<p><b>SECTION 8 - THIRD-PARTY LINKS</b><br><br>



Certain content, products and services available via our Service may include materials from third-parties.

Third-party links on this site may direct you to third-party websites that are not affiliated with us. We are not responsible for examining or evaluating the content or accuracy and we do not warrant and will not have any liability or responsibility for any third-party materials or websites, or for any other materials, products, or services of third-parties.
<br><br>
We are not liable for any harm or damages related to the purchase or use of goods, services, resources, content, or any other transactions made in connection with any third-party websites. Please review carefully the third-party's policies and practices and make sure you understand them before you engage in any transaction. Complaints, claims, concerns, or questions regarding third-party products should be directed to the third-party.
<br><br>
</p>
<p><b>SECTION 9 - USER COMMENTS, FEEDBACK AND OTHER SUBMISSIONS</b><br><br>



If, at our request, you send certain specific submissions (for example contest entries) or without a request from us you send creative ideas, suggestions, proposals, plans, or other materials, whether online, by email, by postal mail, or otherwise (collectively, 'comments'), you agree that we may, at any time, without restriction, edit, copy, publish, distribute, translate and otherwise use in any medium any comments that you forward to us. We are and shall be under no obligation (1) to maintain any comments in confidence; (2) to pay compensation for any comments; or (3) to respond to any comments.
<br><br>
We may, but have no obligation to, monitor, edit or remove content that we determine in our sole discretion are unlawful, offensive, threatening, libelous, defamatory, pornographic, obscene or otherwise objectionable or violates any party?s intellectual property or these Terms of Service.
<br><br>
You agree that your comments will not violate any right of any third-party, including copyright, trademark, privacy, personality or other personal or proprietary right. You further agree that your comments will not contain libelous or otherwise unlawful, abusive or obscene material, or contain any computer virus or other malware that could in any way affect the operation of the Service or any related website. You may not use a false e-mail address, pretend to be someone other than yourself, or otherwise mislead us or third-parties as to the origin of any comments. You are solely responsible for any comments you make and their accuracy. We take no responsibility and assume no liability for any comments posted by you or any third-party.
<br><br>
</p>
<p><b>SECTION 10 - PERSONAL INFORMATION</b><br><br>



Your submission of personal information through the store is governed by our <a href="privacyPolicy.jsp">Privacy Policy</a>.
<br><br></p>
<p><b>SECTION 11 - ERRORS, INACCURACIES AND OMISSIONS</b><br><br>



Occasionally there may be information on our site or in the Service that contains typographical errors, inaccuracies or omissions that may relate to product descriptions, pricing, promotions, offers, product shipping charges, transit times and availability. We reserve the right to correct any errors, inaccuracies or omissions, and to change or update information or cancel orders if any information in the Service or on any related website is inaccurate at any time without prior notice (including after you have submitted your order).
<br><br>
We undertake no obligation to update, amend or clarify information in the Service or on any related website, including without limitation, pricing information, except as required by law. No specified update or refresh date applied in the Service or on any related website, should be taken to indicate that all information in the Service or on any related website has been modified or updated.
<br><br></p>
<p><b>SECTION 12 - PROHIBITED USES</b><br><br>



In addition to other prohibitions as set forth in the Terms of Service, you are prohibited from using the site or its content: (a) for any unlawful purpose; (b) to solicit others to perform or participate in any unlawful acts; (c) to violate any international, federal, provincial or state regulations, rules, laws, or local ordinances; (d) to infringe upon or violate our intellectual property rights or the intellectual property rights of others; (e) to harass, abuse, insult, harm, defame, slander, disparage, intimidate, or discriminate based on gender, sexual orientation, religion, ethnicity, race, age, national origin, or disability; (f) to submit false or misleading information; (g) to upload or transmit viruses or any other type of malicious code that will or may be used in any way that will affect the functionality or operation of the Service or of any related website, other websites, or the Internet; (h) to collect or track the personal information of others; (i) to spam, phish, pharm, pretext, spider, crawl, or scrape; (j) for any obscene or immoral purpose; or (k) to interfere with or circumvent the security features of the Service or any related website, other websites, or the Internet. We reserve the right to terminate your use of the Service or any related website for violating any of the prohibited uses.


<br><br></p>
<p><b>SECTION 13 - DISCLAIMER OF WARRANTIES; LIMITATION OF LIABILITY</b><br><br>



We do not guarantee, represent or warrant that your use of our service will be uninterrupted, timely, secure or error-free.

We do not warrant that the results that may be obtained from the use of the service will be accurate or reliable.

You agree that from time to time we may remove the service for indefinite periods of time or cancel the service at any time, without notice to you.
<br><br>
You expressly agree that your use of, or inability to use, the service is at your sole risk. The service and all products and services delivered to you through the service are (except as expressly stated by us) provided 'as is' and 'as available' for your use, without any representation, warranties or conditions of any kind, either express or implied, including all implied warranties or conditions of merchantability, merchantable quality, fitness for a particular purpose, durability, title, and non-infringement.
<br><br>
In no case shall <b>BestKart4U.com</b>, our directors, officers, employees, affiliates, agents, contractors, interns, suppliers, service providers or licensors be liable for any injury, loss, claim, or any direct, indirect, incidental, punitive, special, or consequential damages of any kind, including, without limitation lost profits, lost revenue, lost savings, loss of data, replacement costs, or any similar damages, whether based in contract, tort (including negligence), strict liability or otherwise, arising from your use of any of the service or any products procured using the service, or for any other claim related in any way to your use of the service or any product, including, but not limited to, any errors or omissions in any content, or any loss or damage of any kind incurred as a result of the use of the service or any content (or product) posted, transmitted, or otherwise made available via the service, even if advised of their possibility. Because some states or jurisdictions do not allow the exclusion or the limitation of liability for consequential or incidental damages, in such states or jurisdictions, our liability shall be limited to the maximum extent permitted by law.

<br><br></p>
<p><b>SECTION 14 - INDEMNIFICATION</b><br><br>




You agree to indemnify, defend and hold harmless BestKart4U.com and our parent, subsidiaries, affiliates, partners, officers, directors, agents, contractors, licensors, service providers, subcontractors, suppliers, interns and employees, harmless from any claim or demand, including reasonable attorneys? fees, made by any third-party due to or arising out of your breach of these Terms of Service or the documents they incorporate by reference, or your violation of any law or the rights of a third-party.
<br><br></p>
<p><b>SECTION 15 - SEVERABILITY</b><br><br>



In the event that any provision of these Terms of Service is determined to be unlawful, void or unenforceable, such provision shall nonetheless be enforceable to the fullest extent permitted by applicable law, and the unenforceable portion shall be deemed to be severed from these Terms of Service, such determination shall not affect the validity and enforceability of any other remaining provisions.

<br><br></p>
<p><b>SECTION 16 - TERMINATION</b><br><br>



The obligations and liabilities of the parties incurred prior to the termination date shall survive the termination of this agreement for all purposes.

These Terms of Service are effective unless and until terminated by either you or us. You may terminate these Terms of Service at any time by notifying us that you no longer wish to use our Services, or when you cease using our site.
<br><br>
If in our sole judgment you fail, or we suspect that you have failed, to comply with any term or provision of these Terms of Service, we also may terminate this agreement at any time without notice and you will remain liable for all amounts due up to and including the date of termination; and/or accordingly may deny you access to our Services (or any part thereof).
<br><br></p>
<p><b>SECTION 17 - ENTIRE AGREEMENT</b><br><br>



The failure of us to exercise or enforce any right or provision of these Terms of Service shall not constitute a waiver of such right or provision.

These Terms of Service and any policies or operating rules posted by us on this site or in respect to The Service constitutes the entire agreement and understanding between you and us and govern your use of the Service, superseding any prior or contemporaneous agreements, communications and proposals, whether oral or written, between you and us (including, but not limited to, any prior versions of the Terms of Service).
<br><br>
Any ambiguities in the interpretation of these Terms of Service shall not be construed against the drafting party.

<br><br></p>
<p><b>SECTION 18 - CHANGES TO TERMS OF SERVICE</b><br><br>



You can review the most current version of the Terms of Service at any time at this page.

We reserve the right, at our sole discretion, to update, change or replace any part of these Terms of Service by posting updates and changes to our website. It is your responsibility to check our website periodically for changes. Your continued use of or access to our website or the Service following the posting of any changes to these Terms of Service constitutes acceptance of those changes.
<br><br></p>
<p><b>SECTION 19 - CONTACT INFORMATION</b><br><br>



Questions about the Terms of Service should be sent to us at <a href="mailto:support@bestkart4u.com">support@bestkart4u.com</a>.

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