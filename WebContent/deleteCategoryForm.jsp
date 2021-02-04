<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script> --%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript">
	function deleteCategoryFunc(){
		if(confirm("Deleting category will delete all Its subCategory, child category and related products. Continue?")){
			document.deleteCategory.submit();
		} 
	}
</script>

</head>
<body>

<jsp:include page="adminLinks.jsp"></jsp:include>
Delete Root Category Form<br/>
<form action="DeleteCategoryAction.action" name="deleteCategory" method="post">

	<table border="1" align="center">
		<tr>
			<td colspan="2"><font color="red"><s:property value="msg"/></font></td>
		</tr>
		<tr>
			<td>Category : </td>
			<td><s:select name="categoryId" key="categoryId" list="allCategory" headerKey="-1" headerValue="--Select--" /></td>
		</tr>
		<tr><td colspan="2">	
			<input type="button" value="Delete" onclick="deleteCategoryFunc()"/>
		</td></tr>
	</table>
</form>

</body>
</html>