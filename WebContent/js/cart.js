function addToCart(productId){
		var subRootCategory = $("#subRootCategory").val();
		var rootCategory = $("#rootCategory").val();
		var drop = document.getElementById('dropDown'+productId).value;
		var price = document.getElementById('offerPrice'+productId).value;
		var quantity = document.getElementById('quantity'+productId).value;
		if(quantity == '' || quantity == null){
			 $('#quantity_success').css('display','block');
			document.getElementById('quantity'+productId).focus();
			return false;
		}
		var brandName = document.getElementById('brandName'+productId).value;
		var productName = document.getElementById('productName'+productId).value;
		var imagePath = document.getElementById('imagePath'+productId).value;
		var saveValue =  document.getElementById('saveValue'+productId).value;
		$.get(
			    "AddToCart.action",
			    {productId:productId,dropDown : drop,price : price,quantity:quantity,brandName:brandName,
			    	productName:productName,imagePath:imagePath,saveValue:saveValue,subRootCategory:subRootCategory,rootCategory:rootCategory  },
			    function(data) {
			    	var arr = drop.split("-");
			    	$('.add_this_content').fadeOut(-1);
			    	$("#productForSuccessMSG").html(quantity + " item of " +productName+"("+brandName+")" + " with " + arr[0] + " added to cart");
                    $('.add_this_content').fadeIn('slow').delay(1500).fadeOut('slow'); 
			    	getCookies();
			    },
			    "html"
			);		
	}
	function deleteFromCart(productId){
		$.get(
			    "DeleteFromCart.action",
			    {productId:productId},
			    function(data) { 
			    	getCookies();
			    },
			    "html"
			);		
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
									'<input type="button" value="-" class="qtyminus" id="decreaseQuantity" onclick="decreaseQuan('+allVal1[0]+')" field="quantity1" />'+
									'<input type="text" name="quantity1" id="quantity1'+allVal1[0].trim()+'" value="'+allVal[3]+'" class="qty" />'+
									'<input type="button" value="+" id="increaseQuantity" onclick="increaseQuan('+allVal1[0]+')" class="qtyplus" field="quantity1" />'+
								'</div><span class="uiv2-items-rate"><span class="WebRupee">Rs.</span> '+allVal[2]+'</span>'+
								'<div class="uiv2-items-saved-rate"><span>SAVED</span><span><span class="WebRupee">Rs.</span> '+allVal[6]+'</span></div>'+
							'</div></div><a href="javascript:void();" class="uiv2-remove-product" id="cart1" onClick="deleteFromCart('+allVal1[0]+');">&nbsp;</a></li>';
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
	
	function increaseQuan(productId){
		var val = document.getElementById("quantity1"+productId+"").value;
		$.get(
			    "IncreaseQuantity.action",
			    {productId:productId,quantity:val},
			    function(data) { 
			    	getCookies();
			    },
			    "html"
			);
	}
	function decreaseQuan(productId){
		var val = document.getElementById("quantity1"+productId+"").value;
		$.get(
			    "DecreaseQuantity.action",
			    {productId:productId,quantity:val},
			    function(data) { 
			    	getCookies();
			    },
			    "html"
			);
	}
	function quantityAdd(productId){
		var arr = $("#quantityShow"+productId).val().trim().split(" ");
		var quan = arr[0];
		if(arr.length < 3){
			if(arr.length == 1){
				$("#quantity"+productId).val(quan);
			}
			else{
				 $('#quantity_success').css('display','block');
				return false;
			}
		}
		$("#quantity"+productId).val(quan);
	}

	