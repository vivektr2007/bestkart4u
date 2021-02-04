package com.retailshop.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;

import com.retailshop.action.admin.SurveyQuestionBean;

public interface ISurveyDAO {
	
	String createSurveyQuery = "INSERT INTO survey_master( survey_id, survey_name, is_visible )	VALUES ( ?, ? , ? )"; 
	
	public boolean createSurvey(Connection connection,String surveyName, String isVisible );
	
	String surveyIdQuery = "select max(survey_id) from survey_master";
	
	public int getSurveyId(Connection connection);
	
	public Map<String,String> getAllSurvey(Connection connection );
	
	String questionIdQuery = "select max(question_id) from survey_question_master";
	
	public int getQuestionId(Connection connection);
	
	String createQuestionQuery = "INSERT INTO survey_question_master( question_id, question_desc, is_visible, survey_id) VALUES ( ?, ? , ? , ? )";
	
	public boolean createQuestionForSurvey(Connection connection, String question[], String isVisible[], String surveyId);
	
	String allQuestionForSurveyAjax = "select * from survey_question_master where survey_id = ? and is_visible = 'T'";
	
	public String getQuestionForSurvey(Connection connection, String surveyId);
	
	String createAnswerQuery = "insert into survey_answer_master(survey_id,question_id,answer_id,answer_desc,answer_type,css_class, more_property, is_visible) value(?,?,?,?,?,?,?,?)";
	
	public boolean submitAnswer(Connection connection,String surveyId,String question,String qType,String answers, String isVisible);
	
	public boolean submitAnswer(Connection connection,String surveyId,String question,String qType,String answers[], String isVisible[]);
	
	String answerIdQuery = "select max(answer_id) from survey_answer_master";
	
	public int getAnswerId(Connection connection);
	
	String GET_ALL_QUESTION_FOR_SURVEY_QUERY = "select * from survey_question_master where survey_id = ? and is_visible = 'T'";
	
	String GET_ALL_ANSWER_FOR_QUESTION_QUERY = "select * from survey_answer_master where survey_id = ? and question_id = ? and is_visible='T'";
	
	public ArrayList<SurveyQuestionBean> getQuestionAnswerForSurvey(Connection connection, String surveyId);
	
	public boolean updateSurvey(Connection connection,String surveyText,String surveyId);
	
	public boolean updateQuestion(Connection connection,String surveyText,String surveyId, String questionId);
	
	String allAnswerForQuestionAjax = "select * from survey_answer_master where survey_id = ? and question_id = ? and is_visible = 'T'";
	
	public String getAnswerForSurvey(Connection connection, String surveyId, String questionId);
	
	public boolean updateAnswer(Connection connection,String surveyText,String surveyId, String questionId, String answerId);
	
	public boolean deleteSurvey(Connection connection, String surveyId);
	
	
}
