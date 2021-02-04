package com.retailshop.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;

import com.retailshop.action.admin.SurveyQuestionBean;

public interface ISurveyService {

	public boolean createSurvey(Connection connection, String surveyName, String isVisible );
	
	public Map<String,String> getAllSurvey(Connection connection );
	
	public boolean createQuestionForSurvey(Connection connection, String question[], String isVisible[],String surveyId);

	public String getQuestionForSurvey(Connection connection, String surveyId);
	
	public boolean submitAnswer(Connection connection,String surveyId,String question,String qType,String answers, String isVisible);
	
	public boolean submitAnswer(Connection connection,String surveyId,String question,String qType,String answers[], String isVisible[]);
	
	public ArrayList<SurveyQuestionBean> getQuestionAnswerForSurvey(Connection connection, String surveyId);
	
	public boolean updateSurvey(Connection connection,String surveyText,String surveyId);
	
	public boolean updateQuestion(Connection connection,String surveyText,String surveyId, String questionId);
	
	public String getAnswerForSurvey(Connection connection, String surveyId, String questionId);
	
	public boolean updateAnswer(Connection connection,String surveyText,String surveyId, String questionId, String answerId);
	
	public boolean deleteSurvey(Connection connection, String surveyId);
	
}

