<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<jsp:include page="adminLinks.jsp"></jsp:include>
Create Survey Form <br/>
<form action="CreateSurveyAction.action" method="post">
<table border="1" align="center">
	<tr>
		<td colspan="2"><font color="red"><s:property value="msg"/></font></td>
	</tr>
	<tr>
		<td>Survey Name</td>
		<td><input type="text" name="surveyName"/></td>
	</tr>
	<tr>
		<td>Is Visible</td>
		<td>
			<select name="isVisible">
				<option value="">--Select--</option>
				<option value="T">Visible</option>
				<option value="F">Invisible</option>
			</select>
		</td>
		
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="Submit"/>
		</td>	
	</tr>

</table>
</form>
</body>
</html>