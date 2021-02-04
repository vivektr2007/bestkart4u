<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<%-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script> --%>
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
			    		$("#subCategoryId").html("<option value='-1'>--select--</option>");
				    	for(var i = 0; i< myData.length; i++){
				    		var keyVal = myData[i].split("-");
				    		if(typeof keyVal[1] !== "undefined"){
				    			var option ="<option value='"+keyVal[0]+"'>"+keyVal[1]+"</option>";
				    			//document.getElementById("subCategoryId").innerHTML = document.getElementById("subCategoryId").innerHTML +option;
				    			$("#subCategoryId").html($("#subCategoryId").html() +option);
				    		}
				    	}
			    	}
			    	$("#categoryText").val("");
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
	
	function editSubCategoryFunc(){
		var txt = $("#categoryId option:selected").text();
		var txt2 = $("#childCategoryId option:selected").val();
		var txt3 = $("#subCategoryId option:selected").val();
		var txt2 = $("#childCategoryId option:selected").val();
		
		
		
		var txt1 = $("#categoryText").val();
		
		if(txt != '--Select--' && txt1 != '' && txt2 != '--select--' && txt3 != '--select--'){
			if(confirm("Are you sure you want to edit?")){
				document.editSubCategory.submit();
			} 
		}
		else{
			alert("Please enter valid values.");
			return false;
		}
	}
	
	function fillTextField(){
	
		var txt = $("#childCategoryId option:selected").text();
		if(txt == '--select--'){
			$("#categoryText").val("");
			alert("Please select valid option.");
			return false;
		}
		else{
			$("#categoryText").val(txt);
		}
		
	}
	
</script>

</head>
<body>

<jsp:include page="adminLinks.jsp"></jsp:include>
Edit Child Category Form<br/>
<form action="EditChildCategory.action" name="editSubCategory" method="post">
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
				<td><select name="childCategoryId" id="childCategoryId" onchange="fillTextField()">
						<option>--Select--</option>
					</select>
				</td>
			</tr>
			<tr>
			<td>Edit Text : </td>
				<td><input type="text" id="categoryText" name="categoryText"/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="button" value="Edit" onclick="editSubCategoryFunc()"/></td>
			</tr>
		</table>
</form>

</body>
</html>