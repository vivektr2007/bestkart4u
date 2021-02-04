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
	function deleteSubCategoryFunc(){
		if(confirm("Deleting Sub category will delete all Its child category and related products. Continue?")){
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
				    			//document.getElementById("subCategoryId").innerHTML = document.getElementById("subCategoryId").innerHTML +option;
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
				    			//document.getElementById("subCategoryId").innerHTML = document.getElementById("subCategoryId").innerHTML +option;
				    			$("#childCategoryId").html($("#childCategoryId").html() +option);
				    		}
				    	}
			    	}
			    },
			    "html"
			);	
	}
	
	
</script>

</head>
<body>

<jsp:include page="adminLinks.jsp"></jsp:include>
Delete Child Category Form<br/>
<form action="DeleteChildCategoryAction.action" name="deleteCategory" method="post">
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
				<td><select name="childCategoryId" id="childCategoryId">
						<option>--Select--</option>
					</select>
				</td>
			</tr>
			
			<tr>
				<td colspan="2"><input type="button" value="Delete" onclick="deleteSubCategoryFunc()"/></td>
			</tr>
		</table>
</form>

</body>
</html>