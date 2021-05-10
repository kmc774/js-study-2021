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
	
	
	//������ ����
	public MemberController(MemberService memberService) {
		
		this.memberService = memberService;
	}
	
	
	
	
	
	
	
	// ȸ������ ������ �̵�
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
			model.addAttribute("alertMsg","ȸ�������� �Ϸ�Ǿ����ϴ�.");
			model.addAttribute("url","loginForm");
		} else {
			model.addAttribute("alertMsg","ȸ�����Կ� �����Ͽ����ϴ�.");
			model.addAttribute("url","joinForm");
		}
		
		
		
		return "common/result";
	}
	
	

	
	
	// �α��� ������ �̵�
	@GetMapping("loginForm")
	public String moveLogin() {
		
		return "member/login_form";
	}
	
	
	
	
	
	// �α��� ����
	@PostMapping("login")
	public String login(String userId, String password , HttpSession session, Model model) {
		Member member = new Member();
		member.setUserId(userId);
		member.setPassword(password);
		
		// �ش� ���� ã��(���� �ܿ��� ���ڵ��� ���Ȯ��)
		Member authMember = memberService.loginMember(member);
		if(authMember != null) {
			// ���ǿ� �α������� ����
			session.setAttribute("member", authMember);
			model.addAttribute("alertMsg","�α��εǾ����ϴ�.");
			model.addAttribute("url","/index");
		} else {
			model.addAttribute("alertMsg","���̵�, ��й�ȣ�� �ٽ� Ȯ�����ּ���");
			model.addAttribute("url","loginForm");
		}
		return "common/result";
	}
	
	
	
	// �α׾ƿ��ϱ�
	@GetMapping("logout")
	public String logout(HttpSession session) {
		
		memberService.logout(session);
		
		return "/home";
	}

}
