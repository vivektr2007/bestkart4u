
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<html>
<head>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<%-- <script src="js/jquery.min.js" type="text/javascript"></script>
 --%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Product Form</title>

</head>
<body>

	<jsp:include page="adminLinks.jsp"></jsp:include>
	Upload images Zip File Form
	<br />


	<form action="ZipUploadTest.action" method="post"
		enctype="multipart/form-data">

		<table align="center" border="1">
			<%
				List<String> msgList = (List<String>) request
						.getAttribute("tempString");
				if (msgList != null) {
					if(msgList.size() > 0){
					for (String msg : msgList) {
			%>
			<tr>
				<td><font color="red"><%=msg%></font>
				</td>
			</tr>
			<%
				}
					}
					else{
						%>
						<tr>
							<td><font color="green">There are no unused image to delete</font>
							</td>
						</tr>
						<%	
					}
				}
			%>
		</table>
	</form>


</body>
</html>