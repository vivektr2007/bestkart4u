<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="js/jquery.min.js" type="text/javascript"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript">

	function getSubCategory(){
		var val = $('#categoryId').val() ;
		$.get(
			    "GetAllSSubCategory.action",
			    {categoryId:val},
			    function(data) { 
			    	var myData = data.split("#");
			    	if(myData != '' && myData.length != 'undefined' && myData.length > 0 ){
			    		$("#subCategoryId").html("<option value=''>--select--</option>");
				    	for(var i = 0; i< myData.length; i++){
				    		var keyVal = myData[i].split("-");
				    		if(typeof keyVal[1] !== "undefined"){
				    			var option ="<option value='"+keyVal[0]+"'>"+keyVal[1]+"</option>";
				    			$("#subCategoryId").html($("#subCategoryId").html() +option);
				    		}
				    	}
			    	}
			    },
			    "html"
			);	
	}
	
	function getChildCategory(){
		var val = $('#categoryId').val() ;
		var val1 = $('#subCategoryId').val() ;
		
		
		$.get(
			    "GetAllChildCategoryAjax.action",
			    {categoryId:val,subCategoryId:val1},
			    function(data) { 
			    	var myData = data.split("#");
			    	if(myData != '' && myData.length != 'undefined' && myData.length > 0 ){
			    		$("#childCategoryId").html("<option value=''>--select--</option>");
				    	for(var i = 0; i< myData.length; i++){
				    		var keyVal = myData[i].split("-");
				    		if(typeof keyVal[1] !== "undefined"){
				    			var option ="<option value='"+keyVal[0]+"'>"+keyVal[1]+"</option>";
				    			$("#childCategoryId").html($("#childCategoryId").html() +option);
				    		}
				    	}
			    	}
			    },
			    "html"
			);	
	}
	
	
function submitEditProduct(){
		var failFlag = 0;
		$('[required]').each(function() {

			var pass = validateRequired(this);

			if (!pass) {
				failFlag++;
			}
			
 		});
		
		if(parseInt(failFlag) > 0){
			return;
		}
		else{
			failFlag = 0;
		}
		$('[intvalidate]').each(function() {
			var passNum = validateNumber(this);
			if (!passNum) {
				failFlag++;
			}
 		});
		if(parseInt(failFlag) > 0){
			return;
		}
		else{
			failFlag = 0;
		}
		if(parseInt(failFlag) == 0){
			document.EditProductForm.submit();
		}
	}
	
</script>
<script src="./js/validation.js" type="text/javascript"></script>
</head>
<body>

<jsp:include page="adminLinks.jsp"></jsp:include>
Edit Product Form<br/>

<form action="EditProduct.action" method="post" enctype="multipart/form-data" name="EditProductForm">
<table border="1" align="center">
	<tr>
		<td colspan="11"><font color="red"><s:property value="successMsg"/></font></td>
	</tr>
	<tr>
		<td>Root Category</td><td>Sub-root Category</td><td>Child Category</td><td>Product Name</td>
		<td>Product Brand Name</td><td>Actual Price</td><td>Offer Price</td><td>Product Size</td><td>About Product</td>
		<td>Ingredient</td><td>Image</td>
	</tr>
	<%
		Map<String, String> productInfo = (Map<String, String>)request.getAttribute("productInfo");
		String categoryId=(String)productInfo.get("ROOTCATEGORYID");
	%>
	
	<tr>
		<td>
		<s:select name="categoryId"  key="categoryId" value='%{#request.categoryId}' list="allCategory" required="required" headerKey="" headerValue="--Select--" onchange="getSubCategory()"/></td>
		<td><select name="subCategoryId" id="subCategoryId" onchange="getChildCategory()" required="required">
				<option value="">--Select--</option>
				<option selected="selected" value="<%=productInfo.get("SUBROOTCATEGORYID")%>"><%=productInfo.get("SUBCATEGORYNAME")%></option>
			</select></td>
		<td><select name="childCategoryName" id="childCategoryId" required="required">
				<option value="">--Select--</option>
				<option selected="selected" value="<%=productInfo.get("CHILDCATEGORYID")%>"><%=productInfo.get("CHILDCATEGORYNAME")%></option>
			</select></td>
		<td><input type="text" required="required" name="productName" value='<%=productInfo.get("PRODUCTNAME")%>'/></td>
		<td><input type="text" required="required" name="productBrandName"  value='<%=productInfo.get("PRODUCTBRANDNAME")%>'/></td>
		<td><input type="text" required="required" intvalidate="intvalidate" name="actualPrice" value='<%=productInfo.get("ACTUALPRICE")%>' /></td>
		<td><input type="text" required="required" intvalidate="intvalidate" name="offerPrice" value='<%=productInfo.get("OFFERPRICE")%>'/></td>
		<td><input type="text" required="required" name="productSize" value='<%=productInfo.get("PRODUCTSIZE")%>'/></td>
		<td><input type="text" required="required" name="aboutProduct" value='<%=productInfo.get("ABOUTPRODUCT")%>'/></td>
		<td><input type="text" required="required" name="ingredient" value='<%=productInfo.get("INGREDIENT")%>'/></td>
		<td><s:file name="productImage" id="productImage"/></td>
		<input type="hidden" name="productId" value='<%=productInfo.get("PRODUCTID")%>'/>
		<input type="hidden" name="imagePathHidden" value="<%=productInfo.get("IMAGEPATH")%>"/>
	</tr>
	<tr>
		<td colspan="11"><input type="button" value="Edit Product" onclick="submitEditProduct()"/></td>
	</tr>

</table>
</form>

</body>
</html>