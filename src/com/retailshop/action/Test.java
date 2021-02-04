package com.retailshop.action;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.opensymphony.xwork2.util.ValueStack;

public class Test implements Interceptor {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		ValueStack valueStack = invocation.getStack();
		valueStack.findString("name");
		
		System.out.println("hello");
		
		String res = invocation.invoke();
		
		System.out.println("before");
		
		return res;
		
	}

}
