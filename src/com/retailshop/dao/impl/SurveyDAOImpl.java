package com.retailshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.retailshop.action.admin.SurveyAnswerBean;
import com.retailshop.action.admin.SurveyQuestionBean;
import com.retailshop.dao.ISurveyDAO;
import com.retailshop.util.DBUtil;

public class SurveyDAOImpl implements ISurveyDAO{

	private static final Logger logger = Logger.getLogger(SurveyDAOImpl.class.getName());
	
	@Override
	public boolean createSurvey(Connection connection, String surveyName,
			String isVisible) {
		PreparedStatement pstmt = null;
		boolean flag = false;
		
		try{
			pstmt = connection.prepareStatement(createSurveyQuery);
			pstmt.setInt(1, getSurveyId(connection));
			pstmt.setString(2, surveyName);
			pstmt.setString(3, isVisible);
			
			int i = pstmt.executeUpdate();
			if(i>0){
				flag = true;
			}			
		}
		catch(Exception e){
			logger.error("There in some error in creatng survey",e);
		}
		finally{
			DBUtil.closePreparedStatement(pstmt);
		}
		return flag;
	}
	
	@Override
	public int getSurveyId(Connection connection){
		PreparedStatement pstmt = null;
		
		int surveyID = 0;
		
		try{
			pstmt = connection.prepareStatement(surveyIdQuery);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				surveyID = rs.getInt(1);
			}
			
		}
		catch(Exception e){
			logger.error("There in some error in creatng survey",e);
		}
		finally{
			DBUtil.closePreparedStatement(pstmt);
		}
		return surveyID+1;
		
	}

	@Override
	public Map<String, String> getAllSurvey(Connection connection) {
		
		Map<String, String> allSurvey = new HashMap<String,String>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String getAllSurveyQuery = "select * from survey_master where is_visible = 'T'";
		
		try{
			pstmt = connection.prepareStatement(getAllSurveyQuery);
			rs = pstmt.executeQuery();
			while(rs.next()){
				allSurvey.put(rs.getString("survey_id"), rs.getString("survey_name"));
			}
		}
		catch (Exception e) {
			logger.error("Error in getting all survey", e);
		}
		finally{
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(pstmt);
		}
		
		return allSurvey;
	}

	@Override
	public boolean createQuestionForSurvey(Connection connection,
			String[] question, String[] isVisible, String surveyId) {
		PreparedStatement pstmt = null;
		boolean flag = false;
		for(int i = 0; i < question.length; i++){
			try{
				pstmt = connection.prepareStatement(createQuestionQuery);
				pstmt.setInt(1, getQuestionId(connection));
				pstmt.setString(2, question[i]);
				pstmt.setString(3, isVisible[i]);
				pstmt.setString(4, surveyId);
				
				int j = pstmt.executeUpdate();
				if(j>0){
					flag = true;
				}			
			}
			catch(Exception e){
				logger.error("There in some error in creatng survey",e);
			}
			finally{
				DBUtil.closePreparedStatement(pstmt);
			}
		}
		return flag;
	}
	
	@Override
	public int getQuestionId(Connection connection){
		PreparedStatement pstmt = null;
		
		int surveyID = 0;
		
		try{
			pstmt = connection.prepareStatement(questionIdQuery);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				surveyID = rs.getInt(1);
			}
			
		}
		catch(Exception e){
			logger.error("There in some error in creatng survey",e);
		}
		finally{
			DBUtil.closePreparedStatement(pstmt);
		}
		return surveyID+1;
		
	}

	@Override
	public String getQuestionForSurvey(Connection connection, String surveyId) {
		PreparedStatement pstmt = null;
		
		String allQuestion = "";
		try{
			pstmt = connection.prepareStatement(allQuestionForSurveyAjax);
			pstmt.setInt(1, Integer.parseInt(surveyId));
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				allQuestion = allQuestion + "#" + rs.getString("question_id") + "$" + rs.getString("question_desc");
			}
			
		}
		catch(Exception e){
			logger.error("There in some error in creatng survey",e);
		}
		finally{
			DBUtil.closePreparedStatement(pstmt);
		}
		if(allQuestion.indexOf("#") != -1){
			allQuestion = allQuestion.substring(1);
		}
		
		return allQuestion;
	}

	@Override
	public boolean submitAnswer(Connection connection, String surveyId,
			String question, String qType, String answers, String isVisible) {
		PreparedStatement pstmt = null;
		boolean flag = false;
		String property = "";
		
		String type="";
		if(qType.equalsIgnoreCase("des")){
			type="textarea";
			property = "rows = '3' cols='15'";
		}
		else if(qType.equalsIgnoreCase("single")){
			type="radio";
			property = "";
		}
		else if(qType.equalsIgnoreCase("mcq")){
			type="checkbox";
			property = "";
		}
		
		try{
			pstmt = connection.prepareStatement(createAnswerQuery);
			pstmt.setInt(1, Integer.parseInt(surveyId));
			pstmt.setInt(2, Integer.parseInt(question));
			pstmt.setInt(3, getAnswerId(connection));
			pstmt.setString(4, answers);
			pstmt.setString(5, type);
			pstmt.setString(6, "");
			pstmt.setString(7, property);
			pstmt.setString(8, isVisible);
			
			int j = pstmt.executeUpdate();
			if(j>0){
				flag = true;
			}			
		}
		catch(Exception e){
			logger.error("There in some error in creatng survey",e);
		}
		finally{
			DBUtil.closePreparedStatement(pstmt);
		}
		
		return flag;
	}

	@Override
	public boolean submitAnswer(Connection connection, String surveyId,
			String question, String qType, String[] answers, String isVisible[]) {
		
		PreparedStatement pstmt = null;
		boolean flag = false;
		String property = "";
		String type="";
		
		
		
		if(qType.equalsIgnoreCase("des")){
			type="textarea";
			property = "rows = '3' cols='15'";
		}
		else if(qType.equalsIgnoreCase("single")){
			type="radio";
			property = "";
		}
		else if(qType.equalsIgnoreCase("mcq")){
			type="checkbox";
			property = "";
		}
		
		boolean isValid = deleteAnswersForQuestion(connection, surveyId, question);
		if(isValid){
			for(int i = 0; i<answers.length; i++){
				try{
					pstmt = connection.prepareStatement(createAnswerQuery);
					pstmt.setInt(1, Integer.parseInt(surveyId));
					pstmt.setInt(2, Integer.parseInt(question));
					pstmt.setInt(3, getAnswerId(connection));
					pstmt.setString(4, answers[i]);
					pstmt.setString(5, type);
					pstmt.setString(6, "");
					pstmt.setString(7, property);
					pstmt.setString(8, isVisible[i]);
					
					int j = pstmt.executeUpdate();
					if(j>0){
						flag = true;
					}			
				}
				catch(Exception e){
					logger.error("There in some error in creatng survey",e);
				}
				finally{
					DBUtil.closePreparedStatement(pstmt);
				}
			}
		}
		return flag;
	}
	
	@Override
	public int getAnswerId(Connection connection){
		PreparedStatement pstmt = null;
		
		int surveyID = 0;
		
		try{
			pstmt = connection.prepareStatement(answerIdQuery);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				surveyID = rs.getInt(1);
			}
			
		}
		catch(Exception e){
			logger.error("There in some error in creatng survey",e);
		}
		finally{
			DBUtil.closePreparedStatement(pstmt);
		}
		return surveyID+1;
		
	}

	@Override
	public ArrayList<SurveyQuestionBean> getQuestionAnswerForSurvey(
			Connection connection, String surveyId) {
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		
		ResultSet rs = null;
		ArrayList<SurveyQuestionBean> allQuestionAnswerForSurvey = new ArrayList<SurveyQuestionBean>();
		
		
		try{
			pstmt = connection.prepareStatement(GET_ALL_QUESTION_FOR_SURVEY_QUERY);
			pstmt.setInt(1, Integer.parseInt(surveyId));
			rs = pstmt.executeQuery();
			while(rs.next()){
				
				int questionId = rs.getInt("question_id");
				
				SurveyQuestionBean objSurveyQuestionBean = new SurveyQuestionBean();
				objSurveyQuestionBean.setQuestionId(questionId);
				objSurveyQuestionBean.setQuestionDesc(rs.getString("question_desc"));
				
				ArrayList<SurveyAnswerBean> answerList = new ArrayList<SurveyAnswerBean>();
				
				pstmt1 = connection.prepareStatement(GET_ALL_ANSWER_FOR_QUESTION_QUERY);
				pstmt1.setInt(1, Integer.parseInt(surveyId));
				pstmt1.setInt(2, questionId);
				ResultSet rs1 = pstmt1.executeQuery();
				while(rs1.next()){
					
					SurveyAnswerBean objSurveyAnswerBean = new SurveyAnswerBean();
					objSurveyAnswerBean.setAnswerId(rs1.getInt("answer_id"));
					objSurveyAnswerBean.setAnswerDesc(rs1.getString("answer_desc"));
					objSurveyAnswerBean.setAnswerType(rs1.getString("answer_type"));
					objSurveyAnswerBean.setCssClass(""+rs1.getString("css_class"));
					objSurveyAnswerBean.setMoreProperty(""+rs1.getString("more_property"));
					
					answerList.add(objSurveyAnswerBean);
					
				}
				
				objSurveyQuestionBean.setSurveyAnswerBean(answerList);
				
				rs1.close();
				pstmt1.close();
				
				allQuestionAnswerForSurvey.add(objSurveyQuestionBean);
				
			}

			
		}
		catch(Exception e){
			logger.error("There in some error in creatng survey",e);
		}
		finally{
			DBUtil.closePreparedStatement(pstmt);
		}
		return allQuestionAnswerForSurvey;
	}

	public boolean deleteAnswersForQuestion(Connection connection, String surveyId, String questionId){
		boolean flag = false;
		PreparedStatement pstmt = null;
		
		try{
			pstmt = connection.prepareStatement("delete from survey_answer_master where survey_id = ? and question_id = ?");
			pstmt.setInt(1, Integer.parseInt(surveyId));
			pstmt.setInt(2, Integer.parseInt(questionId));
			
			int rs = pstmt.executeUpdate();
			if(rs > 0){
				flag = true;
			}
			
		}
		catch(Exception e){
			logger.error("", e);
		}
		finally{
			DBUtil.closePreparedStatement(pstmt);
		}
		
		return flag;
	}

	@Override
	public boolean updateSurvey(Connection connection, String surveyText,
			String surveyId) {
		boolean flag = false;
		PreparedStatement pstmt = null;
		
		try{
			pstmt = connection.prepareStatement("update survey_master set survey_name = ? where survey_id = ?");
			pstmt.setString(1, surveyText);
			pstmt.setInt(2, Integer.parseInt(surveyId));
			
			int rs = pstmt.executeUpdate();
			if(rs > 0){
				flag = true;
			}
		}
		catch(Exception e){
			logger.error("", e);
		}
		finally{
			DBUtil.closePreparedStatement(pstmt);
		}
		
		return flag;
	}
	
	@Override
	public boolean updateQuestion(Connection connection, String surveyText,
			String surveyId, String questionId) {
		boolean flag = false;
		PreparedStatement pstmt = null;
		
		try{
			pstmt = connection.prepareStatement("update survey_question_master set question_desc = ? where survey_id = ? and question_id = ?");
			pstmt.setString(1, surveyText);
			pstmt.setInt(2, Integer.parseInt(surveyId));
			pstmt.setInt(3, Integer.parseInt(questionId));
			
			int rs = pstmt.executeUpdate();
			if(rs > 0){
				flag = true;
			}
		}
		catch(Exception e){
			logger.error("", e);
		}
		finally{
			DBUtil.closePreparedStatement(pstmt);
		}
		
		return flag;
	}

	@Override
	public String getAnswerForSurvey(Connection connection, String surveyId,
			String questionId) {
		String allQuestion = "";
		PreparedStatement pstmt = null;
		try{
			pstmt = connection.prepareStatement(allAnswerForQuestionAjax);
			pstmt.setInt(1, Integer.parseInt(surveyId));
			pstmt.setInt(2, Integer.parseInt(questionId));
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				allQuestion = allQuestion + "#" + rs.getString("answer_id") + "$" + rs.getString("answer_desc");
			}
			
		}
		catch(Exception e){
			logger.error("There in some error in creatng survey",e);
		}
		finally{
			DBUtil.closePreparedStatement(pstmt);
		}
		if(allQuestion.indexOf("#") != -1){
			allQuestion = allQuestion.substring(1);
		}
		
		return allQuestion;
	}
	
	@Override
	public boolean updateAnswer(Connection connection, String surveyText,
			String surveyId, String questionId, String answerId) {
		boolean flag = false;
		PreparedStatement pstmt = null;
		
		try{
			pstmt = connection.prepareStatement("update survey_answer_master set answer_desc = ? where survey_id = ? and question_id = ? and answer_id = ?");
			pstmt.setString(1, surveyText);
			pstmt.setInt(2, Integer.parseInt(surveyId));
			pstmt.setInt(3, Integer.parseInt(questionId));
			pstmt.setInt(4, Integer.parseInt(answerId));
			
			int rs = pstmt.executeUpdate();
			if(rs > 0){
				flag = true;
			}
		}
		catch(Exception e){
			logger.error("", e);
		}
		finally{
			DBUtil.closePreparedStatement(pstmt);
		}
		
		return flag;
	}

	@Override
	public boolean deleteSurvey(Connection connection, String surveyId) {
		boolean flag = false;
		PreparedStatement pstmt = null;
		
		try{
			
			Statement stmt = connection.createStatement();
			stmt.addBatch("update survey_master set is_visible = 'F' where survey_id = "+Integer.parseInt(surveyId));
			stmt.addBatch("update survey_question_master set is_visible = 'F' where survey_id = "+Integer.parseInt(surveyId));
			stmt.addBatch("update survey_answer_master set is_visible = 'F' where survey_id = "+Integer.parseInt(surveyId));
			
			int a[] = stmt.executeBatch();
			if(a[0] > 0 && a[1] > 0 && a[2] > 0){
				flag = true;
			}
			
		}
		catch(Exception e){
			logger.error("", e);
		}
		finally{
			DBUtil.closePreparedStatement(pstmt);
		}
		
		return flag;
	}
}
