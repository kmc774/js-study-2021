package com.prac.spring.common.interceptor;




import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.prac.spring.common.setup.SetupAttribute;
public class AuthInterceptor implements HandlerInterceptor{
	
		@Autowired
		private SetupAttribute setupAttribute;
	

	
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
			            	   setupAttribute.logicAllAttribute("�̹� �α��� �� ȸ���Դϴ�.", "/index", request, response);
			               } break;
			               
			               case "login": if(session.getAttribute("member") != null) {
			            	   setupAttribute.logicAllAttribute("�ش� ��ο� ������ �� �����ϴ�.", "/index", request, response);
			               } break;
			               
			               case "logout": if(session.getAttribute("member") == null) {
			            	   setupAttribute.logicAllAttribute("�ش� ��ο� ������ �� �����ϴ�.", "/member/loginForm", request, response);
			               } break;
		               
		               
		               } // member ���� ��
		               
		               break;
		            case "board" :
			            switch(uriArr[2]) {
			            	case "write" : if(session.getAttribute("member") == null) {
			            		setupAttribute.logicAllAttribute("�α��� �� �̿밡���մϴ�.", "/member/loginForm", request, response);
			            	} break;
			            	
			            	case "modify" : if(session.getAttribute("member") == null) {
			            		setupAttribute.logicAllAttribute("�α��� �� �̿밡���մϴ�.", "/member/loginForm", request, response);
			            	} break;
			            	
			            	case "secretview" : if(session.getAttribute("member") == null) {
			            		setupAttribute.logicAllAttribute("������ �� ���� ����Դϴ�.", "/index", request, response);
			            	} break;
			            	
			            	case "delete" : if(session.getAttribute("member") == null) {
			            		setupAttribute.logicAllAttribute("�α��� �� �̿밡���մϴ�.", "/member/loginForm", request, response);
			            	} break;
			            	
			            	case "recommend" : if(session.getAttribute("member") == null) {
			            		setupAttribute.logicAllAttribute("�α��� �� �̿밡���մϴ�.", "/member/loginForm", request, response);
			            	} break;
			            }
			            
			            break;
		            }
		         }
			return true;
		}
		 
 

}
