package com.retailshop.action.admin;

public class SurveyAnswerBean {
	
	private int answerId;
	private String answerDesc;
	private String answerType;
	private String cssClass;
	private String moreProperty;
	public int getAnswerId() {
		return answerId;
	}
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}
	public String getAnswerDesc() {
		return answerDesc;
	}
	public void setAnswerDesc(String answerDesc) {
		this.answerDesc = answerDesc;
	}
	public String getAnswerType() {
		return answerType;
	}
	public void setAnswerType(String answerType) {
		this.answerType = answerType;
	}
	public String getCssClass() {
		return cssClass;
	}
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
	public String getMoreProperty() {
		return moreProperty;
	}
	public void setMoreProperty(String moreProperty) {
		this.moreProperty = moreProperty;
	}
	
	public String toString(){
		
		return "[" + answerId + ", " + answerDesc + " ," + answerType + " ," + cssClass + ", " + moreProperty + "]";
	}
	
	
}
