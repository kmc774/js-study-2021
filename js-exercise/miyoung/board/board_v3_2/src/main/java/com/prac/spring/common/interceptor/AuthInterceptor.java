package com.prac.spring.common.interceptor;



import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
public class AuthInterceptor implements HandlerInterceptor{
	
	
	
		//HandlerInterceptor�� ����ؼ� Contoller�� ���εǱ� ���� ��� URL�� ���ͼ�Ʈ
		 @Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws Exception {
			
			 
			  HttpSession session =request.getSession(); // ���ǰ� ��������
		      String[] uriArr = request.getRequestURI().split("/");
		      
		      
		      
		      if(uriArr.length > 0) {
		            switch (uriArr[1]) {
		            case "member":
		               switch (uriArr[2]) {
		               case "loginForm": if(session.getAttribute("member") != null) {
		            	   request.setAttribute("alertMsg", "�̹� �α��ε� ȸ���Դϴ�.");
		            	   request.setAttribute("url", "/index");
		            	   RequestDispatcher rd = request.getRequestDispatcher("/result");
		            	   rd.forward(request, response);
		               } break;
		               
		               case "login": if(session.getAttribute("member") != null) {
		            	   request.setAttribute("alertMsg", "�ش� ��ο� ������ �� �����ϴ�.");
		            	   request.setAttribute("url", "/index");
		            	   RequestDispatcher rd = request.getRequestDispatcher("/result");
		            	   rd.forward(request, response);
		               } break;
		               
		               case "logout": if(session.getAttribute("member") == null) {
		            	   request.setAttribute("alertMsg", "�ش� ��ο� ������ �� �����ϴ�.");
		            	   request.setAttribute("url", "/member/loginForm");
		            	   RequestDispatcher rd = request.getRequestDispatcher("/result");
		            	   rd.forward(request, response);
		               } break;
		               
		               } // member ���� ��
		               
		               break;
		               
		               
		            case "board":
		            		if(session.getAttribute("member") == null) {
			            	   request.setAttribute("alertMsg", "�α��� �� �̿밡���մϴ�.");
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
