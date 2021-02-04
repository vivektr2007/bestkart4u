<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="/struts-tags" prefix="s" %>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<html>
<head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<%-- <script src="js/jquery.min.js" type="text/javascript"></script>
 --%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Product Form</title>

<script src="./js/validation.js" type="text/javascript"></script>

<script type="text/javascript">

function getSubCategory(id, id1){
	
	var val = $('#'+id1).val() ;
	$.get(
		    "GetAllSSubCategory.action",
		    {categoryId:val},
		    function(data) { 
		    	var myData = data.split("#");
		    	if(myData != '' && myData.length != 'undefined' && myData.length > 0 ){
		    		$("#"+id).html("<option value=''>--select--</option>");
			    	for(var i = 0; i< myData.length; i++){
			    		var keyVal = myData[i].split("-");
			    		if(typeof keyVal[1] !== "undefined"){
			    			var option ="<option value='"+keyVal[0]+"'>"+keyVal[1]+"</option>";
			    			$("#"+id).html($("#"+id).html() +option);
			    		}
			    	}
		    	}
		    },
		    "html"
		);	
}

function getChildCategory(childCategory, subCategory, category){
	var val = $('#'+category).val() ;
	var val1 = $('#'+subCategory).val() ;
	
	
	$.get(
		    "GetAllChildCategoryAjax.action",
		    {categoryId:val,subCategoryId:val1},
		    function(data) { 
		    	var myData = data.split("#");
		    	if(myData != '' && myData.length != 'undefined' && myData.length > 0 ){
		    		$("#"+childCategory).html("<option value=''>--select--</option>");
			    	for(var i = 0; i< myData.length; i++){
			    		var keyVal = myData[i].split("-");
			    		if(typeof keyVal[1] !== "undefined"){
			    			var option ="<option value='"+keyVal[0]+"'>"+keyVal[1]+"</option>";
			    			$("#"+childCategory).html($("#"+childCategory).html() +option);
			    		}
			    	}
		    	}
		    },
		    "html"
		);	
}

	function submitAddProduct(){
		document.addProductForm.action = "UpdateMassProduct.action";
		document.addProductForm.submit();
		
	}
	function downloadProductForUpdate(){
		document.addProductForm.action = "DownloadProductForUpdate.action";
		document.addProductForm.submit();
		
	}
</script>

</head>
<body>

<jsp:include page="adminLinks.jsp"></jsp:include>

Update Mass Product Page<br/>
<form action="#" method="post" name="addProductForm" enctype="multipart/form-data">
<table border="1" align="center" id="productTable">	
	
	<tr>
		<td>Root Category</td>
		<td><s:select name="categoryId" key="categoryId" list="categoryDetail" headerKey="" headerValue="--Select--" onchange="getSubCategory('subCategoryId','categoryId')" required="required"/></td>
	</tr>
	<tr>
		<td>Sub Root Category</td>
		<td><select name="subCategoryId" id="subCategoryId" onchange="getChildCategory('childCategoryId','subCategoryId','categoryId')">
				<option value="">--Select--</option>
		</select></td>
	</tr>
	<tr>
		<td>Child Category</td>
		<td><select name="childCategoryName" id="childCategoryId">
			<option value="">--Select--</option>
		</select></td>
	</tr>
	
	<tr>
		<td>Download Product For Update : </td><td>
		<a href="javascript:void();" onclick="downloadProductForUpdate();">click here</a>
		</td>
	</tr>
	<tr>
		<td>Upload File for Update : </td><td>
		<s:file name="attachmentFile"></s:file>
		</td>
	</tr>
	<tr>
		<td colspan="2"><input type="button" onclick="submitAddProduct()" value="Add Product"/></td>
	</tr>

</table>
</form>

</body>
</html>