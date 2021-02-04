<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="js/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript">
function addRow(){
		
		var tbl = document.getElementById('childCategoryTable');
		var lastRow = (tbl.rows.length) - 1;
		
		var row = tbl.insertRow(lastRow);
		var cell0 = row.insertCell(0);
		cell0.innerHTML = "Child Category Name";
		var cell1 = row.insertCell(1);
		cell1.innerHTML = "<input type='text' required='required' name='childCategoryName'/>";
	
		var cell2 = row.insertCell(2);
		cell2.innerHTML = '<input type="button" onclick="deleteThisRow(this)" value="delete"/>';

}
function deleteThisRow(obj){
	var i = obj.parentNode.parentNode.rowIndex;
	document.getElementById("childCategoryTable").deleteRow(i);
}

	
	</script>

</head>
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
	
	function addChildCategory(){
		
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
		document.ChildCategoryForm.submit();
		
	}
	
</script>
<script src="./js/validation.js" type="text/javascript"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Child Category</title>
</head>
<body>

<jsp:include page="adminLinks.jsp"></jsp:include>
Add Child Category Form<br/>
<form action="AddChildcategoryForm.action" name="ChildCategoryForm" method="post">
<table border="1" align="center" id="childCategoryTable">
	<tr>
		<td>
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
			%></td>
		<td><input type="button" onclick="addRow()" value="Add Row"></td>
	</tr>
	<tr>
		<td>Category Name</td>
		<td><s:select list="categoryDetail" name="categoryId" key="categoryId" headerKey="" required="required" headerValue="--select--" onchange="getSubCategory()" /></td>
	</tr>
	<tr>
		<td>Sub Category Name</td>
		<td id="SubCategoryTd">
			<select name="subCategoryId" id="subCategoryId" required="required">
				<option value="">--Select--</option>
			</select>
		</td>
	</tr>
	
	<tr>
		<td>Child Category Name</td>
		<td><input type="text" required="required" name="childCategoryName"/></td>
	</tr>
	
	<tr>
		<td colspan="2">
			<input type="button" onclick="addChildCategory()" value="Submit"/>
		</td>	
	</tr>

</table>
</form>
</body>
</html>