<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="./js/validation.js" type="text/javascript"></script>
<script src="js/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript">
	function addRow(){
		
		var tbl = document.getElementById('categoryTable');
		var lastRow = (tbl.rows.length) - 1;
		
		var row = tbl.insertRow(lastRow);
		var cell0 = row.insertCell(0);
		cell0.innerHTML = "Category Name";
		var cell1 = row.insertCell(1);
		cell1.innerHTML = "<input type='text' required='required' name='categoryName'/>";
		
		var cell2 = row.insertCell(2);
		cell2.innerHTML = '<input type="button" onclick="deleteThisRow(this)" value="delete"/>';
		
	}
	function deleteThisRow(obj){
		var i = obj.parentNode.parentNode.rowIndex;
		document.getElementById("categoryTable").deleteRow(i);
	}
	
	function addMainCategory(){
		
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
		document.MainCategoryForm.submit();
	}
	
	</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<jsp:include page="adminLinks.jsp"></jsp:include>
Add Root Category Form<br/>
<form action="AddMainCategory.action" method="post" name="MainCategoryForm">
<table border="1" align="center" id="categoryTable">
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
		<td><input type="text" required="required" name="categoryName"/></td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="button" value="Submit" onclick="addMainCategory()"/>
		</td>	
	</tr>

</table>
</form>
</body>
</html>