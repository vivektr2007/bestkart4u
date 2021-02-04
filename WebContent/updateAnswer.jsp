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
			//$("#surveyText").val("");
			var val = "<option value='-1'>--select--</option>";
			$("#question").html(val);
			alert("Please select valid Survey Id");
			return false;
		}
	}
	
	function getAnswerListForSurvey(){
		var surveyId = $("#surveyId").val();
		var questionId = $("#question").val();
		if(surveyId != '-1' ){
			$.get(
			    "GetAllAnswerForQuestion.action",
			    {surveyIdId:surveyId,questionId:questionId},
			    function(data) { 
			    	var myData = data.split("#");
			    	if(myData != '' && myData.length != 'undefined' && myData.length > 0 ){
			    		$("#answer").html("<option value='-1'>--select--</option>");
				    	for(var i = 0; i< myData.length; i++){
				    		var keyVal = myData[i].split("$");
				    		if(typeof keyVal[1] !== "undefined"){
				    			var option ="<option value='"+keyVal[0]+"'>"+keyVal[1]+"</option>";
				    			$("#answer").html($("#answer").html() +option);
				    		}
				    	}
			    	}
			    },
			    "html"
			);
		}
		else{
			$("#surveyText").val("");
			var val = "<option value='-1'>--select--</option>";
			$("#answer").html(val);
			alert("Please select valid Question Id");
			return false;
		}
	}
	
	function updateAnswerFunc(){
		var txt = $("#surveyId option:selected").text();
		var txt2 = $("#question option:selected").text();
		var txt3 = $("#answer option:selected").text();
		var txt1 = $("#surveyText").val();
		if(txt != '--select--' && txt1 != '' && txt2 != '--select--' && txt3 != '--select--'){
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
		
		var txt = $("#answer option:selected").text();
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
Update Answer Page<br/>
<form action="UpdateAnswerAction.action" name="UpdateQuestionForm" method="post">
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
		<td><select name="question" id="question" onchange="getAnswerListForSurvey()" onchange="getSurveyText()">
				<option value="">--select--</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>Answer</td>
		<td><select name="answer" id="answer" onchange="getSurveyText()">
				<option value="">--select--</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>Survey Text</td>
		<td><input type="text" name="surveyText" id="surveyText" size="50"/></td>
	</tr>
	<tr>
		<td><input type="button" value="Update Answer" onclick="updateAnswerFunc()"/></td>
	</tr>
	
	
</table>
<br/>
<br/></form>
</body>
</html>