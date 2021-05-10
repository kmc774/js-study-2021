package com.prac.spring.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping(value="joinForm", method=RequestMethod.GET)
	public String joinForm() {
		
		return "member/join_form";
	}
	
	
	
	@RequestMapping(value="join", method=RequestMethod.POST)
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
	@RequestMapping(value="loginForm", method=RequestMethod.GET)
	public String moveLogin() {
		
		return "member/login_form";
	}
	
	
	
	
	
	/**
	 * 
	 * @param member
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value="login", method=RequestMethod.POST)
	@ResponseBody
	public String login(@RequestBody Member member, HttpSession session, Model model) {
	
		// �ش� ���� ã��(���� �ܿ��� ���ڵ��� ���Ȯ��)
		Member authMember = memberService.loginMember(member);
		if(authMember != null) {
			// ���ǿ� �α������� ����
			session.setAttribute("member", authMember);
			return "success";
		} else  {
			return "fail";
		} 
	}
	
	
	
	// �α׾ƿ��ϱ�
	@GetMapping("logout")
	public String logout(HttpSession session) {
		
		memberService.logout(session);
		
		return "/home";
	}

}
