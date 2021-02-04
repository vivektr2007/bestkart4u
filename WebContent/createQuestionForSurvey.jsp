<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
	function addRow(){
		
		var innerVal = $("#questionTable").html();
		var newRow1 = '<tr><td><input type="Text" size="150" name="question"/></td><td><select name="isVisible">';
		var newRow2 = '<option value="">--Select--</option><option value="T">Visible</option><option value="F">Invisible</option>';
		var newRow3 = '</select></td></tr>';
		
		var newRow = newRow1+newRow2+newRow3;
		
		$("#questionTable").html(innerVal+newRow);
		
	}
</script>

</head>
<body>

<jsp:include page="adminLinks.jsp"></jsp:include>
Create Question Form<br/>
<form action="CreateQuestionAction.action" method="post">
<table border="1" align="center">
	<tr>
		<td colspan="2"><font color="red"><s:property value="msg"/></font></td>
	</tr>
	<tr>
		<td>Survey Name</td>
		<td><s:select name="surveyId" list="allSurveyList" headerKey="-1" headerValue="--select--"/></td>
	</tr>
</table>
<br/>
<br/>
<table id="questionTable" border="1" width="80%" align="center">
	<tr>
		<td>
			<span onclick="addRow()"><u>Add Row</u></span>
		</td>
		<td>
			<input type="submit" value="Add Questions"/>
		</td>	
	</tr>
	<tr>
		<th>Question</th>
		<th>Is Visible</th>
	</tr>
	<tr>
		<td><input type="Text" size="150" name="question"/></td>
		<td>
			<select name="isVisible">
				<option value="">--Select--</option>
				<option value="T">Visible</option>
				<option value="F">Invisible</option>
			</select>
		</td>
	</tr>
	

</table>
</form>
</body>
</html>