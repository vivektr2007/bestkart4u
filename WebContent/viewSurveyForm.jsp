<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<jsp:include page="adminLinks.jsp"></jsp:include>

View Survey Page<br/>

<br/>
<br/>
<br/>
<table align="center" border="1">
<%
	Map<String, String> allActiveSurvey = (Map<String, String>)request.getAttribute("allSurveyList");
	Set entrySet = allActiveSurvey.entrySet();
	if(entrySet.size() > 0){
		Iterator itr = entrySet.iterator();
		
		while(itr.hasNext()){
			Map.Entry me = (Map.Entry)itr.next();
%>
<tr><td>
	<a href="ParticipateInSurvey.action?surveyId=<%=me.getKey()%>"><%=me.getValue() %></a><br/>
	</td>
	</tr>
<%
		}
	}
	else{
	
%>
	No Running Survey
<%

}
%>
</body>
</html>