package com.retailshop.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;

import com.retailshop.action.admin.SurveyQuestionBean;
import com.retailshop.dao.ISurveyDAO;
import com.retailshop.dao.impl.SurveyDAOImpl;
import com.retailshop.service.ISurveyService;

public class SurveyServiceImpl implements ISurveyService{

	@Override
	public boolean createSurvey(Connection connection, String surveyName,
			String isVisible) {
		
		ISurveyDAO objSurveyDao = new SurveyDAOImpl();
		return objSurveyDao.createSurvey(connection, surveyName, isVisible);
		
	}

	@Override
	public Map<String, String> getAllSurvey(Connection connection) {
		ISurveyDAO objSurveyDao = new SurveyDAOImpl();
		return objSurveyDao.getAllSurvey(connection);
	}

	@Override
	public boolean createQuestionForSurvey(Connection connection,
			String[] question, String[] isVisible, String surveyId) {
		ISurveyDAO objSurveyDao = new SurveyDAOImpl();
		return objSurveyDao.createQuestionForSurvey(connection, question, isVisible, surveyId);
	}

	@Override
	public String getQuestionForSurvey(Connection connection, String surveyId) {
		ISurveyDAO objSurveyDao = new SurveyDAOImpl();
		return objSurveyDao.getQuestionForSurvey(connection, surveyId);
	}

	@Override
	public boolean submitAnswer(Connection connection, String surveyId,
			String question, String qType, String answers, String isVisible) {
		ISurveyDAO objSurveyDao = new SurveyDAOImpl();
		return objSurveyDao.submitAnswer(connection, surveyId, question, qType, answers,isVisible);
	}

	@Override
	public boolean submitAnswer(Connection connection, String surveyId,
			String question, String qType, String[] answers, String isVisible[]) {
		ISurveyDAO objSurveyDao = new SurveyDAOImpl();
		return objSurveyDao.submitAnswer(connection, surveyId, question, qType, answers,isVisible);
	}

	@Override
	public ArrayList<SurveyQuestionBean> getQuestionAnswerForSurvey(
			Connection connection, String surveyId) {
		ISurveyDAO objSurveyDao = new SurveyDAOImpl();
		return objSurveyDao.getQuestionAnswerForSurvey(connection, surveyId);
	}

	@Override
	public boolean updateSurvey(Connection connection,String surveyText,String surveyId){
		ISurveyDAO objSurveyDao = new SurveyDAOImpl();
		return objSurveyDao.updateSurvey(connection, surveyText, surveyId);
	}
	
	@Override
	public boolean updateQuestion(Connection connection,String surveyText,String surveyId, String questionId){
		ISurveyDAO objSurveyDao = new SurveyDAOImpl();
		return objSurveyDao.updateQuestion(connection, surveyText, surveyId, questionId);
	}

	@Override
	public String getAnswerForSurvey(Connection connection, String surveyId,
			String questionId) {
		ISurveyDAO objSurveyDao = new SurveyDAOImpl();
		return objSurveyDao.getAnswerForSurvey(connection, surveyId, questionId);
	}

	@Override
	public boolean updateAnswer(Connection connection,String surveyText,String surveyId, String questionId, String answerId){
		ISurveyDAO objSurveyDao = new SurveyDAOImpl();
		return objSurveyDao.updateAnswer(connection, surveyText, surveyId, questionId, answerId);
	}

	@Override
	public boolean deleteSurvey(Connection connection, String surveyId) {
		ISurveyDAO objSurveyDao = new SurveyDAOImpl();
		return objSurveyDao.deleteSurvey(connection, surveyId);
	}
	
}
