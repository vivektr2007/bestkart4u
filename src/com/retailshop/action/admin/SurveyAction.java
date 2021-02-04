package com.retailshop.action.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.retailshop.service.ISurveyService;
import com.retailshop.service.impl.SurveyServiceImpl;
import com.retailshop.util.DBConnection;
import com.retailshop.util.DBUtil;

public class SurveyAction implements ServletRequestAware, ServletResponseAware {
	
	private static final Logger logger = Logger.getLogger(SurveyAction.class);
	
	String msg;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Map<String, String> allSurveyList;
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getSurveyForm(){
		
		return "success";
	}
	
	public String createSurvey(){
		
		Connection connection = null;
		try{
			connection = DBConnection.getConnection();
			String isVisible = request.getParameter("isVisible");
			String surveyName = request.getParameter("surveyName");
			
			ISurveyService objSurveyService = new SurveyServiceImpl();
			boolean flag = objSurveyService.createSurvey(connection, surveyName, isVisible);
			if(flag){
				setMsg("Survey Created Successfully");
			}
			else{
				setMsg("There is some error while craeting Survey");
			}
			
			connection.close();
		}
		catch (Exception e) {
			logger.error("There is some error while creating survey,",e);	
		}
		finally{
			DBUtil.closeConnection(connection);
		}
		
		return "success";
	}

	public Map<String, String> getAllSurveyList() {
		return allSurveyList;
	}

	public void setAllSurveyList(Map<String, String> allSurveyList) {
		this.allSurveyList = allSurveyList;
	}

	public String getCreateQuestionForm(){
		
		Connection connection = null; 
		try{
			ISurveyService objSurveyService = new SurveyServiceImpl();
			setAllSurveyList(objSurveyService.getAllSurvey(connection));
		}
		catch(Exception e){
			logger.error("", e);
		}
		finally{
			DBUtil.closeConnection(connection);
		}
		return "success";
	}
	
	public String createQuestion(){
		
		String question[] = request.getParameterValues("question");
		String isVisible[] = request.getParameterValues("isVisible");
		String surveyId = request.getParameter("surveyId");
		
		Connection connection = null; 
		try{
			connection = DBConnection.getConnection();
			ISurveyService objSurveyService = new SurveyServiceImpl();
			setAllSurveyList(objSurveyService.getAllSurvey(connection));
			
			boolean flag = objSurveyService.createQuestionForSurvey(connection, question, isVisible, surveyId);
			if(flag){
				setMsg("Question created successfully");
			}
			else{
				setMsg("There is some internal error in creating question.");
			}
		}
		catch (Exception e) {
			logger.error("", e);
		}
		finally{
			DBUtil.closeConnection(connection);
		}
		return "success";
	}
	
	public String getAnswersForm(){
		
		Connection connection = null; 
		
		try{
			connection = DBConnection.getConnection();
			ISurveyService objSurveyService = new SurveyServiceImpl();
			setAllSurveyList(objSurveyService.getAllSurvey(connection));
		}
		catch(Exception e){
			logger.error("",e);
		}
		finally{
			DBUtil.closeConnection(connection);
		}
		return "success";
	}
	
	public String getQuestionForSurveyAjax() throws IOException{
		
		Connection connection = null; 
		String surveyId = request.getParameter("surveyIdId");
		String allValue = "";
		
		try{
			connection = DBConnection.getConnection();
			ISurveyService objSurveyService = new SurveyServiceImpl();
			allValue = objSurveyService.getQuestionForSurvey(connection, surveyId);
		}
		catch(Exception e){
			logger.error("",e);
		}
		finally{
			DBUtil.closeConnection(connection);
		}
		PrintWriter out = response.getWriter();
		out.println(allValue);
		
		return "none";
	}
	
	public String getAnswerForQuestionAjax() throws IOException{
		
		Connection connection = null; 
		String surveyId = request.getParameter("surveyIdId");
		String questionId = request.getParameter("questionId");
		
		String allValue = "";
		
		try{
			connection = DBConnection.getConnection();
			ISurveyService objSurveyService = new SurveyServiceImpl();
			allValue = objSurveyService.getAnswerForSurvey(connection, surveyId, questionId);
		
		}
		catch(Exception e){
			logger.error("",e);
		}
		finally{
			DBUtil.closeConnection(connection);
		}
		PrintWriter out = response.getWriter();
		out.println(allValue);
		
		return "none";
	}
	
	public String submitAnswer(){
		
		boolean flag = false;
		
		String surveyId = request.getParameter("surveyId");
		String question = request.getParameter("question");
		String qType = request.getParameter("qType");
		
		Connection connection = null;
		
		try{
		
			connection = DBConnection.getConnection();	
			ISurveyService objSurveyService = new SurveyServiceImpl();
			
			setAllSurveyList(objSurveyService.getAllSurvey(connection));
			
			if(qType != null && !qType.equals("") && (qType.equals("single") || qType.equals("mcq"))){
				String answers[] = request.getParameterValues("answers");
				String isVisible[] = request.getParameterValues("isVisible");
				flag = objSurveyService.submitAnswer(connection, surveyId, question, qType, answers,isVisible);
			}
			else if(qType != null && !qType.equals("") && qType.equals("des")){
				String answers = request.getParameter("answers");
				String isVisible = request.getParameter("isVisible");
				flag = objSurveyService.submitAnswer(connection, surveyId, question, qType, answers, isVisible);
			}
		}
		finally{
			DBUtil.closeConnection(connection);
		}
		if(flag){
			setMsg("Answer Added Successfully.");
		}
		else{
			setMsg("There are different type of answers already for This question.Please delete them then create new.");
		}
		return "success";
	}
	
	public String viewSurvey(){
		
		Connection connection = null;
		try{
		
			connection = DBConnection.getConnection();	
			ISurveyService objSurveyService = new SurveyServiceImpl();
			setAllSurveyList((Map)objSurveyService.getAllSurvey(connection));
			
		}
		finally{
			DBUtil.closeConnection(connection);
		}

		return "success";
	}
	
	public String getAllSurveyQuestion(){
		
		String surveyId = request.getParameter("surveyId");
		
		Connection connection = null;
		try{
		
			connection = DBConnection.getConnection();	
			ISurveyService objSurveyService = new SurveyServiceImpl();
			
			ArrayList<SurveyQuestionBean> allQuestionAnswerForSurvey = objSurveyService.getQuestionAnswerForSurvey(connection, surveyId);
			
			request.setAttribute("allQuestionAnswerForSurvey", allQuestionAnswerForSurvey);
			
		}
		finally{
			DBUtil.closeConnection(connection);
		}
		
		return "success";
	}
	
	public String getUpdateSurveyForm(){
		
		Connection connection = null; 
		try{
			connection = DBConnection.getConnection();
			ISurveyService objSurveyService = new SurveyServiceImpl();
			setAllSurveyList(objSurveyService.getAllSurvey(connection));
		
		}
		catch(Exception e){
			logger.error("",e);
		}
		finally{
			DBUtil.closeConnection(connection);
		}
		return "success";
	}
	
	public String updateSurvey(){
		
		Connection connection = null; 
		try{			
			String surveyId = request.getParameter("surveyId");
			String surveyText = request.getParameter("surveyText");
			
			connection = DBConnection.getConnection();
			ISurveyService objSurveyService = new SurveyServiceImpl();
			objSurveyService.updateSurvey(connection, surveyText, surveyId);
			
			setAllSurveyList(objSurveyService.getAllSurvey(connection));
			
			setMsg("Survey updated Successfully.");
			
		}
		catch(Exception e){
			logger.error("",e);
		}
		finally{
			DBUtil.closeConnection(connection);
		}
		return "success";
	}
	
	public String getUpdateQuestionForm(){
		
		Connection connection = null; 
		try{			

			connection = DBConnection.getConnection();
			ISurveyService objSurveyService = new SurveyServiceImpl();
			
			setAllSurveyList(objSurveyService.getAllSurvey(connection));
		}
		catch(Exception e){
			logger.error("",e);
		}
		finally{
			DBUtil.closeConnection(connection);
		}
		return "success";
	}
	
	public String updateQuestion(){
		
		Connection connection = null; 
		try{			
			String surveyId = request.getParameter("surveyId");
			String surveyText = request.getParameter("surveyText");
			String question = request.getParameter("question");
			
			connection = DBConnection.getConnection();
			ISurveyService objSurveyService = new SurveyServiceImpl();
			objSurveyService.updateQuestion(connection, surveyText, surveyId, question);
			
			setMsg("Question updated successfully.");
			
			setAllSurveyList(objSurveyService.getAllSurvey(connection));
		}
		catch(Exception e){
			logger.error("",e);
		}
		finally{
			DBUtil.closeConnection(connection);
		}
		return "success";
	}
	
	public String getUpdateAnswerForm(){
		
		Connection connection = null; 
		
		try{
			connection = DBConnection.getConnection();
			ISurveyService objSurveyService = new SurveyServiceImpl();
			setAllSurveyList(objSurveyService.getAllSurvey(connection));
		}
		catch(Exception e){
			logger.error("",e);
		}
		finally{
			DBUtil.closeConnection(connection);
		}
		return "success";
	}
	
	public String updateAnswer(){
		
		Connection connection = null; 
		try{			
			String surveyId = request.getParameter("surveyId");
			String surveyText = request.getParameter("surveyText");
			String question = request.getParameter("question");
			String answer = request.getParameter("answer");
			
			connection = DBConnection.getConnection();
			ISurveyService objSurveyService = new SurveyServiceImpl();
			objSurveyService.updateAnswer(connection, surveyText, surveyId, question,answer);
			
			setMsg("Answer updated successfully.");
			
			setAllSurveyList(objSurveyService.getAllSurvey(connection));
		}
		catch(Exception e){
			logger.error("",e);
		}
		finally{
			DBUtil.closeConnection(connection);
		}
		return "success";
	}
	
	
	
	public String getDeleteSurveyForm(){
		
		Connection connection = null;
		try{
			connection = DBConnection.getConnection();
			ISurveyService objSurveyService = new SurveyServiceImpl();
			setAllSurveyList(objSurveyService.getAllSurvey(connection));
		}
		catch(Exception e){
			logger.error("",e);
		}
		finally{
			DBUtil.closeConnection(connection);
		}
		
		return "success";
		
	}
	
	public String deleteSurvey(){
		
		String surveyId = request.getParameter("surveyId");
		
		Connection connection = null;
		try{
			connection = DBConnection.getConnection();
			ISurveyService objSurveyService = new SurveyServiceImpl();
			
			boolean flag = objSurveyService.deleteSurvey(connection, surveyId);
			
			setAllSurveyList(objSurveyService.getAllSurvey(connection));
		}
		catch(Exception e){
			logger.error("",e);
		}
		finally{
			DBUtil.closeConnection(connection);
		}
		
		return "success";
		
		
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
}
