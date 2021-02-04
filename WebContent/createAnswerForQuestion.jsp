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
			$("#qTypeTr").hide();
			alert("Please select valid Survey Id");
			return false;
		}
	}
		
	
	function getQType(){
		var val = $("#question").val();
		if(val != "-1"){
			$("#qTypeTr").show();
		}
		else{
			$("#qTypeTr").hide();
			alert("Please select valid question");
			return false;
		}
		
	}
	
	function getAnsType(){
		
		var qType = $("#qType").val();
		if(qType == 'mcq'){
			$("#allOptions").hide();
			$("#ansTypeTr").show();
			$("#noOfOptions").val("");
			$("#submitTr").hide();
		}
		else if(qType == 'single'){
			$("#allOptions").hide();
			$("#ansTypeTr").show();
			$("#noOfOptions").val("");
			$("#submitTr").hide();
		}
		else if(qType == 'des'){
			$("#ansTypeTr").hide();
			$("#allOptions").html("Additional Property : <textarea name='answers' rows='2' cols='15'></textarea><select name='isVisible'><option value=''>--select--</option><option value='T'>Visible</option><option value='F'>Invisible</option></select>");
			$("#allOptions").show();
			$("#submitTr").show();
		}
		else{
			alert("Please select Answer Type.");
			$("#ansTypeTr").hide();
			$("#allOptions").hide();
			$("#submitTr").hide();
		}
		
	}
	
	function generateNoOfOptions(){
		
		var count = $("#noOfOptions").val();
		if(count =='' || count == '0' || count ==0){
			alert("Please enter valid no of fileds.");
			$("#allOptions").html("");
			$("#allOptions").hide();
			return false;
		}
		else{
			var qType = $("#qType").val();
			if(qType == 'mcq' || qType == 'single'){
				var answers = "";
				for(var i = 1; i <= count ; i++){
					answers = answers + "Answer "+i+" : <textarea name='answers' rows = '3' cols='15'></textarea> : <select name='isVisible'><option value=''>--select--</option><option value='T'>Visible</option><option value='F'>Invisible</option></select><br/>";
				}
				$("#allOptions").html("<td colspan = '2'>"+answers+"</td>");
				$("#allOptions").show();
				
				//answerHeader
				$("#submitTr").show();
			}
			/* else if(qType == 'single'){
				var answers = "";
				for(var i = 1; i < count ; i++){
					answers = answers + "<input type='text' name='answer'/><br/>";
				}
				$("#allOptions").html(answers);
				$("#allOptions").show();
			} */
			else{
				$("#allOptions").html("");
				$("#allOptions").hide();
				$("#submitTr").show();
			}
		}
	}
	
	function submitForm(){
		if(confirm("Are you sure to continue?")){
			document.CreateAnswerForm.submit();
		}
		else{
			return false;
		}
	}
	
</script>

</head>
<body onload="hide">

<jsp:include page="adminLinks.jsp"></jsp:include>
Create Answer Form<br/>
<form action="CreateAnswerAction.action" name="CreateAnswerForm" method="post">
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
		<td><select name="question" id="question" onchange="getQType()">
				<option value="">--select--</option>
			</select>
		</td>
	</tr>
	<tr id="qTypeTr" style="display:none">
		<td>Question Type</td>
		<td>
			<select id='qType' name='qType' onchange="getAnsType()">
				<option value='' selected='selected'>--select--</option>
				<option value='single'>Single choice Question</option>
				<option value='mcq'>Multi choice Question</option>
				<option value='des'>Descriptive Question</option>
			</select>	
		</td> 
	</tr>
	<tr id="ansTypeTr" style="display:none">
		<td>No of Options</td>
		<td><input type="text" size='5' name="noOfOptions" id="noOfOptions" onblur="generateNoOfOptions()"/> </td>
	</tr>
	<tr>
		<th colspan="2" align="center">Answers</th>
	</tr>
	<tr id="answerHeader" style="display:none">
		<td>Sr. No</td>
		<td>Answer</td>
		<td>Is Visible</td>
	</tr>
	<tr id="allOptions" style="display:none">
		
	</tr>
	
	<tr id="submitTr" style="display:none">
		<td colspan="2"><input type="button" value="Add Answers" onclick="submitForm()"/></td>
	</tr>
	
	
</table>
<br/>
<br/><%-- 
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
 --%></form>
</body>
</html>