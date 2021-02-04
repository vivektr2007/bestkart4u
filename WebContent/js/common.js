$(function() {
	  $("#SearchPendingOrder").autocomplete({
	  	source: function(request, response) {
	  	$.ajax({
	  	url: "PendingOrderIdSearchAutocomplete.action",
	      type: "POST",
	  	dataType: "json",
	  	data: {	name: request.term},
	  	success: function( data ) {
	  		response( $.map( data, function( item ) {
	  		return {	
	  			label: item.name
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

$(function() {
	  $("#SearchPreviousOrder").autocomplete({
	  	source: function(request, response) {
	  	$.ajax({
	  	url: "PreviousOrderIdSearchAutocomplete.action",
	      type: "POST",
	  	dataType: "json",
	  	data: {	name: request.term},
	  	success: function( data ) {
	  		response( $.map( data, function( item ) {
	  		return {	
	  			label: item.name
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


function viewWishlist() {
	$.get(
					"ViewWishlist.action",
					{},
					function(data) {
						if (dataArr != 'undefined' && data != ''
								&& data != 'null' && data != 'NULL') {
							var dataArr = data.split("##");
							var header = "<div id='address_error'></div><div class='tabular_head'>"
									+
									"<div class='name'><b>Product Name</b></div>"
									+
									"<div class='brand'><b>Product Brand</b></div>"
									+
									"<div class='prod_size'><b>Product Size</b></div>"
									+
									"<div class='prod_quantity'><b>Quantity</b></div>"
									+
									"<div class='prod_unit'><b>Unit Price</b></div>"
									+
									"<div class='prod_price'><b>Total Price</b></div>"
									+
									"<div class='prod_action'><b>Action</b></div>"
									+
									"</div><div class='wishlist_main_block'>";
							var body = header + "";
							var allAmount = 0.0;
							for ( var i = 0; i < dataArr.length; i++) {
								var prodDetail = dataArr[i].split("#");

								var categoryId = prodDetail[0];
								var subCategoryId = prodDetail[1];
								var childCategoryId = prodDetail[2];
								var imagePath = prodDetail[3];
								var productName = prodDetail[4];
								var productBrandName = prodDetail[5];
								var actualPrice = prodDetail[6];
								var offerPrice = prodDetail[7];
								var productSize = prodDetail[8];
								var quantity = prodDetail[9];
								var productPriceDetail = prodDetail[10];
								var productId = prodDetail[11];
								var total = parseFloat(offerPrice)
										* parseInt(quantity);
								allAmount = parseFloat(allAmount)
										+ parseFloat(total);

								var categoryIdHidden = "<input type='hidden' value='"+categoryId+"' name='categoryIdWishList"+i+"'id='categoryIdWishList"+i+"'/>";
								var subCategoryIdHidden = "<input type='hidden' value='"+subCategoryId+"' name='subCategoryIdWishList"+i+"'id='subCategoryIdWishList"+i+"'/>";
								var childCategoryIdHidden = "<input type='hidden' value='"+childCategoryId+"' name='childCategoryIdWishList"+i+"'id='childCategoryIdWishList"+i+"'/>";
								var imagePathHidden = "<input type='hidden' value='"+imagePath+"' name='imagePathWishList"+i+"'id='imagePathWishList"+i+"'/>";
								var productNameHidden = "<input type='hidden' value='"+productName+"' name='productNameWishList"+i+"'id='productNameWishList"+i+"'/>";
								var productBrandNameHidden = "<input type='hidden' value='"+productBrandName+"' name='productBrandNameWishList"+i+"'id='productBrandNameWishList"+i+"'/>";
								var actualPriceHidden = "<input type='hidden' value='"+actualPrice+"' name='actualPriceWishList"+i+"'id='actualPriceWishList"+i+"'/>";
								var offerPriceHidden = "<input type='hidden' value='"+offerPrice+"' name='offerPriceWishList"+i+"'id='offerPriceWishList"+i+"'/>";
								var quantityHidden = "<input type='hidden' value='"+quantity+"' name='quantityWishList"+i+"'id='quantityWishList"+i+"'/>";
								var productSizeHidden = "<input type='hidden' value='"+productSize+"' name='productSizeWishList"+i+"'id='productSizeWishList"+i+"'/>";
								var productIdHidden = "<input type='hidden' value='"+productId+"' name='productIdWishList"+i+"'id='productIdWishList"+i+"'/>";
								var allHiddenValues = categoryIdHidden+subCategoryIdHidden+childCategoryIdHidden+imagePathHidden+productNameHidden+productBrandNameHidden
								+actualPriceHidden+offerPriceHidden+quantityHidden+productSizeHidden+productIdHidden;
								
								body = body
										+ "<div class='wishlist_content'>"
										+

										"<div class='name'>"
										+ productName
										+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src='"
										+ imagePath
										+ "' width='50' height='80'/></div>"
										+

										"<div class='brand'>"
										+ productBrandName
										+ "</div>"
										+

										"<div class='prod_size'>"
										+ productSize
										+ "</div>"
										+

										"<div class='prod_quantity'>"
										+

										"<input type='button' value='-' class='qtyminus'  onclick='quanDecrease("+i+")' field='quantity"
										+ i
										+ "' style=' display:inline; padding:2px 4px; cursor:pointer;' />"
										+

										"<input type='text' readonly = 'readonly' id='quanWishList"+i+"' name='quantity"
										+ i
										+ "' value='"
										+ quantity
										+ "' class='qty' style=' display:inline; padding:2px 4px; width:20px;' />"
										+

										"<input type='button' value='+' class='qtyplus' onclick='quanIncrease("+i+")' field='quantity"
										+ i
										+ "' style=' display:inline; padding:2px 4px; cursor:pointer;' />"
										+

										"</div>"
										+

										"<div class='prod_unit'><span id='offerPriceSpan"+i+"'>"
										+ "Rs "+offerPrice
										+ "</span></div>"
										+

										"<div class='prod_price'><span id='totalPriceSpan"+i+"'>"
										+ "Rs "+ total
										+ "</span></div>"
										+

										"<div class='prod_action'><input type='button' value='Add To Cart' class='wishlist_add_cart' onclick='addToCartFromWishlist("+i+")'/><input type='button' value='Remove' onclick='removeFromWishlist("+i+")' class='wishlist_remove_cart'/></div>"
										+allHiddenValues+

										"</div>";

							}

							body = body
									+ "</div><div class='total_block'><b>Total Amount: Rs "
									+ allAmount + "</b></div>";

							$("#uiv2-new-address-popup").html(body);

							show_code('wishlist');

						}
						else{
							var header = "<div id='address_error'></div><div class='tabular_head'>"
									+
									"<div class='name'><b>Product Name</b></div>"
									+
									"<div class='brand'><b>Product Brand</b></div>"
									+
									"<div class='prod_size'><b>Product Size</b></div>"
									+
									"<div class='prod_quantity'><b>Quantity</b></div>"
									+
									"<div class='prod_unit'><b>Unit Price</b></div>"
									+
									"<div class='prod_price'><b>Total Price</b></div>"
									+
									"<div class='prod_action'><b>Action</b></div>"
									+
									"</div><div class='wishlist_main_block'>";
							var body = header + "";
							var allAmount = 0.0;
								body = body
										+ "<div class='wishlist_content'>"
										+
										"<div class='no-item'>No Item in wishlist.</div>";
							body = body
									+ "</div><div class='total_block'><b>Total Amount: Rs "
									+ allAmount + "</b></div>";
							$("#uiv2-new-address-popup").html(body);
							show_code('wishlist');
						}
					},

					"html"

			);

}
function removeFromWishlist(i){
	var productId = $("#productIdWishList"+i).val();
	$.get(
		    "RemoveFromWishlist.action",
		    {productId:productId},
		    function(data) {
		    	$('.add_this_content').fadeOut(-1);
		    	$("#productForSuccessMSG").html("Item removed from wishlist.");
                $('.add_this_content').fadeIn('slow').delay(1500).fadeOut('slow');
				viewWishlist();
		    },
		    "html"
		);
}
function addToCartFromWishlist(i){
	var categoryIdWishList = $("#categoryIdWishList"+i).val();
	var subCategoryId = $("#subCategoryIdWishList"+i).val();
	//var childCategoryId = $("#childCategoryIdWishList"+i).val();
	var imagePath = $("#imagePathWishList"+i).val();
	var productName = $("#productNameWishList"+i).val();
	var productBrandName = $("#productBrandNameWishList"+i).val();
	var actualPrice = $("#actualPriceWishList"+i).val();
	var offerPrice = $("#offerPriceWishList"+i).val();
	var productSize = $("#productSizeWishList"+i).val();
	var quantity = $("#quantityWishList"+i).val();
	var productId = $("#productIdWishList"+i).val();
	
	$.get(
		    "AddToCartFromWishlist.action",
		    {productId:productId,productSize : productSize,actualPrice : actualPrice,price : offerPrice,quantity:quantity,brandName:productBrandName,
		    	productName:productName,imagePath:imagePath,subRootCategory:subCategoryId,rootCategory:categoryIdWishList  },
		    function(data) {
		    	//alert("Item added in cart");
		    	//$("#addedtocartmsg").show();
		    		$('.add_this_content').fadeOut(-1);
		    	$("#productForSuccessMSG").html(quantity + " item of" +productName+"("+productBrandName+")" + " with " + productSize + " added to cart");
                $('.add_this_content').fadeIn('slow').delay(1500).fadeOut('slow'); 
		    	getCookies();
		    	//viewWishlist();
		    },
		    "html"
		);
	
}

function show_code(id) {

	document.getElementById(id).style.display = "block";

}

function hide_code(id) {

	document.getElementById(id).style.display = "none";

}

function customerFeedback() {

	var name = $("#nameFeedback").val().trim();

	var email = $("#emailFeedback").val();

	var contact = $("#contact").val();

	var subject = $("#subject").val();

	var message = $("#message").val();

	var count = 0;

	if (name == '' || name == 'YOUR NAME HERE' || name == 'your name here') {

		$("#nameFeedback").next("div").remove();

		$("#nameFeedback")

				.after(

						$("<div id='errorDiv' class='error' style='color: #BD4247;'> Field can't be Empty. </div>"));

		count = count + 1;
		return false;

	}

	else {

		$("#nameFeedback").next("div").remove();

	}

	if (email == '' || email == 'email address' || email == 'EMAIL ADDRESS') {

		$("#emailFeedback").next("div").remove();

		$("#emailFeedback")

				.after(

						$("<div id='errorDiv' class='error' style='color: #BD4247;'> Field can't be Empty. </div>"));

		count = count + 1;
		return false;
	}

	else if (!validateEmail(email)) {

		$("#emailFeedback").next("div").remove();

		$("#emailFeedback")

				.after(

						$("<div id='errorDiv' class='error' style='color: #BD4247;'> Email id is not valid. </div>"));

		count = count + 1;
		return false;
	}

	else {

		$("#emailFeedback").next("div").remove();

	}

	if (contact == '' || contact == 'contact number'
			|| contact == 'CONTACT NUMBER') {

		$("#contact").next("div").remove();

		$("#contact")

				.after(

						$("<div id='errorDiv' class='error' style='color: #BD4247;'> Field can't be Empty. </div>"));

		count = count + 1;
		return false;
	}

	else {

		$("#contact").next("div").remove();

	}

	if (subject == '' || subject == 'subject' || subject == 'SUBJECT') {

		$("#subject").next("div").remove();

		$("#subject")

				.after(

						$("<div id='errorDiv' class='error' style='color: #BD4247;'> Field can't be Empty. </div>"));

		count = count + 1;
		return false;
	}

	else {

		$("#subject").next("div").remove();

	}

	if (message == '' || message == 'message' || message == 'MESSAGE') {

		$("#message").next("div").remove();

		$("#message")

				.after(

						$("<div id='errorDiv' class='error' style='color: #BD4247;'> Field can't be Empty. </div>"));

		count = count + 1;
		return false;
	}

	else {

		$("#message").next("div").remove();

	}

	if (count > 0) {

		return false;

	}

	$
			.get(

					"FeddbackSubmit.action",

					{
						name : name,
						email : email,
						contact : contact,
						subject : subject,
						message : message
					},

					function(data) {

						if (data == 'success') {
							$('.add_this_content').fadeOut(-1);
							$("#productForSuccessMSG").html("Your feedback has been submitted successfully. Thanks.");
			                $('.add_this_content').fadeIn('slow').delay(1500).fadeOut('slow');
							$("#nameFeedback").val("your name here");

							$("#emailFeedback").val("email address");

							$("#contact").val("contact number");

							$("#subject").val("subject");

							$("#message").val("message");

							$('#feedback_content').css('display', 'none');

						}

						else {

							alert("Your feedback has not been submitted due to some error. Please try later. Thanks.");

							$("#nameFeedback").val("your name here");

							$("#emailFeedback").val("email address");

							$("#contact").val("contact number");

							$("#subject").val("subject");

							$("#message").val("message");

							$('#feedback_content').css('display', 'none');

						}

					},

					"html"

			);

}

function quanIncrease(i) {
	
	var val = parseInt($("#quanWishList"+i).val())+1;
	var offerPrice = ($("#offerPriceSpan"+i).text().trim().split(" ")[1]).trim();
	var offerPriceSpan = parseFloat(offerPrice)*val;
	$("#totalPriceSpan"+i).text("Rs "+offerPriceSpan);
	$("#quanWishList"+i).val(val);
	$("#quantityWishList"+i).val(val);
	
}

function quanDecrease(i) {
	var val = parseInt($("#quanWishList"+i).val());
	if(val > 0){
		val = val-1;
		var offerPrice = ($("#offerPriceSpan"+i).text().trim().split(" ")[1]).trim();
		var offerPriceSpan = parseFloat(offerPrice)*val;
		$("#totalPriceSpan"+i).text("Rs "+offerPriceSpan);
		$("#quanWishList"+i).val(val);
		$("#quantityWishList"+i).val(val);
	}
	
}

$(document).ready(function() {

	$('#feedback').click(function() {

		$('#' + this.id + "_content").css('display', 'block');

	});

	$('#feedback_cancel').click(function() {

		$('#feedback_content').css('display', 'none');

	});

});

function subscribe() {

	var emailId = $("#subscriberEmail").val().trim();

	// alert(emailId);

	if (emailId == '' || emailId == 'Enter your email address') {
		$('#subscribe_success p').text("Please enter Email Id.");	
		$('#subscribe_success').css('display','block');

		return false;

	}

	else if (!validateEmail(emailId)) {

		$('#subscribe_success p').text("Please enter valid Email Id.");	
		$('#subscribe_success').css('display','block');
		return false;

	}

	else {

		$.get(

		"subscribe.action",

		{
			emailId : emailId
		},

		function(data) {

			if (data == 'success') {
				$('.add_this_content').fadeOut(-1);
				$("#productForSuccessMSG").html("you have subscribed successfully.");
                $('.add_this_content').fadeIn('slow').delay(1500).fadeOut('slow'); 
			}

			else {

				//alert("error");

			}

		},

		"html"

		);

	}

}

function validateEmail(sEmail) {

	var filter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;

	if (filter.test(sEmail)) {

		return true;

	}

	else {

		return false;

	}

}


function addToWishList(productId){
		
		var subRootCategory = $("#subRootCategory").val();
		var rootCategory = $("#rootCategory").val();
		var productName = document.getElementById('categoryName').value;
		var startIndex = document.getElementById('startIndex').value;
		var type =  document.getElementById('type').value;
		var drop = $("#dropDown"+productId).val();
		var allBrandName = '';
		$("input[name='brandName'][checked='checked']").each(function(){
			allBrandName = allBrandName + ","+$(this).val();
		});
		allBrandName = allBrandName.substring(1);
		var childCategory = $("#childCategory").val();
		var quantity = document.getElementById('quantity'+productId).value;
		
		if(quantity == '' || quantity == null){
			 $('#quantity_success').css('display','block');
			document.getElementById('quantity'+productId).focus();
			return false;
		}
		$.get(
			    "AddToWishList.action",
			    {productId:productId,quantity:quantity,subRootCategory:subRootCategory,rootCategory:rootCategory,productName : productName, childCategory:childCategory,startIndex:startIndex,type:type },
			    function(data) {
			    	//alert(data+"test");
			    	var dataArr = data.trim().split(":");
			    	if(dataArr != null && dataArr != '' && dataArr.length >=7 ){
				    	if(dataArr[6]=="gotTOLogin"){
				    		//alert(dataArr[2]);
				    		$("#productIdWishlist").val(dataArr[5]);
				    		$("#rootCategoryWishlist").val(dataArr[0]);
				    		$("#subRootCategoryWishlist").val(dataArr[1]);
				    		$("#productNameWishlist").val(dataArr[3]);
				    		$("#startIndexWishlist").val(dataArr[4]);
				    		$("#typeWishlist").val(type);
				    		$("#childCategoryWishlist").val(dataArr[2]);
				    		$("#quantityWishlist").val(dataArr[7]);
				    		$("#brandName").val(allBrandName);
				    		//alert(allBrandName);
				    		
				    		document.wishlistForm.submit();
				    	}
			    	}else if(dataArr[0] == 'success'){
			    		var newArr = drop.split("-");
			    		var brandName = $("#brandName"+productId).val();
			    		$('.add_this_content').fadeOut(-1);
			    		$("#productForSuccessMSG").html(quantity + " item of " +productName.replace(",," , "&")+"("+brandName+") with " + newArr[0] + " added to Wishlist.");
	                    $('.add_this_content').fadeIn('slow').delay(1500).fadeOut('slow'); 
				    	
			    	}
			    	else{
			    		alert("Error while adding item to cart.");
			    	}
			    	getCookies();
			    },
			    "html"
			);
		
	}


function changeSaveValue(productId){
	var val = $('option:selected', "#dropDown"+productId).val().split("-");
	var price = val[1].trim().split(" ")[0].trim();
	var option = $('option:selected', "#dropDown"+productId).attr('offerprice');
	var saveValue = parseFloat(option) - parseFloat(price);
	$("#saveValue"+productId).val(saveValue);
	$("#offerPrice"+productId).val(price);
	$("#offerPriceShow"+productId).html(price);
	$("#actualPriceShow"+productId).html(option);
	$("#saveValueShow"+productId).html(saveValue);
//	alert($("#saveValueShow"+productId).html());
	//alert($("#saveValueShow"+productId).html());
}


function searchProduct(){
	var value = $("#searchProductAutocomplete").val();
	if(value == ''){
		$('#search_header_error').css('display','block');
		return false;
	}
	document.productSearchHeader.submit();
}

