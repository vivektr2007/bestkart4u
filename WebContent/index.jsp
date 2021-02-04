<!doctype html>
<html xmlns:fb="http://ogp.me/ns/fb#">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE-EmulateIE8">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>BestKart4U : Best online Store and Supermarket in India</title>
<meta name="keywords" content=""/>
<meta name="description" content=""/>
<link href="http://fonts.googleapis.com/css?family=Roboto:400,500,300" rel="stylesheet" type="text/css" />
<link href="http://fonts.googleapis.com/css?family=Lato:600,300,400,700" rel="stylesheet" type="text/css" />
<link href="http://fonts.googleapis.com/css?family=Roboto+Slab:700,500,400,300,100" rel="stylesheet" type="text/css" />
<link rel="icon" type="image/png" href="images/favicon.ico" />

<!-- <link rel="stylesheet" href="css/jquery-ui-autocomplete.css" /> -->
<link href="css/stylesheet.css" rel="stylesheet" type="text/css" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<!-- <script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script> -->
<script src="js/sliding-banner-sub.js" type="text/javascript"></script>
<script src="js/sliding-banner.js" type="text/javascript"></script>
<script src="js/common.js" type="text/javascript"></script>
<script src="js/jquery-ui.min.js" type="text/javascript"></script>
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
#slideshow3{
	width:270px!important;
}
</style>
<script type="text/javascript" src="js/testimonials.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		/*	
		jQuery("#slideshow3").cycle({
        timeout:1, // no autoplay
        //fx: 'scrollLeft', //
        next: '#next3',
        prev: '#prev3',
            continuous:0,
            timeout:'7000'
        });*/
		$('#slideshow3') 
		.after('<div id="nav">') 
		.cycle({ 
			fx:     'scrollLeft', 
			speed:  'slow', 
			timeout: 7000, 
			pager:  '#nav' 
		});
	});
</script>

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
					<li><a href="index.jsp" class="current">home</a></li>
					<li><a href="aboutUs.jsp">who we are</a></li>
					<li><a href="contacts.jsp">contact us</a></li>
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
			<div class="uiv2-content-wrapper">
				<!-- Guaranteed Block Starts here -->
				<div class="guaranteed">
					<ul>
						<li class="same-day">&nbsp;</li>
						<li class="on-time">&nbsp;</li>
						<li class="quality">&nbsp;</li>
						<li class="payment-options no_margin">&nbsp;</li>
					</ul>
				</div>
				<!-- Guaranteed Block Ends here -->
				<!--<div class="blocks-sub">
					<div class="blocks_sub">
						<ul>
							<li>
								<a href="javascript:void();"><img src="images/small-banner1.jpg" width="319" height="191" alt="" title="" /></a>
								<div class="caption"><a href="javascript:void();">Fresh and healthy vegetables &amp; fruits delivered at your doorsteps</a></div>
							</li>
							<li>
								<a href="javascript:void();"><img src="images/small-banner2.jpg" width="319" height="191" alt="" title="" /></a>
								<div class="caption"><a href="javascript:void();">get locally produced fresh vegetables online</a></div>
							</li>
							<li>
								<a href="javascript:void();"><img src="images/small-banner3.jpg" width="319" height="191" alt="" title="" /></a>
								<div class="social-media">
									<a href="https://www.facebook.com/bestkart4u/timeline" target="_blank" rel="nofollow" class="fb-follow">&nbsp;</a>
									<a href="https://twitter.com/bestkart4u" target="_blank" rel="nofollow" class="tw-follow">&nbsp;</a>
								</div>
							</li>
							<li>
								<a href="javascript:void();"><img src="images/small-banner4.jpg" width="319" height="191" alt="" title="" /></a>
								<div class="caption"><a href="javascript:void();">We bring all household grocery under one roof</a></div>
							</li>
							<li>
								<a href="javascript:void();"><img src="images/small-banner5.jpg" width="319" height="191" alt="" title="" /></a>
								<div class="caption"><a href="javascript:void();">we greatly value customer satisfaction</a></div>
							</li>
							<li>
								<a href="javascript:void();"><img src="images/small-banner6.jpg" width="319" height="191" alt="" title="" /></a>
								<div class="caption"><a href="javascript:void();">kindly, spare sometime and fill in this brief online survey for us.<br />Tell us how do you like our website. We greatly appreciate your feedback.</a></div>
							</li>
							<li>
								<a href="javascript:void();"><img src="images/small-banner7.jpg" width="319" height="191" alt="" title="" /></a>
								<div class="caption"><a href="javascript:void();">dazzle with our range of beauty products</a></div>
							</li>
							<li>
								<a href="javascript:void();"><img src="images/small-banner8.jpg" width="319" height="191" alt="" title="" /></a>
								<div class="caption"><a href="javascript:void();">enjoy beverages as you like</a></div>
							</li>
							<li>
								<a href="javascript:void();"><img src="images/small-banner9.jpg" width="319" height="191" alt="" title="" /></a>
								<div class="caption"><a href="javascript:void();">a wide variety of snacks to choose from</a></div>
							</li>
						</ul>
					</div>
				</div>-->
				<div class="category-blocks">
					<ul>
						<li>
							<h3><a href="javascript:void();">Fruits &amp; Vegetables</a></h3>
							<ul>
								<li><a href="ViewProducts.action?startIndex=1&categoryName=FRUITS&categoryId=1&rootCategory=8&subRootCategory=2">Fruits</a></li>
								<li><a href="ViewProducts.action?startIndex=1&categoryName=VEGETABLES&categoryId=1&rootCategory=8&subRootCategory=1">Vegetables</a></li>
								<li><a href="ViewProductsForcategory.action?rootCategory=8&startIndex=1&categoryName=FRUITS ,, VEGETABLES"><b>See All</b></a></li>
							</ul>
							<img src="images/fruits-vegetables.png" />
						</li>
						<li>
							<h3><a href="javascript:void();">Atta, Grains &amp; Staples</a></h3>
							<ul>
								<li><a href="ViewProducts.action?startIndex=1&categoryName=DAL&categoryId=1&rootCategory=1&subRootCategory=1">Dal</a></li>
								<li><a href="ViewProducts.action?startIndex=1&categoryName=SUGAR&categoryId=2&rootCategory=1&subRootCategory=8">Sugar</a></li>
								<li><a href="ViewProducts.action?startIndex=1&categoryName=WHEAT ATTA&categoryId=5&rootCategory=1&subRootCategory=4">Wheat Atta</a></li>
								<li><a href="ViewProducts.action?startIndex=1&categoryName=READY MASALAS&categoryId=1&rootCategory=1&subRootCategory=5">Ready Masalas</a></li>
								<li><a href="ViewProductsForcategory.action?rootCategory=1&startIndex=1&categoryName=ATTA, GRAINS ,, STAPLES"><b>See All</b></a></li>
							</ul>
							<img src="images/atta-grains.png" />
						</li>
						<li>
							<h3><a href="javascript:void();">Beverages</a></h3>
							<ul>
								<li><a href="ViewProducts.action?startIndex=1&categoryName=ENERGY DRINK&categoryId=1&rootCategory=2&subRootCategory=3">Energy Drinks</a></li>
								<li><a href="ViewProducts.action?startIndex=1&categoryName=COFFEE&categoryId=1&rootCategory=2&subRootCategory=2">Coffee</a></li>
								<li><a href="ViewProducts.action?startIndex=1&categoryName=SOFT DRINK&categoryId=1&rootCategory=2&subRootCategory=5">Soft Drinks</a></li>
								<li><a href="ViewProducts.action?startIndex=1&categoryName=APPLE JUICES&categoryId=1&rootCategory=2&subRootCategory=1">Apple Juices</a></li>
								<li><a href="ViewProductsForcategory.action?rootCategory=2&startIndex=1&categoryName=BEVERAGES"><b>See All</b></a></li>
							</ul>
							<img src="images/beverages.png" />
						</li>
						<li>
							<h3><a href="javascript:void();">Bread &amp; Dairy Products</a></h3>
							<ul>
								<li><a href="ViewProducts.action?startIndex=1&categoryName=BREAD&categoryId=1&rootCategory=7&subRootCategory=2">Bread</a></li>
								<li><a href="ViewProducts.action?startIndex=1&categoryName=MILK&categoryId=1&rootCategory=7&subRootCategory=1">Milk</a></li>
								<li><a href="ViewProductsForcategory.action?rootCategory=7&startIndex=1&categoryName=BREAD ,, DAIRY PRODUCTS"><b>See All</b></a></li>
							</ul>
							<img src="images/bread-dairy.png" />
						</li>
						<li>
							<h3><a href="javascript:void();">Branded Foods</a></h3>
							<ul>
								<li><a href="ViewProducts.action?startIndex=1&categoryName=FLAKES ,, OATS&categoryId=1&rootCategory=3&subRootCategory=3">Flakes &amp; Oats</a></li>
								<li><a href="ViewProducts.action?startIndex=1&categoryName= JAMS&categoryId=1&rootCategory=3&subRootCategory=6">Jams</a></li>
								<li><a href="ViewProducts.action?startIndex=1&categoryName=PICKLES&categoryId=1&rootCategory=3&subRootCategory=9">Pickles</a></li>
								<li><a href="ViewProducts.action?startIndex=1&categoryName=BAKING ,, DESERT ITEMS&categoryId=1&rootCategory=3&subRootCategory=2">Baking &amp; Desert Items </a></li>
								<li><a href="ViewProductsForcategory.action?rootCategory=3&startIndex=1&categoryName=BRANDED FOODS"><b>See All</b></a></li>
							</ul>
							<img src="images/branded-foods.png" />
						</li>
						<li>
							<h3><a href="javascript:void();">Home Care</a></h3>
							<ul>
								<li><a href="ViewProducts.action?startIndex=1&categoryName=CAR FRESHNER&categoryId=1&rootCategory=5&subRootCategory=9">Car Freshners</a></li>
								<li><a href="ViewProducts.action?startIndex=1&categoryName=BROOM,BRUSHES ,, POCHA&categoryId=2&rootCategory=5&subRootCategory=1">BROOM,BRUSHES &amp; POCHA</a></li>
								<li><a href="ViewProducts.action?startIndex=1&categoryName=FLOOR CLEANER&categoryId=1&rootCategory=5&subRootCategory=1">Floor Cleaner</a></li>
								<li><a href="ViewProducts.action?startIndex=1&categoryName=LIQUID&categoryId=4&rootCategory=5&subRootCategory=3">Liquied Detergents</a></li>
								<li><a href="ViewProductsForcategory.action?rootCategory=5&startIndex=1&categoryName=HOME CARE"><b>See All</b></a></li>
							</ul>
							<img src="images/home-care.png" />
						</li>
						<li>
							<h3><a href="javascript:void();">Personal Care</a></h3>
							<ul>
								<li><a href="ViewProducts.action?startIndex=1&categoryName=CREAM ,, LOTION&categoryId=2&rootCategory=4&subRootCategory=1">Cream &amp; Lotion</a></li>
								<li><a href="ViewProducts.action?startIndex=1&categoryName=DIAPERS ,, WIPES&categoryId=3&rootCategory=4&subRootCategory=1">Dipers &amp; Wipes</a></li>
								<li><a href="ViewProducts.action?startIndex=1&categoryName=FACIAL KIT&categoryId=4&rootCategory=4&subRootCategory=3">Facial Kit</a></li>
								<li><a href="ViewProducts.action?startIndex=1&categoryName=SOAP ,, LIQUID&categoryId=5&rootCategory=4&subRootCategory=1">Soap &amp; Liquid</a></li>
								<li><a href="ViewProductsForcategory.action?rootCategory=4&startIndex=1&categoryName=PERSONAL CARE"><b>See All</b></a></li>
							</ul>
							<img src="images/personal-care.png" />
						</li>
						<li>
							<h3><a href="javascript:void();">Patanjali</a></h3>
							<ul>
								<li><a href="ViewProducts.action?startIndex=1&categoryName=SHARBAT ,, SQUASH&categoryId=1&rootCategory=6&subRootCategory=2">Sharbat &amp; Squash</a></li>
								<li><a href="ViewProducts.action?startIndex=1&categoryName=BISCUITS ,, SWEETS&categoryId=1&rootCategory=6&subRootCategory=4">Biscuits &amp; Sweets</a></li>
								<li><a href="ViewProducts.action?startIndex=1&categoryName=ATTA ,, GRAINS&categoryId=1&rootCategory=6&subRootCategory=1">Atta &amp; Grains</a></li>
								<li><a href="ViewProducts.action?startIndex=1&categoryName=OIL ,, GHEE&categoryId=2&rootCategory=6&subRootCategory=1">Oil &amp; Ghee</a></li>
								<li><a href="ViewProductsForcategory.action?rootCategory=6&startIndex=1&categoryName=PATANJALI"><b>See All</b></a></li>
							</ul>
							<img src="images/patanjali.png" />
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="main_wrapper">
	<div class="wrapper">
		<div class="uiv2-content-wrapper">
			<div class="uiv2-super-market">
				<div class="uiv2-super-content" style=" width:100%;">
					<h1>Online Store in Dwarka(New Delhi)</h1>
					<p>Gone are the days when shopping was fun and it was on a wish-list of every citizen's mind. Nowadays everyone is busy and its no longer a fun activity to go shopping for your daily needs. We, at <b>BestKart4U.com</b>, are excited about making life simpler and easier. Now you can buy Food Products &amp; grocery online at your leisure and from the comfort of your home - no more standing in the long queues at ration shops, provision stores &amp; supermarkets. Online grocery shopping in Dwarka(New Delhi) has been made easy &amp; simple when you are shopping at BestKart4U.</p>
					<div class='seo-test' style='display:none'>
						<h2>Online Food Store</h2>
						<p>BestKart4U.com is the first comprehensive online grocery store in Dwarka(New Delhi). With over 10,000 products and 1000 brands in our grocery list you will find everything you are looking for. Right from fresh fruits and vegetables, rice and daals, spices and seasonings to packaged bread, bakery and dairy products and other branded foods? We have it all. Save time and money, shop at <b>BestKart4U.com</b> - the best online fruit &amp; vegetable store, meat store, provisions store - an all encompassing online groceries store of Dwarka(New Delhi).</p>
						<h2>India's Best Online Supermarket</h2>
						<p>Shopping with BestKart4U.com, the best online grocery and food shopping store at Dwarka(New Delhi), is very simple and easy. No more standing in lines, carrying heavy bags and looking for parking!</p>
						<p>Get started with online grocery shopping at India's favourite online grocery store. Choose from a wide range of options in every groceries list, exclusively handpicked to help you find the best quality available at the lowest prices. Select a time slot for home delivery and your order will be delivered at your doorstep, anywhere Dwarka(New Delhi). In future we will be expanding our Online supermarkets to other places as well. You can pay us by cash also. We guarantee on time delivery and the best quality! Happy Shopping!</p>
					</div>
					<a class="seo-read-more">read more ></a>
				</div>
				<div class="brands">
					<div id="slideshow3">
						<div>
							<ul>
								<li><a href="javascript:void();"><img src="images/brands1.jpg" width="125" height="64" alt="" title="" /></a></li>
								<li><a href="javascript:void();"><img src="images/brands2.jpg" width="125" height="64" alt="" title="" /></a></li>
								<li><a href="javascript:void();"><img src="images/brands3.jpg" width="125" height="64" alt="" title="" /></a></li>
								<li><a href="javascript:void();"><img src="images/brands4.jpg" width="125" height="64" alt="" title="" /></a></li>
								<li><a href="javascript:void();"><img src="images/brands5.jpg" width="125" height="64" alt="" title="" /></a></li>
								<li><a href="javascript:void();"><img src="images/brands6.jpg" width="125" height="64" alt="" title="" /></a></li>
							</ul>
						</div>
						<div>
							<ul>
								<li><a href="javascript:void();"><img src="images/brands4.jpg" width="125" height="64" alt="" title="" /></a></li>
								<li><a href="javascript:void();"><img src="images/brands5.jpg" width="125" height="64" alt="" title="" /></a></li>
								<li><a href="javascript:void();"><img src="images/brands6.jpg" width="125" height="64" alt="" title="" /></a></li>
								<li><a href="javascript:void();"><img src="images/brands1.jpg" width="125" height="64" alt="" title="" /></a></li>
								<li><a href="javascript:void();"><img src="images/brands2.jpg" width="125" height="64" alt="" title="" /></a></li>
								<li><a href="javascript:void();"><img src="images/brands3.jpg" width="125" height="64" alt="" title="" /></a></li>
							</ul>
						</div>
						<div>
							<ul>
								<li><a href="javascript:void();"><img src="images/brands1.jpg" width="125" height="64" alt="" title="" /></a></li>
								<li><a href="javascript:void();"><img src="images/brands2.jpg" width="125" height="64" alt="" title="" /></a></li>
								<li><a href="javascript:void();"><img src="images/brands3.jpg" width="125" height="64" alt="" title="" /></a></li>
								<li><a href="javascript:void();"><img src="images/brands1.jpg" width="125" height="64" alt="" title="" /></a></li>
								<li><a href="javascript:void();"><img src="images/brands2.jpg" width="125" height="64" alt="" title="" /></a></li>
								<li><a href="javascript:void();"><img src="images/brands3.jpg" width="125" height="64" alt="" title="" /></a></li>
							</ul>
						</div>
						<div>
							<ul>
								<li><a href="javascript:void();"><img src="images/brands4.jpg" width="125" height="64" alt="" title="" /></a></li>
								<li><a href="javascript:void();"><img src="images/brands5.jpg" width="125" height="64" alt="" title="" /></a></li>
								<li><a href="javascript:void();"><img src="images/brands6.jpg" width="125" height="64" alt="" title="" /></a></li>
								<li><a href="javascript:void();"><img src="images/brands1.jpg" width="125" height="64" alt="" title="" /></a></li>
								<li><a href="javascript:void();"><img src="images/brands2.jpg" width="125" height="64" alt="" title="" /></a></li>
								<li><a href="javascript:void();"><img src="images/brands3.jpg" width="125" height="64" alt="" title="" /></a></li>
							</ul>
						</div>
						<div>
							<ul>
								<li><a href="javascript:void();"><img src="images/brands1.jpg" width="125" height="64" alt="" title="" /></a></li>
								<li><a href="javascript:void();"><img src="images/brands2.jpg" width="125" height="64" alt="" title="" /></a></li>
								<li><a href="javascript:void();"><img src="images/brands3.jpg" width="125" height="64" alt="" title="" /></a></li>
								<li><a href="javascript:void();"><img src="images/brands4.jpg" width="125" height="64" alt="" title="" /></a></li>
								<li><a href="javascript:void();"><img src="images/brands5.jpg" width="125" height="64" alt="" title="" /></a></li>
								<li><a href="javascript:void();"><img src="images/brands6.jpg" width="125" height="64" alt="" title="" /></a></li>
							</ul>
						</div>
						<div>
							<ul>
								<li><a href="javascript:void();"><img src="images/brands4.jpg" width="125" height="64" alt="" title="" /></a></li>
								<li><a href="javascript:void();"><img src="images/brands5.jpg" width="125" height="64" alt="" title="" /></a></li>
								<li><a href="javascript:void();"><img src="images/brands6.jpg" width="125" height="64" alt="" title="" /></a></li>
								<li><a href="javascript:void();"><img src="images/brands1.jpg" width="125" height="64" alt="" title="" /></a></li>
								<li><a href="javascript:void();"><img src="images/brands2.jpg" width="125" height="64" alt="" title="" /></a></li>
								<li><a href="javascript:void();"><img src="images/brands3.jpg" width="125" height="64" alt="" title="" /></a></li>
							</ul>
						</div>
						<div>
							<ul>
								<li><a href="javascript:void();"><img src="images/brands1.jpg" width="125" height="64" alt="" title="" /></a></li>
								<li><a href="javascript:void();"><img src="images/brands2.jpg" width="125" height="64" alt="" title="" /></a></li>
								<li><a href="javascript:void();"><img src="images/brands3.jpg" width="125" height="64" alt="" title="" /></a></li>
								<li><a href="javascript:void();"><img src="images/brands1.jpg" width="125" height="64" alt="" title="" /></a></li>
								<li><a href="javascript:void();"><img src="images/brands2.jpg" width="125" height="64" alt="" title="" /></a></li>
								<li><a href="javascript:void();"><img src="images/brands3.jpg" width="125" height="64" alt="" title="" /></a></li>
							</ul>
						</div>
						<div>
							<ul>
								<li><a href="javascript:void();"><img src="images/brands4.jpg" width="125" height="64" alt="" title="" /></a></li>
								<li><a href="javascript:void();"><img src="images/brands5.jpg" width="125" height="64" alt="" title="" /></a></li>
								<li><a href="javascript:void();"><img src="images/brands6.jpg" width="125" height="64" alt="" title="" /></a></li>
								<li><a href="javascript:void();"><img src="images/brands1.jpg" width="125" height="64" alt="" title="" /></a></li>
								<li><a href="javascript:void();"><img src="images/brands2.jpg" width="125" height="64" alt="" title="" /></a></li>
								<li><a href="javascript:void();"><img src="images/brands3.jpg" width="125" height="64" alt="" title="" /></a></li>
							</ul>
						</div>
					</div>
					<!--<div id="nav" class="nav">
						<a href="javascript:void();" class="activeSlide">1</a>
						<a href="javascript:void();">2</a>
						<a href="javascript:void();">3</a>
						<a href="javascript:void();">4</a>
					</div>-->
				</div>
				<!-- <div class="uiv2-customer-speak-block">
					<div class="uiv2-customer-h">Customer Feedback <a href="javascript:void();">view all></a></div>
					<div id="slideshow3">
						<div>
							<img width="98" height="87" src="images/client1.jpg" alt="customer" />
							<div class="uiv2-customer-speak-cont">
								<p>Best Kart has captured my attention firstly as a practitioner of marketing and more recently as a wow-ed customer. I have bought my monthly groceries from big basket for 3 consecutive months now...</p>
							</div>
						</div>
						<div>
							<img width="98" height="87" src="images/client2.jpg" alt="customer" />
							<div class="uiv2-customer-speak-cont">
								<p>The best part being you do not need to generate a new list everytime.. This is something that used to irritate me big time in conventional shopping...</p>
							</div>
						</div>
						<div>
							<img width="98" height="87" src="images/client3.jpg" alt="customer" />
							<div class="uiv2-customer-speak-cont">
								<p>Most importantly, the prompt delivery service and  well mannered delivery personnel are the icing on the cake! Best Kart...Every Promise..Big or Small delivered at your doorstep...Thanks Best Kart...</p>
							</div>
						</div>
						<div>
							<img width="98" height="87" src="images/client4.jpg" alt="customer" />
							<div class="uiv2-customer-speak-cont">
								<p>Dear Customer Service Team at bestkart.com, first of all I must say that bestkart.com is a blessing to everyone in today?s hectic life. I am from Goa and am on a Short Term Assignment and stay in a Hotel at MG Road....</p>
							</div>
						</div>
					</div>
				</div> -->
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
