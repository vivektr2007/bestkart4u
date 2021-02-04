<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Sub-Category</title>
<script src="js/jquery.min.js" type="text/javascript"></script>
<script src="./js/validation.js" type="text/javascript"></script>
<script type="text/javascript">
function addRow(){
		
		var tbl = document.getElementById('subCategoryTable');
		var lastRow = (tbl.rows.length) - 1;
		
		var row = tbl.insertRow(lastRow);
		var cell0 = row.insertCell(0);
		cell0.innerHTML = "Sub Category Name";
		var cell1 = row.insertCell(1);
		cell1.innerHTML = "<input type='text' required='required' name='subCategoryName'/>";
	
		var cell2 = row.insertCell(2);
		cell2.innerHTML = '<input type="button" onclick="deleteThisRow(this)" value="delete"/>';

}
function deleteThisRow(obj){
	var i = obj.parentNode.parentNode.rowIndex;
	document.getElementById("subCategoryTable").deleteRow(i);
}
function addSubCategory(){
	
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
	document.SubCategoryForm.submit();
	
}
	</script>

</head>
<body>

<jsp:include page="adminLinks.jsp"></jsp:include>

Add Sub Root Category Form<br/>

<form action="AddSubcategoryForm.action" method="post" name="SubCategoryForm">
<table border="1" align="center" id="subCategoryTable">
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
			%>
		</td>
		<td><input type="button" onclick="addRow()" value="Add Row"></td>
	</tr>
	<tr>
		<td>Category Name</td>
		<td><s:select list="categoryDetail" required="required" name="categoryId" headerKey="" headerValue="--select category--" /></td>
	</tr>
	<tr>
		<td>Sub Category Name</td>
		<td><input type="text" required="required" name="subCategoryName"/></td>
	</tr>
	
	<tr>
		<td colspan="2">
			<input type="button" onclick="addSubCategory()" value="Submit"/>
		</td>	
	</tr>

</table>
</form>
</body>
</html>