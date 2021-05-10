package com.prac.spring.common.setup;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class SetupAttribute {
	
	 
	public SetupAttribute() {
		
	}
	
	/**
	  * alertMsg, url을 모두 받아서 result로 넘겨주는 메소드
	  * @param alertMsg
	  * @param url
	  * @param resultUrl 
	  * @param request 
	  * @param response 
	  * @throws ServletException  
	  * @throws IOException */
	 public void logicAllAttribute(String alertMsg, String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		 request.setAttribute("alertMsg", alertMsg);
		 request.setAttribute("url", url);
		 RequestDispatcher rd = request.getRequestDispatcher("/result");
		 rd.forward(request, response);
		 
	 }
	 
	
	 
	 /**
	  * alertMsg, url을 모두 받아서 model에 추가해주는 메소드
	  * @param alertMsg
	  * @param url
	  * @param resultUrl 
	  * @param request 
	  * @param response 
	  * @throws ServletException  
	  * @throws IOException */
	 public void modelAllAttribute(String alertMsg, String url, Model model){
			 
			 model.addAttribute("alertMsg", alertMsg);
			 model.addAttribute("url", url);
		 }
	 
	 

	

}
