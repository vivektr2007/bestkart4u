package com.retailshop.action;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

public class LogoutAction implements SessionAware {

	private Map session;
	
	public String execute(){
		
		session.clear();
		((SessionMap)session).invalidate();
	
		
		return "success";
	}

	@Override
	public void setSession(Map session) {
		this.session = session;
	}
	
	
	
	
}
