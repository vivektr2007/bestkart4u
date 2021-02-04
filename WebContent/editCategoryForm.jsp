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
	function editCategoryFunc(){
		var txt = $("#categoryId option:selected").text();
		var txt1 = $("#categoryText").val();
		if(txt != '--Select--' && txt1 != ''){
			if(confirm("Are you sure you want to edit?")){
				document.editCategory.submit();
			} 
		}
		else{
			alert("Please enter valid values.");
			return false;
		}
	}
	
	function fillTextField(){
	
		var txt = $("#categoryId option:selected").text();
		if(txt == '--Select--'){
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
Edit Root Category Form<br/>
<form action="EditCategory.action" name="editCategory" method="post">

	<table border="1" align="center">
		<tr>
			<td colspan="2"><font color="red"><s:property value="msg"/></font></td>
		</tr>
		<tr>
			<td>Category : </td>
			<td><s:select name="categoryId" key="categoryId" list="allCategory" headerKey="-1" headerValue="--Select--" onchange="fillTextField()" /></td>
		</tr>
		<tr>
			<td>Edit Text : </td>
			<td><input type="text" id="categoryText" name="categoryText"/></td>
		</tr>
		<tr>
			<td colspan="2">	
			<input type="button" value="Edit" onclick="editCategoryFunc()"/>
		</td></tr>
	</table>
</form>

</body>
</html>