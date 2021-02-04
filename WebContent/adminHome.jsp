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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Home</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script type="text/javascript">
	function addCartId(id){
		//alert(id);
		//alert($(id).attr("cartId"));
		var selectedCartId = $(id).attr("cartId");
		$("#allCartId").val($("#allCartId").val()+":"+selectedCartId);
		//alert($("#allCartId").val());
	}
	function submitForm(){
		
		document.AcceptRejectPendingOrderForm.submit();
	}
	
</script>
</head>
<body>

<jsp:include page="adminLinks.jsp"></jsp:include>

Admin Home Page<br/>

<form action="AcceptRejectPendingOrder.action" name="AcceptRejectPendingOrderForm">
<div style="overflow:scroll;height:500px">
<table border="1" align="center">
		
		<%
			Map<String, ArrayList> orderDetail = (Map<String, ArrayList>)request.getAttribute("orderDetail");
			if(orderDetail == null || orderDetail.size() == 0){
		%>
		<tr>
			<td>Product Brand Name</td>
			<td>Product Name</td>
			<td>Product Price</td>
			<td>Product Quantity</td>
			<td>Product Total Price</td>
			<td>Product Size</td>
		</tr>
		<tr>
			<td colspan="6"><font color="red">No pending order</font></td>
		</tr>
		<%
			}
			else{
		%>
		<tr>
			<td><input type="hidden" name="allCartId" id="allCartId" value=""/>
<input type="button" value="Submit" onclick="submitForm()"/></td>
			<td colspan="5"><font color="red"><s:property value="msg"/></font></td>
		</tr>
		
		<%
			Set values = orderDetail.entrySet();
			Iterator itr = values.iterator();
			while(itr.hasNext()){
				Map.Entry entry = (Map.Entry)itr.next();
				
				String key[] = ((String)entry.getKey()).split("#");
				ArrayList val = (ArrayList)entry.getValue();
			%>
				<tr>
					<th><select cartId="<%=key[0]%>" name="action<%=key[0]%>" id="action" onchange="addCartId(this)">
						<option value="">--select--</option>
						<option value="C">Confirm</option>
						<option value="R">Reject</option>
					</select></th>
					
					<td><span style="word-wrap:break-word;"><b>Date : <%=key[3] %> ,Selected Slot : </b><%=key[2] %></span></td>
					<td><b>Order Id : </b><%=key[0]%></td>
					<td colspan="3"><span style="word-wrap:break-word;"><b>Address : </b><%=key[1]%></span></td>
				</tr>
				<tr>
					<td>Product Brand Name</td>
					<td>Product Name</td>
					<td>Product Price</td>
					<td>Product Quantity</td>
					<td>Product Total Price</td>
					<td>Product Size</td>
				</tr>
				
				<%
				Iterator itr1 = val.iterator();
				double price = 0.0;
				
				while(itr1.hasNext()){	
					String arr[] = (String[])itr1.next();
					price = price + Double.parseDouble(arr[9]);
				%>
					<tr>
						<td><%=arr[6] %></td>
						<td><%=arr[5] %></td>
						<td><%=arr[7] %></td>
						<td><%=arr[8] %></td>
						<td><%=arr[9] %></td>
						<td><%=arr[10] %></td>	
					</tr>
				<%
							}
				%>		
				<tr>
					<td colspan="6" align="right">Total Price : <%=price %></td>
				</tr>	
		<%		
			}
			}
		%>
</table>
</div>
</form>
</body>
</html>