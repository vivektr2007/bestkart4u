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

	function submitAddProduct(){
		
		document.addProductForm.submit();
		
	}
	
</script>

</head>
<body>

<jsp:include page="adminLinks.jsp"></jsp:include>

Download All Product Form<br/>

<form action="UploadProduct.action" method="post" name="addProductForm" enctype="multipart/form-data">
<table border="1" align="center" id="productTable">	
	
	<tr>
		<td>Download all Products : </td><td>
		<a href="DownloadAllProductsAction.action">click here</a>
		</td>
	</tr>
	<%-- <tr>
		<td>Upload File : </td><td>
		<s:file name="attachmentFile"></s:file>
		</td>
	</tr>
	<tr>
		<td colspan="2"><input type="button" onclick="submitAddProduct()" value="Add Product"/></td>
	</tr>
 --%>
</table>
</form>

</body>
</html>