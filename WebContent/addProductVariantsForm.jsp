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
<title>Add Product Variants Forme</title>

<script src="./js/validation.js" type="text/javascript"></script>

<script type="text/javascript">

	function addRow(){
		
		var tbl = document.getElementById('productTable');
		var lastRow = (tbl.rows.length) - 1;
		
		var row = tbl.insertRow(lastRow);
		var cell0 = row.insertCell(0);
		cell0.innerHTML= '<input type="text" name="productPrices" id="productPrices" intvalidate="intvalidate" required="required"/>';
		var cell1 = row.insertCell(1);
		cell1.innerHTML= '<input type="text" name="offerPrices" id="offerPrices" intvalidate="intvalidate" required="required"/>';
		var cell2 = row.insertCell(2);
		cell2.innerHTML = '<input type="text" name="productSizes" id="productSizes" required="required"/>';		
	}

	function deleteThisRow(obj){
		var i = obj.parentNode.parentNode.rowIndex;
		document.getElementById("productTable").deleteRow(i);
	}
	
	function submitAddProductVariants(){
		
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
			document.addProductVariantsForm.submit();
		}
	}
	
</script>

</head>
<body>

<jsp:include page="adminLinks.jsp"></jsp:include>
Add Product Variants Form<br/>

<form action="AddProductVariantsAction.action" method="post" name="addProductVariantsForm">
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
		<td>Products </td>
		<td colspan="2"><s:select name="productId" key="productId" list="products" headerKey="" headerValue="--Select--" onchange="getSubCategory()" required="required"/></td>
	</tr>
	<tr>
		<td colspan="2"><br/></td>
	</tr>
	<tr>
		<th>Product Price</th><th>Offer Price</th><th>Product Size</th>
	</tr>
	<tr>
		<td><input type="text" name="productPrices" id="productPrices" intvalidate="intvalidate" required="required"/></td>
		<td><input type="text" name="offerPrices" id="offerPrices" intvalidate="intvalidate" required="required"/></td>
		<td><input type="text" name="productSizes" id="productSizes" required="required"/></td>
	</tr>
	<tr>
		<td colspan="11"><input type="button" onclick="submitAddProductVariants()" value="Add Variants"/></td>
	</tr>

</table>
</form>

</body>
</html>