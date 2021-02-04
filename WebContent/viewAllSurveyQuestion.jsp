<%@page import="com.retailshop.action.admin.SurveyAnswerBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.retailshop.action.admin.SurveyQuestionBean"%>
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

View All Survey Question Page<br/>

<br/>
<br/>
<%
	int i = 0;
	ArrayList<SurveyQuestionBean> allQuestionAnswerForSurvey = (ArrayList<SurveyQuestionBean>)request.getAttribute("allQuestionAnswerForSurvey");
	if(allQuestionAnswerForSurvey != null && allQuestionAnswerForSurvey.size() > 0){
	Iterator itr = allQuestionAnswerForSurvey.iterator();
	while(itr.hasNext()){
		SurveyQuestionBean objSurveyQuestionBean = (SurveyQuestionBean)itr.next();
		
%>
	<%=++i %> : <%=objSurveyQuestionBean.getQuestionDesc() %><br/>
		
	<%
		List<SurveyAnswerBean> objSurveyAnswerBeanList = objSurveyQuestionBean.getSurveyAnswerBean();
		if(objSurveyAnswerBeanList != null && objSurveyAnswerBeanList.size() > 0){
		Iterator itr1 = objSurveyAnswerBeanList.iterator();
		while(itr1.hasNext()){
			SurveyAnswerBean objSurveyAnswerBean = (SurveyAnswerBean)itr1.next();
			String type = objSurveyAnswerBean.getAnswerType();
			if(type != null && (type.equalsIgnoreCase("radio") || type.equalsIgnoreCase("checkbox"))){
	%>
		<input type="<%=type%>" name="answers<%=objSurveyQuestionBean.getQuestionId()%>" value="<%=objSurveyAnswerBean.getAnswerDesc() %>">
		<%=objSurveyAnswerBean.getAnswerDesc() %><br/>
	<%
		}
		
		else if(type != null && type.equalsIgnoreCase("textarea")){
		%>
			<textarea name="answers<%=objSurveyQuestionBean.getQuestionId()%>" value="" <%=objSurveyAnswerBean.getMoreProperty() %>>
		<br/>
		
	<%	}
		}
		}
	}
	}
%>

</body>
</html>