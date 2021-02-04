<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="js/jquery.min.js" type="text/javascript"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
	function deleteSurvey(){
		var txt = $("#surveyId option:selected").text();
		if(txt == '' || txt == '--select--'){
			alert("Please select Valid survey.");
			return false;
		}
		if(confirm("Are you sure to delete?")){
			document.deleteSurveyForm.submit();
		}
	}
</script>

</head>
<body>

<jsp:include page="adminLinks.jsp"></jsp:include>
Delete Survey Form<br/>
<form action="DeleteSurveyAction.action" method="post" name="deleteSurveyForm">
<table border="1" align="center">
	<tr>
		<td colspan="2"><font color="red"><s:property value="msg"/></font></td>
	</tr>
	<tr>
		<td>Survey Name</td>
		<td><s:select name="surveyId" list="allSurveyList" key="surveyId" headerKey="-1" headerValue="--select--"/></td>
	</tr>
	<tr>
		<td colspan="2"><input type="button" onclick="deleteSurvey()" value="Delete"/></td>
	</tr>
</table>
</form>
</body>
</html>