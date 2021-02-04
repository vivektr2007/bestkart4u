<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="./js/validation.js" type="text/javascript"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Home</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script type="text/javascript">
	var finalAmount = 0;
	function generateReceipt(){
		
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
			document.GenerateCustomReceiptForm.submit();
		}
	}
function calculateAmountToPay(){
	var newAmount = 0.0;
	$('[total]').each(function() {
		var totalvalue = $(this).val();
		if (totalvalue != '') {
			newAmount = parseFloat(newAmount) + parseFloat(totalvalue);
		}
	});
	
	$("#finalAmount").val(newAmount);
	$("#amountToPay").val(newAmount);
	var discount = $("#discount").val().trim();
	var deliveryCharge = $("#deliveryCharge").val().trim();
	if(discount != '' ){
		if(parseFloat(discount)<parseFloat(newAmount)){
			newAmount = newAmount - discount;
			$("#amountToPay").val(newAmount);
		} 
		else{
			alert("FinalAmount must be greater than Discount.");
			$("#discount").val("");
			return false;
		}
	}
	if(deliveryCharge != ''){
		/* if(parseFloat(deliveryCharge)<parseFloat(newAmount)){
		 */	
		newAmount = parseFloat(newAmount) + parseFloat(deliveryCharge);
		$("#amountToPay").val(newAmount);
		/* }
		else{
			alert("FinalAmount must be greater than Delivery Charge.");
			$("#deliveryCharge").val("");
			return false;
		} */
	}
}	
	
function catculateTotalPrice(quanid, priceid, totalpriceId){
	var price = $("#"+priceid).val();
	var quan = $("#"+quanid).val();
	
	if(price != '' && quan != ''){
	
		var total = parseFloat(price) * parseInt(quan);
		$("#"+totalpriceId).val(total);
		calculateAmountToPay();
	}
	
}	
function addRow(){
		
		var tbl = document.getElementById('invoiceTable');
		var lastRow = (tbl.rows.length) - 1;
		
		var unitPrice = "unitPrice" + lastRow;
		var quantity = "quantity" + lastRow;
		var totalPrice = "totalprice" + lastRow;
		
		
		var row = tbl.insertRow(lastRow);
		var cell0 = row.insertCell(0);
		cell0.innerHTML = '<input type="text" required="required" name="prodBrandName"/>';
		var cell1 = row.insertCell(1);
		cell1.innerHTML = '<input type="text" required="required" name="prodName"/>';
		var cell2 = row.insertCell(2);
		cell2.innerHTML = '<input type="text" required="required" name="mrp"/>';
		var cell3 = row.insertCell(3);
		cell3.innerHTML = '<input type="text" intvalidate="intvalidate" id="'+unitPrice+'" onblur="catculateTotalPrice(\''+quantity+'\',\''+unitPrice+'\',\''+totalPrice+'\')" required="required" name="unitPrice"/>';
		var cell4 = row.insertCell(4);
		cell4.innerHTML = '<input type="text" intvalidate="intvalidate" onblur="catculateTotalPrice(\''+quantity+'\',\''+unitPrice+'\',\''+totalPrice+'\')" id="'+quantity+'" required="required" name="quantity"/>';
		var cell5 = row.insertCell(5);
		cell5.innerHTML = '<input type="text" readonly="readonly" total="total" required="required" id="'+totalPrice+'" name="totalPrice"/>';
		var cell6 = row.insertCell(6);
		cell6.innerHTML = '<input type="text" required="required" name="size"/>';
		var cell7 = row.insertCell(7);
		cell7.innerHTML = '<input type="button" onclick="deleteThisRow(this)" value="delete"/>';
	}

	function deleteThisRow(obj){
		var i = obj.parentNode.parentNode.rowIndex;
		document.getElementById("invoiceTable").deleteRow(i);
	}
	
	
</script>
</head>
<body>

<jsp:include page="adminLinks.jsp"></jsp:include>
Generate Custom Receipt Form<br/>
<form action="GenerateCustomReceiptAction.action" name="GenerateCustomReceiptForm" method="post">
<table border="1" align="center" id="invoiceTable">
	<tr>
		<td colspan="2"><input type="button" value="Add Row" onclick="addRow()"/></td>
	</tr>	
	<tr>
		<td>Name : </td>
		<td><input required="required" type="text" name="name"/></td>
	</tr>
	<tr>
		<td>Address</td>
		<td><textarea name="address" rows="3" cols="10"></textarea></td>
	</tr>
	<tr>
		<td>Final Amount : </td>
		<td><input type="text" readonly="readonly" id="finalAmount" name="finalAmount"/></td>
	</tr>
	<tr>
		<td>Discount : </td>
		<td><input type="text" name="discount" id="discount" onblur="calculateAmountToPay()"/></td>
	</tr>
		
	<tr>
		<td>Delivery Charge : </td>
		<td><input type="text" name="deliveryCharge" id="deliveryCharge" onblur="calculateAmountToPay()"/></td>
	</tr>
	<tr>
		<td>Amount to Pay : </td>
		<td><input readonly="readonly" type="text" name="amountToPay" id="amountToPay"/></td>
	</tr>
	<tr>
		<td>Order Date : </td>
		<td><input type="text" name="orderDate" id="orderDate"/></td>
	</tr>
	<tr>
		<td>Delivery Date : </td>
		<td><input type="text" name="deliveryDate" id="deliveryDate"/></td>
	</tr>	
	<tr>
		<td>Product Brand Name</td>
		<td>Product Name</td>
		<td>Mrp.</td>
		<td>Unit Price</td>
		<td>Quantity</td>
		<td>Product Total Price</td>
		<td>Product Size</td>
	</tr>
	<tr>
		<td><input type="text" required="required" name="prodBrandName"/></td>
		<td><input type="text" required="required" name="prodName"/></td>
		<td><input type="text" required="required" name="mrp"/></td>
		<td><input type="text" required="required" onblur="catculateTotalPrice('quantity','unitPrice','totalPrice')" id="unitPrice" intvalidate="intvalidate" name="unitPrice"/></td>
		<td><input type="text" required="required" onblur="catculateTotalPrice('quantity','unitPrice','totalPrice')" intvalidate="intvalidate" id="quantity" name="quantity"/></td>
		<td><input type="text" required="required" total="total" name="totalPrice" readonly="readonly" id="totalPrice"/></td>
		<td><input type="text" required="required" name="size"/></td>
	</tr>	
	<tr>
		<td colspan="6"><input type="button" onclick="generateReceipt()" value="submit"/></td>
	</tr>
</table>
</form>
</body>
</html>