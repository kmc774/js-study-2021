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
			            	   setupAttribute.logicAllAttribute("이미 로그인 된 회원입니다.", "/index", request, response);
			               } break;
			               
			               case "login": if(session.getAttribute("member") != null) {
			            	   setupAttribute.logicAllAttribute("해당 경로에 접근할 수 없습니다.", "/index", request, response);
			               } break;
			               
			               case "logout": if(session.getAttribute("member") == null) {
			            	   setupAttribute.logicAllAttribute("해당 경로에 접근할 수 없습니다.", "/member/loginForm", request, response);
			               } break;
		               
		               
		               } // member 관련 끝
		               
		               break;
		            case "board" :
			            switch(uriArr[2]) {
			            	case "write" : if(session.getAttribute("member") == null) {
			            		setupAttribute.logicAllAttribute("로그인 후 이용가능합니다.", "/member/loginForm", request, response);
			            	} break;
			            	
			            	case "modify" : if(session.getAttribute("member") == null) {
			            		setupAttribute.logicAllAttribute("로그인 후 이용가능합니다.", "/member/loginForm", request, response);
			            	} break;
			            	
			            	case "secretview" : if(session.getAttribute("member") == null) {
			            		setupAttribute.logicAllAttribute("접근할 수 없는 경로입니다.", "/index", request, response);
			            	} break;
			            	
			            	case "delete" : if(session.getAttribute("member") == null) {
			            		setupAttribute.logicAllAttribute("로그인 후 이용가능합니다.", "/member/loginForm", request, response);
			            	} break;
			            	
			            	case "recommend" : if(session.getAttribute("member") == null) {
			            		setupAttribute.logicAllAttribute("로그인 후 이용가능합니다.", "/member/loginForm", request, response);
			            	} break;
			            }
			            
			            break;
		            }
		         }
			return true;
		}
		 
 

}
