package com.prac.spring.common.interceptor;



import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
public class AuthInterceptor implements HandlerInterceptor{
	
	
	
		//HandlerInterceptor를 사용해서 Contoller에 매핑되기 전에 모든 URL을 인터셉트
		 @Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws Exception {
			
			 
			  HttpSession session =request.getSession(); // 세션값 가져오기
		      String[] uriArr = request.getRequestURI().split("/");
		      
		      
		      
		      if(uriArr.length > 0) {
		            switch (uriArr[1]) {
		            case "member":
		               switch (uriArr[2]) {
		               case "loginForm": if(session.getAttribute("member") != null) {
		            	   request.setAttribute("alertMsg", "이미 로그인된 회원입니다.");
		            	   request.setAttribute("url", "/index");
		            	   RequestDispatcher rd = request.getRequestDispatcher("/result");
		            	   rd.forward(request, response);
		               } break;
		               
		               case "login": if(session.getAttribute("member") != null) {
		            	   request.setAttribute("alertMsg", "해당 경로에 접근할 수 없습니다.");
		            	   request.setAttribute("url", "/index");
		            	   RequestDispatcher rd = request.getRequestDispatcher("/result");
		            	   rd.forward(request, response);
		               } break;
		               
		               case "logout": if(session.getAttribute("member") == null) {
		            	   request.setAttribute("alertMsg", "해당 경로에 접근할 수 없습니다.");
		            	   request.setAttribute("url", "/member/loginForm");
		            	   RequestDispatcher rd = request.getRequestDispatcher("/result");
		            	   rd.forward(request, response);
		               } break;
		               
		               } // member 관련 끝
		               
		               break;
		               
		               
		            case "board":
		            		if(session.getAttribute("member") == null) {
			            	   request.setAttribute("alertMsg", "로그인 후 이용가능합니다.");
			            	   request.setAttribute("url", "/member/loginForm");
			            	   RequestDispatcher rd = request.getRequestDispatcher("/result");
			            	   rd.forward(request, response);
		            		}
		               break;
		            }
		         }
			 
			 
			 
			return true;
		}

}
