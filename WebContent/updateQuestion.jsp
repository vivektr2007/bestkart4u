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
	
	function getQuestionListForSurvey(){
		var surveyId = $("#surveyId").val();
		if(surveyId != '-1'){
			$.get(
			    "GetAllQuestionForSurvey.action",
			    {surveyIdId:surveyId},
			    function(data) { 
			    	var myData = data.split("#");
			    	if(myData != '' && myData.length != 'undefined' && myData.length > 0 ){
			    		$("#question").html("<option value='-1'>--select--</option>");
				    	for(var i = 0; i< myData.length; i++){
				    		var keyVal = myData[i].split("$");
				    		if(typeof keyVal[1] !== "undefined"){
				    			var option ="<option value='"+keyVal[0]+"'>"+keyVal[1]+"</option>";
				    			$("#question").html($("#question").html() +option);
				    		}
				    	}
			    	}
			    },
			    "html"
			);
		}
		else{
			var val = "<option value='-1'>--select--</option>";
			$("#question").html(val);
			alert("Please select valid Survey Id");
			return false;
		}
	}
		
	function updateSurveyFunc(){
		var txt = $("#surveyId option:selected").text();
		var txt2 = $("#question option:selected").text();
		var txt1 = $("#surveyText").val();
		if(txt != '--select--' && txt1 != '' && txt2 != '--select--'){
			if(confirm("Are you sure you want to edit?")){
				document.UpdateQuestionForm.submit();
			} 
		}
		else{
			alert("Please enter valid values.");
			return false;
		}
	}
	
	function getSurveyText(){
		
		var txt = $("#question option:selected").text();
		if(txt == '--select--'){
			$("#surveyText").val("");
			alert("Please select valid option.");
			return false;
		}
		else{
			$("#surveyText").val(txt);
		}		
	}
	
	
</script>

</head>
<body onload="hide">

<jsp:include page="adminLinks.jsp"></jsp:include>
Update Question Page
<form action="UpdateQuestionAction.action" name="UpdateQuestionForm" method="post">
<table border="1" align="center">
	<tr>
		<td colspan="2"><font color="red"><s:property value="msg"/></font></td>
	</tr>
	<tr>
		<td>Survey Name</td>
		<td><s:select name="surveyId" key="surveyId" list="allSurveyList" headerKey="-1" headerValue="--select--" onchange="getQuestionListForSurvey()"/></td>
	</tr>
	<tr>
		<td>Question</td>
		<td><select name="question" id="question" onchange="getSurveyText()">
				<option value="">--select--</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>Survey Text</td>
		<td><input type="text" name="surveyText" id="surveyText" size="50"/></td>
	</tr>
	<tr>
		<td><input type="button" value="Update Question" onclick="updateSurveyFunc()"/></td>
	</tr>
	
	
</table>
<br/>
<br/></form>
</body>
</html>