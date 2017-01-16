package it.spring.mvc.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LogHandlerInterceptor implements HandlerInterceptor {
	

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("=== AFTER COMPLETION ===");
        System.out.println("Response ContentType: " + arg1.getContentType());
        System.out.println("------------------------");
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("=== POST HANDLE ==="); 
        System.out.println("View name: " + arg3.getViewName());
        System.out.println("Model: " + arg3.getModel());
        System.out.println("-------------------");
	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("=== PRE HANDLE ===");
        System.out.println("Request URI: " + arg0.getRequestURI()); 
        System.out.println("Request Method: " + arg0.getMethod());
        System.out.println("Request Query String: " + arg0.getQueryString());
        System.out.println("-------------------");
        return true;
	}
	

}
