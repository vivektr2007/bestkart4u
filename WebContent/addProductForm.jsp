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

	function addRow(){
		
		var tbl = document.getElementById('productTable');
		var lastRow = (tbl.rows.length) - 1;
		
		var row = tbl.insertRow(lastRow);
		var cell0 = row.insertCell(0);
		var id = "categoryId" + lastRow;
		var subCategory = "subCategoryId" + lastRow;
		var childCategory = "childCategoryId" + lastRow;
		var option = '<select name="categoryId" id = "'+id+'" onchange="getSubCategory(\''+subCategory+'\',\''+id+'\')" required><option value="">--select--</option>';
		$.get(
			    "GetAllCategoryAjax.action",
			    {categoryId:''},
			    function(data) { 
			    	var myData = data.split("#");
		    		for(var i = 0; i< myData.length; i++){
			    		var keyVal = myData[i].split(":");
			    		if(typeof keyVal[1] !== "undefined"){
			    			option = option + "<option value='"+keyVal[0]+"'>"+keyVal[1]+"</option>";
			    		}
			    	}
		    		option = option+"</select>";
		    		cell0.innerHTML = option;
			    },
			    "html"
			);
		
		
		var cell1 = row.insertCell(1);
		
		var subCategorySelect = '<select name="subCategoryId" id="'+subCategory+'" onchange="getChildCategory(\''+childCategory+'\',\''+subCategory+'\',\''+id+'\')"><option value="">--select--</option></select>';
		var childSelect = '<select name="childCategoryName" id="'+childCategory+'"><option value="">--select--</option></select>';
		
		cell1.innerHTML = subCategorySelect;
		var cell2 = row.insertCell(2);
		cell2.innerHTML = childSelect;
		var cell3 = row.insertCell(3);
		cell3.innerHTML = '<input type="text" name="productName" required="required"/>';
		var cell4 = row.insertCell(4);
		cell4.innerHTML = '<input type="text" name="productBrandName" required="required"/>';
		var cell5 = row.insertCell(5);
		cell5.innerHTML = '<input type="text" name="actualPrice" intvalidate="intvalidate" required="required"/>';
		var cell6 = row.insertCell(6);
		cell6.innerHTML = '<input type="text" name="offerPrice" intvalidate="intvalidate" id="offerPrice" required="required"/>';
		var cell7 = row.insertCell(7);
		cell7.innerHTML = '<input type="text" name="productSize" required="required"/>';
		var cell8 = row.insertCell(8);
		cell8.innerHTML = '<input type="text" name="aboutProduct" required="required"/>';
		var cell9 = row.insertCell(9);
		cell9.innerHTML = '<input type="text" name="ingredient" required="required"/>';
		var cell10 = row.insertCell(10);
		cell10.innerHTML = '<s:file name="productImage" value=""/>';
		
		var cell10 = row.insertCell(11);
		cell10.innerHTML = '<input type="button" onclick="deleteThisRow(this)" value="delete"/>';
		
	}

	function deleteThisRow(obj){
		var i = obj.parentNode.parentNode.rowIndex;
		document.getElementById("productTable").deleteRow(i);
	}
	
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
			document.addProductForm.submit();
		}
	}
	
</script>

</head>
<body>

<jsp:include page="adminLinks.jsp"></jsp:include>
Add Product Form<br/>

<form action="AddProductAction.action" method="post" name="addProductForm" enctype="multipart/form-data">
<table border="1" align="center" id="productTable">
	<tr>
		<td colspan="6">
		<%
			ArrayList<String> messages = (ArrayList<String>)request.getAttribute("messages");
			if(messages != null && messages.size() > 0){
				Iterator itr = messages.iterator();
				while(itr.hasNext()){
		%>
			<%=itr.next() %><br/>
		<%
				}
			}
		%>	
		</td>
		<td colspan="5"><input type="button" onclick="addRow()" value="Add Row"></td>
	</tr>
	<tr>
		<td>Root Category</td><td>Sub-root Category</td><td>Child Category</td><td>Product Name</td>
		<td>Product Brand Name</td><td>Actual Price</td><td>Offer Price</td><td>Product Size</td><td>About Product</td>
		<td>Ingredient</td><td>Image</td>
	</tr>
	<tr>
		<td><s:select name="categoryId" key="categoryId" list="categoryDetail" headerKey="" headerValue="--Select--" onchange="getSubCategory('subCategoryId','categoryId')" required="required"/></td>
		<td><select name="subCategoryId" id="subCategoryId" onchange="getChildCategory('childCategoryId','subCategoryId','categoryId')">
				<option value="">--Select--</option>
			</select></td>
		<td><select name="childCategoryName" id="childCategoryId">
				<option value="">--Select--</option>
			</select></td>
		<td><input type="text" name="productName" required="required"/></td>
		<td><input type="text" name="productBrandName" required="required"/></td>
		<td><input type="text" name="actualPrice" id="actualPrice" intvalidate="intvalidate" required="required"/></td>
		<td><input type="text" name="offerPrice" id="offerPrice" intvalidate="intvalidate" required="required"/></td>
		<td><input type="text" name="productSize" required="required"/></td>
		<td><input type="text" name="aboutProduct" required="required"/></td>
		<td><input type="text" name="ingredient" required="required"/></td>
		<td><s:file name="productImage" required="required"/></td>
	</tr>
	<tr>
		<td colspan="11"><input type="button" onclick="submitAddProduct()" value="Add Product"/></td>
	</tr>

</table>
</form>

</body>
</html>