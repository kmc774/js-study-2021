package com.prac.spring.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.prac.spring.member.model.service.MemberService;
import com.prac.spring.member.model.vo.Member;


@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	
	private final MemberService memberService;
	
	
	//의존성 주입
	public MemberController(MemberService memberService) {
		
		this.memberService = memberService;
	}
	
	
	
	
	
	
	
	// 회원가입 페이지 이동
	@GetMapping("joinForm")
	public String joinForm() {
		
		return "member/join_form";
	}
	
	
	
	
	@PostMapping("join")
	public String join(String userId, String password, Model model) {
		
		
		Member member = new Member();
		member.setUserId(userId);
		member.setPassword(password);
		
		int result = memberService.InsertMember(member);
		
		if(result > 0) {
			model.addAttribute("alertMsg","회원가입이 완료되었습니다.");
			model.addAttribute("url","loginForm");
		} else {
			model.addAttribute("alertMsg","회원가입에 실패하였습니다.");
			model.addAttribute("url","joinForm");
		}
		
		
		
		return "common/result";
	}
	
	

	
	
	// 로그인 페이지 이동
	@GetMapping("loginForm")
	public String moveLogin() {
		
		return "member/login_form";
	}
	
	
	
	
	
	// 로그인 진행
	@PostMapping("login")
	public String login(String userId, String password , HttpSession session, Model model) {
		Member member = new Member();
		member.setUserId(userId);
		member.setPassword(password);
		
		// 해당 유저 찾기(서비스 단에서 인코딩된 비번확인)
		Member authMember = memberService.loginMember(member);
		if(authMember != null) {
			// 세션에 로그인정보 저장
			session.setAttribute("member", authMember);
			model.addAttribute("alertMsg","로그인되었습니다.");
			model.addAttribute("url","/index");
		} else {
			model.addAttribute("alertMsg","아이디, 비밀번호를 다시 확인해주세요");
			model.addAttribute("url","loginForm");
		}
		return "common/result";
	}
	
	
	
	// 로그아웃하기
	@GetMapping("logout")
	public String logout(HttpSession session) {
		
		memberService.logout(session);
		
		return "/home";
	}

}
