<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script> --%>

<script src="js/jquery.min.js" type="text/javascript"></script>

<%-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script> --%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript">
	function deleteSubCategoryFunc(){
		if(confirm("Are you sure you want to delete products?")){
			document.deleteCategory.submit();
		} 
	}
	
	function getSubCategory(){
		var val = $('#categoryId').val() ;
		$.get(
			    "GetAllSSubCategory.action",
			    {categoryId:val},
			    function(data) { 
			    	var myData = data.split("#");
			    	if(myData != '' && myData.length != 'undefined' && myData.length > 0 ){
			    		$("#subCategoryId").html("<option value='-1'>--select--</option>");
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
			    		$("#childCategoryId").html("<option value='-1'>--select--</option>");
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
	
	function getProductList(){
		var val = $('#categoryId').val() ;
		var val1 = $('#subCategoryId').val() ;
		var val2 = $('#childCategoryId').val() ;
		
		$.get(
			    "GetProductAjax.action",
			    {categoryId:val,subCategoryId:val1, childCategoryId:val2},
			    function(data) {
			    	var header = "<tr><th>Product Name</th>	<th>Product Brand Name</th><th>Product Image</th><th>Product Size</th><th>Select</th></tr>";
			    	if(data.trim() != "null" && data.trim() != null && data.trim() != 'null'){
				    	var arr = data.split("$");
				    	var str = "";
				    	if(typeof arr !== undefined &&arr.length!=0){
					    	for(var i = 0; i < arr.length; i++){
					    		var arr1 = arr[i].split("#");
					    		str = str + "<tr><td>"+arr1[0]+"</td><td>"+arr1[1]+"</td><td><img src='"+arr1[3]+"' width='80' height='80'/></td><td>"+arr1[2]+"</td><td><input value='"+arr1[4]+"' type='checkbox' name='productId'/></td></tr>";
					    	}
				    	}
				    	else{
				    		str = "<tr><td colspan='5'>No Data Found</td></tr>";
				    	}
				    	$("#productInfo").html("");
				    	$("#productInfo").html(header+str);
			    	}
			    	else{
			    		var srt = "<tr><td colspan='5'>No Data Found</td></tr>";
			    		$("#productInfo").html("");
				    	$("#productInfo").html(header+srt);
			    	}
			    },
			    "html"
			);
		
	}
	
	
</script>

</head>
<body>

<jsp:include page="adminLinks.jsp"></jsp:include>
Delete Product Form<br/>
<form action="DeleteProductAction.action" name="deleteCategory" method="post">
	<table border="1" align="center">
			<tr>
				<td colspan="2"><font color="red"><s:property value="msg"/></font></td>
			</tr>
			<tr>
				<td>Category : </td>
				<td><s:select name="categoryId" key="categoryId" list="allCategory" headerKey="-1" headerValue="--Select--" onchange="getSubCategory()"/></td>
			</tr>	
			<tr>
				<td>Sub Category : </td>
				<td><select name="subCategoryId" id="subCategoryId" onchange="getChildCategory()">
						<option>--Select--</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>Child Category : </td>
				<td><select name="childCategoryId" id="childCategoryId" onchange="getProductList()">
						<option>--Select--</option>
					</select>
				</td>
			</tr>
			
			<tr>
				<td colspan="2"><input type="button" value="Delete" onclick="deleteSubCategoryFunc()"/></td>
			</tr>
		</table>
		
		<br/>
		
		<table id="productInfo" align="center" border="1">
			<tr>
				<td>Product Name</td>
				<td>Product Brand Name</td>
				<td>Product Image</td>
				<td>Product Size</td>
			</tr>
			
			
		</table>
</form>

</body>
</html>