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
	function getSurveyText(){
		
		var txt = $("#surveyId option:selected").text();
		if(txt == '--select--'){
			$("#surveyText").val("");
			alert("Please select valid option.");
			return false;
		}
		else{
			$("#surveyText").val(txt);
		}		
	}
	
	function updateSurveyFunc(){
		var txt = $("#surveyId option:selected").text();
		var txt1 = $("#surveyText").val();
		if(txt != '--select--' && txt1 != ''){
			if(confirm("Are you sure you want to edit?")){
				document.UpdateSurveyForm.submit();
			} 
		}
		else{
			alert("Please enter valid values.");
			return false;
		}
	}
</script>

</head>
<body>

<jsp:include page="adminLinks.jsp"></jsp:include>
Update Survey Page<br/>
<form action="UpdateSurveyAction.action" method="post" name="UpdateSurveyForm">
	<table border="1" align="center">
		<tr>
			<td colspan="2"><font color="red"><s:property value="msg"/></font></td>
		</tr>
		<tr>
			<td>Survey Name</td>
			<td><s:select name="surveyId" key="surveyId" list="allSurveyList" headerKey="-1" headerValue="--select--" onchange="getSurveyText()"/></td>
		</tr>
		<tr>
			<td>Survey Text</td>
			<td><input type="text" name="surveyText" id="surveyText" size="50"/></td>
		</tr>
		<tr>
			<td><input type="button" value="Update Survey" onclick="updateSurveyFunc()"/></td>
		</tr>
	</table>

</form>
</body>
</html>