package com.retailshop.action.admin;

import java.util.ArrayList;
import java.util.List;

public class SurveyQuestionBean {

	private int questionId;
	private String questionDesc;
	private List<SurveyAnswerBean> surveyAnswerBean = new ArrayList<SurveyAnswerBean>();
	
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public String getQuestionDesc() {
		return questionDesc;
	}
	public void setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
	}
	public List<SurveyAnswerBean> getSurveyAnswerBean() {
		return surveyAnswerBean;
	}
	public void setSurveyAnswerBean(List<SurveyAnswerBean> surveyAnswerBean) {
		this.surveyAnswerBean = surveyAnswerBean;
	} 
	
	public String toString(){
		
		return "{ " + questionId + " ," + questionDesc + " ," + surveyAnswerBean + "}";
	}
	
}
