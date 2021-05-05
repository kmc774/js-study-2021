package com.windfall.cereal.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.windfall.cereal.dto.MemberVO;
import com.windfall.cereal.service.MemberService;

@Controller
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Inject
	public MemberService service;
	
	@GetMapping("/register")
	public String register() {
		logger.info("==register==");
		return "member/register";
	}
	
	@PostMapping("/memberSave.do")
	public String memeberSave(Model model, MemberVO vo) throws Exception {
		logger.info("==member save==");
		int result = service.insertMember(vo);
		return "redirect:/";
	}
	
	@GetMapping("/loginForm")
	public String loginForm() {
		logger.info("==loginForm==");
		return "member/loginForm";
	}
	
	@PostMapping("/login.do")
	public boolean login(MemberVO vo, HttpServletRequest res, RedirectAttributes rttr) throws Exception{
		logger.info("==login==");
		MemberVO loginMember = service.loginMember(vo);
		HttpSession session =  res.getSession();
		
		if(loginMember == null) {
			
			return false;
		
		}else {
			session.setAttribute("session", loginMember);

			return true;
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) throws Exception{
		logger.info("==logout==");
		session.invalidate();
		
		return "redirect:/";
	}
	
}
